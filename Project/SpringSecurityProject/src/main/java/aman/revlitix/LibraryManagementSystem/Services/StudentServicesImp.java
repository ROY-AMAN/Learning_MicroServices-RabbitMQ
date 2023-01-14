package aman.revlitix.LibraryManagementSystem.Services;

import aman.revlitix.LibraryManagementSystem.Dtos.CourseDto;
import aman.revlitix.LibraryManagementSystem.Models.Book;
import aman.revlitix.LibraryManagementSystem.Models.Student;
import aman.revlitix.LibraryManagementSystem.Payloads.ApiResponse;
import aman.revlitix.LibraryManagementSystem.Payloads.BookAllocatedException;
import aman.revlitix.LibraryManagementSystem.Payloads.ResourceNotFoundException;
import aman.revlitix.LibraryManagementSystem.Repository.BookRepo;
import aman.revlitix.LibraryManagementSystem.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class StudentServicesImp implements StudentService{
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private RestTemplate restTemplate;

    public Student addStudent( Student student){
//        student.setName(student.getName().toLowerCase());
        return studentRepo.save(student);
    }

    public Student updateStudent(String id, Student student) {
        System.out.println(id);
        Student prevStudent = studentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student", "ObjectId", id));

        System.out.println(prevStudent);

        prevStudent.setName(student.getName());
        prevStudent.setRoll(student.getRoll());
        prevStudent.setAge(student.getAge());
        prevStudent.setStandard(student.getStandard());

        return studentRepo.save(prevStudent);
    }

    @Override
    public Student getStudentById(String id) {
        Student student = this.studentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student", "ObjectId", id));

        return student;

    }

    @Override
    public List<Student> getAllStudent() {
        List<Student> students = this.studentRepo.findAll();
        return students;
    }

    @Override
    public String deleteStudentById(String id) {
        Student student = this.studentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student", "ObjectId", id));

        this.studentRepo.deleteById(id);
        return "This student is successfully deleted";
    }

    @Override
    public List<Book> allotBookToStudent(String studentId, String bookId) {

        Student student = this.studentRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "StudentsId", studentId));

        Book book = this.bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "ObjectId", bookId));

        if((book.getAllocatedStatus())!= null){
            throw new BookAllocatedException("This book is already allocated");
        }
        //saving student details in book
        book.setAllocatedStatus(studentId);
        this.bookRepo.save(book);


        // allocate book to the student
        List<Book> books = student.getBooks();
        books.add(book);

        student.setBooks(books);

        this.studentRepo.save(student);

        return books;

    }

    @Override
    public ApiResponse deAllocateBook(String bookId) {

        Book book = this.bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "ObjectId", bookId));

        if(book.getAllocatedStatus()==null){
            throw new BookAllocatedException("This is not allocated yet!");
        }

        Student student = this.studentRepo.findById(book.getAllocatedStatus()).orElseThrow(() -> new ResourceNotFoundException("Student", "StudentsId", book.getAllocatedStatus()));

        List<Book> books = student.getBooks();

        System.out.println(books);

//        Boolean flag = books.remove(book);

        Predicate<? super Book> predicate= (b)-> b.getBookId().equals(bookId);
        List<Book> bookList = books.stream().filter(predicate).collect(Collectors.toList());

        Boolean flag = books.removeAll(bookList);

//        System.out.println(bookList+"  "+ flag);

        student.setBooks(books);

        this.studentRepo.save(student);

        //Removing the student details from the book
        book.setAllocatedStatus(null);
        this.bookRepo.save(book);


        return new ApiResponse("Book is deallocated successfully", true);

    }

    @Override
    public List<Book> getAllocatedBooksOfStudent(@PathVariable String studentId) {
        Student student = this.studentRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "ObjectId", studentId));

        return student.getBooks();

    }

    @Override
    public List<Student> getAllStudentsContains(String name, Integer age) {

        return this.studentRepo.getAllStudentsContains("^"+name,age);
    }

    @Override
    public List<Student> findByAgeBetween(Integer age1, Integer age2 ) {
        return this.studentRepo.findByAgeBetween(age1, age2);
    }

    @Override
    public List<Student> getStudentsByName(String name) {
        return this.studentRepo.findByMatchingName(name);
    }

    @Override
    public ApiResponse enrollForCourse(String studentId, String courseId) {

        // get student by Id
        Student student = this.studentRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "studentId", studentId));


        // get courseDTO by courseId from chandan's Database
        CourseDto course = this.restTemplate.getForObject("10.10.1.08:student/course/"+courseId, CourseDto.class);

        List<CourseDto> enrolledCourses = student.getEnrolledCourses();
        enrolledCourses.add(course);
        student.setEnrolledCourses(enrolledCourses);

        this.studentRepo.save(student);

        return new ApiResponse("Congratulations!, you got a new course..", true);

    }

}
