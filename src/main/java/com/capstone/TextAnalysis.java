package com.capstone;

public class TextAnalysis {
	private String[] ignore = {"the", "it", "those", "these", "in", "a", "of", "on", "and", "or", "thus", "to", "this", "for", "with", "are", "can", "be", "use"};
	
	/**
	 * Matches the individual words of query to the words of data.
	 * @param query
	 * @param data
	 * @return
	 */
	 public boolean textMatch(String query, String data) {
		 String queryContainer[] = query.split("\\s");
		 String dataContainer[] = data.split("\\s");
		 
		 for(int i = 0; i < queryContainer.length; i++) {
			 // Check if its an ignored word
			 if(checkIgnore(queryContainer[i].toLowerCase())) {
				 for(int j = 0; j < dataContainer.length; j++) {
					 // Check if the two words match
					 if(queryContainer[i].toLowerCase().equals(dataContainer[j].toLowerCase())){
						 return true;
					 }
				 }
			 }
		 }
		 return false;
	 }
	 
	 /**
	  * Returns true if the word is not to be ignored.
	  * @param word
	  * @return
	  */
	 private boolean checkIgnore(String word) {
		 boolean check = true;
		 
		 for(int k = 0; k < ignore.length; k++) {
			 if(word.equals(ignore[k])) {
				check = false; 
			 }
		 }
		 return check;
	 }
}
