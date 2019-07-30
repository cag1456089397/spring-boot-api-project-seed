package com.company.project.service.impl;

import com.company.project.dao.HistoryEquipTableMapper;
import com.company.project.model.HistoryEquipTable;
import com.company.project.service.HistoryEquipTableService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/04/26.
 */
@Service
@Transactional
public class HistoryEquipTableServiceImpl extends AbstractService<HistoryEquipTable> implements HistoryEquipTableService {
    @Resource
    private HistoryEquipTableMapper historyEquipTableMapper;

    public List list(HistoryEquipTable historyEquipTable){
        return this.historyEquipTableMapper.list(historyEquipTable);
    }

    public boolean add(HistoryEquipTable historyEquipTable)
    {
        return  this.historyEquipTableMapper.add(historyEquipTable);
    }

}
