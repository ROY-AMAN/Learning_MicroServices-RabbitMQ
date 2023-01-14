package aman.revlitix.LibraryManagementSystem.Repository;

import aman.revlitix.LibraryManagementSystem.Models.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestsRepo extends MongoRepository<Request , String> {

}
