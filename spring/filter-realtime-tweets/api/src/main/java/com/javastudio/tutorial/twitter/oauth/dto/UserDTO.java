package com.javastudio.tutorial.twitter.oauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDTO {
    private Long id;

    private Long userId;

    private Date createdAt;

    private String name;

    private String screenName;
}
