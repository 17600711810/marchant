package com.acui.merchant.web.controller;

import com.acui.merchant.web.annotator.CurrentUser;
import com.acui.merchant.web.annotator.NotAuthToken;
import com.acui.merchant.dao.entity.MerchantInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/welcome")
    @NotAuthToken
    public String welcome(){
        return "welcome";
    }

    //测试权限访问
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public Object test(@CurrentUser MerchantInfoEntity merchantInfoEntity) {
        logger.info("已进入验证通过"+merchantInfoEntity);
        return merchantInfoEntity;
    }

}
