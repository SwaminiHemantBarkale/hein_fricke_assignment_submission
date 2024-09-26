package com.heinfricke.assignment;

// Class defined for exception handling as per assignment requirement
public class InvalidPatientIdException extends Exception {
	
	public InvalidPatientIdException(String message) {
        super(message);
    }
}
