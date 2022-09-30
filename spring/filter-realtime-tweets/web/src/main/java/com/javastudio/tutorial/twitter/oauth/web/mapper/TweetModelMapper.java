package com.javastudio.tutorial.twitter.oauth.web.mapper;

import com.javastudio.tutorial.twitter.oauth.dto.TweetDTO;
import com.javastudio.tutorial.twitter.oauth.dto.UserDTO;
import com.javastudio.tutorial.twitter.oauth.web.model.Tweet;
import com.javastudio.tutorial.twitter.oauth.web.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TweetModelMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tweetId", source = "id")
    TweetDTO map(Tweet tweet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", source = "id")
    UserDTO map(User user);
}
