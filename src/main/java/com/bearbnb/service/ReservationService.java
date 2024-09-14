package com.bearbnb.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.bearbnb.domain.Reservation;
import com.bearbnb.dto.ReservationDTO;
import com.bearbnb.dto.ReservationSearchDTO;
import com.bearbnb.dto.ReservationPageDTO;
import com.bearbnb.dto.ReservationConvertCriteriaDTO;
import com.bearbnb.service.GenericService;
import com.bearbnb.dto.common.RequestDTO;
import com.bearbnb.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ReservationService extends GenericService<Reservation, Integer> {

	List<Reservation> findAll();

	ResultDTO addReservation(ReservationDTO reservationDTO, RequestDTO requestDTO);

	ResultDTO updateReservation(ReservationDTO reservationDTO, RequestDTO requestDTO);

    Page<Reservation> getAllReservations(Pageable pageable);

    Page<Reservation> getAllReservations(Specification<Reservation> spec, Pageable pageable);

	ResponseEntity<ReservationPageDTO> getReservations(ReservationSearchDTO reservationSearchDTO);
	
	List<ReservationDTO> convertReservationsToReservationDTOs(List<Reservation> reservations, ReservationConvertCriteriaDTO convertCriteria);

	ReservationDTO getReservationDTOById(Integer reservationId);







}





