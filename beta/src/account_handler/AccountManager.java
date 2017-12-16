package account_handler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AccountManager 
{
	//data members
	CloudQueryInterface cloud;
	String currentUsername;
	Skin currentSkin;
	Achievement[] achievementList;
	
	public String currentUserName;
	
	// constructor 
	public AccountManager()
	{
		try {cloud = new CloudQueryInterface();} 
		catch (FileNotFoundException e) {e.printStackTrace();} 
		catch (IOException e){e.printStackTrace();}
	}
	
	public String getPassword()
	{
		return cloud.getValueFromName("user", currentUserName, "password");
	}
	
	public void changePassword(String newPass)
	{
		cloud.updateNameOfEntry("user", currentUserName, "password", newPass);
	}
	
	//methods 
	public boolean checkSignUpData(String username, String psswd) 
	{
		boolean newUser = cloud.newEntry("user", username, new String[]{"scores:0,0,0,0,0,0",
				 "password:" + psswd,
				 "achievements:" + "1"});
		
		if (newUser)
			currentUsername = username;
		
		return newUser;
	}
	
	public boolean checkLoginData(String username, String psswd) 
	{
		String resultPassword = cloud.getValueFromName("user", username, "password");

		if (!resultPassword.equals(""))
		{
			// then, the user exist
			/*String[] results = cloudUserResult.split("password:");

			String resultPassword = results[1].substring(0, results[1].length() - 1);*/

			if (psswd.equals(resultPassword))
			{
				// then, user has successfully logged in.
				currentUserName = username;
				
				return true;
			}
		}
		
		return false;
	}
	
	public boolean changePsswd(String newPsswd)
	{
		if(newPsswd.length() < 8)
		{
			return false;
		}
		else
		{
			// send password change request to cloud
			return true;
		}
	}
	
	public boolean changeUsername(String newUsername)
	{
		// password change dynamics
		return true;
	}
	
	public boolean changeSkin(Skin newSkin)
	{
		//TODO
		return false;
	}

	public boolean sendScore(int score, int worldNo, int levelNo)
	{
		System.out.println(currentUserName);
		
		String results = cloud.getEntry("user", currentUserName);
		
		String[] elements = results.split("scores:", 2);
		
		String[] scores = (elements[1].split(","));
		
		int previousScore = 0;
		int index = 0;
		
		if (worldNo == 1)
		{
			if (levelNo == 1)
			{
				previousScore = Integer.parseInt(scores[0]);
				index = 0;
			}
			
			else if (levelNo == 2)
			{
				previousScore = Integer.parseInt(scores[1]);
				index = 1;
			}
		}
		else if (worldNo == 2)
		{
			if (levelNo == 1)
			{
				previousScore = Integer.parseInt(scores[2]);
				index = 2;
			}
			
			else if (levelNo == 2)
			{
				previousScore = Integer.parseInt(scores[3]);
				index = 3;
			}
		}
		
		else if (worldNo == 3)
		{
			if (levelNo == 1)
			{
				previousScore = Integer.parseInt(scores[4]);
				index = 4;
			}
			
			else if (levelNo == 2)
			{
				previousScore = Integer.parseInt(scores[5]);
				index = 5;
			}
		}
		
		if (score < previousScore)
		{
			return false;
		}
		
		scores[index] = ("" + score);
		
		String scoreString = String.join(",", scores);
		
		System.out.println(scoreString);
		
		cloud.updateNameOfEntry("user", currentUserName, "scores", scoreString);
		
		return true;
	}
	
	public String getScores(){
		return cloud.getValueFromName("user", currentUserName, "scores");
	}
}
