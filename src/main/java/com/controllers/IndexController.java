package com.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.model.ListModel;

@Controller
public class IndexController {
	@ModelAttribute("text")
	   public ListModel setUpUserForm() {
	      return new ListModel();
	   }
	
	@RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
	public String initialize() {
		return "index";
	}

	
	
}
