package Constructors;

import OtherConstructors.Time;

public class Student {
	private int id;
	private String studentNumber;
	private String firstName;
	private String lastName;
	private Time timeIn;
	private Time timeOut;
	private boolean status;
	private int referenceId;
	private String stimeIn;
	private String stimeOut;
	
	public Student (int id, String studentNumber, String firstName, String lastName, String stimeIn, String stimeOut, boolean status) {
		this.id = id;
		this.studentNumber = studentNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.stimeIn = stimeIn;
		this.stimeOut = stimeOut;
		this.status = status;
	}
	
	public Student(int id, String studentNumber, String firstName, String lastName, Time timeIn, Time timeOut, boolean status, int referenceId) {
		this.id = id;
		this.studentNumber = studentNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.timeIn = timeIn;
		this.timeOut = timeOut;
		this.status = status;
		this.referenceId = referenceId;
	}

	public int getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
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

	public Time getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(Time timeIn) {
		this.timeIn = timeIn;
	}

	public Time getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Time timeOut) {
		this.timeOut = timeOut;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getStimeIn() {
		return stimeIn;
	}

	public void setStimeIn(String stimeIn) {
		this.stimeIn = stimeIn;
	}

	public String getStimeOut() {
		return stimeOut;
	}

	public void setStimeOut(String stimeOut) {
		this.stimeOut = stimeOut;
	}

}
