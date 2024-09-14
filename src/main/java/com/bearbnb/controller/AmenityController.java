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

import com.bearbnb.domain.Amenity;
import com.bearbnb.dto.AmenityDTO;
import com.bearbnb.dto.AmenitySearchDTO;
import com.bearbnb.dto.AmenityPageDTO;
import com.bearbnb.service.AmenityService;
import com.bearbnb.dto.common.RequestDTO;
import com.bearbnb.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/amenity")
@RestController
public class AmenityController {

	private final static Logger logger = LoggerFactory.getLogger(AmenityController.class);

	@Autowired
	AmenityService amenityService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Amenity> getAll() {

		List<Amenity> amenitys = amenityService.findAll();
		
		return amenitys;	
	}

	//@ReadAccess
	@GetMapping(value = "/{amenityId}")
	@ResponseBody
	public AmenityDTO getAmenity(@PathVariable Integer amenityId) {
		
		return (amenityService.getAmenityDTOById(amenityId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addAmenity", method = RequestMethod.POST)
	public ResponseEntity<?> addAmenity(@RequestBody AmenityDTO amenityDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = amenityService.addAmenity(amenityDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/amenitys")
	public ResponseEntity<AmenityPageDTO> getAmenitys(AmenitySearchDTO amenitySearchDTO) {
 
		return amenityService.getAmenitys(amenitySearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateAmenity", method = RequestMethod.POST)
	public ResponseEntity<?> updateAmenity(@RequestBody AmenityDTO amenityDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = amenityService.updateAmenity(amenityDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
