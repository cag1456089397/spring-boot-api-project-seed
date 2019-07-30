package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.CustomerTable;
import com.company.project.model.RecustomerTable;
import com.company.project.service.CustomerTableService;
import com.company.project.service.RecustomerTableService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/02/21.
 */
@RestController
@RequestMapping("/recustomer/table")
public class RecustomerTableController {
    @Resource
    private RecustomerTableService recustomerTableService;
    @Resource
    private CustomerTableService customerTableService;

    @PostMapping("/add")
    public Result add(RecustomerTable recustomerTable) {
        recustomerTableService.save(recustomerTable);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        recustomerTableService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(RecustomerTable recustomerTable) {
        recustomerTableService.update(recustomerTable);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        RecustomerTable recustomerTable = recustomerTableService.findById(id);
        return ResultGenerator.genSuccessResult(recustomerTable);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<RecustomerTable> list = recustomerTableService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 回收客户台账导出
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export2(HttpServletResponse response) throws IOException {
        List<RecustomerTable> customerTableList;
        customerTableList = recustomerTableService.selectUsers();
        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("获取excel测试表格");

        HSSFRow row = null;


        /**
         * 表头
         */
        row = sheet.createRow(0);
        row.setHeight((short) (22.50 * 20));//设置行高
        String[] br;
        br = new String[]{"回收时间", "回收单号", "省或市调单号", "电路编号", "互联地址", "用户IP地址", "IP地址个数", "接入速率", "客户名称", "业务类型",
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
            RecustomerTable customerTable = customerTableList.get(i);
            String rebackTime, openDate, upDate;
            if (customerTable.getRebackTime() != null) {
                rebackTime = sdf.format(customerTable.getRebackTime());
            } else {
                rebackTime = "";
            }
            if (customerTable.getOpenDate() != null) {
                openDate = sdf.format(customerTable.getOpenDate());
            } else {
                openDate = "";
            }
            if (customerTable.getUpDate()!=null) {
                upDate = sdf.format(customerTable.getUpDate());
            } else {
                upDate="";
            }

            String[] br1;
            br1 = new String[]{rebackTime, customerTable.getRebackWfId(), customerTable.getWfId(), customerTable.getElectricId(), customerTable.getConnectIp(), customerTable.getUserIp(),
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
        response.setHeader("Content-disposition", "attachment;filename=reuser.xls");//默认Excel名称
        wb.write(os);
        os.flush();
        os.close();
    }

    /**
     * 回收接口，将客户台账的记录删除，并将其写入回收表中
     *
     * @param page
     * @param size
     * @return
     */
    @PostMapping({"/rebackUser"})
    public Result rebackUser(@RequestParam(defaultValue = "0") Integer page,
                             @RequestParam(defaultValue = "0") Integer size,
                             RecustomerTable recustomerTable) {

        PageHelper.startPage(page, size);
        Integer id = recustomerTable.getId();
        Date rebackDate = new Date();
        recustomerTable.setRebackTime(rebackDate);
        this.recustomerTableService.insertByRebackUser(recustomerTable);
        this.customerTableService.deleteById(id);


        //返回表单，也可以返回任何值
        return ResultGenerator.genSuccessResult();
    }
}
