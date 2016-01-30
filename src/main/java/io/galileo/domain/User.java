package io.galileo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Tolerate;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@ToString(exclude="id")
@Builder
public class User {
	
	@Tolerate
	User() { // jpa only
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter 
    private Long id;

	@Version
    @Getter @Setter 
    private Integer version;
	
	@NotNull
	@Size(min=1, max=240)
    @Getter @Setter 
    private String username;
    
	@NotNull
    @NotBlank
    @Getter @Setter 
    private String name;
	
	@NotNull
    @NotBlank
    @Getter @Setter 
    private String surname;
	
	@Email
    @Getter @Setter 
    private String email;
    
    @Getter @Setter 
    private String photo;

	@NotNull
    @NotBlank
    @JsonIgnore
    @Getter 
    private String password;
	
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	@NotNull
    @Getter @Setter 
	private boolean enabled;
	
    @Getter @Setter 
    private String authority;

    @Getter @Setter 
    private long token;
    
    //TODO only for tests
    @AssertTrue
    @JsonIgnore
	public boolean isValidTest() {
    	return ("test".equals(name) ) ? false : true;
	}

}
