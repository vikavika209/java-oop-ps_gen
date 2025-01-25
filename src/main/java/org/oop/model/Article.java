package org.oop.model;

import java.util.List;

public class Article {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private List<Comment> comments;

    // Конструктор по умолчанию
    public Article() {
    }

    // Конструктор с параметрами
    public Article(Long id, String title, String content, Long authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    // Геттеры
    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    // Сеттеры
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setComments(List<Comment> comments) {}

    @Override
    public String toString() {
        return "ID: " + id + " | Заголовок: " + title + "\nСодержимое: " + content;
    }
}
