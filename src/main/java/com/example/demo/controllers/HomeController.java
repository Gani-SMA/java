package com.example.demo.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.loginCredentials.AdminLogin;
import com.example.demo.loginCredentials.UserLogin;
import com.example.demo.services.ProductServices;
import com.example.demo.services.UserServices;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController 
{
	@Autowired
	private ProductServices productServices;

	@Autowired
	private UserServices userServices;
	@GetMapping(value = {"/home", "/"})
	public String home()
	{
		return "Home";
	}

	@GetMapping("/products")
	public String products( Model model)
	{ 
		List<Product> allProducts = this.productServices.getAllProducts();
		model.addAttribute("products", allProducts);
		return "Products";
	}


	@GetMapping("/login")
	public String login(Model model)
	{
		model.addAttribute("adminLogin", new AdminLogin());
		model.addAttribute("userLogin", new UserLogin());
		return "Login";
	}

	@GetMapping("/register")
	public String showRegister(Model model)
	{
		model.addAttribute("userRegistration", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("userRegistration") User user, Model model)
	{
		try {
			userServices.addUser(user);
			return "redirect:/login";
		} catch (Exception e) {
			model.addAttribute("error", "Registration failed: " + e.getMessage());
			return "register";
		}
	}
}