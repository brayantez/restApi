package com.rest.api.repositories;


import com.rest.api.models.Article;
import com.rest.api.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;
    private Article article;
    private Article article2;

    private User user;

    @BeforeEach
    void setUp() {

        user = new User();
        user.setFirstName("Brian");
        user.setLastName("Matonda");
        user.setEmail("brnnyandieka@gmail.com");
        user.setPassword("12345");

        User user1 =  userRepository.save(user);

        article = new Article();
        article.setTitle("Aminata");
        article.setBody("Set Book");
        article.setUser(user1);

        article2 = new Article();
        article2.setTitle("RiverRoad");
        article2.setBody("Set Novel Book");
        article2.setUser(user1);

    }

    @AfterEach
    void tearDown() {
        articleRepository.deleteAll();
    }

    @Test
    @DisplayName("It should save the article to the database")
    public void shouldSaveArticle(){
        //Arrange
        //Act
       Article savedArticle = articleRepository.save(article);
       //Assert
       assertNotNull(savedArticle);
       assertEquals("Aminata",savedArticle.getTitle());
       assertThat(savedArticle.getId()).isNotEqualTo(null);

    }

    @Test
    @DisplayName("It should get all articles from the database")
    public void shouldGetAllArticle(){
        //Arrange

        //Act
        articleRepository.save(article);
        articleRepository.save(article2);
        List<Article> articles = articleRepository.findAll();
        //Assert
        assertNotNull(articles);
        assertThat(articles).isNotNull();
        assertEquals(2,articles.size());
    }

    @Test
    @DisplayName("It should get an article by id from the database")
    public void shouldGetArticleById(){
        //Arrange
        //Act
        articleRepository.save(article);
        Optional<Article> existingArticle = articleRepository.findById(article.getId());
        //Assert
        assertThat(existingArticle).isNotNull();
        assertEquals("Aminata",existingArticle.get().getTitle());
    }

    @Test
    @DisplayName("It should get an article by title from the database")
    public void shouldGetArticleByTitle(){
        //Arrange
        //Act
        articleRepository.save(article);
        Article article1 = articleRepository.findByTitle(article.getTitle());
        //Assert
        assertThat(article1).isNotNull();
        assertEquals("Aminata",article1.getTitle());
    }

    @Test
    @DisplayName("It should update an article into the database")
    public void shouldUpdateAnArticle(){
        //Arrange
        //Act
        articleRepository.save(article);
        Optional<Article> existingArticle = articleRepository.findById(article.getId());
        existingArticle.get().setTitle("Rutto");
        Article updatedArticle = articleRepository.save(article);
        //Assert
        assertEquals("Set Book",updatedArticle.getBody());
        assertEquals("Rutto",updatedArticle.getTitle());
    }

    @Test
    @DisplayName("It should delete an article from the database")
    public void shouldDeleteAnArticle(){
        //Arrange
        //Act
        articleRepository.save(article);
        articleRepository.save(article2);
        articleRepository.delete(article);

        List<Article> articles = articleRepository.findAll();
        Optional<Article> existingArticle = articleRepository.findById(article.getId());
        //Assert
        assertEquals(1,articles.size());
        assertThat(existingArticle).isEmpty();
    }
}