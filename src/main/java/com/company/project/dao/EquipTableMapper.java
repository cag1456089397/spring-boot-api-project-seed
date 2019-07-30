package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.EquipTable;

import java.util.List;

public interface EquipTableMapper extends Mapper<EquipTable> {

	//--------------------------------------------------------------------------
	List queryByManageIp(String manageIp);
    boolean updateEquip(EquipTable equipTable);
    boolean deleteEquip(EquipTable equipTable);
    boolean addEquip(EquipTable equipTable);
    List queryEquip(EquipTable equipTable);

}