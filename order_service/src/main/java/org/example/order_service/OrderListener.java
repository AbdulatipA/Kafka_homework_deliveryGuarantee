package org.example.order_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrderListener {
    private final ObjectMapper mapper;
    private final OrderService orderService;

    @KafkaListener(topics = "${spring.kafka.consumer.topics}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String client) {

        try {
            EventPerson eventPerson = mapper.readValue(client, EventPerson.class);
            orderService.saveOrder(eventPerson);
            log.info("Событие eventPerson обработано: {}", eventPerson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
