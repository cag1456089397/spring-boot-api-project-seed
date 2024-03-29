package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.SysRolePermission;
import com.company.project.service.SysRolePermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2019/03/06.
*/
@RestController
@RequestMapping("/sys/role/permission")
public class SysRolePermissionController {
    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @PostMapping("/add")
    public Result add(SysRolePermission sysRolePermission) {
        sysRolePermissionService.save(sysRolePermission);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sysRolePermissionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SysRolePermission sysRolePermission) {
        sysRolePermissionService.update(sysRolePermission);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SysRolePermission sysRolePermission = sysRolePermissionService.findById(id);
        return ResultGenerator.genSuccessResult(sysRolePermission);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysRolePermission> list = sysRolePermissionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
