package com.company.consumer.impl.dao;

import java.sql.Types;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.company.consumer.contract.dao.CommentDao;
import com.company.consumer.impl.AbstractDao;
import com.company.consumer.impl.rowMapper.CommentRowMapper;
import com.company.model.bean.Comment;

public class CommentDaoImpl  extends AbstractDao implements CommentDao{
	


    public int registerComment(Comment comment) {
        String sql = "INSERT INTO comment (user_id, topo_id, parent_id, text, date)" +
                "VALUES (:user_id, :topo_id, :parent_id, :text, :date)";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_id", comment.getUserId(), Types.INTEGER);
        args.addValue("topo_id", comment.getTopoId(), Types.INTEGER);
        args.addValue("text", comment.getText(), Types.VARCHAR);
        args.addValue("parent_id", comment.getParentId(), Types.INTEGER);
        args.addValue("date", comment.getDate(), Types.TIMESTAMP);
        
        KeyHolder holder = new GeneratedKeyHolder();

        getNamedParameterJdbcTemplate().update(sql, args, holder);
        
        int generatedKey = (Integer) holder.getKeys().get("id");
        
        return generatedKey;
    }

    public List<Comment> getParentsComments(Comment comment) {
        String sql = "SELECT comment.id, parent_id, user_id, user_account.pseudo, text, comment.date " +
                "FROM comment, user_account " +
                "WHERE comment.user_id = user_account.id " +
                "AND comment.topo_id = :topo_id " +
                "AND comment.parent_id = 0" +
                "ORDER BY comment.date ASC;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("topo_id", comment.getTopoId(), Types.INTEGER);

        RowMapper<Comment> rowMapper = new CommentRowMapper();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

    public List<Comment> getChildrenComments(Comment comment) {
        String sql = "SELECT comment.id, parent_id, user_id, user_account.pseudo, text, comment.date " +
                "FROM comment, user_account " +
                "WHERE comment.user_id = user_account.id " +
                "AND comment.topo_id = :topo_id " +
                "AND comment.parent_id IS NOT NULL " +
                "AND comment.parent_id != 0 " +
                "ORDER BY comment.date ASC;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("topo_id", comment.getTopoId(), Types.INTEGER);

        RowMapper<Comment> rowMapper = new CommentRowMapper();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

    public void updateComment(Comment comment) {
        String sql = "UPDATE comment SET text = :text, date = now() WHERE comment.id = :comment_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("comment_id", comment.getId(), Types.INTEGER);
        args.addValue("text", comment.getText(), Types.VARCHAR);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public void deleteComment(Comment comment) {
        String sql = "DELETE FROM comment WHERE comment.id = :comment_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("comment_id", comment.getId(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }
    

}
