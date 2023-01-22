package structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.Game;

public class Letters {
	
	public List<List<Pair>> letters;
	private Game game;
	
	public Letters (Game game) {
		this.letters = new ArrayList<>();
		this.game = game;
	}
	
	public List<Pair> get (int i) {
		List<Pair> ret = new ArrayList<Pair>();
		ret = this.letters.get(i); 
		return ret;
	}
	
	public void update() {
		this.letters = new ArrayList<>();
		for (int i=0; i<game.answer.length(); i++) {
			this.letters.add(lettersOfPosition(i));
		}
	}
	
	private List<Pair> lettersOfPosition(int position) {
		List<Pair> ret = new ArrayList<Pair>();
		for (String word : game.words) {
				boolean found = false;
				for (Pair pair : ret) {
					if (pair.c == word.charAt(position)) {
						pair.freq++;
						found = true;
						break;
					}
				}
				if (!found) ret.add(new Pair(word.charAt(position), 1));
		}
		Collections.sort(ret);
		return ret;
	}
	
	public String toString() {
		String ret = new String();
		for (int i=0; i<game.answer.length(); i++) {	
			ret = ret + Integer.toString(i) + ": ";
			for (Pair pair : this.letters.get(i)) {
				ret = ret + pair.c + ", "; 
			}
			ret = ret + "\n" ;
		}
		return ret;
	}
}
