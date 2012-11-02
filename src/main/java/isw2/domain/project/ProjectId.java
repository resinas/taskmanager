package isw2.domain.project;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProjectId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3376449183798147165L;
	private String identifier;
	
	private ProjectId() {
		
	}

	public ProjectId(String identifier) {
		super();
		if (identifier == null) throw new IllegalArgumentException("Identifier is null");
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((identifier == null) ? 0 : identifier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProjectId))
			return false;
		ProjectId other = (ProjectId) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProjectId [" + identifier + "]";
	}
	
	
	
}
