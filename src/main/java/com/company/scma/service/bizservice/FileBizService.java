package com.company.scma.service.bizservice;

import com.company.scma.common.dto.UploadFileDTO;
import com.company.scma.common.vo.Result;

import java.io.IOException;

public interface FileBizService {
    public Result uploadFile(UploadFileDTO uploadFileDTO) throws IOException;
    
    public Result downloadFile(String fileUrl);
}
