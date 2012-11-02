package isw2.infrastructure.persistence.jpa;

import javax.persistence.EntityManager;

public abstract class JPARepository {
	private EntityManager entityManager;
	
	public JPARepository(EntityManager em) {
		this.entityManager = em;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
