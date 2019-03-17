package com.acui.merchant.dao.repository;

import com.acui.merchant.dao.entity.NewsEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface NewsRepository extends PagingAndSortingRepository<NewsEntity, String> {
    public List<NewsEntity> findAll(Example<NewsEntity> entityExample, Sort sort, Pageable pageable);
}
