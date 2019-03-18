package com.acui.merchant.web.service;

import com.acui.merchant.dao.entity.MerchantInfActivityEntity;
import com.acui.merchant.dao.entity.MerchantInfoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MerchantInfoActivityService {

    public List<MerchantInfActivityEntity> findMerchantInfoActivityByMerchantId(String id);

    public MerchantInfActivityEntity save(MerchantInfActivityEntity merchantInfActivityEntity);

    public String uploadBackgroundImg(MerchantInfoEntity merchantInfoEntity, MultipartFile file,  String contextPath) throws Exception;
}
