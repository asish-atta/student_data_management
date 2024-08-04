package com.StudentDataManagement.Rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StudentDataManagement.Service.StudentService;
import com.StudentDataManagement.entity.Student;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
@RequestMapping("/student")
public class StudentAPI {
	
	 @Autowired
	 private StudentService studentService;

	    @GetMapping 
	    public List<Student> getStudentDetails() {
	        return studentService.getAllStudents();
	    }
	    
	    @GetMapping("{studentID}")
	    public List<Student> getStudentDetails(@PathVariable String studentID) {
	        return studentService.searchStudent(studentID);
	    }
	    
	    @PostMapping(value = "/save")
	    public String createStudentDetails(@RequestBody Student student) {
	        studentService.saveStudent(student);
	        return "student created Successfully";
	    }
	    
	    @PutMapping(value = "/update")
	    public String updateStudentDetails(@RequestBody Student student) {
	        studentService.updateStudent(student);
	        return "student updated Successfully";
	    }
	    
	    @DeleteMapping(value = "/delete/{studentID}")
	    public String deleteStudentDetails(@PathVariable String studentID) {
	        studentService.deleteStudent(studentID);
	        return "student deleted Successfully";
	    }

}
