package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.EqOrder;

import java.util.List;

public interface EqOrderMapper extends Mapper<EqOrder> {
    List<EqOrder> selectByTwo(String equipType, String orderType);
}