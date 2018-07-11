package de.htwBerlin.ai.kbe.storage;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.math.BigInteger;

import de.htwBerlin.ai.kbe.bean.User;
import de.htwBerlin.ai.kbe.storage.UsersDAO;

public class UserBook {
	private static Map<String,User> storage;
	private static Map<String, String> m_token;
	private static UserBook instance = null;
	
	private UserBook() {
		storage = new HashMap<String,User>();
		m_token = new HashMap<String, String>();
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
		
		// Leon:  /home/s0552107/Uni/Sose18/kbe/SoSe18KBERLIN/songsRX/src/main/resources/user.json
		// Emil: /home/s0549218/Dokumente/GIT/KBE/SoSe18KBERLIN/songsRX/src/main/resources/songs.json
		// EmilMac: /Users/emilovic/Documents/htw/git/SoSe18KBERLIN/songsRX/src/main/resources
		//Leon Home: /home/ozon/Uni/SoSe18/kbe/SoSe18KBERLIN/songsRX/src/main/resources/user.json
		try {
			List<User> initUsers = Parser.readJSONToUsers("/home/ozon/Uni/SoSe18/kbe/SoSe18KBERLIN/songsRX/src/main/resources/user.json");
			for (User u : initUsers)
				storage.put(u.getUserId(), u);
		}
		catch (Exception e) {
			
		}
		
	}

    public boolean findToken(String token)
    {
        if (m_token.containsKey(token))
        {
            return true;
        }
        else
            return false;
    }

    public String findUserByToken(String token){
		String userId = m_token.get(token);
		return userId;
	}

    public Boolean findUser(String userId) {
		if (storage.containsValue(userId))
			return true;
		else
			return false;
	}
	
	public Collection<User> getAllUsers() {
		return storage.values();
	}
	
	public String createToken(String userId, User user){
	    if (user != null)
        {
            Random random = new SecureRandom();
            String token = new BigInteger(130, random).toString(32);
            // create new Token for every request
            if(!m_token.containsValue(userId))
                m_token.put(token, userId);
            else
                m_token.replace(token, userId);

            return token;
        }
        else
            return "WrongToken";
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
