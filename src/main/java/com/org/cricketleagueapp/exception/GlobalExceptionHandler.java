package com.org.cricketleagueapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
   @ExceptionHandler(value=TournamentNotFoundException.class)
   public ResponseEntity<String> handleResourceNotFoundException(Exception e){
   ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
   return responseEntity;
   }    
    
   @ExceptionHandler(value=OrganiserNotFoundException.class)
   public ResponseEntity<String> handleOrganiserNotFoundException(Exception e){
   ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
   return responseEntity;
   }   
   
   @ExceptionHandler(value=FranchaiseNotFoundException.class)
   public ResponseEntity<String> handleFranchaiseNotFoundException(Exception e){
   ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
   return responseEntity;
   }   
   
   @ExceptionHandler(value=MatchNotFoundException.class)
   public ResponseEntity<String> handleMatchNotFoundException(Exception e){
   ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
   return responseEntity;
   }   
   
   @ExceptionHandler(value=DuplicateDataException.class)
   public ResponseEntity<String> handleDuplicateDataException(Exception e){
   ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
   return responseEntity;
   }   
}