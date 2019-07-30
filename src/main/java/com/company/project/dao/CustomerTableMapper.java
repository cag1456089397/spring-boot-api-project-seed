package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.CustomerTable;

import java.util.List;

public interface CustomerTableMapper extends Mapper<CustomerTable> {
    List<CustomerTable> selectUsers();
    List<CustomerTable> selectUsersByAll(CustomerTable customerTable);
    List<CustomerTable> selectUsersByAutoPut(CustomerTable customerTable);
    void updateUserByName(CustomerTable user);

    void addUser(CustomerTable user);

    int selectByWfIdAndElectricId(String wfId, String electricId);
    List<CustomerTable>  selectByTwo(String wfId, String electricId);

	//---------------------------------------------------------------------------
	List queryByUserIp(String userIp);
	boolean addCustomerTable(CustomerTable customerTable);
    boolean updateCustomerTable(CustomerTable customerTable);
    boolean deleteCustomerTable(CustomerTable customerTable);
    List queryCustomerTable(CustomerTable customerTable);
}