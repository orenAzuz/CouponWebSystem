package dbDAO;

import java.sql.SQLException;
 
import java.util.ArrayList;

import core.Company;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;
/**
 * this interface class is designed to bound the implements class to apply the following method.
 * @author user
 *
 */
public interface CompanyDAO {

	/**
	 * this an {@link AbstractMethod} that bound to create company.
	 * @param company
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 * @throws DuplicateException
	 */
	public  void createCompany(Company company) throws SQLException, ClassNotFoundException, InterruptedException, DuplicateException;
	/**
	 * this an {@link AbstractMethod} that bound to remove company .
	 * @param company
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 * @throws NotAvailableException 
	 */
	public void removeCompany(Company company) throws ClassNotFoundException, SQLException, InterruptedException, DuplicateException, NotAvailableException;
    /**
     * this an{@link AbstractMethod} that bound to do an update for company.
     * @param company
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InterruptedException
     */
	public void upDateCompany(Company company) throws ClassNotFoundException, SQLException, InterruptedException;
	/**
	 * this is an {@link AbstractMethod} that bound to extract company from 
	 * DB by adjusting the receiving id in the signature to the id column in DB.
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 * @throws NotAvailableException 
	 */
	public Company getCompanyById(long id) throws SQLException, ClassNotFoundException, InterruptedException, NotAvailableException;
	/**
	 * this {@link AbstractMethod} is bounding to extract all the companies that exist in DB.
	 * @return Company{@link ArrayList} 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	public ArrayList<Company> getAllCompanies() throws ClassNotFoundException, SQLException, InterruptedException;
	/**
	 * this {@link AbstractMethod} is bounding to apply login in front of the DB
	 * @param compName
	 * @param password
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	public boolean login(String compName,String password) throws ClassNotFoundException, SQLException, InterruptedException;
		
		
	
	
		
	

}
