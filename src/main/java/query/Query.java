package query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import config.Connection;



public class Query {
	MongoClient client = Connection.connect();
	DB db = client.getDB("plz");
	DBCollection table = db.getCollection("plzcollection");
	
	public static void main(String [] args){
		boolean running = true;
		Query query = new Query();
		

		while(running){
			String s = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        System.err.println("Befehl <Parameter> Gültige befehl");
	        System.err.println("quit<No Param>");
	        System.err.println("findcity <plz>");
	        System.err.println("findstate <plz>");
	        System.err.println("findstateandcity <plz>");
	        System.err.println("findplz <city>");

	        try {
				s = br.readLine().toLowerCase();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(s.matches("quit")){
				running = false;
			}else if(s.matches("findcity .*")){
				String id = s.replaceFirst("findcity ", "");
				System.out.println(query.getCity(id));
			}else if(s.matches("findstate .*")){
				String id = s.replaceFirst("findstate ", "");
				System.out.println(query.getState(id));
			}else if(s.matches("findstateandcity .*")){
				String id = s.replaceFirst("findstateandcity ", "");
				System.out.println(query.getStateandCity(id));
			}else if(s.matches("findplz .*")){
				String city = s.replaceFirst("findplz ", "");
				System.out.println(query.getPlz(city));
			}
			else{		
				System.err.println("Illegal Parameter Gütige eingabe eingeben");
			}	
			
		}
		
		
	}
	public String getCity(String id){
		BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("id", id);
	  
	    DBCursor cursor = table.find(whereQuery);
	    String city="";
	    while (cursor.hasNext()) {
	    	String doc = cursor.next().toString();
	        System.out.println(doc);
			JSONObject jsonResult = new JSONObject(doc);
			city = jsonResult.get("city").toString();
	    }
		return city;
	}
	
	public String getState(String id){
		
		BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("id", id);
	  
	    DBCursor cursor = table.find(whereQuery);
	    String state="";
	    while (cursor.hasNext()) {
	    	String doc = cursor.next().toString();
	        System.out.println(doc);
			JSONObject jsonResult = new JSONObject(doc);
			state = jsonResult.get("state").toString();
	    }
		return state;
	}
	
	public String getPlz(String city){
		

		BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("city", city.toUpperCase());
	  
	    DBCursor cursor = table.find(whereQuery);

	    String erg="";
	    while (cursor.hasNext()) {
	    	//System.out.println(city+  " here");
	    	String doc = cursor.next().toString();
	        System.out.println(doc);
			JSONObject jsonResult = new JSONObject(doc);
			String state = jsonResult.get("id").toString();
			//System.out.println("plz: " + state);
			erg =  erg + state + "\n" ;
	    }
		return erg;
	}
	
	public String getStateandCity(String id){
		
		return "";
		
	}

}
