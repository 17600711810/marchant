package com.acui.merchant.web.service.impl;

import com.acui.merchant.dao.entity.NewsEntity;
import com.acui.merchant.dao.repository.NewsRepository;
import com.acui.merchant.web.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;

    @Override
    public NewsEntity save(NewsEntity newsEntity) {
        newsRepository.save(newsEntity);
        return null;
    }

    @Override
    public List<NewsEntity> findNewsByTypeAndLimit(String s, Integer pageNumber, Integer number) {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setNewsType(s);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().
                withMatcher("newsType",ExampleMatcher.GenericPropertyMatchers.startsWith());
        Example<NewsEntity> example = Example.of(newsEntity ,exampleMatcher);
        Pageable pageable = new PageRequest(pageNumber,number);
        return  newsRepository.findAll(example,new Sort(Sort.Direction.DESC,"create_time"),pageable);
    }
}
