package com.company.project.service.impl;

import com.company.project.dao.BigCustomerMapper;
import com.company.project.model.BigCustomer;
import com.company.project.service.BigCustomerService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/04/09.
 */
@Service
@Transactional
public class BigCustomerServiceImpl extends AbstractService<BigCustomer> implements BigCustomerService {
    @Resource
    private BigCustomerMapper bigCustomerMapper;
    public List queryByBigCustomer(BigCustomer bigCustomer){

        return this.bigCustomerMapper.queryByBigCustomer(bigCustomer);

    }


    public  boolean addByBigCustomer(BigCustomer bigCustomer){
        return this.bigCustomerMapper.addByBigCustomer(bigCustomer);
    }

    public  boolean updateByBigCustomer(BigCustomer bigCustomer){
        return this.bigCustomerMapper.updateByBigCustomer(bigCustomer);
    }

    public  boolean deleteByBigCustomer(BigCustomer bigCustomer){
        return this.bigCustomerMapper.deleteByBigCustomer(bigCustomer);
    }

}
