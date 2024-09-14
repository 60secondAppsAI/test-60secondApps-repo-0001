package com.bearbnb.dao;

import java.util.List;

import com.bearbnb.dao.GenericDAO;
import com.bearbnb.domain.Property;





public interface PropertyDAO extends GenericDAO<Property, Integer> {
  
	List<Property> findAll();
	






}


