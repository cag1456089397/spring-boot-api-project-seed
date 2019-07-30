package com.company.project.service.impl;

import com.company.project.dao.PortAutoChackMapper;
import com.company.project.model.PortAutoChack;
import com.company.project.service.PortAutoChackService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/07/22.
 */
@Service
@Transactional
public class PortAutoChackServiceImpl extends AbstractService<PortAutoChack> implements PortAutoChackService {
    @Resource
    private PortAutoChackMapper portAutoChackMapper;

}
