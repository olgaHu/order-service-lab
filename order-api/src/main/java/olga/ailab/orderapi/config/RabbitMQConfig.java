package olga.ailab.orderapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import olga.ailab.common.config.RabbitOrderProperties;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final RabbitOrderProperties rabbitOrderProperties;
    private final AmqpAdmin amqpAdmin;


    @Bean
    public Exchange orderExchange() {
        return ExchangeBuilder
                .directExchange(rabbitOrderProperties.getExchange())
                .durable(true)
                .build();
    }

    @Bean
    public Queue orderQueue() {
        return QueueBuilder
                .durable(rabbitOrderProperties.getQueue())
                .build();
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, Exchange orderExchange) {
        return BindingBuilder
                .bind(orderQueue)
                .to(orderExchange)
                .with(rabbitOrderProperties.getRoutingKey())
                .noargs();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void declareRabbitElements() {

        System.out.println("==== RabbitMQ 宣告資訊 ====");
        System.out.println("Exchange: " + rabbitOrderProperties.getExchange());
        System.out.println("Queue   : " + rabbitOrderProperties.getQueue());
        System.out.println("Routing : " + rabbitOrderProperties.getRoutingKey());


        Exchange exchange = ExchangeBuilder
                .directExchange(rabbitOrderProperties.getExchange())
                .durable(true)
                .build();
        Queue queue = QueueBuilder
                .durable(rabbitOrderProperties.getQueue())
                .build();
        Binding binding = BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(rabbitOrderProperties.getRoutingKey())
                .noargs();

        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(binding);

        System.out.println("✅ RabbitMQ Exchange/Queue/Binding 宣告完成！");
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}