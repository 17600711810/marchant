package com.acui.merchant.dao.repository;

import com.acui.merchant.dao.entity.MerchantInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MerchantInfoRepository extends JpaRepository<MerchantInfoEntity, String> {
   public MerchantInfoEntity findMerchantInfoEntityByUserName(String userName);

}
