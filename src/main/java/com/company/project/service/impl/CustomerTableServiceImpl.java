package com.company.project.service.impl;

import com.company.project.common.MyException;
import com.company.project.dao.CustomerTableMapper;
import com.company.project.model.CustomerTable;
import com.company.project.model.IpAddressNew;
import com.company.project.service.CustomerTableService;
import com.company.project.core.AbstractService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.company.project.service.impl.IpAddressNewServiceImpl.getStartIp;


/**
 * Created by CodeGenerator on 2019/02/19.
 */
@Service
@Transactional
public class CustomerTableServiceImpl extends AbstractService<CustomerTable> implements CustomerTableService {
    @Resource
    private CustomerTableMapper customerTableMapper;

    public List<CustomerTable> selectUsers() {
        return customerTableMapper.selectUsers();
    }


    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public boolean batchImport1(String fileName, MultipartFile file) throws Exception {
        boolean notNull = false;
        List<CustomerTable> userList = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new MyException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            notNull = true;
        }
        CustomerTable user;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {//r = 2 表示从第三行开始循环 如果你的第三行开始是数据
            Row row = sheet.getRow(r);//通过sheet表单对象得到 行对象
            if (row == null) {
                continue;
            }

            //sheet.getLastRowNum() 的值是 10，所以Excel表中的数据至少是10条；不然报错 NullPointerException

            user = new CustomerTable();

/*
            if (row.getCell(0).getCellType() != 1) {//循环时，得到每一行的单元格进行判断

                System.out.println("导入失败(第" + (r + 1) + "行,省或市调单号请设为文本格式)");
                continue;
                //throw new MyException("导入失败(第" + (r + 1) + "行,用户名请设为文本格式)");

            }*/
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            String wfId = row.getCell(0).getStringCellValue();//得到每一行第一个单元格的值
            if (wfId == null || wfId.isEmpty()) {//判断是否为空
                throw new MyException("导入失败(第" + (r + 1) + "行,,省或市调单号未填写)");
            }
            String electricId = row.getCell(1).getStringCellValue();
            if (electricId == null || electricId.isEmpty()) {
                throw new MyException("导入失败(第" + (r + 1) + "行,电路编号未填写)");
            }
            String connectIp = row.getCell(2).getStringCellValue();

            String userIp = row.getCell(3).getStringCellValue();
            Integer ipNum;
            if (row.getCell(4) != null) {
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                if (row.getCell(4).getStringCellValue().equals("") || row.getCell(4).getStringCellValue() == null) {
                    ipNum = null;
                } else {
                    ipNum = Integer.parseInt(row.getCell(4).getStringCellValue());
                }
            } else {
                ipNum = null;
            }

            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            Integer insertSpeed;
            if (row.getCell(5).getStringCellValue().equals("") || row.getCell(5).getStringCellValue() == null) {
                insertSpeed = null;
            } else {
                insertSpeed = Integer.parseInt(row.getCell(5).getStringCellValue());
            }
            String customer = row.getCell(6).getStringCellValue();
            String businessType = row.getCell(7).getStringCellValue();
            String produceNumber;
            if (row.getCell(8) == null) {
                produceNumber = null;
            } else {
                produceNumber = row.getCell(8).getStringCellValue();
            }
            String networkName;
            if (row.getCell(9) != null) {
                networkName = row.getCell(9).getStringCellValue();
            } else {
                networkName = null;
            }
            String networkNameOther;
            if (row.getCell(10) != null) {
                networkNameOther = row.getCell(10).getStringCellValue();
            } else {
                networkNameOther = null;
            }
            String networkIp;
            if (row.getCell(11) != null) {
                row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                networkIp = row.getCell(11).getStringCellValue();
            } else {
                networkIp = "";
            }

            String networkPort = row.getCell(12).getStringCellValue();
            String insertName = row.getCell(13).getStringCellValue();
            String insertIp = row.getCell(14).getStringCellValue();
            String insertNameOther = row.getCell(15).getStringCellValue();
            String insertPort = row.getCell(16).getStringCellValue();
            row.getCell(17).setCellType(Cell.CELL_TYPE_STRING);
            String vlanId;
            if (row.getCell(17).getStringCellValue().equals("") || row.getCell(17).getStringCellValue() == null) {
                vlanId = null;
            } else {
                vlanId = row.getCell(17).getStringCellValue();
            }

            row.getCell(18).setCellType(Cell.CELL_TYPE_STRING);
            String linkPeople = row.getCell(18).getStringCellValue();
            String linkPhone;
            if (row.getCell(19) != null) {
                row.getCell(19).setCellType(Cell.CELL_TYPE_STRING);
                linkPhone = row.getCell(19).getStringCellValue();
            } else {
                linkPhone = "";
            }
            String customerAddress;
            if (row.getCell(20) != null) {
                row.getCell(20).setCellType(Cell.CELL_TYPE_STRING);
                customerAddress = row.getCell(20).getStringCellValue();
            } else {
                customerAddress = "";
            }

            Date openDate;
            System.out.println(row.getCell(21));
            if (row.getCell(21) != null) {
                if (row.getCell(21).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    openDate = row.getCell(21).getDateCellValue();
                } else {
                    openDate = null;
                }
            } else {
                openDate = null;
            }
            System.out.println(row.getCell(22));

            Date upDate;
            if (row.getCell(22) != null) {
                if (row.getCell(22).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    upDate = row.getCell(22).getDateCellValue();
                } else {
                    upDate = null;
                }
            } else {
                upDate = null;
            }

            row.getCell(23).setCellType(Cell.CELL_TYPE_STRING);
            String sameBand = row.getCell(23).getStringCellValue();
            row.getCell(24).setCellType(Cell.CELL_TYPE_STRING);
            String remark = row.getCell(24).getStringCellValue();
            String is80;
            if (row.getCell(25) != null) {
                is80 = row.getCell(25).getStringCellValue();
            } else {
                is80 = "否";
            }


            user.setWfId(wfId);
            user.setElectricId(electricId);
            user.setConnectIp(connectIp);
            user.setUserIp(userIp);
            user.setIpNum(ipNum);
            user.setInsertSpeed(insertSpeed);
            user.setCustomer(customer);
            user.setBusinessType(businessType);
            user.setProduceNumber(produceNumber);
            user.setNetworkName(networkName);
            user.setNetworkNameOther(networkNameOther);
            user.setNetworkIp(networkIp);
            user.setNetworkPort(networkPort);
            user.setInsertName(insertName);
            user.setInsertIp(insertIp);
            user.setInsertNameOther(insertNameOther);
            user.setInsertPort(insertPort);
            user.setVlanId(vlanId);
            user.setLinkPeople(linkPeople);
            user.setLinkPhone(linkPhone);
            user.setCustomerAddress(customerAddress);
            user.setOpenDate(openDate);
            user.setUpDate(upDate);
            user.setSameBand(sameBand);
            user.setRemark(remark);
            user.setIs80(is80);


            userList.add(user);
            System.out.println(userList.size());
        }
        int i = 1;
        List list = new ArrayList();
        for (CustomerTable userResord : userList) {
            String wfId = userResord.getWfId();
            String electricId = userResord.getElectricId();
            //String insertPort = userResord.getInsertPort();
            int cnt = customerTableMapper.selectByWfIdAndElectricId(wfId, electricId);
            int id;



            if (cnt == 0) {
                customerTableMapper.addUser(userResord);
                System.out.println(" 插入 " + userResord);
            } else {
                id = customerTableMapper.selectByTwo(wfId, electricId).get(0).getId();
                userResord.setId(id);
                customerTableMapper.updateUserByName(userResord);

                list.add("第" + i + "数据有重复");
                System.out.println(" 更新 " + userResord);
            }
            System.out.println(i);
            i++;
        }
        System.out.println(list);
        return notNull;
    }

    /**
     * 详情里的查询
     *
     * @param customerTable
     * @return
     */
    public List<CustomerTable> selectUsersByAll(CustomerTable customerTable) {
        if (customerTable.getUserIp() != null) {

            List<CustomerTable> customerTableList = new ArrayList<>();

            for (int maskLength = 32; maskLength > 24; maskLength--) {
                String str = customerTable.getUserIp() + "/" + maskLength;
                String str1 = getStartIp(str);
                customerTable.setUserIp(str1);//写数据进post
                int i = customerTableMapper.selectUsersByAll(customerTable).size();
                while (i != 0) {
                    CustomerTable customerTable1 = this.customerTableMapper.selectUsersByAll(customerTable).get(i - 1);
                    customerTableList.add(customerTable1);
                    i--;

                }


            }
            return customerTableList;


        } else {
            return this.customerTableMapper.selectUsersByAll(customerTable);
        }


    }

    public List<CustomerTable> selectUsersByAutoPut(CustomerTable customerTable) {
        return this.customerTableMapper.selectUsersByAutoPut(customerTable);
    }

	//------------------------------------------------------------------------------------------------
	  public List queryByUserIp(String userIp){
        return this.customerTableMapper.queryByUserIp(userIp);

    }

    public boolean addCustomerTable(CustomerTable customerTable){
        return this.customerTableMapper.addCustomerTable(customerTable);
    }

    public boolean updateCustomerTable(CustomerTable customerTable){
        return this.customerTableMapper.updateCustomerTable(customerTable);
    }
    public boolean deleteCustomerTable(CustomerTable customerTable){
        return this.customerTableMapper.deleteCustomerTable(customerTable);
    }

    public List queryCustomerTable(CustomerTable customerTable){
        return  this.customerTableMapper.queryCustomerTable(customerTable);
    }


}


