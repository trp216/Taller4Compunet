package co.edu.icesi.dev.uccareapp.transport.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.dev.uccareapp.transport.delegate.SpecialQueriesDelegateImp;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.validation.Miracle;

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
	
	//carga los territorios de venta
	@GetMapping("/specialqueries/stateprovinces/")
	public String indexSalesterritory(Model model) {
		//System.out.println(sqDelegate.getSalesterritory().toString());
		model.addAttribute("salesterritory", sqDelegate.getSalesterritory());
		return "specialqueries/stateprovinces";
	}
	
	//carga los resultados de la query 2
	@PostMapping("/specialqueries/stateprovincesresults")
	public String indexSpecialqueries2(@Validated(Miracle.class) @ModelAttribute Salesterritory st, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action) {
		
		List<Stateprovince> results = sqDelegate.findStateProvinceAndAddresses(st);
		
	//	ArrayList<Stateprovince> sps = new ArrayList<Stateprovince>();
		//ArrayList<Integer> count = new ArrayList<Integer>();
		
//		for (Object[] i : results) {
//			Stateprovince sp = (Stateprovince)i[0];
//			sps.add(sp);
//			Integer c = (Integer)i[1];
//			count.add(c);
	//	}
		
	//	System.out.println(sps.toString());
		
		model.addAttribute("stateprovinces",results);
		//model.addAttribute("count",count);
		return "specialqueries/stateprovincesresult";
	}
	
	
}
