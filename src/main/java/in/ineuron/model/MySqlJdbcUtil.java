package in.ineuron.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlJdbcUtil
{
	private static Connection connection;
	private static PreparedStatement preparedStatement;

	public static Connection getmySqlJdbcConnection()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schooldbo", "root", "Safvan@123");

			System.out.println("JDBC connection established successfully");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

	// creating prepared Statement Object
	public static PreparedStatement getPreparedStatement(Connection connection, String query)
	{
		try
		{
			preparedStatement = connection.prepareStatement(query);
			System.out.println("preparedStatement created successfully");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return preparedStatement;
	}
}
