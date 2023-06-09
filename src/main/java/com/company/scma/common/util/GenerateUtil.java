package com.company.scma.common.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.dto.*;
import com.company.scma.common.po.*;
import com.company.scma.common.vo.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.stream.Collectors;

public class GenerateUtil {
    public static RoleListVO getRoleListVOByTRole(TRole tRole) {
        RoleListVO roleListVO = new RoleListVO();
        BeanUtils.copyProperties(tRole, roleListVO);
        return roleListVO;
    }

    public static TRole createTRole(CreateRoleDTO createRoleDTO) {
        TRole tRole = new TRole();
        tRole.setRoleName(createRoleDTO.getRoleName());
        tRole.setIntroduction(createRoleDTO.getIntroduction());
        //获取当前登录用户信息
        TUser tUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tRole.setBuildDate(new Date());
        tRole.setBuildUserid(tUser.getUserid());
        tRole.setModifyDate(new Date());
        tRole.setModifyUserid(tUser.getUserid());
        tRole.setDeleteflag(Constant.Judge.YES);
        return tRole;
    }

    public static TRole createTRole(EditRoleDTO editRoleDTO) {
        TRole tRole = new TRole();
        tRole.setRoleName(editRoleDTO.getRoleName());
        tRole.setIntroduction(editRoleDTO.getIntroduction());
        tRole.setRoleId(editRoleDTO.getRoleId());
        //获取当前登录用户信息
        TUser tUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tRole.setModifyDate(new Date());
        tRole.setModifyUserid(tUser.getUserid());
        return tRole;
    }

    public static List<TRoleMtmPermission> createTRoleMtmPermissionList(TRole tRole, CreateRoleDTO createRoleDTO) {
        List<Integer> permissionIdList = createRoleDTO.getPermissionIdList();
        ArrayList<TRoleMtmPermission> tRoleMtmPermissionList = new ArrayList<>();
        for (Integer permissionId : permissionIdList) {
            TRoleMtmPermission tRoleMtmPermission = new TRoleMtmPermission();
            tRoleMtmPermission.setRoleId(tRole.getRoleId());
            tRoleMtmPermission.setPermissionId(permissionId);
            tRoleMtmPermission.setDeleteflag(Constant.Judge.YES);
            tRoleMtmPermissionList.add(tRoleMtmPermission);
        }
        return tRoleMtmPermissionList;
    }

    public static List<TRoleMtmPermission> createTRoleMtmPermissionList(EditRoleDTO editRoleDTO) {
        List<Integer> permissionIdList = editRoleDTO.getPermissionIdList();
        ArrayList<TRoleMtmPermission> tRoleMtmPermissionList = new ArrayList<>();
        for (Integer permissionId : permissionIdList) {
            TRoleMtmPermission tRoleMtmPermission = new TRoleMtmPermission();
            tRoleMtmPermission.setRoleId(editRoleDTO.getRoleId());
            tRoleMtmPermission.setPermissionId(permissionId);
            tRoleMtmPermission.setDeleteflag(Constant.Judge.YES);
            tRoleMtmPermissionList.add(tRoleMtmPermission);
        }
        return tRoleMtmPermissionList;
    }

    public static RoleDetailVO createRoleDetailVO(TRole tRole, List<TPermission> rolePermissionList, 
                                                  List<TPermission> allPermissionList){
        RoleDetailVO roleDetailVO = new RoleDetailVO();
        roleDetailVO.setRoleName(tRole.getRoleName());
        roleDetailVO.setIntroduction(tRole.getIntroduction());
        List<MenuVO> roleMenuVOList = GenerateUtil.createMenuVOByTPermissionList(rolePermissionList);
        List<MenuVO> allMenuVOList = GenerateUtil.createMenuVOByTPermissionList(allPermissionList);
        roleDetailVO.setRolePermission(roleMenuVOList);
        roleDetailVO.setAllPermission(allMenuVOList);
        return roleDetailVO;
    }
    
