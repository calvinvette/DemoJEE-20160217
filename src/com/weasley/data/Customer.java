package com.weasley.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.Email;

@Entity
@XmlRootElement
@XmlType(propOrder={"customerId", "firstName", "lastName", "phoneNumber", "email"}) // JAXB
/*
<customer>
	<customerId>7</customerId>
	<firstName>Ginny</firstName>
	<lastName>Weasley</lastName>
	<phoneNumber>+44 0206 555-1212</phoneNumber>
	<email>ginny.weasley@hogwarts.ac.uk</email>
</customer>

Mark customerId as @XmlAttribute to get it as an attribute instead of an element 
<customer customerId="7">
 	<firstName>Ginny</firstName>
	<lastName>Weasley</lastName>
	<phoneNumber>+44 0206 555-1212</phoneNumber>
	<email>ginny.weasley@hogwarts.ac.uk</email>
</customer>
*/
@NamedQueries({
  @NamedQuery(name=Customer.FIND_BY_LAST_NAME, query="select c from Customer c where c.lastName = :lastName"),
  @NamedQuery(name=Customer.FIND_ALL, query="select c from Customer c")
})
public class Customer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String FIND_BY_LAST_NAME = "Customer.FIND_BY_LAST_NAME";
	public static final String FIND_ALL = "Customer.FIND_ALL";
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Min(0)
//	@XmlAttribute
	private Long customerId = -1L;
	@Size(min=2, max=25)
	private String firstName;
	@Size(min=2, max=25)
	private String lastName;
	
	@Size(min=10, max=20)
	//This is a poor pattern for global phoneNumbers
	//@Pattern(regexp="\\d{3}-\\d{3}-\\d{4}")
	private String phoneNumber;
	
	@Email
	private String email;
	
	public Customer() {

	}

	public Customer(String firstName, String lastName, String phoneNumber, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public Customer(Long customerId, String firstName, String lastName, String phoneNumber, String email) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
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
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
	
	

}
