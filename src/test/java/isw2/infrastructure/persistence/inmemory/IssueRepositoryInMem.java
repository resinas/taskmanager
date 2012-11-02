package isw2.infrastructure.persistence.inmemory;

import isw2.domain.issue.Issue;
import isw2.domain.issue.IssueRepository;
import isw2.domain.project.Project;
import isw2.domain.project.ProjectId;
import isw2.domain.project.ProjectRepository;
import isw2.domain.user.Email;
import isw2.domain.user.User;
import isw2.domain.user.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IssueRepositoryInMem implements IssueRepository {
	
	private Map<Long, Issue> issues;
	private UserRepository userRepository;
	private ProjectRepository projectRepository;
	
	

	public IssueRepositoryInMem(UserRepository userRepository,
			ProjectRepository projectRepository) {
		super();
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
		issues = new HashMap<Long, Issue>();		
	}

	public Issue findIssue(long issueId) {
		return issues.get(issueId);
	}

	public List<Issue> findAll() {
		return new ArrayList<Issue>(issues.values());
	}

	public List<Issue> findIssuesOfProject(ProjectId projectId) {
		Project p = projectRepository.findProject(projectId);
		List<Issue> result = new ArrayList<Issue>();

		for (Issue i: issues.values()) {
			if(i.getProject().equals(p))
				result.add(i);
		}
		return result;
	}

	public List<Issue> findIssuesAssignedToUser(Email user) {
		User u = userRepository.findUser(user);
		List<Issue> result = new ArrayList<Issue>();

		for (Issue i: issues.values()) {
			if(i.getAssignedTo().equals(u))
				result.add(i);
		}
		return result;
	}

	// We are almost duplicating three times the same code. Can you think of a way to avoid it?
	public List<Issue> findIssuesOfProjectAndUser(ProjectId projectId,
			Email user) {
		Project p = projectRepository.findProject(projectId);
		User u = userRepository.findUser(user);
		List<Issue> result = new ArrayList<Issue>();
		
		for (Issue i: issues.values()) {
			if(i.getAssignedTo().equals(u) && i.getProject().equals(p))
				result.add(i);
		}
		
		return result;
	}

	public void store(Issue issue) {
		issues.put(issue.getIssueId(), issue);

	}

	public void remove(long issueId) {
		issues.remove(issueId);
	}

}