    public static List<MenuVO> createMenuVOByTPermissionList(List<TPermission> tPermissionList){
        Map<String, MenuVO> menuVOMap = new HashMap<String, MenuVO>();
        for (TPermission tPermission : tPermissionList) {
            String menuName = tPermission.getMenuName();
            PermissionVO permissionVO = GenerateUtil.getPermissionVOByTPermission(tPermission);
            if(menuVOMap.containsKey(menuName)){
                MenuVO menuVO = menuVOMap.get(menuName);
                List<PermissionVO> permissionVOList = menuVO.getPermissionVOList();
                if(ObjectUtil.isEmpty(permissionVOList)){
                    permissionVOList = new ArrayList<>();
                }
                permissionVOList.add(permissionVO);
            }else{
                MenuVO menuVO = new MenuVO();
                menuVOMap.put(tPermission.getMenuName(),menuVO);
                menuVO.setMenuName(tPermission.getMenuName());
                menuVO.setMenuSort(tPermission.getMenuSort());
                menuVO.setPermissionVOList(new ArrayList<>());
                menuVO.getPermissionVOList().add(permissionVO);
            }
        }
        List<MenuVO> menuVOList = menuVOMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        return menuVOList;
    }
    
    public static PermissionVO getPermissionVOByTPermission(TPermission tPermission){
        PermissionVO permissionVO = new PermissionVO();
        permissionVO.setPermissionId(tPermission.getPermissionId());
        permissionVO.setPermissionName(tPermission.getPermissionName());
        permissionVO.setPermissionSort(tPermission.getPermissionSort());
        return permissionVO;
    }

    public static TUser createTUser(CreateUserDTO createUserDTO){
        TUser tUser = new TUser();
        BeanUtils.copyProperties(createUserDTO,tUser);
        TUser currentUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tUser.setType(Constant.UserType.COMMON_USER);
        tUser.setBuildDate(new Date());
        tUser.setBuildUserid(currentUser.getUserid());
        tUser.setModifyDate(new Date());
        tUser.setModifyUserid(currentUser.getUserid());
        tUser.setDeleteflag(Constant.Judge.YES);
        tUser.setStatus(Constant.Judge.YES);
        return tUser;
    }
    
    public static UserListVO getUserListVO(IPage<TUser> iPage){
        UserListVO userListVO = new UserListVO();
        if(ObjectUtil.isEmpty(iPage)){
            return userListVO;
        }
        userListVO.setUserTotal(iPage.getTotal());
        List<TUser> tUserList = iPage.getRecords();
        if(ObjectUtil.isNotEmpty(tUserList)){
            List<UserListRowVO> collect = tUserList.stream().map(GenerateUtil::getUserListRowVO).collect(Collectors.toList());
            userListVO.setUserListRowVOList(collect);
        }
        return userListVO;
    }
    
    public static UserListRowVO getUserListRowVO(TUser tUser){
        UserListRowVO userListRowVO = new UserListRowVO();
        if(ObjectUtil.isEmpty(tUser)){
            return userListRowVO;
        }
        BeanUtils.copyProperties(tUser,userListRowVO);
        return userListRowVO;
    }
    
    public static UserDetailVO getUserDetailVO(TUser tUser){
        UserDetailVO userDetailVO = new UserDetailVO();
        BeanUtils.copyProperties(tUser,userDetailVO);
        return userDetailVO;
    }
    
    public static TUser getTUser(EditUserDTO editUserDTO){
        TUser tUser = new TUser();
        BeanUtils.copyProperties(editUserDTO,tUser);
        TUser currentUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tUser.setModifyDate(new Date());
        tUser.setModifyUserid(currentUser.getUserid());
        return tUser;
    }

    public static TUser getPartnerShipUser(String username, String name, String password, Integer partnershipId){
        TUser tUser = new TUser();
        if(ObjectUtil.isEmpty(username) || ObjectUtil.isEmpty(password)){
            return tUser;
        }
        tUser.setUsername(username);
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        tUser.setPassword(hashPassword);
        TUser currentUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tUser.setType(Constant.UserType.SUB_ACCOUNT_USER);
        tUser.setName(name);
        tUser.setBuildDate(new Date());
        tUser.setBuildUserid(currentUser.getUserid());
        tUser.setBuildPartnershipid(partnershipId);
        tUser.setModifyDate(new Date());
        tUser.setModifyUserid(currentUser.getUserid());
        tUser.setDeleteflag(Constant.Judge.YES);
        tUser.setStatus(Constant.Judge.YES);
        return tUser;
    }
    
    public static TMember getTMember(CreateMemberDTO createMemberDTO){
        TMember tMember = new TMember();
        BeanUtils.copyProperties(createMemberDTO,tMember);
        TUser currentUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tMember.setBuildDate(new Date());
        tMember.setBuildUserid(currentUser.getUserid());
        tMember.setModifyDate(new Date());
        tMember.setModifyUserid(currentUser.getUserid());
        tMember.setDeleteflag(Constant.Judge.YES);
        tMember.setOwnerUserid(currentUser.getUserid());
        tMember.setOwnerUsername(currentUser.getUsername());
        tMember.setStatus(Constant.MemberStatus.NORMAL);
        return tMember;
    }

