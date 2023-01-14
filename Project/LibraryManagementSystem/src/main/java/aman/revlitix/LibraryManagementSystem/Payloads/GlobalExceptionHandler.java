package aman.revlitix.LibraryManagementSystem.Payloads;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> exceptionHandlerMethod(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse exRes = new ApiResponse(message,false);
        return new ResponseEntity<>(exRes, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BookAllocatedException.class)
    public ResponseEntity<ApiResponse> exceptionHandlerMethod(BookAllocatedException exception){
        String message = exception.getMessage();
        ApiResponse response = new ApiResponse(message, false);

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String message = ex.getMessage();
        ApiResponse response = new ApiResponse(message, false);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

}