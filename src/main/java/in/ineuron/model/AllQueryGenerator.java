package in.ineuron.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class AllQueryGenerator
{
	private String name;
	private Integer age;
	private String gender;
	private Integer mobileNo;
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
			String insertQuery = "INSERT INTO schooldbo.student  ('name','age','gender','mobileno') VALUES (?,?,?,?)";

			return insertQuery;
		} else

			return "";

	}

	public PreparedStatement setUserInputValuesToPreparedStatement(HttpServletRequest request, String dbOperation,
			PreparedStatement preparedStatement)
	{
		// identifying user dbOperation
		if (dbOperation.equals("insert"))
		{
			// getting values entered by the user from request Object
			name = request.getParameter("name");
			age = Integer.parseInt(request.getParameter("age"));
			gender = request.getParameter("gender");
			mobileNo = Integer.parseInt(request.getParameter("mobileno"));

			// setting user input values into preparedStatement object
			try
			{
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, age);
				preparedStatement.setString(3, gender);
				preparedStatement.setInt(4, mobileNo);
			}
			catch (SQLException e)
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
}