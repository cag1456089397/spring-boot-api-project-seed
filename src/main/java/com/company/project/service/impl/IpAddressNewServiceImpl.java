package com.company.project.service.impl;

import com.company.project.common.MyException;
import com.company.project.dao.IpAddressNewMapper;


import com.company.project.model.IpAddressNew;
import com.company.project.service.IpAddressNewService;
import com.company.project.core.AbstractService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2019/01/28.
 */
@Service
@Transactional
public class IpAddressNewServiceImpl extends AbstractService<IpAddressNew> implements IpAddressNewService {
    @Resource
    private IpAddressNewMapper ipAddressNewMapper;

    /**
     * ip表打印
     */
    public List<IpAddressNew> selectIpAddressNew() {

        return this.ipAddressNewMapper.selectIpAddressNew();
    }

    /**
     * 得到起始地址和结束地址的LONG型
     *
     * @return
     */
    public int selectIpAddressNew1() {

        List<IpAddressNew> ipAddressNewList = ipAddressNewMapper.selectIpAddressNew();
        String begin = ipAddressNewList.get(0).getBeginIp();
        if(begin.substring(begin.length()-2,begin.length()).equals("48")){

        }
        else {
            for (IpAddressNew ipAddressNewResord : ipAddressNewList) {
                ipAddressNewResord.setBeginIpLong(ipToLong(ipAddressNewResord.getBeginIp()));

                ipAddressNewResord.setEndIpLong(ipToLong(ipAddressNewResord.getEndIp()));
                this.updateIpAddressNewByFindAll(ipAddressNewResord);
            }
        }
        return 1;
}

    /*
     * 起始IP地址查询
     * */
    public List<IpAddressNew> selectIpAddressNewByBeginIp(String beginIp) {
        return this.ipAddressNewMapper.selectIpAddressNewByBeginIp(beginIp);
    }

    /**
     * ip数目查询
     */
    public List<IpAddressNew> selectIpAddressNewByIpNum(int ipNum) {
        return this.ipAddressNewMapper.selectIpAddressNewByIpNum(ipNum);
    }

    /**
     * id查询
     */
    public List<IpAddressNew> selectIpAddressNewById(int id) {
        return this.ipAddressNewMapper.selectIpAddressNewById(id);
    }

    /*
     * 结束IP地址查询
     * */
    public List<IpAddressNew> selectIpAddressNewByEndIp(String endIp) {
        return this.ipAddressNewMapper.selectIpAddressNewByEndIp(endIp);
    }


    /*
     * 删除
     * */
    public int deleteIpAddressNewByFindAll(IpAddressNew ipAddressNew) {
        return this.ipAddressNewMapper.deleteIpAddressNewByFindAll(ipAddressNew);
    }

    /*
     * 插入
     * */
    public int insertIpAddressNewByFindAll(IpAddressNew ipAddressNew) {
        return this.ipAddressNewMapper.insertIpAddressNewByFindAll(ipAddressNew);
    }

    /*
     * 修改
     * */
    public int updateIpAddressNewByFindAll(IpAddressNew ipAddressNew) {
        return this.ipAddressNewMapper.updateIpAddressNewByFindAll(ipAddressNew);
    }

    /**
     * @param ipAddressNew
     * @return
     */
    public List<IpAddressNew> selectIpAddressNewByLookFor(IpAddressNew ipAddressNew) {
        if (ipAddressNew.getBeginIp() != null) {
            if (ipAddressNew.getMaskLength() != null) {
                String str = ipAddressNew.getBeginIp() + "/" + ipAddressNew.getMaskLength();//从post得到数据
                String str1 = getStartIp(str);
                String str2 = getEndIp(str);
                ipAddressNew.setBeginIp(str1);//写数据进post
                ipAddressNew.setEndIp(str2);
                return this.ipAddressNewMapper.selectIpAddressNewByLookFor(ipAddressNew);
            } else {
                List<IpAddressNew> ipAddressNewList = new ArrayList<>();

                for (int maskLength = 32; maskLength > 24; maskLength--) {
                    String str = ipAddressNew.getBeginIp() + "/" + maskLength;
                    String str1 = getStartIp(str);
                    String str2 = getEndIp(str);
                    ipAddressNew.setBeginIp(str1);//写数据进post
                    ipAddressNew.setEndIp(str2);
                    int i = ipAddressNewMapper.selectIpAddressNewByLookFor(ipAddressNew).size();
                    while (i != 0) {
                        IpAddressNew ipAddressNew1 = this.ipAddressNewMapper.selectIpAddressNewByLookFor(ipAddressNew).get(i - 1);
                        ipAddressNewList.add(ipAddressNew1);
                        i--;

                    }


                }
                return ipAddressNewList;
            }

        } else {
            return this.ipAddressNewMapper.selectIpAddressNewByLookFor(ipAddressNew);
        }

    }

