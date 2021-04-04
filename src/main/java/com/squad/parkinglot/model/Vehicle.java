package com.squad.parkinglot.model;

public abstract class Vehicle {

	private String registrationNo = null;
	private String driverAge = null;
	
	public Vehicle(String registrationNo, String driverAge) {
		this.registrationNo = registrationNo;
		this.driverAge = driverAge;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getDriverAge() {
		return driverAge;
	}

	public void setDriverAge(String driverAge) {
		this.driverAge = driverAge;
	}

	@Override
	public String toString() {
		return "Vehicle [registrationNo=" + registrationNo + ", driverAge=" + driverAge + "]";
	}


}
