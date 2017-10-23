package com.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.capstone.CrossCuttingConcepts;
import com.capstone.DisciplinaryCoreIdea;
import com.capstone.ScienceAndEngineeringPrac;
import com.model.TextAreaBean;

@Controller
public class IndexController {
	@ModelAttribute("text")
	   public TextAreaBean setUpUserForm() {
	      return new TextAreaBean();
	   }
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String initialize() {
		return "index";
	}

	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String processForm(@ModelAttribute("text") TextAreaBean t, ModelMap model)
	{
		String sciResult = "";
		String discResult = "";
		String concResult = "";
		
		ScienceAndEngineeringPrac sci = new ScienceAndEngineeringPrac();
		DisciplinaryCoreIdea disc = new DisciplinaryCoreIdea();
		CrossCuttingConcepts conc = new CrossCuttingConcepts();
		
		 ArrayList <String> sciAndEngResults =  sci.checkMessage(t.getLessonPlan());
	      
	      
	      if( sciAndEngResults.size() > 0) 
	      {
	      
		      String sciAndEngList = Arrays.toString(sciAndEngResults.toArray()).replace("[", "").replace("]", "").replace(",", "");
		      sciResult += sciAndEngList;
	      
	      }
	      else if( sciAndEngResults.size() == 0 )
	      {
	    	  sciResult += "There were no matching results.\n";
	      }
	      
	      ArrayList <String> discpAndCoreResults =  disc.checkMessage(t.getLessonPlan());
	      
	      
	      if( discpAndCoreResults.size() > 0)
	      {
	      
		      String discpAndCoreList = Arrays.toString(discpAndCoreResults.toArray()).replace("[", "").replace("]", "").replace(",", "");
		      discResult += discpAndCoreList;
	      
	      }
	      else if( discpAndCoreResults.size() == 0 )
	      {
	    	  discResult += "There were no matching results.\n";
	      }
	      
	ArrayList <String> crossCutResults =  conc.checkMessage(t.getLessonPlan());
	      
	      
	      if( crossCutResults.size() > 0)
	      {
	     
		      String crossList = Arrays.toString(crossCutResults.toArray()).replace("[", "").replace("]", "").replace(",", "");
		      concResult += crossList;
	      
	      }
	      else if( crossCutResults.size() == 0 )
	      {
	    	  concResult += "There were no matching results.\n";
	      }
		
		model.addAttribute("sciResult", sciResult);
		model.addAttribute("coreResult", discResult);
		model.addAttribute("crossResult", concResult);
		
		return "result";
	}
}
