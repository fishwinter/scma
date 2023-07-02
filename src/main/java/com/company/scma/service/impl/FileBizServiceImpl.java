package com.company.scma.service.impl;

import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.UploadFileDTO;
import com.company.scma.common.util.Base64Util;
import com.company.scma.common.vo.DownloadFileVO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.FileBizService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.*;

@Service
public class FileBizServiceImpl implements FileBizService {
    @Value("${fileupload.path}")
    private String fileuploadPath;

    @Override
    public Result uploadFile(UploadFileDTO uploadFileDTO) throws IOException {
        MultipartFile multipartFile = Base64Util.base64ToMultipart(uploadFileDTO);

        String randomFilename = multipartFile.getOriginalFilename();
        //获取根目录
        ApplicationHome applicationHome = new ApplicationHome(getClass());
        File source = applicationHome.getSource();
        //生成存储目录
        String dirPath = source.getParentFile().toString() + fileuploadPath;
        File dirFile = new File(dirPath);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        String filePath = dirPath + randomFilename;
        filePath = filePath.replace("\\","/");
        File localFile = new File(filePath);
        multipartFile.transferTo(localFile);
        //返回路径
        return Result.success(filePath);
    }

    @Override
    public Result downloadFile(String fileUrl) {
        File file = new File(fileUrl);
        String fileName = file.getName();
        fileName = fileName.substring(33);
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
        DownloadFileVO downloadFileVO = new DownloadFileVO();
        downloadFileVO.setFileName(fileName);
        downloadFileVO.setFileBase64(encode);
        return Result.success(downloadFileVO);
    }
}
