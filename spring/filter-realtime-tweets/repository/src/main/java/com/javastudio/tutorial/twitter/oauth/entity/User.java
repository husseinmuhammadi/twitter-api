package com.javastudio.tutorial.twitter.oauth.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Date createdAt;

    private String name;

    private String screenName;
}
