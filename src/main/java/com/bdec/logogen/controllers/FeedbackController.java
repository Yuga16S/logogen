package com.bdec.logogen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdec.logogen.models.Feedback;
import com.bdec.logogen.services.FeedbackService;


@CrossOrigin(origins = "*")
@RestController
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@CrossOrigin(origins = "*")
	@PostMapping(value="/api/saveFeedback")
	public Integer saveFeeback(@RequestBody Feedback feedback) {
		Feedback savedFeedback = feedbackService.saveFeedback(feedback);
		return savedFeedback.getId();
	}
	
}
