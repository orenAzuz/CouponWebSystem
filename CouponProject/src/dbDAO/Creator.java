package dbDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * The class of {@link Creator} is designed to create a new CouponProgect member 
 * can be a Coupon ,Company or customer it has too static method the first is for checking if the
 * member is exist and the second is to set it in the DB.
 * the cause of this methods been static is to enable to get them without creating instance 
 * @author user
 *
 */
public class Creator {
	/**
	 * this static method is checking if the requested instance is already appears in DB.
	 * if the {@link ResultSet} is come back with details so it will return true.
	 * the instance is exist in the DB if not than it will return false.
	 * it receiving a string value for the query and return a boolean answer.
	 * @param checkStatement
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	public static boolean check(String checkStatement) throws ClassNotFoundException,
	    InterruptedException, SQLException{
		
		BaseHeir bh = new BaseHeir();
		ResultSet rs = bh.statement().executeQuery(checkStatement);
		
		if (rs.next()){
			bh.returnConnectiot();
			return true;
		}	
		    bh.returnConnectiot();
		    return false;
	}

	/**
	 * This static method is assigned to receiving a string value for query and set the instance into DB
	 * in the midst of the creating in the DB it will extract the id value that was given automatically
	 * in the DB and  return the id value back 
	 * @param creatStatement
	 * @return long sqlId
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	public static long creat(String creatStatement) throws ClassNotFoundException,
	    InterruptedException, SQLException{

		long sqlId = 0;
		Connection con = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement
		(creatStatement,Statement.RETURN_GENERATED_KEYS);
		preparedStatement.executeUpdate();
		ResultSet rs =preparedStatement.getGeneratedKeys();

		if (rs.next())
		{
			 sqlId=rs.getLong(1);
		}
        ConnectionPool.getInstance().returnConnection(con);
		return sqlId;
	
		
		
	}
}
