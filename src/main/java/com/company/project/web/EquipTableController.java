package com.company.project.web;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.EquipTable;
import com.company.project.model.HistoryEquipTable;
import com.company.project.service.EquipTableService;
import com.company.project.service.HistoryEquipTableService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* Created by CodeGenerator on 2019/03/07.
*/
@RestController
@RequestMapping("/equip/table")
public class EquipTableController {
    @Resource
    private EquipTableService equipTableService;
    @Resource
    public HistoryEquipTableService historyEquipTableService;

    @PostMapping("/add")
    public Result add(EquipTable equipTable) {
        equipTableService.save(equipTable);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        equipTableService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(EquipTable equipTable) {
        equipTableService.update(equipTable);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        EquipTable equipTable = equipTableService.findById(id);
        return ResultGenerator.genSuccessResult(equipTable);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<EquipTable> list = equipTableService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
//------------------------------------------------------------------------------------------------------------------------------------------------
  @PostMapping("/queryByManageIp")
    public Result queryByManageIp(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,String manageIp) {
        PageHelper.startPage(page, size);
        List<EquipTable> list = equipTableService.queryByManageIp(manageIp);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    //更新设备
    @PostMapping("/updateEquip")
    public boolean updateEquip( @RequestBody JSONObject jsonObject)
    {
        Boolean flag;
        EquipTableController equipTableController = new EquipTableController();
        EquipTable equipTable=equipTableController.getEquip(jsonObject);
        flag= equipTableService.updateEquip(equipTable);

        return  flag;
    }



    //删除设备
    @PostMapping("/deleteEquip")
    public boolean deleteEquip(@RequestBody JSONObject jsonObject){
        boolean flag;
        EquipTableController equipTableController = new EquipTableController();
        EquipTable equipTable=equipTableController.getEquip(jsonObject);
        flag=equipTableService.deleteEquip(equipTable);
        HistoryEquipTable historyEquipTable=getHistoryEquip(jsonObject);
        historyEquipTableService.add(historyEquipTable);

        return flag;
    }

    //添加设备
    @PostMapping("/addEquip")
    public boolean addEquip(@RequestBody JSONObject jsonObject){
        boolean flag;
        EquipTableController equipTableController = new EquipTableController();
        EquipTable equipTable=equipTableController.getEquip(jsonObject);
        flag=equipTableService.addEquip(equipTable);
        return flag;
    }

    //查询设备
    @PostMapping("/queryEquip")
    public Result queryEquip(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,@RequestBody JSONObject jsonObject) {
        PageHelper.startPage(page, size);
        EquipTable equipTable = getEquip(jsonObject);

        System.out.println("manageIp"+equipTable.getManageIp());
        System.out.println("factory"+equipTable.getFactory());
        System.out.println("equipName"+equipTable.getEquipName());
        System.out.println("equipType"+equipTable.getEquipType());
        @SuppressWarnings("unchecked")
        List<EquipTable> list= equipTableService.queryEquip(equipTable);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    //获取EquipTable
    public EquipTable getEquip(@RequestBody JSONObject jsonObject){
        EquipTable equipTable = new EquipTable();
        //获取EquipTable字段
        JSONObject dataJson = new JSONObject(jsonObject);
        JSONObject response = dataJson.getJSONObject("equipTable");
        JSONArray data = response.getJSONArray("equip");
        JSONObject info = data.getJSONObject(0);

        //设备表信息
        equipTable.setManageIp(info.getString("manageIp"));
        equipTable.setId(info.getInteger("id"));
        equipTable.setEquipAddress(info.getString("equipAddress"));
        equipTable.setEquipModel(info.getString("equipModel"));
        equipTable.setEquipName(info.getString("equipName"));
        equipTable.setEquipNameOther(info.getString("equipNameOther"));
        equipTable.setEquipType(info.getString("equipType"));
        equipTable.setNetLevel(info.getString("netLevel"));
        equipTable.setFactory(info.getString("factory"));
        return equipTable;
    }

    //获取HistoryEquipTable
    public HistoryEquipTable getHistoryEquip(@RequestBody JSONObject jsonObject){
        HistoryEquipTable historyEquipTable = new HistoryEquipTable();
        //获取EquipTable字段
        JSONObject dataJson = new JSONObject(jsonObject);
        JSONObject response = dataJson.getJSONObject("equipTable");
        JSONArray data = response.getJSONArray("equip");
        JSONObject info = data.getJSONObject(0);

        //设备表信息
        historyEquipTable.setManageIp(info.getString("manageIp"));
        historyEquipTable.setId(info.getInteger("id"));
        historyEquipTable.setEquipAddress(info.getString("equipAddress"));
        historyEquipTable.setEquipModel(info.getString("equipModel"));
        historyEquipTable.setEquipName(info.getString("equipName"));
        historyEquipTable.setEquipNameOther(info.getString("equipNameOther"));
        historyEquipTable.setEquipType(info.getString("equipType"));
        historyEquipTable.setNetLevel(info.getString("netLevel"));
        historyEquipTable.setFactory(info.getString("factory"));
        historyEquipTable.setDeleteDate(new Date());
        return historyEquipTable;
    }


}
