package com.company.project.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.CustomerTable;
import com.company.project.model.EqOrder;
import com.company.project.model.HistoryCustomerTable;
import com.company.project.service.CustomerTableService;
import com.company.project.service.EqOrderService;
import com.company.project.service.HistoryCustomerTableService;
import com.company.project.util.TelnetOperator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.company.project.service.impl.IpAddressNewServiceImpl.*;

/**
 * Created by CodeGenerator on 2019/02/19.
 */
@RestController
@RequestMapping("/customer/table")
public class CustomerTableController {
    @Resource
    private CustomerTableService customerTableService;
    @Resource
    private HistoryCustomerTableService historyCustomerTableService;
    @Resource
    private EqOrderService eqOrderService;


    @PostMapping("/add")
    public Result add(CustomerTable customerTable) {
        customerTableService.save(customerTable);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        customerTableService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(CustomerTable customerTable) {
        customerTableService.update(customerTable);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        CustomerTable customerTable = customerTableService.findById(id);
        return ResultGenerator.genSuccessResult(customerTable);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CustomerTable> list = customerTableService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 详情查询
     *
     * @param page
     * @param size
     * @param customerTable
     * @return
     */
    @PostMapping("/selectUsersByAll")
    public Result selectUsersByAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                   CustomerTable customerTable) {
        PageHelper.startPage(page, size);
        List<CustomerTable> list = customerTableService.selectUsersByAll(customerTable);

        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 回单模板
     *
     * @param customerTable
     * @return
     */
    @PostMapping("/answer")
    public Result answer(CustomerTable customerTable) {
        int i = 0;
        int ipNum = customerTable.getIpNum();
        while (ipNum != 0) {
            ipNum = ipNum / 2;
            i++;

        }
        int maskLength = 33 - i;
        String maskCode = getNetMask(maskLength);
        String arr[] = customerTable.getUserIp().split("-");
        String str = arr[0] + "/" + maskLength;
        String endIp = getEndIp(str);
        Long endIpLong = ipToLong(endIp);
        Long netWorkLong = endIpLong - 1;
        String netWork = longToIp(netWorkLong);
        String answer = "数据资源分配如下:\n" +
                "接入端口：" + customerTable.getInsertNameOther() + "——" + customerTable.getInsertPort() + "\n" +
                "网关端口：" + customerTable.getNetworkNameOther() + "——" + customerTable.getNetworkPort() + "\n" +
                "描述：" + "," + customerTable.getBusinessType() + "," + customerTable.getInsertSpeed() + "M:1," + customerTable.getWfId() + "," + customerTable.getProduceNumber() + "\n" +
                "IP(" + customerTable.getIpNum() + "):" + customerTable.getUserIp() + "\n" +
                "网关：" + netWork + "\n" +
                "掩码：" + maskCode + "\n" +
                "DNS：116.116.116.116或120.80.88.88\n" +
                "带宽：" + customerTable.getInsertSpeed() + "M\n" +
                "VLAN：" + customerTable.getVlanId() + "\n" +
                "是否开通80、8080、443端口：" + customerTable.getIs80() + "\n" +
                "电路编号：" + customerTable.getElectricId();
        System.out.println(answer);
        return ResultGenerator.genSuccessResult(answer);
    }


    /**
     * 客户台账导入
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
            a = customerTableService.batchImport1(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:index";
    }

    /**
     * 客户台账导出
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export1(HttpServletResponse response) throws IOException {
        List<CustomerTable> customerTableList = customerTableService.selectUsers();

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("获取excel测试表格");

        HSSFRow row = null;


        /**
         * 表头
         */
        row = sheet.createRow(0);
        row.setHeight((short) (22.50 * 20));//设置行高
        String[] br;
        br = new String[]{"省或市调单号", "电路编号", "互联地址", "用户IP地址", "IP地址个数", "接入速率", "客户名称", "业务类型",
                "产品号码", "网关设备名称", "网关别名", "网关设备管理IP地址", "网关端口", "接入设备名称", "接入设备管理IP地址",
                "接入设备别名", "接入端口", "VLAN", "客户", "客户联系方式", "客户地址", "开通日期", "更新日期", "带宽IP一致情况", "备注",
                "是否开通80,8080,443"};
        for (int i = 0; i < br.length; i++) {
            row.createCell(i).setCellValue(br[i]);//单元格设值
        }

        /**
         * 数据
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        for (int i = 0; i < customerTableList.size(); i++) {
            row = sheet.createRow(i + 1);
            CustomerTable customerTable = customerTableList.get(i);
            String openDate, upDate;
            if (customerTable.getOpenDate() != null) {
                openDate = sdf.format(customerTable.getOpenDate());
            } else {
                openDate = "";
            }
            if (customerTable.getUpDate() != null) {
                upDate = sdf.format(customerTable.getUpDate());
            } else {
                upDate = "";
            }
            String[] br1;
            br1 = new String[]{customerTable.getWfId(), customerTable.getElectricId(), customerTable.getConnectIp(), customerTable.getUserIp(),
                    String.valueOf(customerTable.getIpNum()), String.valueOf(customerTable.getInsertSpeed()), customerTable.getCustomer(),
                    customerTable.getBusinessType(), customerTable.getProduceNumber(), customerTable.getNetworkName(), customerTable.getNetworkNameOther(),
                    customerTable.getNetworkIp(), customerTable.getNetworkPort(), customerTable.getInsertName(), customerTable.getInsertIp(),
                    customerTable.getInsertNameOther(), customerTable.getInsertPort(), customerTable.getVlanId(), customerTable.getLinkPeople(),
                    customerTable.getLinkPhone(), customerTable.getCustomerAddress(), openDate, upDate,
                    customerTable.getSameBand(), customerTable.getRemark(), customerTable.getIs80()};
            for (int j = 0; j < br1.length; j++) {
                if (br1[j] != null || br1[j] != "") {
                    row.createCell(j).setCellValue(br1[j]);
                }
            }
        }
        sheet.setDefaultRowHeight((short) (16.5 * 20));
        //列宽自适应
      /*  for (int i = 0; i <= 13; i++) {
            sheet.autoSizeColumn(i);
        }*/

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename=user.xls");//默认Excel名称
        wb.write(os);
        os.flush();
        os.close();


    }

    @PostMapping({"/selectUsersByAutoPut"})
    public Result selectIpAddressNewByBeginIp(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                              CustomerTable customerTable) {
        PageHelper.startPage(page, size);

        List<CustomerTable> list = this.customerTableService.selectUsersByAutoPut(customerTable);

        PageInfo pageInfo = new PageInfo(list);
        //返回表单，也可以返回任何值
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping({"/getZhizhenPic"})
    public Result getZhizhenPic(String ip, String interFace) {
        try {
            System.out.println("start");
            String[] args1=new String[]{"python","/home/guokai/taizhangxitong/pythonfile/traffic_dg.py",ip,interFace};
            /* String[] args1 = new String[]{"python", "D:\\traffic_dg.py", ip, interFace};*/
            Process pr = Runtime.getRuntime().exec(args1);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    pr.getInputStream(),"gb2312"));
            String line;
            String line1=null;
            while ((line = in.readLine()) != null) {
                line1=line1+line;
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
            return ResultGenerator.genSuccessResult(line1);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("失败");
        }

    }


    @PostMapping({"/getPortState"})
    public Result getPortState(String equipType, String orderType, String ip, String interFace) {
        if (this.eqOrderService.selectByTwo(equipType, orderType) != null) {
            List<EqOrder> list = this.eqOrderService.selectByTwo(equipType, orderType);
            TelnetOperator telnet;
            if (list.get(0).getFactoryEq().equals("烽火") || list.get(0).getFactoryEq().equals("思科") || list.get(0).getFactoryEq().equals("贝尔")) {
                telnet = new TelnetOperator("VT220", "#");
            } else {
                telnet = new TelnetOperator("VT220", ">");    //Windows,用VT220,否则会乱码
            }

            telnet.login(ip, 23, list.get(0).getLoginEq(), list.get(0).getPassEq());

            String command;
            if (orderType.equals("查询光功率")) {
                if (list.get(0).getFactoryEq().equals("华为")) {
                    command = list.get(0).getOrderEq() + interFace + " verbose";
                } else if (list.get(0).getFactoryEq().equals("思科")) {
                    command = list.get(0).getOrderEq() + interFace + " phy";
                } else {
                    command = list.get(0).getOrderEq() + interFace;
                }
            } else {
                command = list.get(0).getOrderEq() + interFace;
            }

            String rs = telnet.sendCommand(command);

            try {
                rs = new String(rs.getBytes("ISO-8859-1"), "GBK");    //转一下编码
                System.out.println(rs);
                telnet.distinct();
                return ResultGenerator.genSuccessResult(rs);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                telnet.distinct();
                return ResultGenerator.genFailResult(ip + "\n脚本执行失败,请修改脚本");
            }
        } else {
            return ResultGenerator.genFailResult(ip + "\n未找到脚本");
        }


    }



    @PostMapping("/addCustomerTable")
    public boolean addCustomerTable(@RequestBody JSONObject jsonObject){
        boolean flag;
        CustomerTable customerTable = getCustomerTable(jsonObject);
        System.out.println("customer:"+customerTable.getCustomer());
        System.out.println("userIp:"+customerTable.getUserIp());
        System.out.println("update:"+customerTable.getUpDate());
       // System.out.println(customerTable.getUpDate().getTime());
        flag= customerTableService.addCustomerTable(customerTable);

        return  flag;
    }

    @PostMapping("/updateCustomerTable")
    public boolean updateCustomerTable(@RequestBody JSONObject jsonObject){
        boolean flag;
        CustomerTable customerTable = getCustomerTable(jsonObject);
        System.out.println("customer"+customerTable.getCustomer());
        System.out.println("userIp"+customerTable.getUserIp());
        flag= customerTableService.updateCustomerTable(customerTable);

        return  flag;
    }

    @PostMapping("/deleteCustomerTable")
    public boolean deleteCustomerTable(@RequestBody JSONObject jsonObject){
        boolean flag=false;
        CustomerTable customerTable = getCustomerTable(jsonObject);
        System.out.println("customer"+customerTable.getCustomer());
        System.out.println("userIp"+customerTable.getUserIp());
        flag= customerTableService.deleteCustomerTable(customerTable);

        //获取historyCustomerTableService
        HistoryCustomerTable historyCustomerTable = getHistoryCustomerTable(jsonObject);
        historyCustomerTableService.add(historyCustomerTable);

        return  flag;
    }

    @PostMapping("/queryCustomerTable")
    public Result queryCustomerTable(@RequestBody JSONObject jsonObject){

        CustomerTable customerTable = getCustomerTable(jsonObject);
        System.out.println("customer"+customerTable.getCustomer());
        System.out.println("userIp"+customerTable.getUserIp());
        List<CustomerTable> list= customerTableService.queryCustomerTable(customerTable);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);

    }


