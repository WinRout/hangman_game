package utilities;

import java.io.File;
import java.io.IOException;

public class CreateFile {
	CreateFile(String dictionaryID) {
    	try  {	
			File txtFile = new File("medialab/hangman_" + dictionaryID + ".txt");
			if (txtFile.createNewFile()) {
				System.out.println("File created: " + txtFile.getName());
			} else {
				System.out.println("File already exists.");
			}
		}
    	catch (IOException e) {
    		System.out.println("An error occurred creating the file.");
    		e.printStackTrace();
    	}
	}
}
