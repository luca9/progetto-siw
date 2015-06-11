package it.uniroma3.model;

import it.uniroma3.enums.UserGroup;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by lorenzovalente on 24/05/15.
 */

@Entity
public class Administrator extends User {

	public Administrator() {}

	public Administrator (String username, String password, String firstName, String lastName, String email,
						  String phoneNumber, Date dateOfBirth, Address address) {
		super (username, password, firstName, lastName, email, phoneNumber, dateOfBirth, address);
		setUserGroup(UserGroup.ADMINISTRATOR);
	}

}
