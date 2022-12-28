package in.ineuron.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

public class DisplayOutput
{
	private static DisplayOutput displayOutput ;
	

	private DisplayOutput()
	{
		// restricting class Object creation outside class
	}

	public static DisplayOutput getDisplayVisualsObj()
	{
		
		if (displayOutput == null)
		{
			displayOutput = new DisplayOutput();
			return displayOutput;
		}
		else
			return displayOutput;
		
	}

	// to display result of INSERTION OPERATION
	public void showInsertOperationsResult(HttpServletResponse response,Integer insertRowCount) throws IOException
	{
		System.out.println("\n showInsertOperationsResult method executing...");
		// to display response in the browser screen
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		out.println("<html> <body>");

		// to get successful/failed message
		if (insertRowCount > 0)
			out.println("<h1>" + "Student registartion Successful" + "</h1>");
		else
			out.println("<h1>" + "Student registartion failed" + "</h1>");

		out.println(" </body></html>");

	}
	
	// To Display result of READ Operation
	public void showReadOperationsResult(HttpServletResponse response, ResultSet resultSet) throws IOException
	{
		System.out.println("\n**showReadOperationsResult() method is executing");

		// to display response in the browser screen
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html> <body bgcolor='#F5FFFA'>");
		out.println("<h1> All Student details </h1>");
		out.println("<table width='500' border='3'> "
				+ "<caption style='caption-side:bottom'> Students List</caption>");
		out.println("<tr bgcolor='yellow'>"
				+ "<th>ID</th>"
				+"<th>NAME</th>"
				+ "<th>AGE</th>"
				+ "<th>GENDER</th>"
				+ "<th>MOB.No</th>");
	    
		
		try
		{
			// fetching results from resultSet
			while (resultSet.next())
			{
				// getting values from ResultSet one by one
				Integer id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				Integer age = resultSet.getInt(3);
				String gender = resultSet.getString(4);
				String mobileno = resultSet.getString(5);
				
				// Displaying data to user
				out.println("<tr>"
						+ "<td>"+id+"</td>"
						+ "<td>"+name+"</td>"
						+ "<td>"+age+"</td>"
						+ "<td>"+gender+"</td>"
						+ "<td>"+mobileno+"</td>");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		

		out.println(" </body></html>");
		
		
	}
}
