package com.company.scma.controller;

import com.company.scma.common.constant.Constant;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.*;
import com.company.scma.common.po.TUser;
import com.company.scma.common.vo.*;
import com.company.scma.service.bizservice.AuthorBizService;
import com.company.scma.service.bizservice.PartnershipBizService;
import com.company.scma.service.bizservice.SupplierBizService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "apifox", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiFoxController {
    @Autowired
    private PartnershipBizService partnershipBizService;
    @Autowired
    private SupplierBizService supplierBizService;
    @Autowired
    private AuthorBizService authorBizService;
    
    @PostMapping("/login")
    public ItemVO login(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login1")
    public MemberListRowVO login1(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login2")
    public MemberListVO login2(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login3")
    public MemberTypeVO login3(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login4")
    public MenuVO login4(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login5")
    public OperationDetailVO login5(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login6")
    public OperationListRowVO login6(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login7")
    public OperationListVO login7(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login8")
    public PermissionVO login8(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login9")
    public RoleDetailVO login9(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login10")
    public RoleListVO login10(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login11")
    public UserDetailVO login11(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login12")
    public UserListRowVO login12(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login13")
    public UserListVO login13(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login14")
    public List<OperationListRowVO> login14(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login15")
    public MemberDetailVO login15(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login16")
    public MemberDataBaseListVO login16(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login17")
    public MemberDataBaseListRowVO login17(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login18")
    public PartnershipDetailVO login18(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login19")
    public PartnershipListRowVO login19(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login20")
    public PartnershipListVO login20(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login21")
    public SysConfigDTO login21(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login22")
    public SysConfigDetailVO login22(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login23")
    public PartnershipConfigVO login23(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login24")
    public SupplierListVO login24(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login25")
    public SupplierListRowVO login25(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login26")
    public SupplierDetailVO login26(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login27")
    public ArticleListRowVO login27(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login28")
    public AuthorDetailVO login28(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login29")
    public AuthorListRowVO login29(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login30")
    public AuthorListVO login30(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login31")
    public CaseDetailVO login31(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login32")
    public CaseListRowVO login32(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login33")
    public CaseListVO login33(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login34")
    public CaseTypeVO login34(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login35")
    public UploadFileDTO login35(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login36")
    public DownloadFileVO login36(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login37")
    public PositionVO login37(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login38")
    public StockTypeVO login38(MemberDetailVO memberDetailVO) {
        return null;
    }

    @PostMapping("/login39")
    public CreateArticleDTO login39(CreateArticleDTO createArticleDTO) {
        return null;
    }

    @PostMapping("/login40")
    public CreateAuthorDTO login40(CreateArticleDTO createArticleDTO) {
        return null;
    }

    @PostMapping("/login41")
    public CreateSupplierDTO login41(CreateSupplierDTO createArticleDTO) {
        return null;
    }

    @PostMapping("/login42")
    public CreatePartnershipDTO login42(CreatePartnershipDTO createArticleDTO) {
        return null;
    }

    @PostMapping("/login43")
    public EditAuthorDTO login43(EditAuthorDTO createArticleDTO) {
        return null;
    }

    @PostMapping("/login44")
    public EditPartnershipDTO login44(CreateArticleDTO createArticleDTO) {
        return null;
    }

    @PostMapping("/login45")
    public EditSupplierDTO login45(CreateArticleDTO createArticleDTO) {
        return null;
    }

    @PostMapping("/login46")
    public GetPartnershipDTO login46(CreateArticleDTO createArticleDTO) {
        return null;
    }

    @PostMapping("/login47")
    public GetSupplierDTO login47(CreateArticleDTO createArticleDTO) {
        return null;
    }

    @PostMapping("/login48")
    public PartnershipExcelVO login48(CreateArticleDTO createArticleDTO) {
        return null;
    }

    @PostMapping("/login49")
    public SupplierExcelVO login49(CreateArticleDTO createArticleDTO) {
        return null;
    }

    @PostMapping("/login50")
    public AuthorExcelVO login50(CreateArticleDTO createArticleDTO) {
        return null;
    }

    @RequestMapping(value = "/downloadPartnershipData", method = RequestMethod.POST)
    @RequiresPermissions("partnership:visit")
    public Result downloadPartnershipData(@RequestBody GetPartnershipDTO getPartnershipDTO){
        return partnershipBizService.downloadPartnershipData(getPartnershipDTO);
    }

    @RequestMapping(value = "/downloadSupplierData", method = RequestMethod.POST)
    @RequiresPermissions("supplier:visit")
    public Result downloadSupplierData(@RequestBody GetSupplierDTO getSupplierDTO){
        return supplierBizService.downloadSupplierData(getSupplierDTO);
    }

    @RequestMapping(value = "/downloadAuthorData", method = RequestMethod.POST)
    @RequiresPermissions("author:visit")
    public Result downloadAuthorData(@RequestBody GetAuthorDTO getAuthorDTO){
        return authorBizService.downloadAuthorData(getAuthorDTO);
    }
}
