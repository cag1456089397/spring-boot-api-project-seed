package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ip_address_new")
public class IpAddressNew {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 网络地址
     */
    @Column(name = "begin_ip")
    private String beginIp;
    @Column(name = "begin_ip_long")
    private Long beginIpLong;

    @Override
    public String toString() {
        return "IpAddressNew{" +
                "id=" + id +
                ", beginIp='" + beginIp + '\'' +
                ", beginIpLong='" + beginIpLong + '\'' +
                ", endIp='" + endIp + '\'' +
                ", endIpLong='" + endIpLong + '\'' +
                ", ipNum=" + ipNum +
                ", maskLength=" + maskLength +
                ", isUse='" + isUse + '\'' +
                ", ipType='" + ipType + '\'' +
                ", wfId='" + wfId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", useDate=" + useDate +
                ", unuseDate=" + unuseDate +
                ", remark='" + remark + '\'' +
                '}';
    }

    /**
     * 广播地址
     */
    @Column(name = "end_ip")
    private String endIp;
    @Column(name = "end_ip_long")
    private Long endIpLong;

    /**
     * IP个数
     */
    @Column(name = "ip_num")
    private Integer ipNum;

    /**
     * 掩码长度
     */
    @Column(name = "mask_length")
    private Integer maskLength;

    /**
     * 是否使用
     */
    @Column(name = "is_use")
    private String isUse;

    /**
     * IP类型
     */
    @Column(name = "ip_type")
    private String ipType;

    /**
     * 工单编号
     */
    @Column(name = "wf_id")
    private String wfId;

    /**
     * 客户名称
     */
    @Column(name = "customer_name")
    private String customerName;

    /**
     * 产品号码
     */
    @Column(name = "product_number")
    private String productNumber;

    /**
     * 使用时间
     */
    @Column(name = "use_date")
    private Date useDate;

    /**
     * 回收时间
     */
    @Column(name = "unuse_date")
    private Date unuseDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取网络地址
     *
     * @return begin_ip - 网络地址
     */
    public String getBeginIp() {
        return beginIp;
    }

    public Long getBeginIpLong() {
        return beginIpLong;
    }

    /**
     * 设置网络地址
     *
     * @param beginIp 网络地址
     */
    public void setBeginIp(String beginIp) {
        this.beginIp = beginIp;
    }

    public void setBeginIpLong(Long beginIpLong) {
        this.beginIpLong = beginIpLong;
    }

    /**
     * 获取广播地址
     *
     * @return end_ip - 广播地址
     */
    public String getEndIp() {
        return endIp;
    }

    public Long getEndIpLong() {
        return endIpLong;
    }

    /**
     * 设置广播地址
     *
     * @param endIp 广播地址
     */
    public void setEndIp(String endIp) {
        this.endIp = endIp;
    }

    public void setEndIpLong(Long endIpLong) {
        this.endIpLong = endIpLong;
    }

    /**
     * 获取IP个数
     *
     * @return ip_num - IP个数
     */
    public Integer getIpNum() {
        return ipNum;
    }

    /**
     * 设置IP个数
     *
     * @param ipNum IP个数
     */
    public void setIpNum(Integer ipNum) {
        this.ipNum = ipNum;
    }

    /**
     * 获取掩码长度
     *
     * @return mask_length - 掩码长度
     */
    public Integer getMaskLength() {
        return maskLength;
    }

    /**
     * 设置掩码长度
     *
     * @param maskLength 掩码长度
     */
    public void setMaskLength(Integer maskLength) {
        this.maskLength = maskLength;
    }

    /**
     * 获取是否使用
     *
     * @return is_use - 是否使用
     */
    public String getIsUse() {
        return isUse;
    }

    /**
     * 设置是否使用
     *
     * @param isUse 是否使用
     */
    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    /**
     * 获取IP类型
     *
     * @return ip_type - IP类型
     */
    public String getIpType() {
        return ipType;
    }

    /**
     * 设置IP类型
     *
     * @param ipType IP类型
     */
    public void setIpType(String ipType) {
        this.ipType = ipType;
    }

    /**
     * 获取工单编号
     *
     * @return wf_id - 工单编号
     */
    public String getWfId() {
        return wfId;
    }

    /**
     * 设置工单编号
     *
     * @param wfId 工单编号
     */
    public void setWfId(String wfId) {
        this.wfId = wfId;
    }

    /**
     * 获取客户名称
     *
     * @return customer_name - 客户名称
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 设置客户名称
     *
     * @param customerName 客户名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 获取产品号码
     *
     * @return product_number - 产品号码
     */
    public String getProductNumber() {
        return productNumber;
    }

    /**
     * 设置产品号码
     *
     * @param productNumber 产品号码
     */
    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    /**
     * 获取使用时间
     *
     * @return use_date - 使用时间
     */
    public Date getUseDate() {
        return useDate;
    }

    /**
     * 设置使用时间
     *
     * @param useDate 使用时间
     */
    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    /**
     * 获取回收时间
     *
     * @return unuse_date - 回收时间
     */
    public Date getUnuseDate() {
        return unuseDate;
    }

    /**
     * 设置回收时间
     *
     * @param unuseDate 回收时间
     */
    public void setUnuseDate(Date unuseDate) {
        this.unuseDate = unuseDate;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }



}