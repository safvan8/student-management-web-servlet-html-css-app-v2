package in.ineuron.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class AllQueryGenerator
{
	private static AllQueryGenerator allQueryGenerator;

	private AllQueryGenerator()
	{
		// restricting Object creation outside class
	}

	// allow Object creation of the class only once
	public static AllQueryGenerator getAllQueryGenerator()
	{
		if (allQueryGenerator == null)
		{
			allQueryGenerator = new AllQueryGenerator();
			return allQueryGenerator;
		} else
			return allQueryGenerator;
	}

	public String generateSqlQuery(String dbOperation)
	{

		if (dbOperation.equals("insert"))
		{

			// creating insert query
			String insertQuery = "INSERT INTO schooldbo.student (name,age,gender,mobileno)  VALUES (?,?,?,?)";

			return insertQuery;
		} else if (dbOperation.equals("read"))
		{
			// creating Select Query
			String readQuery = "SELECT * FROM schooldbo.student";

			return readQuery;
		} else if (dbOperation.equals("update"))
		{
			// creating Select Query to get details based on student id
			String readQuery = "SELECT * FROM schooldbo.student where id = ?";

			return readQuery;
		}

		return ""; // for remaining features

	}

	public PreparedStatement setUserInputValuesToInsertPreparedStatement(HttpServletRequest request, String dbOperation,
			PreparedStatement preparedStatement)
	{
		// identifying user dbOperation
		if (dbOperation.equals("insert"))
		{
			// getting values entered by the user from request Object
			String name = request.getParameter("name");
			Integer age = Integer.parseInt(request.getParameter("age"));
			String gender = request.getParameter("gender");
			String mobileNo = request.getParameter("mobileno").trim();

			// setting user input values into preparedStatement object
			try
			{
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, age);
				preparedStatement.setString(3, gender);
				preparedStatement.setString(4, mobileNo);
			} catch (SQLException e)
			{
				e.printStackTrace();
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			// returning Object after setting values
			return preparedStatement;
		}
		return preparedStatement; // dummy

	}

	public PreparedStatement SetUserInput_ExsistingDetailsUpdateQuery(HttpServletRequest request, String dbOperation,
			PreparedStatement preparedStatement)
	{
		Integer id = Integer.parseInt(request.getParameter("id"));

		try
		{
			preparedStatement.setInt(1, id);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println("\nuser input set successfully to ExsistingDetailsUpdateQuery");

		return preparedStatement;
	}
}
