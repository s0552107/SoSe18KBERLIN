package de.htwBerlin.ai.kbe.storage;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.htwBerlin.ai.kbe.bean.User;

public class UserBook {
	private static Map<String,User> storage;
	private static UserBook instance = null;
	
	private UserBook() {
		storage = new HashMap<String,User>();
		initSomeUsers();
	}
	
	public synchronized static UserBook getInstance() {
		if (instance == null) {
			instance = new UserBook();
		}
		return instance;
	}
	
	
	
	private static void initSomeUsers() {
		
//		User bob = new User.Builder(23123, "lol")
//				.artist("lo")
//				.album("a")
//				.released(1212).build();
//		
//		storage.put(bob.getId(), bob);
		
		// Leon:  /home/s0552107/Uni/Sose18/kbe/SoSe18KBERLIN/songsRX/src/main/resources/songs.json
		// Emil: /home/s0549218/Dokumente/GIT/KBE/SoSe18KBERLIN/songsRX/src/main/resources/songs.json
		// EmilMac: /Users/emilovic/Documents/htw/git/SoSe18KBERLIN/songsRX/src/main/resources
		try {
			List<User> initUsers = Parser.readJSONToUsers("/home/s0549218/Dokumente/GIT/KBE/SoSe18KBERLIN/songsRX/src/main/resources/user.json");
			for (User u : initUsers)
				storage.put(u.getUserId(), u);
		}
		catch (Exception e) {
			
		}
		
	}
	
	
	
	public Boolean findUser(String userId) {
		if (storage.containsKey(userId))
			return true;
		else
			return false;
	}
	
	public Collection<User> getAllUsers() {
		return storage.values();
	}
	
	
	
	// returns true (success), when user exists in map and was updated
	// returns false, when user does not exist in map
//	public boolean updateUser(User user) {
//		throw new UnsupportedOperationException("updateUser: not yet implemented");
//	}
//	
//	// returns deleted user
//	public User deleteUser(Integer id) {
//		throw new UnsupportedOperationException("deleteUser: not yet implemented");
//	}
}