    public static TMember getTMember(EditMemberDTO editMemberDTO, TMember selectMember){
        TMember tMember = new TMember();
        BeanUtils.copyProperties(editMemberDTO,tMember);
        TUser currentUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tMember.setModifyDate(new Date());
        tMember.setModifyUserid(currentUser.getUserid());
        tMember.setOwnerPartnershipName(selectMember.getOwnerPartnershipName());
        tMember.setOwnerPartnershipId(selectMember.getOwnerPartnershipId());
        tMember.setOwnerUsername(selectMember.getOwnerUsername());
        tMember.setOwnerUserid(selectMember.getOwnerUserid());
        return tMember;
    }
    
    public static MemberListVO getMemberListVO(IPage<TMember> iPage){
        MemberListVO memberListVO = new MemberListVO();
        if(ObjectUtil.isEmpty(iPage)){
            return memberListVO;
        }
        memberListVO.setMemberTotal(iPage.getTotal());
        List<TMember> tMemberList = iPage.getRecords();
        if(ObjectUtil.isNotEmpty(tMemberList)){
            List<MemberListRowVO> collect = tMemberList.stream().map(GenerateUtil::getMemberListRowVO).collect(Collectors.toList());
            memberListVO.setMemberListRowVOList(collect);
        }
        return memberListVO;
    }
    
    //请注意，该方法生成memberListRowVO中的memberTypeVO中的memberTypeName没有赋值
    public static MemberListRowVO getMemberListRowVO(TMember tMember){
        MemberListRowVO memberListRowVO = new MemberListRowVO();
        if(ObjectUtil.isEmpty(tMember)){
            return memberListRowVO;
        }
        BeanUtils.copyProperties(tMember,memberListRowVO);
        memberListRowVO.setTel(tMember.getContactTel());
        MemberTypeVO memberTypeVO = new MemberTypeVO();
        memberListRowVO.setMemberTypeVO(memberTypeVO);
        memberTypeVO.setMemberTypeId(tMember.getMemberTypeId());
        return memberListRowVO;
    }
    
    public static void setMemberTypeName(List<MemberListRowVO> memberListRowVOList,List<TMemberType> tMemberTypeList){
        if(ObjectUtil.isEmpty(memberListRowVOList) || ObjectUtil.isEmpty(tMemberTypeList)){
            return;
        }
        Map<Integer, String> typeMap = tMemberTypeList.stream().collect(Collectors.toMap(TMemberType::getMemberTypeId, TMemberType::getMemberTypeName));
        memberListRowVOList.stream().forEach(memberListRowVO -> {
            MemberTypeVO memberTypeVO = memberListRowVO.getMemberTypeVO();
            memberTypeVO.setMemberTypeName(typeMap.get(memberTypeVO.getMemberTypeId()));
        });
    }

    public static void setMemberTypeName2(List<MemberDataBaseListRowVO> memberDataBaseListRowVOList,List<TMemberType> tMemberTypeList){
        if(ObjectUtil.isEmpty(memberDataBaseListRowVOList) || ObjectUtil.isEmpty(tMemberTypeList)){
            return;
        }
        Map<Integer, String> typeMap = tMemberTypeList.stream().collect(Collectors.toMap(TMemberType::getMemberTypeId, TMemberType::getMemberTypeName));
        memberDataBaseListRowVOList.stream().forEach(memberDataBaseListRowVO -> {
            MemberTypeVO memberTypeVO = memberDataBaseListRowVO.getMemberTypeVO();
            memberTypeVO.setMemberTypeName(typeMap.get(memberTypeVO.getMemberTypeId()));
        });
    }
    
    public static List<MemberTypeVO> getMemberTypeVOList(List<TMemberType> tMemberTypeList){
        List<MemberTypeVO> memberTypeVOList = new ArrayList<>();
        if(ObjectUtil.isEmpty(tMemberTypeList)){
            return memberTypeVOList;
        }
        memberTypeVOList = tMemberTypeList.stream().map(GenerateUtil::getMemberTypeVO).collect(Collectors.toList());
        return memberTypeVOList;
    }
    
