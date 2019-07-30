package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.HistoryCustomerTable;

import java.util.List;

public interface HistoryCustomerTableMapper extends Mapper<HistoryCustomerTable> {
    public List list(HistoryCustomerTable historyCustomerTable);
    public boolean add(HistoryCustomerTable historyCustomerTable);
}