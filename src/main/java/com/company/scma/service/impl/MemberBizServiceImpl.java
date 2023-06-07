package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateMemberDTO;
import com.company.scma.common.dto.EditMemberDTO;
import com.company.scma.common.dto.GetMyMemberDTO;
import com.company.scma.common.po.TItem;
import com.company.scma.common.po.TMember;
import com.company.scma.common.po.TMemberType;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.*;
import com.company.scma.service.bizservice.MemberBizService;
import com.company.scma.service.mapperservice.ItemService;
import com.company.scma.service.mapperservice.MemberService;
import com.company.scma.service.mapperservice.MemberTypeService;
import com.company.scma.service.validateservice.MemberValidateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberBizServiceImpl implements MemberBizService {
    @Autowired
    private MemberValidateService memberValidateService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberTypeService memberTypeService;
    @Autowired
    private ItemService itemService;

    @Override
    public Result getMyMember(GetMyMemberDTO getMyMemberDTO) {
        //参数校验
        Result result = memberValidateService.validateGetMyMemberDTO(getMyMemberDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //数据查询
        IPage<TMember> tMemberIPage = memberService.getMemberByCondition(getMyMemberDTO);
        MemberListVO memberListVO = GenerateUtil.getMemberListVO(tMemberIPage);
        //设置memberTypeName
        List<TMemberType> allType = memberTypeService.getAllType();
        GenerateUtil.setMemberTypeName(memberListVO.getMemberListRowVOList(),allType);
        //返回
        return Result.success(memberListVO);
    }

    @Override
    public Result getMemberDetail(Integer memberId) {
        //参数校验
        if(ObjectUtil.isEmpty(memberId)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        MemberDetailVO memberDetailVO = new MemberDetailVO();
        //查询所有会员类型
        List<TMemberType> allType = memberTypeService.getAllType();
        List<MemberTypeVO> allMemberTypeVO = GenerateUtil.getMemberTypeVOList(allType);
        //查询省市信息
        List<TItem> allItem = itemService.getAllItem();
        List<ItemVO> allItemVO = GenerateUtil.getItemVOList(allItem);
        //查询当前member信息
        TMember tMember = memberService.getMemberByMemberId(memberId);
        if(ObjectUtil.isEmpty(tMember)){
            return Result.success(memberDetailVO);
        }
        TMemberType myTMemberType = memberTypeService.getMemberTypeByMemberTypeId(tMember.getMemberTypeId());
        MemberTypeVO myMemberTypeVO = GenerateUtil.getMemberTypeVO(myTMemberType);
        TItem myProvince = itemService.getItemByItemCodeAndType(tMember.getProvinceCode(), Constant.ItemType.PROVINCE);
        TItem myCity = itemService.getItemByItemCodeAndType(tMember.getCityCode(), Constant.ItemType.CITY);
        ArrayList<TItem> myItem = new ArrayList<>();
        myItem.add(myProvince);
        myItem.add(myCity);
        List<ItemVO> myItemVO = GenerateUtil.getItemVOList(myItem);
        //组装数据
        BeanUtils.copyProperties(tMember,memberDetailVO);
        memberDetailVO.setMyMemberType(myMemberTypeVO);
        memberDetailVO.setAllMemberType(allMemberTypeVO);
        memberDetailVO.setMyItem(myItemVO);
        memberDetailVO.setAllItem(allItemVO);
        //返回
        return Result.success(memberDetailVO);
    }

    @Override
    @Transactional
    public Result createMember(CreateMemberDTO createMemberDTO) {
        //参数校验
        Result result = memberValidateService.validateCreateMemberDTO(createMemberDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //生成实体类
        TMember tMember = GenerateUtil.getTMember(createMemberDTO);
        //入库
        memberService.save(tMember);
        //返回
        return Result.success();
    }

    @Override
    public Result checkMemberName(String memberName) {
        TMember tMember = memberService.getMemberByMemberName(memberName);
        if(ObjectUtil.isNotEmpty(tMember)){
            return Result.getResult(ResultEnum.EXIST_MEMBER_NAME);
        }
        return Result.success();
    }

    @Override
    @Transactional
    public Result editMember(EditMemberDTO editMemberDTO) {
        //参数校验
        Result result = memberValidateService.validateEditMemberDTO(editMemberDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //修改数据
        TMember tMember = GenerateUtil.getTMember(editMemberDTO);
        //入库
        memberService.saveOrUpdate(tMember);
        //返回
        return Result.success();
    }

    @Override
    @Transactional
    public Result deleteMember(Integer memberId) {
        //参数校验
        if(ObjectUtil.isEmpty(memberId)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //删除
        memberService.deleteMemberByMemberId(memberId);
        //返回
        return Result.success();
    }
}
