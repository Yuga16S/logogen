package com.bdec.logogen.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
	    if (logo == null) {
	    	
	    	
	    	Map<String, String> logoAndCompanyName = fetchLogoAndCompanyName(domain); // Logo not found in the database, fetch it from the domain's website
	    	if (logoAndCompanyName != null && logoAndCompanyName.containsKey("logoUrl")) {
	    		String logoUrl = logoAndCompanyName.get("logoUrl");
                String companyName = logoAndCompanyName.get("companyName");
                
                
                logo = new Logo(domain, logoUrl, companyName); // Store the logo details in the database
                logo.setApproved(false);
                logoService.saveLogo(logo);
                
                return logoUrl;
	    	} else {
	    		return "none"; // Logo not found on the domain's website
	    	}
	    } else {
	    	return logo.getUrl();
	    }
	}
	
    private Map<String, String> fetchLogoAndCompanyName(String domain) {
        try {
            
            Document doc = Jsoup.connect("http://" + domain).get(); // Fetch the HTML content of the domain's website
            Elements logoElements = doc.select("link[rel=icon], link[rel='shortcut icon'], link[rel=apple-touch-icon]"); // Extract the logo URL from the HTML
            String logoUrl = null;
            if (!logoElements.isEmpty()) {
                Element logoElement = logoElements.first();
                logoUrl = logoElement.attr("href");
                
                // Ensure logo URL is absolute
                if (!logoUrl.isEmpty() && !logoUrl.startsWith("http")) {
                    logoUrl = "http://" + domain + "/" + logoUrl;
                }
            }

            String companyName = null; 
            Elements titleElements = doc.select("title");
            if (!titleElements.isEmpty()) {
                companyName = titleElements.first().text();
            }

            Map<String, String> logoAndCompanyName = new HashMap<>(); // Create a map to store logo URL and company name
            logoAndCompanyName.put("logoUrl", logoUrl);
            logoAndCompanyName.put("companyName", companyName);

            return logoAndCompanyName;
        } catch (Exception e) {
            e.printStackTrace();  // Handle any exceptions (e.g., invalid domain, connection timeout)
        }
        return null;
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
