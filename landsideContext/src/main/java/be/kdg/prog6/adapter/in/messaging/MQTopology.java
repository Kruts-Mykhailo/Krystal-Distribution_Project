package be.kdg.prog6.adapter.in.messaging;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class MQTopology {

    public static final String WAREHOUSE_FULLNESS_QUEUE = "warehouse_capacity_change";
    public static final String PAYLOAD_DELIVERY_TICKET_EXCHANGE = "payload_delivery_exchange";
    public static final String PAYLOAD_DELIVERY_TICKET_QUEUE = "payload_delivery_ticket_queue";
    public static final String PAYLOAD_DELIVERY_QUEUE = "payload_delivery_queue";

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    TopicExchange pdtExchange() {
        return new TopicExchange(PAYLOAD_DELIVERY_TICKET_EXCHANGE);
    }

    @Bean
    Queue pdtReceivedQueue() {
        return new Queue(PAYLOAD_DELIVERY_TICKET_QUEUE);
    }


    @Bean
    Binding bindingPdtReceived(TopicExchange exchange, Queue pdtReceivedQueue) {
        return BindingBuilder
                .bind(pdtReceivedQueue)
                .to(exchange)
                .with("landside.#.pdt.received");

    }

    @Bean
    Queue payloadDeliveryQueue() {
        return new Queue(PAYLOAD_DELIVERY_QUEUE);
    }

    @Bean
    Binding bindingPayloadDelivery(TopicExchange exchange, Queue payloadDeliveryQueue) {
        return BindingBuilder
                .bind(payloadDeliveryQueue)
                .to(exchange)
                .with("landside.#.payload.delivery");
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;

    }



}
