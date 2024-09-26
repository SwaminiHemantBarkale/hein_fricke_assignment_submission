package com.heinfricke.assignment;

public class Appointments {
	
	// Defining variables
	private int id;
	private int pId;
	private int dId;
	private String appointmentDateSelected;
	private String statusOfAppointment;
	
	// Parameterized Constructor
	public Appointments(int id, int pId, int dId
			           , String appointmentDateSelected
			           , String appointmentTimeSelected
			           , String statusOfAppointment) {
		this.id=id;
		this.pId=pId;
		this.dId=dId;
		this.appointmentDateSelected=appointmentDateSelected;
		this.statusOfAppointment=statusOfAppointment;
	}
	
	// Method to retrieve Appointment Id with return type int
	public int getAppointmentId() {
		return id;
	}
	
	// Method to set Appointment Status with return type void
	public void setAppointmentStatus(String s) {
		this.statusOfAppointment=s;
	}
	
	// Method to retrieve Appointment status with return type String
	public String getAppointmentStatus() {
		return statusOfAppointment;
	}
	
	// Method to retrieve Doctor's Id with return type int
	public int getDoctorId() {
	    return dId;
	}
	
	// Method to retrieve Appointment Date with return Type String
	public String getAppointmentDate() {
	    return appointmentDateSelected;
	}
	
	// Printing Output
	public String toString() {
		return "Appointment Id: "+id+", Patient's Id: "+pId+", Doctor's Id: "+dId+
				", Appointment Date: "+appointmentDateSelected+", Status Of Appointment: "+statusOfAppointment;
	}

}
