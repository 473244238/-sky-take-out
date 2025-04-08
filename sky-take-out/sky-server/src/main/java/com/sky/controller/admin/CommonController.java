package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/*
通用接口
 */

@RestController
@RequestMapping("/admin/common")
@Slf4j
@Api(tags="常用功能模块")
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil; //获取阿里云存储库的基本信息

    /**
     * 文件上传
     * file
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file){
       log.info("文件上传：{}",file);

       try{
           //原始文件名
           String originalFileName=file.getOriginalFilename();

           //截取原始文件的后缀
           String extension =originalFileName.substring(originalFileName.lastIndexOf("."));

           //构造新文件名称
           String objectName= UUID.randomUUID().toString()+extension;

           //文件的请求路径

               String filePath=aliOssUtil.upload(file.getBytes(),objectName);
               return Result.success(filePath);

       }catch (IOException e){
           log.info("文件上传失败");
       }
        return Result.error("文件上传失败");
    }

}
