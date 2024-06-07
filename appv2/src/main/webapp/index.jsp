<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<title>Attendance System</title>
<link rel="stylesheet" href="style.css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/script.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
	<div class="container">
		<nav>
			<ul>
				<li><a href="#" class="logo"> <img src="mapua.png"> <span
						class="nav-item"> Mapúa <br> University
					</span>
				</a></li>
				<li><a href="index.html"> <i class="fas fa-chart-bar"></i>
						<span class="nav-item">Home</span>
				</a></li>
			</ul>
		</nav>


		<section class="main">
			<div class="main-top">
				<h1>Good Morning</h1>
				<i class="fas fa-user-cog"></i>
			</div>
			<a href="addRoom.jsp"><button id="addRoomButton">Add
					Room</button></a>
			<div id="room-cards-container" class="users"></div>

			<section class="attendance">
				<div class="attendance-list">
					<h1>Room List</h1>
					<table id="attendance-table" class="table">
						<thead>
							<tr>
								<th>ID</th>
								<th>Name</th>
								<th>Department</th>
								<th>Date</th>
								<th>Time Start</th>
								<th>Time End</th>
								<th>Details</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
				</div>
			</section>
		</section>
	</div>

</body>

</html>