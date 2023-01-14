package aman.revlitix.LibraryManagementSystem.Repository;

import aman.revlitix.LibraryManagementSystem.Models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepo extends MongoRepository<Student, String> {

    @Query(" {$and : [{'name' : {$regex : ?0 , $options : 'i' }} , { 'age' : { $gte : ?1 }}] }")
    public List<Student> getAllStudentsContains(String name, Integer age);

    @Query(" { 'age' : { $gte : ?0 , $lt : ?1 } }")
    public List<Student> findByAgeBetween (Integer ageGTE, Integer ageLT);

    @Query("{ 'name' : {$regex : ?0 , $options : 'i' } } ")
    public List<Student> findByMatchingName(String name);
}
