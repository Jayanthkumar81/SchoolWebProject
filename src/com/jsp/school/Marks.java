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
@WebServlet("/Marks")
public class Marks extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		int roolno = Integer.parseInt(id);
		
		String name = req.getParameter("name");
		
		String telugusub = req.getParameter("telugu");
		double telugu = Double.parseDouble(telugusub);
		
		String englishsub = req.getParameter("english");
		double english = Double.parseDouble(englishsub);
		
		String hindisub = req.getParameter("hindi");
		double hindi = Double.parseDouble(hindisub);
		
		String  mathssub = req.getParameter("maths");
		double maths = Double.parseDouble(mathssub);
		
		String  sciencesub = req.getParameter("science");
		double science = Double.parseDouble(sciencesub);
		
		String socialsub = req.getParameter("social");
		double social = Double.parseDouble(socialsub);
		
		
		Double total=telugu+english+hindi+maths+science+social;
		
		String url="jdbc:mysql://localhost:3306/teca44?user=root&password=12345";
		String update="update school set telugu_marks=?,english_marks=? ,hindi_marks=?, maths_marks=?, science_marks=?, social_marks=?,total_marks=?,grade=? where student_id=? and student_name=?";
		
		PrintWriter writer = res.getWriter();
		res.setContentType("text/html");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(update);
			
			ps.setDouble(1, telugu);
			ps.setDouble(2, english);
			ps.setDouble(3, hindi);
			ps.setDouble(4, maths);
			ps.setDouble(5, science);
			ps.setDouble(6, social);
			ps.setDouble(7, total);
			
			String A="A";
			String A1="A1";
			String B1="B1";
			String B="B";
			String C="C";
			String F="F";
			
			if(total<=600&&total>=550) {
				ps.setString(8, A);
			}
			else if(total<=550 &&total>=500) {
				ps.setString(8, A1);
			}
			else if(total<=500 && total>=450) {
				ps.setString(8, B1);
			}
			else if(total<=450 && total>=400) {
				ps.setString(8, B);
			}
			else if(total<=400 && total>=350) {
				ps.setString(8, C);
			}
			else if(total<350) {
				ps.setString(8, F);
			}
			
			ps.setInt(9, roolno );
			ps.setString(10, name);
			
			
			int result = ps.executeUpdate();
			if(result!=0) {
				    RequestDispatcher dispatcher = req.getRequestDispatcher("Marks.html");
				    dispatcher.include(req, res);
				    writer.println("<h1>Marks Submited successfully..</h1>");
			}
			else {
				    RequestDispatcher dispatcher = req.getRequestDispatcher("Marks.html");
				    dispatcher.include(req, res);
				    writer.println("<h1>Marks Submited  Not successfully..</h1>");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}





		
		
	}

}
