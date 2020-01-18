package com.company.consumer.impl.dao;


import java.sql.Types;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.company.consumer.contract.dao.UserDao;
import com.company.consumer.impl.AbstractDao;
import com.company.consumer.impl.rowMapper.UserRowMapper;
import com.company.model.bean.Reponse;
import com.company.model.bean.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserDaoImpl extends AbstractDao implements UserDao{
	
	public Reponse registerUser(User user) {

	    String sql = "INSERT INTO user_account (pseudo, email, password, role) "
	    		+ "VALUES (:pseudo, :email, :password, :role);";
	    String hashedPass = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("pseudo", user.getPseudo(), Types.VARCHAR);
        params.addValue("email", user.getEmail(), Types.VARCHAR);
        params.addValue("password", hashedPass, Types.VARCHAR);
        params.addValue("role", user.getRole(), Types.VARCHAR);

        
        Reponse reponse;
        try {
        	getNamedParameterJdbcTemplate().update(sql, params);
        	reponse = new Reponse(false, "registration success");
        }catch(DataAccessException dae) {
        	reponse = new Reponse(true, "registration failed");
        }
        
        return reponse;
	}
	
	public List<User> getListUsers() {
	/*	String sql = "SELECT * FROM users";

         RowMapper<User> rm = new UserRowMapper();

        List<User> users = getJdbcTemplate().query(sql, rm);*/

        return null;
	}
	
    public User updateUser(User user) {
        String sql = "UPDATE user_account SET pseudo = :user_pseudo, email = :user_email, password = :user_password,  date = now() WHERE user_account.id = :user_id;";

        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_id", user.getId(), Types.INTEGER);
        args.addValue("user_pseudo", user.getPseudo(), Types.VARCHAR);
        args.addValue("user_email", user.getEmail(), Types.VARCHAR);
        args.addValue("user_password", hashed, Types.VARCHAR);

        Reponse reponse;
        try {
        	getNamedParameterJdbcTemplate().update(sql, args);

            return getUserById(user.getId());
        }catch(DataAccessException dae) {
        	 return null;
        }     
    }

    public Reponse deleteUser(User user) {
        String sql = "DELETE FROM user_account WHERE user_account.id = :user_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_id", user.getId(), Types.INTEGER);

        Reponse reponse;
        try {
        	getNamedParameterJdbcTemplate().update(sql, args);
        	reponse = new Reponse(false, "delete User success");
        }catch(DataAccessException dae) {
        	reponse = new Reponse(true, "delete User failed");
        }
        
        return reponse;
    }
    
    public Boolean checkExistingUserEmail(User user) {
    	 String sql = "SELECT user_account.id FROM user_account WHERE email = :user_login";

         MapSqlParameterSource args = new MapSqlParameterSource();
         args.addValue("user_login", user.getEmail(), Types.VARCHAR);
         
         RowMapper<User> rm = new UserRowMapper();
         
         Boolean result = false;
         try {
             User userChecked = getNamedParameterJdbcTemplate().queryForObject(sql, args, rm);
            
             if (userChecked.getId()>-1)
                 return true;

         } catch (EmptyResultDataAccessException exception) {
         }		
         
    	return result;
    }
    
    public User getUserById(int id) {
    	String sql = "SELECT * FROM user_account WHERE user_account.id = :user_id";
    	
    	MapSqlParameterSource args  = new MapSqlParameterSource();
    	args.addValue("user_id", id, Types.INTEGER);
    	
    	RowMapper<User> rm = new UserRowMapper();
    	
    	try {
    		User user = getNamedParameterJdbcTemplate().queryForObject(sql, args, rm);
    		return user;
    	}catch(EmptyResultDataAccessException exception) {
    		return null;
    	}
    }
	
	public User logIn(User user) {
        String sql = "SELECT * FROM user_account WHERE email = :user_login";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_login", user.getEmail(), Types.VARCHAR);

        RowMapper<User> rm = new UserRowMapper();

        try {
            User userLogged = getNamedParameterJdbcTemplate().queryForObject(sql, args, rm);

            if (BCrypt.checkpw(user.getPassword(), userLogged.getPassword())) {

                return userLogged;
            }
            else {
                return null;
            }

        } catch (EmptyResultDataAccessException exception) {
            return null;
        }		
	}

}
