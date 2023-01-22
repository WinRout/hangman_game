package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import structures.Letters;
import structures.Pair;

/**
 * A class that holds all necessary information about the Game. It also
 * implements basic utilities.
 * @author Nikitas Tsinnas, el18187
 *
 */

public class Game {
	
	public int lives;
	public int points;
	public String answer;
	public List<Character> state;
	public int tries;
	public double percentange;
	public List<String> words;
	public Letters letters;
	
	private int rightGuess;

	public Game(String word, Dictionary dict) {
		this.lives = 6;
		this.points = 0;
		this.answer = word;
		this.state=new ArrayList<Character>();
		this.tries = 0;
		this.percentange = 0;
		this.rightGuess = 0;
		
		this.words = dict.getWordsOfLen(this.answer.length());
		this.letters = new Letters(this);
		this.letters.update();
		
		for (int i=0; i<this.answer.length(); i++) {
			this.state.add('_');
		}
	}
	
	public boolean hasWon() {
		if (this.rightGuess == this.answer.length()) return true;
		else return false;
	}
	
	public boolean guess(int p, Character c) {
		
		this.tries++;
		
		Set<String> set = new HashSet<>(this.words);
		
		if (this.answer.charAt(p) == c) { //right guess
			this.rightGuess++;
			//remove the words that are not possible answer
			for (String word : this.words) {
				if (word.charAt(p) != c) {
					set.remove(word);
				}
			}
			//calculate possibility of given letter
			double letterPossibility = 0;
			for (Pair pair : letters.get(p)) {
				if (pair.c == c) {
					letterPossibility = pair.freq/(double)this.words.size();
				}
			}			
			//System.out.println("Letter possibility: " + letterPossibility);
			
			//add the points
			if (letterPossibility >= 0.6) {
				this.points += 5;
			}
			else if (letterPossibility >= 0.4) {
				this.points += 10;
			}
			else if (letterPossibility >= 0.25) {
				this.points += 15;
			}
			else {
				this.points += 30;
			}
			this.state.set(p,c);
		}
		else { //wrong guess
			
			//remove the words that are not possible answer
			for (String word : this.words) {
				if (word.charAt(p) == c) {
					set.remove(word);
				}
			}
			this.lives--; //remove a life
			points = Math.max(points-15, 0); //remove 15 points. No under 0
		}
		
		this.words = new ArrayList<>(set);
		this.letters.update();
		this.percentange = (this.tries-(6-this.lives))/(double)(this.tries);
		
		return (this.answer.charAt(p) == c);
	}
	
	
	public List<Integer> getPositions() {
		List<Integer> ret = new ArrayList<>();
		for (int i=0; i < this.answer.length(); i++) {
			if (this.state.get(i) == '_') {
				ret.add(i);
			}
		}
		return ret;
	}
	
	
	public List<Character> getLetters(int position) {
		List<Character> ret = new ArrayList<>();
		for (Pair p : this.letters.get(position)) {
			ret.add(p.c);
		}
		return ret;
	}
	
	public void printState() {
		for (String w : this.words) {
			System.out.println(w);
		}
		System.out.println("ANSWER: " + this.answer);
	}
	
}
