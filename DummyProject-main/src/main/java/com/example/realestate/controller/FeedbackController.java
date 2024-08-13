package com.example.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.realestate.entity.Feedback;
import com.example.realestate.exception.CustomException;
import com.example.realestate.service.FeedbackService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/write/{userid}/{pgid}")
	public ResponseEntity<?> writeFeedBack(@RequestBody Feedback feedBackRef,@PathVariable Long userid,@PathVariable Long propertyid){
		try {
			return new ResponseEntity<>(feedbackService.writeFeedBack(feedBackRef, userid, propertyid),HttpStatus.CREATED);
		}catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        Feedback feedback = feedbackService.findById(id);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping("/get")
	public ResponseEntity<?> getAllFeedback(){
		try {
			return new ResponseEntity<>(feedbackService.getAllFeedback(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}

    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long id, @RequestBody Feedback updatedFeedback) {
        Feedback feedback = feedbackService.updateFeedback(id, updatedFeedback);
        return ResponseEntity.ok(feedback);
    }

    @DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteFeedBack(@PathVariable Long id){
		try {
			return new ResponseEntity<>(feedbackService.deleteFeedBack(id),HttpStatus.OK);
		}catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
}

