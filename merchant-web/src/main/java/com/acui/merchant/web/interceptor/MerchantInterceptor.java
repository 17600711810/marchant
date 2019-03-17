package com.acui.merchant.web.interceptor;

import com.acui.merchant.common.utils.NewsUtils;
import com.acui.merchant.dao.entity.MerchantInfoEntity;
import com.acui.merchant.dao.entity.NewsEntity;
import com.acui.merchant.web.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Component
public class MerchantInterceptor extends HandlerInterceptorAdapter {

    Logger log = LoggerFactory.getLogger(MerchantInterceptor.class);

    @Autowired
    NewsService newsService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MerchantInfoEntity merchantInfoEntity = (MerchantInfoEntity)request.getAttribute(AuthorizationInterceptor.REQUEST_CURRENT_KEY);
        Timestamp dueTime = merchantInfoEntity.getDueTime();
        long thisDateTime = new Timestamp(System.currentTimeMillis()).getTime();
        if(dueTime.getTime() > thisDateTime){
            long diff = dueTime.getTime() - thisDateTime;
            long days = diff / (1000 * 60 * 60 * 24);
            if (days < 4){
                NewsEntity newsEntity = new NewsEntity();
                newsEntity.setMerchantId(merchantInfoEntity.getId());
                newsEntity.setCreateTime(new Timestamp(new Date().getTime()));
                newsEntity.setMsg(NewsUtils.NEWMSG_MERCHANT_DEFICIENCY_TIME);
                newsEntity.setNewsType(NewsUtils.NEWSTYP_MERCHANT_DEFICIENCY_TIME);
                newsService.save(newsEntity);
            }
            return true;
        }

        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setMerchantId(merchantInfoEntity.getId());
        newsEntity.setCreateTime(new Timestamp(new Date().getTime()));
        newsEntity.setMsg(NewsUtils.NEWMSG_MERCHANTDUE);
        newsEntity.setNewsType(NewsUtils.NEWSTYP_MERCHANTDUE);
        newsService.save(newsEntity);
        return false;
    }
}
