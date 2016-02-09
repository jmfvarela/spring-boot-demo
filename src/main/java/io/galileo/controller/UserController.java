package io.galileo.controller;

import io.galileo.domain.User;
import io.galileo.repository.UserRepository;
import io.galileo.service.DataService;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	DataService<User> dataService;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("")
	public @ResponseBody List<User> list() {
		return (List<User>)dataService.list(userRepository);		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody User get(@PathVariable Long id) {		
		return (User)dataService.get(id, userRepository);		
	}
	
	@RequestMapping(method={RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody User save(@RequestBody @Valid User user) {
		return (User)dataService.save(user, userRepository);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Long delete(@PathVariable Long id) {
		return dataService.delete(id, userRepository);
	}
	
}

