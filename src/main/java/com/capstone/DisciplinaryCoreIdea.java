package com.capstone;

import java.util.ArrayList;

public class DisciplinaryCoreIdea {
	
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
	   
	   
	  // System.out.printf("\n\nThe size of the array is %d ", x);
	   
	   
	   String[] discpList = {"From Molecules to Organisms: Structures and Processes A, Ecosystems: Interactions, Energy, and Dynamics, Heredity: Inheritance of Traits, Biological Evolution: Unity and Diversity, Matter and Its Interactions, Motion and Stability: Forces and Interactions, Energy, Waves and Their Applications in Technologies for Information Transfer, Earth’s Place in the Universe, Earth’s Systems, Earth and Human Activity, Engineering Design, Links Among Engineering, Technology, Science, and Society"};
	   int discpListCount = 0; 
	   int queryCount = 0; 
	   int resultCount = 0; 
	   //x is count of the query container. 
	   ArrayList <String> results = new ArrayList(); 
	   boolean returned = false;
	   
	   
	   for( queryCount = 0;  queryCount < queryContainer.length; queryCount++)
	   {
		   
		   for( discpListCount = 0 ; discpListCount < discpList.length ; discpListCount++)
		   {
			   
		 
			    returned =  discpList[discpListCount].toLowerCase().contains(queryContainer[queryCount].toLowerCase());

			   
			   //might get rid of this
			  // returned =  queryContainer[queryCount].toLowerCase().contains(discpList[discpListCount].toLowerCase());

			   if( returned == true )
			   {
				   
				   results.add("* "+discpList[discpListCount]+".  \n ");
				   
				  // results[resultCount] =  discpList[discpListCount];
				   //resultCount++; 
			   }
			   
		   
			   discpListCount++;
	  
		   }
		   
		   queryCount++; 
		   
	   
	   }
	   
	   
	   
	   return results; 
   }

}


