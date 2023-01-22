package application;

import structures.RotatingQueue;

/**
 * A class that holds all the Rounds history (up to 5 games).
 * @author Nikitas Tsinnas, el18187
 *
 */

public class Rounds {

public RotatingQueue<HistoryItem> history;
	
	public Rounds() {
		this.history = new RotatingQueue<>(5);
	}
	
	public void add(String word, int tries, String winner) {
		HistoryItem h = new HistoryItem(word, tries, winner);
		this.history.insertElement(h);
	}
	
	public String getString() {
		String ret = new String();
		for (int i=0; i<this.history.mostRecentItem+1; i++) {
			System.out.println("debug");
			ret += this.history.getElement(i).toString();
			ret += "\n";
		}
		return ret;
	}
	
	private class HistoryItem {
		private String word;
		private int tries;
		private String winner;
		
		HistoryItem(String word, int tries, String winner) {
			this.word = word;
			this.tries = tries;
			this.winner = winner;
		}
		public String toString() {
			String ret = new String();
			ret = "Word: "+this.word+ "   Tries: " + Integer.toString(this.tries) + "   Winner: " + winner + "\n";
			return ret;
		}
	}	
	
}
