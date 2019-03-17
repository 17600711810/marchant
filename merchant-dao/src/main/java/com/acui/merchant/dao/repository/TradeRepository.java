package com.acui.merchant.dao.repository;

import com.acui.merchant.dao.entity.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<TradeEntity, String> {
}
