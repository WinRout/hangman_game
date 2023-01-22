package utilities;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
	WriteToFile(String filename, String text) {
	    try {
	      FileWriter writer = new FileWriter("medialab/hangman_"+filename + ".txt");
	      writer.write(text);
	      writer.close();
	      System.out.println("Successfully wrote to the file.");
	    } catch (IOException e) {
	      System.out.println("An error occurred writnig to the file.");
	      e.printStackTrace();
	    }
	  }
}
