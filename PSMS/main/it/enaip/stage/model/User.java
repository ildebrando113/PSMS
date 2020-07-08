package it.enaip.stage.model;

import java.sql.Timestamp;
import java.util.Date;

public class User {
	public enum Status {
		C,O,S
		//C=child,O=owner,S=spouse
	}

	protected int id;
	protected String name;
	protected String surname;
	protected Date birthdate;
	protected Timestamp creationtime;
	protected int age;
	protected Status type;

	public User(int id, String name, String surname, Date birthdate, Timestamp creationtime, int age, Status type) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.creationtime = creationtime;
		this.age = age;
		this.type = type;
	}
	public User(int id) {
		this.id=id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Timestamp getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(Timestamp creationtime) {
		this.creationtime = creationtime;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Status getStatus() {
		return type;
	}

	public void setStatus(Status type) {
		this.type = type;
	}

}
