package aman.revlitix.LibraryManagementSystem.Services;

import aman.revlitix.LibraryManagementSystem.Payloads.MyConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void SendDetails(){
        String s="Hello, mitron kaise hain aap sab!!";

        rabbitTemplate.convertAndSend(MyConfig.EXCHANGE,MyConfig.ROUTING_KEY,s);
    }

}
