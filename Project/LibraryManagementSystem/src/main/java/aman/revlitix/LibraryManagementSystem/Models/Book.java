package aman.revlitix.LibraryManagementSystem.Models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Book {

    @Id
    private String bookId;

  @NotEmpty(message = "Name must not be empty!")
    private String name;
    private String category;

    @PositiveOrZero(message = "Price must be negative!")
    @NotEmpty
    private Integer price;
    private String allocatedStatus;

}
