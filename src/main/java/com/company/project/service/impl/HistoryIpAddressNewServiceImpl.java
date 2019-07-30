package com.company.project.service.impl;

import com.company.project.dao.HistoryIpAddressNewMapper;
import com.company.project.model.HistoryIpAddressNew;
import com.company.project.service.HistoryIpAddressNewService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/04/26.
 */
@Service
@Transactional
public class HistoryIpAddressNewServiceImpl extends AbstractService<HistoryIpAddressNew> implements HistoryIpAddressNewService {
    @Resource
    private HistoryIpAddressNewMapper historyIpAddressNewMapper;

}
