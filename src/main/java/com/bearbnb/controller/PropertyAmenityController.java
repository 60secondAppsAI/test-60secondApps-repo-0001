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

import com.bearbnb.domain.PropertyAmenity;
import com.bearbnb.dto.PropertyAmenityDTO;
import com.bearbnb.dto.PropertyAmenitySearchDTO;
import com.bearbnb.dto.PropertyAmenityPageDTO;
import com.bearbnb.service.PropertyAmenityService;
import com.bearbnb.dto.common.RequestDTO;
import com.bearbnb.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/propertyAmenity")
@RestController
public class PropertyAmenityController {

	private final static Logger logger = LoggerFactory.getLogger(PropertyAmenityController.class);

	@Autowired
	PropertyAmenityService propertyAmenityService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<PropertyAmenity> getAll() {

		List<PropertyAmenity> propertyAmenitys = propertyAmenityService.findAll();
		
		return propertyAmenitys;	
	}

	//@ReadAccess
	@GetMapping(value = "/{propertyAmenityId}")
	@ResponseBody
	public PropertyAmenityDTO getPropertyAmenity(@PathVariable Integer propertyAmenityId) {
		
		return (propertyAmenityService.getPropertyAmenityDTOById(propertyAmenityId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addPropertyAmenity", method = RequestMethod.POST)
	public ResponseEntity<?> addPropertyAmenity(@RequestBody PropertyAmenityDTO propertyAmenityDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = propertyAmenityService.addPropertyAmenity(propertyAmenityDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/propertyAmenitys")
	public ResponseEntity<PropertyAmenityPageDTO> getPropertyAmenitys(PropertyAmenitySearchDTO propertyAmenitySearchDTO) {
 
		return propertyAmenityService.getPropertyAmenitys(propertyAmenitySearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updatePropertyAmenity", method = RequestMethod.POST)
	public ResponseEntity<?> updatePropertyAmenity(@RequestBody PropertyAmenityDTO propertyAmenityDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = propertyAmenityService.updatePropertyAmenity(propertyAmenityDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
