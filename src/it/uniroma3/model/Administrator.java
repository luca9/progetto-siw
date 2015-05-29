package it.uniroma3.model;

import it.uniroma3.enums.UserGroup;

import javax.persistence.Entity;

/**
 * Created by lorenzovalente on 24/05/15.
 */

@Entity
public class Administrator extends User {

	private final UserGroup userGroup = UserGroup.ADMINISTRATOR;

	public Administrator() {}

	public UserGroup getUserGroup() {
		return userGroup;
	}
}
