package aman.revlitix.LibraryManagementSystem.Controller;

import aman.revlitix.LibraryManagementSystem.Models.Book;
import aman.revlitix.LibraryManagementSystem.Payloads.ApiResponse;
import aman.revlitix.LibraryManagementSystem.Services.BookServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/book")
public class BookController {

    @Autowired
    private BookServices bookServices;

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book){
        Book addBook = this.bookServices.addBook(book);

        return new ResponseEntity<>(addBook, HttpStatus.CREATED);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable String bookId){
        Book book = this.bookServices.getBookById(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);

    }
    //im adding here a comment as a  Example for testing conflict

    @GetMapping("/")
    public ResponseEntity<List<Book>> getAllBook(){
        List<Book> allBook = this.bookServices.getAllBook();

        return new ResponseEntity<>(allBook, HttpStatus.OK);
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<Book> updateBookById(@PathVariable String bookId,@Valid @RequestBody Book book){
        Book newBook = this.bookServices.updateBookById(bookId, book);

        return new ResponseEntity<>(book, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse> deleteBookById(@PathVariable String bookId){
        ApiResponse apiResponse = this.bookServices.deleteBookById(bookId);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }


    @GetMapping("/status/{bookId}")
    public ResponseEntity<ApiResponse> checkBookStatus(@PathVariable String bookId){
        ApiResponse response = this.bookServices.checkBookStatus(bookId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}
