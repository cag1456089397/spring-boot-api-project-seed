package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.BigCustomer;

import java.util.List;

public interface BigCustomerMapper extends Mapper<BigCustomer> {
    public List queryByBigCustomer(BigCustomer bigCustomer);
    boolean addByBigCustomer(BigCustomer bigCustomer);
    boolean updateByBigCustomer(BigCustomer bigCustomer);
    boolean deleteByBigCustomer(BigCustomer bigCustomer);

}