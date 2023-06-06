package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.dto.GetMyMemberDTO;
import com.company.scma.common.po.TMember;
import com.company.scma.common.po.TUser;
import com.company.scma.mapper.MemberMapper;
import com.company.scma.service.mapperservice.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, TMember> implements MemberService {
    @Autowired
    private MemberMapper memberMapper;
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
}
