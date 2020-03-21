package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        return lettuceConnectionFactory;
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory());
        container.addMessageListener(messageStringListener(), topic01());
        container.addMessageListener(messageDtoListener(), topic02());
        return container;
    }

    @Bean
    MessageListenerAdapter messageStringListener() {

        return new MessageListenerAdapter(new RedisMessageStringSubscriber());
    }

    @Bean
    MessageListenerAdapter messageDtoListener() {

        return new MessageListenerAdapter(new RedisMessageDtoSubscriber());
    }

    @Bean
    ChannelTopic topic01() {

        return new ChannelTopic("ch01");
    }

    @Bean
    ChannelTopic topic02() {

        return new ChannelTopic("ch02");
    }
}