package com.heinfricke.assignment;

import java.util.ArrayList;
import java.util.List;

public class Doctors implements Schedule {
	
	// Defining variables
	protected int id;
	protected String name;
	protected String emergencyContactNumber;
	protected String specialization;
	protected int initialFees;
	protected List<Appointments> appointments;
		
	// Parameterized Constructor
	public Doctors(int id, String name, String emergencyContactNumber,
				       String specialization, int initialFees) {
		this.id=id;
		this.name=name;
		this.emergencyContactNumber=emergencyContactNumber;
		this.specialization= specialization;
		this.appointments= new ArrayList<>();
	}
		
	// Method to add(insert) appointments with return type void
	public void addAppointments(Appointments a) {
		appointments.add(a);
	}
		
	// Method to retrieve list of appointments with return type List<Appointments>
	public List<Appointments> getAppointments(){
		return appointments;
	}
	   
	// Method to update appointment status with return type void
	public void updateStatusOfAppointments(int aId, String aStatus) {
		for(Appointments a: appointments) {
			 if(a.getAppointmentId() == aId) {
				 a.setAppointmentStatus(aStatus);
				 break;
			}
		}
	}
	
	// Method to get Doctor's Id
	public int getDoctorId() {
		return id;
	}
	
	// Method to get Doctor's Name
	public String getName() {
		return name;
	}
	
	// Method to get Specialization
	public String getSpecialization() {
		return specialization;
	}
	
	// Printing output
	public String toString() {
		return "Doctor Id: "+id+", Doctor's Name: "+name+", Doctor's Emergency Contact Number: "+emergencyContactNumber+
				", Specialization: "+specialization+", Initial Fees: "+initialFees;
	}
	
	// Overrided method bookAppointment as Doctors class implements Schedule
	@Override
	public void bookAppointment(Appointments appointment) {
		appointments.add(appointment);
        System.out.println("Appointment booked successfully for Doctor: " + name);
	}
	
	// Overrided method updateAppointmentStatus  as Doctors class implements Schedule
	@Override
	public void updateAppointmentStatus(int appointmentId, String status) {
		for (Appointments a : appointments) {
            if (a.getAppointmentId() == appointmentId) {
                a.setAppointmentStatus(status);
                System.out.println("Appointment ID " + appointmentId + " status updated to: " + status);
                break;
            }
        }
    
	}

}
