package com.rest.api.services;

import com.rest.api.dtos.ArticleRequestDTO;
import com.rest.api.models.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    Article createArticle(ArticleRequestDTO articleRequestDTO);

    List<Article> findAllArticles();

    Article findArticleById(Long id);

    Article updateArticle(Long id, ArticleRequestDTO articleRequestDTO);

    void deleteArticle(Long id);
}
