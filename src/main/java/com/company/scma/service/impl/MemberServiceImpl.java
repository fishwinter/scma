package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.dto.GetMemberDataBaseDTO;
import com.company.scma.common.dto.GetMyMemberDTO;
import com.company.scma.common.po.TMember;
import com.company.scma.common.po.TPartnership;
import com.company.scma.common.po.TUser;
import com.company.scma.mapper.MemberMapper;
import com.company.scma.service.mapperservice.MemberService;
import com.company.scma.service.mapperservice.PartnershipService;
import com.company.scma.service.mapperservice.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, TMember> implements MemberService {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private PartnershipService partnershipService;
    
    @Override
    public TMember getMemberByMemberName(String memberName) {
        QueryWrapper<TMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.MEMBER_NAME,memberName);
        List<TMember> tMemberList = memberMapper.selectList(queryWrapper);
        if(ObjectUtil.isNotEmpty(tMemberList)){
            return tMemberList.get(0);
        }
        return null;
    }

    @Override
    public IPage<TMember> getMemberByCondition(GetMyMemberDTO getMyMemberDTO) {
        QueryWrapper<TMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES);
        TUser currentUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        if(ObjectUtil.isEmpty(currentUser) || ObjectUtil.isEmpty(currentUser.getUserid())){
            return null;
        }
        queryWrapper.eq(Constant.ColumnName.OWNER_USERID,currentUser.getUserid());
        queryWrapper.eq(Constant.ColumnName.STATUS,Constant.MemberStatus.NORMAL);
        queryWrapper.orderByDesc(Constant.ColumnName.MEMBER_ID);

        if (StringUtils.isNotEmpty(getMyMemberDTO.getMemberName())) {
            queryWrapper.eq(Constant.ColumnName.MEMBER_NAME, getMyMemberDTO.getMemberName());
        }

        if (StringUtils.isNotEmpty(getMyMemberDTO.getContacts())) {
            queryWrapper.eq(Constant.ColumnName.CONTACTS, getMyMemberDTO.getContacts());
        }

        if (StringUtils.isNotEmpty(getMyMemberDTO.getTel())) {
            queryWrapper.eq(Constant.ColumnName.TEL, getMyMemberDTO.getTel());
        }

        if (ObjectUtil.isNotEmpty(getMyMemberDTO.getStartDate())) {
            queryWrapper.ge(Constant.ColumnName.BUILD_DATE, getMyMemberDTO.getStartDate());
        }

        if (ObjectUtil.isNotEmpty(getMyMemberDTO.getEndDate())) {
            queryWrapper.le(Constant.ColumnName.BUILD_DATE, getMyMemberDTO.getEndDate());
        }

        Page<TMember> page = Page.of(getMyMemberDTO.getCurrentPage(), getMyMemberDTO.getPageSize(), 0, true);
        IPage<TMember> tMemberIPage = memberMapper.selectPage(page, queryWrapper);
        return tMemberIPage;
    }

    @Override
    public IPage<TMember> getMemberByCondition(GetMemberDataBaseDTO getMemberDataBaseDTO) {
        QueryWrapper<TMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES);
        TUser currentUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        if(ObjectUtil.isEmpty(currentUser)){
            return null;
        }
        Integer buildPartnershipid = currentUser.getBuildPartnershipid();
        Integer currentUserType = currentUser.getType();
        if(ObjectUtil.isNotEmpty(buildPartnershipid) && Constant.UserType.SUB_ACCOUNT_USER.equals(currentUserType)){
            queryWrapper.eq(Constant.ColumnName.OWNER_PARTNERSHIP_ID,buildPartnershipid);
        }
        queryWrapper.ne(Constant.ColumnName.STATUS,Constant.MemberStatus.RELEASED_PUBLIC_RESOURCE);
        queryWrapper.orderByDesc(Constant.ColumnName.MEMBER_ID);

        if (StringUtils.isNotEmpty(getMemberDataBaseDTO.getMemberName())) {
            queryWrapper.like(Constant.ColumnName.MEMBER_NAME, getMemberDataBaseDTO.getMemberName());
        }

        if (StringUtils.isNotEmpty(getMemberDataBaseDTO.getPartnershipName())) {
            queryWrapper.like(Constant.ColumnName.OWNER_PARTNERSHIP_NAME,getMemberDataBaseDTO.getPartnershipName());
        }

        if (StringUtils.isNotEmpty(getMemberDataBaseDTO.getUsername())) {
            queryWrapper.like(Constant.ColumnName.OWNER_USERNAME,getMemberDataBaseDTO.getUsername());
        }

        if (ObjectUtil.isNotEmpty(getMemberDataBaseDTO.getStartDate())) {
            queryWrapper.ge(Constant.ColumnName.BUILD_DATE, getMemberDataBaseDTO.getStartDate());
        }

        if (ObjectUtil.isNotEmpty(getMemberDataBaseDTO.getEndDate())) {
            queryWrapper.le(Constant.ColumnName.BUILD_DATE, getMemberDataBaseDTO.getEndDate());
        }

        Page<TMember> page = Page.of(getMemberDataBaseDTO.getCurrentPage(), getMemberDataBaseDTO.getPageSize(), 0, true);
        IPage<TMember> tMemberIPage = memberMapper.selectPage(page, queryWrapper);
        return tMemberIPage;
    }

    @Override
    public TMember getMemberByMemberId(Integer memberId) {
        QueryWrapper<TMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.MEMBER_ID,memberId);
        List<TMember> tMemberList = memberMapper.selectList(queryWrapper);
        if(ObjectUtil.isNotEmpty(tMemberList)){
            return tMemberList.get(0);
        }
        return null;
    }

    @Override
    public List<TMember> getMemberByMemberType(Integer memberTypeId) {
        QueryWrapper<TMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.MEMBER_TYPE_ID,memberTypeId);
        List<TMember> tMemberList = memberMapper.selectList(queryWrapper);
        return tMemberList;
    }

    @Override
    public void deleteMemberByMemberId(Integer memberId) {
        UpdateWrapper<TMember> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(Constant.ColumnName.DELETEFLAG,Constant.Judge.NO);
        updateWrapper.eq(Constant.ColumnName.MEMBER_ID,memberId);
        memberMapper.update(null,updateWrapper);
    }

    @Override
    public List<TMember> getMemberByOwnerUserid(Integer ownerUserid) {
        QueryWrapper<TMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.OWNER_USERID,ownerUserid);
        List<TMember> tMemberList = memberMapper.selectList(queryWrapper);
        return tMemberList;
    }

    @Override
    public List<TMember> getMemberByOwnerUserid(List<Integer> ownerUseridList, Integer deleteflag) {
        if(ObjectUtil.isEmpty(ownerUseridList)){
            return null;
        }
        QueryWrapper<TMember> queryWrapper = new QueryWrapper<>();
        if(ObjectUtil.isNotEmpty(deleteflag)){
            queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        }
        queryWrapper.in(Constant.ColumnName.OWNER_USERID,ownerUseridList);
        List<TMember> tMemberList = memberMapper.selectList(queryWrapper);
        return tMemberList;
    }

    @Override
    public List<TMember> getMemberByOwnerPartnershipId(Integer ownerPartnershipId) {
        QueryWrapper<TMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.OWNER_PARTNERSHIP_ID,ownerPartnershipId);
        List<TMember> tMemberList = memberMapper.selectList(queryWrapper);
        return tMemberList;
    }
}
