package aman.revlitix.LibraryManagementSystem.Controller;

import aman.revlitix.LibraryManagementSystem.Models.Student;
import aman.revlitix.LibraryManagementSystem.Services.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbitMQ")
public class RabbitMQWebController {


    @Autowired
    private RabbitMQSender rabbitMQSender;


    @PostMapping("/student")
    public String producer() {

        rabbitMQSender.SendDetails();

        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }

}
