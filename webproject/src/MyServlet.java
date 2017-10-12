import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long		serialVersionUID	= 1L;
	static String			url		= "jdbc:mysql://http://ec2xyelder.ddns.net:3306/myDB";
	static String			user		= "newremoteuser";
	static String			password		= "password";
	static Connection			connection	= null;

	public MyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("-------- MJDBC Connection Testing ------------<br>");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver?");
			e.printStackTrace();
			return;
		}
		response.getWriter().println(" JDBC Driver Registered!<br>");
		connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("There was an error with the connection!");
			e.printStackTrace();
			return;
		}
		if (connection != null) {
			response.getWriter().println("Database Connection Successful.<br>");
		} else {
			System.out.println("Connection Error");
		}
		try {
			String selectSQL = "SELECT * FROM myTable WHERE ITEMNUMBER LIKE ?";
			String universalCode = "user%";
			response.getWriter().println(selectSQL + "<br>");
			response.getWriter().println("------------------------------------------<br>");
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, theUserName);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String itemName = rs.getString("ITEMNAME");
				String upc = rs.getString("UPC");
				String sku = rs.getString("SKU");
				String price = rs.getString("PRICE");
				String description = rs.getString("DESCRIPTION");
				String availability = rs.getString("AVAILABLE");
				
				
				response.getWriter().append("ITEM NAME: " + itemName + ", ");
				response.getWriter().append("UPC: " + upc + ", ");
				response.getWriter().append("SKU: " + sku + ", ");
				response.getWriter().append("PRICE: " + price + "<br>");
				response.getWriter().append("DESCRIPTION: " + description + ", ");
				response.getWriter().append("AVAILABILITY: " + availability + ", ");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}



