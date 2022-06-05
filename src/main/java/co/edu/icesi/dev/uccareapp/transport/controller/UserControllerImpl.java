package co.edu.icesi.dev.uccareapp.transport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import co.edu.icesi.dev.uccareapp.transport.delegate.UserDelegate;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserApp;

@Controller
public class UserControllerImpl implements UserController{
	
	//UserServiceImpl userService;
	private UserDelegate userDelegate;
	
	@Autowired
	public UserControllerImpl(UserDelegate userDelegate) {
		this.userDelegate = userDelegate;
	}

	
	@GetMapping("/users/del/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		UserApp user = userDelegate.findById(id);
		userDelegate.delete(user);
		model.addAttribute("users", userDelegate.findAll());
		return "users/index";
	}

	@GetMapping("/users/")
	public String indexUser(Model model) {
		model.addAttribute("users", userDelegate.findAll());
		return "users/index";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("users", userDelegate.findAll());
		//System.out.println(userDelegate.findAll());
		return "/login";
	}

//	@GetMapping("/users/add")
//	public String addUser(Model model) {
//		model.addAttribute("user", new UserApp());
//		model.addAttribute("types", userService.getTypes());
//
//		return "users/add-user";
//	}
//
//	@PostMapping("/users/add/")
//	public String saveUser(@ModelAttribute UserApp user, BindingResult bindingResult,
//			Model model, @RequestParam(value = "action", required = true) String action) {
//		if (!action.equals("Cancel")) {
//			model.addAttribute("user", user);
//			model.addAttribute("types", userService.getTypes());
//
//			if (bindingResult.hasErrors()) {
//
//				return "/users/add-user2";
//
//			}
//
//			userService.save(user);
//		}
//		return "redirect:/users/";
//	}

	

}
