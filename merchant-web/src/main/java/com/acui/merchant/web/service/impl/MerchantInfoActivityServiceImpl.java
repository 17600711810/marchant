package com.acui.merchant.web.service.impl;

import com.acui.merchant.common.utils.FileUtils;
import com.acui.merchant.dao.entity.MerchantInfActivityEntity;
import com.acui.merchant.dao.entity.MerchantInfoEntity;
import com.acui.merchant.dao.repository.MerchantInfoActivityRepository;
import com.acui.merchant.web.listener.EmbeddedServletListener;
import com.acui.merchant.web.service.MerchantInfoActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MerchantInfoActivityServiceImpl implements MerchantInfoActivityService {

    @Autowired
    private MerchantInfoActivityRepository merchantInfoActivityRepository;

    @Autowired
    private EmbeddedServletListener embeddedServletListener;
    @Override
    public List<MerchantInfActivityEntity> findMerchantInfoActivityByMerchantId(String id) {
        return merchantInfoActivityRepository.findAllBymerchantId(id);
    }

    @Override
    public MerchantInfActivityEntity save(MerchantInfActivityEntity merchantInfActivityEntity) {
        return merchantInfoActivityRepository.save(merchantInfActivityEntity);
    }

    @Override
    public String uploadBackgroundImg(MerchantInfoEntity merchantInfoEntity, MultipartFile file, String contextPath) throws Exception {
        // 放在static下的原因是，存放的是静态文件资源，即通过浏览器输入本地服务器地址，加文件名时是可以访问到的
        String path = "static/backgroundImg/";
        String backgroundImgUrl = null;
        String hostPath = "http://"+embeddedServletListener.getHost()+":"+embeddedServletListener.getPort()+"/";
        if(!file.isEmpty()) {
            String fileName = file.getOriginalFilename();// 获取文件名称,包含后缀
            //获取后缀
            String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
            if (!prefix.equals("jpg")) return null;
            //修改后完整的文件名称
            String newFileName = "activity-"+merchantInfoEntity.getId() + "." + prefix;
            backgroundImgUrl = hostPath+path+newFileName;
            FileUtils.fileupload(file.getBytes(),contextPath, path, newFileName);
        }
        return backgroundImgUrl;
    }

}
