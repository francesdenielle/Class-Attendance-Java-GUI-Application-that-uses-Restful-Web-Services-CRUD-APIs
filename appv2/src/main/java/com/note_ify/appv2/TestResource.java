package com.note_ify.appv2;

import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Path("attendancetest")
public class TestResource {
    @POST
    @Path("submittest")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitAttendance(MultivaluedMap<String, String> formParams) {
        System.out.println("Received form data: " + formParams);

        AttendanceData data = new AttendanceData(
            formParams.getFirst("name"),
            formParams.getFirst("email"),
            formParams.getFirst("attendee"),
            formParams.getFirst("comment")
        );

        System.out.println("Deserialized data: " + data);

        return Response.ok(data).build();
    }
}



class AttendanceData {

    private String name;
    private String email;
    private String attendee;
    private String comment;

    public AttendanceData() {}

    public AttendanceData(String name, String email, String attendee, String comment) {
        this.name = name;
        this.email = email;
        this.attendee = attendee;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAttendee() {
        return attendee;
    }
    public void setAttendee(String attendee) {
        this.attendee = attendee;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}

