package isw2.domain.timelog;

import isw2.domain.user.Email;

public interface TimeLogRepository {

	public void store(TimeLogEntry entry);
	public TimeLogHistory lookupTimeLogOfIssue(long id);
	public TimeLogHistory lookupTimeLogOfUser(Email user);
}
