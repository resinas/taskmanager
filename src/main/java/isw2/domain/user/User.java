package isw2.domain.user;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class User {

	@EmbeddedId
	private Email email;
	private String firstName;
	private String lastName;
	@Temporal(TemporalType.TIMESTAMP)
	private Date signUpDate;
	
	private User() {
		
	}
	
	public User(String firstName, String lastName, Email email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;		
		this.signUpDate = new Date();
	}

	/* (non-Javadoc)
	 * @see isw2.domain.user.IUser#getFirstName()
	 */
	public String getFirstName() {
		return firstName;
	}

	/* (non-Javadoc)
	 * @see isw2.domain.user.IUser#setFirstName(java.lang.String)
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/* (non-Javadoc)
	 * @see isw2.domain.user.IUser#getLastName()
	 */
	public String getLastName() {
		return lastName;
	}

	/* (non-Javadoc)
	 * @see isw2.domain.user.IUser#setLastName(java.lang.String)
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/* (non-Javadoc)
	 * @see isw2.domain.user.IUser#getEmail()
	 */
	public Email getEmail() {
		return email;
	}

	/* (non-Javadoc)
	 * @see isw2.domain.user.IUser#setEmail(isw2.domain.user.Email)
	 */
	public void setEmail(Email email) {
		this.email = email;
	}
	
	/* (non-Javadoc)
	 * @see isw2.domain.user.IUser#getSignUpDate()
	 */
	public Date getSignUpDate() {
		return (Date) signUpDate.clone();
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
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public void setSignUpDate(Date signUpDate) {
		this.signUpDate=signUpDate;
	}
	
	
}
