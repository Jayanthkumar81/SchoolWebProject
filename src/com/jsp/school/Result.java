package com.jsp.school;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/Result")
public class Result extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		String id = req.getParameter("number");
		
		int roolno = Integer.parseInt(id);
		
		String url="jdbc:mysql://localhost:3306/teca44?user=root&password=12345";
		String select="select * from school where student_id=?";
		
		PrintWriter writer = res.getWriter();
		res.setContentType("text/html");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(select);
			ps.setInt(1, roolno);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int studentId = rs.getInt(1);
				String name = rs.getString(2);
				double telugu = rs.getDouble(7);
				double english = rs.getDouble(8);
				double hindi = rs.getDouble(9);
				double maths = rs.getDouble(10);
				double science = rs.getDouble(11);
				double social = rs.getDouble(12);
				double totalMarks = rs.getDouble(13);
				String grade = rs.getString(14);
				
				writer.println("<strong>Student Roll no :</strong>"+studentId+"<br>");
				writer.println("<strong>Student Name :</strong>"+name+"<br>");
				writer.println("<strong>Telugu Marks :</strong>"+telugu+"<br>");
				writer.println("<strong>English Marks :</strong>"+english+"<br>");
				writer.println("<strong>Hindi marks :</strong>"+hindi+"<br>");
				writer.println("<strong>Maths Marks :</strong>"+maths+"<br>");
				writer.println("<strong>Science Marks :</strong>"+science+"<br>");
				writer.println("<strong>Social Marks :</strong>"+social+"<br>");
				writer.println("<strong>Total Marks :</strong>"+totalMarks+"<br>");
				writer.println("<strong>Grade :</strong>"+grade+"<br>");			
			}
			else {
				writer.println("<h1>Invalid Rool Number</h1");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		
		
	}

}
