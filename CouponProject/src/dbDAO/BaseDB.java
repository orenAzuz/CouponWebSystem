package dbDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This class is assigned to extract companies,coupons or customers
 * from the DB this is an abstract class  which has 2 methods for
 * connect and return one abstract method and one execute method it
 * similar works as a template method. 
 * @author user
 *
 * @param <T>
 */
public abstract class BaseDB<T> {
	
	Connection con;
	ResultSet rs ;
	/**
	 * The role of this method to produce connection and {@link Statement}
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 */
	public Statement statement() throws SQLException,
	    ClassNotFoundException, InterruptedException
	  {	
		con = ConnectionPool.getInstance().getConnection();
		Statement stmt = con.createStatement();
	    return stmt ;
	  }
	/**
	 * This is an abstract method that any successor will exercise in accordance
	 * with. Role to implement specific extract from data base.
	 * @return ArrayList<T>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	public abstract  ArrayList<T> production() throws
	        ClassNotFoundException, SQLException, InterruptedException;
	/**
	 * The role of this method to return the connection.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void returnConnectiot() throws 
	    ClassNotFoundException, SQLException
	{
	    ConnectionPool.getInstance().returnConnection(con);
	}
	/**
	 * This method is the execute of all the heirs. it's final so it can't be override
	 * and when they running this method all they have to do is to actualize the 
	 * production method to their specific needs.
	 * @param query
	 * @return ArrayList<T>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	public  final ArrayList<T> executeQuery(String query) throws ClassNotFoundException,
	    SQLException, InterruptedException{
		
		ArrayList<T> core = new ArrayList<>();
	    rs = statement().executeQuery(query);
	    core = production();
		returnConnectiot();
		return core;
	}

}
