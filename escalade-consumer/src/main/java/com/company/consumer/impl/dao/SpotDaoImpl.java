package com.company.consumer.impl.dao;
import java.sql.Types;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.company.consumer.contract.dao.SpotDao;
import com.company.consumer.impl.AbstractDao;
import com.company.consumer.impl.rowMapper.SpotRowMapper;
import com.company.consumer.impl.rowMapper.TopoRowMapper;
import com.company.model.bean.Spot;
import com.company.model.bean.Topo;

public class SpotDaoImpl  extends AbstractDao implements SpotDao{
	
	public int registerSpot(Spot spot){

	    String sql = "INSERT INTO spot (user_id, topo_id, name, description, image) VALUES (:user_id, :topo_id, :name, :description, :image);";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("topo_id", spot.getTopoId(), Types.INTEGER);
        params.addValue("user_id", spot.getUserId(), Types.INTEGER);
        params.addValue("name", spot.getName(), Types.VARCHAR);
        params.addValue("description", spot.getDescription(), Types.VARCHAR);
        params.addValue("image", spot.getImage(), Types.VARCHAR);

        KeyHolder holder = new GeneratedKeyHolder();

        getNamedParameterJdbcTemplate().update(sql, params, holder);
        
        int generatedKey = (Integer) holder.getKeys().get("id");
        
        return generatedKey;
	}
	
    public void updateSpot(Spot spot) {
        String sql = "UPDATE spot SET topo_id = :topo_id, description = :description, name = :name, image = :image "
        		+ "WHERE spot.id = :spot_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("spot_id", spot.getId(), Types.INTEGER);
        args.addValue("topo_id", spot.getTopoId(), Types.INTEGER);
        args.addValue("name", spot.getName(), Types.VARCHAR);
        args.addValue("description", spot.getDescription(), Types.VARCHAR);
        args.addValue("image", spot.getImage(), Types.VARCHAR);       

        getNamedParameterJdbcTemplate().update(sql, args);
    }
    
    public void addTopoToSpot(Spot spot) {
        String sql = "UPDATE spot SET topo_id = :topo_id WHERE spot.id = :spot_id ;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("topo_id", spot.getTopoId(), Types.INTEGER);
        args.addValue("spot_id", spot.getId(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public void deleteSpot(Spot spot) {
        String sql = "DELETE FROM voie WHERE voie.secteur_id "
        		+ "IN (SELECT secteur.id FROM secteur WHERE secteur.spot_id = :spot_id);"
    			+ "DELETE FROM secteur WHERE secteur.spot_id = :spot_id;"
        		+ "DELETE FROM spot WHERE spot.id = :spot_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("spot_id", spot.getId(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }
	
	public List<Spot> getListSpots(){
		String sql = "SELECT * FROM spot";

		RowMapper<Spot> rowMapper = new SpotRowMapper();

        List<Spot> spot = getJdbcTemplate().query(sql, rowMapper);

        return spot;
	}
	
	
    public List<Spot> listForSearch(Spot spot) {
        String sql = "SELECT * FROM spot " +
                "WHERE (LOWER(name) LIKE LOWER(:text) OR LOWER(description) LIKE LOWER(:text)) " +
                "ORDER BY name ASC;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("text", "%" + spot.getName() + "%", Types.VARCHAR);

        RowMapper<Spot> rowMapper = new SpotRowMapper();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }
    
    public Spot getSpot(Spot spot) {
        String sql = "SELECT * FROM spot WHERE spot.id = :spot_id ;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("spot_id", spot.getId(), Types.INTEGER);

        RowMapper<Spot> rowMapper = new SpotRowMapper();

        return getNamedParameterJdbcTemplate().queryForObject(sql, args, rowMapper);
    }
    
    public Topo checkForTopo(Spot spot) {
        String sql = "SELECT  topo.name, topo.id, spot.id, spot.topo_id FROM spot, topo " +
                     "WHERE spot.id = ? AND spot.topo_id = topo.id;";
      

      /*  MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("spot_id", spot.getId(), Types.INTEGER);*/
        
        RowMapper<Topo> rowMapper = new TopoRowMapper();


        return  getJdbcTemplate().queryForObject(sql, new Object[]{spot.getId()}, rowMapper);
    }
}



