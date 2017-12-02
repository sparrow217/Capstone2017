package com.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.TextAnalysis;
import com.model.ListModel;
import com.model.FrameworkResultsModel;
import com.model.PerformanceExpectationsResultModel;

@Controller
public class ResultsController {
	
	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public ModelAndView processForm(@ModelAttribute("text") ListModel t)
	{
		ModelAndView model = new ModelAndView("/result");
		
		TextAnalysis ta = new TextAnalysis();
		List<FrameworkResultsModel> frameworkResults = new ArrayList<FrameworkResultsModel>();
		List<PerformanceExpectationsResultModel> PerformanceResults = new ArrayList<PerformanceExpectationsResultModel>();
		
		Connection c;
		try {
			//c = getConnection();
			
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://ec2-54-163-229-169.compute-1.amazonaws.com:5432/d20jlcje56dt5e?sslmode=require","nszxdsponiovbm","63c16f5fdfaec2dfc713dd4768cc1a905c2ce0095c3ac37894e29587cd104bbd");
		
			Statement stmt = c.createStatement();
			String sql;
			sql = "SELECT * FROM Framework"; //Get the framework table
			ResultSet rs = stmt.executeQuery(sql);
			  
			
			while(rs.next()){
				String frameworkSubelement = rs.getString("FrameworkSubelement");
				// Look for a match
				float p = ta.textMatch(t.getLessonPlan(), frameworkSubelement);
				if(p > 0.15){
					FrameworkResultsModel rm = new FrameworkResultsModel();
					
					rm.setDimension(rs.getString("Dimension"));
					rm.setFrameworkElement(rs.getString("FrameworkElement"));
					rm.setFrameworkSubelement(frameworkSubelement);
					rm.setId(rs.getInt("f_id"));
					rm.setPercent(p * 100);
					
					frameworkResults.add(rm);
				}
			}
			
			// Sort by percent
			Collections.sort(frameworkResults, Collections.reverseOrder(new Comparator<FrameworkResultsModel>() {
				@Override
				public int compare(FrameworkResultsModel f1, FrameworkResultsModel f2) {
					
					return Float.compare(f1.getPercent(), f2.getPercent());
					
				}
			}));
			
			// Return matches
			model.addObject("frameworkResults", frameworkResults);
			
			sql = "SELECT * FROM PerformanceExpectations";
			rs = stmt.executeQuery(sql);
			// Look for a match
			while(rs.next()){
				String performanceExpectation = rs.getString("PerformanceExpectation");
				float p = ta.textMatch(t.getLessonPlan(), performanceExpectation);
				if(p > 0.15){
					PerformanceExpectationsResultModel pe = new PerformanceExpectationsResultModel();
					
					pe.setPEID(rs.getString("PEID"));
					pe.setPerformanceExpectation(performanceExpectation);
					pe.setPercent(p * 100);
					
					PerformanceResults.add(pe);
				}
			}
			
			// Sort by percent
			Collections.sort(PerformanceResults, Collections.reverseOrder(new Comparator<PerformanceExpectationsResultModel>() {
				@Override
				public int compare(PerformanceExpectationsResultModel f1, PerformanceExpectationsResultModel f2) {
					
					return Float.compare(f1.getPercent(), f2.getPercent());
					
				}
			}));
			
			// Return matches
			model.addObject("PerformanceResults", PerformanceResults);
			
			model.addObject("Title", t.getTitle());
			model.addObject("Lesson", t.getLessonPlan());
			
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
}
