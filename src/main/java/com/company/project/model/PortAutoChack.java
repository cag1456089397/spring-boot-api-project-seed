package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "port_auto_chack")
public class PortAutoChack {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 设备端口
     */
    @Column(name = "equip_port")
    private String equipPort;

    /**
     * 设备IP
     */
    @Column(name = "equip_ip")
    private String equipIp;

    /**
     * 端口收光
     */
    @Column(name = "equip_port_rt")
    private String equipPortRt;

    /**
     * 端口误码
     */
    @Column(name = "equip_port_err")
    private String equipPortErr;

    /**
     * 自检时间
     */
    @Column(name = "chack_time")
    private Date chackTime;

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
     * 获取设备端口
     *
     * @return equip_port - 设备端口
     */
    public String getEquipPort() {
        return equipPort;
    }

    /**
     * 设置设备端口
     *
     * @param equipPort 设备端口
     */
    public void setEquipPort(String equipPort) {
        this.equipPort = equipPort;
    }

    /**
     * 获取设备IP
     *
     * @return equip_ip - 设备IP
     */
    public String getEquipIp() {
        return equipIp;
    }

    /**
     * 设置设备IP
     *
     * @param equipIp 设备IP
     */
    public void setEquipIp(String equipIp) {
        this.equipIp = equipIp;
    }

    /**
     * 获取端口收光
     *
     * @return equip_port_rt - 端口收光
     */
    public String getEquipPortRt() {
        return equipPortRt;
    }

    /**
     * 设置端口收光
     *
     * @param equipPortRt 端口收光
     */
    public void setEquipPortRt(String equipPortRt) {
        this.equipPortRt = equipPortRt;
    }

    /**
     * 获取端口误码
     *
     * @return equip_port_err - 端口误码
     */
    public String getEquipPortErr() {
        return equipPortErr;
    }

    /**
     * 设置端口误码
     *
     * @param equipPortErr 端口误码
     */
    public void setEquipPortErr(String equipPortErr) {
        this.equipPortErr = equipPortErr;
    }

    /**
     * 获取自检时间
     *
     * @return chack_time - 自检时间
     */
    public Date getChackTime() {
        return chackTime;
    }

    /**
     * 设置自检时间
     *
     * @param chackTime 自检时间
     */
    public void setChackTime(Date chackTime) {
        this.chackTime = chackTime;
    }
}