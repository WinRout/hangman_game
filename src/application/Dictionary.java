package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * A class that holds information of Dictionary
 * @author Nikitas Tsinnas, el18187
 *
 */

public class Dictionary {
	
	/**
	 * Dictionary ID given from player
	 */
	public String ID;
	/**
	 * Total words of Dictionary
	 */
	public int totalWords;
	/**
	 * Percentage of words of length 6
	 */
	public double shortPrc;
	/**
	 * Percentage of words between length 7-9
	 */
	public double midPrc;
	/**
	 * Percentage of words of length over 9
	 */
	public double longPrc;
	
	private List<String> words;
	
	/**
	 * It sets all the public fields of class to the right value.
	 * It calculates all the percentages and stores them to the
	 * public class relative fields.
	 * 
	 * @param ID String of the DictionaryID that is desired to be given
	 * @throws FileNotFoundException
	 */
	public Dictionary(String ID) throws FileNotFoundException {
		this.ID = ID;
		this.words = new ArrayList<>();
		Scanner scanner = new Scanner(new File("medialab/hangman_"+ID+".txt"));
		while(scanner.hasNext()) {
			words.add(scanner.nextLine());
		}
		
		this.totalWords = words.size();
		
		int shortInt = 0, midInt = 0, longInt = 0;
		for (String word : words) {
			int len = word.length();
			if (len == 6) {
				shortInt++;
			}
			else if (len < 10) {
				midInt++;
			}
			else longInt++;
		}
		double div = (double)(words.size());
		
		this.shortPrc = shortInt/div;
		this.midPrc = midInt/div;
		this.longPrc = longInt/div;
	}
	
	/**
	 * Picks a random word from dictionary list of words. 
	 * Uses java.util.Random Class.
	 * 
	 * @return String The random word from Dictionary
	 * 
	 */
	public String pickWord() {
		Random rand = new Random();
		String word = words.get(rand.nextInt(words.size()));
		return word;
	}
	
	/**
	 * Method that picks all words of specific length given
	 * from Dictionary and returns it in a List<String>.
	 * 
	 * @param len Integer corresponding to length of desired words.
	 * @return List<String> A list of words od length equal to len.
	 */
	public List<String> getWordsOfLen(int len) {
		List<String> ret = new ArrayList<>();
		for (String word : words) {
			if (word.length() == len) {
				ret.add(word);
			}
		}
		return ret;
	}
}

