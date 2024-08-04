package com.StudentDataManagement.Service;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StudentDataManagement.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

	
	// already crud operations are predefined and if we want to add other methods
	// we can declare the methods and implement them in repository class.
}
