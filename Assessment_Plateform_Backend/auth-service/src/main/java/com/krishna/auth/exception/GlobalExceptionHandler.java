//package com.krishna.auth.exception;
//
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.NoSuchElementException;
//
//import org.springframework.cloud.gateway.support.ServiceUnavailableException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import com.krishna.auth.util.ErrorResponse;
//
//import org.springframework.validation.FieldError;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    /**
//     * Exception handles when arguments does not contain valid input.
//     * @return errorMap
//     * @param exp methodArgumentNotValidException
//     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public final ResponseEntity<ErrorResponse>
//      handleMethodArgumentNotValidException(
//            final MethodArgumentNotValidException exp) {
//        Map<String, String> response = new HashMap<>();
//        exp.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String message = error.getDefaultMessage();
//            response.put(fieldName, message);
//        });
//        ErrorResponse errorResponse = new ErrorResponse(
//                HttpStatus.BAD_REQUEST.value(), response);
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    /**
//     * Handles NoSuchElementException by returning a response with a "Not Found"
//     * status.
//     * @param exception The NoSuchElementException to handle.
//     * @return A ResponseEntity with a "Not Found" status and an error message.
//     */
//    @ExceptionHandler(NoSuchElementException.class)
//    public final ResponseEntity<ErrorResponse> handleNoSuchElement(
//            final NoSuchElementException exception) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                HttpStatus.NOT_FOUND.value(), exception.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//    }
//
//
//
//
//    /**
//     * Handles ResourceNotFoundException by returning a response with a "Not
//     * Found" status.
//     * @param exception The ResourceNotFoundException to handle.
//     * @return A ResponseEntity with a "Not Found" status and an error message.
//     */
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public final ResponseEntity<ErrorResponse> handleResourceNotFound(
//            final ResourceNotFoundException exception) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                HttpStatus.NOT_FOUND.value(), exception.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//    }
//
//    /**
//     * Handles HttpMessageNotReadableException by returning.
//     *  a response with a "Not Found" status.
//     * @param exception The UserNotFoundException to handle.
//     * @return A ResponseEntity with a "Not Found" status and an error message.
//     */
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public final ResponseEntity<ErrorResponse> handleInvilidRequest(
//            final HttpMessageNotReadableException exception) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                HttpStatus.BAD_REQUEST.value(),
//                "please enter the request body.");
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
//    
//    
//    /**
//     * Handles ServiceUnavailableException by returning a response with a "Service Unavailable"
//     * status.
//     * @param exception The ServiceUnavailableException to handle.
//     * @return A ResponseEntity with a "Service Unavailable" status and an error message.
//     */
//    @ExceptionHandler(ServiceUnavailableException.class)
//    public final ResponseEntity<ErrorResponse> handleServiceUnavailableException(
//            final ServiceUnavailableException exception) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                HttpStatus.SERVICE_UNAVAILABLE.value(), "Service Unavailable");
//        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
//    }
//}