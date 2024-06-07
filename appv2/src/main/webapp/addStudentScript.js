$(document).ready(function() {
    var roomId = getUrlParameter("roomId");
    $('#student-form').submit(function(event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'webapi/attendance/addStudent/' + roomId,
            data: $(this).serialize() + '&roomId=' + roomId,
            contentType: 'application/x-www-form-urlencoded',
            dataType: 'json', 
            success: function() {
                alert('Form submitted successfully');
                // clear the form
                $('#student-form')[0].reset();
                console.log("ajax entered");
            },
            error: function(xhr, status, error) {
                alert('Error submitting form: ' + error);
            }
        });
    });
});

function getUrlParameter(name) {
	name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
	var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
	var results = regex.exec(location.search);
	return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
}