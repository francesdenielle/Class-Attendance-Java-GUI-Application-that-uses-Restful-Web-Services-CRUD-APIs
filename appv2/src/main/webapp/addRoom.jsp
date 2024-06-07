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
    <script src="${pageContext.request.contextPath}/addRoomScript.js"></script>
</head>

<body>

    <div class="container">
        <nav>
            <ul>
                <li><a href="index.html" class="logo">
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
            </div>
            <section class="attendance-2">
                <div class="container2">
                    <div class="title">Add Room</div>
                    <div class="content">
                        <form id="attendance-form" method="POST">
                            <div class="user-details">
                                <div class="input-box">
                                    <span class="details">Room Number</span>
                                    <input type="text" id="name" name="name" placeholder="Room ***" required>
                                </div>
                                <div class="input-box">
                                    <span class="details">Department</span>
                                    <input type="text" id="department" name="department" placeholder="Enter Department" required>
                                </div>
                                <div class="input-box">
                                    <span class="details">Course</span>
                                    <input type="text" id="course" name="course" placeholder="Enter Course" required>
                                </div>
                                <div class="input-box">
                                    <span class="details">Date</span>
                                    <input type="date" name="date" id="date">
                                </div>
                                <div class="input-box">
                                    <span class="details">Start Time</span>
                                    <input type="time" name="starttime" id="starttime">
                                </div>
                                <div class="input-box">
                                    <span class="details">End Time</span>
                                    <input type="time" name="endtime" id="endtime">
                                </div>
                            </div>
                            <div class="button">
                                <input id="addRoom" type="submit" value="Submit">
                            </div>
                        </form>
                    </div>
                </div>
                <a href="index.jsp">
                    <button>Back</button>
                </a>
            </section>
        </section>
    </div>
</body>

</html>