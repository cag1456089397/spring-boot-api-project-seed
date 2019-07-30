package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.dao.HistoryBigCustomerMapper;
import com.company.project.model.HistoryBigCustomer;
import com.company.project.model.HistoryIpAddressNew;
import com.company.project.service.HistoryBigCustomerService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/04/28.
 */
@Service
@Transactional
public class HistoryBigCustomerServiceImpl extends AbstractService<HistoryBigCustomer> implements HistoryBigCustomerService {
    @Resource
    private HistoryBigCustomerMapper historyBigCustomerMapper;
    public boolean add(HistoryBigCustomer historyBigCustomer){
        return  this.historyBigCustomerMapper.add(historyBigCustomer);
    }

    public List list(HistoryBigCustomer historyBigCustomer){
        return  this.historyBigCustomerMapper.list(historyBigCustomer);
    }


}
