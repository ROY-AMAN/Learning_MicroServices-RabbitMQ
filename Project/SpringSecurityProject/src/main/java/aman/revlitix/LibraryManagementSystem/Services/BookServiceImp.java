package aman.revlitix.LibraryManagementSystem.Services;

import aman.revlitix.LibraryManagementSystem.Models.Book;
import aman.revlitix.LibraryManagementSystem.Models.Student;
import aman.revlitix.LibraryManagementSystem.Payloads.ApiResponse;
import aman.revlitix.LibraryManagementSystem.Payloads.BookAllocatedException;
import aman.revlitix.LibraryManagementSystem.Payloads.ResourceNotFoundException;
import aman.revlitix.LibraryManagementSystem.Repository.BookRepo;
import aman.revlitix.LibraryManagementSystem.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImp implements BookServices{

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private StudentRepo studentRepo;

    @Override
    public Book addBook(Book book) {
        return this.bookRepo.save(book);
    }

    @Override
    public Book getBookById(String id) {
        Book book = this.bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "Id", id));

        return book;
    }

    @Override
    public List<Book> getAllBook() {
        return this.bookRepo.findAll();
    }

    @Override
    public Book updateBookById(String id, Book book) {
        Book prevBook = this.bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", id));

//        System.out.println(prevBook);

        prevBook.setName(book.getName());
        prevBook.setPrice(book.getPrice());
        prevBook.setCategory(book.getCategory());
//        prevBook.setQuantity(book.getQuantity());

        return this.bookRepo.save(prevBook);
    }

    @Override
    public ApiResponse deleteBookById(String id) {
        Book book = this.bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", id));
        this.bookRepo.delete(book);
//        this.bookRepo.deleteById(id);

        return new ApiResponse("Book has deleted successfully", true);
    }

    @Override
    public ApiResponse checkBookStatus(String bookId) {
        Book book = this.bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "BookId", bookId));

        String status = book.getAllocatedStatus();

        if(status==null)
            throw new BookAllocatedException("Book is not allocated to anyone");

        Student student = this.studentRepo.findById(status).orElseThrow(() -> new BookAllocatedException("This book is allocated to some unknown student!"));

        return new ApiResponse("This book is allocated to "+student , true);
    }
}
