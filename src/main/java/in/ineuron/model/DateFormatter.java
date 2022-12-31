package in.ineuron.model;

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
}
