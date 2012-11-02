package isw2.infrastructure.persistence.inmemory;

import isw2.domain.project.Project;
import isw2.domain.project.ProjectId;
import isw2.domain.project.ProjectRepository;
import isw2.domain.user.Email;
import isw2.domain.user.User;
import isw2.domain.user.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// This class is just for testing purposes and should not be used in production
public class ProjectRepositoryInMem implements ProjectRepository {
	
	private HashMap<ProjectId, Project> projects;
	private UserRepository userRepository;
	
	public ProjectRepositoryInMem(UserRepository userRepository) {
		projects = new HashMap<ProjectId, Project>();
		this.userRepository = userRepository;
	}

	public void store(Project project) {
		projects.put(project.getId(), project);
	}

	public Project findProject(ProjectId identifier) {
		return projects.get(identifier);
	}

	public List<Project> findAll() {
		return new ArrayList<Project>(projects.values());
	}

	public List<Project> findProjectsOfUser(Email user) {
		User u = userRepository.findUser(user);
		List<Project> result = new ArrayList<Project>();
		for (Project p: projects.values()) {
			if (p.getMembers().contains(u))
				result.add(p);
		}
		
		return result;
	}

}
