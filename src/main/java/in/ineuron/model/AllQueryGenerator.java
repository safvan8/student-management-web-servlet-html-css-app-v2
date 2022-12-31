package in.ineuron.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class AllQueryGenerator
{
	private static AllQueryGenerator allQueryGenerator;

	// Dateformatter object for all database Date related Conversions
	DateFormatter dateFormatter = DateFormatter.getDateFormatter();
	
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

	// this method will generate query for any type of db operation
	public String generateSqlQuery(String dbOperation)
	{
		if (dbOperation.equals("insert"))
		{
			// creating insert query
			String insertQuery = "INSERT INTO schooldbo.student (name,age,dob,gender,mobileno,city)  VALUES (?,?,?,?,?,?)";
			return insertQuery;
		} 
		else if (dbOperation.equals("read"))
		{
			// creating Select Query
			String readQuery = "SELECT id,name,age,ifnull(gender,'Not Disclosed'),mobileno FROM schooldbo.student";
			return readQuery;
		}
		// to generate query for fetching existing student details based on entered student id
		else if (dbOperation.equals("fetchingBeforeUpdate"))
		{
			// creating Select Query to get details based on student id
			String readQuery = "SELECT * FROM schooldbo.student where id = ?";
			return readQuery;
		}
		// Query for updating db with changes made by user based on sid
		else if (dbOperation.equals("update"))
		{
			// creating Select Query to UPDATE existing student details based on id
			String updateQuery = "UPDATE schooldbo.student "
					+ "SET name=?,age=?,gender=?,mobileno=?  "
					+ " WHERE id=?";
			return updateQuery;
		}
		// to generate delete query for deleting existing student records
		else if (dbOperation.equals("delete"))
		{
			String deleteQuery = "DELETE FROM schooldbo.student WHERE id=?";
			return deleteQuery;
		}

		return ""; // for remaining features
	}

	// this method is to Set user input values to the preparedSatetement for INSERT Operations
	public PreparedStatement setUserInputValuesToInsertPreparedStatement(HttpServletRequest request,PreparedStatement preparedStatement)
	{
			String dob = request.getParameter("dob");
			
			// getting values entered by the user from request Object
			String name = request.getParameter("name");
			Integer age = Integer.parseInt(request.getParameter("age"));
			
			// getting sqldate from String date
			java.sql.Date sqlDob = dateFormatter.getSqlDate(dob);
			
			String gender = request.getParameter("gender");
			String mobileNo = request.getParameter("mobileno").trim();
			String city = request.getParameter("city");

			// setting user input values into preparedStatement object
			try
			{
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, age);
				preparedStatement.setString(3, dob);
				preparedStatement.setString(4, gender);
				preparedStatement.setString(5, mobileNo);
				preparedStatement.setString(6, city);
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

	// this method is to Set student id to the preparedStatement for fetching  ExsistingDetails before updating
	public PreparedStatement setUserInput_ExsistingDetailsUpdateQuery(HttpServletRequest request,PreparedStatement preparedStatement)
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
	
	// this method is to Set user input values to the preparedSatetement for UPDATE Operations
	public PreparedStatement setUserInput_UpdateQuery(HttpServletRequest request,PreparedStatement preparedStatementForUpdate)
	{
					// getting values entered by the user from request Object
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					String name = request.getParameter("name");
					Integer age = Integer.parseInt(request.getParameter("age"));
					String gender = request.getParameter("gender");
					String mobileNo = request.getParameter("mobileno").trim();
					
					System.out.println(id+" "+name+" "+" "+age+" "+" "+gender+" "+" "+mobileNo+" ");
					
					try
					{
						// setting user input values into preparedStatement object
						preparedStatementForUpdate.setString(1, name);
						preparedStatementForUpdate.setInt(2, age);
						preparedStatementForUpdate.setString(3, gender);
						preparedStatementForUpdate.setString(4, mobileNo);
						
						preparedStatementForUpdate.setInt(5, id);
								
					} catch (SQLException e)
					{

						e.printStackTrace();
					}
					// returning Object after setting values
					return preparedStatementForUpdate;
	}
	
	
	// this method is to Set user specified student id to the preparedSatetement for DELETE Operations
	public PreparedStatement setUserInput_DeleteQuery(HttpServletRequest request,
			PreparedStatement preparedStatementForDelete)
	{
		// getting values entered by the user from request Object
		Integer id = Integer.parseInt(request.getParameter("id"));

		System.out.println("usesr enterd student id : " + id);

		try
		{ // setting user input values into preparedStatement object
			preparedStatementForDelete.setInt(1, id);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		// returning Object after setting values
		return preparedStatementForDelete;
	}
}
