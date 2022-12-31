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
	
	// to convert sqlDate into string "yyyy-MM-dd" : accepted in html forms
	public String getHtmlFriendlyStringDate( java.sql.Date sqlDate)
	{
		// getting sqlDate and Converting to utilDate , html accepts util date only
		java.util.Date utilDob = new java.util.Date(sqlDate.getTime());

		// Create a SimpleDateFormat object with the desired date format
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// Use the format() method of the SimpleDateFormat object to convert the
		// java.util.Date object to a string
		 String  htmlFriendlyStringDate = dateFormat.format(utilDob);
		 
		 return htmlFriendlyStringDate;
	}
}
