package dbDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
/**
 * This is the class which provide connection to the DB and also close all the connection.
 * it's a singleTone type of class which means that there is only one appearance of this class.
 * @author user
 *
 */
public class ConnectionPool {
	
	private static ConnectionPool instance=null;
	private String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CouponProject;integratedSecurity=true;";  
	private Connection con;
	private boolean allowedConnecet = true;
	private Object key = new Object();
	private static final int forConnection=4;
	private Set<Connection>connections = new HashSet<Connection>();
	/**
	 * this is a private constructor it bounded to be private for blocking an option to
	 * get an appearance more than once.
	 * it also has the connections {@link Set} in it.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private ConnectionPool() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		
		for (int i=0;i<forConnection;i++)
		{
			connections.add( DriverManager.getConnection(connectionUrl));
		}

	}
	/**
	 * this getInstance method returning the instance of this class if the instance is been taken
	 * once it will provide the same instance again that how it remaining as a singleTone.
	 * it also a static method for the reason that it would be possible to get instance.
	 * it is also synchronized to tackle multiThread condition.
	 * @return instance
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static synchronized ConnectionPool getInstance() throws ClassNotFoundException, SQLException{
		
		if(instance==null){
			
			instance = new ConnectionPool();
		}
		return instance;
	}
	/**
	 * this getConnection method is assigned to supply connection to the DB to all comer
	 * it synchronized on a key object and in case there is no available connections it will 
	 * send the requesting thread to wait and when the connection is returning it also
	 * notify the waiting thread .
	 * eventually it returning the connection and remove it from the {@link Set}connections
	 * @return Connection
	 * @throws InterruptedException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection getConnection() throws InterruptedException, SQLException, ClassNotFoundException  {
		
		
			synchronized (key) {
				
				while(connections.isEmpty())
				{
					System.out.println("One moment please!");
					key.wait();
				}
				if(allowedConnecet==true){
				con = (Connection)connections.iterator().next();
				connections.remove(0);
			    System.out.println("Connect");
		        return con;
				}
				return null;
			}
	}
	/**
	 * this method is receiving a connection and set it back to the connections {@link Set}
	 * it also notify to the key object for the occasion that there is a thread that wait
	 * for connection.
	 * @param con
	 * @throws SQLException
	 */
	public void returnConnection (Connection con) throws SQLException{
		  
		synchronized (key) {
			connections.add(con);
			key.notify();
			System.out.println("The Connection has returned");
		
		}
		
		
	}
	/**
	 * this method is closing all the connections to the DB.
	 * first it will update the boolean allowedConnection to false than it will
	 * wait for 2 seconds to the connections to come back
	 * and than it will pass on all the connections {@link Set} and close 
	 * them one by one. it also synchronized on the key object for the reason
	 * that no one be able to take connection while it close them.
	 */
	public  void closeAllConection(){
		
			
			if(connections.size()<forConnection){
				
				allowedConnecet=false;
				
				try {
					key.wait(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
			synchronized(key){
				
		    while(connections.iterator().hasNext()){
		    	
			try {
				connections.iterator().next().close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		  }
	    }
	  }
}
