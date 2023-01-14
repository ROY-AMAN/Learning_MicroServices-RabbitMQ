package aman.revlitix.LibraryManagementSystem.Controller;

import aman.revlitix.LibraryManagementSystem.Models.Book;
import aman.revlitix.LibraryManagementSystem.Models.Student;
import aman.revlitix.LibraryManagementSystem.Payloads.ApiResponse;
import aman.revlitix.LibraryManagementSystem.Services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/app/student")
public class StudentController {

    @Autowired
    private StudentService studentServicesImp;

    //CREATE
    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestHeader(value = "Accept") String token, @RequestHeader(value = "Authorization") String authorMessage, @RequestBody Student student) {

        HttpHeaders headers = new HttpHeaders();

        headers.set(token, authorMessage);

        HttpEntity entity = new HttpEntity<>("", headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Object> response = restTemplate.exchange("localhost:8080/app/student/add", HttpMethod.GET, entity, Object.class);

        System.out.println(response.getHeaders()+" "+response.getBody());

        Student st = this.studentServicesImp.addStudent(student);

        return new ResponseEntity<>(st, HttpStatus.CREATED);
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Student student = this.studentServicesImp.getStudentById(id);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getStudentById() {
        List<Student> students = this.studentServicesImp.getAllStudent();

        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // UPDATE

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudentById(@PathVariable String id, @Valid @RequestBody Student student) {

        Student updateStudent = this.studentServicesImp.updateStudent(id, student);

        return new ResponseEntity<>(updateStudent, HttpStatus.CREATED);
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable String id) {

        String response = this.studentServicesImp.deleteStudentById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/allot/{studentId}/{bookId}")
    public ResponseEntity<List<Book>> allotBookToStudent(@PathVariable String studentId, @PathVariable String bookId) {
        List<Book> books = this.studentServicesImp.allotBookToStudent(studentId, bookId);
        return new ResponseEntity<>(books, HttpStatus.OK);

    }

    @PutMapping("/deallocate/{bookId}")
    public ResponseEntity<ApiResponse> deAllocateBook(@PathVariable String bookId) {
        ApiResponse response = this.studentServicesImp.deAllocateBook(bookId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("books/{studentId}")
    public ResponseEntity<List<Book>> getAllocatedBooksOfStudent(@PathVariable String studentId) {

        List<Book> booksOfStudent = this.studentServicesImp.getAllocatedBooksOfStudent(studentId);

        return new ResponseEntity<>(booksOfStudent, HttpStatus.OK);

    }

    @GetMapping("/agefilter")
    public ResponseEntity<List<Student>> getAllByNameAgeGraterThan(@RequestParam(name = "name") String name, @RequestParam(name = "age") Integer age) {
        List<Student> studentList = this.studentServicesImp.getAllStudentsContains(name, age);
        System.out.println("Controller--> JAY SHREE RAM!");
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }

    @GetMapping("/age")
    public ResponseEntity<List<Student>> findByAgeBetween (@RequestParam(name = "ageGTE") Integer ageGTE, @RequestParam(name = "ageLT") Integer ageLT){

        System.out.println("jay ho!");
        List<Student> students = this.studentServicesImp.findByAgeBetween(ageGTE, ageLT);
        System.out.println("jay ho!!");

        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Student>> findByNameMatching (@RequestParam(name = "name") String name){

        List<Student> students = this.studentServicesImp.getStudentsByName(name);

        return new ResponseEntity<>(students, HttpStatus.OK);
    }

}