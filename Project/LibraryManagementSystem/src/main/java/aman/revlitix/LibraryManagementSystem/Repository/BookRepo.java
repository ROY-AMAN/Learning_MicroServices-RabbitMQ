package aman.revlitix.LibraryManagementSystem.Repository;

import aman.revlitix.LibraryManagementSystem.Models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepo extends MongoRepository<Book, String> {


}
