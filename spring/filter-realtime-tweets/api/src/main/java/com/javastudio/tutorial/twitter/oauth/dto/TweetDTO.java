package com.javastudio.tutorial.twitter.oauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TweetDTO {

    private Long id;

    private Long tweetId;

    private Date createdAt;

    private String text;

    private UserDTO user;

}
