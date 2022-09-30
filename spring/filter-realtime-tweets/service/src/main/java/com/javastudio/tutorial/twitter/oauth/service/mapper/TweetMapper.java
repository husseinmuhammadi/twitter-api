package com.javastudio.tutorial.twitter.oauth.service.mapper;

import com.javastudio.tutorial.twitter.oauth.dto.TweetDTO;
import com.javastudio.tutorial.twitter.oauth.dto.UserDTO;
import com.javastudio.tutorial.twitter.oauth.entity.Tweet;
import org.apache.catalina.User;
import org.mapstruct.Mapper;

@Mapper(uses = UserMapper.class)
public interface TweetMapper {

    Tweet map(TweetDTO tweetDTO);
    TweetDTO map(Tweet tweet);
}
