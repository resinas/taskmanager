package isw2.infrastructure.persistence.inmemory;

import isw2.domain.issue.Issue;
import isw2.domain.issue.IssueRepository;
import isw2.domain.timelog.TimeLogEntry;
import isw2.domain.timelog.TimeLogHistory;
import isw2.domain.timelog.TimeLogRepository;
import isw2.domain.user.Email;
import isw2.domain.user.User;
import isw2.domain.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class TimeLogRepositoryInMem implements TimeLogRepository {

	private UserRepository userRepository;
	private IssueRepository issueRepository;
	private List<TimeLogEntry> entries;
	
	
	
	public TimeLogRepositoryInMem(UserRepository userRepository,
			IssueRepository issueRepository) {
		super();
		this.userRepository = userRepository;
		this.issueRepository = issueRepository;
		entries = new ArrayList<TimeLogEntry>();
	}

	public void store(TimeLogEntry entry) {
		entries.add(entry);
	}

	public TimeLogHistory lookupTimeLogOfIssue(long id) {
		Issue issue = issueRepository.findIssue(id);
		List<TimeLogEntry> history = new ArrayList<TimeLogEntry>();
		for (TimeLogEntry e: entries) {
			if (e.getIssue().equals(issue)) 
				history.add(e);
		}
		
		return new TimeLogHistory(history);
	}

	public TimeLogHistory lookupTimeLogOfUser(Email user) {
		User u = userRepository.findUser(user);
		List<TimeLogEntry> history = new ArrayList<TimeLogEntry>();
		for (TimeLogEntry e: entries) {
			if (e.getUser().equals(u)) 
				history.add(e);
		}
		
		return new TimeLogHistory(history);
	}

}
