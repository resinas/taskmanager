package isw2.domain.user;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.persistence.Embeddable;

@Embeddable
public class Email implements Serializable {

	  /**
	 * 
	 */
	private static final long serialVersionUID = -7137320837675581299L;

	private static final Pattern VALID_PATTERN = Pattern.compile("^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@(([0-9a-zA-Z])([-\\w]*[0-9a-zA-Z])*\\.)[a-zA-Z]{2,9})$");
	  
	  private String email;
	  
	  public Email(String email) {
		  setEmail(email);
	  }
	  
	  private Email() {
		  
	  }
	  
	  private void setEmail(String email) {
		  if (! VALID_PATTERN.matcher(email).matches())
			  throw new IllegalArgumentException("Email no v‡lido");
		  
		  this.email = email;
		  
	  }
	  
	  public String getEmail() {
		  return email;
	  }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Email))
			return false;
		Email other = (Email) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	  
	  
}
