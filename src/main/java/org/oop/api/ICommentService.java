package org.oop.api;

import org.oop.model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface ICommentService {
    Comment createComment(String text, long userId, long articleId) throws SQLException;
    Comment getComment(long commentId);
    List<Comment> getAllCommentsByArticle(long articleId);
    List<Comment> getAllCommentsByUser(long userId);
    boolean deleteComment(long commentId);
}
