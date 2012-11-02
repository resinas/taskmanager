package isw2.domain.issue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Status {
	NEW, ASSIGNED, RESOLVED, CLOSED;
	
	public static final Map<Status, List<Status>>VALID_TRANSITIONS= new HashMap<Status, List<Status>>();
	
	static {
		VALID_TRANSITIONS.put(NEW, 		Arrays.asList(ASSIGNED, RESOLVED));
		VALID_TRANSITIONS.put(ASSIGNED, Arrays.asList(RESOLVED));
		VALID_TRANSITIONS.put(RESOLVED, Arrays.asList(ASSIGNED, CLOSED));
		VALID_TRANSITIONS.put(CLOSED, 	new ArrayList<Status>());			
	}
	
	public boolean isValidTransitionTo(Status s) {
		return VALID_TRANSITIONS.get(this).contains(s);
	}

	
}
