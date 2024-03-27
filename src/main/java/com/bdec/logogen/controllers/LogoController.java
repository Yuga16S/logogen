package com.bdec.logogen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bdec.logogen.models.Logo;
import com.bdec.logogen.services.LogoService;

@CrossOrigin(origins = "*")
@RestController
public class LogoController {
	
	@Autowired
	private LogoService logoService;
	
	@CrossOrigin(origins = "*")
	@GetMapping(value="/api/logoUrl/{domain}")
	public String getLogoUrl(@PathVariable String domain) {
	    Logo logo = logoService.getByDomain(domain);
	    return logo == null ? "none" : logo.getUrl();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(value="/api/companyName/{domain}")
	public String getLogoCompanyName(@PathVariable String domain) {
		Logo logo = logoService.getByDomain(domain);
		return logo == null ? "none" : logo.getCompanyName();
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(value="/api/save")
	public Integer saveLogo(@RequestBody Logo logo) {
		logo.setApproved(false);
		Logo savedLogo = logoService.saveLogo(logo);
		return savedLogo.getId();
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(value="/api/displayLogoDetails")
	public List<Logo> displayLogoDetails(@RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "10") int limit) {
	    List<Logo> logos = logoService.getAllLogos(page, limit);
	    return logos;
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(value="/api/approveLogo/{id}")
	public void approveLogo(@PathVariable Integer id) {
	    logoService.approveLogo(id);
	}
}
