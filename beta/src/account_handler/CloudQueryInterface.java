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

public class CloudQueryInterface 
{
	// Directory of the json cloud key 
	String keyloc = "data/cloud/Space Out Alpha-81438254e2aa.json";
	
	Datastore data;
	
	// CQI constructor 
	public CloudQueryInterface() throws FileNotFoundException, IOException
	{
		data = DatastoreOptions.newBuilder() 
				.setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(keyloc)))
			    .build()
			    .getService();
	}
	
	public boolean updateNameOfEntry(String kind, String key, String name, String value)
	{
		KeyFactory keyFactory = data.newKeyFactory().setKind(kind);
		Key entryKey = keyFactory.newKey(key);
		Entity entity = data.get(entryKey);
		
		if (entity != null)
		{
			Entity ent = Entity.newBuilder(entity).set(name, value).build();
			
			data.update(ent);
			
			return true;	
		}
		
		return false;
	}

	public String getValueFromName(String kind, String key, String name)
	{
		KeyFactory keyFactory = data.newKeyFactory().setKind(kind);
		Key entryKey = keyFactory.newKey(key);
		Entity entity = data.get(entryKey);
		
		if (entity != null)
		{
			// if entity exists get value from name
			
			String value = entity.getString(name);
			
			return value;	
		}
		
		return "";
	}
	
	// add new entry to the given kind with the given key and properties
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
	
	// update a key from a kind that already exists	
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
	
	// get properties of the entry as a String #TODO: change this to a 2D array?
	public String getEntry(String kind, String key)
	{
		KeyFactory keyFactory = data.newKeyFactory().setKind(kind);
		Key entryKey = keyFactory.newKey(key);
		Entity entity = data.get(entryKey);
		
		String returnStr = "";
		
		Set<String> propset = entity.getNames();
		String[] props = propset.toArray(new String[ propset.size()] );
		
		if (entity != null) 
		{
			for(int i = 0; i < props.length; i++)
			{
				String name = props[i];
				String newData = name + ":" + entity.getString(name);
				returnStr += newData + "\n";
			}
		}
		
		return returnStr;
	}
	
	// delete an entry from a kind
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

	// search for a given query and return results
	public String query()
	{
		// TODO
		return "";
	}

}