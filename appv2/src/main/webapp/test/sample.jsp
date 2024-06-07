<html>
  <head>
    <title>Attendance Web App</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/myscript.js"></script>
  </head>
  <body>
	<form id="attendance-form" action="webapi/attendance/submit" method="POST">
		<label for="name">Name:</label>
		  <input type="text" id="name" name="name" required>
		  <br>
		  <label for="email">Email:</label>
		  <input type="email" id="email" name="email" required>
		  <br>
		  <label for="attendee">Will you attend?</label>
			<input type="radio" id="attendee_yes" name="attendee" value="Yes" required>
			<label for="attendee_yes">Yes</label>
			<input type="radio" id="attendee_no" name="attendee" value="No" required>
			<label for="attendee_no">No</label>
		  <br>
		  <label for="comment">Comment:</label>
		  <textarea id="comment" name="comment"></textarea>
		  <br>
		  <input type="submit" value="Submit">
		</form>
  </body>
</html>
