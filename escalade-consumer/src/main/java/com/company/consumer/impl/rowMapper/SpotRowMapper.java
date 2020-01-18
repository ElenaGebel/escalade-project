package com.company.consumer.impl.rowMapper;

import org.springframework.jdbc.core.RowMapper;

import com.company.model.bean.Spot;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpotRowMapper implements RowMapper<Spot> {
    public Spot mapRow(ResultSet rs, int rowNum) throws SQLException {
        Spot spot = new Spot();

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            if (rs.getMetaData().getColumnName(i).equals("id")) 
            	spot.setId(rs.getInt("id"));
            else if (rs.getMetaData().getColumnName(i).equals("topo_id")) 
            	spot.setTopoId(rs.getInt("topo_id"));
            else if (rs.getMetaData().getColumnName(i).equals("user_id")) 
            	spot.setUserId(rs.getInt("user_id"));
            else if (rs.getMetaData().getColumnName(i).equals("name")) 
            	spot.setName(rs.getString("name"));
            else if (rs.getMetaData().getColumnName(i).equals("description")) 
            	spot.setDescription(rs.getString("description"));
            else if (rs.getMetaData().getColumnName(i).equals("image")) 
            	spot.setImage(rs.getString("image"));
        }
        return spot;
    }
}






