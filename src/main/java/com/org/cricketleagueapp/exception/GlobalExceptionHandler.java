package com.org.cricketleagueapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
	@ExceptionHandler(value=AudienceNotFoundException.class)
    public ResponseEntity<String> handleAudienceNotFoundException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    }   
	
    @ExceptionHandler(value=DuplicateDataException.class)
    public ResponseEntity<String> handleDuplicateDataException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    }
    
    @ExceptionHandler(value=FranchaiseNotFoundException.class)
    public ResponseEntity<String> handleFranchaiseNotFoundException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    }  
    
    @ExceptionHandler(value=GroundAlreadyBookedException.class)
    public ResponseEntity<String> handleGroundAlreadyBookedException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    }  
    
    @ExceptionHandler(value=GroundNotFoundException.class)
    public ResponseEntity<String> handleGroundNotFoundException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    }  
    
    @ExceptionHandler(value=InsufficientBalanceException.class)
    public ResponseEntity<String> handleInsufficientBalanceException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    } 
    
    @ExceptionHandler(value=MatchNotFoundException.class)
    public ResponseEntity<String> handleMatchNotFoundException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    }   
    
    @ExceptionHandler(value=MinimumTeamException.class)
    public ResponseEntity<String> handleMinimumTeamException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    }  
   	
    @ExceptionHandler(value=OrganiserNotFoundException.class)
    public ResponseEntity<String> handleOrganiserNotFoundException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    }
    
    @ExceptionHandler(value=PlayerNotFoundException.class)
    public ResponseEntity<String> handlePlayerNotFoundException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    }
    
    @ExceptionHandler(value=SeatNotAvailable.class)
    public ResponseEntity<String> handleSeatNotAvailable(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    }
    
    @ExceptionHandler(value=TeamNotFoundException.class)
    public ResponseEntity<String> handleTeamNotFoundException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    }   
    
    @ExceptionHandler(value=TicketNotFoundException.class)
    public ResponseEntity<String> handleTicketNotFoundException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    }
    
    @ExceptionHandler(value=TournamentDateTimeNotFoundException.class)
    public ResponseEntity<String> handleTournamentDateTimeNotFoundException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    }
    
    @ExceptionHandler(value=TournamentNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    }
    
    @ExceptionHandler(value=TournamentNotStartedException.class)
    public ResponseEntity<String> handleTournamentNotStartedException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    }
    
    @ExceptionHandler(value=TournamentPrizeMoneyNotSetException.class)
    public ResponseEntity<String> handleTournamentPrizeMoneyNotSetException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    }
    
    @ExceptionHandler(value=TournamentStartedException.class)
    public ResponseEntity<String> handleTournamentStartedException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    }   
    
    @ExceptionHandler(value=UnknownEnumValueException.class)
    public ResponseEntity<String> handleUnknownEnumValueException(Exception e){
	    ResponseEntity<String> responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	    return responseEntity;
    } 
}