package aman.revlitix.LibraryManagementSystem.Services;

import aman.revlitix.LibraryManagementSystem.Models.Book;
import aman.revlitix.LibraryManagementSystem.Models.Student;
import aman.revlitix.LibraryManagementSystem.Payloads.ApiResponse;
import io.swagger.annotations.Api;

import java.util.List;

public interface StudentService {

    public Student addStudent(Student s);

    public Student updateStudent(String id, Student s) ;

    public Student getStudentById(String id);
    public List<Student> getAllStudent();

    public String deleteStudentById(String id);


    public List<Book> allotBookToStudent(String studentId, String bookId);

    public ApiResponse deAllocateBook( String bookId);

    public List<Book> getAllocatedBooksOfStudent(String studentId);

    List<Student> getAllStudentsContains(String name, Integer age);

    public List<Student> findByAgeBetween (Integer ageGTE, Integer ageLT);

    public List<Student> getStudentsByName (String name);

    public ApiResponse enrollForCourse(String studentId, String courseId);


}
