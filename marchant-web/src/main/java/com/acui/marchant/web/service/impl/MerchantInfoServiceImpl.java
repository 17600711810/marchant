package com.acui.marchant.web.service.impl;

import com.acui.marchant.web.service.MerchantInfoService;
import com.acui.merchant.common.config.MerchantConfig;
import com.acui.merchant.common.utils.ConstantKit;
import com.acui.merchant.common.utils.HashUtils;
import com.acui.merchant.common.utils.RedisUtils;
import com.acui.merchant.dao.entity.MerchantInfoEntity;
import com.acui.merchant.dao.repository.MerchantInfoRepository;
import com.acui.merchant.common.utils.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class MerchantInfoServiceImpl implements MerchantInfoService {

    @Autowired
    private MerchantInfoRepository merchantInfoRepository;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    @Transactional
    public MerchantInfoEntity login(String username, String password) throws Exception{
        String merchantId = redisUtils.get("user_name:" + username);
        MerchantInfoEntity merchantInfoEntity = null;
        if (merchantId != null){
            Map<Object, Object> merchantInfoMap = redisUtils.getHashEntries("merchant_info:" + merchantId);
            merchantInfoEntity = (MerchantInfoEntity) BeanUtils.mapToObject(merchantInfoMap, MerchantInfoEntity.class);
            if (merchantInfoEntity == null){
                merchantInfoEntity = merchantInfoRepository.findMerchantInfoEntityByUserName(username);
                if (merchantInfoEntity != null){
                    Map<String,String> merchantMap = new HashMap<String,String>();
                    BeanUtils.transformBeanToMap(merchantInfoEntity,merchantMap);
                    redisUtils.putAll("merchant_info:" + merchantId,merchantMap);
                }
            }
        }else {
            merchantInfoEntity = merchantInfoRepository.findMerchantInfoEntityByUserName(username);
            redisUtils.set("user_name:"+username,merchantInfoEntity.getId());
        }
        if (merchantInfoEntity == null) return null;
        if (merchantInfoEntity.getPassword().equals(HashUtils.sha1HashEncryPassword(password))){
            merchantInfoEntity.setLoginTime(new Timestamp(new Date().getTime()));
            merchantInfoRepository.save(merchantInfoEntity);
            Map<String, String> map = new HashMap<String,String>();
            BeanUtils.transformBeanToMap(merchantInfoEntity,map);
            redisUtils.putAll("user:" + merchantInfoEntity.getId(),map);
            redisUtils.expire("user:" + merchantInfoEntity.getId(), MerchantConfig.MERCHANTINFOCACHTIME, TimeUnit.SECONDS );
            return merchantInfoEntity;
        }
        return null;
    }

    public MerchantInfoEntity findMerchantInfoEntityByUserName(String userName) {
        return merchantInfoRepository.findMerchantInfoEntityByUserName(userName);
    }
    public MerchantInfoEntity save(MerchantInfoEntity merchantInfoEntity){
        return merchantInfoEntity = merchantInfoRepository.save(merchantInfoEntity);
    }
}
