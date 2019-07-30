package com.company.project.service.impl;

import com.company.project.dao.EquipTableMapper;
import com.company.project.model.EquipTable;
import com.company.project.service.EquipTableService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/03/07.
 */
@Service
@Transactional
public class EquipTableServiceImpl extends AbstractService<EquipTable> implements EquipTableService {
    @Resource
    private EquipTableMapper equipTableMapper;

	//-----------------------------------------------------------------------------------------------------------------
	 @Override
    public List queryByManageIp(String manageIp) {
        return this.equipTableMapper.queryByManageIp(manageIp);
    }

    public boolean updateEquip(EquipTable equipTable){
        return this.equipTableMapper.updateEquip(equipTable);
    }

    public boolean deleteEquip(EquipTable equipTable){
        return this.equipTableMapper.deleteEquip(equipTable);
    }

    public boolean addEquip(EquipTable equipTable){
        return this.equipTableMapper.addEquip(equipTable);
    }

    public List queryEquip(EquipTable equipTable){
	     return  this.equipTableMapper.queryEquip(equipTable);
    }

}
