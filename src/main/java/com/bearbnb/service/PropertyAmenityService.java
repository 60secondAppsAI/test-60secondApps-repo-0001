package com.bearbnb.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.bearbnb.domain.PropertyAmenity;
import com.bearbnb.dto.PropertyAmenityDTO;
import com.bearbnb.dto.PropertyAmenitySearchDTO;
import com.bearbnb.dto.PropertyAmenityPageDTO;
import com.bearbnb.dto.PropertyAmenityConvertCriteriaDTO;
import com.bearbnb.service.GenericService;
import com.bearbnb.dto.common.RequestDTO;
import com.bearbnb.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PropertyAmenityService extends GenericService<PropertyAmenity, Integer> {

	List<PropertyAmenity> findAll();

	ResultDTO addPropertyAmenity(PropertyAmenityDTO propertyAmenityDTO, RequestDTO requestDTO);

	ResultDTO updatePropertyAmenity(PropertyAmenityDTO propertyAmenityDTO, RequestDTO requestDTO);

    Page<PropertyAmenity> getAllPropertyAmenitys(Pageable pageable);

    Page<PropertyAmenity> getAllPropertyAmenitys(Specification<PropertyAmenity> spec, Pageable pageable);

	ResponseEntity<PropertyAmenityPageDTO> getPropertyAmenitys(PropertyAmenitySearchDTO propertyAmenitySearchDTO);
	
	List<PropertyAmenityDTO> convertPropertyAmenitysToPropertyAmenityDTOs(List<PropertyAmenity> propertyAmenitys, PropertyAmenityConvertCriteriaDTO convertCriteria);

	PropertyAmenityDTO getPropertyAmenityDTOById(Integer propertyAmenityId);







}





