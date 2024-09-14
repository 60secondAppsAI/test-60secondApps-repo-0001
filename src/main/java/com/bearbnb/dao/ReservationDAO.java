package com.bearbnb.dao;

import java.util.List;

import com.bearbnb.dao.GenericDAO;
import com.bearbnb.domain.Reservation;





public interface ReservationDAO extends GenericDAO<Reservation, Integer> {
  
	List<Reservation> findAll();
	






}


