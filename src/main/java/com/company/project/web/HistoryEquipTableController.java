package com.company.project.web;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.EquipTable;
import com.company.project.model.HistoryEquipTable;
import com.company.project.service.HistoryBigCustomerService;
import com.company.project.service.HistoryEquipTableService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
* Created by CodeGenerator on 2019/04/26.
*/
@RestController
@RequestMapping("/history/equip/table")
public class HistoryEquipTableController {
    @Resource
    private HistoryEquipTableService historyEquipTableService;

    @PostMapping("/add")
    public Result add(HistoryEquipTable historyEquipTable) {
        historyEquipTableService.save(historyEquipTable);
        System.out.println(historyEquipTable.getDeleteDate());
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        historyEquipTableService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(HistoryEquipTable historyEquipTable) {
        historyEquipTableService.update(historyEquipTable);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        HistoryEquipTable historyEquipTable = historyEquipTableService.findById(id);
        return ResultGenerator.genSuccessResult(historyEquipTable);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, @RequestBody JSONObject jsonObject) {
        PageHelper.startPage(page, size);
        EquipTableController equipTableController = new EquipTableController();
        HistoryEquipTable historyEquipTable = equipTableController.getHistoryEquip(jsonObject);
        //获取EquipTable字段
        JSONObject dataJson = new JSONObject(jsonObject);
        JSONObject response = dataJson.getJSONObject("equipTable");
        JSONArray data = response.getJSONArray("equip");
        JSONObject info = data.getJSONObject(0);
        //获取要查询的日期
        historyEquipTable.setDeleteDate(info.getDate("deleteDate"));

        @SuppressWarnings("unchecked")
        List<HistoryEquipTable> list = historyEquipTableService.list(historyEquipTable);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // 毫秒转日期
        Calendar c = Calendar.getInstance();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                c.setTimeInMillis(list.get(i).getDeleteDate().getTime());
                Date date = c.getTime();
                try {
                    list.get(i).setDeleteDate(sdf.parse(sdf.format(date)));
                    System.out.println(sdf.parse(sdf.format(date)).getClass().getName());
                }
                catch (Exception e){
                    e.printStackTrace( );
                }
                //System.out.println(sdf.parse(sdf.format(date)));
                System.out.println("deleteDate:"+list.get(i).getDeleteDate());
            }
        }
            PageInfo pageInfo = new PageInfo(list);
            return ResultGenerator.genSuccessResult(pageInfo);

    }
}
