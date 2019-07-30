package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.IpAddressNew;


import java.util.List;
import java.util.Map;


public interface IpAddressNewMapper extends Mapper<IpAddressNew> {



    int insertIpAddressNewByFindAll(IpAddressNew ipAddressNew);
    int deleteIpAddressNewByFindAll(IpAddressNew ipAddressNew);
    int updateIpAddressNewByFindAll(IpAddressNew ipAddressNew);
    int selectIpAddressNew1();
    List<IpAddressNew> selectIpAddressNewById(int id);
    List<IpAddressNew> selectIpAddressNewByBeginIp(String beginIp);
    List<IpAddressNew> selectIpAddressNewByEndIp(String endIp);
    List<IpAddressNew> selectIpAddressNewByRangeIp(IpAddressNew ipAddressNew);
    List<IpAddressNew> selectIpAddressNewByIpNum(int ipNum);
    List<IpAddressNew> selectIpAddressNew();
    //业务开通——分配资源
    List<IpAddressNew> selectIpAddressNewByBuOpen(IpAddressNew ipAddressNew);
    //业务开通——分配资源
    List<IpAddressNew> selectIpAddressNewByLookFor(IpAddressNew ipAddressNew);

	//--------------------------------------------------------------------------------------------
	 List queryByBeginIp(String beginIp);
    List query(String beginIp);
    List combine();
    boolean updateTable(IpAddressNew ipAddressNew);
    boolean addTable(IpAddressNew ipAddressNew);
    boolean deleteTable(String beginIp);
    List<Map<String, Object>> ipUseInfo();
    //List ipUseInfo();
}