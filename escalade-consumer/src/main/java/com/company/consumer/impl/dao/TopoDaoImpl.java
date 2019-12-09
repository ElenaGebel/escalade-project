package com.company.consumer.impl.dao;
import java.sql.Types;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.DataAccessException;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


import com.company.consumer.contract.dao.TopoDao;
import com.company.consumer.impl.AbstractDao;
import com.company.consumer.impl.rowMapper.TopoRowMapper;

import com.company.model.bean.Topo;


public class TopoDaoImpl  extends AbstractDao implements TopoDao{
	
	public int registerTopo(Topo topo){

	    String sql = "INSERT INTO topo (user_id, name, description, image, date_publication) "
	    		+ "VALUES (:topo_id, :name, :description);";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", topo.getUserId(), Types.INTEGER);
        params.addValue("name", topo.getName(), Types.VARCHAR);
        params.addValue("description", topo.getDescription(), Types.VARCHAR);
        params.addValue("image", topo.getImage(), Types.VARCHAR);
        params.addValue("date_publication", topo.getDate(), Types.TIMESTAMP);
        KeyHolder holder = new GeneratedKeyHolder();

        getNamedParameterJdbcTemplate().update(sql, params, holder);
        
        return holder.getKey().intValue();

	}
	public void deleteTopo(Topo topo){};
	
    public void updateTopo(Topo topo) {
        String sql = "UPDATE topo SET user_id = :user_id, name = :name, description = :description, image = :image, date = now() WHERE topo.id = :topo_id;";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", topo.getUserId(), Types.INTEGER);
        params.addValue("name", topo.getName(), Types.VARCHAR);
        params.addValue("description", topo.getDescription(), Types.VARCHAR);
        params.addValue("image", topo.getImage(), Types.VARCHAR);
        params.addValue("date_publication", topo.getDate(), Types.TIMESTAMP);
        params.addValue("topo_id", topo.getId(), Types.INTEGER);

        try {
        	getNamedParameterJdbcTemplate().update(sql, params);

        }catch(DataAccessException dae) {

        }     
    }

	
	public List<Topo> getListTopos(){
		String sql = "SELECT * FROM topo";
		RowMapper<Topo> rm = new TopoRowMapper();

        List<Topo> topo = getJdbcTemplate().query(sql, rm);

        return topo;
	}
}



