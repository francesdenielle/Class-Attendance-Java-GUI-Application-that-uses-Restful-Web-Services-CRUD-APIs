<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <title>Attendance System</title>
    <link rel="stylesheet" href="style.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/newRoomScript.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

    <div class="container">
        <nav>
            <ul>
                <li><a href="index.jsp" class="logo">
                        <img src="mapua.png">
                        <span class="nav-item"> Mapúa <br> University </span>
                    </a></li>
                <li><a href="index.jsp">
                        <i class="fas fa-chart-bar"></i>
                        <span class="nav-item">Home</span>
                    </a></li>
            </ul>
        </nav>
        <section class="main">
            <div class="main-top">
                <h1 id="roomname">Room 319</h1>
                <i><a id="addStudentButt" href="#">
                        <i class="fas fa-user-cog"></i>
                    </a></i>
            </div>
            <section class="attendance-2">
                <div class="attendance-list-2">
                    <label for="Section">Section</label>
                    <select name="sections" id="sections">
                        <option value="AM1">AM1</option>
                        <option value="AM2">AM2</option>
                        <option value="AM3">AM3</option>
                        <option value="AM4">AM4</option>
                    </select>

                    <input type="date" id="date">

                    <h1>Students</h1>
                    <table class="table" id="student-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Student Number</th>
                                <th>Date</th>
                                <th>Login Time</th>
                                <th>Logout Time</th>
                                <th>Status</th>
                                <th> </th>
                                <th> </th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>01</td>
                                <td>PlaceHolder</td>
                                <td>20211012001</td>
                                <td>03-24-22</td>
                                <td>8:00AM</td>
                                <td>3:00PM</td>
                                <td><button id='present'>Present</button></td>
                                <td><button id='absent'>Absent</button></td>
                                <td><button id='delete'>Delete</button></td>
                                <td><button id='edit'>Edit</button></td>
                            </tr>
                        </tbody>
                    </table>
                    <a href="index.jsp">
                        <button class="button2">Back</button>
                    </a>
                </div>
            </section>
        </section>
    </div>
</body>

</html>