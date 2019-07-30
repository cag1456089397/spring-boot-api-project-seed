package com.company.project.service;
import com.company.project.model.BigCustomer;
import com.company.project.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/04/09.
 */
public interface BigCustomerService extends Service<BigCustomer> {
    public List queryByBigCustomer(BigCustomer bigCustomer);
    public  boolean addByBigCustomer(BigCustomer bigCustomer);
    public  boolean deleteByBigCustomer(BigCustomer bigCustomer);
    public  boolean updateByBigCustomer(BigCustomer bigCustomer);


}
