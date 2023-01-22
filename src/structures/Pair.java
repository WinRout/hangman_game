package structures;

public class Pair implements Comparable<Pair> {
	
	public Character c;
	public int freq;
	
	public Pair(Character c, int freq) {
		this.c = c;
		this.freq = freq;
	}	
	public int compareTo(Pair otherPair) {
		return this.freq > otherPair.freq ? -1 : this.freq < otherPair.freq ? 1 : 0;
	}
}
