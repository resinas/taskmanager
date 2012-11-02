package isw2.infrastructure.persistence.jpa;

import isw2.domain.timelog.TimeLogEntry;
import isw2.domain.timelog.TimeLogHistory;
import isw2.domain.timelog.TimeLogRepository;
import isw2.domain.user.Email;

import javax.persistence.EntityManager;

public class TimeLogRepositoryImpl extends JPARepository implements
		TimeLogRepository {
	
	public TimeLogRepositoryImpl(EntityManager em) {
		super(em);
	}

	public void store(TimeLogEntry entry) {
		getEntityManager().persist(entry);
	}

	public TimeLogHistory lookupTimeLogOfIssue(long id) {
		return new TimeLogHistory(
					getEntityManager()
						.createQuery("SELECT e FROM TimeLogEntry e WHERE e.issue.issueId = :id)", TimeLogEntry.class)
						.setParameter("id", id)
						.getResultList());
	}

	public TimeLogHistory lookupTimeLogOfUser(Email user) {
		return new TimeLogHistory(
					getEntityManager()
						.createQuery("SELECT e FROM TimeLogEntry e WHERE e.user.email = :email)", TimeLogEntry.class)
						.setParameter("email", user)
						.getResultList());
	}

}
