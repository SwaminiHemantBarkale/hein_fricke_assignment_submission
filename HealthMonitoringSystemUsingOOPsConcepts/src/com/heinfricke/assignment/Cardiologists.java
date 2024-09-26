package com.heinfricke.assignment;

public class Cardiologists extends Doctors{
	
	// extends keyword for inheritance
	
	// Parameterized Constructor
	public Cardiologists(int dId, String dName, String dEmergencyContact,int fees) {
		super(dId, dName, dEmergencyContact, "Cardiologist", fees);
	}
	
	// @Override helps in implementing Polymorphism
	@Override
    public String toString() {
        return "Cardiologist - Doctor Id: " + id + 
        		""+ ", Name: " + name + 
        		", Emergency Contact: " + emergencyContactNumber + 
        		""+ ", Fees: " + initialFees;
    }
	

}
