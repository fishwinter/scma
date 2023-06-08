package com.company.scma.controller;

import com.company.scma.common.constant.Constant;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.SysConfigDTO;
import com.company.scma.common.po.TUser;
import com.company.scma.common.vo.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "apifox", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiFoxController {
    @PostMapping("/login")
    public ItemVO login(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public MemberListRowVO login1(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public MemberListVO login2(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public MemberTypeVO login3(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public MenuVO login4(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public OperationDetailVO login5(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public OperationListRowVO login6(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public OperationListVO login7(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public PermissionVO login8(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public RoleDetailVO login9(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public RoleListVO login10(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public UserDetailVO login11(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public UserListRowVO login12(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public UserListVO login13(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public List<OperationListRowVO> login14(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public MemberDetailVO login15(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public MemberDataBaseListVO login16(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public MemberDataBaseListRowVO login17(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public PartnershipDetailVO login18(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public PartnershipListRowVO login19(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public PartnershipListVO login20(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public SysConfigDTO login21(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login")
    public SysConfigDetailVO login22(MemberDetailVO memberDetailVO) {
        return null;
    }
}
