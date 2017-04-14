package dbDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import core.Coupon;
import core.Customer;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;
/**
 * this interface class is designed to bound the implements class to apply the following method.
 * @author user
 *
 */
public interface CustomerDAO {
	/**
	 *  this an {@link AbstractMethod} that bound to create customer.
	 * @param customer
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 * @throws SQLException
	 * @throws DuplicateException
	 */
	public void creatCustomer(Customer customer) throws ClassNotFoundException, InterruptedException, SQLException, DuplicateException;
	/**
	 * this an {@link AbstractMethod} that bound to remove customer .
	 * @param customer
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 * @throws NotAvailableException 
	 */
	public void removeCustomer(Customer customer) throws ClassNotFoundException, SQLException, InterruptedException, NotAvailableException;
	/**
	 * this an{@link AbstractMethod} that bound to do an update for customer.
	 * @param customer
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	public void updateCustomer(Customer customer) throws ClassNotFoundException, SQLException, InterruptedException;
	/**
	 * this is an {@link AbstractMethod} that bound to extract customer from 
	 * DB by adjusting the receiving id in the signature to the id column in DB.
	 * @param id
	 * @return customer
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 * @throws NotAvailableException 
	 */
	public Customer getCustomerById(long id) throws ClassNotFoundException, SQLException, InterruptedException, NotAvailableException;
	/**
	 * this {@link AbstractMethod} is bounding to extract all the customers that exist in DB
	 * @return Customer {@link ArrayList}
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	public ArrayList<Customer> getAllCustomer() throws ClassNotFoundException, SQLException, InterruptedException;
	/**
	 * 	this {@link AbstractMethod} is bounding to apply login in front of the DB
	 * @param custName
	 * @param Password
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	public boolean login(String custName, String Password) throws ClassNotFoundException, SQLException, InterruptedException;

	ArrayList<Coupon> getCoupons(Customer customer) throws ClassNotFoundException, SQLException, InterruptedException;
	

}
