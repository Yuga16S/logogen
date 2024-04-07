package com.bdec.logogen.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bdec.logogen.models.Logo;
import com.bdec.logogen.repositories.ILogoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@SuppressWarnings("unchecked")
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
	
	public Map<String, String> fetchLogoAndCompanyName(String domain) {
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
}