    /**
     * @param ipAddressNew
     * @return
     */
    public int updateIpAddressNewByErData(IpAddressNew ipAddressNew) {
        if (ipAddressNew.getMaskLength() != null) {
            Integer maskLength = ipAddressNew.getMaskLength();
            //定义掩码范围0<mask<=32，强制转换
            if (0 < maskLength && maskLength <= 32) {

                int ipNum = 1 << (32 - maskLength);

                ipAddressNew.setIpNum(ipNum);//关联ipNum

                return testTogether(ipAddressNew);
            } else {
                return 0;
            }
        } else {
            //定义IP数目为2的倍数
            if (ipAddressNew.getIpNum() != null) {
                int ipNum = ipAddressNew.getIpNum();
                if ((ipNum % 2 == 0 || ipNum == 1) && ipNum > 0) {
                    //关联子网掩码
                    int i = 0;
                    while (ipNum != 0) {
                        ipNum = ipNum / 2;
                        i++;

                    }
                    int maskLength = 33 - i;
                    ipAddressNew.setMaskLength(maskLength);

                    return testTogether(ipAddressNew);
                } else {
                    return 0;
                }
            } else {
                return testTogether(ipAddressNew);
            }
        }


    }

    private int testTogether(IpAddressNew ipAddressNew) {
        IpAddressNew ipAddressNew1 = selectIpAddressNewById(ipAddressNew.getId()).get(0);
        this.ipAddressNewMapper.updateIpAddressNewByFindAll(ipAddressNew);
        IpAddressNew ipAddressNew2 = selectIpAddressNewByBeginIp(ipAddressNew1.getBeginIp()).get(0);
        if (ipAddressNew2.getIsUse().equals("预留")) {
            autoTogetherIp(ipAddressNew2);
        }
        return 1;
    }


    /*
     *业务开通返回值
     * */
    public List<IpAddressNew> selectIpAddressNewByBuOpen(IpAddressNew ipAddressNew) {
        if (ipAddressNew.getBeginIp() != null) {
            long beginIpLong = ipToLong(ipAddressNew.getBeginIp());
            ipAddressNew.setBeginIpLong(beginIpLong);
        }
        if (ipAddressNew.getEndIp() != null) {
            long endIpLong = ipToLong(ipAddressNew.getEndIp());
            ipAddressNew.setEndIpLong(endIpLong);
        }
        return this.ipAddressNewMapper.selectIpAddressNewByBuOpen(ipAddressNew);

    }

    /*
     * IP地址段拆分（待完善，不存在两个IP地址的使用，当掩码等于30时调用两次）
     * */
    public int splitIp(String beginIp) {
        IpAddressNew ipAddressNew1 = selectIpAddressNewByBeginIp(beginIp).get(0);
        String ipType = ipAddressNew1.getIpType();
        //ip数目除2
        int ipNum = ipAddressNew1.getIpNum();
        ipNum = ipNum / 2;
        if (ipNum > 0) {
            ipAddressNew1.setIpNum(ipNum);
            //掩码地址加一
            int maskLength = ipAddressNew1.getMaskLength();
            maskLength = maskLength + 1;
            ipAddressNew1.setMaskLength(maskLength);
            if (ipAddressNew1.getIsUse().equals("预留")) {
                //修改结束IP，第一段IP地址
                String endIp1 = ipAddressNew1.getBeginIp() + "/" + maskLength;
                endIp1 = getEndIp(endIp1);
                ipAddressNew1.setEndIp(endIp1);
                long endIpLong1 = ipToLong(endIp1);
                ipAddressNew1.setEndIpLong(endIpLong1);
                System.out.println(ipAddressNew1);
                this.updateIpAddressNewByFindAll(ipAddressNew1);

                //修改起始IP,第二段IP地址
                long beginIpLong2 = ipToLong(endIp1) + 1;
                ipAddressNew1.setBeginIpLong(beginIpLong2);
                String beginIp2 = longToIp(beginIpLong2);
                ipAddressNew1.setBeginIp(beginIp2);
                String str = beginIp2 + "/" + maskLength;
                String endIp2 = getEndIp(str);
                ipAddressNew1.setEndIp(endIp2);
                long endIpLong2 = ipToLong(endIp2);
                ipAddressNew1.setEndIpLong(endIpLong2);
                ipAddressNew1.setIpType(ipType);
                this.insertIpAddressNewByFindAll(ipAddressNew1);
                System.out.println(ipAddressNew1);
                System.out.println("成功");
                return 1;
            } else {
                System.out.println("失败");
                return 0;
            }
        } else {
            System.out.println("失败");
            return 0;
        }
    }

