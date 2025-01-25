package org.oop.service;

import org.oop.api.ICommentService;
import org.oop.dao.CommentDao;
import org.oop.model.Comment;

import java.sql.SQLException;
import java.util.List;

public class CommentService implements ICommentService {
    private final CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public Comment createComment(String text, long userId, long articleId) throws SQLException {
        return commentDao.createComment(text, userId, articleId);
    }

    @Override
    public Comment getComment(long commentId) {
        return commentDao.getComment(commentId);
    }

    @Override
    public List<Comment> getAllCommentsByArticle(long articleId) {
        return commentDao.getAllCommentsByArticle(articleId);
    }

    @Override
    public List<Comment> getAllCommentsByUser(long userId) {
        return commentDao.getAllCommentsByUser(userId);
    }

    @Override
    public boolean deleteComment(long commentId) {
        return commentDao.deleteComment(commentId);
    }
}
