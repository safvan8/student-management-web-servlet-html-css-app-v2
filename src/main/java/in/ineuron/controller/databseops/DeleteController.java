package in.ineuron.controller.databseops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.model.AllQueryGenerator;
import in.ineuron.model.MySqlJdbcUtil;
import in.ineuron.view.DisplayOutput;

public class DeleteController
{
	// for deleting existing student deatils
	private PreparedStatement preparedStatementForDelete;
	
	
	// to allow object creation only once -- instance var
	private static DeleteController deleteController;

	// object to generate queries
	private AllQueryGenerator allQueryGenerator = AllQueryGenerator.getAllQueryGenerator();
	
	// rowcount deleted
	private int deleteRowCount;
	
	// Object of view component -- to display outputs to end user
	private DisplayOutput displayOutput = DisplayOutput.getDisplayOutput();

	private DeleteController()
	{
		// restricting object creation ouside class
	}
	
	// making class as singleton
	public static DeleteController getDeleteController()
	{
		if (deleteController == null)
		{
			deleteController = new DeleteController();
			return deleteController;
		} else
			return deleteController;
	}
	
	
	// method for delelting existing student details -- this method will perform DB operation
	public void runStudentDeleteOperation(Connection connection, HttpServletRequest request,
			HttpServletResponse response, String deleteQuery) 
	{
		// getting preparedStatement object from util class for deletion
		preparedStatementForDelete = MySqlJdbcUtil.getPreparedStatement(connection, deleteQuery);
		
		// setting user entered values to the preparedStatement before execution
		 preparedStatementForDelete = allQueryGenerator.setUserInput_DeleteQuery(request, preparedStatementForDelete);
		
			// clearing previous rowcount
			deleteRowCount = 0;
			try
			{ // executing delete Query
				deleteRowCount = preparedStatementForDelete.executeUpdate();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			System.out.println(deleteRowCount);
		 displayOutput.showDeleteOperationsResult(response, deleteRowCount);
	}
	
}
