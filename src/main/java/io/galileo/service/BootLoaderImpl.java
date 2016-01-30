package io.galileo.service;

import io.galileo.domain.User;
import io.galileo.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BootLoaderImpl implements BootLoader {

	private static final Logger LOG = LoggerFactory.getLogger(BootLoaderImpl.class);

	@Autowired
	ServletContext servletContext;
	
	@Autowired
	UserRepository userRepository;

	@PostConstruct
	public void bootLoader() throws Exception {
	
		LOG.info("bootLoader - start");
		
		if ( userRepository.count()>0 ) return;
		
		User user= User.builder()
						.username("admin")
						.password("admin")
						.enabled(true)
						.authority("ROLE_ADMIN")
						.name("Default")
						.surname("Administrator")
						.build();

		userRepository.save(user);
		
		LOG.info("bootLoader - finish");
	}
	
}
