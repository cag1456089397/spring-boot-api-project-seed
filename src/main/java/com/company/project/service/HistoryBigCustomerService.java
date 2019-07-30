package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.HistoryBigCustomer;
import com.company.project.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/04/28.
 */
public interface HistoryBigCustomerService extends Service<HistoryBigCustomer> {
public boolean add(HistoryBigCustomer historyBigCustomer);
public List list(HistoryBigCustomer historyBigCustomer);

}
