package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Constructs an object to hold and manipulate information for users
 * @author Nathan Cornelison
 */
public abstract class User {

	/** Student's first name */
	private String firstName;
	/** Student's last name */
	private String lastName;
	/** Student's email */
	private String email;
	/** Student's password */
	private String password;
	/** Student's id */
	private String id;

	/**
	 * Constructs a user with all the fields
	 * @param firstName First name of the User
	 * @param lastName Last name of the User
	 * @param id Id of the User
	 * @param email Email of the User
	 * @param password Password of the User
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		super();
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	/**
	 * Gets the student's first name
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the student's first name
	 * @param firstName Student's first name
	 * @throws IllegalArgumentException exception if firstName is null or blank
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.length() <= 0) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Gets the student's last name
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the student's last name
	 * @param lastName Student's last name
	 * @throws IllegalArgumentException if lastName is null or blank
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.length() <= 0) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Gets the student's id
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the student's id
	 * @param id Student's id
	 * @throws IllegalArgumentException if id is null or blank
	 */
	protected void setId(String id) {
		if (id == null || id.length() <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returns the student's email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the student's email.
	 * @param email Student's email
	 * @throws IllegalArgumentException if email is null or a blank string, doesn't contain an '@' or an '.',
	 *  or if there isn't a '.' after the '@'
	 */
	public void setEmail(String email) {
		if (email == null || email.length() <= 0) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!email.contains("@") || !email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (email.lastIndexOf('.') < email.lastIndexOf('@')) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Gets the student's password
	 * @return password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the student's password
	 * @param password Student's password
	 * @throws IllegalArgumentException if password is null or blank
	 */
	public void setPassword(String password) {
		if (password == null || password.length() <= 0) {
			throw new IllegalArgumentException("Invalid password");
		}
		
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}