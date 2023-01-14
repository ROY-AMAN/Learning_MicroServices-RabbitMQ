package aman.revlitix.LibraryManagementSystem.Models;

import aman.revlitix.LibraryManagementSystem.Dtos.CourseDto;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class Student {
    @Id
    private String id;

    @Indexed(unique = true)
    @Positive(message = "Roll number must be unique and positive" )
    private Integer roll;

    @Size(min = 2, message = "Name must be at least of 2 letters!")
    private String name;

    @PositiveOrZero( message = "Standard can't be in negative!!")
    private Integer standard;

    @Positive(message = "Age can't be negative!!")
    private Integer age;

    private List<Book> books = new ArrayList<>();

    private List<CourseDto> enrolledCourses = new ArrayList<>();

}
