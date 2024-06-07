function generateRoomCards(rooms) {
	var container = $('#room-cards-container');
	var table = document.getElementById("attendance-table").getElementsByTagName('tbody')[0];

	for (var i = 0; i < rooms.length; i++) {
		var room = rooms[i];
		console.log(room);

		//Gawa ng card
		var card = $('<div>', { class: 'card' });

		//image
		var image = $('<img>', { src: 'soit.jpeg' });
		card.append(image);

		//room and code
		var title = $('<h4>', { text: room.name });
		var code = $('<p>', { text: room.id });
		card.append(title);
		card.append(code);

		//other echos
		var statusTable = $('<table>');
		var maxSeatsRow = $('<tr>');
		var maxSeatsLabel = $('<td>', { text: 'Maximum Seats' });
		var maxSeatsValue = $('<td>').append($('<span>', { text: room.capacity }).addClass('per'));
		maxSeatsRow.append(maxSeatsLabel);
		maxSeatsRow.append(maxSeatsValue);

		var statusRow = $('<tr>');
		var statusLabel = $('<td>', { text: 'Status' });
		var statusValue = $('<td>').append($('<span>', { text: room.status ? 'Open' : 'Closed' }).addClass('per'));
		statusRow.append(statusLabel);
		statusRow.append(statusValue);

		statusTable.append(maxSeatsRow);
		statusTable.append(statusRow);
		card.append(statusTable);

		var newRow = table.insertRow();

		var idCell = newRow.insertCell(0);
		var nameCell = newRow.insertCell(1);
		var departmentCell = newRow.insertCell(2);
		var dateCell = newRow.insertCell(3);
		var startTimeCell = newRow.insertCell(4);
		var endTimeCell = newRow.insertCell(5);
		var detailsCell = newRow.insertCell(6);
		var deleteCell = newRow.insertCell(7);

		// pancit ng values
		idCell.innerHTML = room.id;
		nameCell.innerHTML = room.name;
		departmentCell.innerHTML = room.department;
		dateCell.innerHTML = room.sdate;
		startTimeCell.innerHTML = room.sstartTime;
		endTimeCell.innerHTML = room.sendTime;
		var roomId = room.id;
		detailsCell.innerHTML = '<a href="newRoom.jsp?roomId=' + roomId + '"><button>View</button></a>';
		deleteCell.innerHTML = '<td><button class="deleteRoomButt">Delete Room</button></td>';

		//Iadd sa table at card container
		container.append(card);
	}

	table.addEventListener('click', function(event) {
		var target = event.target;
		if (target.classList.contains('deleteRoomButt')) {
			console.log('Delete button clicked');
			var row = target.parentNode.parentNode;
			var roomId = row.cells[0].innerHTML;
			console.log('Room id:', roomId);
			$.ajax({
				url: 'webapi/attendance/deleteRoom/'+ roomId,
				type: 'DELETE',
				dataType: 'json',
				success: function(data) {
					console.log("Response received:", data);
				},
				error: function(xhr, textStatus, errorThrown) {
					console.log("Error:", errorThrown);
				}
			});
		}
	});
}

$(document).ready(function() {
	console.log('Document ready');
	$.ajax({
		url: 'webapi/attendance/rooms',
		type: 'GET',
		dataType: 'json',
		success: function(data) {
			console.log("Response received:", data);
			generateRoomCards(data);
		},
		error: function(xhr, textStatus, errorThrown) {
			console.log("Error:", errorThrown);
		}
	});
});