    public static MemberTypeVO getMemberTypeVO(TMemberType tMemberType){
        MemberTypeVO memberTypeVO = new MemberTypeVO();
        if(ObjectUtil.isEmpty(tMemberType)){
            return memberTypeVO;
        }
        BeanUtils.copyProperties(tMemberType,memberTypeVO);
        return memberTypeVO;
    }
    
    public static List<ItemVO> getItemVOList(List<TItem> itemList){
        List<ItemVO> itemVOList = new ArrayList<>();
        if(ObjectUtil.isEmpty(itemList)){
            return itemVOList;
        }
        itemVOList = itemList.stream().map(GenerateUtil::getItemVO).collect(Collectors.toList());
        return itemVOList;
    }
    
    public static ItemVO getItemVO(TItem tItem){
        ItemVO itemVO = new ItemVO();
        if(ObjectUtil.isEmpty(tItem)){
            return itemVO;
        }
        BeanUtils.copyProperties(tItem,itemVO);
        return itemVO;
    }
    
    public static TOperation getTOperation(CreateOperationDTO createOperationDTO) {
        TOperation tOperation = new TOperation();
        if (ObjectUtil.isEmpty(createOperationDTO)) {
            return tOperation;
        }
        BeanUtils.copyProperties(createOperationDTO, tOperation);
        tOperation.setStatus(DateUtil.nowBelongCalendar(createOperationDTO.getStartDate(),createOperationDTO.getEndDate()));
        TUser tUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tOperation.setBuildDate(new Date());
        tOperation.setBuildUserid(tUser.getUserid());
        tOperation.setModifyDate(new Date());
        tOperation.setModifyUserid(tUser.getUserid());
        tOperation.setDeleteflag(Constant.Judge.YES);
        return tOperation;
    }

    public static TOperation getTOperation(EditOperationDTO editOperationDTO) {
        TOperation tOperation = new TOperation();
        if (ObjectUtil.isEmpty(editOperationDTO)) {
            return tOperation;
        }
        BeanUtils.copyProperties(editOperationDTO, tOperation);
        tOperation.setStatus(DateUtil.nowBelongCalendar(editOperationDTO.getStartDate(),editOperationDTO.getEndDate()));
        TUser tUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tOperation.setModifyDate(new Date());
        tOperation.setModifyUserid(tUser.getUserid());
        return tOperation;
    }
    
    public static OperationListVO getOperationListVO(IPage<TOperation> iPage){
        OperationListVO operationListVO = new OperationListVO();
        if(ObjectUtil.isEmpty(iPage)){
            return operationListVO;
        }
        operationListVO.setOperationTotal(iPage.getTotal());
        List<TOperation> tOperationList = iPage.getRecords();
        if(ObjectUtil.isNotEmpty(tOperationList)){
            List<OperationListRowVO> operationListRowVOList = tOperationList.stream().map(GenerateUtil::getOperationListRowVO).collect(Collectors.toList());
            operationListVO.setOperationListRowVOList(operationListRowVOList);
        }
        return operationListVO;
    }
    
    public static OperationListRowVO getOperationListRowVO(TOperation tOperation){
        OperationListRowVO operationListRowVO = new OperationListRowVO();
        if(ObjectUtil.isEmpty(tOperation)){
            return operationListRowVO;
        }
        BeanUtils.copyProperties(tOperation,operationListRowVO);
        return operationListRowVO;
    }
    
    public static OperationDetailVO getOperationDetailVO(TOperation tOperation){
        OperationDetailVO operationDetailVO = new OperationDetailVO();
        if(ObjectUtil.isEmpty(tOperation)){
            return operationDetailVO;
        }
        BeanUtils.copyProperties(tOperation,operationDetailVO);
        return operationDetailVO;
    }
    
    public static TPartnership getTPartnership (CreatePartnershipDTO createPartnershipDTO,Integer operationStatus){
        TPartnership tPartnership = new TPartnership();
        if(ObjectUtil.isEmpty(createPartnershipDTO)){
            return tPartnership;
        }
        BeanUtils.copyProperties(createPartnershipDTO,tPartnership);
        TUser tUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tPartnership.setOperationStatus(operationStatus);
        tPartnership.setBuildDate(new Date());
        tPartnership.setBuildUserid(tUser.getUserid());
        tPartnership.setModifyDate(new Date());
        tPartnership.setModifyUserid(tUser.getUserid());
        tPartnership.setDeleteflag(Constant.Judge.YES);
        StringBuffer sb = new StringBuffer();
        List<Integer> projectType = createPartnershipDTO.getProjectType();
        if(ObjectUtil.isNotEmpty(projectType)){
            for (Integer projectTypeId : projectType) {
                sb.append(projectTypeId).append(",");
            }
            String projectTypeStr = sb.toString();
            projectTypeStr = projectTypeStr.substring(0,projectTypeStr.length() - 1);
            tPartnership.setProjectType(projectTypeStr);
        }
        return tPartnership;
    }
    
