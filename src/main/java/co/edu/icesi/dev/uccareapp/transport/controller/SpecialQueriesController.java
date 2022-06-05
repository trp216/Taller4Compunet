package co.edu.icesi.dev.uccareapp.transport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.icesi.dev.uccareapp.transport.delegate.SpecialQueriesDelegateImp;

@Controller
public class SpecialQueriesController {

	private SpecialQueriesDelegateImp sqDelegate;

	@Autowired
	public SpecialQueriesController(SpecialQueriesDelegateImp sqDelegate) {
		this.sqDelegate = sqDelegate;
	}
	
	@GetMapping("/specialqueries/addresses/")
	public String indexSpecialqueries(Model model) {
		model.addAttribute("addresses", 
				sqDelegate.findAddressesWithSalesorderheader());
		return "specialqueries/addresses";
	}
	
	
}
