package com.company.project.web;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.HistoryBigCustomer;
import com.company.project.service.HistoryBigCustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
* Created by CodeGenerator on 2019/04/28.
*/
@RestController
@RequestMapping("/history/big/customer")
public class HistoryBigCustomerController {
    @Resource
    private HistoryBigCustomerService historyBigCustomerService;

    @PostMapping("/add")
    public Result add(HistoryBigCustomer historyBigCustomer) {
        historyBigCustomerService.save(historyBigCustomer);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        historyBigCustomerService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(HistoryBigCustomer historyBigCustomer) {
        historyBigCustomerService.update(historyBigCustomer);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        HistoryBigCustomer historyBigCustomer = historyBigCustomerService.findById(id);
        return ResultGenerator.genSuccessResult(historyBigCustomer);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, @RequestBody JSONObject jsonObject) {

        PageHelper.startPage(page, size);
        HistoryBigCustomer historyBigCustomer;
        BigCustomerController bigCustomerController =new BigCustomerController();
        historyBigCustomer=bigCustomerController.getHistoryBigCustomer(jsonObject);
        JSONObject dataJson = new JSONObject(jsonObject);
        JSONObject response = dataJson.getJSONObject("bigCustomer");
        //获取EquipTable字段

        JSONArray data = response.getJSONArray("customer");
        JSONObject info = data.getJSONObject(0);
        historyBigCustomer.setDeleteDate(info.getString("deleteDate"));
        System.out.println("deleteDate:"+historyBigCustomer.getDeleteDate());
        @SuppressWarnings("unchecked")
        List<HistoryBigCustomer> list = historyBigCustomerService.list(historyBigCustomer);

        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
