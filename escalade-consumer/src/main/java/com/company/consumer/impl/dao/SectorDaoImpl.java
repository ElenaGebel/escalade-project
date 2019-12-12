package com.company.consumer.impl.dao;

import java.sql.Types;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.company.consumer.contract.dao.SectorDao;
import com.company.consumer.impl.AbstractDao;
import com.company.consumer.impl.rowMapper.SectorRowMapper;
import com.company.model.bean.Sector;

public class SectorDaoImpl extends AbstractDao implements SectorDao {

    public List<Sector> getListSectors(Sector sector) {
    	
        String sql;

        MapSqlParameterSource args = new MapSqlParameterSource();
        if( sector != null) {
        	sql = "SELECT * FROM sector WHERE sector.spot_id = :spot_id;";
        	 args.addValue("spot_id", sector.getSpotId(), Types.INTEGER);
        }
        else {
        	sql = "SELECT * FROM sector WHERE sector.spot_id IS NULL;";
        }
       

        RowMapper<Sector> rowMapper = new SectorRowMapper();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

    public int registerSector(Sector sector) {
        String sql = "INSERT INTO sector (spot_id, name, description) VALUES :spot_id, :name, : description);";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("spot_id", sector.getSpotId(), Types.INTEGER);
        
        args.addValue("name", sector.getName(), Types.VARCHAR);
        args.addValue("description", sector.getDescription(), Types.VARCHAR);
        
        KeyHolder holder = new GeneratedKeyHolder();

        getNamedParameterJdbcTemplate().update(sql, args, holder);
        
        int generatedKey = (Integer) holder.getKeys().get("id");
        
        return generatedKey;
    }

    public void updateSector(Sector sector) {
        String sql = "UPDATE sector SET name = :name, description = :description, spot_id = :spot_id  WHERE sector.id = :sector_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("spot_id", sector.getSpotId(), Types.INTEGER);
        args.addValue("sector_id", sector.getId(), Types.INTEGER);
        args.addValue("name", sector.getName(), Types.VARCHAR);
        args.addValue("description", sector.getDescription(), Types.VARCHAR);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public void deleteSector(Sector sector) {
    	String sql = "DELETE FROM sector WHERE sector.id = :sector_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("sector_id", sector.getId(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }
    
    public List<Sector> listForSearch(Sector sector) {
        String sql = "SELECT id, topo_id, name, description, image " +
                "FROM sector " +
                "WHERE LOWER(name) LIKE LOWER(:name) " +
                "ORDER BY name ASC;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name", "%" + sector.getName() + "%", Types.VARCHAR);

        RowMapper<Sector> rowMapper = new SectorRowMapper();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }
}  
