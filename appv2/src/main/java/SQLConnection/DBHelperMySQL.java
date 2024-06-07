package SQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Constructors.*;
import OtherConstructors.MyDate;
import OtherConstructors.Time;

public class DBHelperMySQL {

    public static Connection getConnection() {
    	Connection conn = null;
        try {
            if (conn == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/attendance";
                String username = "root";
                String password = "Katsoo0204";
                conn = DriverManager.getConnection(url, username, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void addRoom(String name, String department, String course, String date, String startTime,
        String endTime, String capacity, String status) throws SQLException {
        String sql = "INSERT INTO Rooms (name, department, course, date, starttime, endtime, capacity, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
            closeConnection(conn);
        }
    }
    
    public void deleteRoomById(int id) throws SQLException {
        String sql = "DELETE FROM Rooms WHERE id = ?";
        String sql2 = "DELETE FROM Students WHERE roomnum = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql2);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
        
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
    
    public void deleteStudentById(int id) throws SQLException {
        String sql = "DELETE FROM Students WHERE id = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
    
    public void editStudent (int id, String firstname, String lastname,String studentnum) throws SQLException {
    	String sql = "UPDATE Students SET firstname=?, lastname=?, studentnum=? WHERE id=?";
    	Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, firstname);
        pstmt.setString(2, lastname);
        pstmt.setString(3, studentnum);
        pstmt.setInt(4, id);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    public List<Room> getAllRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM Rooms";
        Connection conn = getConnection();

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }

        return rooms;
    }

    public Room getRoomById(int id) throws SQLException {
        String sql = "SELECT * FROM Rooms WHERE id = ?";
        Connection conn = getConnection();

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
                int capacity = rs.getInt("capacity");
                boolean status = rs.getBoolean("status");

                MyDate myDate = MyDate.newDate(date);
                Time newStartTime = Time.newTime(startTime);
                Time newEndTime = Time.newTime(endTime);
                List<Student> students = null;

                Room room = new Room(id, name, department, course, myDate, newStartTime, newEndTime, capacity,
                        status, students);
                return room;
            }
        } catch (SQLException e) {
            System.out.println("error getting room");
        } finally {
            pstmt.close();
            closeConnection(conn);
        }

        return null;
    }

    public void addStudent(String studentNumber, String firstName, String lastName, String timeIn,
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
            e.printStackTrace();
        } finally {
            pstmt.close();
            closeConnection(conn);
        }
    }
    
    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT * FROM Students WHERE id = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        Student student = null;
        if (rs.next()) {
            boolean newStatus = rs.getBoolean("status");
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