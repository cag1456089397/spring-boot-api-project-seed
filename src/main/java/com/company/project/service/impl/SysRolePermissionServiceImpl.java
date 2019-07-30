package com.company.project.service.impl;

import com.company.project.dao.SysRolePermissionMapper;
import com.company.project.model.SysRolePermission;
import com.company.project.service.SysRolePermissionService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/03/06.
 */
@Service
@Transactional
public class SysRolePermissionServiceImpl extends AbstractService<SysRolePermission> implements SysRolePermissionService {
    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

}
