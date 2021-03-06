package com.acui.merchant.web.controller;

import com.acui.merchant.web.annotator.CurrentUser;
import com.acui.merchant.web.config.TokenUtils;
import com.acui.merchant.web.service.MerchantInfoService;
import com.acui.merchant.common.utils.*;
import com.acui.merchant.dao.entity.MerchantInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/merchant")
public class MerchantInfoController {
    Logger logger = LoggerFactory.getLogger(MerchantInfoController.class);

    @Autowired
    MerchantInfoService merchantInfoService;

    @Autowired
    RedisUtils redisUtils;
    //登陆
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(String userName, String password) {
        Result result = null;
        try {
            logger.info("" +
                    "已进入登陆：-u:"+userName+"  -p:"+password);
            MerchantInfoEntity merchantInfoEntity = merchantInfoService.login(userName, password);
            if (merchantInfoEntity == null){
                result = Result.failure(ResultCode.USER_LOGIN_ERROR,ResultCode.USER_LOGIN_ERROR.message());
            }else{
                String token = StringUtils.getUUID();
                result.setToken(token);
                TokenUtils.setToken(redisUtils,merchantInfoEntity);
                result = Result.success(merchantInfoEntity);
            }
        }catch (Exception e){
            logger.error("登陆失败，服务器异常！！！",e);
            result = Result.failure(ResultCode.FAILURE,"登陆失败，服务器异常！！！");
        }finally {
            return  result;
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(MerchantInfoEntity merchantInfoEntity) {
        Result result = null;

        try {
            if (merchantInfoEntity == null){
                Result.failure(ResultCode.PARAM_IS_BLANK);
            }
            if (merchantInfoEntity.getUserName() == null
                    || merchantInfoEntity.getUserName().trim().equals("")
                    || merchantInfoEntity.getPassword() == null
                    || merchantInfoEntity.getPassword().trim().equals(""))
            {
                Result.failure(ResultCode.PARAM_NOT_COMPLETE);
            }

            if (redisUtils.get("user_name:"+merchantInfoEntity.getUserName()) != null
                    || merchantInfoService.findMerchantInfoEntityByUserName(merchantInfoEntity.getUserName()) != null){
                Result.failure(ResultCode.PARAM_IS_INVALID,"账号已存在！");
            }

            String token = StringUtils.getUUID();
            merchantInfoEntity.setCreateTime(new Timestamp(new Date().getTime()));
            if(merchantInfoEntity.getMerchantName() == null) merchantInfoEntity.setMerchantName(StringUtils.getUUID());
            merchantInfoEntity.setState("0");
            merchantInfoEntity = merchantInfoService.save(merchantInfoEntity);
            result.setToken(token);
            TokenUtils.setToken(redisUtils,merchantInfoEntity);
            result = Result.success(merchantInfoEntity);
        }catch (Exception e){
            logger.error("登陆失败，服务器异常！！！",e);
            result = Result.failure(ResultCode.FAILURE,"注册失败，服务器异常！！！");
        }finally {
            return  result;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Result logout(@CurrentUser MerchantInfoEntity merchantInfoEntity){
        Result result = null;
        try{
            redisUtils.delete("token:"+merchantInfoEntity.getId());
            result = Result.success();
        }catch (Exception e){
            logger.error("失败，服务器异常！！！",e);
            result = Result.failure(ResultCode.FAILURE,"退出失败，服务器异常！！！");
        }finally{
            return result;
        }
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public Result updatePassword(@CurrentUser MerchantInfoEntity merchantInfoEntity){
        Result result = null;
        try {
            merchantInfoEntity = merchantInfoService.updatePassword(merchantInfoEntity);
            result = Result.success(merchantInfoEntity);
        }catch (Exception e){
            logger.error("失败，服务器异常！！！",e);
            result = Result.failure(ResultCode.FAILURE,"密码更改失败，服务器异常！！！");
        }finally {
            return result;
        }
    }
}
