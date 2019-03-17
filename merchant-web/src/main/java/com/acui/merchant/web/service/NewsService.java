package com.acui.merchant.web.service;

import com.acui.merchant.dao.entity.NewsEntity;

import java.util.List;

public interface NewsService {
    NewsEntity save(NewsEntity newsEntity);

    List<NewsEntity> findNewsByTypeAndLimit(String s, Integer pageNumber, Integer number);

}
