package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateAuthorDTO;
import com.company.scma.common.dto.EditAuthorDTO;
import com.company.scma.common.dto.GetAuthorDTO;
import com.company.scma.common.po.TArticle;
import com.company.scma.common.po.TAuthor;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.*;
import com.company.scma.service.bizservice.AuthorBizService;
import com.company.scma.service.mapperservice.ArticleService;
import com.company.scma.service.mapperservice.AuthorService;
import com.company.scma.service.validateservice.AuthorValidateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorBizServiceImpl implements AuthorBizService {
    @Autowired
    private AuthorValidateService authorValidateService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private ArticleService articleService;

    @Override
    public Result getAuthorDetail(Integer authorId) {
        //参数校验
        if(ObjectUtil.isEmpty(authorId)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //数据查询
        TAuthor tAuthor = authorService.getAuthorById(authorId);
        if(ObjectUtil.isEmpty(tAuthor)){
            return Result.success(tAuthor);
        }
        //数据封装
        AuthorDetailVO authorDetailVO = new AuthorDetailVO();
        BeanUtils.copyProperties(tAuthor,authorDetailVO);
        //查询文章信息
        List<TArticle> tArticleList = articleService.getTArticleByAuthorId(authorId);
        //设置文章信息
        if(ObjectUtil.isNotEmpty(tArticleList)){
            List<ArticleListRowVO> articleListRowVOList = tArticleList.stream().map(GenerateUtil::getArticleListRowVO).collect(Collectors.toList());
            authorDetailVO.setArticleListRowVOList(articleListRowVOList);
        }
        //返回
        return Result.success(authorDetailVO);
    }

    @Override
    public Result getAllAuthor(GetAuthorDTO getAuthorDTO) {
        //参数校验
        Result result = authorValidateService.validateGetAuthorDTO(getAuthorDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //数据查询
        IPage<TAuthor> iPage = authorService.getAuthorByCondition(getAuthorDTO);
        //数据封装
        AuthorListVO authorListVO = GenerateUtil.getAuthorListVO(iPage);
        //返回
        return Result.success(authorListVO);
    }

    @Override
    public Result downloadAuthorData(GetAuthorDTO getAuthorDTO) {
        //参数校验
        Result result = authorValidateService.validateGetAuthorDTO(getAuthorDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //数据查询
        IPage<TAuthor> iPage = authorService.getAuthorByCondition(getAuthorDTO);
        if(ObjectUtil.isEmpty(iPage)){
            return Result.getResult(ResultEnum.NO_EXCEL_DATA);
        }
        //数据封装
        List<AuthorExcelVO> authorExcelVOList = GenerateUtil.getAuthorExcelVOList(iPage);
        //输出流
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        EasyExcel.write(bao,AuthorExcelVO.class).sheet(Constant.Common.SHEET_NAME).doWrite(authorExcelVOList);
        //返回
        return Result.success(bao.toByteArray());
    }

    @Override
    @Transactional
    public Result createAuthor(CreateAuthorDTO createAuthorDTO) {
        //参数校验
        Result result = authorValidateService.validateCreateAuthorDTO(createAuthorDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //数据封装
        TAuthor tAuthor = GenerateUtil.getTAuthor(createAuthorDTO);
        //入库
        authorService.save(tAuthor);
        //返回
        return Result.success();
    }

    @Override
    @Transactional
    public Result editAuthor(EditAuthorDTO editAuthorDTO) {
        //参数校验
        Result result = authorValidateService.validateEditAuthorDTO(editAuthorDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //数据封装
        TAuthor tAuthor = GenerateUtil.getTAuthor(editAuthorDTO);
        //入库
        authorService.saveOrUpdate(tAuthor);
        //返回
        return Result.success();
    }

    @Override
    public Result deleteAuthor(Integer authorId) {
        //参数校验
        if(ObjectUtil.isEmpty(authorId)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //删除
        authorService.deleteAuthorById(authorId);
        //返回
        return Result.success();
    }
}
