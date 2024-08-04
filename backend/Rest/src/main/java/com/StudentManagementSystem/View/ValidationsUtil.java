package com.StudentManagementSystem.View;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

public class ValidationsUtil {


	private JFrame frame_work() {
	JFrame frame = new JFrame();
	frame.setSize(200, 200);
	frame.setLocationRelativeTo(null);
	return frame;
	}
	
	public static Object table;

	public  void add_action_not_Performed() {
			JOptionPane.showMessageDialog(frame_work(), "Enter all Details..","Warning!..", JOptionPane.WARNING_MESSAGE);		
		}
	
	
	
	public void add_action_Performed() {
		JOptionPane.showMessageDialog(frame_work(), "Details added successfully.");	
	}
	
	public void delete_action_Performed() {
		JOptionPane.showMessageDialog(frame_work(), "Deleted successfully.");
	}
	
	
	public void delete_action_not_Performed() {
		JOptionPane.showMessageDialog(frame_work(), "No id found to delete.");
	}


	
	public void update_action_Performed() {
		JOptionPane.showMessageDialog(frame_work(), "Details updated successfully.");
	}
	
	
	public void search_not_Performed() {
		JOptionPane.showMessageDialog(frame_work(), "Id not found..","Warning!..", JOptionPane.WARNING_MESSAGE);
	}

	
	
	public void save_action_Performed() {
		JOptionPane.showMessageDialog(frame_work(), "Data saved successfully.");	
	}
	
	
	
	public static java.lang.Runnable LookandFeel() {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	        if ("Nimbus".equals(info.getName())) {
	        	try {
	            UIManager.setLookAndFeel(info.getClassName());
	        	} catch(ClassNotFoundException |
       InstantiationException |
       IllegalAccessException |
       UnsupportedLookAndFeelException e) {
	        		e.printStackTrace();
	        	}
	        	break;
	        }
	    }
		return null ;
	}




}
