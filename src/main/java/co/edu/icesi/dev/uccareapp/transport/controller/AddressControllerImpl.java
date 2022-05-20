package co.edu.icesi.dev.uccareapp.transport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserApp;
import co.edu.icesi.dev.uccareapp.transport.services.AddressServiceImp;
import co.edu.icesi.dev.uccareapp.transport.services.StateprovinceServiceImp;
import co.edu.icesi.dev.uccareapp.transport.validation.Miracle;

@Controller
public class AddressControllerImpl {
	
	AddressServiceImp addressService;
	StateprovinceServiceImp spService;

	@Autowired
	public AddressControllerImpl(AddressServiceImp addressService, StateprovinceServiceImp spService) {
	
		this.addressService = addressService;
		this.spService = spService;
	}



	@GetMapping("/address/")
	public String indexAddress(Model model) {
		model.addAttribute("addresses", addressService.findAll());
		return "address/index";
	}
	
	@GetMapping("/address/add")
	public String addAddress(Model model) {
		model.addAttribute("address", new Address());
		model.addAttribute("stateprovinces", spService.findAll());

		return "address/add-address";
	}

	@PostMapping("/address/add/")
	public String saveAddress(@Validated(Miracle.class) @ModelAttribute Address address, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			model.addAttribute("address", address);

			if (bindingResult.hasErrors()) {
				model.addAttribute("stateprovinces", spService.findAll());


				return "/address/add-address";

			}

			addressService.save(address);
		}
		return "redirect:/address/";
	}
	
	@GetMapping("/address/edit/{id}")
	public String showEditAddress(@PathVariable("id") Integer id,Model model) {
		Address address = addressService.findById(id);
		if (address == null)
			throw new IllegalArgumentException("Invalid country Id:" + id);
		
		model.addAttribute("address", address);
		model.addAttribute("provinces", spService.findAll());
		return "address/edit-address";
	}
	
	@PostMapping("/address/edit/{id}")
	public String editAddress(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) 
	String action,@Validated(Miracle.class) @ModelAttribute Address address, BindingResult bindingResult, Model model) {
		
		if(action.equals("Cancel")) {
			return "redirect:/address/";
		}
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("addresses", addressService.findAll());
			
			return "address/index";
		}
		if (!action.equals("Cancel")) {
			address.setAddressid(id);
			addressService.edit(address,address.getStateprovince().getStateprovinceid());
			model.addAttribute("provinces", spService.findAll());
		}
		return "redirect:/address/";
	}

}
