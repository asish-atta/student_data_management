package com.StudentDataManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StudentDataManagement.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	
	 @Autowired
	  private StudentRepository studentRepository;


    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchStudent(String studentID) {
        return studentRepository.findById(studentID).map(List::of) 
                .orElseGet(List::of); 
    }

    @Override
    public void saveStudent(Student student) {
    	 if (studentRepository.existsById(student.getStudentID())) {
             throw new RuntimeException("Student already exists with id: " + student.getStudentID());
         } else {
             studentRepository.save(student);
         }
    }

    @Override
    public void updateStudent(Student student) {
        if (studentRepository.existsById(student.getStudentID())) {
            studentRepository.save(student);
        } else {
            throw new RuntimeException("Student not found with id: " + student.getStudentID());
        }
    }

    @Override
    public void deleteStudent(String studentID) {
        studentRepository.deleteById(studentID);
    }

	 
	 
	 /*
	

    private final StudentManagementSystemDAO studentManagementSystemDAO;

    public StudentServiceImpl(StudentManagementSystemDAO studentManagementSystemDAO) {
        this.studentManagementSystemDAO = studentManagementSystemDAO;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentManagementSystemDAO.getAllStudents();
    }	

    @Override
    public List<Student> searchStudent(String studentID) {
        return studentManagementSystemDAO.searchUser(studentID);
    }

    @Override
    public void saveStudent(Student student) {
        studentManagementSystemDAO.saveUser(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentManagementSystemDAO.modifyUser(student);
    }

    @Override
    public void deleteStudent(String studentID) {
        studentManagementSystemDAO.removeUser(studentID);
    }

*/
}
