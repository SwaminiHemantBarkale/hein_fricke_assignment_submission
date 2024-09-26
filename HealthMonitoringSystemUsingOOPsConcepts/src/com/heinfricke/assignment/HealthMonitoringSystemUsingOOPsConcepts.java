package com.heinfricke.assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class HealthMonitoringSystemUsingOOPsConcepts {

	public static void main(String[] args) throws IOException, InvalidPatientIdException {
		
		// Reading the data from file(Patients.csv)
        List<Patients> patients = readPatientsFromFile("src/data/Patients.csv");
        
        // Reading the data from file(Doctors.csv)
        List<Doctors> doctors = readDoctorsFromFile("src/data/Doctors.csv");
        
        // Reading the data from file(Appointments.csv)
        List<Appointments> appointments = readAppointmentsFromFile("src/data/Appointments.csv");
        
        // Appointments sorted using bubble sort
        bubbleSortAppointments(appointments);
        
        // Appointments sorted using quicksort
        quickSortAppointments(appointments);
        
        // Adding i.e insert inputs manually 
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to register a new patient? (yes/no)");
        String response = scanner.nextLine();
        
        while (response.equalsIgnoreCase("yes")) {
            registerNewPatient(patients, scanner);
            System.out.println("Do you want to register another patient? (yes/no)");
            response = scanner.nextLine();
        }
        
        // Printing all patients to help during debugging and error solving
        System.out.println("Patients List:");
        for (Patients p : patients) {
            System.out.println(p);
        }
        
        // Sorting patients by their age (Simple sorting)
        patients.sort(Comparator.comparingInt(Patients::getAge));
        System.out.println("\n Patients sorted by age:");
        for (Patients p : patients) {
            System.out.println(p);
        }
       

        // Scheduling the appointments 
        System.out.println("\nDo you want to schedule an appointment? (yes/no)");
        String scheduleResponse = scanner.nextLine();
        
        while (scheduleResponse.equalsIgnoreCase("yes")) {
            scheduleAppointment(doctors, patients, appointments, scanner);
            System.out.println("Do you want to schedule another appointment? (yes/no)");
            scheduleResponse = scanner.nextLine();
        }
        
        // Retrieving appointments for a specific doctor
        System.out.println("\nEnter the doctor's ID to retrieve their appointments:");
        int doctorId = Integer.parseInt(scanner.nextLine());
        retrieveAppointmentsForDoctor(doctorId, appointments);
        
        // Displaying the appointments for all Doctors
        System.out.println("\nDisplaying all appointments for each doctor:");
        displayAppointmentsForAllDoctors(doctors, appointments);
        
        // Updating the status of Appointment
        System.out.println("\nDo you want to update the status of any appointment? (yes/no)");
        String updateResponse = scanner.nextLine();
        while (updateResponse.equalsIgnoreCase("yes")) {
            updateAppointmentStatus(appointments, scanner);
            System.out.println("Do you want to update another appointment? (yes/no)");
            updateResponse = scanner.nextLine();
        }

        
        // Updating Patient VitalReportDetails
        updatePatientVitals(patients, scanner);
        
        // Retrieving the MedicalHistoryDetails of the Patient
        retrieveMedicalHistory(patients, scanner);
        
        // Use of interface reference for Polymorphism with Schedule interface
        Schedule cardiologist = new Cardiologists(1, "Dr. Smith", "123-456", 300);
        Schedule dermatologist = new Dermatologists(2, "Dr. Brown", "987-654", 250);

        // Booking appointments
        Appointments appointment1 = new Appointments(1, 1, 1, "2024-09-26", "", "Booked");
        cardiologist.bookAppointment(appointment1);

        Appointments appointment2 = new Appointments(2, 2, 2, "2024-09-27", "", "Booked");
        dermatologist.bookAppointment(appointment2);

        // Retrieve i.e select appointments for both doctors
        System.out.println("\nAppointments for Cardiologist:");
        cardiologist.getAppointments().forEach(System.out::println);

        System.out.println("\nAppointments for Dermatologist:");
        dermatologist.getAppointments().forEach(System.out::println);

        // Updating the status of Appointment
        cardiologist.updateAppointmentStatus(1, "Completed");
        
        scanner.close();
  
	}
	

	
	public static List<Patients> readPatientsFromFile(String fileName) throws IOException {
	    List<Patients> patients = new ArrayList<>();
	    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

	    String line;
	    bufferedReader.readLine(); 

	    while ((line = bufferedReader.readLine()) != null) {
	        String[] data = line.split(",", -1); 
	        int id = Integer.parseInt(data[0]);
	        String name = data[1];
	        String address = data[2];
	        String gender = data[3];
	        String contactNumber = data[4];
	        int age = Integer.parseInt(data[5]);

	        String medicalHistoryRaw = data[6].replaceAll("[\\[\\]\"]", ""); 
	        List<String> medicalHistory = Arrays.asList(medicalHistoryRaw.split(","));

	        patients.add(new Patients(id, name, address, gender, contactNumber, age, medicalHistory));
	    }

	    bufferedReader.close();
	    return patients;
	}

	
	
	private static void registerNewPatient(List<Patients> patients, Scanner scanner) {
	   
	    int id = patients.isEmpty() ? 1 : patients.get(patients.size() - 1).getId() + 1;
	    System.out.println("Generated Patient ID: " + id);
	    
	    System.out.println("Enter Patient Name:");
	    String name = scanner.nextLine();
	    
	    System.out.println("Enter Patient Address:");
	    String address = scanner.nextLine();
	    
	    System.out.println("Enter Patient Gender:");
	    String gender = scanner.nextLine();
	    
	    System.out.println("Enter Patient Contact Number:");
	    String contactNumber = scanner.nextLine();
	    
	    System.out.println("Enter Patient Age:");
	    int age = Integer.parseInt(scanner.nextLine());
	    
	    System.out.println("Enter Medical History (comma-separated):");
	    String medicalHistoryInput = scanner.nextLine();
	    List<String> medicalHistory = Arrays.asList(medicalHistoryInput.split(","));
	    
	    
	    Patients newPatient = new Patients(id, name, address, gender, contactNumber, age, medicalHistory);
	    patients.add(newPatient);
	    System.out.println("Patient registered successfully!");
	}

	
	
	public static List<Doctors> readDoctorsFromFile(String fileName) throws IOException {
	    List<Doctors> doctors = new ArrayList<>();
	    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
	    String line;
	    
	    
	    bufferedReader.readLine();
	    
	    while ((line = bufferedReader.readLine()) != null) {
	        String[] data = line.split(",");
	        int doctorId = Integer.parseInt(data[0]);
	        String name = data[1];
	        String emergencyContactNumber = data[2];
	        String specialization = data[3];
	        int initialFees = Integer.parseInt(data[4]);
	        
	        
	        Doctors doctor = new Doctors(doctorId, name, emergencyContactNumber, specialization, initialFees);
	        doctors.add(doctor);
	    }
	    
	    bufferedReader.close();
	    return doctors;
	}
	
    public static List<Appointments> readAppointmentsFromFile(String fileName) throws IOException {
        List<Appointments> appointments = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line;
        
        
        bufferedReader.readLine();
        
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");
            int appointmentId = Integer.parseInt(data[0]);
            int patientId = Integer.parseInt(data[1]);
            int doctorId = Integer.parseInt(data[2].trim());
            String appointmentDate = data[3];
            String status = data[4];
            
            Appointments appointment = new Appointments(appointmentId, patientId, doctorId, appointmentDate, "", status);
            appointments.add(appointment);
        }
        
        bufferedReader.close();
        return appointments;
    }

    
    private static void scheduleAppointment(List<Doctors> doctors, List<Patients> patients, List<Appointments> appointments, Scanner scanner) throws InvalidPatientIdException {
    	
    	System.out.println("Enter Patient ID:");
        int patientId = Integer.parseInt(scanner.nextLine());

        // Checking if the entered Patient's Id is valid 
        // Exception Handling Implementation
        if (patients.stream().noneMatch(p -> p.getId() == patientId)) {
            throw new InvalidPatientIdException("Invalid Patient ID: " + patientId);
        }
        System.out.println("Enter the specialization for the doctor (e.g., Cardiologist, Dermatologist, etc.):");
        String specialization = scanner.nextLine();
        
        
        List<Doctors> specializedDoctors = doctors.stream()
            .filter(d -> d.getSpecialization().equalsIgnoreCase(specialization))
            .toList();
        
        if (specializedDoctors.isEmpty()) {
            System.out.println("No doctors found with specialization: " + specialization);
            return;
        }
        
        
        System.out.println("Available doctors with specialization '" + specialization + "':");
        for (Doctors doctor : specializedDoctors) {
            System.out.println("Doctor ID: " + doctor.getDoctorId() + ", Name: " + doctor.getName());
        }

        
        System.out.println("Enter the Doctor ID to schedule an appointment:");
        int doctorId = Integer.parseInt(scanner.nextLine());
        
        // Checking if the selected Doctor exists in the specialization of doctors data or not
        Doctors selectedDoctor = specializedDoctors.stream()
            .filter(d -> d.getDoctorId() == doctorId)
            .findFirst()
            .orElse(null);
        
        if (selectedDoctor == null) {
            System.out.println("Invalid Doctor ID selected.");
            return;
        }
        
        
        System.out.println("Enter Patient ID:");
        int patientId2 = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter Appointment Date (YYYY-MM-DD):");
        String appointmentDate = scanner.nextLine();

        System.out.println("Enter Appointment Status (e.g., Booked):");
        String status = scanner.nextLine();

        
        int appointmentId = appointments.size() + 1; 
        Appointments newAppointment = new Appointments(appointmentId, patientId2, doctorId, appointmentDate, "", status);
        appointments.add(newAppointment);

        System.out.println("Appointment scheduled successfully with Dr. " + selectedDoctor.getName() + " (" + specialization + ").");
    }



    private static void retrieveAppointmentsForDoctor(int doctorId, List<Appointments> appointments) {
        System.out.println("\nAppointments for Doctor ID " + doctorId + ":");
        List<Appointments> doctorAppointments = appointments.stream()
            .filter(a -> a.getDoctorId() == doctorId)
            .sorted(Comparator.comparing(Appointments::getAppointmentDate))
            .toList();
        
        if (doctorAppointments.isEmpty()) {
            System.out.println("No appointments found for Doctor ID " + doctorId);
        } else {
            doctorAppointments.forEach(System.out::println);
        }
    }
    

    private static void updatePatientVitals(List<Patients> patients, Scanner scanner) {
        System.out.println("Enter Patient ID to update vitals:");
        int patientId = Integer.parseInt(scanner.nextLine());

        // Finding the patient by id
        Patients patientToUpdate = null;
        for (Patients patient : patients) {
            if (patient.getId() == patientId) {
                patientToUpdate = patient;
                break;
            }
        }

        
        if (patientToUpdate != null) {
            boolean moreVitals = true;
            while (moreVitals) {
                System.out.println("Enter Vital Key (e.g., Blood Pressure, Heart Rate, etc.):");
                String vitalKey = scanner.nextLine();
                System.out.println("Enter Value for " + vitalKey + ":");
                String vitalValue = scanner.nextLine();

                
                patientToUpdate.updateVitalReportDetails(vitalKey, vitalValue);
                System.out.println("Vital '" + vitalKey + "' updated to '" + vitalValue + "' for patient ID: " + patientId);

                
                System.out.println("Do you want to update more vitals? (yes/no)");
                String response = scanner.nextLine();
                if (!response.equalsIgnoreCase("yes")) {
                    moreVitals = false;
                }
            }
        } else {
            System.out.println("Patient ID not found.");
        }
    }

    
    private static void retrieveMedicalHistory(List<Patients> patients, Scanner scanner) {
        System.out.println("Enter Patient ID to retrieve medical history:");
        int patientId = Integer.parseInt(scanner.nextLine());

        for (Patients patient : patients) {
            if (patient.getId() == patientId) {
                System.out.println("Medical History: " + patient.getMedicalHistoryDetails());
                return;
            }
        }
        System.out.println("Patient ID not found.");
    }
    
    private static void displayAppointmentsForAllDoctors(List<Doctors> doctors, List<Appointments> appointments) {
        for (Doctors doctor : doctors) {
            System.out.println("\nAppointments for Dr. " + doctor.getName() + " (Specialization: " + doctor.getSpecialization() + "):");
            List<Appointments> doctorAppointments = appointments.stream()
                .filter(a -> a.getDoctorId() == doctor.getDoctorId())
                .sorted(Comparator.comparing(Appointments::getAppointmentDate))
                .toList();
            
            if (doctorAppointments.isEmpty()) {
                System.out.println("No appointments found for this doctor.");
            } else {
                doctorAppointments.forEach(System.out::println);
            }
        }
    }
    
    private static void updateAppointmentStatus(List<Appointments> appointments, Scanner scanner) {
        System.out.println("\nEnter Appointment ID to update the status:");
        int appointmentId = Integer.parseInt(scanner.nextLine());

        Appointments appointmentToUpdate = null;
        for (Appointments appointment : appointments) {
            if (appointment.getAppointmentId() == appointmentId) {
                appointmentToUpdate = appointment;
                break;
            }
        }

        if (appointmentToUpdate != null) {
            System.out.println("Current Status: " + appointmentToUpdate.getAppointmentStatus());
            System.out.println("Enter new status (e.g., Completed, Canceled):");
            String newStatus = scanner.nextLine();
            appointmentToUpdate.setAppointmentStatus(newStatus);
            System.out.println("Appointment ID " + appointmentId + " status updated to: " + newStatus);
        } else {
            System.out.println("Appointment ID not found.");
        }
    
    }
    
    private static void bubbleSortAppointments(List<Appointments> appointments) {
    	System.out.println("Using BubbleSort");
        int n = appointments.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (appointments.get(j).getAppointmentDate().compareTo(appointments.get(j + 1).getAppointmentDate()) > 0) {
                    Appointments temp = appointments.get(j);
                    appointments.set(j, appointments.get(j + 1));
                    appointments.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) 
            	break;
        }
        
        System.out.println("Appointments sorted by date using bubble sort algorithm:");
        for (Appointments appointment : appointments) {
            System.out.println(appointment);
        }
        
    }
    
    private static void quickSortAppointments(List<Appointments> appointments) {
        System.out.println("Using QuickSort");
        quickSortAppointments(appointments, 0, appointments.size() - 1);
        
        // Displaying the sorted appointments
        System.out.println("Appointments sorted by date using quicksort:");
        for (Appointments appointment : appointments) {
            System.out.println(appointment);
        }
    }

    private static void quickSortAppointments(List<Appointments> appointments, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(appointments, low, high);
            
            quickSortAppointments(appointments, low, partitionIndex - 1);
            quickSortAppointments(appointments, partitionIndex + 1, high);
        }
    }

    private static int partition(List<Appointments> appointments, int low, int high) {
        
        String pivot = appointments.get(high).getAppointmentDate();
        int i = (low - 1); 

        for (int j = low; j < high; j++) {
            
            if (appointments.get(j).getAppointmentDate().compareTo(pivot) <= 0) {
                i++;

                
                Appointments temp = appointments.get(i);
                appointments.set(i, appointments.get(j));
                appointments.set(j, temp);
            }
        }

        
        Appointments temp = appointments.get(i + 1);
        appointments.set(i + 1, appointments.get(high));
        appointments.set(high, temp);

        return i + 1; 
    }


}   
