package be.kdg.prog6.adapter.in.messaging;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQTopology {
    public static final String PAYLOAD_DELIVERY_TICKET_QUEUE = "pdt_received";
    public static final String WAREHOUSE_FULLNESS_QUEUE = "warehouse_capacity_change";
    public static final String WAREHOUSE_FULLNESS_EXCHANGE = "warehouse_capacity_exchange";


    @Bean
    TopicExchange warehouseCapacityExchange() {
        return new TopicExchange(WAREHOUSE_FULLNESS_EXCHANGE);
    }


    @Bean
    Queue warehouseCapacityQueue() {
        return new Queue(WAREHOUSE_FULLNESS_QUEUE, true);
    }
    @Bean
    Binding bindingWarehouseCapacityChanged(TopicExchange exchange, Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("warehouse.#.capacity.changed");

    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;

    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
