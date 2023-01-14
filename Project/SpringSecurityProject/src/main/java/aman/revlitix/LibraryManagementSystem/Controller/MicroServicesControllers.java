package aman.revlitix.LibraryManagementSystem.Controller;

import aman.revlitix.LibraryManagementSystem.Dtos.ContactDto;
import aman.revlitix.LibraryManagementSystem.Dtos.CourseDto;
import aman.revlitix.LibraryManagementSystem.Models.Book;
import aman.revlitix.LibraryManagementSystem.Payloads.ApiResponse;
import com.mongodb.lang.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/microService")
public class MicroServicesControllers {

    @Autowired
    private RestTemplate restTemplate;

    private String url = "http://10.10.6.90:8080/mqsender/receiveHead";

//    private  String url = "https://riptutorial.com/spring/example/24622/setting-headers-on-spring-resttemplate-request";
    @GetMapping("/contacts")
    public ResponseEntity<List<ContactDto>> getAllContacts(){
        List<ContactDto> contacts = this.restTemplate.getForObject("http://10.10.6.50:8808/contact/", ArrayList.class );

        System.out.println(contacts);

        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/getHeader")
    public void getAllHeader(){

//        HttpHeaders headers = new HttpHeaders();

//        headers.setContentType(MediaType.ALL);

//        headers.set("Authorization", " JAY SHREE RAM!! ");
//
//
//        HttpEntity entity = new HttpEntity<>(MediaType.ALL, headers);

        RestTemplate restTemplate = new RestTemplate();

//        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);

//        ResponseEntity<HttpEntity> entity = restTemplate.getForEntity(url, HttpEntity.class);
//        System.out.println(entity.getHeaders()+" "+entity.getBody());

        ResponseEntity<Map> response = restTemplate.getForEntity("https://httpbin.org/user-agent", Map.class);
        System.out.println(response.getBody());

//        System.out.println(entity);
    }

    @PostMapping("/contact")
    public ApiResponse createContact( @RequestBody ContactDto contact){


        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.set("header", "Jay shree Ram!!");

        HttpEntity entity = new HttpEntity<>(contact, headers);

        ResponseEntity<Object> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);


        System.out.println(exchange);


        return new ApiResponse("New Contact with a header created successfully!", true);

    }

    @PostMapping("/postheader")
    public String sendHeader(@RequestBody String msg){

        System.out.println("heated you..");
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("header","This is my header bro!!");

        HttpEntity entity = new HttpEntity<>(msg, headers);


     return  restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();

    }
}
