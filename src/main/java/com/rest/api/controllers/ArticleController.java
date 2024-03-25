package com.rest.api.controllers;

import com.rest.api.dtos.ArticleRequestDTO;
import com.rest.api.models.Article;
import com.rest.api.services.ArticleService;
import com.rest.api.utils.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/create-article")
    public ResponseEntity<ApiResponse<Article>> createArticle(@RequestBody @Valid ArticleRequestDTO articleRequestDTO){
        ApiResponse<Article> apiResponse = new ApiResponse<>();
        LOGGER.info("Initiating new article creation: {}", articleRequestDTO);
        Article article = articleService.createArticle(articleRequestDTO);
        if(article == null){
            apiResponse.setStatus(0);
            apiResponse.setMessage("Failed to create a new article");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        apiResponse.setData(article);
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Article>>> getAllArticles(){
        LOGGER.info("Retrieving all available article of is");
        ApiResponse<List<Article>> apiResponse = new ApiResponse<>();
        List<Article> articleList = articleService.findAllArticles();
        if(articleList.isEmpty()){
            apiResponse.setStatus(0);
            apiResponse.setMessage("No articles found");
            return new ResponseEntity<>(apiResponse,HttpStatus.NO_CONTENT);
        }
        apiResponse.setData(articleList);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Article>> getArticleById(@PathVariable("id") Long id){
        LOGGER.info("Retrieving one article of is: {}", id);
        ApiResponse<Article> apiResponse = new ApiResponse<>();
        Optional<Article> article = articleService.findArticleById(id);
        if(article.isEmpty()){
            apiResponse.setStatus(0);
            apiResponse.setMessage("Article not found");
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Article>> updateArticle(@PathVariable("id") Long id,@RequestBody ArticleRequestDTO articleRequestDTO){
        LOGGER.info("Updating an article by id 13131313: {}", id);
        ApiResponse<Article> apiResponse = new ApiResponse<>();
        Optional<Article> article = articleService.findArticleById(id);
        if(article.isEmpty()){
            apiResponse.setStatus(0);
            apiResponse.setMessage("Article not found");
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
        Article updatedArticle = articleService.updateArticle(id,articleRequestDTO);
        apiResponse.setStatus(0);
        apiResponse.setData(updatedArticle);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Article>> deleteArticle(@PathVariable("id") Long id){
        LOGGER.info("Deleting an article by id: {}", id);
        ApiResponse<Article> apiResponse = new ApiResponse<>();
        articleService.deleteArticle(id);
        apiResponse.setMessage("Article deleted successfully");
        return new ResponseEntity<>(apiResponse,HttpStatus.NO_CONTENT);
    }
}
