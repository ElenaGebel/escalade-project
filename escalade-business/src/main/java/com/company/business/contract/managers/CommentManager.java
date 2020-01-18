package com.company.business.contract.managers;

import java.util.List;

import com.company.model.bean.Comment;

public interface CommentManager {
	int registerComment(Comment comment);
	void deleteComment(Comment comment);
	void updateComment(Comment comment);
	List<Comment> getChildrenComments(Comment comment);
	
	List<Comment> getParentsComments(Comment comment);
}
