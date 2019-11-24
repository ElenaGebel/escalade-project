package com.company.consumer.impl.dao;


import java.sql.Types;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.company.consumer.contract.dao.UserDao;
import com.company.consumer.impl.AbstractDao;
import com.company.model.bean.User;

public class UserDaoImpl extends AbstractDao implements UserDao{
	
	public void registerUser(User user) {

	    String sql = "INSERT INTO users (nickname, email, password) VALUES (:nickname, :email, :password);";

 

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nickname", user.getNickname(), Types.VARCHAR);
        params.addValue("email", user.getEmail(), Types.VARCHAR);
        params.addValue("password", user.getPassword(), Types.VARCHAR);
       // params.addValue("role", user.getRole(), Types.VARCHAR);

        getNamedParameterJdbcTemplate().update(sql, params);

	}
	
	public List<User> getListUsers() {
		String sql = "SELECT * FROM users";

        RowMapper<User> rowMapper = new RowMapper<User>() {
            public User mapRow(ResultSet pRS, int pRowNum) throws SQLException {
            	User user = new User();
            	user.setNickname(pRS.getString("nickname"));
            	user.setEmail(pRS.getString("email"));
            	user.setPassword(pRS.getString("password"));
            	user.setRole(pRS.getString("role"));
                return user;
            }
        };

        List<User> users = getJdbcTemplate().query(sql, rowMapper);

        return users;
	}
	
	public void deleteUser(User user) {
		
	}
	
	public void updateUser(User user) {
		
	}
	
	public void logIn(User user) {
		
	}

}
