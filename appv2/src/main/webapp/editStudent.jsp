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
    <script src="${pageContext.request.contextPath}/editStudentScript.js"></script>
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
            </div>
            <section class="attendance-2">
                <div class="container2">
                    <div class="title">Edit Student</div>
                    <div class="content">
						<form id="student-form" method="POST">
							<div class="user-details">
								<div class="input-box">
									<span class="details">First Name</span> <input type="text"
										name="firstname" placeholder="Enter your First Name" required>
								</div>
								<div class="input-box">
									<span class="details">Last Name</span> <input type="text"
										name="lastname" placeholder="Enter your Last Name" required>
								</div>
								<div class="input-box">
									<span class="details">Student Number</span> <input type="text"
										name="studentnumber" placeholder="Enter your Student Number" required>
								</div>
							</div>
							<div class="button">
								<input id="addStudent" type="submit" value="Submit">
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