    /*
     * IP聚合方法（（待完善，不存在两个IP地址的使用，当掩码等于32时调用两次））
     * */
    public int togetherIp(String beginIp) {
        if (selectIpAddressNewByBeginIp(beginIp).size() != 0) {
            IpAddressNew ipAddressNew1 = selectIpAddressNewByBeginIp(beginIp).get(0);
            //IP数目乘二
            int ipNum = ipAddressNew1.getIpNum();
            ipNum = ipNum * 2;

            //子网掩码减一
            int maskLength = ipAddressNew1.getMaskLength();
            maskLength = maskLength - 1;

            //修改IP地址段
            String str = ipAddressNew1.getBeginIp() + "/" + maskLength;
            String beginIp1 = getStartIp(str);
            long beginIpLong1 = ipToLong(beginIp1);
            String endIp1 = getEndIp(str);
            long endIpLong1 = ipToLong(endIp1);
            if (selectIpAddressNewByBeginIp(beginIp1).size() != 0 && selectIpAddressNewByEndIp(endIp1).size() != 0) {
                IpAddressNew ipAddressNew2 = selectIpAddressNewByBeginIp(beginIp1).get(0);
                System.out.println(ipAddressNew2.getIsUse());
                System.out.println(ipAddressNew2);
                IpAddressNew ipAddressNew3 = selectIpAddressNewByEndIp(endIp1).get(0);
                System.out.println(ipAddressNew3.getIsUse());
                System.out.println(ipAddressNew3);
                if (ipAddressNew2.getIsUse().equals("预留") && ipAddressNew3.getIsUse().equals("预留")
                        && ipAddressNew2.getMaskLength() == ipAddressNew3.getMaskLength()) {

                    this.deleteIpAddressNewByFindAll(ipAddressNew2);
                    this.deleteIpAddressNewByFindAll(ipAddressNew3);
                    ipAddressNew1.setMaskLength(maskLength);
                    ipAddressNew1.setIpNum(ipNum);
                    ipAddressNew1.setBeginIp(beginIp1);
                    ipAddressNew1.setBeginIpLong(beginIpLong1);
                    ipAddressNew1.setEndIp(endIp1);
                    ipAddressNew1.setEndIpLong(endIpLong1);
                    this.insertIpAddressNewByFindAll(ipAddressNew1);
                    System.out.println("成功");
                    System.out.println(ipAddressNew1);
                    return 1;

                } else {
                    System.out.println("失败");
                    return 0;
                }
            } else {
                System.out.println("失败");
                return 0;

            }
        } else {
            System.out.println("无法聚合");
            return 0;
        }
    }

