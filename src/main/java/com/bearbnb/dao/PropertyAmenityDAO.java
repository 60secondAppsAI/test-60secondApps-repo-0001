package com.bearbnb.dao;

import java.util.List;

import com.bearbnb.dao.GenericDAO;
import com.bearbnb.domain.PropertyAmenity;





public interface PropertyAmenityDAO extends GenericDAO<PropertyAmenity, Integer> {
  
	List<PropertyAmenity> findAll();
	






}


