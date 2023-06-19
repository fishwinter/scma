package com.company.scma.task;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.po.TMember;
import com.company.scma.common.po.TOperation;
import com.company.scma.common.po.TPartnership;
import com.company.scma.common.po.TUser;
import com.company.scma.service.mapperservice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        //遍历所有活动
        List<TOperation> tOperationList = operationService.getBaseMapper().selectList(new QueryWrapper<TOperation>());
        if(ObjectUtil.isEmpty(tOperationList)){
            return;
        }
        for (TOperation tOperation : tOperationList) {
            if(ObjectUtil.isEmpty(tOperation)){
                continue;
            }
            if(Constant.Judge.NO.equals(tOperation.getDeleteflag())){
                //删除的活动
                List<TPartnership> tPartnershipList = partnershipService.getTPartnershipByOperationId(tOperation.getOperationId(),null);
                if(ObjectUtil.isEmpty(tPartnershipList)){
                    continue;
                }
                List<Integer> partnershipIdList = tPartnershipList.stream().map(TPartnership::getPartnershipId).collect(Collectors.toList());
                List<TUser> userList = userService.getUserByTypeAndBuildId(Constant.UserType.SUB_ACCOUNT_USER, partnershipIdList,null,null);
                if(ObjectUtil.isEmpty(userList)){
                    continue;
                }
                //将所有未被删除的用户的状态置为失效
                ArrayList<TUser> tempUserLIst = new ArrayList<>();
                for (TUser tUser : userList) {
                    if(Constant.Judge.YES.equals(tUser.getDeleteflag()) && Constant.Judge.YES.equals(tUser.getStatus())){
                        tUser.setStatus(Constant.Judge.NO);
                        tempUserLIst.add(tUser);
                    }
                }
                //更新用户状态
                userService.saveOrUpdateBatch(tempUserLIst);
                //查询所有会员
                List<Integer> useridList = userList.stream().map(TUser::getUserid).collect(Collectors.toList());
                List<TMember> tMemberList = memberService.getMemberByOwnerUserid(useridList, null);
                //查询系统配置
                String releaseWay = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.RELEASE_WAY);
                //所有未被删除的会员置为释放状态，清除所属人和所属合作企业
                for (TMember tMember : tMemberList) {
                    if(Constant.Judge.NO.equals(tMember.getDeleteflag())){
                        continue;
                    }
                    tMember.setOwnerUsername(null);
                    tMember.setOwnerUserid(null);
                    tMember.setOwnerPartnershipName(null);
                    tMember.setOwnerPartnershipId(null);
                    //如果释放到公司会员数据库，设置名字和状态
                    if(Constant.ReleaseWay.RELEASE_TO_MEMBER_DATABASE.equals(releaseWay)){
                        tMember.setOwnerPartnershipName(Constant.Common.DEFAULT_PARTNERSHIP_NAME);
                        tMember.setStatus(Constant.MemberStatus.RELEASED_MEMBER_DATABASE);
                    }else{
                        tMember.setStatus(Constant.MemberStatus.RELEASED_PUBLIC_RESOURCE);
                    }
                }
            }
        }
    }
}
