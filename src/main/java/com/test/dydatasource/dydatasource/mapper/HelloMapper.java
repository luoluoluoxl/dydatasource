package com.test.dydatasource.dydatasource.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface HelloMapper {
    @Select("select * from t_main_fee")
    List<Map> selectAll();
}