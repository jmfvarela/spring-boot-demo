package io.galileo.repository;

import io.galileo.domain.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByEmail(String email);
	
	User findByEmailAndToken(String email, Long token);
}
