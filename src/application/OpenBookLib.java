package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;

/**
 * A calss for JASON parsing. It gets the description of a specific BookID given
 * from the BOOKopenLibrary
 * @author Nikitas Tsinnas, el18187
 *
 */

public class OpenBookLib {
	
	private static HttpURLConnection connection;
	private static String openLibraryID;
	public static String description;
	
	public OpenBookLib(String openLibraryID) {
		OpenBookLib.openLibraryID = openLibraryID;
		description = new String();
	}
	
	public String getDescritpion() throws Exception {
		sendRequest(openLibraryID);
		return OpenBookLib.description;
	}
	
	private static void sendRequest(String openLibraryID) throws Exception {
		//java.net.HttpURLConnection
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();
		try {
			URL url = new URL("https://openlibrary.org/works/" + openLibraryID + ".json");
			connection = (HttpURLConnection) url.openConnection();
			
			//Request setup
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			
			int status = connection.getResponseCode();
			//System.out.println(status);
			
			if(status > 299) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			parse(responseContent.toString());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
	}
	
	private static String parse (String responseBody) throws Exception {
		JSONObject response = new JSONObject(responseBody);
		JSONObject description = response.getJSONObject("description");
		String text = description.getString("value");
		OpenBookLib.description = text;
		return null;	
	}
}