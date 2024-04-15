package com.rest.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.dtos.ArticleRequestDTO;
import com.rest.api.models.Article;
import com.rest.api.services.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(value = ArticleController.class)
class ArticleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("It should get all articles")
    void itShouldGetAllArticles() throws Exception {

        var articles = List.of(new Article("champion","here"),new Article("dragon","you"));

        when(articleService.findAllArticles()).thenReturn(articles);

        mockMvc.perform(get("/api/v1/article/all")
                         .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                      status().isOk(),
                      content().contentType(MediaType.APPLICATION_JSON),
                      jsonPath("$.data[0].title").value("champion")
                );

    }

}