import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author SJHSStudent
 *
 */ 
public class GerryManderingScienceResearch extends JFrame implements ActionListener {

	/**
	 * @param args
	 */
	private static final long serialVersionUID = 1; 
	private GridBagLayout layout;
	private JTextField myTextField; // variable for text box
	
	/*
	 * Open in project explorer, 
	 */
	/**
	 * @param args
	 */
	public GerryManderingScienceResearch(){
		
		 super( "ScienceResearchGerryMandeering");
		 layout = new GridBagLayout();
		 // variable for the gridbag layout
		 JButton button;
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 GridBagConstraints c = new GridBagConstraints(); // creates a new GridBagRestraint
			// general constraints
			c.fill = GridBagConstraints.BOTH; // sets c to resize components BOTH vertically and horizontally
			// text field setup
		
			
			button = new JButton("Redictrict");
			button.setFont(new Font("TimesRoman", Font.ITALIC, 12)); // sets font, makes font italicized and font size
			button.addActionListener(this); // adds ActionListener to button to roll dice
			layout.setConstraints(button, c); // more button constraints
			button.setVisible(true);
			add(button); // adds button to frame

			myTextField = new JTextField("Please enter a county you would like to redistrict"); // creates new text field and sets text in textfield
			c.weightx = 1.0; // sets weight for text field to have more weight
			c.gridwidth = 5; // allocates 5 cells for text field
			c.gridwidth = GridBagConstraints.REMAINDER; // text field restraints
			layout.setConstraints(myTextField, c); // text field constraints
			myTextField.setEditable(true); // makes the text field not editable 
			add(myTextField);
			myTextField.addActionListener(this);
			setResizable(true); // makes the frame unresizable 
			setSize(500, 500); // sets the frame to the preferred size 
			 setVisible(true); // makes the frame visible
		 /*
		  * EXAMPLE ON HOW TO GOOGLE SEARCH
		  * 
		  *  String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
    String search = "stackoverflow";
    String charset = "UTF-8";

    URL url = new URL(google + URLEncoder.encode(search, charset));
    Reader reader = new InputStreamReader(url.openStream(), charset);
    GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

    // Show title and URL of 1st result.
    System.out.println(results.getResponseData().getResults().get(0).getTitle());
    System.out.println(results.getResponseData().getResults().get(0).getUrl());
		  */
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GerryManderingScienceResearch();
		/*
		 * NOTE TO ASK MR. WOYTEK: 
		 * Ask about if possible to read type from text field 
		 * 
		 * Enter name of a county- check
		 * find name of all voting districts / voting areas using queries
		 * find results from elections online in said voting districts / township using queries
		 * find GPS coordinates for said voting districts /  townships using queries
		 * Use distance formula to find the the shortest line between GPS coordinates *NOTE* API will do this for me if i learn and use it
		 * Draw a map on window to show township /  voting district boundaries
		 * ***NOTE*** MUST exclude state, city, and preferably unaffected boundaries
		 */
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {


public class PlacesService {
    private static final String LOG_TAG = "ExampleApp";

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";

    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String TYPE_DETAILS = "/details";
    private static final String TYPE_SEARCH = "/search";

    private static final String OUT_JSON = "/json";

    // KEY!
    private static final String API_KEY = "AIzaSyBA8fULAQpdL9Z6p6j88ETFwBMr9sQQ1Qs";

    public static ArrayList<Place> autocomplete(String input) {
        ArrayList<Place> resultList = null;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
            sb.append(TYPE_AUTOCOMPLETE);
            sb.append(OUT_JSON);
            sb.append("?sensor=false");
            sb.append("&key=" + API_KEY);
            sb.append("&input=" + URLEncoder.encode(input, "utf8"));

            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            resultList = new ArrayList<Place>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                Place place = new Place();
                place.reference = predsJsonArray.getJSONObject(i).getString("reference");
                place.name = predsJsonArray.getJSONObject(i).getString("description");
                resultList.add(place);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error processing JSON results", e);
        }

        return resultList;
    }

    public static ArrayList<Place> search(String keyword, double lat, double lng, int radius) {
        ArrayList<Place> resultList = null;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
            sb.append(TYPE_SEARCH);
            sb.append(OUT_JSON);
            sb.append("?sensor=false");
            sb.append("&key=" + API_KEY);
            sb.append("&keyword=" + URLEncoder.encode(keyword, "utf8"));
            sb.append("&location=" + String.valueOf(lat) + "," + String.valueOf(lng));
            sb.append("&radius=" + String.valueOf(radius));

            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("results");

            // Extract the Place descriptions from the results
            resultList = new ArrayList<Place>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                Place place = new Place();
                place.reference = predsJsonArray.getJSONObject(i).getString("reference");
                place.name = predsJsonArray.getJSONObject(i).getString("name");
                resultList.add(place);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error processing JSON results", e);
        }

        return resultList;
    }

    public static Place details(String reference) {
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
            sb.append(TYPE_DETAILS);
            sb.append(OUT_JSON);
            sb.append("?sensor=false");
            sb.append("&key=" + API_KEY);
            sb.append("&reference=" + URLEncoder.encode(reference, "utf8"));

            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return null;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        Place place = null;
        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString()).getJSONObject("result");

            place = new Place();
            place.icon = jsonObj.getString("icon");
            place.name = jsonObj.getString("name");
            place.formatted_address = jsonObj.getString("formatted_address");
            if (jsonObj.has("formatted_phone_number")) {
                place.formatted_phone_number = jsonObj.getString("formatted_phone_number");
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error processing JSON results", e);
        }

        return place;
    }
}
		 


}
