package com.company.project.model;

import javax.persistence.*;

@Table(name = "eq_order")
public class EqOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 厂家
     */
    @Column(name = "factory_eq")
    private String factoryEq;

    /**
     * 设备型号
     */
    @Column(name = "equip_type")
    private String equipType;

    /**
     * 指令类型
     */
    @Column(name = "order_type")
    private String orderType;

    /**
     * 具体指令
     */
    @Column(name = "order_eq")
    private String orderEq;

    /**
     * 用户字符
     */
    @Column(name = "login_str")
    private String loginStr;

    /**
     * 密码字符
     */
    @Column(name = "password_str")
    private String passwordStr;

    /**
     * 用户名
     */
    @Column(name = "login_eq")
    private String loginEq;

    /**
     * 密码
     */
    @Column(name = "pass_eq")
    private String passEq;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取厂家
     *
     * @return factory_eq - 厂家
     */
    public String getFactoryEq() {
        return factoryEq;
    }

    /**
     * 设置厂家
     *
     * @param factoryEq 厂家
     */
    public void setFactoryEq(String factoryEq) {
        this.factoryEq = factoryEq;
    }

    /**
     * 获取设备型号
     *
     * @return equip_type - 设备型号
     */
    public String getEquipType() {
        return equipType;
    }

    /**
     * 设置设备型号
     *
     * @param equipType 设备型号
     */
    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    /**
     * 获取指令类型
     *
     * @return order_type - 指令类型
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置指令类型
     *
     * @param orderType 指令类型
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取具体指令
     *
     * @return order_eq - 具体指令
     */
    public String getOrderEq() {
        return orderEq;
    }

    /**
     * 设置具体指令
     *
     * @param orderEq 具体指令
     */
    public void setOrderEq(String orderEq) {
        this.orderEq = orderEq;
    }

    /**
     * 获取用户字符
     *
     * @return login_str - 用户字符
     */
    public String getLoginStr() {
        return loginStr;
    }

    /**
     * 设置用户字符
     *
     * @param loginStr 用户字符
     */
    public void setLoginStr(String loginStr) {
        this.loginStr = loginStr;
    }

    /**
     * 获取密码字符
     *
     * @return password_str - 密码字符
     */
    public String getPasswordStr() {
        return passwordStr;
    }

    /**
     * 设置密码字符
     *
     * @param passwordStr 密码字符
     */
    public void setPasswordStr(String passwordStr) {
        this.passwordStr = passwordStr;
    }

    /**
     * 获取用户名
     *
     * @return login_eq - 用户名
     */
    public String getLoginEq() {
        return loginEq;
    }

    /**
     * 设置用户名
     *
     * @param loginEq 用户名
     */
    public void setLoginEq(String loginEq) {
        this.loginEq = loginEq;
    }

    /**
     * 获取密码
     *
     * @return pass_eq - 密码
     */
    public String getPassEq() {
        return passEq;
    }

    /**
     * 设置密码
     *
     * @param passEq 密码
     */
    public void setPassEq(String passEq) {
        this.passEq = passEq;
    }
}