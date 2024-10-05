package be.kdg.prog6.adapter.in.messaging;

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
    Jackson2JsonMessageConverter consumerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    TopicExchange warehouseCapacityExchange() {
        return new TopicExchange(WAREHOUSE_FULLNESS_EXCHANGE);
    }

    @Bean
    Queue warehouseCapacityQueue() {
        return new Queue(WAREHOUSE_FULLNESS_QUEUE, true);
    }
    @Bean
    Binding bindingPdtReceived(TopicExchange exchange, Queue queue) {
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
    Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
