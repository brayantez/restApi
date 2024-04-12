package com.rest.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @Column(
            name = "id",
            updatable = false
    )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(
            name = "title",
            nullable = false,
            columnDefinition = "VARCHAR(100)"
    )
    private String title;

    @Column(
            name = "body",
            nullable = false,
            columnDefinition = "VARCHAR(100)"
    )
    private String body;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "ARTICLE_USER_ID_FK")
    )
    private User users;

    public Article(String championBoy, String amHere) {
    }

    public Article() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return users;
    }

    public void setUser(User user) {
        this.users = user;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", user=" + users +
                '}';
    }
}
