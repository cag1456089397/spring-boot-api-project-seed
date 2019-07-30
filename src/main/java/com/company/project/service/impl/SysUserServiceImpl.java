package com.company.project.service.impl;

import com.company.project.dao.SysUserMapper;
import com.company.project.model.SysUser;
import com.company.project.service.SysUserService;
import com.company.project.core.AbstractService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/03/06.
 */
@Service
@Transactional
public class SysUserServiceImpl extends AbstractService<SysUser> implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    public List login(@Param("username")String username, @Param("password") String password){
        return this.sysUserMapper.login(username,password);
    }

}
