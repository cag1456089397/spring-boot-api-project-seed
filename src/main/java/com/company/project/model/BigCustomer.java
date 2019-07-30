package com.company.project.model;

import javax.persistence.*;

@Table(name = "big_customer")
public class BigCustomer {
    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 工程统一工单编号
     */
    @Column(name = "engineering_unified_work_order_number")
    private String engineeringUnifiedWorkOrderNumber;

    /**
     * 工单编号
     */
    @Column(name = "order_number")
    private String orderNumber;

    /**
     * 一站式流水号
     */
    @Column(name = "one_stop_flow_number")
    private String oneStopFlowNumber;

    /**
     * 调单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * A端
     */
    @Column(name = "a_point")
    private String aPoint;

    /**
     * Z端
     */
    @Column(name = "z_point")
    private String zPoint;

    /**
     * 镇区
     */
    private String town;

    /**
     * 区域
     */
    private String area;

    /**
     * 客户名称(旧)
     */
    @Column(name = "old_customer_name")
    private String oldCustomerName;

    /**
     * 客户名称（标准）
     */
    @Column(name = "standard_customer_name")
    private String standardCustomerName;

    /**
     * 产品编号
     */
    @Column(name = "product_number")
    private String productNumber;

    /**
     * 是否对应集客
     */
    @Column(name = "is_collectors")
    private String isCollectors;

    /**
     * TOP名单标识
     */
    @Column(name = "top_list_ident")
    private String topListIdent;

    /**
     * 客户等级	
     */
    @Column(name = "customer_grade")
    private String customerGrade;

    /**
     * 速率(M)
     */
    private String rate;

    /**
     * 电路类型
     */
    @Column(name = "circuit_type")
    private String circuitType;

    /**
     * 业务类型	
     */
    @Column(name = "business_type")
    private String businessType;

    /**
     * 级别(类)
     */
    private String level;

    /**
     * 电路条数
     */
    @Column(name = "number_of_circuit_bars")
    private String numberOfCircuitBars;

    /**
     * 第一路由
     */
    @Column(name = "first_route")
    private String firstRoute;

    /**
     * 第二路由
     */
    @Column(name = "second_route")
    private String secondRoute;

    /**
     * 客户使用特性(全天/工作时间/特殊)
     */
    @Column(name = "customer_usage_features")
    private String customerUsageFeatures;

    /**
     * 所在行业
     */
    private String industry;

    /**
     * 客户联系人
     */
    @Column(name = "customer_contact")
    private String customerContact;

    /**
     * 客户联系电话
     */
    @Column(name = "customer_telephone")
    private String customerTelephone;

    /**
     * Z端客户地址
     */
    @Column(name = "z_point_customer_address")
    private String zPointCustomerAddress;

    /**
     * A端客户地址 
     */
    @Column(name = "a_point_customer_address")
    private String aPointCustomerAddress;

    /**
     * 自维负责人
     */
    @Column(name = "self_division_manager")
    private String selfDivisionManager;

    /**
     * 自维负责人联系电话
     */
    @Column(name = "self_division_telephone")
    private String selfDivisionTelephone;

    /**
     * 自维负责人电子邮件
     */
    @Column(name = "self_division_mail")
    private String selfDivisionMail;

    /**
     * 接入站
     */
    @Column(name = "access_station")
    private String accessStation;

    /**
     * 客户设备安装资料
     */
    @Column(name = "customer_equip_install_info")
    private String customerEquipInstallInfo;

    /**
     * 业务开通时间
     */
    @Column(name = "business_opening_date")
    private String businessOpeningDate;

    /**
     * 台账最近更新时间
     */
    @Column(name = "ledger_update_time")
    private String ledgerUpdateTime;

    /**
     * 备注
     */
    private String comment1;

    /**
     * 备注
     */
    private String comment2;

    /**
     * 电路编号
     */
    @Column(name = "circuit_number")
    private String circuitNumber;

    /**
     * 号码资源/IP资源 
     */
    @Column(name = "`number_or_ ip_ resource`")
    private String numberOrIpResource;

    /**
     * 本地传输端口信息、路由、电路信息
     */
    @Column(name = "local_transmission_info")
    private String localTransmissionInfo;

