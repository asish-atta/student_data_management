package com.StudentDataManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="studentregister1")
public class Student {
	
	private String studentName;
	private String fatherName;
	@Id
	private String studentID;
	private String studentDOB;
	private String studentGender;
	private String studentDepartment;
	private String studentMobileNumber;
	private String studentParentMobileNumber;
	private String studentEmail;
	private String studentAddress;
//	private String searchId;
	
	
	public Student(String studentName, String fatherName, String studentID, String studentDOB, String studentGender,
			String studentDepartment, String studentMobileNumber, String studentParentMobileNumber, String studentEmail,
			String studentAddress) {
		
		this.studentName = studentName;
		this.fatherName = fatherName;
		this.studentID = studentID;
		this.studentDOB = studentDOB;
		this.studentGender = studentGender;
		this.studentDepartment = studentDepartment;
		this.studentMobileNumber = studentMobileNumber;
		this.studentParentMobileNumber = studentParentMobileNumber;
		this.studentEmail = studentEmail;
		this.studentAddress = studentAddress;
	//	this.searchId = searchId;
	}

	public Student() {
		
	}
	
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getStudentDOB() {
		return studentDOB;
	}
	public void setStudentDOB(String studentDOB) {
		this.studentDOB = studentDOB;
	}
	public String getStudentGender() {
		return studentGender;
	}
	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}
	public String getStudentDepartment() {
		return studentDepartment;
	}
	public void setStudentDepartment(String studentDepartment) {
		this.studentDepartment = studentDepartment;
	}
	public String getStudentMobileNumber() {
		return studentMobileNumber;
	}
	public void setStudentMobileNumber(String studentMobileNumber) {
		this.studentMobileNumber = studentMobileNumber;
	}
	public String getStudentParentMobileNumber() {
		return studentParentMobileNumber;
	}
	public void setStudentParentMobileNumber(String studentParentMobileNumber) {
		this.studentParentMobileNumber = studentParentMobileNumber;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentAddress() {
		return studentAddress;
	}
	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

}
