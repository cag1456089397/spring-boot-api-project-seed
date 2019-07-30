package com.company.project.service;

import com.company.project.model.IpAddressNew;
import com.company.project.core.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2019/01/28.
 */
public interface IpAddressNewService extends Service<IpAddressNew> {

    int insertIpAddressNewByFindAll(IpAddressNew ipAddressNew);

    int deleteIpAddressNewByFindAll(IpAddressNew ipAddressNew);

    int updateIpAddressNewByFindAll(IpAddressNew ipAddressNew);

    int splitIp(String beginIp);

    int togetherIp(String beginIp);
    List<IpAddressNew> selectIpAddressNew();
    int selectIpAddressNew1();
    List<IpAddressNew> selectIpAddressNewByBeginIp(String beginIp);
    List<IpAddressNew> selectIpAddressNewById(int id);

    List<IpAddressNew> selectIpAddressNewByEndIp(String endIp);

    List<IpAddressNew> selectIpAddressNewByRangeIp(IpAddressNew ipAddressNew);

    List<IpAddressNew> selectIpAddressNewByIpNum(int ipNum);
    //业务开通——分配资源
    List<IpAddressNew> selectIpAddressNewByBuOpen(IpAddressNew ipAddressNew);
    //错误信息更正
    int updateIpAddressNewByErData(IpAddressNew ipAddressNew);
    //业务开通——分配资源
    List<IpAddressNew> selectIpAddressNewByLookFor(IpAddressNew ipAddressNew);

    int autoSplitIp(IpAddressNew ipAddressNew);

    int autoTogetherIp(IpAddressNew ipAddressNew);


    boolean batchImport(String fileName, MultipartFile file) throws Exception;

	//------------------------------------------------------------------------------------------------------------------------

	public List queryByBeginIp(String beginIp);
    public List query(String beginIp);
    public List combine();
    public boolean updateTable(IpAddressNew ipAddressNew);
    public boolean deleteTable(String beginIp);
    public boolean addTable(IpAddressNew ipAddressNew);
    public List<Map<String, Object>> ipUseInfo();
   // public List ipUseInfo();
}
