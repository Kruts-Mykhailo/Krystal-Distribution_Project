package be.kdg.prog6.adapters.in.messaging;

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

    public static final String FINISH_OPERATIONS_QUEUE = "finish_operations_queue";
    public static final String FINISH_OPERATIONS_EXCHANGE = "finish_operations_exchange";

    @Bean
    TopicExchange finishOperationsExchange() {
        return new TopicExchange(FINISH_OPERATIONS_EXCHANGE);
    }

    @Bean
    Queue finishOperationsQueue() {
        return new Queue(FINISH_OPERATIONS_QUEUE, true);
    }

    @Bean
    Binding bindingFinishOperations(TopicExchange exchange, Queue queue) {
        return BindingBuilder.
                bind(queue).
                to(exchange).
                with("operations.#.finished");
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
