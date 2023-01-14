package aman.revlitix.MessageSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mqsender")
public class RabbitController {

    @Autowired
    private RabbitMQSender sender;

    @PostMapping("/")
    public String sendMessage(@RequestBody String message){

        this.sender.sendMessage(message);

        return "Your message has send successfully";

    }

    @PostMapping("/receiveHead")
    public String getHeader(@RequestHeader(value = "header") String head, @RequestBody String message){

        System.out.println("HEAD: "+ head);
        System.out.println("Body: "+ message);

        return head;

    }

}
