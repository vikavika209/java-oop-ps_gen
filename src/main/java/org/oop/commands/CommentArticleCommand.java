package org.oop.commands;

import org.oop.api.*;
import org.oop.commands.menu.ArticleMenu;
import org.oop.commands.menu.BaseCommand;
import org.oop.di.Injector;
import org.oop.model.Article;
import org.oop.model.User;

import java.sql.SQLException;
import java.util.List;

public class CommentArticleCommand extends BaseCommand {

    private final IArticleService articleService;
    private final IAuthService authService;
    private final ICommentService commentService;

    public CommentArticleCommand() {
        this.articleService = Injector.getInstance().getService(IArticleService.class);
        this.authService =  Injector.getInstance().getService(IAuthService.class);
        this.commentService = Injector.getInstance().getService(ICommentService.class);
    }

    @Override
    public ICommand execute() {
        List<Article> articles = articleService.getAllArticles();
        String articleId = ioService.prompt("Введите id статьи для добавления комментария: ");
        try {
           long articleIdLong = Long.parseLong(articleId);
            long userId = authService.getCurrentUserId();

            String comment = ioService.prompt("Введите комментарий: ");

            commentService.createComment(comment, userId, articleIdLong);

            ioService.printLine("Комментарий успешно добавлен к статье id: " + articleIdLong);

        } catch (NumberFormatException | SQLException e) {
            ioService.printLine("Ошибка при добавлении комментария: " + e.getMessage());
        }
        return new ArticleMenu();
    }

    @Override
    public String getDescription() {
        return "";
    }
}
