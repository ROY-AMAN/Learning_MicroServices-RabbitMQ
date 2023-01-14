package aman.revlitix.LibraryManagementSystem.Services;

import aman.revlitix.LibraryManagementSystem.Models.Book;
import aman.revlitix.LibraryManagementSystem.Payloads.ApiResponse;

import java.util.List;

public interface BookServices {

    public Book addBook(Book book);

    public Book getBookById(String id);

    public List<Book> getAllBook();

    public Book updateBookById(String id, Book book);

    public ApiResponse deleteBookById(String id);

    public ApiResponse checkBookStatus(String bookId);

}
