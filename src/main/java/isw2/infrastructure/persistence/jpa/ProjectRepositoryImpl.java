package isw2.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import isw2.domain.project.Project;
import isw2.domain.project.ProjectId;
import isw2.domain.project.ProjectRepository;
import isw2.domain.user.Email;

public class ProjectRepositoryImpl extends JPARepository implements
		ProjectRepository {
	
	public ProjectRepositoryImpl(EntityManager em){
		super(em);
	}

	public void store(Project project) {
		getEntityManager().persist(project);
	}

	public Project findProject(ProjectId identifier) {
		return getEntityManager().find(Project.class, identifier);
	}

	public List<Project> findAll() {
		return getEntityManager().createQuery("from Project", Project.class).getResultList();
	}

	public List<Project> findProjectsOfUser(Email user) {
		return getEntityManager()
				.createQuery("SELECT p FROM Project p JOIN p.members u WHERE u.email = :email",Project.class)
				.setParameter("email", user)
				.getResultList();
	}

}
