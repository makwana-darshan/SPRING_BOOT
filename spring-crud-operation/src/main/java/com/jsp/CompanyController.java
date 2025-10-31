package com.jsp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyRepository companyRepository;

	@PostMapping
	public String SaveCompany(@RequestBody Company company) {
		companyRepository.save(company);
		return "save";
	}

	@PostMapping("/all")
	public String SaveAllCompany(@RequestBody List<Company> company) {
		companyRepository.saveAll(company);
		return "saveall";
	}

	@GetMapping
	public List<Company> getAllCompany() {
		List<Company> companies = companyRepository.findAll();
		return companies;
	}

	@GetMapping("/{id}")
	public Optional<Company> getCompanyById(@PathVariable Integer id) {
		Optional<Company> opt = companyRepository.findById(id);

		if (opt.isPresent()) {
			return opt;
		} else {
			return null;
		}
	}

}
