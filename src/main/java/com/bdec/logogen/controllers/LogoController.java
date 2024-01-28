package com.bdec.logogen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdec.logogen.models.Logo;
import com.bdec.logogen.services.LogoService;

@RestController
public class LogoController {
	
	@Autowired
	private LogoService logoService;
	
	@GetMapping(value="/api/logoUrl/{domain}")
	public String getLogoUrl(@PathVariable String domain) {
	    Logo logo = logoService.getByDomain(domain);
	    return logo == null ? "none" : logo.getUrl();
	}
	
	@GetMapping(value="/api/companyName/{domain}")
	public String getLogoCompanyName(@PathVariable String domain) {
		Logo logo = logoService.getByDomain(domain);
		return logo == null ? "none" : logo.getCompanyName();
	}
	
	
	@PostMapping(value="/api/save")
	public Integer saveLogo(@RequestBody Logo logo) {
		Logo savedLogo = logoService.saveLogo(logo);
		return savedLogo.getId();
	}
}
