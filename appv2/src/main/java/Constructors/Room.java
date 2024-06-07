package Constructors;

import java.util.List;

import OtherConstructors.MyDate;
import OtherConstructors.Time;

public class Room {
	private int id;
	private String name;
	private String department;
	private String course;
	private MyDate date;
	private Time startTime;
	private Time endTime;
	private String sdate;
	private String sstartTime;
	private String sendTime;
	private int capacity;
	private boolean status;
	private List<Student> students;
	
	public Room () {
		
	}

	public Room(int id, String name, String department, String course, String date, String startTime, String endTime, int capacity, boolean status, List<Student> students) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.course = course;
		this.sdate = date;
		this.sstartTime = startTime;
		this.sendTime = endTime;
		this.capacity = capacity;
		this.status = status;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getSstartTime() {
		return sstartTime;
	}

	public void setSstartTime(String sstartTime) {
		this.sstartTime = sstartTime;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public Room(int id, String name, String department, String course, MyDate date, Time startTime, Time endTime, int capacity, boolean status, List<Student> students) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.course = course;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.capacity = capacity;
		this.status = status;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}

	public MyDate getDate() {
		return date;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public int getCapacity() {
		return capacity;
	}

	public boolean getStatus() {
		return status;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setDate(MyDate date) {
		this.date = date;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

}
