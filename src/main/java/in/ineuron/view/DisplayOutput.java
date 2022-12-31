package in.ineuron.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import in.ineuron.model.DateFormatter;

public class DisplayOutput
{
	//  obj for any Date related Conversions
	DateFormatter dateFormatter = DateFormatter.getDateFormatter();
	
	// to make singleton class
	private static DisplayOutput displayOutput ;
	
	
	private DisplayOutput()
	{
		// restricting class Object creation outside class
	}

	public static DisplayOutput getDisplayOutput()
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
	public void showReadOperationsResult(HttpServletResponse response, ResultSet resultSet) 
	{
		System.out.println("\n**showReadOperationsResult() method is executing");

		// to display response in the browser screen
		response.setContentType("text/html");

		PrintWriter out = null;
		try
		{
			out = response.getWriter();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		out.println("<html> <body bgcolor='#F5FFFA'>");
		out.println("<h1> All Student details </h1>");
		out.println("<table width='500' border='3'> "
				+ "<caption style='caption-side:bottom'> Students List</caption>");
		out.println("<tr bgcolor='yellow'>"
				+ "<th>ID</th>"
				+"<th>Name</th>"
				+ "<th>Age</th>"
				+ "<th>Date of Birth</th>"
				+ "<th>Gender</th>"
				+ "<th>Mobile No.</th>"
				+ "<th>City</th>");
		
		try
		{
			// fetching results from resultSet
			while (resultSet.next())
			{
				// getting values from ResultSet one by one
				Integer id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				Integer age = resultSet.getInt(3);
				
				// getting Sql Date -- not user friendly
				java.sql.Date sqlDate = resultSet.getDate(4);
				// converting to user firiendly Date format
				String dob = dateFormatter.getUserFriendlyFormattedDate(sqlDate);
				
				String gender = resultSet.getString(5);
				String mobileno = resultSet.getString(6);
				String city = resultSet.getString(7);
				
				// Displaying data to user
				out.println("<tr>"
						+ "<td>"+id+"</td>"
						+ "<td>"+name+"</td>"
						+ "<td>"+age+"</td>"
						+ "<td>"+dob+"</td>"
						+ "<td>"+gender+"</td>"
						+ "<td>"+mobileno+"</td>"
						+ "<td>"+city+"</td>");
				
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		out.println(" </body></html>");
		
	}
	
	// To display existing details , before updating
	// this method will display form to user for original updation and saving 
	public void showExistingDetailsBeforeUpdate(HttpServletResponse response, ResultSet resultSet) 
	{
		System.out.println("\n**showExistingDetailsBeforeUpdate() method is executing");

		// to display response in the browser screen
		response.setContentType("text/html");

		PrintWriter out = null;
		try
		{
			out = response.getWriter();
		} catch (IOException e)
		{
			e.printStackTrace(); 
		}
		
		Integer id=0;
		String name = "";
		Integer age=0;
		String dob="";
		String gender="";
		String mobileno="";
		String city="";
		try
		{
			// fetching results from resultSet
			while (resultSet.next())
			{
				// getting values from ResultSet one by one
				id = resultSet.getInt(1);
				name = resultSet.getString(2);
				age = resultSet.getInt(3);
				
				// getting sqlDate	
				java.sql.Date sqlDob = resultSet.getDate(4);
				// converting sqlDob to "yyyy-MM-dd" string format : accepted in html forms
				dob = dateFormatter.getHtmlFriendlyStringDate(sqlDob);
				
				gender = resultSet.getString(5);
				mobileno = resultSet.getString(6);

			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		// displaying existing student details
		out.println("<html>"
				+ "<head>"
				+ "<title>Insert title here</title>"
				+ "</head>"
				+ "<body bgcolor='#FFFFE0'>"
				+ "	<h1>Update existing Student details</h1>"
				+ "	<h3 style=\"color: crimson;\">Update changes and save</h3>"
				+ "<h2>Student ID:"+id +"</h2>"
				+ "	<style>"
				+ "label {"
				+ "	display: block;"
				+ "	text-align: left;"
				+ "}"
				+ "</style>");

		// showing a form to user and Getting post request for update
		out.println("<form style='color: blue' action='./mainServlet' method='POST'>");
		
		out.print("<input type='hidden' name='operation' value='update'>" + 
				"<input type='hidden' name='id' value="+id +"><br>" + 
				
		"<label for='name'>Name of Student: </label>"  +

		"<input type='text' name='name' value="+name +"><br>" +

		"<label for='age'>Age: </label> <input type='text' id='age' name='age' value="+age +" >" +
		
		"<label for='dob'>Date of Birth: </label> <input type='date' id='dob' name='dob' value="+dob +" ><br>");
		
		out.println("<label for='gender'>Gender: </label> "+ 
				 
				"<select name=gender multiple >");
				
				out.println("<option value='Male'>Male</option>" +
				"<option value='Female'>Female</option>" +
				"<option value='Other'>Other</option>" +
		
			"</select> <br> <label for='mobileno'>Mobile No:</label>" + 
			"<input type='text' id='mobileno' name='mobileno' value="+mobileno +"><br> <br>" + 
			
			"</select> <br> <label for='city'>City:</label>" + 
			"<input type='text' id='city' name='city' value="+city +"><br> <br>" + 
			
			"<input type='submit' value='Save Changes'>" +
			
			"</form>" +
			"</body>" +
			"</html>"  );				
	}
	
	// to display Final result of UPDATE OPERATION
	public void showUpdateOperationsResult(HttpServletResponse response, Integer updateRowCount)
	{
		System.out.println("\n showUpdateOperationsResult method executing...");
		// to display response in the browser screen
		response.setContentType("text/html");

		PrintWriter out = null;
		
		try
		{
			out = response.getWriter();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		out.println("<html> <body>");

		// to get successful/failed message
		if (updateRowCount > 0)
			out.println("<h1>" + "Student Detais updated successfuly" + "</h1>");
		else
			out.println("<h1>" + "Student details update failed" + "</h1>");

		out.println(" </body></html>");
	}

	// to display Final result of DELETE OPERATION	
	public void showDeleteOperationsResult(HttpServletResponse response, Integer deleteRowCount)	
	{
		System.out.println("\n showDeleteOperationsResult method executing...");
		
		// to display response in the browser screen
		response.setContentType("text/html");
		
		PrintWriter out = null;
				try
		{
			out = response.getWriter();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		out.println("<html> <body>");

		// to get successful/failed message
		if (deleteRowCount > 0)
			out.println("<h1>" + "Student record Deleted successfuly" + "</h1>");
		else
			out.println("<h1>" + "Student record deletion failed" + "</h1>");

		out.println(" </body></html>");
	}
}
