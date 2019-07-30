package com.company.project.service.impl;

import com.company.project.dao.HistoryCustomerTableMapper;
import com.company.project.model.HistoryCustomerTable;
import com.company.project.service.HistoryCustomerTableService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/04/29.
 */
@Service
@Transactional
public class HistoryCustomerTableServiceImpl extends AbstractService<HistoryCustomerTable> implements HistoryCustomerTableService {
    @Resource
    private HistoryCustomerTableMapper historyCustomerTableMapper;
    public List list(HistoryCustomerTable historyCustomerTable){
        return  this.historyCustomerTableMapper.list(historyCustomerTable);
    }

    public boolean add(HistoryCustomerTable historyCustomerTable){
        return this.historyCustomerTableMapper.add(historyCustomerTable);
    }

}
