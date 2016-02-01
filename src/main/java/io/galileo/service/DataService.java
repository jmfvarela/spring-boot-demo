package io.galileo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DataService<T> {

	List<T> list(JpaRepository<T, Long> jpaRepository);
	
	T get(Long id, JpaRepository<T, Long> jpaRepository);
	
	T save(T object, JpaRepository<T, Long> jpaRepository);
	
	Long delete(Long id, JpaRepository<T, Long> jpaRepository);
}