    public static TOperationOtmPartnership getTOperationOtmPartnership(Integer operationId,Integer partnershipId){
        TOperationOtmPartnership tOperationOtmPartnership = new TOperationOtmPartnership();
        if(ObjectUtil.isEmpty(operationId) || ObjectUtil.isEmpty(partnershipId)){
            return tOperationOtmPartnership;
        }
        
        tOperationOtmPartnership.setOperationId(operationId);
        tOperationOtmPartnership.setPartnershipId(partnershipId);
        tOperationOtmPartnership.setBuildDate(new Date());
        tOperationOtmPartnership.setDeleteflag(Constant.Judge.YES);
        return tOperationOtmPartnership;
    }

    public static PartnershipListVO getPartnershipListVO(IPage<TPartnership> iPage){
        PartnershipListVO partnershipListVO = new PartnershipListVO();
        if(ObjectUtil.isEmpty(iPage)){
            return partnershipListVO;
        }
        partnershipListVO.setPartnershipTotal(iPage.getTotal());
        List<TPartnership> tPartnershipList = iPage.getRecords();
        if(ObjectUtil.isNotEmpty(tPartnershipList)){
            List<PartnershipListRowVO> partnershipListRowVOList = tPartnershipList.stream().map(GenerateUtil::getPartnershipListRowVO).collect(Collectors.toList());
            partnershipListVO.setPartnershipListRowVOList(partnershipListRowVOList);
        }
        return partnershipListVO;
    }

    public static PartnershipListRowVO getPartnershipListRowVO(TPartnership tPartnership){
        PartnershipListRowVO partnershipListRowVO = new PartnershipListRowVO();
        if(ObjectUtil.isEmpty(tPartnership)){
            return partnershipListRowVO;
        }
        BeanUtils.copyProperties(tPartnership,partnershipListRowVO);
        partnershipListRowVO.setStatus(tPartnership.getOperationStatus());
        return partnershipListRowVO;
    }
    
    public static PartnershipDetailVO getPartnershipDetailVO(TPartnership tPartnership){
        PartnershipDetailVO partnershipDetailVO = new PartnershipDetailVO();
        if(ObjectUtil.isEmpty(tPartnership)){
            return partnershipDetailVO;
        }
        BeanUtils.copyProperties(tPartnership,partnershipDetailVO);
        return partnershipDetailVO;
    }
    
    public static PartnershipTypeVO getPartnershipTypeVO(List<PartnershipTypeVO> partnershipTypeVOList,Integer partnershipTypeId){
        PartnershipTypeVO result = null;
        if(ObjectUtil.isEmpty(partnershipTypeVOList) || ObjectUtil.isEmpty(partnershipTypeId)){
            return result;
        }
        for (PartnershipTypeVO partnershipTypeVO : partnershipTypeVOList) {
            if(partnershipTypeVO.getPartnershipTypeId()!= null && partnershipTypeVO.getPartnershipTypeId() == partnershipTypeId){
                result = partnershipTypeVO;
                return result;
            }
        }
        return null;
    }

    public static List<PartnershipProjectTypeVO> getPartnershipProjectTypeVO
            (List<PartnershipProjectTypeVO> partnershipProjectTypeVOList,List<String> partnershipProjectTypeIdList){
        List<PartnershipProjectTypeVO> result = null;
        if(ObjectUtil.isEmpty(partnershipProjectTypeVOList) || ObjectUtil.isEmpty(partnershipProjectTypeIdList)){
            return result;
        }
        result = new ArrayList<PartnershipProjectTypeVO>();
        Map<Integer, PartnershipProjectTypeVO> collect = partnershipProjectTypeVOList.stream().collect(Collectors.toMap(PartnershipProjectTypeVO::getPartnershipProjectTypeId, partnershipProjectTypeVO -> partnershipProjectTypeVO));
        for (String partnershipProjectTypeId : partnershipProjectTypeIdList) {
            if(ObjectUtil.isEmpty(partnershipProjectTypeId)){
                continue;
            }
            int partnershipProjectTypeIdInt = Integer.parseInt(partnershipProjectTypeId);
            PartnershipProjectTypeVO partnershipProjectTypeVO = collect.get(partnershipProjectTypeIdInt);
            if(ObjectUtil.isNotEmpty(partnershipProjectTypeVO)){
                result.add(partnershipProjectTypeVO);
            }
        }
        return result;
    }

