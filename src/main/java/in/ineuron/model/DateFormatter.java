package in.ineuron.model;

import java.text.SimpleDateFormat;

public class DateFormatter
{
	
	private static DateFormatter dateFormatter;
	
	// to restrict object creation from outside class
	private DateFormatter()
	{
	}

	// making class as single-ton
	public static DateFormatter getDateFormatter()
	{
		if (dateFormatter == null)
		{
			dateFormatter = new DateFormatter();
			return dateFormatter;
		} else
			return dateFormatter;
	}
	
	// to Convert String Date to SqlDate
	public java.sql.Date getSqlDate(String date)
	{
		java.sql.Date sqlDate = java.sql.Date.valueOf(date);
		
		return sqlDate;
	}
	
	// to convert SqlDate to user friendly format
	public String getUserFriendlyFormattedDate(java.sql.Date sqlDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		
		String userFriendlyDate = simpleDateFormat.format(sqlDate);
		
		return userFriendlyDate;
	}
}
