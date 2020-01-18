package com.company.consumer.impl.rowMapper;
import org.springframework.jdbc.core.RowMapper;

import com.company.model.bean.Quotation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuotationRowMapper implements RowMapper<Quotation> {
	
    public Quotation mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Quotation quotation = new Quotation();

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
        	
        	 if (rs.getMetaData().getColumnName(i).equals("id")) quotation.setId(rs.getInt("id"));
             else if (rs.getMetaData().getColumnName(i).equals("name")) quotation.setName(rs.getString("name"));

        }
        return quotation;
    }
}