    public static TPartnership getTPartnership (EditPartnershipDTO editPartnershipDTO,Integer operationStatus){
        TPartnership tPartnership = new TPartnership();
        if(ObjectUtil.isEmpty(editPartnershipDTO)){
            return tPartnership;
        }
        BeanUtils.copyProperties(editPartnershipDTO,tPartnership);
        TUser tUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tPartnership.setOperationStatus(operationStatus);
        tPartnership.setModifyDate(new Date());
        tPartnership.setModifyUserid(tUser.getUserid());
        StringBuffer sb = new StringBuffer();
        List<Integer> projectType = editPartnershipDTO.getProjectType();
        if(ObjectUtil.isNotEmpty(projectType)){
            for (Integer projectTypeId : projectType) {
                sb.append(projectTypeId).append(",");
            }
            String projectTypeStr = sb.toString();
            projectTypeStr = projectTypeStr.substring(0,projectTypeStr.length() - 1);
            tPartnership.setProjectType(projectTypeStr);
        }
        return tPartnership;
    }

    public static MemberDataBaseListVO getMemberDataBaseListVO(IPage<TMember> iPage){
        MemberDataBaseListVO memberDataBaseListVO = new MemberDataBaseListVO();
        if(ObjectUtil.isEmpty(iPage)){
            return memberDataBaseListVO;
        }
        memberDataBaseListVO.setMemberDataBaseTotal(iPage.getTotal());
        List<TMember> tMemberList = iPage.getRecords();
        if(ObjectUtil.isNotEmpty(tMemberList)){
            List<MemberDataBaseListRowVO> collect = tMemberList.stream().map(GenerateUtil::getMemberDataBaseListRowVO).collect(Collectors.toList());
            memberDataBaseListVO.setMemberDataBaseListRowVOList(collect);
        }
        return memberDataBaseListVO;
    }

    public static MemberDataBaseListRowVO getMemberDataBaseListRowVO(TMember tMember){
        MemberDataBaseListRowVO memberDataBaseListRowVO = new MemberDataBaseListRowVO();
        if(ObjectUtil.isEmpty(tMember)){
            return memberDataBaseListRowVO;
        }
        BeanUtils.copyProperties(tMember,memberDataBaseListRowVO);
        memberDataBaseListRowVO.setPartnershipName(tMember.getOwnerPartnershipName());
        MemberTypeVO memberTypeVO = new MemberTypeVO();
        memberDataBaseListRowVO.setMemberTypeVO(memberTypeVO);
        memberTypeVO.setMemberTypeId(tMember.getMemberTypeId());
        return memberDataBaseListRowVO;
    }

    public static List<TMemberType> getTMemberTypeList(List<String> memberTypeNameList){
        List<TMemberType> tMemberTypeList = new ArrayList<>();
        if(ObjectUtil.isEmpty(memberTypeNameList)){
            return tMemberTypeList;
        }
        TUser tUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        Integer userid = tUser.getUserid();
        memberTypeNameList.stream().forEach(memberTypeName -> {
            TMemberType tMemberType = new TMemberType();
            tMemberType.setMemberTypeName(memberTypeName);
            tMemberType.setBuildUserid(userid);
            tMemberType.setBuildDate(new Date());
            tMemberType.setDeleteflag(Constant.Judge.YES);
            tMemberTypeList.add(tMemberType);
        });

        return tMemberTypeList;
    }
    
    public static TSupplier getTSupplier(CreateSupplierDTO createSupplierDTO){
        TSupplier tSupplier = new TSupplier();
        if(ObjectUtil.isEmpty(createSupplierDTO)){
            return tSupplier;
        }
        BeanUtils.copyProperties(createSupplierDTO,tSupplier);
        TUser tUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tSupplier.setBuildDate(new Date());
        tSupplier.setBuildUserid(tUser.getUserid());
        tSupplier.setModifyDate(new Date());
        tSupplier.setModifyUserid(tUser.getUserid());
        tSupplier.setDeleteflag(Constant.Judge.YES);
        return tSupplier;
    }
    
