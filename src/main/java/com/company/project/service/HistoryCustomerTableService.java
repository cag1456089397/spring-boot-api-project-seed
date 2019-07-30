package com.company.project.service;
import com.company.project.model.HistoryCustomerTable;
import com.company.project.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/04/29.
 */
public interface HistoryCustomerTableService extends Service<HistoryCustomerTable> {
    public List list(HistoryCustomerTable historyCustomerTable);
    public boolean add(HistoryCustomerTable historyCustomerTable);

}
