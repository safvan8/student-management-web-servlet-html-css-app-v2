package in.ineuron.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

public class DisplayOutput
{
	private static DisplayOutput displayOutput ;
	
	HttpServletResponse response = null;

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
}