    /*
     * IP地址使用情况的修改和导入
     * 导入使用或分配数据后缀alter
     * */
    public int autoSplitIp(IpAddressNew ipAddressNew) {

        if (ipAddressNew.getIsUse() == null) {
            ipAddressNew.setIsUse("已分配");
        }
        if (ipAddressNew.getMaskLength() != null) {
            int maskLengthAlter = ipAddressNew.getMaskLength();
            if (16 < maskLengthAlter && maskLengthAlter <= 32) {
                int ipNumAlter = 1 << (32 - maskLengthAlter);
                ipAddressNew.setIpNum(ipNumAlter);
                formatPut(ipAddressNew);
            } else {
                System.out.println("掩码输入错误！！");
            }
        } else {
            if (ipAddressNew.getIpNum() != null) {
                int ipNumAlter = ipAddressNew.getIpNum();
                if ((ipNumAlter % 2 == 0 || ipNumAlter == 1) && ipNumAlter > 0) {
                    //关联子网掩码
                    int n = (int) (Math.log(ipNumAlter) / Math.log(2));
                    int maskLengthAlter = 32 - n;
                    ipAddressNew.setMaskLength(maskLengthAlter);
                    formatPut(ipAddressNew);
                    System.out.println(ipAddressNew);
                } else {
                    System.out.println("数目输入错误！！");
                }
            }
        }

        /**
         * 循环查找范围IP
         */

        if (selectIpAddressNewByRangeIp(ipAddressNew).size() != 0) {
            IpAddressNew ipAddressNewRange = selectIpAddressNewByRangeIp(ipAddressNew).get(0);
            int maskLengthRange = ipAddressNewRange.getMaskLength();
            while (ipAddressNew.getMaskLength() - maskLengthRange != 0) {
                int i = splitIp(ipAddressNewRange.getBeginIp());
                if (i == 0) {
                    System.out.println(ipAddressNew + "有问题");
                    break;
                }
                ipAddressNewRange = selectIpAddressNewByRangeIp(ipAddressNew).get(0);
                maskLengthRange = ipAddressNewRange.getMaskLength();
            }
            /**
             * 扫除IP数目为2的记录(待完善)
             */
            if (selectIpAddressNewByIpNum(2).size() != 0) {
                for (int i = 0; selectIpAddressNewByIpNum(2).size() - i > 0; i++) {
                    IpAddressNew ipAddressNew1 = selectIpAddressNewByIpNum(2).get(i);
                    splitIp(ipAddressNew1.getBeginIp());
                }


            }
            IpAddressNew ipAddressNewRange1 = ipAddressNew;
            ipAddressNewRange1.setId(ipAddressNewRange.getId());

            if (ipAddressNewRange.getIsUse().equals("预留") || ipAddressNewRange.getProductNumber() != null &&
                    ipAddressNew.getProductNumber() != null &&
                    ipAddressNewRange.getProductNumber().equals(ipAddressNew.getProductNumber())) {
                this.updateIpAddressNewByFindAll(ipAddressNewRange1);
            } else {
                System.out.println("起始地址为：" + ipAddressNew.getBeginIp() + "已被其他用户使用");
            }


        }
        return 0;

    }

    /*
     * 格式输入,当前必须默认输入IP，掩码和个数二选一
     * */
    public IpAddressNew formatPut(IpAddressNew ipAddressNew) {
        String beginIpAlter = ipAddressNew.getBeginIp();
        int maskLengthAlter = ipAddressNew.getMaskLength();
        String str = beginIpAlter + "/" + maskLengthAlter;
        String beginIpAlter1 = getStartIp(str);
        ipAddressNew.setBeginIp(beginIpAlter1);
        long beginIpLongAlter1 = ipToLong(beginIpAlter1);
        ipAddressNew.setBeginIpLong(beginIpLongAlter1);
        String endIpAlter1 = getEndIp(str);
        ipAddressNew.setEndIp(endIpAlter1);
        long endIpLongAlter1 = ipToLong(endIpAlter1);
        ipAddressNew.setEndIpLong(endIpLongAlter1);

        return ipAddressNew;
    }

    /**
     * 范围查找
     */
    public List<IpAddressNew> selectIpAddressNewByRangeIp(IpAddressNew ipAddressNew) {
        return this.ipAddressNewMapper.selectIpAddressNewByRangeIp(ipAddressNew);
    }


