package org.oop.dao;

import org.oop.api.dao.ICommentDao;
import org.oop.model.Article;
import org.oop.model.Comment;
import org.oop.model.User;

import java.sql.*;
import java.util.List;

public class CommentDao extends Dao implements ICommentDao {
    ArticleDao articleDao = new ArticleDao();
    UserDao userDao = new UserDao();

    @Override
    public Comment createComment(String text, long userId, long articleId) throws SQLException {
        String query = "INSERT INTO comments (article_id, user_id, comment_text) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setLong(1, articleId);
            preparedStatement.setLong(2, userId);
            preparedStatement.setString(3, text);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating comment failed, no rows affected.");
            }

            Comment comment = new Comment(text, userId, articleId);

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    comment.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating comment failed, no ID obtained.");
                }
        }
            return comment;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Comment getComment(long commentId) {
        String query = "SELECT id, article_id, user_id, comment_text FROM comments WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, commentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Comment(
                            resultSet.getLong("id"),
                            resultSet.getString("comment_text"),
                            resultSet.getLong("user_id"),
                            resultSet.getLong("article_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Comment> getAllCommentsByArticle(long articleId) {
        String query = "SELECT id, article_id, user_id, comment_text FROM comments WHERE article_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, articleId);

            Article article = articleDao.getArticleById(articleId);

            return article.getComments();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Comment> getAllCommentsByUser(long userId) {
        String query = "SELECT id, article_id, user_id, comment_text FROM comments WHERE user_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, userId);

            User user = userDao.getUserById((int) userId);

            return user.getComments();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteComment(long commentId) {

        String query = "DELETE FROM comments WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, commentId);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
