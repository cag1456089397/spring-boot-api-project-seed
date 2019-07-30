package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_table")
public class CustomerTable {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 客户名称
     */
    private String customer;

    /**
     * 业务类型
     */
    @Column(name = "business_type")
    private String businessType;

    /**
     * 产品号码
     */
    @Column(name = "produce_number")
    private String produceNumber;

    /**
     * 网关名称
     */
    @Column(name = "network_name")
    private String networkName;

    /**
     * 网关别名
     */
    @Column(name = "network_name_other")
    private String networkNameOther;

    /**
     * 网关设备IP
     */
    @Column(name = "network_ip")
    private String networkIp;

    /**
     * 网关接口
     */
    @Column(name = "network_port")
    private String networkPort;

    /**
     * 接入设备名称
     */
    @Column(name = "insert_name")
    private String insertName;

    /**
     * 接入设备别名
     */
    @Column(name = "insert_name_other")
    private String insertNameOther;

    /**
     * 接入设备别名
     */
    @Column(name = "insert_ip")
    private String insertIp;

    /**
     * 接入端口
     */
    @Column(name = "insert_port")
    private String insertPort;

    /**
     * VLAN
     */
    @Column(name = "vlan_id")
    private String vlanId;

    /**
     * 工单号
     */
    @Column(name = "wf_id")
    private String wfId;

    /**
     * 电路编号
     */
    @Column(name = "electric_id")
    private String electricId;

    /**
     * 互联IP
     */
    @Column(name = "connect_ip")
    private String connectIp;

    /**
     * 用户IP
     */
    @Column(name = "user_ip")
    private String userIp;

    /**
     * IP数量
     */
    @Column(name = "ip_num")
    private Integer ipNum;

    /**
     * 接入速率
     */
    @Column(name = "insert_speed")
    private Integer insertSpeed;

    /**
     * 客户联系人
     */
    @Column(name = "link_people")
    private String linkPeople;

    /**
     * 客户联系方式
     */
    @Column(name = "link_phone")
    private String linkPhone;

    /**
     * 装机地址
     */
    @Column(name = "customer_address")
    private String customerAddress;

    /**
     * 开通时间
     */
    @Column(name = "open_date")
    private Date openDate;

    /**
     * 更新时间
     */
    @Column(name = "up_date")
    private Date upDate;

    /**
     * 带宽是否一致
     */
    @Column(name = "same_band")
    private String sameBand;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否开通80、8080、443端口
     */
    @Column(name = "is_80")
    private String is80;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取客户名称
     *
     * @return customer - 客户名称
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * 设置客户名称
     *
     * @param customer 客户名称
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * 获取业务类型
     *
     * @return business_type - 业务类型
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * 设置业务类型
     *
     * @param businessType 业务类型
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    /**
     * 获取产品号码
     *
     * @return produce_number - 产品号码
     */
    public String getProduceNumber() {
        return produceNumber;
    }

    /**
     * 设置产品号码
     *
     * @param produceNumber 产品号码
     */
    public void setProduceNumber(String produceNumber) {
        this.produceNumber = produceNumber;
    }

    /**
     * 获取网关名称
     *
     * @return network_name - 网关名称
     */
    public String getNetworkName() {
        return networkName;
    }

    /**
     * 设置网关名称
     *
     * @param networkName 网关名称
     */
    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    /**
     * 获取网关别名
     *
     * @return network_name_other - 网关别名
     */
    public String getNetworkNameOther() {
        return networkNameOther;
    }

    /**
     * 设置网关别名
     *
     * @param networkNameOther 网关别名
     */
    public void setNetworkNameOther(String networkNameOther) {
        this.networkNameOther = networkNameOther;
    }

    /**
     * 获取网关设备IP
     *
     * @return network_ip - 网关设备IP
     */
    public String getNetworkIp() {
        return networkIp;
    }

    /**
     * 设置网关设备IP
     *
     * @param networkIp 网关设备IP
     */
    public void setNetworkIp(String networkIp) {
        this.networkIp = networkIp;
    }

    /**
     * 获取网关接口
     *
     * @return network_port - 网关接口
     */
    public String getNetworkPort() {
        return networkPort;
    }

    /**
     * 设置网关接口
     *
     * @param networkPort 网关接口
     */
    public void setNetworkPort(String networkPort) {
        this.networkPort = networkPort;
    }

    /**
     * 获取接入设备名称
     *
     * @return insert_name - 接入设备名称
     */
    public String getInsertName() {
        return insertName;
    }

    /**
     * 设置接入设备名称
     *
     * @param insertName 接入设备名称
     */
    public void setInsertName(String insertName) {
        this.insertName = insertName;
    }

    /**
     * 获取接入设备别名
     *
     * @return insert_name_other - 接入设备别名
     */
    public String getInsertNameOther() {
        return insertNameOther;
    }

