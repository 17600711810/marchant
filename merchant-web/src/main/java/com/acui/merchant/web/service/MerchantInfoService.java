package com.acui.merchant.web.service;

import com.acui.merchant.dao.entity.MerchantInfoEntity;

public interface MerchantInfoService {
    public MerchantInfoEntity login(String username, String password) throws Exception;

    public MerchantInfoEntity findMerchantInfoEntityByUserName(String userName);

    public MerchantInfoEntity save(MerchantInfoEntity merchantInfoEntity);

    public MerchantInfoEntity findById(String id);

    public MerchantInfoEntity updatePassword(MerchantInfoEntity merchantInfoEntity)throws Exception;
}
