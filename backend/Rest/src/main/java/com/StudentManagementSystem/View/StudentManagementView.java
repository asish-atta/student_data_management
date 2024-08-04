package com.StudentManagementSystem.View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.StudentDataManagement.entity.Student;
import com.StudentManagementSystem.Entity.UserActions;

@SpringBootApplication
public class StudentManagementView extends JFrame {
	
	
	
	public  JTextField Name_textfield, Father_Name_textfield, Id_textfield, DOB_textfield, Gender_textfield, Dept_textfield, mobile_no_textfield,
	Parent_mobile_textfield, Mail_Id_textfield, Address_textfield,SearchId_textfield;
public  JButton add_button, delete_button, update_button,clear_button,find_button;

private static final long serialVersionUID = 1L;
UserActions options = new UserActions();

String[][] data = {  };
String[] column = { "Name","Father_Name", "Id","DOB","Gender", "Department","Mobile_number","Parent_Mobile","Mail_id","Address"};

DefaultTableModel model = new DefaultTableModel(data, column);
JTable table = new JTable(model);

public StudentManagementView() {

try {
	List<Student> studentsList = getAllStudents();
	displayAllStudents(studentsList);
}catch(Exception e){
	System.out.println("There is an error while retrieving all the students");
}


SwingUtilities.invokeLater(ValidationsUtil.LookandFeel());
setTitle("Student Registration Page");
setSize(800, 600);
setDefaultCloseOperation(EXIT_ON_CLOSE);
setVisible(true);


setLayout(new GridLayout(3, 1));
JPanel fieldsPanel = new JPanel();
JPanel buttonsPanel = new JPanel();
JScrollPane display_table = new JScrollPane(table);

add(fieldsPanel);
add(buttonsPanel);
add(display_table);

Name_textfield = new JTextField();
Father_Name_textfield = new JTextField();
Id_textfield = new JTextField();
DOB_textfield = new JTextField();
Gender_textfield = new JTextField();
Dept_textfield = new JTextField();
mobile_no_textfield = new JTextField();
Parent_mobile_textfield = new JTextField();
Mail_Id_textfield = new JTextField();
Address_textfield = new JTextField();
SearchId_textfield = new JTextField();
SearchId_textfield.setText("\"Enter Id to search\"");

buttonsPanel.setLayout(new GridBagLayout());
GridBagConstraints gridBagConstraints = new GridBagConstraints();
gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

add_button = new JButton("Add");
gridBagConstraints.gridx = 0;
gridBagConstraints.gridy = 0;
buttonsPanel.add(add_button);

delete_button = new JButton("Delete");
gridBagConstraints.gridx = 2;
gridBagConstraints.gridy = 0;
buttonsPanel.add(delete_button);

update_button = new JButton("Update");
gridBagConstraints.gridx = 3;
gridBagConstraints.gridy = 0;
buttonsPanel.add(update_button);


clear_button = new JButton("Reset");
gridBagConstraints.gridx = 4;
gridBagConstraints.gridy = 0;
buttonsPanel.add(clear_button);


find_button = new JButton("find");
// buttonsPanel.add(SearchId_textfield);
buttonsPanel.add(find_button);


fieldsPanel.setLayout(new GridLayout(5, 2));
fieldsPanel.add(new JLabel("Name"));
fieldsPanel.add(Name_textfield);

fieldsPanel.add(new JLabel("Father_Name"));
fieldsPanel.add(Father_Name_textfield);

fieldsPanel.add(new JLabel("Id"));
fieldsPanel.add(Id_textfield);

fieldsPanel.add(new JLabel("DOB"));
fieldsPanel.add(DOB_textfield);

fieldsPanel.add(new JLabel("Gender"));
fieldsPanel.add(Gender_textfield);

fieldsPanel.add(new JLabel("Department"));
fieldsPanel.add(Dept_textfield);

fieldsPanel.add(new JLabel("Mobile_Number"));
fieldsPanel.add(mobile_no_textfield);

fieldsPanel.add(new JLabel("Parent_Mobile"));
fieldsPanel.add(Parent_mobile_textfield);

fieldsPanel.add(new JLabel("Mail_Id"));
fieldsPanel.add(Mail_Id_textfield);

fieldsPanel.add(new JLabel("Address"));
fieldsPanel.add(Address_textfield);

setVisible(true);


ValidationsUtil validationsUtil = new ValidationsUtil();

Student student = new Student();


add_button.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(ActionEvent e) {
		student.setStudentName(Name_textfield.getText());
		student.setFatherName(Father_Name_textfield.getText());
		student.setStudentID(Id_textfield.getText());
		student.setStudentDOB(DOB_textfield.getText());
		student.setStudentGender(Gender_textfield.getText());
		student.setStudentDepartment(Dept_textfield.getText());
		student.setStudentMobileNumber(mobile_no_textfield.getText());
		student.setStudentParentMobileNumber(Parent_mobile_textfield.getText());
		student.setStudentEmail(Mail_Id_textfield.getText());
		student.setStudentAddress(Address_textfield.getText());
		
		if(student.getStudentName().equals("")
		|| student.getFatherName().equals("")
		|| student.getStudentID().equals("")
		|| student.getStudentDOB().equals("")
		|| student.getStudentGender().equals("")
		|| student.getStudentDepartment().equals("")
		|| student.getStudentMobileNumber().equals("")
		|| student.getStudentParentMobileNumber().equals("")
		|| student.getStudentEmail().equals("")
		|| student.getStudentAddress().equals("")) {
	validationsUtil.add_action_not_Performed();
		} else {
			try {
				options.addUser(student);
				validationsUtil.add_action_Performed();
				try {
					model.setRowCount(0);
					List<Student> studentsList = getAllStudents();
					displayAllStudents(studentsList);

				}catch(Exception e1){
					System.out.println(e1);
					
				}
				clearFields();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
});


update_button.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(ActionEvent e) {
		student.setStudentName(Name_textfield.getText());
		student.setFatherName(Father_Name_textfield.getText());
		student.setStudentID(Id_textfield.getText());
		student.setStudentDOB(DOB_textfield.getText());
		student.setStudentGender(Gender_textfield.getText());
		student.setStudentDepartment(Dept_textfield.getText());
		student.setStudentMobileNumber(mobile_no_textfield.getText());
		student.setStudentParentMobileNumber(Parent_mobile_textfield.getText());
		student.setStudentEmail(Mail_Id_textfield.getText());
		student.setStudentAddress(Address_textfield.getText());
		
		
		if(student.getStudentName().equals("")
				|| student.getFatherName().equals("")
				|| student.getStudentID().equals("")
				|| student.getStudentDOB().equals("")
				|| student.getStudentGender().equals("")
				|| student.getStudentDepartment().equals("")
				|| student.getStudentMobileNumber().equals("")
				|| student.getStudentParentMobileNumber().equals("")
				|| student.getStudentEmail().equals("")
				|| student.getStudentAddress().equals("")) {
			validationsUtil.add_action_not_Performed();
			
		}else {
		try {
			options.modifyData(student);
			validationsUtil.update_action_Performed();
			try {
				model.setRowCount(0);
				List<Student> studentsList = getAllStudents();
				displayAllStudents(studentsList);
			}catch(Exception e1){
				System.out.println(e1);
				
			}
			clearFields();
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		}
	}
});


find_button.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(ActionEvent e) {
	try {
	
		List<Student> student = new ArrayList<Student>();
		student =  options.search(Id_textfield.getText());
		Name_textfield.setText(student.get(0).getStudentName());
		Father_Name_textfield.setText(student.get(0).getFatherName());
		Id_textfield.setText(student.get(0).getStudentID());
		DOB_textfield.setText(student.get(0).getStudentDOB());
		Gender_textfield.setText(student.get(0).getStudentGender());
		Dept_textfield.setText(student.get(0).getStudentDepartment());
		mobile_no_textfield.setText(student.get(0).getStudentMobileNumber());
		Parent_mobile_textfield.setText(student.get(0).getStudentParentMobileNumber());
		Mail_Id_textfield.setText(student.get(0).getStudentEmail());
		Address_textfield.setText(student.get(0).getStudentAddress());
	
	} catch (Exception e1) {
		validationsUtil.search_not_Performed();
		e1.printStackTrace();
		
	}

}
});

