package isw2.infrastructure.persistence.jpa;

import isw2.domain.user.Email;
import isw2.domain.user.User;
import isw2.domain.user.UserRepository;

import java.util.List;

import javax.persistence.EntityManager;

public class UserRepositoryImpl extends JPARepository implements UserRepository {

	public UserRepositoryImpl(EntityManager em) {
		super(em);
	}
	
	public void store(User user) {
		getEntityManager().persist(user);
	}

	public User findUser(Email email) {
		return getEntityManager().find(User.class, email);
	}

	public List<User> findAll() {
		return getEntityManager().createQuery("from User", User.class).getResultList();
	}

}
