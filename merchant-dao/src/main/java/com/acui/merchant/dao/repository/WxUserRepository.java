package com.acui.merchant.dao.repository;

import com.acui.merchant.dao.entity.WxUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WxUserRepository extends JpaRepository<WxUserEntity, String> {
}
