package isw2.domain.timelog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeLogHistory {
	public static final TimeLogHistory EMPTY = new TimeLogHistory(
			Collections.<TimeLogEntry> emptyList());

	private final List<TimeLogEntry> timelogs;

	public TimeLogHistory(List<TimeLogEntry> history) {
		if (history == null)
			throw new IllegalArgumentException("History is null");
		this.timelogs = new ArrayList<TimeLogEntry>(history);
	}

	public long totalTimeSpentInMinutes() {
		long result = 0;
		for (TimeLogEntry t: timelogs) {
			result += t.spentTimeInMinutes();
		}
		
		return result;
	}
	
	public List<TimeLogEntry> getTimeLogs() {
		return Collections.unmodifiableList(timelogs);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((timelogs == null) ? 0 : timelogs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TimeLogHistory))
			return false;
		TimeLogHistory other = (TimeLogHistory) obj;
		if (timelogs == null) {
			if (other.timelogs != null)
				return false;
		} else if (!timelogs.equals(other.timelogs))
			return false;
		return true;
	}

}
