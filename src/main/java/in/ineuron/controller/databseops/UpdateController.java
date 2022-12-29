package in.ineuron.controller.databseops;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.model.AllQueryGenerator;
import in.ineuron.model.MySqlJdbcUtil;

public class UpdateController
{
	// allow Object creation of the class only once
	private static UpdateController updateController;
	
	private PreparedStatement preparedStatementforExistingDetails;
	
	// object to generate queries 
	private AllQueryGenerator allQueryGenerator = AllQueryGenerator.getAllQueryGenerator();
	
	private UpdateController()
	{
		// restricting Object creation outside class
	}

	// allow Object creation of the class only once
	public static UpdateController getAllQueryGenerator()
	{
		if (updateController == null)
		{
			updateController = new UpdateController();
			return updateController;
		} else
			return updateController;
	}

	// method for displaying existing student details before updating
	public void showExistingStudentDetailsBeforeUpdate(Connection connection, String dbOperation,
			HttpServletRequest request, HttpServletResponse response, String existingDetailsSqlQuery) throws IOException
	{
		System.out.println(connection);
		if (connection != null)
		{
			// getting preparedStatement for displaying existing student details
			preparedStatementforExistingDetails = MySqlJdbcUtil.getPreparedStatement(connection, existingDetailsSqlQuery);
			
			// setting user input values to the ExsistingDetailsUpdateQuery
			preparedStatementforExistingDetails = allQueryGenerator.SetUserInput_ExsistingDetailsUpdateQuery(request, dbOperation, preparedStatementforExistingDetails);
		}
	

		// to display response in the browser screen
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		out.println("<html> <body>");

		out.println("<h1>" + preparedStatementforExistingDetails + "</h1>");

		out.println(" </body></html>");

	}
}
