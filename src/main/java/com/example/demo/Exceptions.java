package com.example.demo;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class Exceptions
{

	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)

	@ExceptionHandler(value=Exception.class) 
	public String handler(Exception e, Model model)
	{
		System.out.println("Exception Handled: " + e.getMessage());
		e.printStackTrace();
		model.addAttribute("errorMessage", e.getMessage());
		return "exception"; 
	}


}