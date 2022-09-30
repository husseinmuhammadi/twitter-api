package com.javastudio.tutorial.twitter.oauth.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Tweet {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("text")
    private String text;

    @JsonProperty("user")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
