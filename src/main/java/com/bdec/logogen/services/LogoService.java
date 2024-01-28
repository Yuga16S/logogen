package com.bdec.logogen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdec.logogen.models.Logo;
import com.bdec.logogen.repositories.ILogoRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class LogoService {
		
	@Autowired
	private ILogoRepository logoRepository;
	
    public Logo getByDomain(String domain) {
        return logoRepository.findByDomain(domain);
    }

	public Logo saveLogo(Logo logo) {
		return logoRepository.save(logo);
	}
}
