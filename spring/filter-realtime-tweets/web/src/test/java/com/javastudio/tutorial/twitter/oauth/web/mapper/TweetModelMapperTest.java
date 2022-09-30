package com.javastudio.tutorial.twitter.oauth.web.mapper;

import com.javastudio.tutorial.twitter.oauth.dto.UserDTO;
import com.javastudio.tutorial.twitter.oauth.web.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TweetModelMapperTest {

    private TweetModelMapper mapper = Mappers.getMapper(TweetModelMapper.class);

    @Test
    void shouldMapUserToUserDto() {
        User user = new User();
        user.setId(1L);
        UserDTO sut = mapper.map(user);
        assertEquals(sut.getUserId(), 1L);
        assertNotEquals(sut.getId(), 1L);
    }
}