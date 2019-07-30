package com.company.project.web;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.BigCustomer;
import com.company.project.model.HistoryBigCustomer;
import com.company.project.service.BigCustomerService;
import com.company.project.service.HistoryBigCustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* Created by CodeGenerator on 2019/04/09.
*/
@RestController
@RequestMapping("/big/customer")
public class BigCustomerController {
    @Resource
    private BigCustomerService bigCustomerService;
    @Resource
    private  HistoryBigCustomerService historyBigCustomerService;
    @PostMapping("/add")
    public Result add(BigCustomer bigCustomer) {
        bigCustomerService.save(bigCustomer);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        bigCustomerService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(BigCustomer bigCustomer) {
        bigCustomerService.update(bigCustomer);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        BigCustomer bigCustomer = bigCustomerService.findById(id);
        return ResultGenerator.genSuccessResult(bigCustomer);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BigCustomer> list = bigCustomerService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 查询大客户
     * @param page
     * @param size
     * @param e
     * @return
     */
    @PostMapping(value="/queryByBigCustomer",produces = "application/json;charset=UTF-8")
    public Result queryByBigCustomer(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, @RequestBody JSONObject e) {
        PageHelper.startPage(page, size);
        BigCustomer bigCustomer=getBigCustomer(e);
        System.out.println("id:"+bigCustomer.getId());
        System.out.println("orderNo"+bigCustomer.getOrderNo());
        System.out.println("orderNumber:"+bigCustomer.getOrderNumber());
        System.out.println("oldCustomerName:"+bigCustomer.getOldCustomerName());
        System.out.println("standardCustomer:"+bigCustomer.getStandardCustomerName());
        System.out.println("business_opening_date:"+bigCustomer.getBusinessOpeningDate());
        System.out.println("z_point_customer_address:"+bigCustomer.getzPointCustomerAddress());
        System.out.println("local_transmission_info:"+bigCustomer.getLocalTransmissionInfo());
        System.out.println("number_or_ip_resource:"+bigCustomer.getNumberOrIpResource());
        @SuppressWarnings("unchecked")
        List<BigCustomer> list = bigCustomerService.queryByBigCustomer(bigCustomer);

       //匹配日期的正则表达式
        String strReg="\\d{4}-\\d+-\\d+";
        Pattern p = Pattern.compile(strReg);

        for(int i=0;i<list.size();i++)
        {
            System.out.println("numberOrIpResource:"+list.get(i).getNumberOrIpResource());
            if (list.get(i).getBusinessOpeningDate()==null||list.get(i).getBusinessOpeningDate().length()==0)
            {
                System.out.println("empty");
            }
            else
            {
                Matcher m = p.matcher(list.get(i).getBusinessOpeningDate());
                while(m.find()){
                    list.get(i).setBusinessOpeningDate(m.group());
                }
               /* if(list.get(i).getBusinessOpeningDate().indexOf(" ")!=-1){
                    //System.out.println(list.get(i).getBusinessOpeningDate().substring(0,list.get(i).getBusinessOpeningDate().indexOf("")));
                list.get(i).setBusinessOpeningDate(list.get(i).getBusinessOpeningDate().substring(0,10));}
            else{

                }*/
            }
        System.out.println("第"+i+"条数据："+list.get(i).getBusinessOpeningDate());

            if (list.get(i).getLedgerUpdateTime()==null||list.get(i).getLedgerUpdateTime().length()==0)
            {
                System.out.println("empty");
            }
            else {
                Matcher m = p.matcher(list.get(i).getLedgerUpdateTime());
                while (m.find()) {
                    list.get(i).setLedgerUpdateTime(m.group());
              /*  if(list.get(i).getLedgerUpdateTime().indexOf(" ")!=-1){
                    //System.out.println(list.get(i).getBusinessOpeningDate().substring(0,list.get(i).getBusinessOpeningDate().indexOf("")));
                    list.get(i).setLedgerUpdateTime(list.get(i).getLedgerUpdateTime().substring(0,10));}
                else{

                }*/
                }
            }
            System.out.println("第"+i+"条数据："+list.get(i).getLedgerUpdateTime());
    }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    /**
     * 修改大客户表
     * @param e
     * @return
     */

    @PostMapping(value="/updateByBigCustomer",produces = "application/json;charset=UTF-8")
    public Boolean updateByBigCustomer( @RequestBody JSONObject e) {
        Boolean flag;
        BigCustomer bigCustomer=getBigCustomer(e);
        System.out.println(bigCustomer.getId());
        System.out.println(bigCustomer.getOrderNo());
        System.out.println(bigCustomer.getOrderNumber());
        System.out.println(bigCustomer.getBusinessOpeningDate());
        System.out.println(bigCustomer.getNumberOrIpResource());

        flag = bigCustomerService.updateByBigCustomer(bigCustomer);
        return flag;
    }

    /**
     * 添加
     * @param e
     * @return
     */
    @PostMapping(value="/addByBigCustomer",produces = "application/json;charset=UTF-8")
    public boolean addByBigCustomer(@RequestBody JSONObject e) {
        Boolean flag;
        BigCustomer bigCustomer=getBigCustomer(e);
        System.out.println("id:"+bigCustomer.getId());
        System.out.println("orderNo:"+bigCustomer.getOrderNo());
        System.out.println("orderNumber:"+bigCustomer.getOrderNumber());
        System.out.println("number_or_ip_resource:"+bigCustomer.getNumberOrIpResource());
        System.out.println("business_opening_date:"+bigCustomer.getBusinessOpeningDate());
        System.out.println("number_or_ip_resource"+bigCustomer.getNumberOrIpResource());
        flag = bigCustomerService.addByBigCustomer(bigCustomer);

        return flag;
    }

    /**
     * 删除大客户
     * @param e
     * @return
     */
    @PostMapping(value="/deleteByBigCustomer",produces = "application/json;charset=UTF-8")
    public boolean deleteByBigCustomer(@RequestBody JSONObject e) {
        Boolean flag;
        BigCustomer bigCustomer=getBigCustomer(e);
        HistoryBigCustomer historyBigCustomer=getHistoryBigCustomer(e);



        System.out.println(bigCustomer.getId());
        System.out.println(bigCustomer.getOrderNo());
        System.out.println(bigCustomer.getOrderNumber());

        // @SuppressWarnings("unchecked")
        flag = bigCustomerService.deleteByBigCustomer(bigCustomer);

        historyBigCustomerService.add(historyBigCustomer);

        return flag;
    }

    /**
     * 获取BigCustomer
     * @param jsonObject
     * @return
     */
    public BigCustomer getBigCustomer(@RequestBody JSONObject jsonObject){
        BigCustomer bigCustomer= new BigCustomer();
        JSONObject dataJson = new JSONObject(jsonObject);
        JSONObject response = dataJson.getJSONObject("bigCustomer");
        //获取EquipTable字段

        JSONArray data = response.getJSONArray("customer");
        JSONObject info = data.getJSONObject(0);

        bigCustomer.setAccessStation(info.getString("accessStation"));
        bigCustomer.setaPoint(info.getString("aPoint"));
        bigCustomer.setaPointCustomerAddress(info.getString("aPointCustomerAddress"));
        bigCustomer.setArea(info.getString("area"));
        bigCustomer.setBusinessOpeningDate(info.getString("businessOpeningDate"));
        bigCustomer.setBusinessType(info.getString("businessType"));
        bigCustomer.setCircuitNumber(info.getString("circuitNumber"));
        bigCustomer.setCircuitType(info.getString("circuitType"));
        bigCustomer.setComment1(info.getString("comment1"));
        bigCustomer.setComment2(info.getString("comment2"));
        bigCustomer.setCustomerContact(info.getString("customerContact"));
        bigCustomer.setCustomerEquipInstallInfo(info.getString("customerEquipInstallInfo"));
        bigCustomer.setCustomerGrade(info.getString("customerGrade"));
        bigCustomer.setCustomerUsageFeatures(info.getString("customerUsageFeatures"));
        bigCustomer.setCustomerTelephone(info.getString("customerTelephone"));
        bigCustomer.setEngineeringUnifiedWorkOrderNumber(info.getString("engineeringUnifiedWorkOrderNumber"));
        bigCustomer.setFirstRoute(info.getString("firstRoute"));
        bigCustomer.setId(info.getInteger("id"));
        bigCustomer.setIndustry(info.getString("industry"));
        bigCustomer.setIsCollectors(info.getString("isCollectors"));
        bigCustomer.setLedgerUpdateTime(info.getString("ledgerUpdateTime"));
        bigCustomer.setLevel(info.getString("level"));
        bigCustomer.setLocalTransmissionInfo(info.getString("localTransmissionInfo"));
        bigCustomer.setNumberOfCircuitBars(info.getString("numberOfCircuitBars"));
        bigCustomer.setNumberOrIpResource(info.getString("numberOrIpResource"));
        bigCustomer.setOldCustomerName(info.getString("oldCustomerName"));
        bigCustomer.setOneStopFlowNumber(info.getString("oneStopFlowNumber"));
        bigCustomer.setOrderNo(info.getString("orderNo"));
        bigCustomer.setOrderNumber(info.getString("orderNumber"));
        bigCustomer.setProductNumber(info.getString("productNumber"));
        bigCustomer.setRate(info.getString("rate"));
        bigCustomer.setSecondRoute(info.getString("secondRoute"));
        bigCustomer.setSelfDivisionMail(info.getString("selfDivisionMail"));
        bigCustomer.setSelfDivisionManager(info.getString("selfDivisionManager"));
        bigCustomer.setSelfDivisionTelephone(info.getString("selfDivisionTelephone"));
        bigCustomer.setStandardCustomerName(info.getString("standardCustomerName"));
        bigCustomer.setTopListIdent(info.getString("topListIdent"));
        bigCustomer.setTown(info.getString("town"));
        bigCustomer.setzPoint(info.getString("zPoint"));
        bigCustomer.setzPointCustomerAddress(info.getString("zPointCustomerAddress"));
        return  bigCustomer;
    }

    /**
     *
     * @param jsonObject
     * @return historyBigCustomer
     */

    public HistoryBigCustomer getHistoryBigCustomer(@RequestBody JSONObject jsonObject){
        HistoryBigCustomer historyBigCustomer= new HistoryBigCustomer();
        JSONObject dataJson = new JSONObject(jsonObject);
        JSONObject response = dataJson.getJSONObject("bigCustomer");
        //获取EquipTable字段

        JSONArray data = response.getJSONArray("customer");
        JSONObject info = data.getJSONObject(0);

        historyBigCustomer.setAccessStation(info.getString("accessStation"));
        historyBigCustomer.setaPoint(info.getString("aPoint"));
        historyBigCustomer.setaPointCustomerAddress(info.getString("aPointCustomerAddress"));
        historyBigCustomer.setArea(info.getString("area"));
        historyBigCustomer.setBusinessOpeningDate(info.getString("businessOpeningDate"));
        historyBigCustomer.setBusinessType(info.getString("businessType"));
        historyBigCustomer.setCircuitNumber(info.getString("circuitNumber"));
        historyBigCustomer.setCircuitType(info.getString("circuitType"));
        historyBigCustomer.setComment1(info.getString("comment1"));
        historyBigCustomer.setComment2(info.getString("comment2"));
        historyBigCustomer.setCustomerContact(info.getString("customerContact"));
        historyBigCustomer.setCustomerEquipInstallInfo(info.getString("customerEquipInstallInfo"));
        historyBigCustomer.setCustomerGrade(info.getString("customerGrade"));
        historyBigCustomer.setCustomerUsageFeatures(info.getString("customerUsageFeatures"));
        historyBigCustomer.setCustomerTelephone(info.getString("customerTelephone"));
        historyBigCustomer.setEngineeringUnifiedWorkOrderNumber(info.getString("engineeringUnifiedWorkOrderNumber"));
        historyBigCustomer.setFirstRoute(info.getString("firstRoute"));
        historyBigCustomer.setId(info.getInteger("id"));
        historyBigCustomer.setIndustry(info.getString("industry"));
        historyBigCustomer.setIsCollectors(info.getString("isCollectors"));
        historyBigCustomer.setLedgerUpdateTime(info.getString("ledgerUpdateTime"));
        historyBigCustomer.setLevel(info.getString("level"));
        historyBigCustomer.setLocalTransmissionInfo(info.getString("localTransmissionInfo"));
        historyBigCustomer.setNumberOfCircuitBars(info.getString("numberOfCircuitBars"));
        historyBigCustomer.setNumberOrIpResource(info.getString("numberOrIpResource"));
        historyBigCustomer.setOldCustomerName(info.getString("oldCustomerName"));
        historyBigCustomer.setOneStopFlowNumber(info.getString("oneStopFlowNumber"));
        historyBigCustomer.setOrderNo(info.getString("orderNo"));
        historyBigCustomer.setOrderNumber(info.getString("orderNumber"));
        historyBigCustomer.setProductNumber(info.getString("productNumber"));
        historyBigCustomer.setRate(info.getString("rate"));
        historyBigCustomer.setSecondRoute(info.getString("secondRoute"));
        historyBigCustomer.setSelfDivisionMail(info.getString("selfDivisionMail"));
        historyBigCustomer.setSelfDivisionManager(info.getString("selfDivisionManager"));
        historyBigCustomer.setSelfDivisionTelephone(info.getString("selfDivisionTelephone"));
        historyBigCustomer.setStandardCustomerName(info.getString("standardCustomerName"));
        historyBigCustomer.setTopListIdent(info.getString("topListIdent"));
        historyBigCustomer.setTown(info.getString("town"));
        historyBigCustomer.setzPoint(info.getString("zPoint"));
        historyBigCustomer.setzPointCustomerAddress(info.getString("zPointCustomerAddress"));
        String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        historyBigCustomer.setDeleteDate(date);
        return  historyBigCustomer;
    }
}