/*
SearchId_textfield.addMouseListener(new MouseAdapter(){
	public void mouseClicked(MouseEvent e) {
		SearchId_textfield.setText(null);
	}
});

*/

clear_button.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(ActionEvent e) {
			clearFields();
	}
});


delete_button.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(ActionEvent e) {
	 student.setStudentID(Id_textfield.getText());
		try {
			options.deleteUser(student.getStudentID());
			validationsUtil.delete_action_Performed();
			try {
				model.setRowCount(0);
				List<Student> studentsList = getAllStudents();
				
				displayAllStudents(studentsList);
			}catch(Exception e1){
				System.out.println(e1);
			}
			clearFields();
		} catch (Exception e1) {
			validationsUtil.delete_action_not_Performed();
			e1.printStackTrace();
		}
	}

	
});




table.addMouseListener(new MouseAdapter() {
	public void mouseClicked(MouseEvent e) {
		int rowIndex = table.getSelectedRow();
		
		String Name = (String)model.getValueAt(rowIndex, 0);
		String Father_Name = (String)model.getValueAt(rowIndex, 1);
		String Id = (String)model.getValueAt(rowIndex, 2);
		String DOB = (String)model.getValueAt(rowIndex, 3);
		String Gender = (String)model.getValueAt(rowIndex, 4);
		String Department = (String)model.getValueAt(rowIndex, 5);
		String Mobile_Number  = (String)model.getValueAt(rowIndex, 6);
		String Parent_Mobile = (String)model.getValueAt(rowIndex, 7);
		String Mail_Id = (String)model.getValueAt(rowIndex, 8);
		String Address  = (String)model.getValueAt(rowIndex, 9);
		
		
		Name_textfield.setText(Name);
		Father_Name_textfield.setText(Father_Name);
		Id_textfield.setText(Id);
		DOB_textfield.setText(DOB);
		Gender_textfield.setText(Gender);
		Dept_textfield.setText(Department);
		mobile_no_textfield.setText(Mobile_Number);
		Parent_mobile_textfield.setText(Parent_Mobile);
		Mail_Id_textfield.setText(Mail_Id);
		Address_textfield.setText(Address);
	}
});



