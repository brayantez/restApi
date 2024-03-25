package com.rest.api.services;

import com.rest.api.dtos.ArticleRequestDTO;
import com.rest.api.models.Article;
import com.rest.api.repositories.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleServiceImp implements ArticleService{

    private final ArticleRepository articleRepository;

    public ArticleServiceImp(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @Transactional
    @Modifying
    @Override
    public Article createArticle(ArticleRequestDTO articleRequestDTO) {
        Article article = new Article();
        article.setTitle(articleRequestDTO.getTitle());
        article.setBody(articleRequestDTO.getBody());
        article.setUser(null);
        return articleRepository.save(article);
    }

    @Override
    public List<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> findArticleById(Long id) {
        return articleRepository.findById(id);
    }

    @Transactional
    @Modifying
    @Override
    public Article updateArticle(Long id, ArticleRequestDTO articleRequestDTO) {
        Article article = new Article();
        article.setTitle(articleRequestDTO.getTitle());
        article.setBody(articleRequestDTO.getBody());
        article.setUser(null);
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Long id) {
       articleRepository.deleteById(id);
    }
}
