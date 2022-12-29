package in.ineuron.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.controller.databseops.InsertController;
import in.ineuron.controller.databseops.ReadController;
import in.ineuron.controller.databseops.UpdateController;
import in.ineuron.model.AllQueryGenerator;
import in.ineuron.model.MySqlJdbcUtil;
import in.ineuron.view.DisplayOutput;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException	;
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

	// object for insert operations
	InsertController insertController = InsertController.getInsertController();
	
	// Object for Read operations
	ReadController readController = ReadController.getReadController();
	
	// Object for Update operations
	UpdateController updateController = UpdateController.getAllQueryGenerator();

	//Object  of view component -- to display outputs to end user
	private DisplayOutput displayOutput = DisplayOutput.getDisplayVisualsObj();;

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
		System.out.println("Servlet initilization success");
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

			// calling method Student details insertion to DB
			insertController.runStudentInsertOperation(connection,dbOperation, request, response, sqlQuery);
		} else if (dbOperation.equals("read"))
		{
			// getting sql query based on the specified dbOperation
			String sqlQuery = allQueryGenerator.generateSqlQuery(dbOperation);

			System.out.println(sqlQuery);

			readController.runStudentReadOperation(connection, dbOperation, request, response, sqlQuery);
		} else if (dbOperation.equals("fetchingBeforeUpdate"))
		{
			System.out.println("hi");
			// generating sql query to fetch existing details of student based on student id
			String existingDetailsSqlQuery = allQueryGenerator.generateSqlQuery(dbOperation);
			
			System.out.println("existing details fetching using  :"+existingDetailsSqlQuery);
			
			updateController.showExistingStudentDetailsBeforeUpdate(connection, dbOperation, request, response, existingDetailsSqlQuery);
		}

	}
		
}
