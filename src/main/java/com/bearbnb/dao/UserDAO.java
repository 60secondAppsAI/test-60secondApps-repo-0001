package com.bearbnb.dao;

import java.util.List;

import com.bearbnb.dao.GenericDAO;
import com.bearbnb.domain.User;

import java.util.Optional;




public interface UserDAO extends GenericDAO<User, Integer> {
  
	List<User> findAll();
	






}


