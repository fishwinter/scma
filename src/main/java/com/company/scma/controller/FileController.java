package com.company.scma.controller;

import cn.hutool.core.util.NumberUtil;
import com.company.scma.common.util.Base64Util;
import com.company.scma.common.vo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "file", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileController {
    @Value("${fileupload.path}")
    private String fileuploadPath;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public Result uploadFile(String fileBase64) {
        try {
            MultipartFile multipartFile = Base64Util.base64ToMultipart(fileBase64);

            String originalFilename = multipartFile.getOriginalFilename();

            // 文件扩展名
            // image格式: "data:image/png;base64," + "图片的base64字符串"
            String ext = originalFilename.substring(originalFilename.lastIndexOf(".")).trim();

            List<String> extList = Arrays.asList(".jpg", ".png", ".jpeg", ".gif");
            if (!extList.contains(ext)) {
                return Result.success("图片格式非法！");
            }

            //String randomFilename = System.currentTimeMillis() + NumberUtil.random(6) + ext;
            String randomFilename = System.currentTimeMillis() + ext;
            //将文件写入服务器
            String fileLocalPath = fileuploadPath + "commentImage/" + randomFilename;
            File localFile = new File(fileLocalPath);
            multipartFile.transferTo(localFile);

            //写入服务器成功后组装返回的数据格式
            Map<String, Object> fileMap = new HashMap<>();
            //文件存放路径
            fileMap.put("filePath", "commentImage/" + randomFilename);
            return Result.success(fileMap);
        } catch (Exception e) {
            Result.success("公众号评价商户上传图片失败：");
        }
        return Result.success();
    }

}