    /**
     * 获取序号
     *
     * @return id - 序号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置序号
     *
     * @param id 序号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取工程统一工单编号
     *
     * @return engineering_unified_work_order_number - 工程统一工单编号
     */
    public String getEngineeringUnifiedWorkOrderNumber() {
        return engineeringUnifiedWorkOrderNumber;
    }

    /**
     * 设置工程统一工单编号
     *
     * @param engineeringUnifiedWorkOrderNumber 工程统一工单编号
     */
    public void setEngineeringUnifiedWorkOrderNumber(String engineeringUnifiedWorkOrderNumber) {
        this.engineeringUnifiedWorkOrderNumber = engineeringUnifiedWorkOrderNumber;
    }

    /**
     * 获取工单编号
     *
     * @return order_number - 工单编号
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * 设置工单编号
     *
     * @param orderNumber 工单编号
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * 获取一站式流水号
     *
     * @return one_stop_flow_number - 一站式流水号
     */
    public String getOneStopFlowNumber() {
        return oneStopFlowNumber;
    }

    /**
     * 设置一站式流水号
     *
     * @param oneStopFlowNumber 一站式流水号
     */
    public void setOneStopFlowNumber(String oneStopFlowNumber) {
        this.oneStopFlowNumber = oneStopFlowNumber;
    }

    /**
     * 获取调单号
     *
     * @return order_no - 调单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置调单号
     *
     * @param orderNo 调单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取A端
     *
     * @return a_point - A端
     */
    public String getaPoint() {
        return aPoint;
    }

    /**
     * 设置A端
     *
     * @param aPoint A端
     */
    public void setaPoint(String aPoint) {
        this.aPoint = aPoint;
    }

    /**
     * 获取Z端
     *
     * @return z_point - Z端
     */
    public String getzPoint() {
        return zPoint;
    }

    /**
     * 设置Z端
     *
     * @param zPoint Z端
     */
    public void setzPoint(String zPoint) {
        this.zPoint = zPoint;
    }

    /**
     * 获取镇区
     *
     * @return town - 镇区
     */
    public String getTown() {
        return town;
    }

    /**
     * 设置镇区
     *
     * @param town 镇区
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * 获取区域
     *
     * @return area - 区域
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置区域
     *
     * @param area 区域
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取客户名称(旧)
     *
     * @return old_customer_name - 客户名称(旧)
     */
    public String getOldCustomerName() {
        return oldCustomerName;
    }

    /**
     * 设置客户名称(旧)
     *
     * @param oldCustomerName 客户名称(旧)
     */
    public void setOldCustomerName(String oldCustomerName) {
        this.oldCustomerName = oldCustomerName;
    }

    /**
     * 获取客户名称（标准）
     *
     * @return standard_customer_name - 客户名称（标准）
     */
    public String getStandardCustomerName() {
        return standardCustomerName;
    }

    /**
     * 设置客户名称（标准）
     *
     * @param standardCustomerName 客户名称（标准）
     */
    public void setStandardCustomerName(String standardCustomerName) {
        this.standardCustomerName = standardCustomerName;
    }

    /**
     * 获取产品编号
     *
     * @return product_number - 产品编号
     */
    public String getProductNumber() {
        return productNumber;
    }

    /**
     * 设置产品编号
     *
     * @param productNumber 产品编号
     */
    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    /**
     * 获取是否对应集客
     *
     * @return is_collectors - 是否对应集客
     */
    public String getIsCollectors() {
        return isCollectors;
    }

    /**
     * 设置是否对应集客
     *
     * @param isCollectors 是否对应集客
     */
    public void setIsCollectors(String isCollectors) {
        this.isCollectors = isCollectors;
    }

    /**
     * 获取TOP名单标识
     *
     * @return top_list_ident - TOP名单标识
     */
    public String getTopListIdent() {
        return topListIdent;
    }

    /**
     * 设置TOP名单标识
     *
     * @param topListIdent TOP名单标识
     */
    public void setTopListIdent(String topListIdent) {
        this.topListIdent = topListIdent;
    }

    /**
     * 获取客户等级	
     *
     * @return customer_grade - 客户等级	
     */
    public String getCustomerGrade() {
        return customerGrade;
    }

    /**
     * 设置客户等级	
     *
     * @param customerGrade 客户等级	
     */
    public void setCustomerGrade(String customerGrade) {
        this.customerGrade = customerGrade;
    }

    /**
     * 获取速率(M)
     *
     * @return rate - 速率(M)
     */
    public String getRate() {
        return rate;
    }

