package com.company.scma.task;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.po.TMember;
import com.company.scma.common.po.TOperation;
import com.company.scma.common.po.TPartnership;
import com.company.scma.common.po.TUser;
import com.company.scma.common.util.DateUtil;
import com.company.scma.service.mapperservice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduledTask {
    @Autowired
    private OperationService operationService;
    @Autowired
    private UserService userService;
    @Autowired
    private PartnershipService partnershipService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private SysConfigService sysConfigService;

    @Scheduled(fixedRate = 3000)
    public void scheduledTask() {
        //查询系统配置
        String releaseWay = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.RELEASE_WAY);
        String suspendedTermStr = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.SUSPENDED_TERM);
        String releaseTermStr = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.RELEASE_TERM);
        //遍历所有活动
        List<TOperation> tOperationList = operationService.getBaseMapper().selectList(new QueryWrapper<TOperation>());
        if (ObjectUtil.isEmpty(tOperationList)) {
            return;
        }
        for (TOperation tOperation : tOperationList) {
            if (ObjectUtil.isEmpty(tOperation)) {
                continue;
            }
            if (Constant.Judge.NO.equals(tOperation.getDeleteflag())) {
                this.operateDeletedOperation(tOperation, releaseWay);
            } else {
                //更新活动状态
                tOperation.setStatus(DateUtil.nowBelongCalendar(tOperation.getStartDate(), tOperation.getEndDate()));
                if (Constant.OperationStatus.FINISH.equals(tOperation.getStatus())) {
                    //活动结束
                    this.operateFinishOperation(tOperation, releaseWay, suspendedTermStr, releaseTermStr);
                }
            }
        }
    }

    //活动删除时的处理方法
    private void operateDeletedOperation(TOperation tOperation, String releaseWay) {
        List<TPartnership> tPartnershipList = partnershipService.getTPartnershipByOperationId(tOperation.getOperationId(), null);
        if (ObjectUtil.isEmpty(tPartnershipList)) {
            return;
        }
        List<Integer> partnershipIdList = tPartnershipList.stream().map(TPartnership::getPartnershipId).collect(Collectors.toList());
        this.operateDeletedPartnership(partnershipIdList, releaseWay);
    }

    //活动已结束时的处理方法
    private void operateFinishOperation(TOperation tOperation, String releaseWay, String suspendedTermStr, String releaseTermStr) {
        //已经结束的活动
        List<TPartnership> tPartnershipList = partnershipService.getTPartnershipByOperationId(tOperation.getOperationId(), null);
        if (ObjectUtil.isEmpty(tPartnershipList)) {
            return;
        }
        //筛选出被删除的合作企业和正常的合作企业
        List<Integer> deletedPartnershipIdList = new ArrayList<>();
        List<Integer> commonPartnershipIdList = new ArrayList<>();
        List<Integer> partnershipIdList = tPartnershipList.stream().map(TPartnership::getPartnershipId).collect(Collectors.toList());
        for (Integer partnershipId : partnershipIdList) {
            if (Constant.Judge.YES.equals(partnershipId)) {
                commonPartnershipIdList.add(partnershipId);
            } else {
                deletedPartnershipIdList.add(partnershipId);
            }
        }
        //处理被删除的合作企业
        this.operateDeletedPartnership(deletedPartnershipIdList, releaseWay);
        //处理正常的合作企业
        List<TUser> userList = userService.getUserByTypeAndBuildId(Constant.UserType.SUB_ACCOUNT_USER, commonPartnershipIdList, null, null);
        if (ObjectUtil.isEmpty(userList)) {
            return;
        }
        List<Integer> deletedUseridList = new ArrayList<>();
        List<Integer> commonUseridList = new ArrayList<>();
        List<TUser> commonUserList = new ArrayList<>();
        for (TUser tUser : userList) {
            Integer status = tUser.getStatus();
            Integer deleteflag = tUser.getDeleteflag();
            if (Constant.Judge.NO.equals(deleteflag)) {
                //删除用户id
                deletedUseridList.add(tUser.getUserid());
            } else {
                //普通用户id
                commonUseridList.add(tUser.getUserid());
                commonUserList.add(tUser);
            }
        }
        //处理删除用户
        this.operateDeletedUser(deletedUseridList, releaseWay);
        //处理普通用户
        this.operateCommonUser(commonUserList, commonUseridList, suspendedTermStr, releaseTermStr, releaseWay, tOperation.getEndDate());
    }

    //合作企业删除时的处理方法
    private void operateDeletedPartnership(List<Integer> partnershipIdList, String releaseWay) {
        List<TUser> userList = userService.getUserByTypeAndBuildId(Constant.UserType.SUB_ACCOUNT_USER, partnershipIdList, null, null);
        if (ObjectUtil.isEmpty(userList)) {
            return;
        }
        //将所有未被删除的用户的状态置为失效
        ArrayList<TUser> tempUserLIst = new ArrayList<>();
        for (TUser tUser : userList) {
            if (Constant.Judge.YES.equals(tUser.getDeleteflag()) && Constant.Judge.YES.equals(tUser.getStatus())) {
                tUser.setStatus(Constant.Judge.NO);
                tempUserLIst.add(tUser);
            }
        }
        //更新用户状态
        userService.saveOrUpdateBatch(tempUserLIst);
        //用户处理
        List<Integer> useridList = userList.stream().map(TUser::getUserid).collect(Collectors.toList());
        this.operateDeletedUser(useridList, releaseWay);
    }

    //处理删除用户
    private void operateDeletedUser(List<Integer> useridList, String releaseWay) {
        if (ObjectUtil.isEmpty(useridList)) {
            return;
        }
        List<TMember> tMemberList = memberService.getMemberByOwnerUserid(useridList, Constant.Judge.YES);

        if (ObjectUtil.isEmpty(tMemberList)) {
            return;
        }
        //所有未被删除的会员置为释放状态，清除所属人和所属合作企业
        for (TMember tMember : tMemberList) {
            tMember.setOwnerUsername(null);
            tMember.setOwnerUserid(null);
            tMember.setOwnerPartnershipName(null);
            tMember.setOwnerPartnershipId(null);
            //如果释放到公司会员数据库，设置名字和状态
            if (Constant.ReleaseWay.RELEASE_TO_MEMBER_DATABASE.equals(releaseWay)) {
                tMember.setOwnerPartnershipName(Constant.Common.DEFAULT_PARTNERSHIP_NAME);
                tMember.setStatus(Constant.MemberStatus.RELEASED_MEMBER_DATABASE);
            } else {
                tMember.setStatus(Constant.MemberStatus.RELEASED_PUBLIC_RESOURCE);
            }
        }
    }

    //处理需要重新设置生效和失效的用户及其会员
    private void operateCommonUser(List<TUser> userList, List<Integer> useridList,
                                   String suspendedTermStr, String releaseTermStr,
                                   String releaseWay, Date operationEndTime) {
        if (ObjectUtil.isEmpty(useridList)) {
            return;
        }
        //更新用户
        Integer suspendedTerm = Integer.valueOf(suspendedTermStr);
        Date suspendTime = DateUtil.getDaysDateFromSource(suspendedTerm, operationEndTime);
        boolean suspendFlag = DateUtil.isSourceDateBeforeComparedDate(suspendTime, new Date());
        if (suspendFlag) {
            //冻结
            userList.stream().filter(user -> Constant.Judge.YES.equals(user)).forEach(user -> {
                user.setStatus(Constant.Judge.NO);
            });
        } else {
            //激活
            userList.stream().filter(user -> Constant.Judge.NO.equals(user)).forEach(user -> {
                user.setStatus(Constant.Judge.YES);
            });
        }
        //更新会员
        List<TMember> tMemberList = memberService.getMemberByOwnerUserid(useridList, Constant.Judge.YES);
        if (ObjectUtil.isEmpty(tMemberList)) {
            return;
        }
        //只处理正常状态的会员，已经被释放的不修改
        Integer releaseTerm = Integer.valueOf(releaseTermStr);
        Date releaseTime = DateUtil.getDaysDateFromSource(suspendedTerm + releaseTerm, operationEndTime);
        boolean releaseFlag = DateUtil.isSourceDateBeforeComparedDate(releaseTime, new Date());
        if (releaseFlag) {
            //释放
            //根据配置设置会员释放状态
            Integer releaseStatus = Constant.ReleaseWay.RELEASE_TO_MEMBER_DATABASE.equals(releaseWay) ? Constant.MemberStatus.RELEASED_MEMBER_DATABASE : Constant.MemberStatus.RELEASED_PUBLIC_RESOURCE;
            tMemberList.stream().filter(tMember -> Constant.MemberStatus.NORMAL.equals(tMember.getStatus())).
                    forEach(tMember -> {
                        tMember.setStatus(releaseStatus);
                    });
        }
    }

}
