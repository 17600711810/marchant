package com.acui.merchant.web.service.impl;

import com.acui.merchant.dao.repository.BurseRepository;
import com.acui.merchant.web.config.TokenUtils;
import com.acui.merchant.web.service.MerchantInfoService;
import com.acui.merchant.common.config.MerchantConfig;
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

    @Autowired
    private BurseRepository burseRepository;

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
                    if (!merchantInfoEntity.getState().equals("0")) return null;
                    TokenUtils.saveMerchantInfoByMerchantInfoEntity(redisUtils,merchantInfoEntity);
                }
            }
        }else {
            merchantInfoEntity = merchantInfoRepository.findMerchantInfoEntityByUserName(username);
            redisUtils.set("user_name:"+username,merchantInfoEntity.getId());
        }
        if (merchantInfoEntity == null) return null;
        if (!merchantInfoEntity.getState().equals("0")) return null;
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

    @Override
    public MerchantInfoEntity findMerchantInfoEntityByUserName(String userName) {
        MerchantInfoEntity merchantInfoEntity = merchantInfoRepository.findMerchantInfoEntityByUserName(userName);
        if(merchantInfoEntity != null)
        merchantInfoEntity.setBurseEntity(burseRepository.findById(merchantInfoEntity.getId()).get());
        return merchantInfoEntity;
    }

    @Override
    public MerchantInfoEntity save(MerchantInfoEntity merchantInfoEntity){
        return merchantInfoEntity = merchantInfoRepository.save(merchantInfoEntity);
    }

    @Override
    public MerchantInfoEntity findById(String id) {
        MerchantInfoEntity merchantInfoEntity = merchantInfoRepository.findById(id).get();
        if(merchantInfoEntity != null)
        merchantInfoEntity.setBurseEntity(burseRepository.findById(merchantInfoEntity.getId()).get());
        return merchantInfoEntity;
    }

    @Override
    public MerchantInfoEntity updatePassword(MerchantInfoEntity merchantInfoEntity) throws Exception{
        String password = merchantInfoEntity.getPassword();
        String encryPassword = HashUtils.sha1HashEncryPassword(password);
        merchantInfoEntity.setPassword(encryPassword);
        MerchantInfoEntity entity = merchantInfoRepository.save(merchantInfoEntity);
        TokenUtils.saveMerchantInfoByMerchantInfoEntity(redisUtils,entity);
        return entity;
    }
}
