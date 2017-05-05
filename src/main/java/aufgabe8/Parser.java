package aufgabe8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import config.Connection;

public class Parser {

	public static void main(String [] args){
		MongoClient client = Connection.connect();
		DB db = client.getDB("sindeslebens");
		DBCollection table = db.getCollection("sindeslebensdoc");
		try {
			BufferedReader reader = new BufferedReader(new FileReader("/Volumes/biraj/workspace/bigdata_mongodb/sinndeslebens.txt"));
			String s;
			while((s = reader.readLine() )!= null){
				String jsonString = s;
				JSONObject jsonResult = new JSONObject(jsonString);
				System.out.println(jsonResult.toString());
				String pop = jsonResult.get("pop").toString();
				String loc = jsonResult.get("loc").toString();
				String id = jsonResult.get("_id").toString();
				String city = jsonResult.get("city").toString();
				String state = jsonResult.get("state").toString();
				
		    	  BasicDBObject document = new BasicDBObject();
		    	  Map m = new HashMap<>();
		    	  m.put("id", id);
		    	  m.put("pop", pop);
		    	  m.put("loc", loc);
		    	  m.put("city",city);
		    	  m.put("state", state);
		    	  document.putAll(m);
		    	  table.insert(document);
				System.out.println("pop:" + pop + "  loc: " + loc + " id:" + id + " city: " + city + " state: " + state);
			}
		} catch (  JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}

