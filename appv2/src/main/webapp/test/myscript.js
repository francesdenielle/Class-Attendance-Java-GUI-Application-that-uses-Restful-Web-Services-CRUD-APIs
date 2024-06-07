$.ajax({
    type: 'POST',
    url: 'webapi/attendance/submit',
    data: $(this).serialize(),
    contentType: 'application/x-www-form-urlencoded',
    dataType: 'json', // specify that the response is in JSON format
    success: function(response) {
        alert('Form submitted successfully. Response: ' + JSON.stringify(response));
    },
    error: function(xhr, status, error) {
        alert('Error submitting form: ' + error);
    }
});
