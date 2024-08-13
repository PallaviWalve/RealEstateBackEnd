package com.example.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.realestate.entity.Feedback;
import com.example.realestate.entity.Property;
import com.example.realestate.entity.User;
import com.example.realestate.exception.CustomException;
import com.example.realestate.repository.FeedbackRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    
	@Autowired
	private UserService userServiceRef;

	@Autowired
	private PropertyService propertyServiceRef;
    

	public Feedback writeFeedBack(Feedback feedBackRef, Long userid, Long propertyid) {
		User user = userServiceRef.findById(userid);
		Property propertyRef = propertyServiceRef.getOneFlat(propertyid);
		feedBackRef.setUser(user);
//		feedBackRef.setPropertyId(propertyid);;
		Feedback feedBack = feedbackRepository.save(feedBackRef);
		if (feedBack != null) {
			return feedBack;
		} else {
			throw new CustomException("unable to save feedBack");
		}
	}

    public Feedback findById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feedback not found with id: " + id));
    }

    public List<Feedback> getAllFeedback() {
		List<Feedback> feedbackList = feedbackRepository.findAll();
		if (feedbackList.isEmpty()) {
			throw new CustomException("No feedback available");
		} else {
			return feedbackList;
		}
	}
    
    public String deleteFeedBack(Long id) {
		Feedback feedBack = feedbackRepository.findById(id).orElseThrow(() -> new CustomException("Feedback not found"));
		if (feedBack != null) {
			feedbackRepository.deleteById(id);
			return "feedback deleted successfull";
		} else {
			throw new CustomException("unable to delete feedback");
		}
	}

    public Feedback updateFeedback(Long id, Feedback updatedFeedback) {
        Feedback feedback = findById(id);
        feedback.setComments(updatedFeedback.getComments());
        feedback.setRating(updatedFeedback.getRating());
        feedback.setUser(updatedFeedback.getUser());
        
        return feedbackRepository.save(feedback);
    }
}
