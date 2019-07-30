package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.PortAutoChack;
import com.company.project.service.PortAutoChackService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2019/07/22.
*/
@RestController
@RequestMapping("/port/auto/chack")
public class PortAutoChackController {
    @Resource
    private PortAutoChackService portAutoChackService;

    @PostMapping("/add")
    public Result add(PortAutoChack portAutoChack) {
        portAutoChackService.save(portAutoChack);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        portAutoChackService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(PortAutoChack portAutoChack) {
        portAutoChackService.update(portAutoChack);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        PortAutoChack portAutoChack = portAutoChackService.findById(id);
        return ResultGenerator.genSuccessResult(portAutoChack);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PortAutoChack> list = portAutoChackService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
