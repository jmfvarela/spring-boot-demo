package io.galileo.dto;

import lombok.Getter;
import lombok.Setter;

public class FieldErrorDTO {
	
	@Getter @Setter
    private String field;
 
	@Getter @Setter
    private String message;
 
    public FieldErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }
 
}