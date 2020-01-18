package com.company.business.impl.managers;

import java.util.List;

import com.company.business.contract.managers.CommentManager;
import com.company.business.impl.AbstractManager;
import com.company.model.bean.Comment;


public class CommentManagerImpl extends AbstractManager implements CommentManager{
	
	public int registerComment(Comment comment){
		return getDaoFactory().getCommentDao().registerComment(comment);
	}
	
	public void deleteComment(Comment comment){
		getDaoFactory().getCommentDao().deleteComment(comment);
	}
	
	public void updateComment(Comment comment){
		getDaoFactory().getCommentDao().updateComment(comment);
	}
	
	public List<Comment> getChildrenComments(Comment comment){
		return getDaoFactory().getCommentDao().getChildrenComments(comment);
	}
	
	public List<Comment> getParentsComments(Comment comment){
		return getDaoFactory().getCommentDao().getParentsComments(comment);
	}
}
