package com.capstone;

import java.util.ArrayList;

public class CrossCuttingConcepts {
	
	 private String message;
	 private String query; 
	 private String categoryResults; 
	 private String reqList[]; 
	 
   public void setMessage(String message){
      this.message  = message;
   }
   public void getMessage(){
      System.out.println("\n\nEnd of " + message);
   }
   
   
   //Sets the user's input text
   public void setQuery(String query){
	      this.query  = query;
	   }
  
   
   //returns The results in a string in string concat
   public  ArrayList <String> checkMessage(String query2)
   {
	   
	   
	   //array to hold query of user 
	   String queryContainer[] = query2.split("\\s");
	   
	   int x = 0; 
	   
	   for (x = 0 ; x < queryContainer.length; x++ )
	   {
		   System.out.printf("%s\n",queryContainer[x]);
		   
	   }
	   
	   
	   //System.out.printf("\n\nThe size of the array is %d ", x);
	   
	   //String[] crossList = {"Ask Question  Define Problem", "Develop Use Model", "Plan  Carry Investigation", "Analyze  Interpret Data", "Math  Computation Think", "Construction Explanation Design Solution", "Engage Argument Evidence", "Obtain Evaluate Communicate Information"};
	   String[] crossList = {"Patterns", "Cause and Effect", "Scale, Proportion, and Quantity", "Systems and System Models", "Energy and Matter", "Structure and Function", "Stability and Change" };
	   int crossListCount = 0; 
	   int queryCount = 0; 
	   int resultCount = 0; 
	   //x is count of the query container. 
	   ArrayList <String> results = new ArrayList(); 
	   boolean returned = false;
	   
	   
	   for( queryCount = 0;  queryCount < queryContainer.length; queryCount++)
	   {
		   
		   for( crossListCount = 0 ; crossListCount < crossList.length ; crossListCount++)
		   {
			   
		 
			    returned =  crossList[crossListCount].toLowerCase().contains(queryContainer[queryCount].toLowerCase());

			   
			   //might get rid of this
			  // returned =  queryContainer[queryCount].toLowerCase().contains(crossList[crossListCount].toLowerCase());

			   if( returned == true )
			   {
				   
				   results.add("* "+crossList[crossListCount]+".  \n");
				   
				  // results[resultCount] =  crossList[crossListCount];
				   //resultCount++; 
			   }
			   
		   
			   crossListCount++;
	  
		   }
		   
		   queryCount++; 
		   
	   
	   }
	   
	   
	   
	   return results; 
   }

}


