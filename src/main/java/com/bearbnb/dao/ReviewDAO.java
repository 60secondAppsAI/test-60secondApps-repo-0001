package com.bearbnb.dao;

import java.util.List;

import com.bearbnb.dao.GenericDAO;
import com.bearbnb.domain.Review;





public interface ReviewDAO extends GenericDAO<Review, Integer> {
  
	List<Review> findAll();
	






}


