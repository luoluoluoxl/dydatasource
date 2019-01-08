package com.test.dydatasource.dydatasource.base;

import com.test.dydatasource.dydatasource.util.DynamicDataSourceUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Autowired

    private Environment env;


    @Bean("mysql") // bean的名称
    @ConfigurationProperties(prefix = "spring.datasource") // application.properteis中对应属性的前缀
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

   

    @Primary
    @Bean("dataSource")
    public DynamicDataSource dataSource(Map<String,DataSource> dataSources) {

        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        Map targetDataSources = new HashMap<>();
        for (Map.Entry<String, DataSource> stringDataSourceEntry : dataSources.entrySet()) {
            targetDataSources.put(stringDataSourceEntry.getKey(),stringDataSourceEntry.getValue());
        }
        dynamicDataSource.setTargetDataSources(targetDataSources);

        // 设置默认的数据源

        dynamicDataSource.setDefaultTargetDataSource(mysqlDataSource());

        return dynamicDataSource;

    }

   @Autowired
    DynamicDataSourceUtil dynamicDataSourceUtil;

    @Primary
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        Map<String,DataSource> dataSources = dynamicDataSourceUtil.buildDataSources();
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(this.dataSource(dataSources));
        SqlSessionFactory factory = sessionFactory.getObject();
        return factory;

    }

   

    @Bean("transactionManager")

    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DynamicDataSource dataSource) throws Exception {

       return new DataSourceTransactionManager(dataSource);

    }

}