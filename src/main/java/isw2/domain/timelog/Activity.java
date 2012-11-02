package isw2.domain.timelog;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Activity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1645619100358512633L;
	public enum Type {
		ANALYSIS, DESIGN, IMPLEMENTATION, PLANIFICATION;
	}
	
	@Enumerated(value=EnumType.STRING)
	private Type type;
	private String description;
	
	Activity() {}
	
	public Activity(Type type, String description) {
		super();
		this.type = type;
		this.description = description;
	}
	public Type getType() {
		return type;
	}
	public String getDescription() {
		return description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Activity))
			return false;
		Activity other = (Activity) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	
}
