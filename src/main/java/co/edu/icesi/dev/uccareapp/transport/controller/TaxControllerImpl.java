package co.edu.icesi.dev.uccareapp.transport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import co.edu.icesi.dev.uccareapp.transport.delegate.SalextaxrateDelegateImp;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;
import co.edu.icesi.dev.uccareapp.transport.validation.Miracle;

@Controller
public class TaxControllerImpl {

	private SalextaxrateDelegateImp strDelegate;

	@Autowired
	public TaxControllerImpl(SalextaxrateDelegateImp strDelegate) {
		this.strDelegate = strDelegate;
	}



	@GetMapping("/tax/")
	public String indexAddress(Model model) {
		model.addAttribute("taxes", strDelegate.findAll());
		return "tax/index";
	}

	@GetMapping("/tax/add")
	public String addTax(Model model) {
		model.addAttribute("tax", new Salestaxrate());
		model.addAttribute("stateprovinces", strDelegate.getStateprovinces());

		return "tax/add-tax";
	}

	@PostMapping("/tax/add/")
	public String saveTax(@Validated(Miracle.class) @ModelAttribute Salestaxrate str, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			model.addAttribute("tax", str);

			if (bindingResult.hasErrors()) {
				model.addAttribute("stateprovinces", strDelegate.findAll());


				return "/tax/add-tax";

			}

			strDelegate.save(str);
		}
		return "redirect:/tax/";
	}


	@GetMapping("/tax/edit/{id}")
	public String showEditTax(@PathVariable("id") Integer id,Model model) {
		Salestaxrate salestaxrate = strDelegate.findById(id);
		if (salestaxrate == null)
			throw new IllegalArgumentException("Invalid sales Id:" + id);

		model.addAttribute("salestaxrate", salestaxrate);
		model.addAttribute("provinces", strDelegate.getStateprovinces());
		return "tax/edit-tax";
	}

	@PostMapping("/tax/edit/{id}")
	public String editTax(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) 
	String action, @Validated(Miracle.class) @ModelAttribute Salestaxrate salestaxrate, BindingResult bindingResult, Model model) {

		if (action.equals("Cancel")) {
			return "redirect:/tax/";
		}

		if(bindingResult.hasErrors()) {
			model.addAttribute("taxes", strDelegate.findAll());

			return "tax/index";
		}
		if (!action.equals("Cancel")) {
			salestaxrate.setSalestaxrateid(id);
			strDelegate.edit(salestaxrate);
			model.addAttribute("taxes", strDelegate.findAll());

		}
		return "redirect:/tax/";
	}


}
