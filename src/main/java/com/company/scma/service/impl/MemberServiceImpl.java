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
        Integer currentUserUserid = currentUser.getUserid();
        if(ObjectUtil.isNotEmpty(buildPartnershipid) && Constant.UserType.SUB_ACCOUNT_USER.equals(currentUserType)){
            //查询该合作企业下所有用户
            List<TUser> tUserList = userService.getUserByTypeAndBuildId(Constant.UserType.SUB_ACCOUNT_USER, buildPartnershipid);
            List<Integer> useridList = tUserList.stream().map(TUser::getUserid).collect(Collectors.toList());
            queryWrapper.in(Constant.ColumnName.OWNER_USERID,useridList);
        }
        queryWrapper.eq(Constant.ColumnName.STATUS,Constant.MemberStatus.NORMAL);
        queryWrapper.orderByDesc(Constant.ColumnName.MEMBER_ID);

        if (StringUtils.isNotEmpty(getMemberDataBaseDTO.getMemberName())) {
            queryWrapper.like(Constant.ColumnName.MEMBER_NAME, getMemberDataBaseDTO.getMemberName());
        }

        if (StringUtils.isNotEmpty(getMemberDataBaseDTO.getPartnershipName())) {
            //子账号用户只能查询本企业下的会员，因此所属单位名称对其相当于没有用，这里直接跳过
            if(Constant.UserType.COMMON_USER.equals(currentUserType)){
                //模糊查询企业名字
                List<TPartnership> tPartnershipList = partnershipService.fuzzQueryTPartnershipByPartnershipName(getMemberDataBaseDTO.getPartnershipName());
                if(ObjectUtil.isNotEmpty(tPartnershipList)){
                    List<Integer> partnershipIdList = tPartnershipList.stream().map(TPartnership::getPartnershipId).collect(Collectors.toList());
                    //查询这些企业下所有子用户
                    List<TUser> subAccountList = userService.getUserByTypeAndBuildId(Constant.UserType.SUB_ACCOUNT_USER, partnershipIdList);
                    //查询这些子用户下所有的会员
                    queryWrapper.in(Constant.ColumnName.OWNER_USERID,subAccountList);
                }
            }
        }

        if (StringUtils.isNotEmpty(getMemberDataBaseDTO.getUsername())) {
            //模糊查询用户名称
            List<TUser> tUserList = userService.fuzzGetTUserByUsername(getMemberDataBaseDTO.getUsername());
            if(ObjectUtil.isNotEmpty(tUserList)){
                List<Integer> useridList = tUserList.stream().map(TUser::getUserid).collect(Collectors.toList());
                queryWrapper.in(Constant.ColumnName.OWNER_USERID,useridList);
            }
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
}
