package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateCaseDTO;
import com.company.scma.common.dto.EditCaseDTO;
import com.company.scma.common.dto.GetCaseDTO;
import com.company.scma.common.po.TCase;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.CaseDetailVO;
import com.company.scma.common.vo.CaseListVO;
import com.company.scma.common.vo.CaseTypeVO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.CaseBizService;
import com.company.scma.service.mapperservice.CaseService;
import com.company.scma.service.mapperservice.SysConfigService;
import com.company.scma.service.validateservice.CaseValidateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CaseBizServiceImpl implements CaseBizService {
    @Autowired
    private CaseValidateService caseValidateService;
    @Autowired
    private CaseService caseService;
    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public Result getAllCase(GetCaseDTO getCaseDTO) {
        //参数校验
        Result result = caseValidateService.validateGetCaseDTO(getCaseDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //数据查询
        IPage<TCase> tCaseIPage = caseService.getCaseByCondition(getCaseDTO);
        //数据封装
        CaseListVO caseListVO = GenerateUtil.getCaseListVO(tCaseIPage);
        //设置case_type的名字
        String caseTypeStr = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.CASE_TYPE);
        List<CaseTypeVO> caseTypeVOList = JSON.parseArray(caseTypeStr, CaseTypeVO.class);
        GenerateUtil.setCaseTypeName(caseListVO.getCaseListRowVOList(),caseTypeVOList);
        //返回
        return Result.success(caseListVO);
    }

    @Override
    public Result getCaseDetail(Integer caseId) {
        //参数校验
        if(ObjectUtil.isEmpty(caseId)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //查询案例
        CaseDetailVO caseDetailVO = new CaseDetailVO();
        TCase tCaseById = caseService.getTCaseById(caseId);
        if(ObjectUtil.isEmpty(tCaseById)){
            return Result.success(caseDetailVO);
        }
        //数据组装
        BeanUtils.copyProperties(tCaseById,caseDetailVO);
        //查询案列类别
        String caseTypeStr = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.CASE_TYPE);
        List<CaseTypeVO> caseTypeVOList = JSON.parseArray(caseTypeStr, CaseTypeVO.class);
        //设置案列类别
        caseDetailVO.setAllCaseType(caseTypeVOList);
        caseDetailVO.setMyCaseType(GenerateUtil.getCaseTypeVO(caseTypeVOList,tCaseById.getTypeId()));
        //返回
        return Result.success(caseDetailVO);
    }

    @Override
    @Transactional
    public Result createCase(CreateCaseDTO createCaseDTO) {
        //参数校验
        Result result = caseValidateService.validateCreateCaseDTO(createCaseDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //创建实体
        TCase tCase = GenerateUtil.getTCase(createCaseDTO);
        //入库
        caseService.save(tCase);
        //返回
        return Result.success();
    }

    @Override
    @Transactional
    public Result editCase(EditCaseDTO editCaseDTO) {
        //参数校验
        Result result = caseValidateService.validateEditCaseDTO(editCaseDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //创建实体
        TCase tCase = GenerateUtil.getTCase(editCaseDTO);
        //入库
        caseService.saveOrUpdate(tCase);
        //返回
        return Result.success();
    }

    @Override
    @Transactional
    public Result deleteCase(Integer caseId) {
        //参数校验
        if(ObjectUtil.isEmpty(caseId)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //删除
        caseService.deleteCaseById(caseId);
        //返回
        return Result.success();
    }
}
