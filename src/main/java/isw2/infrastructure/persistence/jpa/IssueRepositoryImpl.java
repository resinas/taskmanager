package isw2.infrastructure.persistence.jpa;

import isw2.domain.issue.Issue;
import isw2.domain.issue.IssueRepository;
import isw2.domain.project.ProjectId;
import isw2.domain.user.Email;

import java.util.List;

import javax.persistence.EntityManager;

public class IssueRepositoryImpl extends JPARepository implements
		IssueRepository {

	public IssueRepositoryImpl(EntityManager em) {
		super(em);
	}

	public Issue findIssue(long issueId) {
		return getEntityManager().find(Issue.class, issueId);
	}

	public List<Issue> findAll() {
		return getEntityManager().createQuery("from Issue", Issue.class)
				.getResultList();
	}

	public List<Issue> findIssuesOfProject(ProjectId projectId) {
		return getEntityManager()
				.createQuery("SELECT i FROM Issue i WHERE i.project.id = :projectId", Issue.class)
				.setParameter("projectId", projectId)
				.getResultList();
	}

	public List<Issue> findIssuesAssignedToUser(Email user) {
		return getEntityManager()
				.createQuery("SELECT i FROM Issue i WHERE i.assignedTo.email = :email", Issue.class)
				.setParameter("email", user)
				.getResultList();
	}

	public List<Issue> findIssuesOfProjectAndUser(ProjectId projectId,
			Email user) {
		return getEntityManager()
				.createQuery("SELECT i FROM Issue i WHERE i.assignedTo.email = :email AND i.project.id = :id", Issue.class)
				.setParameter("email", user)
				.setParameter("id", projectId)
				.getResultList();
	}

	public void store(Issue issue) {
		getEntityManager().persist(issue);
	}

	public void remove(long issueId) {
		Issue issueToRemove = findIssue(issueId);
		getEntityManager().remove(issueToRemove);

	}

}
