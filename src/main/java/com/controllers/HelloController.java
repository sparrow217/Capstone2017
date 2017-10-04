package com.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller

public class HelloController {
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String printHello(ModelMap model) {
		model.addAttribute("message", "Hello Spring MVC Framework!");
	  
		Connection c;
		try {
			c = getConnection();
			
			//Class.forName("org.postgresql.Driver");
		//c = DriverManager.getConnection("postgres://nszxdsponiovbm:63c16f5fdfaec2dfc713dd4768cc1a905c2ce0095c3ac37894e29587cd104bbd@ec2-54-163-229-169.compute-1.amazonaws.com:5432/d20jlcje56dt5e","nszxdsponiovbm","63c16f5fdfaec2dfc713dd4768cc1a905c2ce0095c3ac37894e29587cd104bbd");
		
		Statement stmt = c.createStatement();
		String sql;
		sql = "SELECT username, password FROM useraccount";
		ResultSet rs = stmt.executeQuery(sql);
		  
		String username = null;
		String password = null;
		
		while(rs.next()){
			username = rs.getString("username");
		password = rs.getString("password");
		}
		
		model.addAttribute("username", username);
		model.addAttribute("password", password);
			
			rs.close();
		    stmt.close();
		    c.close();
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

  return "hello";
   }
   
   private static Connection getConnection() throws URISyntaxException, SQLException {
	   URI dbUri = new URI(System.getenv("DATABASE_URL"));

	   String username = dbUri.getUserInfo().split(":")[0];
	   String password = dbUri.getUserInfo().split(":")[1];
	   String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	   return DriverManager.getConnection(dbUrl, username, password);
	}
}