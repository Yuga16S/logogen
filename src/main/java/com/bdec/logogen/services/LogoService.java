package com.bdec.logogen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bdec.logogen.models.Logo;
import com.bdec.logogen.repositories.ILogoRepository;

import java.util.List;

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

	public List<Logo> getAllLogos(int page, int limit) {
		Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("columnName").ascending()); 
		return (List<Logo>) logoRepository.findAll(pageable);
	}
	
	public void approveLogo(Integer id) {
		Logo logo = logoRepository.findById(id).orElse(null);
		if (logo != null) {
			logo.setApproved(true);
			logoRepository.save(logo);
		}
	}
}
