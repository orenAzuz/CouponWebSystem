package dbDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import core.Customer;

public class CustList<T> extends BaseDB<T>{
	/**
	 * This method is an override method . in this particular class it returning 
	 * a customer {@link ArrayList} from the DB according to the
	 * query that received in the method signature.
	 * Working with a database is done from the {@link BaseDB}class.
	 * the new customer instance update it's fields with this values.
	 * next step will be the creating a new {@link CustomerDBDAO} instance to
	 * informing it's sqlId member with the same customer id that came out .
	 * in it the progress that execute first the customer values that
	 * comes out from the DB is setting in a new customer instance fields 
	 * than the customer will be filed in it coupons {@link ArrayList} using
	 * getAllPurchasedCoupon() method.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<T> production() throws ClassNotFoundException,
	       SQLException, InterruptedException {
		
		   ArrayList<Customer>allCustomers = new ArrayList<>();
		   
    while (rs.next()) {
			
			long id = rs.getLong("ID");
			String name = rs.getString("CUST_NAME");
			String password = rs.getString("PASSWORD");
			Customer cust = new Customer(id, name.trim(), password.trim());
			CustomerDBDAO custDB = new CustomerDBDAO();
			custDB.setSqlId(id);
			cust.setCoupon(custDB.getAllPurchasedCoupon());
			allCustomers.add (cust);

		}
		return (ArrayList<T>) allCustomers;
	}
	
	

}
