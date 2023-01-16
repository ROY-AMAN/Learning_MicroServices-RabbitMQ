package aman.revlitix.LibraryManagementSystem.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChandanController {

    @PostMapping("/con")
    public String usingForLoop(){
        Integer j=0;
        for(int i=0; i<=10; i++){
            j+=i;
            System.out.println("Printing the Values "+ i);
        }
        return "Total Sum is "+j;
    }
}
///////////////testidknfo;sa