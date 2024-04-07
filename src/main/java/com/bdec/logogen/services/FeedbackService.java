package com.bdec.logogen.services;

import com.bdec.logogen.models.Feedback;
import com.bdec.logogen.repositories.IfeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;


@Service
@Transactional
public class FeedbackService {
	
	@Autowired
	private IfeedbackRepository feedbackRepository;

	public Feedback saveFeedback(Feedback feedback) {
		return feedbackRepository.save(feedback);
	}

}
