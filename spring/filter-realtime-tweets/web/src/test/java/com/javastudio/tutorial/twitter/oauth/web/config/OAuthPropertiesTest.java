package com.javastudio.tutorial.twitter.oauth.web.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ObjectMapper.class})
@EnableConfigurationProperties(value = {OAuthProperties.class})
@TestPropertySource(value = {"classpath:twitter-oauth.properties"})
class OAuthPropertiesTest {

    @Autowired
    OAuthProperties oAuthProperties;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldLoadConfig() throws JsonProcessingException {
        Assertions.assertEquals("OAUTH-CONSUMER-KEY", oAuthProperties.getKey());
        Assertions.assertEquals("OAUTH-CONSUMER-SECRET", oAuthProperties.getSecret());
    }

}