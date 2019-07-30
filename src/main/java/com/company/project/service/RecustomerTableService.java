package com.company.project.service;

import com.company.project.model.RecustomerTable;
import com.company.project.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/02/21.
 */
public interface RecustomerTableService extends Service<RecustomerTable> {
    List<RecustomerTable> selectUsers();

    int insertByRebackUser(RecustomerTable recustomerTable);
}
