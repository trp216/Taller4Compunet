package co.edu.icesi.dev.uccareapp.transport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import co.edu.icesi.dev.uccareapp.transport.delegate.AddressDelegateImp;
import co.edu.icesi.dev.uccareapp.transport.delegate.CountryregionDelegateImp;
import co.edu.icesi.dev.uccareapp.transport.delegate.StateprovinceDelegateImp;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.validation.Miracle;

@Controller
public class StateprovinceControllerImpl {
	
	private StateprovinceDelegateImp spDelegate;
	
	private CountryregionDelegateImp crDelegate;
	
	private AddressDelegateImp adDelegate;

	@Autowired
	public StateprovinceControllerImpl(StateprovinceDelegateImp spDelegate,
			CountryregionDelegateImp crDelegate,
			AddressDelegateImp adDelegate) {
		this.spDelegate = spDelegate;
		this.crDelegate = crDelegate;
		this.adDelegate = adDelegate;
	}
		
	@GetMapping("/stateprovince/")
	public String indexStateprovince(Model model) {
		model.addAttribute("stateprovinces", spDelegate.findAll());
		return "stateprovince/index";
	}
	
	@GetMapping("/stateprovince/{id}")
	public String indexSingleStateprovince(@PathVariable("id") Integer id, Model model) {
		Stateprovince spFound = spDelegate.findById(id);
		Countryregion crFound = crDelegate.findByStateprovince(id);
		model.addAttribute("stateprovince", spFound);
        model.addAttribute("countryregion", crFound);
        return "stateprovince/single-stateprovince";
	}
	
	@GetMapping("/stateprovince/address/{id}")
	public String indexSingleStateprovince2(@PathVariable("id") Integer id, Model model) {
		Stateprovince spFound = spDelegate.findById(id);
		Address adFound = adDelegate.findByStateprovince(id);
		model.addAttribute("stateprovince", spFound);
        model.addAttribute("address", adFound);
        return "stateprovince/single-stateprovince2";
	}
	
	@GetMapping("/stateprovince/add")
	public String addStateprovince(Model model) {
		model.addAttribute("stateprovince", new Stateprovince());
		model.addAttribute("countryregions", spDelegate.getCountryregion());
		model.addAttribute("salesterritory", spDelegate.getSalesterritory());

		return "stateprovince/add-stateprovince";
	}
	
	@PostMapping("/stateprovince/add/")
	public String saveStateprovince(@Validated(Miracle.class) @ModelAttribute Stateprovince sp, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			model.addAttribute("stateprovince", sp);

			if (bindingResult.hasErrors()) {
				model.addAttribute("salesterritory", spDelegate.getSalesterritory());
				model.addAttribute("contryregion", spDelegate.getCountryregion());


				return "/stateprovince/add-stateprovince";

			}

			spDelegate.save(sp);
		}
		return "redirect:/stateprovince/";
	}
	
	@GetMapping("/stateprovince/edit/{id}")
	public String showEditProvince(@PathVariable("id") Integer id,Model model) {
		Stateprovince stateprovince = spDelegate.findById(id);
		if (stateprovince == null)
			throw new IllegalArgumentException("Invalid country Id:" + id);
		
		model.addAttribute("stateprovince", stateprovince);
		model.addAttribute("countries", spDelegate.getCountryregion());
		return "stateprovince/edit-stateprovince";
	}
	
	@PostMapping("/stateprovince/edit/{id}")
	public String updateProvince(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) 
	String action, @Validated(Miracle.class) @ModelAttribute Stateprovince stateprovince, BindingResult bindingResult, Model model) {
		
		if(action.equals("Cancel")) {
			return "redirect:/stateprovince/";
		}
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("stateprovince", spDelegate.findAll());
			
			return "stateprovince/index";
		}
		if (action != null && !action.equals("Cancel")) {
			stateprovince.setStateprovinceid(id);
			spDelegate.edit(stateprovince);
			
			model.addAttribute("stateprovinces", spDelegate.findAll());
		}
		return "redirect:/stateprovince/";
	}
	

}
