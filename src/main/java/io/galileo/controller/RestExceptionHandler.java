package io.galileo.controller;

import io.galileo.arq.DataNotFoundException;
import io.galileo.dto.ErrorDTO;
import io.galileo.dto.ValidationErrorDTO;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
 
@ControllerAdvice
public class RestExceptionHandler {
 
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody ErrorDTO dataNotFoundExceptionHandler(DataNotFoundException ex) {
        return new ErrorDTO("Data not found");
    }
    
    @ExceptionHandler({ObjectOptimisticLockingFailureException.class, HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    //TODO: otras excepciones de formato?
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody ErrorDTO BadRequestExceptionsHandler(Exception ex) {
        if (ex.getClass()==ObjectOptimisticLockingFailureException.class) 
        	return new ErrorDTO("Record has been modified");
        else if (ex.getClass()==HttpMessageNotReadableException.class)
        	return new ErrorDTO("Request message not readable");
        else if (ex.getClass()==MethodArgumentTypeMismatchException.class)
        	return new ErrorDTO("Data type in request is not correct");
        else
        	return new ErrorDTO("Bad request");        		
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
 
        return processFieldErrors(fieldErrors);
    }
    
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    public ErrorDTO HttpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException ex) {
        return new ErrorDTO(ex.getMessage());
    }
       
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody ErrorDTO exceptionHandler(Exception ex) {
        return new ErrorDTO(ex.toString());
    }
    
    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDTO dto = new ValidationErrorDTO();
 
        for (FieldError fieldError: fieldErrors) {
            String localizedErrorMessage = fieldError.getDefaultMessage(); //TODO: resolveLocalizedErrorMessage(fieldError);
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }
 
        return dto;
    }
 
//  private MessageSource messageSource;
//
//  @Autowired
//  public RestExceptionHandler(MessageSource messageSource) {
//      this.messageSource = messageSource;
//  }
//
//    private String resolveLocalizedErrorMessage(FieldError fieldError) {
//        Locale currentLocale =  LocaleContextHolder.getLocale();
//        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
// 
//        //If the message was not found, return the most accurate field error code instead.
//        //You can remove this check if you prefer to get the default error message.
//        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
//            String[] fieldErrorCodes = fieldError.getCodes();
//            localizedErrorMessage = fieldErrorCodes[0];
//        }
// 
//        return localizedErrorMessage;
//    }
    
    //TODO:
    //FORBIDDEN 403 Forbidden. ¿UNAUTHORIZED  401 Unauthorized ? --> spring security
	
}