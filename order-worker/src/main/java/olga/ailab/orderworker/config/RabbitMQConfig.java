package olga.ailab.orderworker.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import olga.ailab.common.config.RabbitOrderProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;


@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final RabbitOrderProperties rabbitOrderProperties;



    @Bean
    public Exchange orderExchange() {
        return ExchangeBuilder.directExchange(rabbitOrderProperties.getExchange()).durable(true).build();
    }

    @Bean
    public Queue orderQueue() {
        return QueueBuilder.durable(rabbitOrderProperties.getQueue()).build();
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, Exchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange)
                .with(rabbitOrderProperties.getRoutingKey()).noargs();
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return new Jackson2JsonMessageConverter(mapper);
    }


    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter messageConverter
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }
}