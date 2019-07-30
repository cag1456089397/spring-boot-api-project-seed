package com.company.project.model;

import javax.persistence.*;

@Table(name = "base_table")
public class BaseTable {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 设备类型
     */
    @Column(name = "ip_type")
    private String ipType;

    /**
     * 用户名
     */
    @Column(name = "ip_user")
    private String ipUser;

    /**
     * 密码
     */
    @Column(name = "ip_pass")
    private String ipPass;

    /**
     * 统计所有端口
     */
    @Column(name = "port_all")
    private String portAll;

    /**
     * 端口收光
     */
    @Column(name = "port_rx")
    private String portRx;

    /**
     * 端口误包
     */
    @Column(name = "port_crc")
    private String portCrc;

    @Column(name = "ip_factory")
    private String ipFactory;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取ip地址
     *
     * @return ip - ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置ip地址
     *
     * @param ip ip地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取设备类型
     *
     * @return ip_type - 设备类型
     */
    public String getIpType() {
        return ipType;
    }

    /**
     * 设置设备类型
     *
     * @param ipType 设备类型
     */
    public void setIpType(String ipType) {
        this.ipType = ipType;
    }

    /**
     * 获取用户名
     *
     * @return ip_user - 用户名
     */
    public String getIpUser() {
        return ipUser;
    }

    /**
     * 设置用户名
     *
     * @param ipUser 用户名
     */
    public void setIpUser(String ipUser) {
        this.ipUser = ipUser;
    }

    /**
     * 获取密码
     *
     * @return ip_pass - 密码
     */
    public String getIpPass() {
        return ipPass;
    }

    /**
     * 设置密码
     *
     * @param ipPass 密码
     */
    public void setIpPass(String ipPass) {
        this.ipPass = ipPass;
    }

    /**
     * 获取统计所有端口
     *
     * @return port_all - 统计所有端口
     */
    public String getPortAll() {
        return portAll;
    }

    /**
     * 设置统计所有端口
     *
     * @param portAll 统计所有端口
     */
    public void setPortAll(String portAll) {
        this.portAll = portAll;
    }

    /**
     * 获取端口收光
     *
     * @return port_rx - 端口收光
     */
    public String getPortRx() {
        return portRx;
    }

    /**
     * 设置端口收光
     *
     * @param portRx 端口收光
     */
    public void setPortRx(String portRx) {
        this.portRx = portRx;
    }

    /**
     * 获取端口误包
     *
     * @return port_crc - 端口误包
     */
    public String getPortCrc() {
        return portCrc;
    }

    /**
     * 设置端口误包
     *
     * @param portCrc 端口误包
     */
    public void setPortCrc(String portCrc) {
        this.portCrc = portCrc;
    }

    /**
     * @return ip_factory
     */
    public String getIpFactory() {
        return ipFactory;
    }

    /**
     * @param ipFactory
     */
    public void setIpFactory(String ipFactory) {
        this.ipFactory = ipFactory;
    }
}