    public CustomerTable getCustomerTable(JSONObject jsonObject){
        CustomerTable customerTable = new CustomerTable();
        //获取EquipTable字段
        JSONObject dataJson = new JSONObject(jsonObject);
        JSONObject response = dataJson.getJSONObject("customerTable");
        JSONArray data = response.getJSONArray("customer");
        JSONObject info = data.getJSONObject(0);
        customerTable.setBusinessType(info.getString("businessType"));
        customerTable.setConnectIp(info.getString("connectIp"));
        customerTable.setCustomer(info.getString("customer"));
        customerTable.setCustomerAddress(info.getString("customerAddress"));
        customerTable.setElectricId(info.getString("electricId"));
        customerTable.setInsertIp(info.getString("insertIp"));
        customerTable.setInsertName(info.getString("insertName"));
        customerTable.setInsertNameOther(info.getString("insertNameOther"));
        customerTable.setInsertSpeed(info.getInteger("insertSpeed"));
        customerTable.setId(info.getInteger("id"));
        customerTable.setIpNum(info.getInteger("ipNum"));
        customerTable.setInsertPort(info.getString("insertPort"));
        customerTable.setIs80(info.getString("is80"));
        customerTable.setLinkPeople(info.getString("linkPeople"));
        customerTable.setLinkPhone(info.getString("linkPhone"));
        customerTable.setNetworkIp(info.getString("networkIp"));
        customerTable.setNetworkName(info.getString("networkName"));
        customerTable.setNetworkNameOther(info.getString("networkNameOther"));
        customerTable.setNetworkPort(info.getString("networkPort"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(info.getString("openDate"));
        Date openDate = null;
        Date upDate = null;
        try {
            openDate= sdf.parse(info.getString("openDate"));
            upDate = sdf.parse(info.getString("upDate"));
        }catch (Exception e){}
        customerTable.setOpenDate(openDate);
        customerTable.setProduceNumber(info.getString("produceNumber"));
        customerTable.setRemark(info.getString("remark"));
        customerTable.setSameBand(info.getString("sameBand"));
        customerTable.setUserIp(info.getString("userIp"));
        customerTable.setUpDate(upDate);
        customerTable.setVlanId(info.getString("vlanId"));
        customerTable.setWfId(info.getString("wfId"));
        return  customerTable;


    }

    public HistoryCustomerTable getHistoryCustomerTable(JSONObject jsonObject){
        HistoryCustomerTable historyCustomerTable = new HistoryCustomerTable();
        //获取EquipTable字段
        JSONObject dataJson = new JSONObject(jsonObject);
        JSONObject response = dataJson.getJSONObject("customerTable");
        JSONArray data = response.getJSONArray("customer");
        JSONObject info = data.getJSONObject(0);
        historyCustomerTable.setBusinessType(info.getString("businessType"));
        historyCustomerTable.setConnectIp(info.getString("connectIp"));
        historyCustomerTable.setCustomer(info.getString("customer"));
        historyCustomerTable.setCustomerAddress(info.getString("customerAddress"));
        historyCustomerTable.setElectricId(info.getString("electricId"));
        historyCustomerTable.setInsertIp(info.getString("insertIp"));
        historyCustomerTable.setInsertName(info.getString("insertName"));
        historyCustomerTable.setInsertNameOther(info.getString("insertNameOther"));
        historyCustomerTable.setInsertSpeed(info.getInteger("insertSpeed"));
        historyCustomerTable.setId(info.getInteger("id"));
        historyCustomerTable.setIpNum(info.getInteger("ipNum"));
        historyCustomerTable.setInsertPort(info.getString("insertPort"));
        historyCustomerTable.setIs80(info.getString("is80"));
        historyCustomerTable.setLinkPeople(info.getString("linkPeople"));
        historyCustomerTable.setLinkPhone(info.getString("linkPhone"));
        historyCustomerTable.setNetworkIp(info.getString("networkIp"));
        historyCustomerTable.setNetworkName(info.getString("networkName"));
        historyCustomerTable.setNetworkNameOther(info.getString("networkNameOther"));
        historyCustomerTable.setNetworkPort(info.getString("networkPort"));
        historyCustomerTable.setOpenDate(info.getDate("openDate"));
        historyCustomerTable.setProduceNumber(info.getString("productNumber"));
        historyCustomerTable.setRemark(info.getString("remark"));
        historyCustomerTable.setSameBand(info.getString("sameBand"));
        historyCustomerTable.setUserIp(info.getString("userIp"));
        historyCustomerTable.setUpDate(info.getDate("upDate"));
        historyCustomerTable.setVlanId(info.getString("vlanId"));
        historyCustomerTable.setWfId(info.getString("wfId"));
        historyCustomerTable.setDeleteDate(new Date());
        return  historyCustomerTable;


    }

}
