package com.company.webapp.controller;

import com.company.business.contract.managers.CommentManager;
import com.company.model.bean.Comment;
import com.company.model.bean.User;
import com.company.webapp.AbstractController;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class CommentController  extends AbstractController{
	
	private CommentManager commentManager = getManagerFactory().getCommentManager();
	
    @PostMapping("/topo/comment/{parentId}")
    public String addComment(@PathVariable String parentId, @SessionAttribute User user, @RequestParam int publicationId, @RequestParam String content, @RequestParam String currentURI) {
        Comment comment = new Comment();
        comment.setUserId(user.getId());
        comment.setTopoId(publicationId);
        
        if (parentId != null) 
        	comment.setParentId(Integer.parseInt(parentId));
        else
        	comment.setParentId(0);
        
        comment.setText(content);
        comment.setDate(new Date());

        commentManager.registerComment(comment);
        return "redirect:/topo/" + publicationId;
    }

    @PostMapping("/topo/comment/{commentId}/update")
    public String updateComment(@PathVariable String commentId, @RequestParam String content, @RequestParam String currentURI) {
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(commentId));
        comment.setText(content);

        commentManager.updateComment(comment);
        return "redirect:" + currentURI;
    }

    @PostMapping("/topo/comment/{commentId}/delete")
    public String deleteComment(@PathVariable String commentId, @RequestParam String currentURI,  @RequestParam int publicationId) {
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(commentId));

        commentManager.deleteComment(comment);
        return "redirect:/topo/" + publicationId;
    }
}


