package com.example.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.realestate.entity.Bill;
import com.example.realestate.entity.User;
import com.example.realestate.exception.CustomException;
import com.example.realestate.service.BillService;
import com.example.realestate.service.UserService;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/bill")
public class BillController {

	@Autowired
	private BillService billService;
	
	@Autowired 
	private UserService userService;
	

	@PostMapping("/generateBill")
	public ResponseEntity<?> generateBill(@RequestHeader("Authorization") String jwt) {
		try {
			User userref=userService.findUserProfileByJwt(jwt);
			return new ResponseEntity<>(billService.generateBillForUser(userref), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}     
	}

	@GetMapping("/getbillbyid/{billId}")
	public ResponseEntity<?> getBill(@PathVariable Long billId) {
		try {
			return new ResponseEntity<>(billService.getBillById(billId), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getbill")
	public ResponseEntity<?>getBillByToken(@RequestHeader("Authorization") String token){
		try {
			User user=userService.findUserProfileByJwt(token);
			return new ResponseEntity<>(billService.getBillByUserId(user.getId()),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getbillbyUserid/{id}")
	public ResponseEntity<?> getBillByUserId(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(billService.getBillByUserId(id), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deletebill/{billId}")
	public ResponseEntity<?> deleteBill(@PathVariable Long billId) {
		try {
			return new ResponseEntity<>(billService.deleteBillbyBillId(billId), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/downloadBill/{billId}")
	public ResponseEntity<?> downloadPdf(@PathVariable Long billId) {
	    try {
	        Bill bill = billService.getBillById(billId);
	        byte[] pdfBytes = billService.generatePdf(bill);

	        // Check if the PDF byte array is not empty
	        if (pdfBytes != null && pdfBytes.length > 0) {
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_PDF);
	            headers.setContentDispositionFormData("attachment", billId + "_bill.pdf");
	            headers.setContentLength(pdfBytes.length);

	            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	        } else {
	            throw new CustomException("No pending bills or PDF generation failed");
	        }

	    } catch (CustomException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


}
