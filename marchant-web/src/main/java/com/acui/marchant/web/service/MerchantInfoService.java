package com.acui.marchant.web.service;

import com.acui.merchant.dao.entity.MerchantInfoEntity;

public interface MerchantInfoService {
    public MerchantInfoEntity login(String username, String password) throws Exception;

    public MerchantInfoEntity findMerchantInfoEntityByUserName(String userName);

    public MerchantInfoEntity save(MerchantInfoEntity merchantInfoEntity);
}
