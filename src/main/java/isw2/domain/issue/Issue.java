package isw2.domain.issue;

import isw2.domain.project.Project;
import isw2.domain.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Issue {

	// You could have also used a class named IssueId. Actually, that would have been a better option.
	@Id
	private long issueId;
	@ManyToOne
	private Project project;
//	@ManyToOne(targetEntity=User.class)
	@ManyToOne
	@JoinColumn(name="assignedTo")
	private User assignedTo;
//	@ManyToOne(targetEntity=User.class)
	@ManyToOne
	private User createdBy;
	private Date creationDate;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ElementCollection
	private List<Comment> comments;
	
	private Issue() {		
	}
	
	public Issue(long issueId, Project project, User createdBy) {
		super();
		this.issueId = issueId;
		this.project = project;
		this.createdBy = createdBy;
		this.comments = new ArrayList<Comment>();
		this.status = Status.NEW;
		this.creationDate = new Date();
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
		if (status.equals(Status.NEW))
			status = Status.ASSIGNED;
	}

	public Status getStatus() {
		return status;
	}
	
	public List<Status> getNextPossibleStatus() {
		return Status.VALID_TRANSITIONS.get(this.status);
	}

	public void setStatus(Status status) {
		if (! this.status.isValidTransitionTo(status)) throw new IllegalArgumentException("Illegal status transition");
		this.status = status;
	}

	public long getIssueId() {
		return issueId;
	}

	public Project getProject() {
		return project;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public Date getCreationDate() {
		return (Date) creationDate.clone();
	}

	public List<Comment> getComments() {
		return Collections.unmodifiableList(comments);
	}
	
	public void addComment(Comment c) {
		if (c == null) throw new IllegalArgumentException("Comment must not be null");
		comments.add(c);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (issueId ^ (issueId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Issue))
			return false;
		Issue other = (Issue) obj;
		if (issueId != other.issueId)
			return false;
		return true;
	}
	
	
	
}
