package com.heinfricke.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Patients {
	
	 // Defining variables
	 private int id;
	 private String name;
	 private String address;
	 private String gender;
	 private String contactNumber;
	 private int age;
	 private List<String> medicalHistoryDetails;
	 private Map<String, String> vitalReportDetails;
		
		
	//  Parameterized Constructor
	public Patients(int id, String name,String address,String gender,String contactNumber 
				   ,int age,List<String> medicalHistoryDetails) {
	     this.id = id;
	     this.name = name;
	     this.address=address;
	     this.age = age;
	     this.gender = gender;
	     this.contactNumber=contactNumber;
	     this.medicalHistoryDetails = new ArrayList<>(medicalHistoryDetails);
	     this.vitalReportDetails = new HashMap<>();
	 }
		
	//  Method to update vitalReportDetails with return type void
	public void updateVitalReportDetails(String key, String value) {
		vitalReportDetails.put(key, value);
	}
	
	// Method to retrieve vitalReportDetails
	public Map<String, String> getVitalReportDetails() {
        return vitalReportDetails;
    }
		
	//  Method to retrieve medicalHistoryDetails 
	//  Implementing Encapsulation to hide sensitive and confidential information
	//  and retrieve only necessary details through get method
	public List<String> getMedicalHistoryDetails(){
		return medicalHistoryDetails;
	}
		
    //  Method to retrieve age with return type int
	public int getAge() {
		return age;
	}
	
	// Method to retrieve id with return type int
	public int getId() {
		return id;
	}
	
	// Printing output 
	public String toString() {
		return "Id: "+id+", Name of Patient: "+name+", Age of Patient: "+age+
					", Gender of Patient: "+gender+", Contact Number of Patient: "+contactNumber+
					", Address of Patient: "+address;
	}

		
}
