package account_handler;

public class AccountManager {
	
	//data members
	String currentUsername;
	String currentEmail;
	Skin currentSkin;
	Achievement[] achievementList;
	
	// constructor 
	public AccountManager(String username, String email, Skin skin){
		currentUsername = username;
		currentEmail = email;
		currentSkin = skin;
		// achievementList to be constructed later.
	}
	
	//methods 
	public boolean checkPsswd(){
		//TODO
		return false;
	}
	public boolean checkUsername(){
		//TODO
		return false;
	}
	public boolean changePsswd(String newPsswd){
		if(newPsswd.length() < 8){
			return false;
		}
		else{
			// send password change request to cloud
			return true;
		}
	}
	public boolean changeUsername(String newUsername){
		// password change dynamics
		return true;
	}
	public boolean changeSkin(Skin newSkin){
		//TODO
		return false;
	}

}
