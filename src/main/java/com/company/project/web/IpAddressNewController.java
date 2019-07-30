package com.company.project.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.CustomerTable;
import com.company.project.model.EquipTable;
import com.company.project.model.IpAddressNew;
import com.company.project.service.CustomerTableService;
import com.company.project.service.EquipTableService;
import com.company.project.service.IpAddressNewService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.company.project.service.impl.IpAddressNewServiceImpl.ipToLong;
import static com.company.project.service.impl.IpAddressNewServiceImpl.longToIp;

/**
 * Created by CodeGenerator on 2019/01/28.
 */
@RestController
@RequestMapping("/ip/address/new")
public class IpAddressNewController {
    @Resource
    private IpAddressNewService ipAddressNewService;
	//---------------------------------------------
    @Resource
    public EquipTableService equipTableService;
    @Resource
    public CustomerTableService customerTableService;


	//-------------------------------------------------


    @PostMapping("/add")
    public Result add(IpAddressNew ipAddressNew) {
        ipAddressNewService.save(ipAddressNew);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        ipAddressNewService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(IpAddressNew ipAddressNew) {
        ipAddressNewService.update(ipAddressNew);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        IpAddressNew ipAddressNew = ipAddressNewService.findById(id);
        return ResultGenerator.genSuccessResult(ipAddressNew);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<IpAddressNew> list = ipAddressNewService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(HttpServletResponse response) throws IOException {
        List<IpAddressNew> ipAddressNewList = ipAddressNewService.selectIpAddressNew();

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("获取excel测试表格");

        HSSFRow row = null;


        /**
         * 表头
         */

        row = sheet.createRow(0);
        row.setHeight((short) (22.50 * 20));//设置行高

        row.createCell(0).setCellValue("起始IP");//为第一个单元格设值
        row.createCell(1).setCellValue("结束IP");//为第二个单元格设值
        row.createCell(2).setCellValue("掩码长度");//为第三个单元格设值
        row.createCell(3).setCellValue("IP数目");//为第三个单元格设值
        row.createCell(4).setCellValue("IP类型");//为第三个单元格设值
        row.createCell(5).setCellValue("是否使用");//为第三个单元格设值
        row.createCell(6).setCellValue("工单编号");//为第三个单元格设值
        row.createCell(7).setCellValue("客户名称");//为第三个单元格设值
        row.createCell(8).setCellValue("产品号码");//为第三个单元格设值
        row.createCell(9).setCellValue("使用日期");//为第三个单元格设值
        row.createCell(10).setCellValue("回收日期");//为第三个单元格设值
        row.createCell(11).setCellValue("备注");//为第三个单元格设值
        /**
         * 数据
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        for (int i = 0; i < ipAddressNewList.size(); i++) {
            row = sheet.createRow(i + 1);
            IpAddressNew ipAddressNew = ipAddressNewList.get(i);
            row.createCell(0).setCellValue(ipAddressNew.getBeginIp());
            row.createCell(1).setCellValue(ipAddressNew.getEndIp());
            row.createCell(2).setCellValue(ipAddressNew.getMaskLength());
            row.createCell(3).setCellValue(ipAddressNew.getIpNum());
            if (ipAddressNew.getIpType() != null) {
                row.createCell(4).setCellValue(ipAddressNew.getIpType());
            }
            if (ipAddressNew.getIsUse() != null) {
                row.createCell(5).setCellValue(ipAddressNew.getIsUse());
            }

            if (ipAddressNew.getWfId() != null) {
                row.createCell(6).setCellValue(ipAddressNew.getWfId());
            }
            if (ipAddressNew.getCustomerName() != null) {
                row.createCell(7).setCellValue(ipAddressNew.getCustomerName());
            }
            if (ipAddressNew.getProductNumber() != null) {
                row.createCell(8).setCellValue(ipAddressNew.getProductNumber());
            }
            if (ipAddressNew.getUseDate() != null) {
                String useDate=sdf.format(ipAddressNew.getUseDate());
                row.createCell(9).setCellValue(useDate);
            }
            if (ipAddressNew.getUnuseDate() != null) {
                String unuseDate=sdf.format(ipAddressNew.getUnuseDate());
                row.createCell(10).setCellValue(unuseDate);
            }
            if (ipAddressNew.getRemark() != null) {
                row.createCell(11).setCellValue(ipAddressNew.getRemark());
            }


        }
        sheet.setDefaultRowHeight((short) (16.5 * 20));
        //列宽自适应
        for (int i = 0; i <= 13; i++) {
            sheet.autoSizeColumn(i);
        }

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename=ipAddressAll.xls");//默认Excel名称
        wb.write(os);
        os.flush();
        os.close();


    }

    /**
     * 表格输入
     *
     * @param file
     * @param session
     * @return
     */
    @RequestMapping(value = "/import")
    public String exImport(@RequestParam(value = "filename") MultipartFile file, HttpSession session) {

        boolean a = false;

        String fileName = file.getOriginalFilename();

        try {
            a = ipAddressNewService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:index";
    }

    /*
     *业务开通——分配资源
     **/
    @PostMapping({"/getIpAddressNewByBuOpen"})
    public Result getIpAddressNewByBuOpen(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                          IpAddressNew ipAddressNew) {
        PageHelper.startPage(page, size);

        List<IpAddressNew> list = this.ipAddressNewService.selectIpAddressNewByBuOpen(ipAddressNew);
        PageInfo pageInfo = new PageInfo(list);
        //返回表单，也可以返回任何值
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 得到起始地址和结束地址的LONG型
     *
     * @param page
     * @param size
     * @return
     */
    @PostMapping({"/selectIpAddressNew1"})
    public Result selectIpAddressNew1(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size
    ) {
        PageHelper.startPage(page, size);

        int status = this.ipAddressNewService.selectIpAddressNew1();
        return ResultGenerator.genSuccessResult(status);
    }

    /**
     * 起始IP查询
     *
     * @param page
     * @param size
     * @param beginIp
     * @return
     */
    @PostMapping({"/selectIpAddressNewByBeginIp"})
    public Result selectIpAddressNewByBeginIp(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                              String beginIp) {
        PageHelper.startPage(page, size);

        List<IpAddressNew> list = this.ipAddressNewService.selectIpAddressNewByBeginIp(beginIp);
/*
        for (IpAddressNew ipAddressNew : list) {

            //将数据进行格式化，按照一定的格式，比如xxxx年xxxx月xxxx日
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            //将util.Date转换成为sql.Date，在Date.valueOf(String str)中发生了 util.Date.toSting(),就将Date类型转换成了String类型的字符串
            String useDateStr=sdf.format(ipAddressNew.getUseDate());

            //利用simpleDateFormat.format（）方法进行格式输出，他的作用就是按照第一步的格式进行输出时间

        }*/


        PageInfo pageInfo = new PageInfo(list);
        //返回表单，也可以返回任何值
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /*
     * 错误信息更正
     * */
    @PostMapping({"/updateIpAddressNewByErData"})
    public Result updateIpAddressByErData(@RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "0") Integer size,
                                          IpAddressNew ipAddressNew) {
        PageHelper.startPage(page, size);

        int status = this.ipAddressNewService.updateIpAddressNewByErData(ipAddressNew);
        //返回表单，也可以返回任何值
        return ResultGenerator.genSuccessResult(status);
    }


    /**
     * 地址回收
     *
     * @param page
     * @param size
     * @param ipAddressNew
     * @return
     */

    @PostMapping({"/autoTogetherIp"})
    public Result autoTogetherIp(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                 IpAddressNew ipAddressNew) {
        ipAddressNew.setIsUse("预留");
        Date date = new Date();
        ipAddressNew.setUnuseDate(date);

        PageHelper.startPage(page, size);

        int status = this.ipAddressNewService.autoTogetherIp(ipAddressNew);
        //返回表单，也可以返回任何值
        return ResultGenerator.genSuccessResult(status);
    }

    /**
     * 自动地址拆分
     *
     * @param page
     * @param size
     * @param ipAddressNew
     * @return
     */
    @PostMapping({"/autoSplitIp"})
    public Result autoSplitIp(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                              IpAddressNew ipAddressNew) {
        PageHelper.startPage(page, size);

        int status = this.ipAddressNewService.autoSplitIp(ipAddressNew);
        //返回表单，也可以返回任何值
        return ResultGenerator.genSuccessResult(status);
    }

    /**
     * 手动地址拆分
     *
     * @param page
     * @param size
     * @param beginIp
     * @return
     */
    @PostMapping({"/splitIp"})
    public Result splitIp(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                          String beginIp) {
        int ipNum = this.ipAddressNewService.selectIpAddressNewByBeginIp(beginIp).get(0).getIpNum();
        if (ipNum != 1) {
            PageHelper.startPage(page, size);
            this.ipAddressNewService.splitIp(beginIp);
            //返回表单，也可以返回任何值
            List<IpAddressNew> list = new ArrayList<>();
            IpAddressNew ipAddressNew = this.ipAddressNewService.selectIpAddressNewByBeginIp(beginIp).get(0);
            list.add(ipAddressNew);
            Long endIpLong = ipToLong(ipAddressNew.getEndIp());
            Long beginIpLong2 = endIpLong + 1;
            String beginIp2 = longToIp(beginIpLong2);
            IpAddressNew ipAddressNew2 = this.ipAddressNewService.selectIpAddressNewByBeginIp(beginIp2).get(0);
            list.add(ipAddressNew2);
            PageInfo pageInfo = new PageInfo(list);
            return ResultGenerator.genSuccessResult(pageInfo);
        } else {
            return ResultGenerator.genFailResult("ip数目小于2的地址不能拆分");
        }
    }


	//-------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 根据ip_type和beginIp选择要查询的表
     *
     * @param page
     * @param size
     * @param beginIp
     * @return
     */
    @PostMapping("/queryDetail")
    public Result queryDetail(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, String beginIp) {
        PageHelper.startPage(page, size);
        @SuppressWarnings("unchecked")
        String tableName = "";
        List<IpAddressNew> list = ipAddressNewService.queryByBeginIp(beginIp);
        List<?> table = null;
        if (list.size() > 0) {
            System.out.println("ipType:" + list.get(0).getIpType());
            System.out.println(chooseTable(list.get(0).getIpType()));
            tableName = chooseTable(list.get(0).getIpType());

            if (tableName.equals("equip_table")) {
                //查询设备表
                @SuppressWarnings("unchecked")
                List<EquipTable> equip = equipTableService.queryByManageIp(beginIp);
                table = equip;

            } else {
                //查询客户表
                @SuppressWarnings("unchecked")
                List<CustomerTable> customer = customerTableService.queryByUserIp(beginIp);
                table = customer;
            }
        } else {
            table = null;
        }


        PageInfo pageInfo = new PageInfo(table);
        // PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    /**
     * 通过beginIp查询ip_address_new表信息
     *
     * @param page
     * @param size
     * @param beginIp
     * @return
     */
    @PostMapping("/query")
    public Result queryByBeginIp(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, String beginIp) {
        PageHelper.startPage(page, size);
        @SuppressWarnings("unchecked")
        List<IpAddressNew> li = ipAddressNewService.query(beginIp);
        if (li.size() > 0) {
            System.out.println("ipType:" + li.get(0).getIpType());
            System.out.println("hello");
        }
      /* System.out.println(chooseTable(list.get(0).getIpType()));
        String tableName=chooseTable(list.get(0).getIpType());
        List<?> list2;
        if(tableName.equals("equip_table")){
            //查询设备表
            @SuppressWarnings("unchecked")
            List<EquipTable> list1=equipTableService.queryByManageIp(begin_ip);
            list2=list1;

        }
        else{
            //查询客户表
            @SuppressWarnings("unchecked")unchecked
            List<CustomerTable> list1= customerTableService.queryByUserIp(begin_ip);
            list2=list1;
        }
        PageInfo pageInfo = new PageInfo(list2);*/
        PageInfo pageInfo = new PageInfo(li);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/tables")
    public Result showResult(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        List<?> table = ipAddressNewService.combine();
        PageInfo pageInfo = new PageInfo(table);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 获取ip_address_new表中的ip_type的值
     *
     * @param page
     * @param size
     * @param begin_ip
     * @return
     */
    public String IpType(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, String begin_ip) {
        String ipType = "";
        @SuppressWarnings("unchecked")
        List<IpAddressNew> row = ipAddressNewService.queryByBeginIp(begin_ip);
        System.out.println(row.get(0).getIpType());
        if (row.size() > 0) {
            System.out.println("ip_type:" + row.get(0).getIpType());
            ipType = row.get(0).getIpType();
        } else {
            System.out.println("记录不存在");

        }
        return ipType;
    }


   /*
   根据getIpType类型确定需要进行查询的表
   */

    public String chooseTable(String ipType) {
        String tableName = "";
        if (ipType.equals("扁平化地址") || ipType.equals("设备管理地址") || ipType.equals("互联地址")) {
            tableName = "equip_table";
        } else {
            tableName = "customer_table";
        }

        return tableName;

    }

    /**
     * 编辑设备表信息并同步到ip总表
     *
     * @param page
     * @param size
     * @param equipTable 要变更的equipTable对象
     * @return
     */
    @PostMapping(value = "/editDetail")
    public boolean editDetail(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, @RequestBody JSONObject e) {
        boolean flag = false;

        IpAddressNew ipAddressNew = new IpAddressNew();
        EquipTable equipTable = new EquipTable();
        JSONObject dataJson = new JSONObject(e);
        JSONObject response = dataJson.getJSONObject("equipTable");
        //获取EquipTable字段

        JSONArray data = response.getJSONArray("equip");
        JSONObject info = data.getJSONObject(0);

        //获取旧的ip地址
       ;
        JSONArray da = response.getJSONArray("ipAddress");
        JSONObject in=da.getJSONObject(0);
        //System.out.println(in.getString("oldIp"));
        System.out.println(info.getString("manageIp"));

        //获取equipTable
        System.out.println(info.getString("manageIp"));
        equipTable.setManageIp(info.getString("manageIp"));
        equipTable.setId(Integer.parseInt(info.getString("id")));
        equipTable.setEquipAddress(info.getString("equipAddress"));
        equipTable.setEquipModel(info.getString("equipModel"));
        equipTable.setEquipName(info.getString("equipName"));
        equipTable.setEquipNameOther(info.getString("equipNameOther"));
        equipTable.setEquipType(info.getString("equipType"));
        equipTable.setNetLevel(info.getString("netLevel"));
        equipTable.setFactory(info.getString("factory"));
        flag = equipTableService.updateEquip(equipTable);
        //获取ipAddressNew
        ipAddressNew.setId(in.getInteger("id"));
        ipAddressNew.setBeginIp(in.getString("beginIp"));
        ipAddressNew.setBeginIpLong(in.getLong("beginIpLong"));
        ipAddressNew.setCustomerName(in.getString("customerName"));
        ipAddressNew.setEndIp(in.getString("endIp"));
        ipAddressNew.setEndIpLong(in.getLong("endIpLong"));
        ipAddressNew.setIpNum(in.getInteger("ipNum"));
        ipAddressNew.setIpType(in.getString("ipType"));
        ipAddressNew.setIsUse(in.getString("isUse"));
        ipAddressNew.setMaskLength(in.getInteger("maskLength"));
        ipAddressNew.setProductNumber(in.getString("productNumber"));
        ipAddressNew.setRemark(in.getString("remark"));
        ipAddressNew.setUnuseDate(in.getDate("unUseDate"));
        ipAddressNew.setUseDate(in.getDate("useDate"));
        ipAddressNew.setWfId(in.getString("wfId"));
        //equipTableService.update(equipTable);
        //ipAddressNewService.queryByBeginIp(info.getString("manageIp"));
        ipAddressNewService.updateTable(ipAddressNew);

        return flag;

    }

    /**
     * 删除设备表信息并同步到ip总表
     *
     * @param page
     * @param size
     * @param JSONObject 要删除的equipTable对象
     * @return
     */
    @PostMapping(value = "/deleteDetail")
    public boolean deleteDetail(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, @RequestBody JSONObject e) {
        boolean flag = false;
        EquipTable equipTable = new EquipTable();
        IpAddressNew ipAddressNew = new IpAddressNew();
        JSONObject dataJson = new JSONObject(e);
        JSONObject response = dataJson.getJSONObject("equipTable");
        //获取EquipTable字段

        JSONArray data = response.getJSONArray("equip");
        JSONObject info = data.getJSONObject(0);

        //获取beginIp地址

        //JSONArray da = response.getJSONArray("ipAddress");
        //JSONObject in=da.getJSONObject(0);
        //System.out.println(in.getString("oldIp"));
        System.out.println(info.getString("manageIp"));
        //ipAddressNewService.updateTable(info.getString("manageIp"),in.getString("oldIp"));

        System.out.println(info.getString("manageIp"));
        equipTable.setManageIp(info.getString("manageIp"));
        equipTable.setId(Integer.parseInt(info.getString("id")));
        equipTable.setEquipAddress(info.getString("equipAddress"));
        equipTable.setEquipModel(info.getString("equipModel"));
        equipTable.setEquipName(info.getString("equipName"));
        equipTable.setEquipNameOther(info.getString("equipNameOther"));
        equipTable.setEquipType(info.getString("equipType"));
        equipTable.setNetLevel(info.getString("netLevel"));
        equipTable.setFactory(info.getString("factory"));
        flag = equipTableService.deleteEquip(equipTable);
        //equipTableService.update(equipTable);
        ipAddressNewService.deleteTable(info.getString("manageIp"));


        return flag;

    }

    /**
     * 添加设备表信息并同步到ip总表
     *
     * @param page
     * @param size
     * @param JSONObject 要添加的equipTable对象和ipAddressNew对象
     * @return
     */
    @PostMapping(value = "/addDetail")
    public boolean addDetail(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, @RequestBody JSONObject e) {
        boolean flag = false;
        EquipTable equipTable = new EquipTable();
        IpAddressNew ipAddressNew = new IpAddressNew();
        JSONObject dataJson = new JSONObject(e);
        JSONObject response = dataJson.getJSONObject("equipTable");
        //获取EquipTable字段

        JSONArray data = response.getJSONArray("equip");
        JSONObject info = data.getJSONObject(0);

        //获取旧的ip地址
        ;
        JSONArray da = response.getJSONArray("ipAddress");
        JSONObject in=da.getJSONObject(0);
        //System.out.println(in.getString("oldIp"));
        System.out.println(info.getString("manageIp"));
       // ipAddressNewService.updateTable(info.getString("manageIp"),in.getString("oldIp"));
       // System.out.println(info.getString("manageIp"));
        System.out.println(in.getString("beginIp"));

        //设备表信息
        equipTable.setManageIp(info.getString("manageIp"));
        equipTable.setId(Integer.parseInt(info.getString("id")));
        equipTable.setEquipAddress(info.getString("equipAddress"));
        equipTable.setEquipModel(info.getString("equipModel"));
        equipTable.setEquipName(info.getString("equipName"));
        equipTable.setEquipNameOther(info.getString("equipNameOther"));
        equipTable.setEquipType(info.getString("equipType"));
        equipTable.setNetLevel(info.getString("netLevel"));
        equipTable.setFactory(info.getString("factory"));

        //ip总表信息
        ipAddressNew.setId(in.getInteger("id"));
        ipAddressNew.setBeginIp(in.getString("beginIp"));
        ipAddressNew.setBeginIpLong(in.getLong("beginIpLong"));
        ipAddressNew.setCustomerName(in.getString("customerName"));
        ipAddressNew.setEndIp(in.getString("endIp"));
        ipAddressNew.setEndIpLong(in.getLong("endIpLong"));
        ipAddressNew.setIpNum(in.getInteger("ipNum"));
        ipAddressNew.setIpType(in.getString("ipType"));
        ipAddressNew.setIsUse(in.getString("isUse"));
        ipAddressNew.setMaskLength(in.getInteger("maskLength"));
        ipAddressNew.setProductNumber(in.getString("productNumber"));
        ipAddressNew.setRemark(in.getString("remark"));
        ipAddressNew.setUnuseDate(in.getDate("unUseDate"));
        ipAddressNew.setUseDate(in.getDate("useDate"));
        ipAddressNew.setWfId(in.getString("wfId"));

        //System.out.println(ipAddressNew.getBeginIp());
       // System.out.println(ipAddressNew.getEndIp());
        flag = equipTableService.addEquip(equipTable);
        //equipTableService.update(equipTable);
        boolean f=ipAddressNewService.addTable(ipAddressNew);
        System.out.println("f:"+f);

        return flag;

    }

    //编辑ip地址表
    @PostMapping(value = "/updateTable")
    public boolean updateTable(IpAddressNew ipAddressNew) {
        boolean flag = false;

        flag=ipAddressNewService.updateTable(ipAddressNew);
        return flag;
    }

    //删除ip地址表中的一条记录
    @PostMapping(value = "/deleteTable")
    public boolean deleteTable(String beginIp) {
        boolean flag ;
        flag=ipAddressNewService.deleteTable(beginIp);
        return flag;
    }

    //添加Ip地址表中的一条记录
    @PostMapping(value = "/addTable")
    public boolean addTable(IpAddressNew ipAddressNew) {
        boolean flag ;
        flag=ipAddressNewService.addTable(ipAddressNew);
        return flag;
    }

    //查询ip利用情况

    @GetMapping(value="/ipUseInfo")
    public List ipUseInfo(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size){


        //各种ip的数量  第一个数是未被使用的  第二个数是已被使用的  第三个是总数
        int CGN[] =new int [3];
        int DIA[]=new int [3];
        int IDC[]=new int [3];
        int IPTV[]=new int [3];
        int PPPOE[]=new int [3];
        int WLAN[]=new int [3];
        int interconnectionAddress[]=new int [3];
        int broadband[]=new int [3];
        int cooperation[]=new int [3];
        int flatAddress[]=new int [3];
        int rent[]=new int [3];
        int deviceManagementAddress[]=new int [3];
        int safeguard[]=new int [3];

        PageHelper.startPage(page, size);
        List<Map<String, Object>> list = ipAddressNewService.ipUseInfo();
        System.out.println("list 的值为：");
        Object []keys=null;
     for(int i=0;i<list.size();i++) {
         //System.out.println(list.get(i));
         //list.get(i).
         keys = list.get(i).values().toArray();
         System.out.println("key数组:" + Arrays.toString(keys));
         switch((String)keys[2]){
             case "CGN":{

                 CGN[2]=CGN[2]+ Integer.parseInt(keys[0].toString());
                 if(keys[1].equals("预留")){
                     CGN[0]=Integer.parseInt(keys[0].toString());
                 }
                 else{
                     CGN[1]=CGN[1]+Integer.parseInt(keys[0].toString());
                 }

             }break;

             case "DIA":{
                 DIA[2]=DIA[2]+Integer.parseInt(keys[0].toString());
                 if(keys[1].equals("预留")){
                     DIA[0]=Integer.parseInt(keys[0].toString());
                 }
                 else{
                     DIA[1]=DIA[1]+Integer.parseInt(keys[0].toString());
                 }
                 break;
             }

             case "IDC":{
                 IDC[2]=IDC[2]+Integer.parseInt(keys[0].toString());
                 if(keys[1].equals("预留")){
                     IDC[0]=Integer.parseInt(keys[0].toString());
                 }
                 else{
                     IDC[1]=IDC[1]+Integer.parseInt(keys[0].toString());
                 }
                 break;
             }

             case "IPTV":{
                 IPTV[2]=IPTV[2]+Integer.parseInt(keys[0].toString());
                 if(keys[1].equals("预留")){
                     IPTV[0]=Integer.parseInt(keys[0].toString());
                 }
                 else{
                     IPTV[1]=IPTV[1]+Integer.parseInt(keys[0].toString());
                 }
                 break;
             }

             case "PPPOE":{
                 PPPOE[2]=PPPOE[2]+Integer.parseInt(keys[0].toString());
                 if(keys[1].equals("预留")){
                     PPPOE[0]=Integer.parseInt(keys[0].toString());
                 }
                 else{
                     PPPOE[1]=PPPOE[1]+Integer.parseInt(keys[0].toString());
                 }
                 break;
             }

             case "WLAN":{
                 WLAN[2]=CGN[2]+Integer.parseInt(keys[0].toString());
                 if(keys[1].equals("预留")){
                     WLAN[0]=Integer.parseInt(keys[0].toString());
                 }
                 else{
                     WLAN[1]=WLAN[1]+Integer.parseInt(keys[0].toString());
                 }
                 break;
             }

             case "互联地址":{
                 interconnectionAddress[2]=interconnectionAddress[2]+Integer.parseInt(keys[0].toString());
                 if(keys[1].equals("预留")){
                     interconnectionAddress[0]=Integer.parseInt(keys[0].toString());
                 }
                 else{
                     interconnectionAddress[1]=interconnectionAddress[1]+Integer.parseInt(keys[0].toString());
                 }
                 break;
             }

             case "合作":{
                 cooperation[2]=cooperation[2]+Integer.parseInt(keys[0].toString());
                 if(keys[1].equals("预留")){
                     cooperation[0]=Integer.parseInt(keys[0].toString());
                 }
                 else{
                     cooperation[1]=cooperation[1]+Integer.parseInt(keys[0].toString());
                 }
                 break;
             }

             case "宽带":{
                 broadband[2]=broadband[2]+Integer.parseInt(keys[0].toString());
                 if(keys[1].equals("预留")){
                     broadband[0]=Integer.parseInt(keys[0].toString());
                 }
                 else{
                     broadband[1]=broadband[1]+Integer.parseInt(keys[0].toString());
                 }
                 break;
             }

             case "扁平化地址":{
                 flatAddress[2]=flatAddress[2]+Integer.parseInt(keys[0].toString());
                 if(keys[1].equals("预留")){
                     flatAddress[0]=Integer.parseInt(keys[0].toString());
                 }
                 else{
                     flatAddress[1]=flatAddress[1]+Integer.parseInt(keys[0].toString());
                 }
                 break;
             }

             case "租用":{

                 rent[2]=rent[2]+Integer.parseInt(keys[0].toString());
                 if(keys[1].equals("预留")){
                     rent[0]=Integer.parseInt(keys[0].toString());
                 }
                 else{
                     rent[1]=rent[1]+Integer.parseInt(keys[0].toString());
                 }

             }break;



             case "设备管理地址":{

                 deviceManagementAddress[2]=deviceManagementAddress[2]+Integer.parseInt(keys[0].toString());
                 if(keys[1].equals("预留")){
                     deviceManagementAddress[0]=Integer.parseInt(keys[0].toString());
                 }
                 else{
                     deviceManagementAddress[1]=deviceManagementAddress[1]+Integer.parseInt(keys[0].toString());
                 }

             }break;



             case "重保":{

                 safeguard[2]=safeguard[2]+Integer.parseInt(keys[0].toString());
                 if(keys[1].equals("预留")){
                     safeguard[0]=Integer.parseInt(keys[0].toString());
                 }
                 else{
                     safeguard[1]=safeguard[1]+Integer.parseInt(keys[0].toString());
                 }

             }break;



         }

     }
        //将小数格式数据转换成百分比格式数据
        DecimalFormat df = new DecimalFormat("0.00%");

        //各种ip的数量  第一个数是未被使用的  第二个数是已被使用的  第三个是总数
        IpStatistics ipStatistics1 = new IpStatistics("CGN",CGN[0],CGN[1],CGN[2],df.format((float)CGN[1]/(float)CGN[2]));
        IpStatistics ipStatistics2 = new IpStatistics("DIA",DIA[0],DIA[1],DIA[2],df.format((float)DIA[1]/(float)DIA[2]));
        IpStatistics ipStatistics3 = new IpStatistics("IDC",IDC[0],IDC[1],IDC[2],df.format((float)IDC[1]/(float)IDC[2]));
        IpStatistics ipStatistics4 = new IpStatistics("IPTV",IPTV[0],IPTV[1],IPTV[2],df.format((float)IPTV[1]/(float)IPTV[2]));
        IpStatistics ipStatistics5 = new IpStatistics("WLAN",WLAN[0],WLAN[1],WLAN[2],df.format((float)WLAN[1]/(float)WLAN[2]));
        IpStatistics ipStatistics6 = new IpStatistics("interconnectionAddress",interconnectionAddress[0],interconnectionAddress[1],interconnectionAddress[2],df.format((float)interconnectionAddress[1]/(float)interconnectionAddress[2]));
        IpStatistics ipStatistics7 = new IpStatistics("cooperation",cooperation[0],cooperation[1],cooperation[2],df.format((float)cooperation[1]/(float)cooperation[2]));
        IpStatistics ipStatistics8 = new IpStatistics("broadband",broadband[0],broadband[1],broadband[2],df.format((float)broadband[1]/(float)broadband[2]));
        IpStatistics ipStatistics9 = new IpStatistics("flatAddress",flatAddress[0],flatAddress[1],flatAddress[2],df.format((float)flatAddress[1]/(float)flatAddress[2]));
        IpStatistics ipStatistics10 = new IpStatistics("rent",rent[0],rent[1],rent[2],df.format((float)rent[1]/rent[2]));
        IpStatistics ipStatistics11 = new IpStatistics("deviceManagementAddress",deviceManagementAddress[0],deviceManagementAddress[1],deviceManagementAddress[2],df.format((float)deviceManagementAddress[1]/(float)deviceManagementAddress[2]));
        IpStatistics ipStatistics12 = new IpStatistics("safeguard", safeguard[0], safeguard[1], safeguard[2], df.format((float)safeguard[1]/ (float)safeguard[2]));
        //IpStatistics ipStatistics13 = new IpStatistics("CGN",CGN[0],CGN[1],CGN[2],CGN[1]/CGN[2]);

        System.out.println("剩余："+CGN[0]+" "+"已用："+CGN[1]+" "+"总数："+CGN[2]);
        System.out.println("剩余："+DIA[0]+" "+"已用："+DIA[1]+" "+"总数："+DIA[2]);


        System.out.println();
        List<IpStatistics> li = new ArrayList <IpStatistics> ();
        li.add(ipStatistics1);
        li.add(ipStatistics2);
        li.add(ipStatistics3);
        li.add(ipStatistics4);
        li.add(ipStatistics5);
        li.add(ipStatistics6);
        li.add(ipStatistics7);
        li.add(ipStatistics8);
        li.add(ipStatistics9);
        li.add(ipStatistics10);
        li.add(ipStatistics11);
        li.add(ipStatistics12);
        System.out.println(li.get(0).getPrecent());
        //PageInfo pageInfo = new PageInfo(list);
        return li;
    }
}
