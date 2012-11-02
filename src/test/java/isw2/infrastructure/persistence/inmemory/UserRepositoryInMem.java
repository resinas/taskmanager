package isw2.infrastructure.persistence.inmemory;

import isw2.domain.user.Email;
import isw2.domain.user.User;
import isw2.domain.user.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositoryInMem implements UserRepository {

	private Map<Email,User> users;
	
	public UserRepositoryInMem() {
		users = new HashMap<Email,User>();
	}
	
	public void store(User user) {
		if (users.containsKey(user.getEmail())) throw new IllegalArgumentException("Users already exists");
		users.put(user.getEmail(), user);
	}

	public User findUser(Email email) {
		return users.get(email);
	}

	public List<User> findAll() {
		return new ArrayList<User>(users.values());
	}
	
	public void init() {
		store(new User("John", "Locke", new Email("john.lock@lost.com")));
		store(new User("Jack", "Sheppard", new Email("jack.shepard@lost.com")));
		store(new User("Hugo", "Reyes", new Email("hugo@lost.com")));
	}

}
