package com.javastudio.tutorial.twitter.oauth.service.mapper;

import com.javastudio.tutorial.twitter.oauth.dto.UserDTO;
import com.javastudio.tutorial.twitter.oauth.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User map(UserDTO userDTO);
    UserDTO map(User user);
}
