package com.company.consumer.impl.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.company.model.bean.Topo;

public class TopoRowMapper implements RowMapper<Topo>{
	
	public Topo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Topo topo = new Topo();
		
		for(int i=1; i< rs.getMetaData().getColumnCount(); i++) {
			
			if (rs.getMetaData().getColumnName(i).equals("id")) 
				topo.setId(rs.getInt("id"));
			else if (rs.getMetaData().getColumnName(i).equals("user_id")) 
				topo.setUserId(rs.getInt("user_id"));
			else if (rs.getMetaData().getColumnName(i).equals("name")) 
				topo.setName(rs.getString("name"));
			else if(rs.getMetaData().getColumnName(i).equals("description"))
				topo.setDescription(rs.getString("description"));
			else if(rs.getMetaData().getColumnName(i).equals("image"))
				topo.setImage(rs.getString("image"));
			else if(rs.getMetaData().getColumnName(i).equals("reserved"))
				topo.setReserved(rs.getBoolean("reserved"));
			else if(rs.getMetaData().getColumnName(i).equals("date_reservation"))
				topo.setReservationDate(rs.getDate("date_reservation"));
			else if (rs.getMetaData().getColumnName(i).equals("user_reserved_id")) 
				topo.setUserReservedId(rs.getInt("user_reserved_id"));
			else if (rs.getMetaData().getColumnName(i).equals("status_reservation")) 
				topo.setStatusReservation(rs.getInt("status_reservation"));
		/*	else if(rs.getMetaData().getColumnName(i).equals("date_publication"))
				topo.setDate(rs.getTimestamp("date_publication"));*/
				
		}
		return topo;
		
	}

}
