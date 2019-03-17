package com.acui.merchant.web.config;

import com.acui.merchant.common.utils.BeanUtils;
import com.acui.merchant.common.utils.ConstantKit;
import com.acui.merchant.common.utils.RedisUtils;
import com.acui.merchant.common.utils.StringUtils;
import com.acui.merchant.dao.entity.MerchantInfoEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TokenUtils {
    public static String setToken(RedisUtils redisUtils, MerchantInfoEntity merchantInfoEntity){
        String token = StringUtils.getUUID();
        redisUtils.set("token:"+token,merchantInfoEntity.getId());
        redisUtils.expire("toekn:"+token, ConstantKit.TOKEN_EXPIRE_TIME, TimeUnit.HOURS);
        redisUtils.set(token + merchantInfoEntity.getId(),System.currentTimeMillis()+"");
        return token;
    }

    public static void saveMerchantInfoByMerchantInfoEntity(RedisUtils redisUtils, MerchantInfoEntity merchantInfoEntity)throws Exception{
        Map<String,String> merchantMap = new HashMap<String,String>();
        BeanUtils.transformBeanToMap(merchantInfoEntity,merchantMap);
        redisUtils.putAll("merchant_info:" + merchantInfoEntity.getId(),merchantMap);
    }

}
