package in.ineuron.controller.databseops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.ineuron.model.AllQueryGenerator;
import in.ineuron.model.MySqlJdbcUtil;
import in.ineuron.view.DisplayOutput;

public class UpdateController
{
	// allow Object creation of the class only once
	private static UpdateController updateController;
	
	// for fetching existing student details
	private PreparedStatement preparedStatementforExistingDetails;
	
	// object to generate queries 
	private AllQueryGenerator allQueryGenerator = AllQueryGenerator.getAllQueryGenerator();
	
	// to collect existing details
	private ResultSet resultSet;
	
	// Object of view component -- to display outputs to end user
	private DisplayOutput displayOutput = DisplayOutput.getDisplayOutput();
	
	// for updating existing student details
	private PreparedStatement preparedStatementForUpdate ;

	//to verify student record updated or not
	private int updateRowCount;
	
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
	public void showExistingStudentDetailsBeforeUpdate(Connection connection, HttpServletRequest request,
			HttpServletResponse response, String existingDetailsSqlQuery) 
	{
		System.out.println(connection);
		if (connection != null)
		{
			// getting preparedStatement for displaying existing student details
			preparedStatementforExistingDetails = MySqlJdbcUtil.getPreparedStatement(connection, existingDetailsSqlQuery);
			
			// setting user input values to the ExsistingDetailsUpdateQuery
			preparedStatementforExistingDetails = allQueryGenerator.setUserInput_ExsistingDetailsUpdateQuery(request,
					preparedStatementforExistingDetails);

			// executing select query
			if (preparedStatementforExistingDetails != null)
			{
				try
				{
					resultSet = preparedStatementforExistingDetails.executeQuery();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
				
				displayOutput.showExistingDetailsBeforeUpdate(response, resultSet);
			}
		}
	}

	// method for updating modification done on student details by user
	public void runStudentUpdateOperation(Connection connection, HttpServletRequest request,
			HttpServletResponse response, String updateQuery) 
	{
		if (connection != null)
		{
			// getting Prepared Statement Object from util class for updation
			preparedStatementForUpdate = MySqlJdbcUtil.getPreparedStatement(connection, updateQuery);

			// setting user entered values to the preparedStatement before execution
			preparedStatementForUpdate = allQueryGenerator.setUserInput_UpdateQuery(request,
					preparedStatementForUpdate);

			// clearing previoss row count values
			updateRowCount = 0;
			if (preparedStatementForUpdate != null)
			{
				try
				{ // executing update query
					updateRowCount = preparedStatementForUpdate.executeUpdate();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			displayOutput.showUpdateOperationsResult(response, updateRowCount);
		}
	}

}
