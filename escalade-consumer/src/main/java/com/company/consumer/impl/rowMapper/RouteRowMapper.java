package com.company.consumer.impl.rowMapper;
import org.springframework.jdbc.core.RowMapper;

import com.company.model.bean.Route;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteRowMapper implements RowMapper<Route> {
    public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
        Route route = new Route();

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
        	
        	 if (rs.getMetaData().getColumnName(i).equals("id")) route.setId(rs.getInt("id"));
        	 else if (rs.getMetaData().getColumnName(i).equals("parent_id")) route.setParentId(rs.getInt("parent_id"));
             else if (rs.getMetaData().getColumnName(i).equals("secteur_id")) route.setSectorId(rs.getInt("secteur_id"));
             else if (rs.getMetaData().getColumnName(i).equals("user_id")) route.setUserId(rs.getInt("user_id"));
             else if (rs.getMetaData().getColumnName(i).equals("name")) route.setName(rs.getString("name"));
             else if (rs.getMetaData().getColumnName(i).equals("description")) route.setDescription(rs.getString("description"));
       
            else if (rs.getMetaData().getColumnName(i).equals("height")) route.setHeight(rs.getInt("height"));
            else if (rs.getMetaData().getColumnName(i).equals("quotation")) route.setQuotation(rs.getInt("quotation"));
            else if (rs.getMetaData().getColumnName(i).equals("laititude")) route.setLaititude(rs.getString("laititude"));
            else if (rs.getMetaData().getColumnName(i).equals("longitude")) route.setLongitude(rs.getString("longitude"));
            else if (rs.getMetaData().getColumnName(i).equals("points_num")) route.setPointsNum(rs.getInt("points_num"));
            else if (rs.getMetaData().getColumnName(i).equals("type")) route.setType(rs.getString("type"));
        }
        return route;
    }
}