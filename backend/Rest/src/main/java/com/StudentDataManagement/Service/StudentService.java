package com.StudentDataManagement.Service;

import java.util.List;

import com.StudentDataManagement.entity.Student;

public interface StudentService {
	
	  List<Student> getAllStudents();

	    List<Student> searchStudent(String studentID);

	    void saveStudent(Student student);

	    void updateStudent(Student student);

	    void deleteStudent(String studentID);

}
