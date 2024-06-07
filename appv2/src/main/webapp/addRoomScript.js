$(document).ready(function() {
  $('#attendance-form').submit(function(event) {
    event.preventDefault();
    $.ajax({
      type: 'POST',
      url: 'webapi/attendance/addRoom',
      data: $(this).serialize(),
      contentType: 'application/x-www-form-urlencoded',
      dataType: 'json',
      success: function() {
        alert("Room Added");
        // clear the form
        $('#attendance-form')[0].reset();
        console.log("ajax entered");
      },
      error: function(xhr, status, error) {
        alert('Error submitting form: ' + error);
      }
    });
  });
});


