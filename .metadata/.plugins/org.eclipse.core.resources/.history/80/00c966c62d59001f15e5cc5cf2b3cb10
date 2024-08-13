package com.example.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.realestate.entity.Booking;
import com.example.realestate.entity.User;
import com.example.realestate.exception.CustomException;
import com.example.realestate.exception.UserNotFoundException;
import com.example.realestate.service.BookingService;
import com.example.realestate.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/getBookingById/{bookingId}")
	public ResponseEntity<?> getBookingById(@PathVariable Long bookingId) {
		try {
		  return  new ResponseEntity<>(bookingService.getBookingById(bookingId),HttpStatus.OK);
		}catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/getBookingByUserId/{userId}")
	public ResponseEntity<?> getBookingByUserId(@PathVariable Long userId) {
		try {
		  return  new ResponseEntity<>(bookingService.getBookingByUserId(userId),HttpStatus.OK);
		}catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getBookingByUserEmail")
	public ResponseEntity<?> getBookingByUserEmail(@RequestHeader("Authorization") String jwt) {
		try {
			User userRef=userService.findUserProfileByJwt(jwt);
		  return  new ResponseEntity<>(bookingService.getBookingByUserEmail(userRef.getEmail()),HttpStatus.OK);
		}catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/book/{roomId}")
	public ResponseEntity<?> makeBooking(@RequestBody Booking booking, @PathVariable Long flatId,@RequestHeader("Authorization") String jwt) {
		try {
			User userRef=userService.findUserProfileByJwt(jwt);
			return new ResponseEntity<>(bookingService.makeBooking(booking, userRef.getId(), flatId),HttpStatus.OK);
		}catch (CustomException e) {
		    return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@DeleteMapping("/cancalBooking/{bookingId}")
	public ResponseEntity<?> cancalBooking(@PathVariable Long bookingId) {
		try {
			return new ResponseEntity<>(bookingService.cancalBooking(bookingId),HttpStatus.OK);
		}catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	
	
	
	
	
	
	
}