    /**
     * 自动聚合，必须输入beginIp
     */
    public int autoTogetherIp(IpAddressNew ipAddressNew) {
        IpAddressNew ipAddressNewPre = selectIpAddressNewByBeginIp(ipAddressNew.getBeginIp()).get(0);
        ipAddressNew.setId(ipAddressNewPre.getId());
        updateIpAddressNewByFindAll(ipAddressNew);
        IpAddressNew ipAddressNewAfter = selectIpAddressNewByBeginIp(ipAddressNew.getBeginIp()).get(0);
        // if (ipAddressNewPre.getIsUse().equals("已分配") && ipAddressNewAfter.getIsUse().equals("预留")) {
        System.out.println(ipAddressNew.getMaskLength());
        if (ipAddressNewAfter.getMaskLength() != null) {
            int i = ipAddressNewAfter.getMaskLength();
            /**
             * 处理聚合为2个IP地址的情况
             */
            if (i == 32) {
                String str = ipAddressNew.getBeginIp() + "/" + 30;
                String beginIpPut1 = getStartIp(str);
                long beginIpPutLong1 = ipToLong(beginIpPut1);
                long beginIpPutLong2 = beginIpPutLong1 + 1;
                long beginIpPutLong3 = beginIpPutLong1 + 2;
                long beginIpPutLong4 = beginIpPutLong1 + 3;
                String beginIpPut2 = longToIp(beginIpPutLong2);
                String beginIpPut3 = longToIp(beginIpPutLong3);
                String beginIpPut4 = longToIp(beginIpPutLong4);
                if (selectIpAddressNewByBeginIp(beginIpPut1).size() != 0 && selectIpAddressNewByBeginIp(beginIpPut2).size() != 0
                        && selectIpAddressNewByBeginIp(beginIpPut3).size() != 0 && selectIpAddressNewByBeginIp(beginIpPut4).size() != 0) {
                    IpAddressNew ipAddressNew1 = selectIpAddressNewByBeginIp(beginIpPut1).get(0);
                    IpAddressNew ipAddressNew2 = selectIpAddressNewByBeginIp(beginIpPut2).get(0);
                    IpAddressNew ipAddressNew3 = selectIpAddressNewByBeginIp(beginIpPut3).get(0);
                    IpAddressNew ipAddressNew4 = selectIpAddressNewByBeginIp(beginIpPut4).get(0);

                    if (ipAddressNew1.getIsUse().equals("预留") && ipAddressNew2.getIsUse().equals("预留") &&
                            ipAddressNew3.getIsUse().equals("预留") && ipAddressNew4.getIsUse().equals("预留")) {
                        togetherIp(beginIpPut1);
                        togetherIp(beginIpPut3);
                        i--;
                        togetherIp(beginIpPut1);
                        i--;

                    }

                }
            }
            int j = 1;
            while (i > 16 && i != 32 && j != 0) {
                String str = ipAddressNew.getBeginIp() + "/" + i;
                String beginIpPut = getStartIp(str);
                i--;
                j = togetherIp(beginIpPut);


            }
        }

        // }
        return 0;
    }


    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {

        boolean notNull = false;
        List<IpAddressNew> ipAddressNewsList = new ArrayList<>();
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
        IpAddressNew ipAddressNew;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {//r = 2 表示从第三行开始循环 如果你的第三行开始是数据
            Row row = sheet.getRow(r);//通过sheet表单对象得到 行对象
            if (row == null) {
                continue;
            }

            ipAddressNew = new IpAddressNew();
            /**
             * 此处可以进行格式和内容的判断，将表的内容填入ipAddressNewsList
             */

            if (row.getCell(0).getCellType() != 1) {
                throw new MyException("导入失败(第" + (r + 1) + "行,起始IP请设为文本格式)");

            }

            String beginIp = row.getCell(0).getStringCellValue();

            if (beginIp == null || beginIp.isEmpty()) {
                throw new MyException("导入失败(第" + (r + 1) + "行,起始IP地址未填写)");
            }

            //row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);//设置格式为字符传型
            String endIp = row.getCell(1).getStringCellValue();
            if (endIp == null || endIp.isEmpty()) {
                throw new MyException("导入失败(第" + (r + 1) + "行,结束IP地址未填写)");
            }
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            Integer maskLength = Integer.parseInt(row.getCell(2).getStringCellValue());
            if (maskLength == null) {
                throw new MyException("导入失败(第" + (r + 1) + "行,掩码未填写)");
            }
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            Integer ipNum = Integer.parseInt(row.getCell(3).getStringCellValue());
            if (ipNum == null) {
                throw new MyException("导入失败(第" + (r + 1) + "行,IP数目未填写)");
            }
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            String ipType = row.getCell(4).getStringCellValue();
            if (ipType == null || ipType.isEmpty()) {
                throw new MyException("导入失败(第" + (r + 1) + "行,IP地址类型未填写)");
            }
            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            String isUse = row.getCell(5).getStringCellValue();
            if (isUse == null || isUse.isEmpty()) {
                throw new MyException("导入失败(第" + (r + 1) + "行,使用情况未填写)");
            }
            String wfId;
            if (row.getCell(6) != null) {
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                wfId = row.getCell(6).getStringCellValue();
            } else {
                wfId = null;
            }
            String customerName;
            if (row.getCell(7) != null) {
                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                customerName = row.getCell(7).getStringCellValue();
            } else {
                customerName = null;
            }
            String productNumber;
            if (row.getCell(8) != null) {
                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                productNumber = row.getCell(8).getStringCellValue();
            } else {
                productNumber = null;
            }
            Date useDate;
            if (row.getCell(9) != null) {
                useDate = row.getCell(9).getDateCellValue();
            } else {
                useDate = null;
            }

            Date unuseDate;
            if (row.getCell(10) != null) {
                unuseDate = row.getCell(10).getDateCellValue();
            } else {
                unuseDate = null;
            }
            String remark;
            if (row.getCell(11) != null) {
                row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                remark = row.getCell(11).getStringCellValue();
            } else {
                remark = null;
            }

            ipAddressNew.setBeginIp(beginIp);
            ipAddressNew.setBeginIpLong(ipToLong(beginIp));
            ipAddressNew.setEndIp(endIp);
            ipAddressNew.setEndIpLong(ipToLong(endIp));
            ipAddressNew.setIpNum(ipNum);
            ipAddressNew.setMaskLength(maskLength);
            ipAddressNew.setIpType(ipType);
            ipAddressNew.setIsUse(isUse);
            ipAddressNew.setWfId(wfId);
            ipAddressNew.setCustomerName(customerName);
            ipAddressNew.setProductNumber(productNumber);
            ipAddressNew.setUseDate(useDate);
            ipAddressNew.setUnuseDate(unuseDate);
            ipAddressNew.setRemark(remark);


            ipAddressNewsList.add(ipAddressNew);
        }

        /**
         *查看数据库是否有此数据，有则更新（需要改动） ,在这里定义循环调用自动分配IP地址的方法
         */

        for (IpAddressNew ipAddressNewResort : ipAddressNewsList) {
            autoSplitIp(ipAddressNewResort);


        }
        return notNull;
    }

