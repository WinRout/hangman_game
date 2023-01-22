package utilities;

import java.util.Set;

import application.OpenBookLib;
import exceptions.UnbalancedException;
import exceptions.UndersizeException;

import java.util.HashSet;
import java.util.Scanner;

public class CreateDictionary {
	
	public CreateDictionary(String openLibraryID, String dictionaryID) 
			throws UndersizeException, UnbalancedException, Exception {
		Set<String> set = new HashSet<String>();
		OpenBookLib book = new OpenBookLib(openLibraryID);
		String text = book.getDescritpion(); //this can throw Exception if BookID is invalid
		String word = null;
		
		int totalWords = 0;
		int nineLetters = 0;	
		
		try (Scanner scanner = new Scanner(text)) {
			word = scanner.useDelimiter("[^A-Za-z]+").next();
			while(scanner.hasNext()) {
		    	int len = word.length();
				if (len > 5) {
		    		set.add(word);
		    		totalWords++;
		    		if (word.length() > 8) {
		    			nineLetters++;
		    		}
		    	}
		    	word = scanner.next();
			}
		}
		catch (Exception e) {
			System.out.println("Error scannign the description text");
		}
		
		//If less than 20 words throw exception
		if (totalWords < 20) { 
			throw new UndersizeException();
		}
		//If not enough long words throw exception
		if ((nineLetters/(double)(totalWords)) < 0.2) { 
			throw new UnbalancedException();
		}
		
		//if Dictionary is valid then write to file
		new CreateFile(dictionaryID);
		
		String result = new String();
		for (String w : set) {
			result = result + w.toUpperCase() + "\n";
		}
		new WriteToFile(dictionaryID, result);
    }
}

