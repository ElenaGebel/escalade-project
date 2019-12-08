package com.company.consumer.impl.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.company.model.bean.User;

public class UserRowMapper implements RowMapper<User>{
	
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		
		for(int i=1; i< rs.getMetaData().getColumnCount(); i++) {
			
			if (rs.getMetaData().getColumnName(i).equals("id")) 
				user.setId(rs.getInt("id"));
			else if(rs.getMetaData().getColumnName(i).equals("pseudo"))
				user.setPseudo(rs.getString("pseudo"));
			else if(rs.getMetaData().getColumnName(i).equals("email"))
				user.setEmail(rs.getString("email"));
			else if(rs.getMetaData().getColumnName(i).equals("password"))
				user.setPassword(rs.getString("password"));
			else if(rs.getMetaData().getColumnName(i).equals("role"))
				user.setRole(rs.getString("role"));
		}
		
		return user;
		
	}

}
