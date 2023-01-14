package aman.revlitix.LibraryManagementSystem.Dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class ContactDto {

     @Id
     private String cid;
     private String email;
     private String contactName;
     private String userId;


}
