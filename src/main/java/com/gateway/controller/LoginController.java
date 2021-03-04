package com.gateway.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@GetMapping("/login")
	public String loginPage(@RequestParam(value="error", required=false) String error,
			@RequestParam(value="logout", required=false) String logout, Model model) 
	{
		System.out.println("In Login Page..");
		String message = error!= null ? "UserName Or Password is InCorrect..!" : "You have Successfully Logged out !!";
		model.addAttribute("message",message);
		return "login";
	}

	@GetMapping("/logout")
	public String logoutPage(HttpServletRequest hRequest, HttpServletResponse hResponse) 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();	
		if(auth != null ) {
			new SecurityContextLogoutHandler().logout(hRequest, hResponse, auth);
		}
		return "redirect:/login?logout=true";
	}
}
