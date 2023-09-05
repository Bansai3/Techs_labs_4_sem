package ru.fedotov.endpoint.kafka_services;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class KafkaService {
    @Autowired
    private ReplyingKafkaTemplate<String, Object, Object> replyingKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public <T> T sendSync(String topic, Object value) throws ExecutionException, InterruptedException {
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topic, value);
        RequestReplyFuture<String, Object, Object> requestReplyFuture = replyingKafkaTemplate.sendAndReceive(producerRecord);
        Object result = requestReplyFuture.get().value();
        return (T) result;
    }

    public void sendAsync(String topic, Object value) {
        kafkaTemplate.send(topic, value);
    }
}
