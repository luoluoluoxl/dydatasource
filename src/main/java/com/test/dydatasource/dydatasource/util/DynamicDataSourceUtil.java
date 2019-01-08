package com.test.dydatasource.dydatasource.util;

import com.test.dydatasource.dydatasource.mapper.CBSProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 罗项令
 * @DATE 2019/1/3  17:01.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
public class DynamicDataSourceUtil {
    @Autowired
    DataSourceConn conn ;
    public Map<String, DataSource> buildDataSources() {
        HashMap<String, DataSource> dataSourceMap = new HashMap<>();

        DataSource source = conn.getDataSource();
         SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(source);

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from cbs_project");
        for (Map<String, Object> map : maps) {
            DataSourceConn dataSourceConn = new DataSourceConn();
            DataSource dataSource = dataSourceConn.getDataSource(map);
            if (dataSource != null) {
                dataSourceMap.put((String)map.get("projectId"), dataSource);
            }
        }
        return dataSourceMap;
    }
}
