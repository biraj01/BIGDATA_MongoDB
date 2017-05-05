package config;


import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import com.mongodb.ServerAddress;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Connection {
	public static final String databasename ="plz";
	public static final String collectionName ="plzcollection";
	public MongoClient mongo;
	
	public static MongoClient connect(){
  	 return new MongoClient( "localhost" , 27017 );		
	}
	
	

	   public static void main( String args[] ) {
		
	      try{
			
	         // To connect to mongodb server
	    	  MongoClient mongo = new MongoClient( "localhost" , 27017 );
				
	         // Now connect to your databases
	    	  DB db = mongo.getDB("plz");
	    	  List<String> dbs = mongo.getDatabaseNames();
	    	  for(String dbank : dbs){
	    	  	System.out.println(dbank);
	    	  }
	    	  Set<String> tables = db.getCollectionNames();

	    	  for(String coll : tables){
	    	  	System.out.println(coll);
	    	  }
	    	  
	    	  DBCollection table = db.getCollection("plzcollection");
	    	  BasicDBObject document = new BasicDBObject();
	    	  document.put("name", "mkyong");
	    	  document.put("age", 30);
	    	  document.put("createdDate", new Date());
	    	  table.insert(document);
	      }catch(Exception e){
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      }
	   }
	}

