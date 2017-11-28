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
		 float wordCount = 0;
		 
		 for(int i = 0; i < queryContainer.length; i++) {
			 
			 String queryWord = wordFix(queryContainer[i]);
			// Check if its an ignored word
			 if(checkIgnore(queryWord)) {
				 
				 for(int j = 0; j < dataContainer.length; j++) {
					 String dataWord = wordFix(dataContainer[j]);
					 // Check if the two words match
					 if(queryWord.equals(dataWord)){
						 wordCount++;
					 }
				 }
			 }
		 }
		 // Return true is the percentage of matched words is over a specified threshold
		 if(wordCount / dataContainer.length > 0.12) {
			 return true;
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
	 
	 private String wordFix(String word) {
		 word = word.toLowerCase();
		 word = word.replace(",", "");
		 word = word.replace(".", "");
		 word = word.replace("!", "");
		 word = word.replace("?", "");
		 word = word.replace("(", "");
		 word = word.replace(")", "");
		 word = word.replace("\"", "");
		 return word;
	 }
}
