package com.company.consumer.impl.dao;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


import com.company.consumer.contract.dao.TopoDao;
import com.company.consumer.impl.AbstractDao;
import com.company.consumer.impl.rowMapper.SpotRowMapper;
import com.company.consumer.impl.rowMapper.TopoRowMapper;
import com.company.model.bean.Spot;
import com.company.model.bean.Topo;
import com.company.model.bean.User;


public class TopoDaoImpl  extends AbstractDao implements TopoDao{
	
	public int registerTopo(Topo topo){

	    String sql = "INSERT INTO topo (user_id, name, description, image, date_publication) "
	    		+ "VALUES (:user_id, :name, :description, :image, :date_publication); "
	    		+ "INSERT INTO user_topo_reservation (user_id, topo_id, reserved) VALUES "
	    		+ "(:user_id, (SELECT currval('topo_id_seq')), FALSE);";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", topo.getUserId(), Types.INTEGER);
        params.addValue("name", topo.getName(), Types.VARCHAR);
        params.addValue("description", topo.getDescription(), Types.VARCHAR);
        params.addValue("image", topo.getImage(), Types.VARCHAR);
        params.addValue("date_publication", topo.getDate(), Types.TIMESTAMP);
        KeyHolder holder = new GeneratedKeyHolder();

        getNamedParameterJdbcTemplate().update(sql, params, holder);
        
        int generatedKey = (Integer) holder.getKeys().get("id");
        
        return generatedKey;

	}

	public void deleteTopo(Topo topo){
		
	       String sql = "DELETE FROM topo WHERE topo.id = :topo_id; "
	       		+ "DELETE FROM user_topo_reservation WHERE user_topo_reservation.topo_id = :topo_id;";

	        MapSqlParameterSource args = new MapSqlParameterSource();
	        args.addValue("topo_id", topo.getId(), Types.INTEGER);

	        getNamedParameterJdbcTemplate().update(sql, args);
	};
	
    public void updateTopo(Topo topo) {
        String sql = "UPDATE topo SET user_id = :user_id, name = :name, description = :description, image = :image, date_publication = now() WHERE id = :topo_id;";

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
		String sql = "SELECT topo.id, topo.user_id, topo.name, topo.description, topo.image, topo.date_publication,  user_topo_reservation.topo_id, "
				+ "user_topo_reservation.reserved, user_topo_reservation.date_reservation, user_topo_reservation.user_reserved_id "
				+ "FROM topo, user_topo_reservation "
				+ "WHERE topo.id = user_topo_reservation.topo_id;";
		RowMapper<Topo> rm = new TopoRowMapper();

        List<Topo> topo = getJdbcTemplate().query(sql, rm);

        return topo;
	}
	
    public List<Topo> listForSearch(Topo topo) {
        String sql = "SELECT * FROM topo " +
                "WHERE (LOWER(name) LIKE LOWER(:text) OR LOWER(description) LIKE LOWER(:text)) " +
                "ORDER BY name ASC;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("text", "%" + topo.getName() + "%", Types.VARCHAR);

        RowMapper<Topo> rowMapper = new TopoRowMapper();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }
    
    
    public Topo getTopo(Topo topo) {
    	String sql = "SELECT topo.id, topo.user_id, topo.name, topo.description, topo.image, topo.date_publication,  user_topo_reservation.topo_id, "
				+ "user_topo_reservation.reserved, user_topo_reservation.date_reservation, user_topo_reservation.user_reserved_id "
				+ "FROM topo, user_topo_reservation "
				+ "WHERE topo.id = :topo_id "
				+ "AND topo.id = user_topo_reservation.topo_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("topo_id", topo.getId(), Types.INTEGER);

        RowMapper<Topo> rowMapper = new TopoRowMapper();

        return getNamedParameterJdbcTemplate().queryForObject(sql, args, rowMapper);
    }

    public List<Spot> getNotRelatedSpots(Topo topo) {
        String sql = "SELECT spot.name, spot.id FROM spot WHERE spot.topo_id != :topo_id " +
                "ORDER BY spot.name ASC;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("topo_id", topo.getId(), Types.INTEGER);

        RowMapper<Spot> rowMapper = new SpotRowMapper();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

        public List<Spot> getRelatedSpots(Topo topo) {
            String sql = "SELECT spot.name, spot.id FROM spot WHERE spot.topo_id = :topo_id " +
                    "ORDER BY spot.name ASC;";

            MapSqlParameterSource args = new MapSqlParameterSource();
            args.addValue("topo_id", topo.getId(), Types.INTEGER);

            RowMapper<Spot> rowMapper = new SpotRowMapper();
            return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }
    
    public void updateReservation(Topo topo){
        String sql = "UPDATE user_topo_reservation SET reserved = :reserved, "+
        		"date_reservation = :date_reservation, user_reserved_id = :user_reserved_id "+
        		"WHERE user_topo_reservation.topo_id = :topo_id;";


        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_id", topo.getUserId(), Types.INTEGER);
        args.addValue("topo_id", topo.getId(), Types.INTEGER);
        args.addValue("user_reserved_id", topo.getUserReservedId(), Types.INTEGER);
        args.addValue("reserved", topo.getReserved(), Types.BOOLEAN);
        args.addValue("date_reservation", new Date(), Types.TIMESTAMP);

        getNamedParameterJdbcTemplate().update(sql, args);
    }
        

}



