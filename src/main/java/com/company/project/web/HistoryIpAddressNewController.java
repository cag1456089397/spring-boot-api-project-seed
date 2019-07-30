package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.HistoryIpAddressNew;
import com.company.project.service.HistoryIpAddressNewService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2019/04/26.
*/
@RestController
@RequestMapping("/history/ip/address/new")
public class HistoryIpAddressNewController {
    @Resource
    private HistoryIpAddressNewService historyIpAddressNewService;

    @PostMapping("/add")
    public Result add(HistoryIpAddressNew historyIpAddressNew) {
        historyIpAddressNewService.save(historyIpAddressNew);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        historyIpAddressNewService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(HistoryIpAddressNew historyIpAddressNew) {
        historyIpAddressNewService.update(historyIpAddressNew);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        HistoryIpAddressNew historyIpAddressNew = historyIpAddressNewService.findById(id);
        return ResultGenerator.genSuccessResult(historyIpAddressNew);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<HistoryIpAddressNew> list = historyIpAddressNewService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
