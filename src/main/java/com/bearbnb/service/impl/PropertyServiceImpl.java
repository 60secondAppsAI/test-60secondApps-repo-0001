package com.bearbnb.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.bearbnb.dao.GenericDAO;
import com.bearbnb.service.GenericService;
import com.bearbnb.service.impl.GenericServiceImpl;
import com.bearbnb.dao.PropertyDAO;
import com.bearbnb.domain.Property;
import com.bearbnb.dto.PropertyDTO;
import com.bearbnb.dto.PropertySearchDTO;
import com.bearbnb.dto.PropertyPageDTO;
import com.bearbnb.dto.PropertyConvertCriteriaDTO;
import com.bearbnb.dto.common.RequestDTO;
import com.bearbnb.dto.common.ResultDTO;
import com.bearbnb.service.PropertyService;
import com.bearbnb.util.ControllerUtils;





@Service
public class PropertyServiceImpl extends GenericServiceImpl<Property, Integer> implements PropertyService {

    private final static Logger logger = LoggerFactory.getLogger(PropertyServiceImpl.class);

	@Autowired
	PropertyDAO propertyDao;

	


	@Override
	public GenericDAO<Property, Integer> getDAO() {
		return (GenericDAO<Property, Integer>) propertyDao;
	}
	
	public List<Property> findAll () {
		List<Property> propertys = propertyDao.findAll();
		
		return propertys;	
		
	}

	public ResultDTO addProperty(PropertyDTO propertyDTO, RequestDTO requestDTO) {

		Property property = new Property();

		property.setPropertyId(propertyDTO.getPropertyId());


		property.setName(propertyDTO.getName());


		property.setLocation(propertyDTO.getLocation());


		property.setDescription(propertyDTO.getDescription());


		property.setPricePerNight(propertyDTO.getPricePerNight());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		property = propertyDao.save(property);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Property> getAllPropertys(Pageable pageable) {
		return propertyDao.findAll(pageable);
	}

	public Page<Property> getAllPropertys(Specification<Property> spec, Pageable pageable) {
		return propertyDao.findAll(spec, pageable);
	}

	public ResponseEntity<PropertyPageDTO> getPropertys(PropertySearchDTO propertySearchDTO) {
	
			Integer propertyId = propertySearchDTO.getPropertyId(); 
 			String name = propertySearchDTO.getName(); 
 			String location = propertySearchDTO.getLocation(); 
 			String description = propertySearchDTO.getDescription(); 
  			String sortBy = propertySearchDTO.getSortBy();
			String sortOrder = propertySearchDTO.getSortOrder();
			String searchQuery = propertySearchDTO.getSearchQuery();
			Integer page = propertySearchDTO.getPage();
			Integer size = propertySearchDTO.getSize();

	        Specification<Property> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, propertyId, "propertyId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, location, "location"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("location")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Property> propertys = this.getAllPropertys(spec, pageable);
		
		//System.out.println(String.valueOf(propertys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(propertys.getTotalPages()));
		
		List<Property> propertysList = propertys.getContent();
		
		PropertyConvertCriteriaDTO convertCriteria = new PropertyConvertCriteriaDTO();
		List<PropertyDTO> propertyDTOs = this.convertPropertysToPropertyDTOs(propertysList,convertCriteria);
		
		PropertyPageDTO propertyPageDTO = new PropertyPageDTO();
		propertyPageDTO.setPropertys(propertyDTOs);
		propertyPageDTO.setTotalElements(propertys.getTotalElements());
		return ResponseEntity.ok(propertyPageDTO);
	}

	public List<PropertyDTO> convertPropertysToPropertyDTOs(List<Property> propertys, PropertyConvertCriteriaDTO convertCriteria) {
		
		List<PropertyDTO> propertyDTOs = new ArrayList<PropertyDTO>();
		
		for (Property property : propertys) {
			propertyDTOs.add(convertPropertyToPropertyDTO(property,convertCriteria));
		}
		
		return propertyDTOs;

	}
	
	public PropertyDTO convertPropertyToPropertyDTO(Property property, PropertyConvertCriteriaDTO convertCriteria) {
		
		PropertyDTO propertyDTO = new PropertyDTO();
		
		propertyDTO.setPropertyId(property.getPropertyId());

	
		propertyDTO.setName(property.getName());

	
		propertyDTO.setLocation(property.getLocation());

	
		propertyDTO.setDescription(property.getDescription());

	
		propertyDTO.setPricePerNight(property.getPricePerNight());

	

		
		return propertyDTO;
	}

	public ResultDTO updateProperty(PropertyDTO propertyDTO, RequestDTO requestDTO) {
		
		Property property = propertyDao.getById(propertyDTO.getPropertyId());

		property.setPropertyId(ControllerUtils.setValue(property.getPropertyId(), propertyDTO.getPropertyId()));

		property.setName(ControllerUtils.setValue(property.getName(), propertyDTO.getName()));

		property.setLocation(ControllerUtils.setValue(property.getLocation(), propertyDTO.getLocation()));

		property.setDescription(ControllerUtils.setValue(property.getDescription(), propertyDTO.getDescription()));

		property.setPricePerNight(ControllerUtils.setValue(property.getPricePerNight(), propertyDTO.getPricePerNight()));



        property = propertyDao.save(property);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PropertyDTO getPropertyDTOById(Integer propertyId) {
	
		Property property = propertyDao.getById(propertyId);
			
		
		PropertyConvertCriteriaDTO convertCriteria = new PropertyConvertCriteriaDTO();
		return(this.convertPropertyToPropertyDTO(property,convertCriteria));
	}







}
