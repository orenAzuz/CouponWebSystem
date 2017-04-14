package facade;

import java.sql.SQLException;
import java.util.ArrayList;
import core.Company;
import core.Customer;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;
import dbDAO.CompanyDBDAO;
import dbDAO.CustomerDBDAO;
/**
 * This is the class that director all the manager possibility
 * in this system of the coupon project .
 * @author user
 *
 */
public class AdminFacade implements CouponClientFacade{
	
	private String name= "admin";
	private String password = "1234";
	
	private CustomerDBDAO customerDBDAO = new CustomerDBDAO();
	
	private CompanyDBDAO companyDBDAO = new CompanyDBDAO();
	private Company company = new Company();

	/**
	 * this method gives instruction for {@link CompanyDBDAO}class
	 * to set a company to the DB.it receiving a company instance and 
	 * conductor it to the createCompany method in {@link CompanyDBDAO} class
	 * @param company
	 */
	public void createCompany(Company company) {
		
		try {
		
			companyDBDAO.createCompany(company);

		} catch (ClassNotFoundException | SQLException | InterruptedException | DuplicateException e) {
			
			System.err.println(e.getMessage());
		}
		/**
		 * this method gives instruction for {@link CompanyDBDAO}class
		 * to remove a company from the DB.it receiving a company instance and 
	     * conductor it to the removeCompany method in {@link CompanyDBDAO} class			
		 */
	}
	public void removeCompany(Company company){
		
		try {
			companyDBDAO.removeCompany(company);
		} catch (ClassNotFoundException | SQLException | InterruptedException | NotAvailableException e) {
			
			System.err.println(e.getMessage());
		}
	}
	/**
	 * this method gives instruction for {@link CompanyDBDAO}class
	 * to update a company in the DB it receiving a company instance and 
	 * conductor it to the update method in {@link CompanyDBDAO} class
	 * @param company
	 */
	public void updateCompany(Company company){
		try {
			companyDBDAO.upDateCompany(company);
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			
			System.err.println(e.getMessage());
		}
		
	}
	/**
	 * this method gives instruction for {@link CompanyDBDAO}class
	 * to extract all the companies that exist in the DB .
	 * @return allCompanys {@link ArrayList}.
	 */
	public ArrayList<Company> getAllCompanies(){
		ArrayList <Company>allCompanys = new ArrayList<>();

		try {
			allCompanys.addAll( companyDBDAO.getAllCompanies());
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			
			System.err.println(e.getMessage());
		}
		
		return 	allCompanys;

		
	}
	/**
	 * this method gives instruction for {@link CompanyDBDAO}class
	 * to extract a company from DB by receiving long id and 
	 * conductor it to the getCompanyById method in {@link CompanyDBDAO}class
	 * @param id
	 * @return company
	 */
	public Company  getCompanyById(long id){
		
		try {
			 company =companyDBDAO.getCompanyById(id);
			
		} catch (ClassNotFoundException | SQLException | InterruptedException | NotAvailableException e) {
			
			System.err.println(e.getMessage());
		}
		return company;
		
	}
	/**
	 * this method gives instruction for {@link CustomerDBDAO}class
	 * to set a customer to the DB.it receiving a customer instance and 
	 * conductor it to the creatCustomer method in {@link CustomerDBDAO} class
	 * @param customer
	 */
	public void createCustomer(Customer customer) {
		
		try {
			customerDBDAO.creatCustomer(customer);
		} catch (ClassNotFoundException | InterruptedException | SQLException | DuplicateException e) {
			
			System.err.println(e.getMessage());
		}
		
	}
	/**
	 * this method gives instruction for {@link CustomerDBDAO}class
		 * to remove a customer from the DB.it receiving a customer instance and 
	     * conductor it to the removeCustomer method in {@link CustomerDBDAO} class	
	 * @param customer
	 */
	public void removeCustomer(Customer customer) {
		
		try {
			customerDBDAO.removeCustomer(customer);
		} catch (ClassNotFoundException | SQLException | InterruptedException | NotAvailableException e) {
			
			System.err.println(e.getMessage());
		}
	/**
	 * this method gives instruction for {@link CustomerDBDAO}class
	 * to update a customer in the DB it receiving a customer instance and 
	 * conductor it to the update method in {@link CustomerDBDAO} class	
	 */
	}
	public void updateCustomet(Customer customer){
		
		try {
			customerDBDAO.updateCustomer(customer );
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			
			System.err.println(e.getMessage());
		}			
	}
	/**
	 * this method gives instruction for {@link CustomerDBDAO}class
	 * to extract a customer from DB by receiving long id and 
	 * conductor it to the getCustomer method in {@link CustomerDBDAO}class
	 * @param id
	 * @return customer
	 */
	public Customer getCustomer(long id){
		Customer customer = new Customer();
		try {
			customerDBDAO.setSqlId(id);
			customer =customerDBDAO.getCustomerById(id);
		} catch (ClassNotFoundException | SQLException | InterruptedException | NotAvailableException e) {
			
			System.err.println(e.getMessage());
		}
		
		return customer;
	}
	/**
	 * this method gives instruction for {@link CustomerDBDAO}class
	 * to extract all the customers that exist in the DB .
	 * @return allCustomer {@link ArrayList}.
	 */
	public ArrayList<Customer> getAllCustomer(){
		ArrayList<Customer> allCustomer = new ArrayList<>();
		try {
		allCustomer.addAll(customerDBDAO.getAllCustomer()) ;
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			
			System.err.println(e.getMessage());
		}
		
		return allCustomer;
	}
	/**
	 * This login method is comparing the name and password String values which
	 * received in the signature of the the method to the local fields name and password
	 * and returning a boolean answer.
	 */
	@Override
	public boolean login(String name, String password, String clientType) {
		
		if(this.name.equalsIgnoreCase(name)&&this.password.equalsIgnoreCase(password)){
			return true;
		}
		return false;
	}
	

}    
