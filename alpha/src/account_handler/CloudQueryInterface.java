package account_handler;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;

import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

public class CloudQueryInterface {
	
	String kind;
	String query;
	
	String keyloc = "data/cloud/Space Out Alpha-81438254e2aa.json";
	Datastore data;
	
	public CloudQueryInterface( /*String kind, String query*/ ) throws FileNotFoundException, IOException{
		/*this.kind = kind;
		this.query = query;*/
		
		data = DatastoreOptions.newBuilder() 
				.setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(keyloc)))
			    .build()
			    .getService();
	}
	
	public boolean newEntry(String kind, String key, String[] param)
	{
		KeyFactory keyFactory = data.newKeyFactory().setKind(kind);
		Key entryKey = keyFactory.newKey(key);
		Entity entity = data.get(entryKey);
		
		if (entity != null)
		{
			// if user does not exist
			System.out.println("Entity with key: " + key + " already exists.");
			return false;
		}
					
		entity = Entity.newBuilder(entryKey).build();
			
		for(int i = 0; i < param.length; i++)
		{
			String[] currentParams = param[i].split(":", 2);
				
			entity = Entity.newBuilder(entity).set(currentParams[0], currentParams[1]).build();				
		}
			
		data.put(entity);
		
		System.out.println("Entity with key: " + key + " added to datastore.");
		return true;		
	}
	
	public boolean updateEntry(String kind, String key, String[] param)
	{
		KeyFactory keyFactory = data.newKeyFactory().setKind(kind);
		Key entryKey = keyFactory.newKey(key);
		Entity entity = data.get(entryKey);
		
		if (entity != null)
		{
			// if entity exists, update it
			
			entity = Entity.newBuilder(entryKey).build();
			
			for(int i = 0; i < param.length; i++)
			{
				String[] currentParams = param[i].split(":", 2);
					
				entity = Entity.newBuilder(entity).set(currentParams[0], currentParams[1]).build();				
			}
				
			data.put(entity);
			
			System.out.println("Entity with key: " + key + " was updated");
			return true;
		}
		
		System.out.println("Entity with key " + key + " does not exist.");
		return false;
		
		
	}
	
	public String getEntry(String kind, String key)
	{
		KeyFactory keyFactory = data.newKeyFactory().setKind(kind);
		Key entryKey = keyFactory.newKey(key);
		Entity entity = data.get(entryKey);
		
		String returnStr = "";
		
		Set<String> propset = entity.getNames();
		String[] props = propset.toArray(new String[propset.size()]);
		
		
		if (entity != null) 
		{
			for(int i = 0; i < props.length; i++)
			{
				String name = props[i];
				String newData = name + " : " + entity.getString(name);
				returnStr += newData + "\n";
			}
		}
		
		return returnStr;
	}
	
	public boolean deleteEntry(String kind, String key)
	{
		KeyFactory keyFactory = data.newKeyFactory().setKind(kind);
		Key entryKey = keyFactory.newKey(key);
		Entity entity = data.get(entryKey);
		
		if (entity != null) 
		{
			data.delete(entryKey);
			System.out.println("The entity with key " + key + " is deleted.");
			return true;
		}
			
		System.out.println("The entity with key " + key + " was not found.");
		return false;
	}

	public String query()
	{
		// TODO
		return "";
	}

}
