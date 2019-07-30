package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends Mapper<SysUser> {
    List login(@Param("username") String username, @Param("password") String password);
}