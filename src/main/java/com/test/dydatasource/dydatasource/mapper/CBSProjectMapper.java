package com.test.dydatasource.dydatasource.mapper;

import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 罗项令
 * @DATE 2019/1/3  16:58.
 */
@Mapper
public interface CBSProjectMapper {
    @Select("select * from cbs_project")
    List<Map<String,String>> selectProject();
}
