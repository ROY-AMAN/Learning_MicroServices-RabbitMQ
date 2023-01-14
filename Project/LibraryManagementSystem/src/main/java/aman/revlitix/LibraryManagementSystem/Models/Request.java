package aman.revlitix.LibraryManagementSystem.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Request {
    @Id
    private String tokerId;

    private String message;

}
