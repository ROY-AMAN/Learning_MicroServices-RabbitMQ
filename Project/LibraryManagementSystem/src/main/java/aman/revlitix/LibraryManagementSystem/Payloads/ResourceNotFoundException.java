package aman.revlitix.LibraryManagementSystem.Payloads;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue){
        super(resourceName+" is not found with "+fieldName+" "+fieldValue);

    }

}
