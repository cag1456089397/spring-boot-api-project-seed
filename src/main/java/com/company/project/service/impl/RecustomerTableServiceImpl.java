package com.company.project.service.impl;

import com.company.project.dao.CustomerTableMapper;
import com.company.project.dao.RecustomerTableMapper;

import com.company.project.model.RecustomerTable;
import com.company.project.service.RecustomerTableService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/02/21.
 */
@Service
@Transactional
public class RecustomerTableServiceImpl extends AbstractService<RecustomerTable> implements RecustomerTableService {
    @Resource
    private RecustomerTableMapper recustomerTableMapper;
    @Resource
    private CustomerTableMapper customerTableMapper;

    public List<RecustomerTable> selectUsers() {
        return recustomerTableMapper.selectUsers();
    }


    public int insertByRebackUser(RecustomerTable recustomerTable) {
        return recustomerTableMapper.insertByRebackUser(recustomerTable);
    }



}
