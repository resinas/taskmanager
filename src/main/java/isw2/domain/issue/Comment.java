package isw2.domain.issue;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3806197064691032484L;
	private Date creationDate;
	private String text;
	
	private Comment() {}
	
	public Comment(String text) {
		super();
		this.creationDate = new Date();
		this.text = text;
	}
	
	
	public Date getCreationDate() {
		return (Date) creationDate.clone();
	}
	public String getText() {
		return text;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Comment))
			return false;
		Comment other = (Comment) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	
	
	
	
}