    /**
     * 设置接入设备别名
     *
     * @param insertNameOther 接入设备别名
     */
    public void setInsertNameOther(String insertNameOther) {
        this.insertNameOther = insertNameOther;
    }

    /**
     * 获取接入设备别名
     *
     * @return insert_ip - 接入设备别名
     */
    public String getInsertIp() {
        return insertIp;
    }

    /**
     * 设置接入设备别名
     *
     * @param insertIp 接入设备别名
     */
    public void setInsertIp(String insertIp) {
        this.insertIp = insertIp;
    }

    /**
     * 获取接入端口
     *
     * @return insert_port - 接入端口
     */
    public String getInsertPort() {
        return insertPort;
    }

    /**
     * 设置接入端口
     *
     * @param insertPort 接入端口
     */
    public void setInsertPort(String insertPort) {
        this.insertPort = insertPort;
    }

    /**
     * 获取VLAN
     *
     * @return vlan_id - VLAN
     */
    public String getVlanId() {
        return vlanId;
    }

    /**
     * 设置VLAN
     *
     * @param vlanId VLAN
     */
    public void setVlanId(String vlanId) {
        this.vlanId = vlanId;
    }

    /**
     * 获取工单号
     *
     * @return wf_id - 工单号
     */
    public String getWfId() {
        return wfId;
    }

    /**
     * 设置工单号
     *
     * @param wfId 工单号
     */
    public void setWfId(String wfId) {
        this.wfId = wfId;
    }

    /**
     * 获取电路编号
     *
     * @return electric_id - 电路编号
     */
    public String getElectricId() {
        return electricId;
    }

    /**
     * 设置电路编号
     *
     * @param electricId 电路编号
     */
    public void setElectricId(String electricId) {
        this.electricId = electricId;
    }

    /**
     * 获取互联IP
     *
     * @return connect_ip - 互联IP
     */
    public String getConnectIp() {
        return connectIp;
    }

    /**
     * 设置互联IP
     *
     * @param connectIp 互联IP
     */
    public void setConnectIp(String connectIp) {
        this.connectIp = connectIp;
    }

    /**
     * 获取用户IP
     *
     * @return user_ip - 用户IP
     */
    public String getUserIp() {
        return userIp;
    }

    /**
     * 设置用户IP
     *
     * @param userIp 用户IP
     */
    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    /**
     * 获取IP数量
     *
     * @return ip_num - IP数量
     */
    public Integer getIpNum() {
        return ipNum;
    }

    /**
     * 设置IP数量
     *
     * @param ipNum IP数量
     */
    public void setIpNum(Integer ipNum) {
        this.ipNum = ipNum;
    }

    /**
     * 获取接入速率
     *
     * @return insert_speed - 接入速率
     */
    public Integer getInsertSpeed() {
        return insertSpeed;
    }

    /**
     * 设置接入速率
     *
     * @param insertSpeed 接入速率
     */
    public void setInsertSpeed(Integer insertSpeed) {
        this.insertSpeed = insertSpeed;
    }

    /**
     * 获取客户联系人
     *
     * @return link_people - 客户联系人
     */
    public String getLinkPeople() {
        return linkPeople;
    }

    /**
     * 设置客户联系人
     *
     * @param linkPeople 客户联系人
     */
    public void setLinkPeople(String linkPeople) {
        this.linkPeople = linkPeople;
    }

    /**
     * 获取客户联系方式
     *
     * @return link_phone - 客户联系方式
     */
    public String getLinkPhone() {
        return linkPhone;
    }

    /**
     * 设置客户联系方式
     *
     * @param linkPhone 客户联系方式
     */
    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    /**
     * 获取装机地址
     *
     * @return customer_address - 装机地址
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * 设置装机地址
     *
     * @param customerAddress 装机地址
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * 获取开通时间
     *
     * @return open_date - 开通时间
     */
    public Date getOpenDate() {
        return openDate;
    }

    /**
     * 设置开通时间
     *
     * @param openDate 开通时间
     */
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    /**
     * 获取更新时间
     *
     * @return up_date - 更新时间
     */
    public Date getUpDate() {
        return upDate;
    }

    /**
     * 设置更新时间
     *
     * @param upDate 更新时间
     */
    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }

    /**
     * 获取带宽是否一致
     *
     * @return same_band - 带宽是否一致
     */
    public String getSameBand() {
        return sameBand;
    }

    /**
     * 设置带宽是否一致
     *
     * @param sameBand 带宽是否一致
     */
    public void setSameBand(String sameBand) {
        this.sameBand = sameBand;
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

    /**
     * 获取是否开通80、8080、443端口
     *
     * @return is_80 - 是否开通80、8080、443端口
     */
    public String getIs80() {
        return is80;
    }

    /**
     * 设置是否开通80、8080、443端口
     *
     * @param is80 是否开通80、8080、443端口
     */
    public void setIs80(String is80) {
        this.is80 = is80;
    }
}