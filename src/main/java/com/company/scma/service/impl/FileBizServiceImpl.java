package com.company.scma.service.impl;

import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.util.Base64Util;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.FileBizService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.*;

@Service
public class FileBizServiceImpl implements FileBizService {
    @Value("${fileupload.path}")
    private String fileuploadPath;

    @Override
    public Result uploadFile(String fileBase64) throws IOException {
        MultipartFile multipartFile = Base64Util.base64ToMultipart(fileBase64);

        String originalFilename = multipartFile.getOriginalFilename();

        // 文件扩展名
        // image格式: "data:image/png;base64," + "图片的base64字符串"
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".")).trim();

        String randomFilename = UUID.randomUUID() + ext;
        randomFilename = randomFilename.replace("-", "");
        //获取根目录
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        //获取存储目录
        File upload = new File(path.getAbsoluteFile(), fileuploadPath);
        if (!upload.exists()) {
            upload.mkdirs();
        }
        //String fileLocalPath = fileuploadPath + "commentImage/" + randomFilename;
        String filePath = upload.getPath() + "\\" + randomFilename;
        //替换一下符号，linux平台也可以使用
        filePath = filePath.replace("\\", "/");
        File localFile = new File(filePath);
        multipartFile.transferTo(localFile);
        //返回路径
        return Result.success(filePath);
    }

    @Override
    public Result downloadFile(String fileUrl) {
        File file = new File(fileUrl);
        InputStream in = null;
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = in.read(bytes)) != -1) {
                bao.write(bytes, 0, len);
            }
            if(in != null){
                in.close();
            }
            if(bao != null){
                bao.close();
            }
        } catch (FileNotFoundException e) {
            return Result.getResult(ResultEnum.ERROR_PARAM);
        } catch (IOException e) {
            return Result.getResult(ResultEnum.UNKNOWN_ERROR);
        }
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(bao.toByteArray());
        //去除换行符，否则解析有问题
        encode = encode.replaceAll("\r|\n", "");
        return Result.success(encode);
    }
}
