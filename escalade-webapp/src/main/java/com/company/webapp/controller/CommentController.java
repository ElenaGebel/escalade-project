package com.company.webapp.controller;

import com.company.business.contract.managers.CommentManager;
import com.company.model.bean.Comment;
import com.company.model.bean.User;
import com.company.webapp.AbstractController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
public class CommentController  extends AbstractController{
	
	private CommentManager commentManager = getManagerFactory().getCommentManager();
	
    @PostMapping("/comment/{parentId}")
    public String addComment(@PathVariable String parentId, @SessionAttribute User user, @RequestParam int publicationId, @RequestParam String content, @RequestParam String currentURI) {
        Comment comment = new Comment();
        comment.setUserId(user.getId());
        comment.setTopoId(publicationId);
        if (parentId != null) comment.setParentId(Integer.parseInt(parentId));
        comment.setText(content);

        commentManager.registerComment(comment);
        return "redirect:" + currentURI;
    }

    @PostMapping("/comment/{commentId}/update")
    public String updateComment(@PathVariable String commentId, @RequestParam String content, @RequestParam String currentURI) {
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(commentId));
        comment.setText(content);

        commentManager.updateComment(comment);
        return "redirect:" + currentURI;
    }

    @PostMapping("/comment/{commentId}/delete")
    public String deleteComment(@PathVariable String commentId, @RequestParam String currentURI) {
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(commentId));

        commentManager.deleteComment(comment);
        return "redirect:" + currentURI;
    }
}


