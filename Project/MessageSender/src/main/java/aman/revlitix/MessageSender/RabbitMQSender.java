package aman.revlitix.MessageSender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void sendMessage(String msg){

        rabbitTemplate.convertAndSend(MyConfig.EXCHANGE,MyConfig.ROUTING_KEY,  msg );

    }

}
