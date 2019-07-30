package com.company.project.service;
import com.company.project.model.EquipTable;
import com.company.project.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/03/07.
 */
public interface EquipTableService extends Service<EquipTable> {

	//-------------------------------------------------------------------------------------------------
	 List queryByManageIp(String manageIp);
    boolean updateEquip(EquipTable equipTable);
    boolean deleteEquip(EquipTable equipTable);
    boolean addEquip(EquipTable equipTable);
    List queryEquip(EquipTable equipTable);

}
