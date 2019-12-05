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
import com.company.model.bean.Reponse;
import com.company.model.bean.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserDaoImpl extends AbstractDao implements UserDao{
	
	public Reponse registerUser(User user) {

	    String sql = "INSERT INTO user_account (pseudo, email, password, role) VALUES (:pseudo, :email, :password, :role);";
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

        RowMapper<User> rowMapper = new RowMapper<User>() {
            public User mapRow(ResultSet pRS, int pRowNum) throws SQLException {
            	User user = new User();
            	user.setPseudo(pRS.getString("pseudo"));
            	user.setEmail(pRS.getString("email"));
            	user.setPassword(pRS.getString("password"));
            	user.setRole(pRS.getString("role"));
                return user;
            }
        };

        List<User> users = getJdbcTemplate().query(sql, rowMapper);*/

        return null;
	}
	
    public Reponse updateUser(User user) {
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
        	reponse = new Reponse(false, "update User success");
        }catch(DataAccessException dae) {
        	reponse = new Reponse(true, "update User failed");
        }
        return reponse;
    }

    public Reponse deleteUser(User user) {
        String sql = "UPDATE publication SET user_account_id = :user_robot WHERE user_account_id = :user_id;" +
                "UPDATE comment SET user_account_id = :user_robot WHERE user_account_id = :user_id;" +
                "DELETE FROM user_has_topo WHERE user_id = :user_id;" +
                "DELETE FROM user_account WHERE user_account.id = :user_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_id", user.getId(), Types.INTEGER);
        args.addValue("user_robot", 0, Types.INTEGER);

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

         RowMapper<User> rowMapper = new RowMapper<User>() {
             public User mapRow(ResultSet pRS, int pRowNum) throws SQLException {
             	User user = new User();
             	user.setId(pRS.getInt("id"));
                return user;
             }
         };
         
         Boolean result = false;
         try {
             User userChecked = getNamedParameterJdbcTemplate().queryForObject(sql, args, rowMapper);
            
             if (userChecked.getId()>-1)
                 return true;

         } catch (EmptyResultDataAccessException exception) {
         }		
         
    	return result;
    }
	
	public User logIn(User user) {
        String sql = "SELECT * FROM user_account WHERE email = :user_login";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_login", user.getEmail(), Types.VARCHAR);

        RowMapper<User> rowMapper = new RowMapper<User>() {
            public User mapRow(ResultSet pRS, int pRowNum) throws SQLException {
            	User user = new User();
            	user.setId(pRS.getInt("id"));
            	user.setPseudo(pRS.getString("pseudo"));
            	user.setEmail(pRS.getString("email"));
            	user.setPassword(pRS.getString("password"));
            	user.setRole(pRS.getString("role"));
                return user;
            }
        };

        try {
            User userLogged = getNamedParameterJdbcTemplate().queryForObject(sql, args, rowMapper);
           
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
