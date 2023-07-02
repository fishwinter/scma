package com.company.scma.common.dto;

import lombok.Data;

@Data
public class UploadFileDTO {
    //文件名
    private String fileName;
    //文件base64字符串
    private String fileBase64;
}
