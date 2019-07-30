package com.company.project.service;
import com.company.project.model.HistoryBigCustomer;
import com.company.project.model.HistoryEquipTable;
import com.company.project.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/04/26.
 */
public interface HistoryEquipTableService extends Service<HistoryEquipTable> {

    public List list(HistoryEquipTable historyEquipTable);

    public boolean add(HistoryEquipTable historyEquipTable);

}
