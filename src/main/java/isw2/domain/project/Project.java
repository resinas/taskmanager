package isw2.domain.project;

import isw2.domain.issue.Issue;
import isw2.domain.user.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Project {

	@EmbeddedId
	private ProjectId id;
	private String name;
	
	@ManyToMany
	private Set<User> members;
	
	@OneToMany(mappedBy="project")
	private Set<Issue> issues;

	private Project() {}
	
	public Project(ProjectId id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.members = new HashSet<User>();
		this.issues = new HashSet<Issue>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProjectId getId() {
		return id;
	}

	public Set<User> getMembers() {
		return Collections.unmodifiableSet(members);
	}
	
	public void addMember(User user){
		members.add(user);
	}
	
	public void removeMember(User user) {
		members.remove(user);
	}

	public Set<Issue> getIssues() {
		return Collections.unmodifiableSet(issues);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Project))
			return false;
		Project other = (Project) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
