package in.ineuron.controller;

import java.sql.Connection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.ineuron.controller.databseops.DeleteController;
import in.ineuron.controller.databseops.InsertController;
import in.ineuron.controller.databseops.ReadController;
import in.ineuron.controller.databseops.UpdateController;
import in.ineuron.model.AllQueryGenerator;
import in.ineuron.model.MySqlJdbcUtil;
import java.io.IOException;
import javax.servlet.annotation.WebInitParam;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet(loadOnStartup = 1, initParams = {
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
	
	// Object for Student details deletion
	DeleteController deleteController = DeleteController.getDeleteController();


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

			System.out.println("\ngenerated query "+sqlQuery);
			
			// calling method Student details for inserting Student details to DB
			insertController.runStudentInsertOperation(connection, request, response, sqlQuery);
		}
		else if (dbOperation.equals("read"))
		{
			// getting sql query based on the specified dbOperation
			String sqlQuery = allQueryGenerator.generateSqlQuery(dbOperation);

			System.out.println("\ngenerated query "+sqlQuery);

			readController.runStudentReadOperation(connection, request, response, sqlQuery);
		}
		// to display existing student in details in the form to user
		else if (dbOperation.equals("fetchingBeforeUpdate"))
		{
			// generating sql query to FETCH existing student details of student based on student id
			String existingDetailsSqlQuery = allQueryGenerator.generateSqlQuery(dbOperation);
			
			System.out.println("existing details fetching using  :"+existingDetailsSqlQuery);
			
			updateController.showExistingStudentDetailsBeforeUpdate(connection, request, response, existingDetailsSqlQuery);
		}
		// for updating the student details with values entered by user
		else if (dbOperation.equals("update"))
		{
			// generating sql query to UPDATE existing student details based on student id
			
			String updateQuery = allQueryGenerator.generateSqlQuery(dbOperation);
			System.out.println(updateQuery);
			
			updateController.runStudentUpdateOperation(connection,request, response, updateQuery);
		}
		
		else if (dbOperation.equals("delete"))
		{
			 // generating sql query to DELETE existing student details based on student id
			String deleteQuery = allQueryGenerator.generateSqlQuery(dbOperation);
			System.out.println("generated query" +deleteQuery);
			
			deleteController.runStudentDeleteOperation(connection, request, response, deleteQuery);
		}
	}		
}
