package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateMemberDTO;
import com.company.scma.common.dto.EditMemberDTO;
import com.company.scma.common.dto.GetMyMemberDTO;
import com.company.scma.common.po.TItem;
import com.company.scma.common.po.TMember;
import com.company.scma.common.po.TMemberType;
import com.company.scma.common.po.TUser;
import com.company.scma.common.vo.Result;
import com.company.scma.service.mapperservice.ItemService;
import com.company.scma.service.mapperservice.MemberService;
import com.company.scma.service.mapperservice.MemberTypeService;
import com.company.scma.service.validateservice.CommonValidateService;
import com.company.scma.service.validateservice.MemberValidateService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberValidateServiceImpl implements MemberValidateService {
    @Autowired
    private CommonValidateService commonValidateService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberTypeService memberTypeService;
    @Autowired
    private ItemService itemService;
    
    @Override
    public Result validateCreateMemberDTO(CreateMemberDTO createMemberDTO) {
        if(ObjectUtil.isEmpty(createMemberDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        
        if(!commonValidateService.validateAnnotation(createMemberDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //校验memberName是否重复
        TMember tMember = memberService.getMemberByMemberName(createMemberDTO.getMemberName());
        if(ObjectUtil.isNotEmpty(tMember)){
            return Result.getResult(ResultEnum.EXIST_MEMBER_NAME);
        }
        //校验会员类型是否正确
        TMemberType tMemberType = memberTypeService.getMemberTypeByMemberTypeId(createMemberDTO.getMemberTypeId());
        if(ObjectUtil.isEmpty(tMemberType)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //校验省市code是否正确
        TItem provinceItem = itemService.getItemByItemCodeAndType(createMemberDTO.getProvinceCode(), Constant.ItemType.PROVINCE);
        TItem cityItem = itemService.getItemByItemCodeAndType(createMemberDTO.getCityCode(), Constant.ItemType.CITY);
        if(ObjectUtil.isEmpty(provinceItem) || ObjectUtil.isEmpty(cityItem)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        return Result.success();
    }

    @Override
    public Result validateEditMemberDTO(EditMemberDTO editMemberDTO) {
        if(ObjectUtil.isEmpty(editMemberDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if(!commonValidateService.validateAnnotation(editMemberDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //校验member是否为当前用户所属
        TMember tMemberById = memberService.getById(editMemberDTO.getMemberId());
        if(ObjectUtil.isEmpty(tMemberById)){
            return Result.getResult(ResultEnum.ERROR_MEMBER_ID);
        }
        Integer ownerUserid = tMemberById.getOwnerUserid();
        TUser currentUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        Integer currentUserid = currentUser.getUserid();
        if(!currentUserid.equals(ownerUserid)){
            return Result.getResult(ResultEnum.USER_CANNOT_OPERATE_MEMBER);
        }
        //校验memberName是否重复
        TMember tMemberByMemberName = memberService.getMemberByMemberName(editMemberDTO.getMemberName());
        if(ObjectUtil.isNotEmpty(tMemberByMemberName) && !tMemberByMemberName.getMemberId().equals(editMemberDTO.getMemberId())){
            return Result.getResult(ResultEnum.EXIST_MEMBER_NAME);
        }
        //校验会员类型是否正确
        TMemberType tMemberType = memberTypeService.getMemberTypeByMemberTypeId(editMemberDTO.getMemberId());
        if(ObjectUtil.isEmpty(tMemberType)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //校验省市code是否正确
        TItem provinceItem = itemService.getItemByItemCodeAndType(editMemberDTO.getProvinceCode(), Constant.ItemType.PROVINCE);
        TItem cityItem = itemService.getItemByItemCodeAndType(editMemberDTO.getCityCode(), Constant.ItemType.CITY);
        if(ObjectUtil.isEmpty(provinceItem) || ObjectUtil.isEmpty(cityItem)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        return Result.success();
    }

    @Override
    public Result validateGetMyMemberDTO(GetMyMemberDTO getMyMemberDTO) {
        if(ObjectUtil.isEmpty(getMyMemberDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        
        if(!commonValidateService.validateAnnotation(getMyMemberDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        
        return Result.success();
    }
}
