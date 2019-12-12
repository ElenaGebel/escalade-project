package com.company.consumer.impl.dao;

import java.sql.Types;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.company.consumer.contract.dao.RouteDao;
import com.company.consumer.impl.AbstractDao;
import com.company.consumer.impl.rowMapper.QuotationRowMapper;
import com.company.consumer.impl.rowMapper.RouteRowMapper;
import com.company.model.bean.Quotation;
import com.company.model.bean.Route;

public class RouteDaoImpl extends AbstractDao implements RouteDao{
	
    public List<Route> listRoutesFromParent(Route route) {
        String sql = "SELECT * FROM route WHERE route.parent_id = :route_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("route_id", route.getId(), Types.INTEGER);

        RowMapper<Route> rowMapper = new RouteRowMapper();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }
    
    public Route getRoute(Route route) {
        String sql = "SELECT * FROM route WHERE route.id = :route_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("route_id", route.getId(), Types.INTEGER);

        RowMapper<Route> rowMapper = new RouteRowMapper();

        return getNamedParameterJdbcTemplate().queryForObject(sql, args, rowMapper);
    }
    
    public List<Quotation> listQuotation() {
        String sql = "SELECT * FROM quotation;";

        MapSqlParameterSource args = new MapSqlParameterSource();

        RowMapper<Quotation> rowMapper = new QuotationRowMapper();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

    public int registerRoute(Route route) {
        String sql = "INSERT INTO voie (name, secteur_id, parent_id, height, quotation, laititude, longitude, points_num, type) " +
                "VALUES (:name, :secteur_id, :parent_id, :height, :quotation, :laititude, :longitude, :points_num, :type);";

        MapSqlParameterSource args = new MapSqlParameterSource();

        args.addValue("name", route.getName(), Types.VARCHAR);
        args.addValue("secteur_id", route.getSectorId(), Types.INTEGER);
        args.addValue("parent_id", route.getParentId(), Types.INTEGER);
        args.addValue("height", route.getHeight(), Types.INTEGER);
        args.addValue("quotation", route.getQuotation(), Types.VARCHAR);
        args.addValue("description", route.getDescription(), Types.VARCHAR);
        
        args.addValue("laititude", route.getLaititude(), Types.VARCHAR);
        args.addValue("longitude", route.getLongitude(), Types.VARCHAR);
        args.addValue("points_num", route.getPointsNum(), Types.INTEGER);
        args.addValue("type", route.getType(), Types.VARCHAR);

        KeyHolder holder = new GeneratedKeyHolder();

        getNamedParameterJdbcTemplate().update(sql, args, holder);
        
        int generatedKey = (Integer) holder.getKeys().get("id");
        
        return generatedKey;
    }

    public List<Route> listRoutsForSector(Route route) {
    	  String sql = "SELECT * FROM route WHERE route.secteur_id = :secteur_id;";

          MapSqlParameterSource args = new MapSqlParameterSource();
          args.addValue("secteur_id", route.getSectorId(), Types.INTEGER);

          RowMapper<Route> rowMapper = new RouteRowMapper();

          return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }
    
    public List<Route> listFreeRouts(Route route) {
  	    String sql = "SELECT * FROM route WHERE route.secteur_id IS NULL ;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("secteur_id", route.getSectorId(), Types.INTEGER);

        RowMapper<Route> rowMapper = new RouteRowMapper();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

    public void updateRoute(Route route) {
        String sql = "UPDATE route SET height = height, quotation = :quotation, laititude = :laititude, longitude = :longitude, points_num = :points_num " +
                "WHERE route.id = :route_id;";
        
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("route_id", route.getId(), Types.INTEGER);
        args.addValue("name", route.getName(), Types.VARCHAR);
        args.addValue("secteur_id", route.getSectorId(), Types.INTEGER);
        args.addValue("parent_id", route.getParentId(), Types.INTEGER);
        args.addValue("height", route.getHeight(), Types.INTEGER);
        args.addValue("quotation", route.getQuotation(), Types.VARCHAR);
        args.addValue("description", route.getDescription(), Types.VARCHAR);
        
        args.addValue("laititude", route.getLaititude(), Types.VARCHAR);
        args.addValue("longitude", route.getLongitude(), Types.VARCHAR);
        args.addValue("points_num", route.getPointsNum(), Types.INTEGER);
        args.addValue("type", route.getType(), Types.VARCHAR);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public void deleteRoute(Route route) {
        String sql = "DELETE FROM route WHERE route.id IN (SELECT route.id FROM route WHERE route.parent_id = :route_id);" +
                "DELETE FROM route WHERE route.id = :route_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("route_id", route.getId(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public List<Route> listLengthsFromRoute(Route route) {
        String sql = "SELECT route.name, route.id, route.sector_id, route.height, route.points_num, quotation, laititude, longitude FROM route "
        		+ "WHERE route.parent_id = :route_id AND type = :type;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("route_id", route.getParentId(), Types.INTEGER);
        args.addValue("type", "length", Types.VARCHAR);

        RowMapper<Route> rowMapper = new RouteRowMapper();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }
    
    public List<Route> listForSearch(Route route) {
        String sql = "SELECT id, parent_id, name, height, quotation, points_num " +
                "FROM route " +
                "WHERE LOWER(name) LIKE LOWER(:name) " +
                "AND (height <= :height OR height ISNULL) " +
                "AND quotation LIKE :quotation " +
                "AND (points_num >= :points_num ISNULL) " +
                "ORDER BY name ASC;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name", "%" + route.getName() + "%", Types.VARCHAR);
        args.addValue("height", route.getHeight(), Types.INTEGER);
        args.addValue("quotation", route.getQuotation(), Types.VARCHAR);
        args.addValue("points_num", route.getPointsNum(), Types.INTEGER);

        RowMapper<Route> rowMapper = new RouteRowMapper();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

}
