package com.company.project.service;
import com.company.project.model.EqOrder;
import com.company.project.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/05/14.
 */
public interface EqOrderService extends Service<EqOrder> {
    List<EqOrder> selectByTwo(String equipType, String orderType);
}