    /**
     * 设置速率(M)
     *
     * @param rate 速率(M)
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     * 获取电路类型
     *
     * @return circuit_type - 电路类型
     */
    public String getCircuitType() {
        return circuitType;
    }

    /**
     * 设置电路类型
     *
     * @param circuitType 电路类型
     */
    public void setCircuitType(String circuitType) {
        this.circuitType = circuitType;
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
     * 获取级别(类)
     *
     * @return level - 级别(类)
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置级别(类)
     *
     * @param level 级别(类)
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 获取电路条数
     *
     * @return number_of_circuit_bars - 电路条数
     */
    public String getNumberOfCircuitBars() {
        return numberOfCircuitBars;
    }

    /**
     * 设置电路条数
     *
     * @param numberOfCircuitBars 电路条数
     */
    public void setNumberOfCircuitBars(String numberOfCircuitBars) {
        this.numberOfCircuitBars = numberOfCircuitBars;
    }

    /**
     * 获取第一路由
     *
     * @return first_route - 第一路由
     */
    public String getFirstRoute() {
        return firstRoute;
    }

    /**
     * 设置第一路由
     *
     * @param firstRoute 第一路由
     */
    public void setFirstRoute(String firstRoute) {
        this.firstRoute = firstRoute;
    }

    /**
     * 获取第二路由
     *
     * @return second_route - 第二路由
     */
    public String getSecondRoute() {
        return secondRoute;
    }

    /**
     * 设置第二路由
     *
     * @param secondRoute 第二路由
     */
    public void setSecondRoute(String secondRoute) {
        this.secondRoute = secondRoute;
    }

    /**
     * 获取客户使用特性(全天/工作时间/特殊)
     *
     * @return customer_usage_features - 客户使用特性(全天/工作时间/特殊)
     */
    public String getCustomerUsageFeatures() {
        return customerUsageFeatures;
    }

    /**
     * 设置客户使用特性(全天/工作时间/特殊)
     *
     * @param customerUsageFeatures 客户使用特性(全天/工作时间/特殊)
     */
    public void setCustomerUsageFeatures(String customerUsageFeatures) {
        this.customerUsageFeatures = customerUsageFeatures;
    }

    /**
     * 获取所在行业
     *
     * @return industry - 所在行业
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置所在行业
     *
     * @param industry 所在行业
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * 获取客户联系人
     *
     * @return customer_contact - 客户联系人
     */
    public String getCustomerContact() {
        return customerContact;
    }

    /**
     * 设置客户联系人
     *
     * @param customerContact 客户联系人
     */
    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    /**
     * 获取客户联系电话
     *
     * @return customer_telephone - 客户联系电话
     */
    public String getCustomerTelephone() {
        return customerTelephone;
    }

    /**
     * 设置客户联系电话
     *
     * @param customerTelephone 客户联系电话
     */
    public void setCustomerTelephone(String customerTelephone) {
        this.customerTelephone = customerTelephone;
    }

    /**
     * 获取Z端客户地址
     *
     * @return z_point_customer_address - Z端客户地址
     */
    public String getzPointCustomerAddress() {
        return zPointCustomerAddress;
    }

    /**
     * 设置Z端客户地址
     *
     * @param zPointCustomerAddress Z端客户地址
     */
    public void setzPointCustomerAddress(String zPointCustomerAddress) {
        this.zPointCustomerAddress = zPointCustomerAddress;
    }

    /**
     * 获取A端客户地址 
     *
     * @return a_point_customer_address - A端客户地址 
     */
    public String getaPointCustomerAddress() {
        return aPointCustomerAddress;
    }

    /**
     * 设置A端客户地址 
     *
     * @param aPointCustomerAddress A端客户地址 
     */
    public void setaPointCustomerAddress(String aPointCustomerAddress) {
        this.aPointCustomerAddress = aPointCustomerAddress;
    }

    /**
     * 获取自维负责人
     *
     * @return self_division_manager - 自维负责人
     */
    public String getSelfDivisionManager() {
        return selfDivisionManager;
    }

    /**
     * 设置自维负责人
     *
     * @param selfDivisionManager 自维负责人
     */
    public void setSelfDivisionManager(String selfDivisionManager) {
        this.selfDivisionManager = selfDivisionManager;
    }

    /**
     * 获取自维负责人联系电话
     *
     * @return self_division_telephone - 自维负责人联系电话
     */
    public String getSelfDivisionTelephone() {
        return selfDivisionTelephone;
    }

    /**
     * 设置自维负责人联系电话
     *
     * @param selfDivisionTelephone 自维负责人联系电话
     */
    public void setSelfDivisionTelephone(String selfDivisionTelephone) {
        this.selfDivisionTelephone = selfDivisionTelephone;
    }

    /**
     * 获取自维负责人电子邮件
     *
     * @return self_division_mail - 自维负责人电子邮件
     */
    public String getSelfDivisionMail() {
        return selfDivisionMail;
    }

    /**
     * 设置自维负责人电子邮件
     *
     * @param selfDivisionMail 自维负责人电子邮件
     */
    public void setSelfDivisionMail(String selfDivisionMail) {
        this.selfDivisionMail = selfDivisionMail;
    }

    /**
     * 获取接入站
     *
     * @return access_station - 接入站
     */
    public String getAccessStation() {
        return accessStation;
    }

    /**
     * 设置接入站
     *
     * @param accessStation 接入站
     */
    public void setAccessStation(String accessStation) {
        this.accessStation = accessStation;
    }

    /**
     * 获取客户设备安装资料
     *
     * @return customer_equip_install_info - 客户设备安装资料
     */
    public String getCustomerEquipInstallInfo() {
        return customerEquipInstallInfo;
    }

    /**
     * 设置客户设备安装资料
     *
     * @param customerEquipInstallInfo 客户设备安装资料
     */
    public void setCustomerEquipInstallInfo(String customerEquipInstallInfo) {
        this.customerEquipInstallInfo = customerEquipInstallInfo;
    }

    /**
     * 获取业务开通时间
     *
     * @return business_opening_date - 业务开通时间
     */
    public String getBusinessOpeningDate() {
        return businessOpeningDate;
    }

    /**
     * 设置业务开通时间
     *
     * @param businessOpeningDate 业务开通时间
     */
    public void setBusinessOpeningDate(String businessOpeningDate) {
        this.businessOpeningDate = businessOpeningDate;
    }

    /**
     * 获取台账最近更新时间
     *
     * @return ledger_update_time - 台账最近更新时间
     */
    public String getLedgerUpdateTime() {
        return ledgerUpdateTime;
    }

    /**
     * 设置台账最近更新时间
     *
     * @param ledgerUpdateTime 台账最近更新时间
     */
    public void setLedgerUpdateTime(String ledgerUpdateTime) {
        this.ledgerUpdateTime = ledgerUpdateTime;
    }

    /**
     * 获取备注
     *
     * @return comment1 - 备注
     */
    public String getComment1() {
        return comment1;
    }

    /**
     * 设置备注
     *
     * @param comment1 备注
     */
    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    /**
     * 获取备注
     *
     * @return comment2 - 备注
     */
    public String getComment2() {
        return comment2;
    }

    /**
     * 设置备注
     *
     * @param comment2 备注
     */
    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    /**
     * 获取电路编号
     *
     * @return circuit_number - 电路编号
     */
    public String getCircuitNumber() {
        return circuitNumber;
    }

    /**
     * 设置电路编号
     *
     * @param circuitNumber 电路编号
     */
    public void setCircuitNumber(String circuitNumber) {
        this.circuitNumber = circuitNumber;
    }

    /**
     * 获取号码资源/IP资源 
     *
     * @return number_or_ ip_ resource - 号码资源/IP资源 
     */
    public String getNumberOrIpResource() {
        return numberOrIpResource;
    }

    /**
     * 设置号码资源/IP资源 
     *
     * @param numberOrIpResource 号码资源/IP资源 
     */
    public void setNumberOrIpResource(String numberOrIpResource) {
        this.numberOrIpResource = numberOrIpResource;
    }

    /**
     * 获取本地传输端口信息、路由、电路信息
     *
     * @return local_transmission_info - 本地传输端口信息、路由、电路信息
     */
    public String getLocalTransmissionInfo() {
        return localTransmissionInfo;
    }

    /**
     * 设置本地传输端口信息、路由、电路信息
     *
     * @param localTransmissionInfo 本地传输端口信息、路由、电路信息
     */
    public void setLocalTransmissionInfo(String localTransmissionInfo) {
        this.localTransmissionInfo = localTransmissionInfo;
    }
}