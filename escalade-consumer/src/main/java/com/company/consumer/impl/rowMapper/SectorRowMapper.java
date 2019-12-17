package com.company.consumer.impl.rowMapper;

import org.springframework.jdbc.core.RowMapper;

import com.company.model.bean.Sector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectorRowMapper implements RowMapper<Sector> {
    public Sector mapRow(ResultSet rs, int rowNum) throws SQLException {
        Sector sector = new Sector();

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
        	if (rs.getMetaData().getColumnName(i).equals("id")) sector.setId(rs.getInt("id"));
            else if (rs.getMetaData().getColumnName(i).equals("spot_id")) sector.setSpotId(rs.getInt("spot_id"));
            else if (rs.getMetaData().getColumnName(i).equals("user_id")) sector.setUserId(rs.getInt("user_id"));
        	else if (rs.getMetaData().getColumnName(i).equals("name")) sector.setName(rs.getString("name"));
        	else if (rs.getMetaData().getColumnName(i).equals("description")) sector.setDescription(rs.getString("description"));

        }

        return sector;
    }
}