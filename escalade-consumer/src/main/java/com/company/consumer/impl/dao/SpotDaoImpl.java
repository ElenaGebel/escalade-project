package com.company.consumer.impl.dao;
import java.sql.Types;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.company.consumer.contract.dao.SpotDao;
import com.company.consumer.impl.AbstractDao;
import com.company.model.bean.Spot;

public class SpotDaoImpl  extends AbstractDao implements SpotDao{
	
	public void registerSpot(Spot spot){

	    String sql = "INSERT INTO spot (topo_id, name, description) VALUES (:topo_id, :name, :description);";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("topo_id", spot.getTopoId(), Types.VARCHAR);
        params.addValue("name", spot.getName(), Types.VARCHAR);
        params.addValue("description", spot.getDescription(), Types.VARCHAR);
        params.addValue("image", spot.getDescription(), Types.VARCHAR);

        getNamedParameterJdbcTemplate().update(sql, params);

	}
	public void deleteSpot(Spot spot){};
	public void updateSpot(Spot spot) {};
	
	public List<Spot> getListSpots(){
		String sql = "SELECT * FROM spot";

        RowMapper<Spot> rowMapper = new RowMapper<Spot>() {
            public Spot mapRow(ResultSet pRS, int pRowNum) throws SQLException {
            	Spot spot = new Spot();
            	spot.setId(pRS.getInt("id"));
            	spot.setTopoId(pRS.getInt("topo_id"));
            	spot.setName(pRS.getString("name"));
            	spot.setDescription(pRS.getString("description"));
            	spot.setImage(pRS.getString("image"));
                return spot;
            }
        };

        List<Spot> spot = getJdbcTemplate().query(sql, rowMapper);

        return spot;
	}
}



