package isw2.infrastructure.persistence.jpa;

import isw2.domain.issue.Issue;
import isw2.domain.issue.IssueRepository;
import isw2.domain.project.Project;
import isw2.domain.project.ProjectId;
import isw2.domain.project.ProjectRepository;
import isw2.domain.user.Email;
import isw2.domain.user.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
	private EntityManagerFactory emf;
	private EntityManager em;
	
	private User user;
	private User userJack;
	private Project isw2Project;
	private Issue issue1;
	private Issue issue2;

	@Before
	public void setup() {
//		BasicConfigurator.configure();
		emf = Persistence.createEntityManagerFactory("isw2.tasks.test");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		user = new User("John", "Locke", new Email("john.locke@lost.com"));
		userJack = new User("Jack", "Shephard", new Email("jack@lost.com"));
		isw2Project = new Project(new ProjectId("2011-01"),"ISW2" );
		isw2Project.addMember(user);
		isw2Project.addMember(userJack);
		issue1 = new Issue(1, isw2Project, user);
		issue1.setAssignedTo(user);
		issue2 = new Issue(2, isw2Project, user);
		issue2.setAssignedTo(userJack);

		em.persist(user);
		em.persist(userJack);
		em.persist(isw2Project);
		em.persist(issue1);
		em.persist(issue2);
		em.getTransaction().commit();
		em.close();
		
		em = emf.createEntityManager();
	}
	
	@After
	public void cleanup() {
		em.close();
		emf.close();
	}
	
    /**
     * Rigourous Test :-)
     */
	@Test
    public void testApp()
    {
        Assert.assertTrue( true );
    }
	
	@Test
	public void testPersitence() {
		
		em.getTransaction().begin();
		List<User> result = em.createQuery("from User", User.class).getResultList();
		User retrievedUser = result.get(0);
		Assert.assertNotNull(retrievedUser);
		Assert.assertEquals(2, result.size());
		Assert.assertEquals(user, retrievedUser);
		Assert.assertEquals(user.getSignUpDate(), retrievedUser.getSignUpDate());
		
		em.getTransaction().commit();
	}
	
	@Test
	public void testIssueRepositoryOfProject() {
		IssueRepository issueRepository = new IssueRepositoryImpl(em);
		
		List<Issue> issues = issueRepository.findIssuesOfProject(isw2Project.getId());
		Assert.assertEquals(2, issues.size());
		Assert.assertEquals(issue1, issues.get(0));
		
	}
	
	@Test
	public void testIssueRepositoryAssignedUser() {
		IssueRepository issueRepository = new IssueRepositoryImpl(em);
		
		List<Issue> issues = issueRepository.findIssuesAssignedToUser(user.getEmail());
		Assert.assertEquals(1, issues.size());
		Assert.assertEquals(issue1, issues.get(0));
		
		List<Issue> issuesProject = issueRepository.findIssuesOfProjectAndUser(isw2Project.getId(), user.getEmail());
		Assert.assertEquals(issues, issuesProject);
	}
	
	@Test
	public void testProjectRepositoryOfUser() {
		ProjectRepository projectRepository = new ProjectRepositoryImpl(em);

		List<Project> projects = projectRepository.findProjectsOfUser(user.getEmail());
		Assert.assertEquals(1, projects.size());
		Assert.assertEquals(isw2Project, projects.get(0));
	}
}
