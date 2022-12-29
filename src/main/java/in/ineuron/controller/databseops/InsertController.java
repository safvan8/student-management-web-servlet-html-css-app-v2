package in.ineuron.controller.databseops;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.model.AllQueryGenerator;
import in.ineuron.model.MySqlJdbcUtil;
import in.ineuron.view.DisplayOutput;

public class InsertController
{
	
	private PreparedStatement preparedStatementForInsert;
	
	private Integer insertRowCount;
	
	// object to generate queries 
	private AllQueryGenerator allQueryGenerator = AllQueryGenerator.getAllQueryGenerator();
	
	// to allow object creation only once -- instance var
	private static InsertController insertController;
	
	private InsertController()
	{
		// restricting Object creation outside class
	}
	
	public static InsertController getInsertController()
	{
		if (insertController == null)
		{
			insertController = new InsertController();
			
			return insertController;
		}
		else
		{
			return insertController;
		}
	}

	//Object  of view component -- to display outputs to end user
	private DisplayOutput displayOutput = DisplayOutput.getDisplayVisualsObj();;

	// for Student INSERTION to database
	public void runStudentInsertOperation(Connection connection,    String dbOperation, HttpServletRequest request, HttpServletResponse response,
			String sqlQuery)
	{
		if (connection != null)
		{
			// getting preparedStatement for insert operation from Util class
			preparedStatementForInsert = MySqlJdbcUtil.getPreparedStatement(connection, sqlQuery);

			// setting user input values to the insert query
			preparedStatementForInsert = allQueryGenerator.setUserInputValuesToInsertPreparedStatement(request, dbOperation,
					preparedStatementForInsert);
			System.out.println("user entered values Set to PreparedStatement successfull");

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
		try
		{
			// to display response in the browser screen -- passing response and row count
			// object
			displayOutput.showInsertOperationsResult(response, insertRowCount);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
