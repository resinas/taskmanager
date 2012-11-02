package isw2.domain.timelog;

import isw2.domain.issue.Issue;
import isw2.domain.user.User;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TimeLogEntry {

	@Embedded
	private Activity activity;
	
	@ManyToOne
	private User user;
	private Date beginTime;
	private Date endTime;
	
	@ManyToOne
	private Issue issue;
	
	@Id
	@GeneratedValue
	private long id;
	
	TimeLogEntry () {}
	
	public TimeLogEntry(Activity activity, User user, Date beginTime,
			Date endTime, Issue issue) {
		super();
		if (activity == null ||
				user == null ||
				beginTime == null ||
				endTime == null ||
				issue == null)
			throw new IllegalArgumentException("Argument must not be null");
		if (beginTime.after(endTime)) throw new IllegalArgumentException("Begin time should be before end time");
		this.activity = activity;
		this.user = user;
		this.beginTime = (Date) beginTime.clone();
		this.endTime = (Date) endTime.clone();
		this.issue = issue;
	}
	public Activity getActivity() {
		return activity;
	}
	public User getUser() {
		return user;
	}
	public Date getBeginTime() {
		return new Date(beginTime.getTime());
	}
	public Date getEndTime() {
		return new Date(endTime.getTime());
	}
	public Issue getIssue() {
		return issue;
	}
	
	public long spentTimeInMinutes() {
		double diff = (endTime.getTime() - beginTime.getTime())/(60000);
		return Math.round(diff);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activity == null) ? 0 : activity.hashCode());
		result = prime * result
				+ ((beginTime == null) ? 0 : beginTime.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((issue == null) ? 0 : issue.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TimeLogEntry))
			return false;
		TimeLogEntry other = (TimeLogEntry) obj;
		if (activity == null) {
			if (other.activity != null)
				return false;
		} else if (!activity.equals(other.activity))
			return false;
		if (beginTime == null) {
			if (other.beginTime != null)
				return false;
		} else if (!beginTime.equals(other.beginTime))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (issue == null) {
			if (other.issue != null)
				return false;
		} else if (!issue.equals(other.issue))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
	
	
}
