import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;

import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Run {

	public static void main(String[] args) throws IOException
	{
		// location of the auth json key
		String keyloc = "C:/Users/UmutBerk/Desktop/"
				+ "Google Cloud Datastore Stuff/"
				+ "Space Out Alpha-81438254e2aa.json";
		
		// scanner ohject instantiation
		Scanner scan = new Scanner(System.in);
		
		// create the datastore object and pass in the key
		Datastore data = DatastoreOptions.newBuilder() 
				.setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(keyloc)))
			    .build()
			    .getService();
		
		// select the kind
		KeyFactory keyFactory = data.newKeyFactory().setKind("user");
		
		// user string inst
		String user = "gcloud";		
		
		//main loop
		while ( !user.equals("quit") || !user.equals("q") )
		{
			System.out.print("> ");
			user = scan.nextLine();
			
			// create new user with given data
			if (user.equals("new"))
			{
				System.out.print("Username: ");
				String username = scan.nextLine();
				
				Key userKey = keyFactory.newKey(username);
				
				Entity entity = data.get(userKey);
				
				if (entity != null) // if user does not exist
					System.out.println("User already exists.");
				else
				{
					System.out.print("Password: ");
					String password = scan.nextLine();
					
					System.out.print("E-mail: ");
					String email = scan.nextLine();
					
					entity = Entity.newBuilder(userKey).set("email", email)
							.set("password", password)
							.build();
					
					data.put(entity);
				}
			}
			
			// update an already existing user with given data
			else if (user.equals("update"))
			{
				System.out.print("Username: ");
				String userStr = scan.nextLine();
				
				Key userKey = keyFactory.newKey(userStr);
				
				Entity entity = data.get(userKey);
				
				System.out.print("Password: ");
				String password = scan.nextLine();
				
				System.out.print("E-mail: ");
				String email = scan.nextLine();
				
				if (entity != null) // if user does not exist
				{
					  entity = Entity.newBuilder(entity)
							  .set("email", email)
							  .set("password", password)
							  .build();
					  
					  data.update(entity);
				}
				else
				{
					System.out.println("The user was not found.");
				}
			}
			
			// delete a given user from the datastore
			else if (user.equals("delete"))
			{
				System.out.print("Username: ");
				String userStr = scan.nextLine();
				
				Key userKey = keyFactory.newKey(userStr);
				
				Entity entity = data.get(userKey);
				
				if (entity != null) 
				{
					data.delete(userKey);
				}
				else
				{
					System.out.println("The user was not found.");
				}
			}
		}
	}
}