table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
}



private List<Student> getAllStudents() throws Exception{
	return options.getAllStudents();
}	


private void displayAllStudents(List<Student> studentsList) {
	DefaultTableModel model1 = (DefaultTableModel)table.getModel(); 
	for(int studentCount = 0; studentCount <= studentsList.size(); studentCount++) {
		String[] studentDetails = new String[model1.getColumnCount()];
		Student tempStudent = studentsList.get(studentCount);
		studentDetails[0] = tempStudent.getStudentName();
		studentDetails[1] = tempStudent.getFatherName();
		studentDetails[2] = tempStudent.getStudentID();
		studentDetails[3] = tempStudent.getStudentDOB();
		studentDetails[4] = tempStudent.getStudentGender();
		studentDetails[5] = tempStudent.getStudentDepartment();
		studentDetails[6] = tempStudent.getStudentMobileNumber();
		studentDetails[7] = tempStudent.getStudentParentMobileNumber();
		studentDetails[8] = tempStudent.getStudentEmail();
		studentDetails[9] = tempStudent.getStudentAddress();
		
		
		model1.addRow(studentDetails);
	}
}



	



private void clearFields() {
Name_textfield.setText(null);
Father_Name_textfield.setText(null);
Id_textfield.setText(null);
DOB_textfield.setText(null);
Gender_textfield.setText(null);
Dept_textfield.setText(null);
mobile_no_textfield.setText(null);
Parent_mobile_textfield.setText(null);
Mail_Id_textfield.setText(null);
Address_textfield.setText(null);
}





public static void main(String args[]) {
new StudentManagementView();
}


}