    /*
     * 十进制转二进制
     * */
    public String function1(long n) {
        String result = Long.toBinaryString(n);
        //int r = Integer.parseInt(result);
        //System.out.println(r);
        return result;
    }




    /*
     * IP工具类
     *根据掩码位数计算掩码
     *根据网段计算起始IP 网段格式:x.x.x.x/x
     * 根据网段计算结束IP
     * ip地址转数字
     * ip数字转换为ip串
     * */

    /**
     * 根据掩码位数计算掩码
     *
     * @param inetMask 掩码位
     * @return 子网掩码
     */
    public static String getNetMask(int inetMask) {
        StringBuilder mask = new StringBuilder();
        if (inetMask > 32) {
            return null;
        }
        // 子网掩码为1占了几个字节
        int num1 = inetMask / 8;
        // 子网掩码的补位位数
        int num2 = inetMask % 8;
        int array[] = new int[4];
        for (int i = 0; i < num1; i++) {
            array[i] = 255;
        }
        for (int i = num1; i < 4; i++) {
            array[i] = 0;
        }
        for (int i = 0; i < num2; num2--) {
            array[num1] += 1 << 8 - num2;
        }
        for (int i = 0; i < 4; i++) {
            if (i == 3) {
                mask.append(array[i]);
            } else {
                mask.append(array[i] + ".");
            }
        }
        return mask.toString();
    }

