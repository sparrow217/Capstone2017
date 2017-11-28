package com.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.FrameworkResultsModel;
import com.model.ListModel;
import com.model.PerformanceExpectationsResultModel;
import com.model.SavedResultsModel;

@Controller
public class SavedController {
	@RequestMapping(value = "/saved", method = RequestMethod.POST)
	public ModelAndView processForm(@ModelAttribute("text") ListModel d)
	{
		ModelAndView model = new ModelAndView("redirect:/saved");
		
		Connection c;
		try {
			
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://ec2-54-163-229-169.compute-1.amazonaws.com:5432/d20jlcje56dt5e?sslmode=require","nszxdsponiovbm","63c16f5fdfaec2dfc713dd4768cc1a905c2ce0095c3ac37894e29587cd104bbd");
		
			Statement stmt = c.createStatement();
			String sql;
			//Insert the lesson plan info and get back the id
			sql = "INSERT INTO LessonPlans (u_id, title, lessonplan) VALUES ('1', '" + d.getTitle() + "', '" + d.getLessonPlan() + "') RETURNING l_id;";
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				int lid = rs.getInt("l_id");
				
				// Insert the lesson plan id matched with the framework ids
				for(int i : d.getFlist()) {
					sql = "INSERT INTO Framework_Lessons (l_id, f_id) VALUES ('" + lid + "', '" + i + "');";
					stmt.executeUpdate(sql);
				}
				
				// Insert the lesson plan id matched with the performance expectation PEIDs
				for(String i : d.getPlist()) {
					sql = "INSERT INTO Performance_Lessons (l_id, PEID) VALUES ('" + lid + "', '" + i + "');";
					stmt.executeUpdate(sql);
				}
			}
			
			rs.close();
		    stmt.close();
		    c.close();
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}
	
	@RequestMapping(value = "/saved", method = RequestMethod.GET)
	public String initialize(ModelMap model) {
		
		List<SavedResultsModel> savedResults = new ArrayList<SavedResultsModel>();
		
		Connection c;
		try {
			
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://ec2-54-163-229-169.compute-1.amazonaws.com:5432/d20jlcje56dt5e?sslmode=require","nszxdsponiovbm","63c16f5fdfaec2dfc713dd4768cc1a905c2ce0095c3ac37894e29587cd104bbd");
		
			Statement stmt = c.createStatement();
			String sql;
			//Get the lesson plans for the user
			sql = "SELECT * FROM LessonPlans WHERE LessonPlans.u_id = 1;"; 
			ResultSet rs = stmt.executeQuery(sql);
			
			// Store all of the lesson plans
			while(rs.next()) {
				SavedResultsModel s = new SavedResultsModel();
				s.setLid(rs.getInt("l_id"));
				s.setLessonPlan(rs.getString("lessonplan"));
				s.setTitle(rs.getString("title"));
				savedResults.add(s);			
			}
			
			// Get the saved framework matches for each lesson plan
			for(SavedResultsModel s : savedResults) {
				sql = "SELECT * FROM Framework, Framework_Lessons WHERE l_id = '" + s.getLid() + "' AND Framework_Lessons.f_id = Framework.f_id;";
				rs = stmt.executeQuery(sql);
				List<FrameworkResultsModel> frameworkResults = new ArrayList<FrameworkResultsModel>();
				while(rs.next()) {
					FrameworkResultsModel rm = new FrameworkResultsModel();
					
					rm.setDimension(rs.getString("Dimension"));
					rm.setFrameworkElement(rs.getString("FrameworkElement"));
					rm.setFrameworkSubelement(rs.getString("FrameworkSubelement"));
					
					frameworkResults.add(rm);
				}
				
				s.setFrm(frameworkResults);
			}
			
			// Get the saved performance expectation matches for each lesson plan
			for(SavedResultsModel s : savedResults) {
				sql = "SELECT * FROM PerformanceExpectations, Performance_Lessons WHERE l_id = '" + s.getLid() + "' AND Performance_Lessons.PEID = PerformanceExpectations.PEID;";
				rs = stmt.executeQuery(sql);
				List<PerformanceExpectationsResultModel> PerformanceResults = new ArrayList<PerformanceExpectationsResultModel>();
				while(rs.next()) {
					PerformanceExpectationsResultModel pe = new PerformanceExpectationsResultModel();
					
					pe.setPEID(rs.getString("PEID"));
					pe.setPerformanceExpectation(rs.getString("PerformanceExpectation"));
					
					PerformanceResults.add(pe);
				}
				
				s.setPerm(PerformanceResults);
			}
			
			
			model.addAttribute("SavedResults", savedResults);
			
			rs.close();
			
		    stmt.close();
		    c.close();
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "saved";
	}
}
