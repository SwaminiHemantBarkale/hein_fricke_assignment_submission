package com.heinfricke.assignment;

public class Dermatologists extends Doctors {
	
	// extends keyword for inheritance
	
	// Parameterized Constructor
	public Dermatologists(int dId, String dName, String dEmergencyContact,int fees) {
		super(dId, dName, dEmergencyContact, "Detmatologist", fees);
	}
	
	// @Override helps in implementing Polymorphism
	@Override
    public String toString() {
        return "Dermatologist - Doctor Id: " + id + 
        		", Name: " + name + 
        		", Emergency Contact: " + emergencyContactNumber + 
        		", Fees: " + initialFees;
    }
}
