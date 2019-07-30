package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "history_equip_table")
public class HistoryEquipTable {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 设备名称
     */
    @Column(name = "equip_name")
    private String equipName;

    /**
     * 设备别名
     */
    @Column(name = "equip_name_other")
    private String equipNameOther;

    /**
     * 网络层次
     */
    @Column(name = "net_level")
    private String netLevel;

    /**
     * 管理IP
     */
    @Column(name = "manage_Ip")
    private String manageIp;

    /**
     * 厂商
     */
    private String factory;

    /**
     * 设备类型
     */
    @Column(name = "equip_type")
    private String equipType;

    /**
     * 设备型号
     */
    @Column(name = "equip_model")
    private String equipModel;

    /**
     * 设备所在位置
     */
    @Column(name = "equip_address")
    private String equipAddress;

    /**
     * 删除日期
     */
    @Column(name = "delete_date")
    private Date deleteDate;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取设备名称
     *
     * @return equip_name - 设备名称
     */
    public String getEquipName() {
        return equipName;
    }

    /**
     * 设置设备名称
     *
     * @param equipName 设备名称
     */
    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    /**
     * 获取设备别名
     *
     * @return equip_name_other - 设备别名
     */
    public String getEquipNameOther() {
        return equipNameOther;
    }

    /**
     * 设置设备别名
     *
     * @param equipNameOther 设备别名
     */
    public void setEquipNameOther(String equipNameOther) {
        this.equipNameOther = equipNameOther;
    }

    /**
     * 获取网络层次
     *
     * @return net_level - 网络层次
     */
    public String getNetLevel() {
        return netLevel;
    }

    /**
     * 设置网络层次
     *
     * @param netLevel 网络层次
     */
    public void setNetLevel(String netLevel) {
        this.netLevel = netLevel;
    }

    /**
     * 获取管理IP
     *
     * @return manage_Ip - 管理IP
     */
    public String getManageIp() {
        return manageIp;
    }

    /**
     * 设置管理IP
     *
     * @param manageIp 管理IP
     */
    public void setManageIp(String manageIp) {
        this.manageIp = manageIp;
    }

    /**
     * 获取厂商
     *
     * @return factory - 厂商
     */
    public String getFactory() {
        return factory;
    }

    /**
     * 设置厂商
     *
     * @param factory 厂商
     */
    public void setFactory(String factory) {
        this.factory = factory;
    }

    /**
     * 获取设备类型
     *
     * @return equip_type - 设备类型
     */
    public String getEquipType() {
        return equipType;
    }

    /**
     * 设置设备类型
     *
     * @param equipType 设备类型
     */
    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    /**
     * 获取设备型号
     *
     * @return equip_model - 设备型号
     */
    public String getEquipModel() {
        return equipModel;
    }

    /**
     * 设置设备型号
     *
     * @param equipModel 设备型号
     */
    public void setEquipModel(String equipModel) {
        this.equipModel = equipModel;
    }

    /**
     * 获取设备所在位置
     *
     * @return equip_address - 设备所在位置
     */
    public String getEquipAddress() {
        return equipAddress;
    }

    /**
     * 设置设备所在位置
     *
     * @param equipAddress 设备所在位置
     */
    public void setEquipAddress(String equipAddress) {
        this.equipAddress = equipAddress;
    }

    /**
     * 获取删除日期
     *
     * @return deleteDate - 删除日期
     */
    public Date getDeleteDate() {
        return deleteDate;
    }

    /**
     * 设置删除日期
     *
     * @param deletedate 删除日期
     */
    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }
}