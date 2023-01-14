package aman.revlitix.LibraryManagementSystem.Services;

import aman.revlitix.LibraryManagementSystem.Payloads.MyConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {

    @RabbitListener(queues = MyConfig.QUEUE)
    public void getRabbitMessage( String message){

        System.out.println(message);
    }
}
