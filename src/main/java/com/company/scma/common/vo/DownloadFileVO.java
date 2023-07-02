package com.company.scma.common.vo;

import lombok.Data;

@Data
public class DownloadFileVO {
    //文件名
    private String fileName;
    //文件base64字符串
    private String fileBase64;
}
