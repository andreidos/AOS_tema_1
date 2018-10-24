
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DataAccessLayer {
	
	private Connection con = null;
	private Statement stmt = null;
	private PreparedStatement prepStmt = null;
	
	DataAccesLayer(){
		createConnection();
	}
	
	private void createConnection()
	{
		try 
		{
			Class.forName("org.postgresql.Driver");
	    	con = DriverManager.getConnection("jdbc:postgresql://localhost/tema", "postgres","1q2w3e")
	    	con.setAutoCommit(false);
		} 
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void modifyUser(String usr, String email) throws MyException
	{
		if(usr.isEmpty() || email.isEmpty())
			throw new MyException("Complete all fields!!");
		createConnection();
		try
	    {
			prepStmt = con.prepareStatement("Insert into User(username, email) values(?,?)");
			prepStmt.setString(1, usr);
	        prepStmt.setString(2, email);
	        prepStmt.execute();
	        prepStmt.close();
	        con.commit();
	        con.close();
	    } 
	    catch (SQLException e) 
	    {
	    	e.printStackTrace();
	    }
	}
	

}
