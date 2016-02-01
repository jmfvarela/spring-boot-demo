package io.galileo.service;

import io.galileo.arq.DataNotFoundException;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service("dataService")
public class DataServiceImpl<T> implements DataService<T> {

	private static final Logger LOG = LoggerFactory.getLogger(DataServiceImpl.class);
	
	
	public List<T> list(JpaRepository<T, Long> jpaRepository) {
		LOG.info("list - init");
		return jpaRepository.findAll();		
	}
	
	public T get(Long id, JpaRepository<T, Long> jpaRepository) {
		LOG.info("get - init");
		T object = jpaRepository.findOne(id);
		if (object==null) throw new DataNotFoundException(id.toString());
		return object;		
	}
	
	public T save(T object, JpaRepository<T, Long> jpaRepository) {
		LOG.info("save - init");
		T objectTemp = null;
		
		Long id=null;
		Integer version=null;
		try {
			id=(Long)PropertyUtils.getProperty(object, "id"); 
			version=(Integer)PropertyUtils.getProperty(object, "version");
		} catch (Exception e) {
			LOG.error("Error accessing to id/version of bean",e);
		} 

		if (id!=null) {					
			objectTemp=jpaRepository.findOne(id);
			if (objectTemp==null) throw new DataNotFoundException(id.toString());
			if (version==null)
				try {
					PropertyUtils.setProperty(object, "version", 0);
				} catch (Exception e) {
					LOG.error("Error accessing to version of bean",e);
				} // In order to not create a new one
		}
		else {
			try {
				PropertyUtils.setProperty(object, "version", null);
			} catch (Exception e) {
				LOG.error("Error accessing to version of bean",e);
			}
		}
		objectTemp=jpaRepository.save(object);

		return objectTemp;
	}
	
	public Long delete(Long id, JpaRepository<T, Long> jpaRepository) {
		LOG.info("delete - init");
		try {
			jpaRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new DataNotFoundException(id.toString());
		}
		return id;
	}

	
}
