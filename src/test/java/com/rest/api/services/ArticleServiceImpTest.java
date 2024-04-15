package com.rest.api.services;

import com.rest.api.dtos.ArticleRequestDTO;
import com.rest.api.models.Article;
import com.rest.api.models.User;
import com.rest.api.repositories.ArticleRepository;
import com.rest.api.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceImpTest {

    @InjectMocks
    private ArticleServiceImp articleService;

    @Mock
    private ArticleRepository articleRepository;

    private Article article;

    private Article article2;
    private ArticleRequestDTO articleRequestDTO;

    @BeforeEach
    void setUp() {

        article = new Article();
        article.setId(1L);
        article.setTitle("Aminata");
        article.setBody("Set Book");

        article2 = new Article();
        article.setId(2L);
        article2.setTitle("Makaveli");
        article2.setBody("Play Book");

        articleRequestDTO = new ArticleRequestDTO();
        articleRequestDTO.setTitle("RiverRoad");
        articleRequestDTO.setBody("Set Novel Book");

    }

    @Test
    @DisplayName("It should create an article")
    void itShouldCreateArticle() {
        when(articleRepository.save(any(Article.class))).thenReturn(article);
        Article savedArticle = articleService.createArticle(articleRequestDTO);
        assertNotNull(savedArticle);
        assertEquals("Aminata",savedArticle.getTitle());
    }

    @Test
    @DisplayName("It should find a list of articles")
    void findAllArticles() {
        List<Article> articleList = new ArrayList<>();
        articleList.add(article);
        articleList.add(article2);
        when(articleRepository.findAll()).thenReturn(articleList);
        List<Article> articles = articleService.findAllArticles();
        assertNotNull(articles);
        assertEquals(2,articles.size());
    }

    @Test
    @DisplayName("It should find an article by id")
    void findArticleById() {
        when(articleRepository.findById(anyLong())).thenReturn(Optional.ofNullable(article));
        Article extistingArticle = articleService.findArticleById(article.getId());
        assertEquals("Aminata",extistingArticle.getTitle());
    }

    @Test
    @DisplayName("It should throw an exception find an article by id")
    void findArticleByIdForException() {
        when(articleRepository.findById(1L)).thenReturn(Optional.ofNullable(article));
        assertThrows(RuntimeException.class,() -> {
            articleService.findArticleById(2L);
        });
    }

    @Test
    @DisplayName("It should update article")
    void updateArticle() {
        when(articleRepository.findById(anyLong())).thenReturn(Optional.ofNullable(article));
        when(articleRepository.save(any(Article.class))).thenReturn(article);
        articleRequestDTO.setTitle("Onyango");
        Article updatedArticle =  articleService.updateArticle(article.getId(),articleRequestDTO);
        assertNotNull(updatedArticle);
        assertEquals("Onyango",updatedArticle.getTitle());
    }

    @Test
    @DisplayName("It should delete an article")
    void deleteArticle() {
        when(articleRepository.findById(anyLong())).thenReturn(Optional.ofNullable(article));
        doNothing().when(articleRepository).delete(any(Article.class));
        articleService.deleteArticle(article.getId());
        verify(articleRepository,times(1)).delete(article);
    }
}