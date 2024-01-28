package com.bdec.logogen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bdec.logogen.models.Logo;

public interface ILogoRepository extends JpaRepository<Logo, Integer> {
	
	public Logo findByDomain(String domain);
}