    public static SupplierDetailVO getSupplierDetailVO(TSupplier tSupplier){
        SupplierDetailVO supplierDetailVO = new SupplierDetailVO();
        if(ObjectUtil.isEmpty(tSupplier)){
            return supplierDetailVO;
        }
        BeanUtils.copyProperties(tSupplier,supplierDetailVO);
        return supplierDetailVO;
    }

    public static TSupplier getTSupplier(EditSupplierDTO editSupplierDTO){
        TSupplier tSupplier = new TSupplier();
        if(ObjectUtil.isEmpty(editSupplierDTO)){
            return tSupplier;
        }
        BeanUtils.copyProperties(editSupplierDTO,tSupplier);
        TUser tUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tSupplier.setModifyDate(new Date());
        tSupplier.setModifyUserid(tUser.getUserid());
        return tSupplier;
    }
    
    public static SupplierListVO getSupplierListVO(IPage<TSupplier> tSupplierIPage){
        SupplierListVO supplierListVO = new SupplierListVO();
        if(ObjectUtil.isEmpty(tSupplierIPage)){
            return supplierListVO;
        }
        supplierListVO.setSupplierTotal(tSupplierIPage.getTotal());
        List<TSupplier> tSupplierList = tSupplierIPage.getRecords();
        if(ObjectUtil.isEmpty(tSupplierList)){
            return supplierListVO;
        }
        List<SupplierListRowVO> collect = tSupplierList.stream().map(GenerateUtil::getSupplierListRowVO).collect(Collectors.toList());
        supplierListVO.setSupplierListRowVOList(collect);
        return supplierListVO;
    }
    
    public static SupplierListRowVO getSupplierListRowVO(TSupplier tSupplier){
        SupplierListRowVO supplierListRowVO = new SupplierListRowVO();
        if(ObjectUtil.isEmpty(tSupplier)){
            return supplierListRowVO;
        }
        BeanUtils.copyProperties(tSupplier,supplierListRowVO);
        return supplierListRowVO;
    }
    
    public static TCase getTCase(CreateCaseDTO createCaseDTO){
        TCase tCase = new TCase();
        if(ObjectUtil.isEmpty(createCaseDTO)){
            return tCase;
        }
        BeanUtils.copyProperties(createCaseDTO,tCase);
        TUser tUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tCase.setBuildDate(new Date());
        tCase.setBuildUserid(tUser.getUserid());
        tCase.setModifyDate(new Date());
        tCase.setModifyUserid(tUser.getUserid());
        tCase.setDeleteflag(Constant.Judge.YES);
        return tCase;
    }

    public static TCase getTCase(EditCaseDTO editCaseDTO){
        TCase tCase = new TCase();
        if(ObjectUtil.isEmpty(editCaseDTO)){
            return tCase;
        }
        BeanUtils.copyProperties(editCaseDTO,tCase);
        TUser tUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tCase.setModifyDate(new Date());
        tCase.setModifyUserid(tUser.getUserid());
        return tCase;
    }
    
    public static CaseListVO getCaseListVO(IPage<TCase> tCaseIPage){
        CaseListVO caseListVO = new CaseListVO();
        if(ObjectUtil.isEmpty(tCaseIPage)){
            return caseListVO;
        }
        caseListVO.setCaseTotal(tCaseIPage.getTotal());
        List<TCase> tCaseList = tCaseIPage.getRecords();
        List<CaseListRowVO> caseListRowVOList = tCaseList.stream().map(GenerateUtil::getCaseListRowVO).collect(Collectors.toList());
        caseListVO.setCaseListRowVOList(caseListRowVOList);
        return caseListVO;
    }
    
    public static CaseListRowVO getCaseListRowVO(TCase tCase){
        CaseListRowVO caseListRowVO = new CaseListRowVO();
        if(ObjectUtil.isEmpty(tCase)){
            return caseListRowVO;
        }
        BeanUtils.copyProperties(tCase,caseListRowVO);
        CaseTypeVO caseTypeVO = new CaseTypeVO();
        caseListRowVO.setCaseTypeVO(caseTypeVO);
        caseTypeVO.setCaseTypeId(tCase.getTypeId());
        return caseListRowVO;
    }
    
