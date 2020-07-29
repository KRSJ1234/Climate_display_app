package Kevin;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;



public class helper {
	
	public static String getCoordinates(String city) throws ParseException, IOException {
		city = URLEncoder.encode(city, "UTF-8");
		String urlstr = "https://api.mapbox.com/geocoding/v5/mapbox.places/"+city+".json?access_token=pk.eyJ1Ijoia3JzajEyMzQiLCJhIjoiY2tja2ptcWY2MXc5aDM1bngwanJ2OW12MyJ9.rArHeU0GXf1qIJCu73o4SQ";
		URL url = new URL(urlstr);
		HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();
		httpconn.setRequestMethod("GET");
		httpconn.setRequestProperty("Content-Type", "application/json\"");
		
		int status = httpconn.getResponseCode();
		//System.out.println("This is helper getcoordinates"+ urlstr);
		String coord = null;
		if(status==200) {
			Scanner netSc = new Scanner(httpconn.getInputStream());
			StringBuffer content = new StringBuffer();
			while (netSc.hasNextLine()) {
			    content.append(netSc.nextLine()+"\n");
			}
			netSc.close();
			
			coord = content.toString();
		}

		
		if(coord==null)
			return null;
		
		JSONObject jsonObj = (JSONObject) (new JSONParser().parse(coord));
		JSONArray Jarr = (JSONArray) jsonObj.get("features");
		Iterator featuresIterator = Jarr.iterator();
		
		if(featuresIterator.hasNext()) {
			JSONArray newiterator = (JSONArray) ((JSONObject)featuresIterator.next()).get("center"); 
			Iterator latLonIt = newiterator.iterator();
			
			JSONObject result = new JSONObject();
			result.put("longitude", (double) latLonIt.next());
			result.put("latitude",(double) latLonIt.next());
			
			return result.toJSONString();
		}
		
		return "";
	}
	
	public static String getWeather(String location_coord) throws ParseException, IOException {
		
		JSONObject Json_coord = (JSONObject)new JSONParser().parse(location_coord);
		double latitude = (double) (Json_coord.get("latitude"));
		double longitude = (double)( Json_coord.get("longitude"));
		
		String urlstr = "https://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&appid=e32118b878a0aeca173446910cb2ed5b";
		String weatherans="";
		
		try {
			URL url = new URL(urlstr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			
			int status = connection.getResponseCode();
			
			
			
			if(status==200) {
				Scanner netSc = new Scanner(connection.getInputStream());
				StringBuffer content = new StringBuffer();
				while (netSc.hasNextLine()) {
				    content.append(netSc.nextLine()+"\n");
				}
				netSc.close();
				weatherans =  content.toString();
			}
			
			if(weatherans==null)
				return null;
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		
		
		JSONObject jsonObj = (JSONObject) (new JSONParser().parse(weatherans.toString()));
		JSONObject weather_ans = (JSONObject)((JSONArray) jsonObj.get("weather")).iterator().next();
		JSONObject result = new JSONObject();
		
		result.put("description",(String)weather_ans.get("main"));
		
		String icon = (String)((JSONObject)((JSONArray) jsonObj.get("weather")).iterator().next()).get("icon");
		result.put("icon", icon);
		
		result.put("name",(String)jsonObj.get("name"));
		
		double temp = (double)((JSONObject) jsonObj.get("main")).get("temp");
		DecimalFormat formatter = new DecimalFormat("##.00");
		result.put("temperature", Double.parseDouble(formatter.format((temp-273.15))));
		
		return result.toJSONString();
	}
	
	
}
