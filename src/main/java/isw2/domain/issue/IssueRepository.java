package isw2.domain.issue;

import isw2.domain.project.ProjectId;
import isw2.domain.user.Email;

import java.util.List;

public interface IssueRepository {

	public Issue findIssue(long issueId);
	
	public List<Issue> findAll();
	
	public List<Issue> findIssuesOfProject(ProjectId projectId);
	
	public List<Issue> findIssuesAssignedToUser(Email user);
	
	public List<Issue> findIssuesOfProjectAndUser(ProjectId projectId, Email user);
	
	public void store(Issue issue);
	
	// You could have also used Issue as well.
	public void remove(long issueId);
}
