package com.company.project.service;
import com.company.project.model.CustomerTable;
import com.company.project.core.Service;
import com.company.project.model.IpAddressNew;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/02/19.
 */
public interface CustomerTableService extends Service<CustomerTable> {

    List<CustomerTable> selectUsers();
    List<CustomerTable> selectUsersByAll(CustomerTable customerTable);
    List<CustomerTable> selectUsersByAutoPut(CustomerTable customerTable);
    boolean batchImport1(String fileName, MultipartFile file) throws Exception;

	//--------------------------------------------------------------------------------------
	  List queryByUserIp(String userIp);
	  boolean addCustomerTable(CustomerTable customerTable);
	  boolean deleteCustomerTable(CustomerTable customerTable);
	  boolean updateCustomerTable(CustomerTable customerTable);
	  List queryCustomerTable(CustomerTable customerTable);

}
