package com.company.consumer.contract.dao;

import java.util.List;

import com.company.model.bean.Comment;

public interface CommentDao {
	int registerComment(Comment comment);
	void deleteComment(Comment comment);
	void updateComment(Comment comment);
	List<Comment> getParentsComments(Comment comment);
	List<Comment> getChildrenComments(Comment comment);
}
