package com.Cap; 


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner; 

public class Driver {
   public static void main(String[] args) {
	   
	  
	   
	   Scanner scan = new Scanner(System.in);
	   
	   System.out.print("Please enter text for analysis: "); 
	   String userInput = scan.nextLine(); 
	  
      ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
      
      //classes for 3 dimensional sections
      ScienceAndEngineeringPrac sci = (ScienceAndEngineeringPrac) context.getBean("science");
      DisciplinaryCoreIdea disc = (DisciplinaryCoreIdea) context.getBean("idea");	
      CrossCuttingConcepts conc = (CrossCuttingConcepts) context.getBean("concepts"); 
      
   //
      
      ArrayList <String> sciAndEngResults =  sci.checkMessage(userInput);
      
      System.out.print("The results for the three-dimensional category of Science and Engineering Practices\n");
      if( sciAndEngResults.size() > 0)
      {
     // Printed results for science and engineering standard
      System.out.print("The matching section(s) for the Science and Engineering Practices Dimension: \n" );
      
      String sciAndEngList = Arrays.toString(sciAndEngResults.toArray()).replace("[", "").replace("]", "").replace(",", "");
      System.out.print(sciAndEngList);
      
      }
      else if( sciAndEngResults.size() == 0 )
      {
    	  System.out.print("There were no matching results.\n");
      }
      
      sci.getMessage();
      //
      
ArrayList <String> discpAndCoreResults =  disc.checkMessage(userInput);
      
      
      if( discpAndCoreResults.size() > 0)
      {
     // Printed results for science and engineering standard
      System.out.print("The matching section(s) for the Disciplinary Core Idea Dimension: \n" );
      
      String discpAndCoreList = Arrays.toString(discpAndCoreResults.toArray()).replace("[", "").replace("]", "").replace(",", "");
      System.out.print(discpAndCoreList);
      
      }
      else if( discpAndCoreResults.size() == 0 )
      {
    	  System.out.print("There were no matching results.\n");
      }
      
      
      disc.getMessage();
      
      //
      
ArrayList <String> crossCutResults =  conc.checkMessage(userInput);
      
      
      if( crossCutResults.size() > 0)
      {
     // Printed results for science and engineering standard
      System.out.print("The matching section(s) for the Cross Cutting Concept Dimension: \n" );
      
      String crossList = Arrays.toString(crossCutResults.toArray()).replace("[", "").replace("]", "").replace(",", "");
      System.out.print(crossList);
      
      }
      else if( crossCutResults.size() == 0 )
      {
    	  System.out.print("There were no matching results.\n");
      }
      
      
      
      
      conc.getMessage(); 
      
      
   }
}
