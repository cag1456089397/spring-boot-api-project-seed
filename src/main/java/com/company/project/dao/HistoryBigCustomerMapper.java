package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.core.Result;
import com.company.project.model.HistoryBigCustomer;

import java.util.List;

public interface HistoryBigCustomerMapper extends Mapper<HistoryBigCustomer> {
    public boolean add(HistoryBigCustomer historyBigCustomer);
    public List list(HistoryBigCustomer historyBigCustomer);
}