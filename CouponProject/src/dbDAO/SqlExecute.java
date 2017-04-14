package dbDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * The role of this class is to execute queries for this coupon project.
 *  using {@link BaseHeir}class.
 * @author user
 *
 */
public class SqlExecute {
	

	/**
	 * this method receiving a string for query send it to the DB and if 
	 * the result is positive it will return a long id value this is made
	 * usually for check existing and receiving an id value.
	 * using {@link BaseHeir}class.
	 * @param check
	 * @return long id value
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 */
	public static long check(String check) throws SQLException, 
	    ClassNotFoundException, InterruptedException{
		BaseHeir bh = new BaseHeir();

		long id = 0;
		
		ResultSet result = bh.statement().executeQuery(check);
		
		if(result.next()){
			id = result.getLong(1);
		}
		
		bh.returnConnectiot();
		return id;
	}
	/**
	 * This method is execute the type of queries which not return any comment
	 * such as remove or update kind of queries.
	 * it receiving string for the query.
	 * using {@link BaseHeir}class.
	 * @param quarry
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	public static void execute(String quarry) throws ClassNotFoundException,
	            InterruptedException, SQLException{
		
		 BaseHeir bh = new BaseHeir();
		 bh.statement().execute(quarry);
		 bh.returnConnectiot();
	}
}
