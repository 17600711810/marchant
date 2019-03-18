package com.acui.merchant.web.controller;

import com.acui.merchant.common.utils.Result;
import com.acui.merchant.common.utils.ResultCode;
import com.acui.merchant.dao.entity.MerchantInfoEntity;
import com.acui.merchant.web.annotator.CurrentUser;
import com.acui.merchant.web.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/trade")
public class TradeController {

    Logger logger = LoggerFactory.getLogger(NewsController.class);
    @Autowired
    private TradeService tradeService;

    @RequestMapping(value = "/hotNews", method = RequestMethod.POST)
    public Result hotNews(@CurrentUser MerchantInfoEntity merchantInfoEntity, @RequestParam("file") MultipartFile file){
        Result result = null;
        try {

            result = Result.success(merchantInfoEntity);
        }catch (Exception e){
            logger.error("数据导入失败，服务器异常！！！",e);
            result = Result.failure(ResultCode.FAILURE,"订单数据导入失败，服务器异常！！！");
        }finally {
            return result;
        }
    }
}
