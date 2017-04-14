package dbDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import core.Company;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;
/**
 * The class Company is the class that do all the required access
 * work with DB using the methods below.
 * implements {@link CompanyDAO} and apply the following methods.
 * this class using the {@link SqlQueries} class for all the SQL queries 
 * in all methods.
 * @author user
 *
 */
public class CompanyDBDAO implements CompanyDAO {

	private CompList<Company> cl = new CompList<>();
	private  long sqlId;
	/**
	 * this getter reveals the id according the company id in DB that informed in the sqlId member. 
	 * @return sqlId
	 */
public long getSqlId() {
	return sqlId;
}
/**
 * this setter is informing the company id to the sqlId member.
 * @param sqlId
 */
public void setSqlId(long sqlId) {
	this.sqlId = sqlId;
}
	/**
	 * creatCompany method is receiving a company instance and set it to the DB.
	 *  {@link Creator}class first checking in front of DB if the company
	 *  already exist in case the response is true it will throw an {@link DuplicateException}
	 *  otherwise {@link Creator}class will set it to DB and return the id number
	 *  that the DB adjusted to the new company and save it in sqlId member.
	 */
	@Override
	public void createCompany(Company company) throws SQLException, ClassNotFoundException
          , InterruptedException, DuplicateException {
	
	     boolean result = Creator.check(String.format(SqlQueries.getCompanyByName,
	    		 company.getCopmName()));
		
		 if(result)
		 {	 
		  throw new DuplicateException("the company "+company.getCopmName()+" is alredy exist!");
		 } 
		 else
		 {	 
		 sqlId = Creator.creat(String.format(SqlQueries.insertCompanyToDB,company.getCopmName()
				 ,company.getPassword(),company.getEmail() ));
				 
		 }
	  }
	/**
	 * This method removing all the company records including coupons that exist even in a
	 * customer ownership.
	 * {@link SqlExecute}class checking in front of the DB if the company exist
	 * in case that the response is false it will throw an {@link DuplicateException}
	 * otherwise it will remove the require company and it's marketed coupons from all
	 * the tables in DB.
	 * than return an announcement that this particularly company has removed.
	 */

	@Override
	public void removeCompany(Company company) throws ClassNotFoundException,
	     SQLException, InterruptedException, NotAvailableException {
		
		 long id = 0;
	    
		 long result = SqlExecute.check(String.format(SqlQueries.getCompIdByName,company.getCopmName()));
		
		 if(result!=0)
		 {
			id = result;
   
		 SqlExecute.execute(String.format(SqlQueries.removeCustomerCouponByCompID,id));
		 SqlExecute.execute(String.format(SqlQueries.removeCouponsByCompID,id));
		 SqlExecute.execute(String.format(SqlQueries.removeCompanyCouponByCompID,id));
		 SqlExecute.execute(String.format(SqlQueries.removeCompanyByName,company.getCopmName()));
		 
		 System.out.println("The company  "+company.getCopmName()+"  has removed.");
		 
		 }else{
			 throw new NotAvailableException("The required company name "+company.getCopmName()+
					 " is not found or not exist!");
		 }
	}
/**
 * this method is updating the password and the email of the company
 * using fields that received in the company instance according to the company name
 * that also received in the same instance.
 * it will only send a String quarry to {@link SqlExecute}class that do
 * all the work with the DB.
 */
	@Override
	public void upDateCompany(Company company) throws ClassNotFoundException, SQLException,
	InterruptedException {
	    
		SqlExecute.execute(String.format(SqlQueries.updateCompany,company.getPassword(),
				company.getEmail(),company.getCopmName()));
	}
/**
 * This method is returning a company instance from the DB according to the
 * id that received in the method signature.
 * than run a quarry to the DB using {@link CompList} class. extract values and
 * the new company instance update it's fields with this values
 * next step will be the creating a new {@link CouponDBDAO} instance and
 * informing it's compID member with the same company id that previous recalls.
 * finally the company instance will add all it's marked coupons into it's 
 * {@link ArrayList}member.
 * @throws NotAvailableException 
 */
	@Override
	public Company getCompanyById(long id) throws SQLException,
	ClassNotFoundException, InterruptedException, NotAvailableException {
		
		
		long i = SqlExecute.check(String.format(SqlQueries.getCmpanyByID,id));
		if(i == 0){
			throw new NotAvailableException("The company is not found insert right id !");
		}
		ArrayList<Company>allCompanys = cl.executeQuery
				(String.format(SqlQueries.getCmpanyByID,id ));
		Company  company = allCompanys.get(0);
		

		return company;
	}
    /**
     * this method is extract all the companies that exist in the DB
     * using {@link CompList}class which has all the logic work in front of
     * the DB.
     * return Company {@link ArrayList}.
     */
	@Override
	public ArrayList<Company> getAllCompanies() throws ClassNotFoundException,
	       SQLException, InterruptedException {
		ArrayList <Company>allCompanys = cl.executeQuery
				(String.format(SqlQueries.allCompanies));
		
		return allCompanys;
	}
/**
 * this method is operate the login in front the DB using {@link SQLException} class.
 * it receiving a String values for name and password and 
 * authenticating those values than returning a boolean values according the result.
 * it also returning the id value and update the sqlId field in case the {@link ResultSet}
 * is back with data.
 */
	@Override
	public boolean login(String compName, String password) throws ClassNotFoundException,
	    SQLException, InterruptedException {
		
		long id = 0;
		id = SqlExecute.check(String.format(SqlQueries.loginExecut,compName,password));

		if (id != 0)
		{
			this.sqlId = id;	
			return true;
		}
		    return false;	
	    }
     }



