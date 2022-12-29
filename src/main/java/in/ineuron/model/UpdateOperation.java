package in.ineuron.model;

import java.io.PrintWriter;

public class UpdateOperation
{
	private static UpdateOperation updateOperation;

	private UpdateOperation()
	{
		// restricting Object creation outside class
	}

	// allow Object creation of the class only once
	public static UpdateOperation getAllQueryGenerator()
	{
		if (updateOperation == null)
		{
			updateOperation = new UpdateOperation();
			return updateOperation;
		} else
			return updateOperation;
	}

	public void showExistingStudentDetailsBeforeUpdate()
	{
		// to display response in the browser screen
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		out.println("<html> <body>");

		out.println("<h1>" + resultSet + "</h1>");

		out.println(" </body></html>");

	}
}
