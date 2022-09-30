package com.javastudio.tutorial.twitter.oauth.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Tweet {

    @Id
    @GeneratedValue
    private Long id;

    private Long tweetId;

    private Date createdAt;

    private String text;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;
}
