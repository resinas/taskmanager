package isw2.domain.project;

import isw2.domain.user.Email;

import java.util.List;

public interface ProjectRepository {

	public void store(Project project);
	public Project findProject(ProjectId identifier);
	public List<Project> findAll();
	public List<Project> findProjectsOfUser(Email user);	
}
