package com.bearbnb.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.bearbnb.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.bearbnb.domain.Reservation;
import com.bearbnb.dto.ReservationDTO;
import com.bearbnb.dto.ReservationSearchDTO;
import com.bearbnb.dto.ReservationPageDTO;
import com.bearbnb.service.ReservationService;
import com.bearbnb.dto.common.RequestDTO;
import com.bearbnb.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/reservation")
@RestController
public class ReservationController {

	private final static Logger logger = LoggerFactory.getLogger(ReservationController.class);

	@Autowired
	ReservationService reservationService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Reservation> getAll() {

		List<Reservation> reservations = reservationService.findAll();
		
		return reservations;	
	}

	//@ReadAccess
	@GetMapping(value = "/{reservationId}")
	@ResponseBody
	public ReservationDTO getReservation(@PathVariable Integer reservationId) {
		
		return (reservationService.getReservationDTOById(reservationId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addReservation", method = RequestMethod.POST)
	public ResponseEntity<?> addReservation(@RequestBody ReservationDTO reservationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = reservationService.addReservation(reservationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/reservations")
	public ResponseEntity<ReservationPageDTO> getReservations(ReservationSearchDTO reservationSearchDTO) {
 
		return reservationService.getReservations(reservationSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateReservation", method = RequestMethod.POST)
	public ResponseEntity<?> updateReservation(@RequestBody ReservationDTO reservationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = reservationService.updateReservation(reservationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
