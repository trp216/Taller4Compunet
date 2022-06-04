package co.edu.icesi.dev.uccareapp.transport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import co.edu.icesi.dev.uccareapp.transport.delegate.CountryregionDelegateImp;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.services.CountryregionServiceImp;
import co.edu.icesi.dev.uccareapp.transport.validation.Miracle;

@Controller
public class CountryregionControllerImpl {
	
	//CountryregionServiceImp crService;
	private CountryregionDelegateImp crDelegate;

	@Autowired
	public CountryregionControllerImpl(CountryregionDelegateImp crDelegate) {
		this.crDelegate = crDelegate;
	}
	
	@GetMapping("/countryregion")
	public String indexCountryregion(Model model) {
		model.addAttribute("countries", crDelegate.findAll());
		return "countryregion/index";
	}
	
	@GetMapping("/countryregion/add")
	public String addCountryregion(Model model) {
		model.addAttribute("countryregion", new Countryregion());

		return "countryregion/add-countryregion";
	}
	
	@PostMapping("/countryregion/add")
	public String saveCountryregion(@Validated(Miracle.class) @ModelAttribute Countryregion countryregion, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			model.addAttribute("countryregion", countryregion);

			if (bindingResult.hasErrors()) {
				

				return "/countryregion/add-countryregion";

			}

			crDelegate.save(countryregion);
		}
		return "redirect:/countryregion/";
	}
	
	@GetMapping("/countryregion/edit/{id}")
	public String showEditCountryregion(@PathVariable("id") Integer id,Model model) {
		Countryregion countryregion = crDelegate.findById(id);
		
		if (countryregion == null)
			throw new IllegalArgumentException("Invalid country Id:" + id);
		
		model.addAttribute("countryregion", countryregion);
		return "countryregion/edit-countryregion";
	}
	
	@PostMapping("/countryregion/edit/{id}")
	public String editCountryregion(@Validated(Miracle.class) @ModelAttribute Countryregion countryregion, BindingResult bindingResult,
			Model model,@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action) {
		
		if (action.equals("Cancel")) {
			return "redirect:/countryregion/";
		}
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("countries", crDelegate.findAll());
			return "countryregion/index";
		}
		if (!action.equalsIgnoreCase("Cancel")) {
			countryregion.setCountryregionid(id);
			crDelegate.edit(countryregion);
			model.addAttribute("countries", crDelegate.findAll());
		}
		return "redirect:/countryregion/";
	}

}
