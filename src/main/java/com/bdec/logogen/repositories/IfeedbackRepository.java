package com.bdec.logogen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bdec.logogen.models.Feedback;

public interface IfeedbackRepository extends JpaRepository<Feedback, Integer> {
	
}
