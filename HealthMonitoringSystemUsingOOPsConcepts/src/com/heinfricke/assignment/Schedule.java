package com.heinfricke.assignment;

import java.util.List;

public interface Schedule {
	
	// Method to book an Appointment with return type void
    void bookAppointment(Appointments appointment);
    
    // Method to retrieve i.e select all Appointments with return type List
    List<Appointments> getAppointments();
    
    // Method to update the status of an Appointment with return type void
    void updateAppointmentStatus(int appointmentId, String status);

}
