package com.note_ify.appv2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import Constructors.Room;
import Constructors.Student;
import SQLConnection.DBHelperMySQL;

@Path("attendance")
public class AttendanceResource {

	// ito yung tinatapon sa ajax para makapagappend ng room card yung javascript
	@GET
	@Path("rooms")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Room> getRooms() {
		List<Room> rooms = new ArrayList<>();
		List<Room> displayRooms = new ArrayList<>();
		DBHelperMySQL dbhelper = new DBHelperMySQL();
		System.out.println("entered rooms");

		try {
			rooms = dbhelper.getAllRooms();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		displayRooms = toDisplay(rooms);
		return displayRooms;
	}

	//pansalo sa tinatapon ng ajax para sa addRoom
	@POST
	@Path("addRoom")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void addRoom(MultivaluedMap<String, String> formParams) {
		// para lng makita ko sa console ano narereceive
		DBHelperMySQL dbhelper = new DBHelperMySQL();
		System.out.println("Form data na tinapon ng ajax: " + formParams);

		try {
			dbhelper.addRoom(formParams.getFirst("name"), formParams.getFirst("department"),
					formParams.getFirst("course"), formParams.getFirst("date"), formParams.getFirst("starttime"),
					formParams.getFirst("endtime"), "40", "true");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Response.ok(room).build();
	}

	@POST
	@Path("addStudent/{roomId}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStudent(@PathParam("roomId") int roomId, MultivaluedMap<String, String> formParams) {
		// para lng makita ko sa console ano narereceive
		DBHelperMySQL dbhelper = new DBHelperMySQL();
		System.out.println("Form data na tinapon ng ajax: " + formParams);
		String ref = Integer.toString(roomId);
		try {
			dbhelper.addStudent(formParams.getFirst("studentnumber"), formParams.getFirst("firstname"),
					formParams.getFirst("lastname"), "8:00", "3:00", "true", ref);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Response.ok(room).build();
		return Response.ok("Student Added").build();
	}

	@GET
	@Path("rooms/{roomId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Room getRoom(@PathParam("roomId") int roomId) {
		//check
		System.out.println("entered rooms/roomId: " + roomId);
		DBHelperMySQL roomDB = new DBHelperMySQL();
		Room room = null;
		try {
			room = roomDB.getRoomById(roomId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Room newRoom = toString(room);

		return newRoom;
	}

	@PUT
	@Path("status/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void setStatus(@PathParam("id") int id, MultivaluedMap<String, String> formParams) {
	    // para lng makita ko sa console ano narereceive
	    System.out.println("Form data na tinapon ng ajax: " + formParams + " " + id);
	    String status = formParams.getFirst("status");    

	    DBHelperMySQL dbhelper = new DBHelperMySQL();
	    try {
	        Student student = dbhelper.getStudentById(id);
	        if (status.equals("present")) {
	            student.setStatus(true);
	            dbhelper.setStudentStatus("true", id);
	        } else {
	            student.setStatus(false);
	            dbhelper.setStudentStatus("false", id);
	        }

	        System.out.print(student.isStatus());

	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
	@DELETE
	@Path("deleteStudent/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteStudent(@PathParam("id") int id) throws SQLException {
		//check
		DBHelperMySQL dbhelper = new DBHelperMySQL();
		dbhelper.deleteStudentById(id);
	}
	
	@DELETE
	@Path("deleteRoom/{id}")
	public void deleteRoom(@PathParam("id") int id) throws SQLException {
		//check
		DBHelperMySQL dbhelper = new DBHelperMySQL();
		dbhelper.deleteRoomById(id);	
	}
	
	@PUT
	@Path("editStudent/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void editStudent(@PathParam("id") int id, MultivaluedMap<String, String> formParams) throws SQLException {
		//check
		System.out.println("Form data na tinapon ng ajax: " + formParams + " " + id);
		DBHelperMySQL dbhelper = new DBHelperMySQL();
		String firstname = (formParams.getFirst("firstname"));
		String lastname = (formParams.getFirst("lastname"));
		String studentnum = (formParams.getFirst("studentnumber"));
		dbhelper.editStudent(id, firstname, lastname, studentnum);
	}
	
	
	
	
	//KADIRING PART DO NOT LOOK
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static List<Room> toDisplay(List<Room> rooms) {
		List<Room> displayRooms = new ArrayList<>();
		List<Student> students = new ArrayList<>();
		Room newRoom;
		for (int i = 0; i < rooms.size(); i++) {
			String month;
			String day;
			String year;
			String startHour;
			String endHour;
			String startMinute;
			String endMinute;
			String startMeridiem;
			String endMeridiem;
			String startTime;
			String endTime;
			String date;

			Room room = rooms.get(i);

			int id = room.getId();
			int capacity = room.getCapacity();
			boolean status = room.getStatus();
			String name = room.getName();
			String department = room.getDepartment();
			String course = room.getCourse();

			month = Integer.toString(room.getDate().getMonth());
			day = Integer.toString(room.getDate().getDay());
			year = Integer.toString(room.getDate().getYear());
			date = month + "-" + day + "-" + year;

			startHour = Integer.toString(room.getStartTime().getHour());
			startMinute = Integer.toString(room.getStartTime().getMinute());
			startMeridiem = room.getStartTime().getMeridiem();
			startTime = startHour + ":" + startMinute + " " + startMeridiem;

			endHour = Integer.toString(room.getEndTime().getHour());
			endMinute = Integer.toString(room.getEndTime().getMinute());
			endMeridiem = room.getEndTime().getMeridiem();
			endTime = endHour + ":" + endMinute + " " + endMeridiem;

			students = null;
			newRoom = new Room(id, name, department, course, date, startTime, endTime, capacity, status, students);

			displayRooms.add(newRoom);
		}

		return displayRooms;

	}

	public static Room toString(Room room) {
		DBHelperMySQL roomDB = new DBHelperMySQL();
		List<Student> students = new ArrayList<>();
		List<Student> newStudents = new ArrayList<>();
		int roomId = room.getId();

		try {
			students = roomDB.getAllStudents(roomId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int id = room.getId();
		int capacity = room.getCapacity();
		boolean status = room.getStatus();
		String name = room.getName();
		String department = room.getDepartment();
		String course = room.getCourse();

		String month = Integer.toString(room.getDate().getMonth());
		String day = Integer.toString(room.getDate().getDay());
		String year = Integer.toString(room.getDate().getYear());
		String date = month + "-" + day + "-" + year;

		String startHour = Integer.toString(room.getStartTime().getHour());
		String startMinute = Integer.toString(room.getStartTime().getMinute());
		String startMeridiem = room.getStartTime().getMeridiem();
		String startTime = addZero(startHour) + ":" + addZero(startMinute) + " " + startMeridiem;

		String endHour = Integer.toString(room.getEndTime().getHour());
		String endMinute = Integer.toString(room.getEndTime().getMinute());
		String endMeridiem = room.getEndTime().getMeridiem();
		String endTime = addZero(endHour) + ":" + addZero(endMinute) + " " + endMeridiem;

		if (students != null) {
			for (int j = 0; j < students.size(); j++) {
				int sid = students.get(j).getId();
				boolean studentstatus = students.get(j).isStatus();
				String firstname = students.get(j).getFirstName();
				String lastname = students.get(j).getLastName();
				String studentnum = students.get(j).getStudentNumber();

				String inHour = Integer.toString(students.get(j).getTimeIn().getHour());
				String inMin = Integer.toString(students.get(j).getTimeIn().getMinute());
				String inMer = students.get(j).getTimeIn().getMeridiem();
				String timeIn = addZero(inHour) + ":" + addZero(inMin) + " " + inMer;

				String outHour = Integer.toString(students.get(j).getTimeOut().getHour());
				String outMin = Integer.toString(students.get(j).getTimeOut().getMinute());
				String outMer = students.get(j).getTimeOut().getMeridiem();
				String timeOut = addZero(outHour) + ":" + addZero(outMin) + " " + outMer;

				Student newStudent = new Student(sid, studentnum, firstname, lastname, timeIn, timeOut, studentstatus);
				newStudents.add(newStudent);
				System.out.println(newStudents.get(j).getFirstName());
			}
		}

		Room newRoom = new Room(id, name, department, course, date, startTime, endTime, capacity, status, newStudents);
		newRoom.setStudents(newStudents);
		return newRoom;
	}
	
	public static String addZero(String time) {
		if (time.equals("0")) 
			time = "00";
		return time;
	}
}

