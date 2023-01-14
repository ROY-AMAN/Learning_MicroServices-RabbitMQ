package aman.revlitix.MessageSender;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyConfig {

//    @Value("${spring.rabbitmq.password}")
//    private String password;


    public static final String QUEUE="MicroQueue";
    public static final String EXCHANGE="TwoApp";
    public static final String ROUTING_KEY="password";


    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind((org.springframework.amqp.core.Queue) queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate getTemplate(ConnectionFactory connectionFactory) {
      RabbitTemplate template = new RabbitTemplate(connectionFactory);

        template.setMessageConverter(messageConverter());

        return template;
    }


}
