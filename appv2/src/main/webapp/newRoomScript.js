var table = document.getElementById("student-table");
$(document).ready(function() {
	console.log('Document ready');
	var roomId = getUrlParameter("roomId"); //kuha id
	$.ajax({
		url: 'webapi/attendance/rooms/' + roomId,
		type: 'GET',
		dataType: 'json',
		success: function(data) {
			console.log("Response received:", data);
			var addStudentButton = document.getElementById("addStudentButt");
			addStudentButton.href = "addStudent.jsp?roomId=" + roomId;
			table = document.getElementById("student-table");
			populatePage(data);
		},
		error: function(xhr, textStatus, errorThrown) {
			console.log("Error:", errorThrown);
		}
	});
});

//get the # from the url gague
function getUrlParameter(name) {
	name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
	var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
	var results = regex.exec(location.search);
	return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
}



function populatePage(room) {
	var roomname = document.getElementById("roomname");

	roomname.innerHTML = room.name;

	const students = room.students;
	for (let i = 0; i < students.length; i++) {
		const student = students[i];
		const newRow = table.insertRow();

		// put in row pls
		var idCell = newRow.insertCell(0);
		var nameCell = newRow.insertCell(1);
		var studentnumberCell = newRow.insertCell(2);
		var dateCell = newRow.insertCell(3);
		var timeInCell = newRow.insertCell(4);
		var timeOutCell = newRow.insertCell(5);
		var presentCell = newRow.insertCell(6);
		var absentCell = newRow.insertCell(7);
		var deleteCell = newRow.insertCell(8);
		var editCell = newRow.insertCell(9);

		idCell.innerHTML = student.id;
		nameCell.innerHTML = student.firstName + " " + student.lastName;
		studentnumberCell.innerHTML = student.studentNumber;
		dateCell.innerHTML = room.sdate;
		timeInCell.innerHTML = student.stimeIn;
		timeOutCell.innerHTML = student.stimeOut;
		presentCell.innerHTML = "<td><button class='present' id='present'>Present</button></td>";
		absentCell.innerHTML = "<td><button class='absent' id='absent'>Absent</button></td>";
		deleteCell.innerHTML = "<td><button class='delete' id='delete'>Delete</button></td>";
		editCell.innerHTML = '<a href="editStudent.jsp?studentId=' + student.id + '"><button>Edit</button></a>';
	}
}

document.addEventListener('DOMContentLoaded', function() {
	var table = document.getElementById("student-table");
	var status = 'status';
	table.addEventListener('click', function(event) {
		if (event.target.classList.contains('present')) {
			console.log('Present button clicked');
			status = 'present';
			var id = event.target.closest('tr').querySelector('td:first-child').innerHTML;
			console.log('ID:', id);
			$.ajax({
				url: 'webapi/attendance/status/' + id.trim(),
				type: 'PUT',
				data: { status: status },
				dataType: 'json',
				success: function(data) {
					console.log("Response received:", data);
				},
				error: function(xhr, textStatus, errorThrown) {
					console.log("Error:", errorThrown);
				}
			});

		} else if (event.target.classList.contains('absent')) {
			console.log('Absent button clicked');
			status = 'absent';
			var id = event.target.closest('tr').querySelector('td:first-child').innerHTML;
			console.log('ID:', id);
			$.ajax({
				url: 'webapi/attendance/status/' + id.trim(),
				type: 'PUT',
				data: { status: status },
				dataType: 'json',
				success: function(data) {
					console.log("Response received:", data);
				},
				error: function(xhr, textStatus, errorThrown) {
					console.log("Error:", errorThrown);
				}
			});
		} else if (event.target.classList.contains('delete')) {
			console.log('Absent button clicked');
			status = 'absent';
			var id = event.target.closest('tr').querySelector('td:first-child').innerHTML;
			console.log('delete ID:', id);
			$.ajax({
				url: 'webapi/attendance/deleteStudent/' + id.trim(),
				type: 'DELETE',
				dataType: 'json',
				success: function(data) {
					alert("Student Deleted");
				},
				error: function(xhr, textStatus, errorThrown) {
					console.log("Error:", errorThrown);
				}
			});
		} 
	});
});