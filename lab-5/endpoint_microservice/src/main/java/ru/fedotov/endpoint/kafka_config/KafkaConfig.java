package ru.fedotov.endpoint.kafka_config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaConfig {

    @Value("${ru.fedotov.replyTopics}")
    private String replyTopics;

    @Value("${kafka.group.id}")
    private String groupId;

    @Bean
    public ReplyingKafkaTemplate<String, Object, Object> replyingKafkaTemplate(
            ProducerFactory<String, Object> pf,
            ConcurrentMessageListenerContainer<String, Object> lc) {
        return new ReplyingKafkaTemplate<>(pf, lc);
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, Object> replyListenerContainer(
            ConcurrentKafkaListenerContainerFactory<String, Object> containerFactory) {
            ConcurrentMessageListenerContainer<String, Object> replyContainer = containerFactory.createContainer(replyTopics);
            replyContainer.getContainerProperties().setGroupId(groupId);
            replyContainer.setAutoStartup(false);
            return replyContainer;
    }
}