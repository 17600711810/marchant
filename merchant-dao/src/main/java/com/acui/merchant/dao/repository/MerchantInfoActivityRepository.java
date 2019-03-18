package com.acui.merchant.dao.repository;

import com.acui.merchant.dao.entity.MerchantInfActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MerchantInfoActivityRepository extends JpaRepository<MerchantInfActivityEntity, String> {
    public List<MerchantInfActivityEntity> findAllBymerchantId(String merchantId);
}
