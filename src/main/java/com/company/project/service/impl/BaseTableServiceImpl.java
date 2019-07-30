package com.company.project.service.impl;

import com.company.project.dao.BaseTableMapper;
import com.company.project.model.BaseTable;
import com.company.project.service.BaseTableService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/07/22.
 */
@Service
@Transactional
public class BaseTableServiceImpl extends AbstractService<BaseTable> implements BaseTableService {
    @Resource
    private BaseTableMapper baseTableMapper;

}
