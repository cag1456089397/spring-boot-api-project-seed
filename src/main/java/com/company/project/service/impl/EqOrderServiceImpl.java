package com.company.project.service.impl;

import com.company.project.dao.EqOrderMapper;
import com.company.project.model.EqOrder;
import com.company.project.service.EqOrderService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/05/14.
 */
@Service
@Transactional
public class EqOrderServiceImpl extends AbstractService<EqOrder> implements EqOrderService {
    @Resource
    private EqOrderMapper eqOrderMapper;

    public List<EqOrder> selectByTwo(String equipType, String orderType){
        return  this.eqOrderMapper.selectByTwo(equipType,orderType);
    }

}