    /**
     * 根据网段计算起始IP 网段格式:x.x.x.x/x
     * 一个网段0一般为网络地址,255一般为广播地址.
     * 起始IP计算:网段与掩码相与之后加一的IP地址
     *
     * @param segment 网段
     * @return 起始IP
     */
    public static String getStartIp(String segment) {
        StringBuffer startIp = new StringBuffer();
        if (segment == null) {
            return null;
        }
        String arr[] = segment.split("/");
        String ip = arr[0];
        Integer maskIndex = Integer.parseInt(arr[1]);
        String mask = getNetMask(maskIndex);
        if (4 != ip.split("\\.").length || mask == null) {
            return null;
        }
        int ipArray[] = new int[4];
        int netMaskArray[] = new int[4];
        for (int i = 0; i < 4; i++) {
            try {
                ipArray[i] = Integer.parseInt(ip.split("\\.")[i]);
                netMaskArray[i] = Integer.parseInt(mask.split("\\.")[i]);
                if (ipArray[i] > 255 || ipArray[i] < 0 || netMaskArray[i] > 255 || netMaskArray[i] < 0) {
                    return null;
                }
                ipArray[i] = ipArray[i] & netMaskArray[i];
                if (i == 3) {
                    //startIp.append(ipArray[i] + 1);//获取可用的第一个IP地址
                    startIp.append(ipArray[i]);//获取网路地址
                } else {
                    startIp.append(ipArray[i] + ".");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
//                System.out.println(e.getMessage());
            }
        }
        return startIp.toString();
    }

    /**
     * 根据网段计算结束IP
     *
     * @param segment
     * @return 结束IP
     */
    public static String getEndIp(String segment) {
        StringBuffer endIp = new StringBuffer();
        String startIp = getStartIp(segment);
        if (segment == null) {
            return null;
        }
        String arr[] = segment.split("/");
        String maskIndex = arr[1];
        //实际需要的IP个数
        int hostNumber = 0;
        int startIpArray[] = new int[4];
        try {
            hostNumber = 1 << 32 - (Integer.parseInt(maskIndex));
            for (int i = 0; i < 4; i++) {
                startIpArray[i] = Integer.parseInt(startIp.split("\\.")[i]);
                if (i == 3) {
                    /*startIpArray[i] = startIpArray[i] - 1;
                     * 可用最后一个IP地址
                     * 网关
                     * */
                    startIpArray[i] = startIpArray[i];//广播地址

                    break;
                }
            }
            startIpArray[3] = startIpArray[3] + hostNumber - 1;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        if (startIpArray[3] > 255) {
            int k = startIpArray[3] / 256;
            startIpArray[3] = startIpArray[3] % 256;
            startIpArray[2] = startIpArray[2] + k;
        }
        if (startIpArray[2] > 255) {
            int j = startIpArray[2] / 256;
            startIpArray[2] = startIpArray[2] % 256;
            startIpArray[1] = startIpArray[1] + j;
            if (startIpArray[1] > 255) {
                int k = startIpArray[1] / 256;
                startIpArray[1] = startIpArray[1] % 256;
                startIpArray[0] = startIpArray[0] + k;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (i == 3) {
                startIpArray[i] = startIpArray[i];
            }
            if ("" == endIp.toString() || endIp.length() == 0) {
                endIp.append(startIpArray[i]);
            } else {
                endIp.append("." + startIpArray[i]);
            }
        }
        return endIp.toString();
    }

    /**
     * ip地址转数字
     *
     * @param ipAddress ip地址串
     * @return
     */
    public static long ipToLong(String ipAddress) {

        String[] ipAddressInArray = ipAddress.split("\\.");

        long result = 0;
        for (int i = 0; i < ipAddressInArray.length; i++) {

            int power = 3 - i;
            int ip = Integer.parseInt(ipAddressInArray[i]);
            result += ip * Math.pow(256, power);

        }

        return result;
    }

    /**
     * ip数字转换为ip串
     *
     * @param ip
     * @return
     */
    public static String longToIp(long ip) {
        return ((ip >> 24) & 0xFF) + "."
                + ((ip >> 16) & 0xFF) + "."
                + ((ip >> 8) & 0xFF) + "."
                + (ip & 0xFF);
    }



	//----------------------------------------------------------------------------------------------------------------------------------------
	public List queryByBeginIp(String beginIp){
        return this.ipAddressNewMapper.queryByBeginIp(beginIp);
    }

    public List query(String beginIp){
        return this.ipAddressNewMapper.query(beginIp);
    }


    public List combine(){
        return this.ipAddressNewMapper.combine();
    }

    /**
     * 更新ipAddressNew
     * @param beginIp
     * @param oldIp
     * @return true/false
     */
    public boolean updateTable(IpAddressNew ipAddressNew){
        return this.ipAddressNewMapper.updateTable(ipAddressNew);
    }

    public boolean deleteTable(String beginIp){
        return this.ipAddressNewMapper.deleteTable(beginIp);
    }

    public boolean addTable(IpAddressNew ipAddressNew){

        return this.ipAddressNewMapper.addTable(ipAddressNew);
    }

    public List<Map<String, Object>> ipUseInfo(){
        return this.ipAddressNewMapper.ipUseInfo();
    }

  /*   public List ipUseInfo(){
        return this.ipAddressNewMapper.ipUseInfo();
    }*/
}



