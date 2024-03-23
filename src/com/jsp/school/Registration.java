package com.jsp.school;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import com.sun.java.swing.plaf.windows.resources.windows;
@WebServlet("/Registration")
public class Registration extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String number = req.getParameter("number");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String class1 = req.getParameter("class");
		
		String url="jdbc:mysql://localhost:3306/teca44?user=root&password=12345";
		String insert="insert into school(student_name, student_email, student_number, student_password,gender,student_class) values(?,?,?,?,?,?)";
		
		PrintWriter writer = res.getWriter();
		res.setContentType("text/html");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(insert);
			
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, number);
			ps.setString(4, password);
			ps.setString(5, gender);
			ps.setString(6, class1);
			
			int result = ps.executeUpdate();
			if(result!=0) {
			    RequestDispatcher dispatcher = req.getRequestDispatcher("Registration.html");
			    dispatcher.include(req, res);
			    writer.println("Registration Successfully.....");
			}
			else {
				RequestDispatcher dispatcher = req.getRequestDispatcher("Registration.html");
			    dispatcher.include(req, res);
			    writer.println("<center><h1>Not Registed...</h1></center>");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		


		
	}

}
