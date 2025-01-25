package org.oop.model;

import java.util.Objects;

public class Comment {

    long id;
    String text;
    long userID;
    long articleId;

    public Comment() {
    }

    public Comment(long id, String text, long userID, long articleId) {
        this.id = id;
        this.text = text;
        this.userID = userID;
        this.articleId = articleId;
    }

    public Comment(String text, long userID, long articleId) {
        this.text = text;
        this.userID = userID;
        this.articleId = articleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && userID == comment.userID && articleId == comment.articleId && Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, userID, articleId);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", userID=" + userID +
                ", articleId=" + articleId +
                '}';
    }
}
