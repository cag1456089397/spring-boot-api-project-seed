package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.RecustomerTable;

import java.util.List;

public interface RecustomerTableMapper extends Mapper<RecustomerTable> {
    List<RecustomerTable> selectUsers();
    int insertByRebackUser(RecustomerTable recustomerTable);
}