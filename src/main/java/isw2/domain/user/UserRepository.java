package isw2.domain.user;

import java.util.List;

public interface UserRepository {

	public void store(User user);
	
	public User findUser(Email email);
	
	public List<User> findAll();
}
