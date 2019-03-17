package com.acui.marchant.web.interceptor;

import com.acui.marchant.web.annotator.NotAuthToken;
import com.acui.merchant.common.config.MerchantConfig;
import com.acui.merchant.common.utils.BeanUtils;
import com.acui.merchant.common.utils.ConstantKit;
import com.acui.merchant.common.utils.RedisUtils;
import com.acui.merchant.dao.entity.MerchantInfoEntity;
import com.acui.merchant.dao.repository.MerchantInfoRepository;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    Logger log = LoggerFactory.getLogger(AuthorizationInterceptor.class);
    //存放鉴权信息的Header名称，默认是Authorization
    private String httpHeaderName = "Authorization";

    //鉴权失败后返回的错误信息，默认为401 unauthorized
    private String unauthorizedErrorMessage = "401 unauthorized";

    //鉴权失败后返回的HTTP错误码，默认为401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    //存放登录用户模型Key的Request Key
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private MerchantInfoRepository merchantInfoRepository;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 如果打上了NotAuthToken注解则不需要验证token
        if (method.isAnnotationPresent(NotAuthToken.class)) {
            return true;
        }

        String token = request.getParameter(httpHeaderName);
        log.info("Get token from request is {} ", token);
        String merchantId = "";

        if (token != null && token.length() != 0) {
            merchantId = redisUtils.get("token:"+token)+"";
            log.info("Get userId from Redis is {}", merchantId);
        }
        if (merchantId != null && !merchantId.trim().equals("")) {
            Long tokeBirthTime = Long.valueOf(redisUtils.get(token + merchantId)+"");
            log.info("token Birth time is: {}", tokeBirthTime);
            Long diff = System.currentTimeMillis() - tokeBirthTime;log.info("token is exist : {} ms", diff);
            if (diff > ConstantKit.TOKEN_RESET_TIME) {
                redisUtils.expire(token, ConstantKit.TOKEN_EXPIRE_TIME,TimeUnit.HOURS);
                log.info("Reset expire time success!");
                Long newBirthTime = System.currentTimeMillis();
                redisUtils.set(token + merchantId, newBirthTime.toString());
            }

            Map<Object, Object> merchantInfoMap = redisUtils.getHashEntries("merchant_info:" + merchantId);
            MerchantInfoEntity merchantInfoEntity = null;

            if(merchantInfoMap == null || merchantInfoMap.size() == 0){
                Map<String,String> merchantMap = new HashMap<String,String>();
                merchantInfoEntity = merchantInfoRepository.findById(merchantId).get();
                BeanUtils.transformBeanToMap(merchantInfoEntity,merchantMap);
                redisUtils.putAll("merchant_info:" + merchantId,merchantMap);
            }else{
                 merchantInfoEntity = (MerchantInfoEntity) BeanUtils.mapToObject(merchantInfoMap, MerchantInfoEntity.class);
            }

            request.setAttribute(REQUEST_CURRENT_KEY, merchantInfoEntity);
            return true;
        } else {
            JSONObject jsonObject = new JSONObject();

            PrintWriter out = null;
            try {
                response.setStatus(unauthorizedErrorCode);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                jsonObject.put("code", ((HttpServletResponse) response).getStatus());
                jsonObject.put("message", HttpStatus.UNAUTHORIZED);
                out = response.getWriter();
                out.println(jsonObject);

                return false;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            }
        }

        request.setAttribute(REQUEST_CURRENT_KEY, null);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception { }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception { }
}