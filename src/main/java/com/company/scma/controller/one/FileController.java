package com.company.scma.controller.one;

import cn.hutool.core.util.NumberUtil;
import com.company.scma.common.dto.UploadFileDTO;
import com.company.scma.common.util.Base64Util;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.FileBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "file", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileController {
    @Autowired
    FileBizService fileBizService;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public Result uploadFile(@RequestBody UploadFileDTO uploadFileDTO) throws IOException {
        return fileBizService.uploadFile(uploadFileDTO);
    }

    @RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
    public Result downloadFile(@RequestBody String fileUrl)  {
        return fileBizService.downloadFile(fileUrl);
    }

}
