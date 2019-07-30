package com.company.project.service.impl;

import com.company.project.dao.SixWMapper;
import com.company.project.model.SixW;
import com.company.project.service.SixWService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/03/07.
 */
@Service
@Transactional
public class SixWServiceImpl extends AbstractService<SixW> implements SixWService {
    @Resource
    private SixWMapper sixWMapper;

}
