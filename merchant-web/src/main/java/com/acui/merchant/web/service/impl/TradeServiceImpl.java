package com.acui.merchant.web.service.impl;

import com.acui.merchant.dao.repository.TradeRepository;
import com.acui.merchant.web.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeRepository tradeRepository;

}
