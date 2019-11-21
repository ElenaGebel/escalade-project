package com.company.consumer.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class AbstractDao {
	
	private static DataSource dataSource;
	
    public static void setDataSource(DataSource dataSource) {
    	AbstractDao.dataSource = dataSource;
    }
    

    public static JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

}
