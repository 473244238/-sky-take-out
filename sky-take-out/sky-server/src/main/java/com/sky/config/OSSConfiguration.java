package com.sky.config;

import com.sky.properties.AliOssProperties;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类，用于创建AliOss工具类
 */
@Configuration
@Slf4j
public class OSSConfiguration {

    @Bean //项目启动会交给String容器管理
    @ConditionalOnMissingBean //表示整个容器只能创建一个该对象
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties){
       log.info("开始创建阿里云文件上传工具类对象：{}",aliOssProperties);
        return new AliOssUtil(aliOssProperties.getEndpoint(),
               aliOssProperties.getAccessKeyId(),
               aliOssProperties.getAccessKeySecret(),
               aliOssProperties.getBucketName());
   }
}
