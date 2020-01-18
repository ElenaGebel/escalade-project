package com.company.consumer.impl.rowMapper;

import org.springframework.jdbc.core.RowMapper;

import com.company.model.bean.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Comment> {
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            if (rs.getMetaData().getColumnName(i).equals("id")) comment.setId(rs.getInt("id"));
            else if (rs.getMetaData().getColumnName(i).equals("user_id")) comment.setUserId(rs.getInt("user_id"));
            else if (rs.getMetaData().getColumnName(i).equals("topo_id")) comment.setTopoId(rs.getInt("topo_id"));
            else if (rs.getMetaData().getColumnName(i).equals("parent_id")) comment.setParentId(rs.getInt("parent_id"));
            else if (rs.getMetaData().getColumnName(i).equals("date")) comment.setDate(rs.getTimestamp("date"));
            else if (rs.getMetaData().getColumnName(i).equals("text")) comment.setText(rs.getString("text"));
        }
        UserRowMapper userRM = new UserRowMapper();
        comment.setUser(userRM.mapRow(rs, rowNum));

        return comment;
    }
}
