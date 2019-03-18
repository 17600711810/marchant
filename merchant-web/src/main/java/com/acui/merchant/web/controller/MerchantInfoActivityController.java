package com.acui.merchant.web.controller;

import com.acui.merchant.common.utils.Result;
import com.acui.merchant.common.utils.ResultCode;
import com.acui.merchant.dao.entity.MerchantInfActivityEntity;
import com.acui.merchant.dao.entity.MerchantInfoEntity;
import com.acui.merchant.web.annotator.CurrentUser;
import com.acui.merchant.web.service.MerchantInfoActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class MerchantInfoActivityController {

    Logger logger = LoggerFactory.getLogger(MerchantInfoActivityController.class);
    
    @Autowired
    private MerchantInfoActivityService merchantInfoActivityService;

    @RequestMapping(value = "/findActivityList", method = RequestMethod.POST)
    public Result findActivityListByMerchantId(@CurrentUser MerchantInfoEntity merchantInfoEntity){
        Result result = null;
        try {
            List<MerchantInfActivityEntity> merchantInfoActivityList = merchantInfoActivityService.findMerchantInfoActivityByMerchantId(merchantInfoEntity.getId());
            result = Result.success(merchantInfoActivityList);
        }catch (Exception e){
            logger.error("获取活动列表失败，服务器异常！！！",e);
            result = Result.failure(ResultCode.FAILURE,"获取活动列表失败，服务器异常！！！");
        }finally {
            return result;
        }
    }

    @RequestMapping(value = "/saveActivity", method = RequestMethod.POST)
    public Result saveMerchantInfoActivity(@CurrentUser MerchantInfoEntity merchantInfoEntity,
                                           @RequestBody MerchantInfActivityEntity merchantInfActivityEntity){
        Result result = null;
        try {
            merchantInfActivityEntity.setMerchantId(merchantInfoEntity.getId());
            merchantInfActivityEntity = merchantInfoActivityService.save(merchantInfActivityEntity);
            result = Result.success(merchantInfActivityEntity);
        }catch (Exception e){
            logger.error("添加活动失败，服务器异常！！！",e);
            result = Result.failure(ResultCode.FAILURE,"添加活动失败，服务器异常！！！");
        }finally {
            return result;
        }
    }

    @RequestMapping("/uploadBackgroundImg")
    public Result uploadBackgroundImg(@CurrentUser MerchantInfoEntity merchantInfoEntity,
                                      @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Result result = null;
        try {
            String backgroundImgUrl = merchantInfoActivityService.uploadBackgroundImg(
                    merchantInfoEntity, file, request.getSession().getServletContext().getRealPath(""));
            if (backgroundImgUrl == null){
                result = Result.failure(ResultCode.FAILURE,"上传图片失败，文件为空或非jpg文件！！！");
            }else{
                result = Result.success("{\"backgroundImgUrl\":"+backgroundImgUrl+"}");
            }
        }catch (Exception e){
            logger.error("上传图片失败，服务器异常！！！",e);
            result = Result.failure(ResultCode.FAILURE,"上传图片失败，服务器异常！！！");
        }finally {
            return result;
        }
    }
}
