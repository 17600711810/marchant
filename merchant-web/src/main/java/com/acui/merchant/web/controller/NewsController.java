package com.acui.merchant.web.controller;

import com.acui.merchant.common.utils.Result;
import com.acui.merchant.common.utils.ResultCode;
import com.acui.merchant.dao.entity.MerchantInfoEntity;
import com.acui.merchant.dao.entity.NewsEntity;
import com.acui.merchant.web.annotator.CurrentUser;
import com.acui.merchant.web.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    Logger logger = LoggerFactory.getLogger(NewsController.class);
    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/hotNews", method = RequestMethod.POST)
    public Result hotNews(@CurrentUser MerchantInfoEntity merchantInfoEntity,@RequestParam(required = true,defaultValue = "0") Integer pageNumber, @RequestParam(required = true,defaultValue = "5") Integer pageSize){
        Result result = null;
        try {
           List<NewsEntity> newsEntityList = newsService.findNewsByTypeAndLimit("1",pageNumber,pageSize);
           result = Result.success(newsEntityList);
        }catch (Exception e){
            logger.error("获取消息失败，服务器异常！！！",e);
            result = Result.failure(ResultCode.FAILURE,"获取消息失败，服务器异常！！！");
        }finally {
            return result;
        }
    }

    @RequestMapping(value = "/accountNews", method = RequestMethod.POST)
    public Result accountNews(@CurrentUser MerchantInfoEntity merchantInfoEntity,@RequestParam(required = true,defaultValue = "0") Integer pageNumber, @RequestParam(required = true,defaultValue = "5") Integer pageSize){
        Result result = null;
        try {
            List<NewsEntity> newsEntityList = newsService.findNewsByTypeAndLimit("2",pageNumber,pageSize);
            result = Result.success(newsEntityList);
        }catch (Exception e){
            logger.error("获取消息失败，服务器异常！！！",e);
            result = Result.failure(ResultCode.FAILURE,"获取消息失败，服务器异常！！！");
        }finally {
            return result;
        }
    }
}