    public static void setCaseTypeName(List<CaseListRowVO> caseListRowVOList,List<CaseTypeVO> caseTypeVOList){
        if(ObjectUtil.isEmpty(caseListRowVOList) || ObjectUtil.isEmpty(caseTypeVOList)){
            return;
        }
        Map<Integer, String> map = caseTypeVOList.stream().collect(Collectors.toMap(CaseTypeVO::getCaseTypeId, CaseTypeVO::getCaseTypeName));
        caseListRowVOList.stream().forEach(caseListRowVO -> {
            CaseTypeVO caseTypeVO = caseListRowVO.getCaseTypeVO();
            caseTypeVO.setCaseTypeName(map.get(caseTypeVO.getCaseTypeId()));
        });
    }
    
    public static CaseTypeVO getCaseTypeVO(List<CaseTypeVO> allCaseType,Integer myCaseTypeId){
        if(ObjectUtil.isEmpty(allCaseType) || ObjectUtil.isEmpty(myCaseTypeId)){
            return null;
        }
        CaseTypeVO caseTypeVO = new CaseTypeVO();
        Map<Integer, String> map = allCaseType.stream().collect(Collectors.toMap(CaseTypeVO::getCaseTypeId, CaseTypeVO::getCaseTypeName));
        caseTypeVO.setCaseTypeId(myCaseTypeId);
        caseTypeVO.setCaseTypeName(map.get(myCaseTypeId));
        return caseTypeVO;
    }
    
    public static TAuthor getTAuthor(CreateAuthorDTO createAuthorDTO){
        TAuthor tAuthor = new TAuthor();
        if(ObjectUtil.isEmpty(createAuthorDTO)){
            return tAuthor;
        }
        BeanUtils.copyProperties(createAuthorDTO,tAuthor);
        TUser tUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tAuthor.setBuildDate(new Date());
        tAuthor.setBuildUserid(tUser.getUserid());
        tAuthor.setModifyDate(new Date());
        tAuthor.setModifyUserid(tUser.getUserid());
        tAuthor.setDeleteflag(Constant.Judge.YES);
        return tAuthor;
    }
    
    public static TAuthor getTAuthor(EditAuthorDTO editAuthorDTO){
        TAuthor tAuthor = new TAuthor();
        if(ObjectUtil.isEmpty(editAuthorDTO)){
            return tAuthor;
        }
        BeanUtils.copyProperties(editAuthorDTO,tAuthor);
        TUser tUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tAuthor.setModifyDate(new Date());
        tAuthor.setModifyUserid(tUser.getUserid());
        return tAuthor;
    }
    
    public static AuthorListVO getAuthorListVO(IPage<TAuthor> iPage){
        AuthorListVO authorListVO = new AuthorListVO();
        if(ObjectUtil.isEmpty(iPage)){
            return authorListVO;
        }
        authorListVO.setAuthorTotal(iPage.getTotal());
        List<TAuthor> tAuthorList = iPage.getRecords();
        List<AuthorListRowVO> authorListRowVOList = tAuthorList.stream().map(GenerateUtil::getAuthorListRowVO).collect(Collectors.toList());
        authorListVO.setAuthorListRowVOList(authorListRowVOList);
        return authorListVO;
    }
    
    public static AuthorListRowVO getAuthorListRowVO(TAuthor tAuthor){
        AuthorListRowVO authorListRowVO = new AuthorListRowVO();
        if(ObjectUtil.isEmpty(tAuthor)){
            return authorListRowVO;
        }
        BeanUtils.copyProperties(tAuthor,authorListRowVO);
        return authorListRowVO;
    }
    
    public static ArticleListRowVO getArticleListRowVO(TArticle tArticle){
        ArticleListRowVO articleListRowVO = new ArticleListRowVO();
        if(ObjectUtil.isEmpty(tArticle)){
            return articleListRowVO;
        }
        BeanUtils.copyProperties(tArticle,articleListRowVO);
        return articleListRowVO;
    } 
    
    public static TArticle getTArticle(CreateArticleDTO createArticleDTO){
        TArticle tArticle = new TArticle();
        if(ObjectUtil.isEmpty(createArticleDTO)){
            return tArticle;
        }
        BeanUtils.copyProperties(createArticleDTO,tArticle);
        TUser tUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tArticle.setBuildDate(new Date());
        tArticle.setBuildUserid(tUser.getUserid());
        tArticle.setModifyDate(new Date());
        tArticle.setModifyUserid(tUser.getUserid());
        tArticle.setDeleteflag(Constant.Judge.YES);
        return tArticle;
    }
}
