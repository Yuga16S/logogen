package com.bdec.logogen.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="feedback")
public class Feedback {
	
	private Integer id;
	private String feedback;
	private String emailId;
	
	public Feedback() {}
	
    public Feedback(String feedbackText, String emailID) {
    	this.feedback = feedbackText;
        this.emailId = emailID;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
