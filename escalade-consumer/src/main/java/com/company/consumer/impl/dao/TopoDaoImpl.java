package com.company.consumer.impl.dao;
import java.sql.Types;
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
	    		+ "VALUES (:user_id, :name, :description, :image, :date_publication);";

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
		
	       String sql = "DELETE FROM topo WHERE topo.id = :topo_id;";

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
		String sql = "SELECT * FROM topo";
		RowMapper<Topo> rm = new TopoRowMapper();

        List<Topo> topo = getJdbcTemplate().query(sql, rm);

        return topo;
	}
	
    public List<Topo> listForSearch(Topo topo) {
        String sql = "SELECT * FROM topo " +
                "WHERE (LOWER(name) LIKE LOWER('%c%') OR LOWER(description) LIKE LOWER('%c%')) " +
                "ORDER BY name ASC;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name", "%" + topo.getName() + "%", Types.VARCHAR);

        RowMapper<Topo> rowMapper = new TopoRowMapper();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }
    
    
    public Topo getTopo(Topo topo) {
        String sql = "SELECT * FROM topo WHERE topo.id = :topo_id;";

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

       /*   public void deleteTopoHastSpot(Topo topo, Spot spot) {
        String sql = "DELETE FROM topo_has_spot WHERE topo_id = :topo_id AND spot_id = :spot_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("topo_id", topo.getPublicationId(), Types.INTEGER);
        args.addValue("spot_id", spot.getPublicationId(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public void addUserHasTopo(UserAccount user) {
        String sql = "INSERT INTO user_has_topo (user_id, topo_id, is_loaned) VALUES (:user_id, :topo_id, FALSE)";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_id", user.getId(), Types.INTEGER);
        args.addValue("topo_id", user.getTopo().getPublicationId(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public boolean getNotRelatedUser(Topo topo) {
        String sql = "SELECT CASE WHEN exists( " +
                "SELECT user_id " +
                "FROM user_has_topo, topo " +
                "WHERE user_has_topo.topo_id = topo.publication_id " +
                "AND user_id = :user_id " +
                "AND topo_id = :topo_id " +
                ") " +
                "THEN CAST(1 AS BOOLEAN) " +
                "ELSE CAST(0 AS BOOLEAN) " +
                "END;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_id", topo.getUserAccountId(), Types.INTEGER);
        args.addValue("topo_id", topo.getPublicationId(), Types.INTEGER);

        return getNamedParameterJdbcTemplate().queryForObject(sql, args, boolean.class);
    }

    public List<UserAccount> getUserHasTopo(Topo topo) {
        String sql = "SELECT pseudo, is_loaned, borrowing_date, return_date, user_id AS id, topo_id " +
                "FROM user_account, user_has_topo " +
                "WHERE user_account.id = user_has_topo.user_id " +
                "AND topo_id = :topo_id " +
                "ORDER BY return_date ASC;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("topo_id", topo.getPublicationId(), Types.INTEGER);

        RowMapper<UserAccount> rowMapper = new UserAccountRM();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

    public void updateUserHasTopo(UserAccount user) {
        String sql = "UPDATE user_has_topo " +
                "SET user_id = :user_id, topo_id = :topo_id, is_loaned = :is_loaned, borrowing_date = :borrowing_date, return_date = :return_date " +
                "WHERE user_id = :user_id AND topo_id = :topo_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_id", user.getId(), Types.INTEGER);
        args.addValue("topo_id", user.getTopo().getPublicationId(), Types.INTEGER);
        args.addValue("is_loaned", user.getTopo().isLoaned(), Types.BOOLEAN);
        args.addValue("borrowing_date", user.getTopo().getBorrowingDate(), Types.DATE);
        args.addValue("return_date", user.getTopo().getReturnDate(), Types.DATE);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public void deleteUserHasTopo(UserAccount user) {
        String sql = "DELETE FROM user_has_topo WHERE user_id = :user_id AND topo_id = :topo_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_id", user.getId(), Types.INTEGER);
        args.addValue("topo_id", user.getTopo().getPublicationId(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }*/

}



