package in.ineuron.model;

import java.io.PrintWriter;

public class UpdateOperationModel
{
	private static UpdateOperationModel updateOperationModel;

	private UpdateOperationModel()
	{
		// restricting Object creation outside class
	}

	// allow Object creation of the class only once
	public static UpdateOperationModel getAllQueryGenerator()
	{
		if (updateOperationModel == null)
		{
			updateOperationModel = new UpdateOperationModel();
			return updateOperationModel;
		} else
			return updateOperationModel;
	}

	public void showExistingStudentDetailsBeforeUpdate()
	{
//		// to display response in the browser screen
//		response.setContentType("text/html");
//
//		PrintWriter out = response.getWriter();
//
//		out.println("<html> <body>");
//
//		out.println("<h1>" + resultSet + "</h1>");
//
//		out.println(" </body></html>");

	}
}
