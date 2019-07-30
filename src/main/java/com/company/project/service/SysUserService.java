package com.company.project.service;
import com.company.project.model.SysUser;
import com.company.project.core.Service;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/03/06.
 */
public interface SysUserService extends Service<SysUser> {
    public List login(@Param("username")String username, @Param("password") String password);

}
