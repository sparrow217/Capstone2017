<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Results</title>
		<style>
			table, th, td {
			    border: 1px solid black;
			}
		</style>
	</head>
	
	<body>
	<h1>Framework Matches:</h1>
	<table style="width:100%">
		<tr>
	    	<th>Dimension</th>
	    	<th>FrameworkElement</th> 
	    	<th>frameworkSubelement</th>
		</tr>
		<c:if test="${not empty frameworkResults}">
		    	<c:forEach var = "r" items = "${frameworkResults}">
			    	<tr>
			        	<td>${r.dimension}</td>
			        	<td>${r.frameworkElement}</td>
			        	<td>${r.frameworkSubelement}</td>
			        </tr>
		        </c:forEach>
		</c:if>
	</table>
	
	<h1>Performance Expectation Matches:</h1>
	<table style="width:100%">
		<tr>
	    	<th>PEID</th>
	    	<th>PerformanceExpectation</th> 
		</tr>
		<c:if test="${not empty PerformanceResults}">
		    	<c:forEach var = "r" items = "${PerformanceResults}">
			    	<tr>
			        	<td>${r.PEID}</td>
			        	<td>${r.performanceExpectation}</td>
			        </tr>
		        </c:forEach>
		</c:if>
	</table>
	
	<h1>Framework Matched With Performance Expectation</h1>
	<table style="width:100%">
		<tr>
	    	<th>PEID</th>
	    	<th>frameworkSubelement</th> 
		</tr>
		<c:if test="${not empty frameXExpecResults}">
		    	<c:forEach var = "r" items = "${frameXExpecResults}">
			    	<tr>
			        	<td>${r.PEID}</td>
			        	<td>${r.frameworkSubelement}</td>
			        </tr>
		        </c:forEach>
		</c:if>
	</table>
	
	</body>
</html>