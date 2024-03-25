package com.rest.api.services;

import com.rest.api.dtos.ArticleRequestDTO;
import org.junit.jupiter.api.*;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class ArticleServiceImpTest {

    //Junit test Assertions
//    @Test
//    void createArticleTest() {
//        var article = new ArticleRequestDTO("Makaveli","today is on");
//        assertEquals("Makaveli",article.getTitle(),"Article name was not equal to Makaveli");
//    }

    //Assert J test
//    @Test
//    void createArticleTest() {
//        var article = new ArticleRequestDTO("Makaveli","today is on");
//        assertThat(article.getTitle()).isEqualTo("Makaveli")
//                .startsWith("M")
//                .endsWith("i")
//                .contains("av")
//                .isEqualToIgnoringCase("makaveli");
//    }
   //Hamcrest test
//    @Test
//    void createArticleTest() {
//        var article = new ArticleRequestDTO("Makaveli","today is on");
//        var article2 = new ArticleRequestDTO("Makaveli","today is on");
//        assertThat(article,equalTo(article2));
//
//    }
}