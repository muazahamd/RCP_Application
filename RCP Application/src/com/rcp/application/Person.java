package com.rcp.application;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	public static int count = 0;
	
	public static final String FIRST_NAME_PROPERTY = "FIRST_NAME";
	public static final String LAST_NAME_PROPERTY = "LAST_NAME";
	public static final String AGE_PROPERTY = "AGE";
	public static final String GENDER_PROPERTY = "GENER";
	public static final String PROFESSION_PROPERTY = "PROFESSION";
	
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private String gender;
	private String profession;
	
	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	
	public Person(int id, String firstName, String lastName, int age,
			String gender, String profession) {
		super();
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.profession = profession;
		
		count++;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		String oldValue = this.firstName;
		this.firstName = firstName;
		
		listeners.firePropertyChange(FIRST_NAME_PROPERTY, oldValue, this.firstName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		String oldValue = this.lastName;
		this.lastName = lastName;
		
		listeners.firePropertyChange(LAST_NAME_PROPERTY, oldValue, this.lastName);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		int oldValue = this.age;
		this.age = age;
		
		listeners.firePropertyChange(AGE_PROPERTY, oldValue, this.age);
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		String oldValue = this.gender;
		this.gender = gender;
		
		listeners.firePropertyChange(GENDER_PROPERTY, oldValue, this.gender);
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		String oldValue = this.profession;
		this.profession = profession;
		
		listeners.firePropertyChange(PROFESSION_PROPERTY, oldValue, this.profession);
	}

	public int getID() {
		return id;
	}

	public void setID(int ID) {
		this.id = ID;
	}

	@Override
	public String toString() {
		return firstName + "," + lastName + "," + Integer.toString(age) + ","
				+ gender + "," + profession;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
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
		Person other = (Person) obj;
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
