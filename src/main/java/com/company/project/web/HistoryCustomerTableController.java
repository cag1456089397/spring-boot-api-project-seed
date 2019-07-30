package com.company.project.web;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.CustomerTable;
import com.company.project.model.HistoryCustomerTable;
import com.company.project.service.HistoryCustomerTableService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
* Created by CodeGenerator on 2019/04/29.
*/
@RestController
@RequestMapping("/history/customer/table")
public class HistoryCustomerTableController {
    @Resource
    private HistoryCustomerTableService historyCustomerTableService;

    @PostMapping("/add")
    public Result add(HistoryCustomerTable historyCustomerTable) {
        historyCustomerTableService.add(historyCustomerTable);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        historyCustomerTableService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(HistoryCustomerTable historyCustomerTable) {
        historyCustomerTableService.update(historyCustomerTable);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        HistoryCustomerTable historyCustomerTable = historyCustomerTableService.findById(id);
        return ResultGenerator.genSuccessResult(historyCustomerTable);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, @RequestBody JSONObject jsonObject) {
        PageHelper.startPage(page, size);
        JSONObject dataJson = new JSONObject(jsonObject);
        JSONObject response = dataJson.getJSONObject("customerTable");
        JSONArray data = response.getJSONArray("customer");
        JSONObject info = data.getJSONObject(0);
        CustomerTableController customerTableController = new CustomerTableController();
        HistoryCustomerTable historyCustomerTable =customerTableController.getHistoryCustomerTable(jsonObject);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
           date= sdf.parse(info.getDate("deleteDate").toString());
        }catch (Exception e){}
        historyCustomerTable.setDeleteDate(date);
        System.out.println(historyCustomerTable.getDeleteDate());
        System.out.println(historyCustomerTable.getConnectIp());
        @SuppressWarnings("unchecked")
        List<HistoryCustomerTable> list = historyCustomerTableService.list(historyCustomerTable);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
