package SQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Constructors.Room;
import Constructors.Student;
import OtherConstructors.MyDate;
import OtherConstructors.Time;

public class DBHelper {

    public static Connection getConnection() {
    	Connection conn = null;
        try {
            if (conn == null) {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\katri\\OneDrive - Mapúa University\\Documents\\NoteApp\\appv2\\database\\newAttendance7.db");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

	public static void addRoom(String name, String department, String course, String date, String startTime,
			String endTime, String capacity, String status) throws SQLException {
		String sql = "INSERT INTO Rooms (name, department, course, date, starttime, endtime, capacity, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = getConnection();

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, department);
			pstmt.setString(3, course);
			pstmt.setString(4, date);
			pstmt.setString(5, startTime);
			pstmt.setString(6, endTime);
			pstmt.setString(7, capacity);
			pstmt.setString(8, status);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			closeConnection(conn);
		}
	}

	public List<Room> getAllRooms() throws SQLException {
		List<Room> rooms = new ArrayList<>();
		String sql = "SELECT * FROM Rooms";
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String department = rs.getString("department");
			String course = rs.getString("course");
			String date = rs.getString("date");
			String startTime = rs.getString("starttime");
			String endTime = rs.getString("endtime");
			String capacity = rs.getString("capacity");
			String status = rs.getString("status");

			int capacityInt = Integer.parseInt(capacity);
			MyDate myDate = MyDate.newDate(date);
			Time newStartTime = Time.newTime(startTime);
			Time newEndTime = Time.newTime(endTime);
			boolean newStatus;
			List<Student> students = null;

			if (status.equals("true"))
				newStatus = true;
			else
				newStatus = false;

			Room room = new Room(id, name, department, course, myDate, newStartTime, newEndTime, capacityInt,
					newStatus, students);
			rooms.add(room);
		}
		rs.close();
		stmt.close();
		closeConnection(conn);

		return rooms;
	}

	public Room getRoomById(int id) throws SQLException {
		String sql = "SELECT * FROM Rooms WHERE id = ?";
		Connection conn = getConnection();
		
		String sid = Integer.toString(id);
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("getting room...");

			if (rs.next()) {
				String name = rs.getString("name");
				String department = rs.getString("department");
				String course = rs.getString("course");
				String date = rs.getString("date");
				String startTime = rs.getString("starttime");
				String endTime = rs.getString("endtime");
				String capacity = rs.getString("capacity");
				String status = rs.getString("status");

				int capacityInt = Integer.parseInt(capacity);
				MyDate myDate = MyDate.newDate(date);
				Time newStartTime = Time.newTime(startTime);
				Time newEndTime = Time.newTime(endTime);
				boolean newStatus;
				List<Student> students = null;

				if (status.equals("true"))
					newStatus = true;
				else
					newStatus = false;

				Room room = new Room(id, name, department, course, myDate, newStartTime, newEndTime, capacityInt,
						newStatus, students);
				return room;
			}
		} catch (SQLException e) {
			System.out.println("error getting room");
		}
		pstmt.close();
		closeConnection(conn);

		return null;
	}

	// STUDEEEEEEEEEEENT
	public static void addStudent(String studentNumber, String firstName, String lastName, String timeIn,
		String timeOut, String status, String referenceId) throws SQLException {
		String sql = "INSERT INTO Students (studentnum, firstname, lastname, timein, timeout, status, roomnum) VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection conn = getConnection();

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentNumber);
			pstmt.setString(2, firstName);
			pstmt.setString(3, lastName);
			pstmt.setString(4, timeIn);
			pstmt.setString(5, timeOut);
			pstmt.setString(6, status);
			pstmt.setString(7, referenceId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pstmt.close();
		closeConnection(conn);
	}

	public List<Student> getAllStudents(int roomnum) throws SQLException {
		List<Student> students = new ArrayList<>();
		String sql = "SELECT * FROM Students";
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			int room;
			String roomId = rs.getString("roomnum");
			room = Integer.parseInt(roomId);
			if (room == roomnum) {
				int id = rs.getInt("id");
				String studentNumber = rs.getString("studentnum");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String timeIn = rs.getString("timein");
				String timeOut = rs.getString("timeout");
				String status = rs.getString("status");

				Time newTimeIn = Time.newTime(timeIn);
				Time newTimeOut = Time.newTime(timeOut);
				boolean newStatus;

				if (status.equals("true"))
					newStatus = true;
				else
					newStatus = false;

				Student student = new Student(id, studentNumber, firstName, lastName, newTimeIn, newTimeOut, newStatus,
						room);
				students.add(student);
			}
		}
		rs.close();
		stmt.close();
		closeConnection(conn);

		return students;
	}
	
	public void deleteAllStudents() throws SQLException {
	    String sql = "DELETE FROM Students";
	    Connection conn = getConnection();
	    Statement stmt = conn.createStatement();
	    stmt.executeUpdate(sql);
	    stmt.close();
	    closeConnection(conn);
	}

	public Student getStudentById(int id) throws SQLException {
	    String sql = "SELECT * FROM Students WHERE id = ?";
	    Connection conn = getConnection();
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, id);
	    ResultSet rs = pstmt.executeQuery();

	    Student student = null;
	    if (rs.next()) {
	        String status = rs.getString("status");
	        boolean newStatus;
	        if (status.equals("true"))
	            newStatus = true;
	        else
	            newStatus = false;

	        student = new Student(
	                rs.getInt("id"), 
	                rs.getString("studentnum"),
	                rs.getString("firstname"), 
	                rs.getString("lastname"), 
	                rs.getString("timein"), 
	                rs.getString("timeout"), 
	                newStatus
	        );
	    }

	    rs.close();
	    pstmt.close();
	    closeConnection(conn);

	    return student; 
	}
	
	public void setStudentStatus(String status, int id) throws SQLException {
	    String sql = "UPDATE Students SET status = ? WHERE id = ?";
	    Connection conn = getConnection();
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, status);
	    pstmt.setInt(2, id);
	    pstmt.executeUpdate();
	    pstmt.close();
	    closeConnection(conn);
	}
	
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
