package com.acui.merchant.dao.repository;

import com.acui.merchant.dao.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface NewsRepository extends JpaRepository<NewsEntity, String> {
}
