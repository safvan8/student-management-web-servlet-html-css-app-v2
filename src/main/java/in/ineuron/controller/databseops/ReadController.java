package in.ineuron.controller.databseops;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.model.MySqlJdbcUtil;
import in.ineuron.view.DisplayOutput;

public class ReadController
{
	private PreparedStatement preparedStatementForRead;

	private ResultSet resultSet;
	
	//Object  of view component -- to display outputs to end user
	private DisplayOutput displayOutput = DisplayOutput.getDisplayVisualsObj();;

	// to allow object creation only once -- instance var
	private static ReadController readController;

	private ReadController()
	{
		// restricting Object creation outside class
	}

	public static ReadController getReadController()
	{
		if (readController == null)
		{
			readController = new ReadController();

			return readController;
		} else
		{
			return readController;
		}
	}
	
	// To READ All student details from Database
		public void runStudentReadOperation(Connection connection, HttpServletRequest request, HttpServletResponse response,
				String sqlQuery) throws IOException
		{
			if (connection != null)
			{
				// getting preparedStatement for insert operation from Util class
				preparedStatementForRead = MySqlJdbcUtil.getPreparedStatement(connection, sqlQuery);

				// user input not required for fetching complete Student details

				if (preparedStatementForRead != null)
				{

					try
					{
						// executing READ query and Collecting results to ResultSet
						resultSet = preparedStatementForRead.executeQuery();
					} catch (SQLException e)
					{
						e.printStackTrace();
					}

					displayOutput.showReadOperationsResult(response, resultSet);
				}

			}
		}
		

}
