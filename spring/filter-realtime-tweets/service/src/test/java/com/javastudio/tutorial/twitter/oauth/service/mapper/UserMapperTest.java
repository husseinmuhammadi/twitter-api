package com.javastudio.tutorial.twitter.oauth.service.mapper;

import com.javastudio.tutorial.twitter.oauth.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    void shouldMap(){
        assertNotNull(mapper);
    }
}