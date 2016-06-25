package dictionary.muktobangla.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager
{
	Connection connection = null;
	Statement statement;
	
    public void Connect(String dbURL)
	{
		try{
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
		}
		catch(SQLException e){
		  System.err.println(e.getMessage());
		}
	}
	
	void InsertData(String insertQuery)
	{
		try{
			statement.executeUpdate(insertQuery);
		}
		catch(SQLException e){
			System.err.println(e.getMessage());
		}
	}
	
	void UpdateData(String updateQuery)
	{
		try{
			statement.executeUpdate(updateQuery);
		}
		catch(SQLException e){
			System.err.println(e.getMessage());
		}
	}
	
	void DeleteData(String deleteQuery)
	{
		try{
			statement.executeUpdate(deleteQuery);
		}
		catch(SQLException e){
			System.err.println(e.getMessage());
		}
	}
	
	public ResultSet GetResult(String resultQuery)
	{
		ResultSet rs=null;
		try{
			rs = statement.executeQuery(resultQuery);
		}
		catch(SQLException e){
			System.err.println(e.getMessage());
		}

		return rs;
	}
	
	void StopConnection()
	{
		try{
			if(connection != null)
				connection.close();
		}
		catch(SQLException e){
			System.err.println(e);
		}
	}
}
