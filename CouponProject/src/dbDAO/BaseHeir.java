package dbDAO;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * This class is extends {@link BaseDB} it designed for functions that the {@link BaseDB}
 * cannot provide such as work with a static method.
 * @author user
 *
 */
@SuppressWarnings("rawtypes")
public  class BaseHeir extends BaseDB {
	
	/**
	 * This method is not in use in this class.
	 */
	@Override
	public ArrayList production() throws ClassNotFoundException,
      SQLException, InterruptedException {
		return null;
	}

	
}


