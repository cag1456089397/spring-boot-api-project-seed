package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.HistoryBigCustomer;
import com.company.project.model.HistoryEquipTable;

import java.util.List;

public interface HistoryEquipTableMapper extends Mapper<HistoryEquipTable> {
    public List list(HistoryEquipTable historyEquipTable);
    public boolean add(HistoryEquipTable historyEquipTable);

}