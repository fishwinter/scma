package com.company.scma.service.bizservice;

import com.company.scma.common.vo.Result;

import java.io.IOException;

public interface FileBizService {
    public Result uploadFile(String fileBase64) throws IOException;
    
    public Result downloadFile(String fileUrl);
}
