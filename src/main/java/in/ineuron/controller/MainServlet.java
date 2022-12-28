package in.ineuron.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.model.AllQueryGenerator;
import in.ineuron.model.MySqlJdbcUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebInitParam;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet(urlPatterns = { "/mainServlet" }, initParams = {
		@WebInitParam(name = "jdbcURL", value = "jdbc:mysql://localhost:3306/schooldbo"),
		@WebInitParam(name = "username", value = "root"), @WebInitParam(name = "passwd", value = "Safvan@123") })
public class MainServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private Connection connection;

	private AllQueryGenerator allQueryGenerator = AllQueryGenerator.getAllQueryGenerator();

	PreparedStatement preparedStatementForInsert;

	private int insertRowCount;

	static
	{
		System.out.println("servlet class loading success");
	}

	public MainServlet()
	{
		System.out.println("servlet  instantiation success");
	}

	@Override
	public void init()
	{
		System.out.println("Servlet initilization succes");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// finding which operation is requested by user from html file
		String dbOperation = request.getParameter("operation");

		// getting JDBC connection from util class
		connection = MySqlJdbcUtil.getmySqlJdbcConnection();

		if (dbOperation.equals("insert"))
		{

			// getting sql query based on the specified dbOperation
			String sqlQuery = allQueryGenerator.generateSqlQuery(dbOperation);

			if (connection != null)
			{
				// getting preparedStatement for insert operation from Util class
				preparedStatementForInsert = MySqlJdbcUtil.getPreparedStatement(connection, sqlQuery);

				// setting user input values to the insert query
				preparedStatementForInsert = allQueryGenerator.setUserInputValuesToPreparedStatement(request,
						dbOperation, preparedStatementForInsert);
				System.out.println("insert query values set successfull");

				insertRowCount = 0;
				if (preparedStatementForInsert != null)
				{
					try
					{
						insertRowCount = preparedStatementForInsert.executeUpdate();

					} catch (SQLException e)
					{
						e.printStackTrace();
					}
				}

			}
		}

		// to display response in the browser screen
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		out.println("<html> <body>");

		// to get successfull/failed message
		if (insertRowCount > 0)
			out.println("<h1>" + "Student registartion Successfull" + "</h1>");
		else
			out.println("<h1>" + "Student registartion failed" + "</h1>");

		out.println(" </body></html>");

	}

}
