package dbDAO;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import core.Coupon;
import core.CouponType;
import core.Customer;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;
import customExceptions.PurchaseFailedException;
import customExceptions.RanOutOfStockException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * The class {@link CustomerDBDAO} is the class that do all the required access
 * work with DB using the methods below.
 * implements {@link CustomerDAO} and apply the following methods.
 * this class using the {@link SqlQueries} class for all the SQL queries 
 * in all methods.
 * @author user
 */
public class CustomerDBDAO implements CustomerDAO {
	
	private long sqlId;
    CoupList <Coupon>coupList = new CoupList<>();

	public long getSqlId() {
		return sqlId;
	}

	/**
	 * This setter is informing the customer id to the sqlId member.
	 * @param sqlId
	 */
	public void setSqlId(long sqlId) {
		this.sqlId = sqlId;
	}
/**
	 * creatCompany method is receiving a company instance and set it to the DB.
	 *  {@link Creator}class first checking in front of DB if the customer
	 *  already exist in case the response is true it will throw an {@link DuplicateException}
	 *  otherwise {@link Creator}class will set it to DB and return the id number
	 *  that the DB adjusted to the new customer and save it in sqlId member.
	 */
	@Override
	public void creatCustomer(Customer customer) throws ClassNotFoundException, InterruptedException, SQLException, DuplicateException{
		
		boolean result = Creator.check(String.format(SqlQueries.customerByCustName, customer.getCustName()));
		
		if (result) {
			throw new DuplicateException("the customer "+customer.getCustName()+" is exist!!");
		} else {

		sqlId = Creator.creat(String.format(SqlQueries.insertCustomerToDB, customer.getCustName()
				,customer.getPassword()));
		}

	}
/**
	 * This method removing all the customer records including coupons that in the customer ownership.
	 * {@link SqlExecute}class checking in front of the DB if the customer exist
	 * in case that the response is false it will throw an {@link DuplicateException}
	 * otherwise it will remove the require customer and he's coupons from all
	 * the tables in DB.
	 * than return an announcement that this particularly customer has removed.
	 */
	@Override
	public void removeCustomer(Customer customer) throws ClassNotFoundException,
	SQLException, InterruptedException, NotAvailableException {

		long id = 0;	
		long result = SqlExecute.check(String.format
				(SqlQueries.customerIDByCustName, customer.getCustName()));
	
		  if (result!=0){  
			id = result;
			
			SqlExecute.execute(String.format(SqlQueries.removeCustomerCouponByCustID, id));
			SqlExecute.execute(String.format(SqlQueries.removeCustomerByID,id));		
			System.out.println("Customer name " + customer.getCustName() + " has removed.");
			
		} else {
			throw new NotAvailableException("The required customer name "+customer.getCustName()
			+" is not found or not exist!");
		}
	}

/**
 * this method is updating the password of the customer
 * using fields that received in the customer instance according to the customer name
 * that also received in the same instance.
 * it will only send a String quarry to {@link SqlExecute}class that do
 * all the access work with the DB.
 */
	@Override
	public void updateCustomer(Customer customer) throws ClassNotFoundException,
	SQLException, InterruptedException {

		SqlExecute.execute(String.format(SqlQueries.updateCustomer,
				customer.getPassword(),customer.getCustName()));
		
	}		

/**
 * This method is returning a customer instance from the DB according to the
 * id that received in the method signature.
 * first it will authenticates the id in front of the DB to prevent an exception
 * that could collapse the system in the case there is no customer with the same id 
 * and try to point it through an {@link ArrayList}.
 * if the {@link SqlExecute} check method will return a positive reaction.
 * the {@link CoupList} will fill the allCustomer ArrayList and than the Customer
 * will point on the first customer instance and be returned.
 * In case the {@link SqlExecute} check method will return a negative reaction
 * it will throw {@link NotAvailableException}
 * {@link ArrayList} member.
 * @throws NotAvailableException 
 */
	@Override
	public Customer getCustomerById(long id) throws ClassNotFoundException, SQLException, InterruptedException, NotAvailableException {
		
		long i = SqlExecute.check(String.format(SqlQueries.customerByID, id));
		if(i == 0){
			throw new NotAvailableException("The customer is not found insert right id !");
		}
		CustList<Customer> custList = new CustList<>();
		ArrayList <Customer>allCustomers = custList.executeQuery 
				(String.format(SqlQueries.customerByID, id));	
		Customer cust = allCustomers.get(0);

		return cust;
	}

    /**
     * this method is extract all the customers that exist in the DB
     * using the {@link CoupList} customers method by sending it a
     * a query .
    */
	@Override
	public ArrayList<Customer> getAllCustomer() throws ClassNotFoundException,
	SQLException, InterruptedException {
		
        CustList <Customer>cl = new CustList<>();
		ArrayList<Customer> allCustomer = cl.executeQuery
				(String.format(SqlQueries.allCustomers));

		return allCustomer;

	}
/**
 * This method returning the specific coupons that the requesting customer
 * the real access work with the DB is in the {@link CoupList}class
 * which return the desirable coupons according the query.
 */
	@Override
	public ArrayList<Coupon> getCoupons(Customer customer)
			throws ClassNotFoundException, SQLException, InterruptedException {
	    	
		    ArrayList<Coupon> myCoupons = new ArrayList<>();
			myCoupons.addAll(coupList.executeQuery(String.format(SqlQueries.getCouponsByCustName,
			customer.getCustName())));
		
    return myCoupons;
	}
/**
 * this method is divided to four methods to make all the progress of purchasing coupon
 * be readable. first it will check the existing of the coupon and throw a {@link PurchaseFailedException}
 * if it's not been found in DB else it will keep going to the next method.
 * @param coupon
 * @throws ClassNotFoundException
 * @throws SQLException
 * @throws InterruptedException
 * @throws RanOutOfStockException
 * @throws PurchaseFailedException
 */
	public void purchaseCoupon(Coupon coupon) throws ClassNotFoundException, SQLException,
	    InterruptedException, RanOutOfStockException, PurchaseFailedException {
		
		boolean result1 = Creator.check(String.format(SqlQueries.couponByTitle, coupon.getTitle()));
		          
		if (result1) 
		   {	
		   checkIfCustomerOwnTheCoupon(coupon);	
		   }
	   else
	       {	   
		   throw new PurchaseFailedException("Wrong Coupon title or not exist in the system");
		   }
	     }
/**
 * This is the second method of the purchaseCoupon progress.
 * now the check is if the customer own this coupon in case he dose
 * it will throw {@link PurchaseFailedException} the system not allowing 
 * to purchase coupon more than ones.
 * @param coupon
 * @throws ClassNotFoundException
 * @throws InterruptedException
 * @throws SQLException
 * @throws RanOutOfStockException
 * @throws PurchaseFailedException
 */
	public void checkIfCustomerOwnTheCoupon (Coupon coupon) throws ClassNotFoundException,
	            InterruptedException, SQLException, RanOutOfStockException, PurchaseFailedException{
		
		boolean result = Creator.check(String.format(SqlQueries.ifCustomerOwnCoupon,
				sqlId,coupon.getTitle()));	
		
		if (result) 
		   {	
		   throw new PurchaseFailedException(" You can't purchase coupon more than once! ");
		   }   
		else 
		   {	
		   checkAmountAndDate(coupon);
	       }
	     }
	/**
	 * This is the third method on the purchase coupon progress.
	 * this method check that the coupon date is not expired and there is on stuck
	 * and throws specific exceptions on each one when it turns out as positive
	 * result. 
	 * @param coupon
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 * @throws SQLException
	 * @throws RanOutOfStockException
	 */
	public void checkAmountAndDate(Coupon coupon) throws ClassNotFoundException,
	InterruptedException, SQLException, RanOutOfStockException{

		  Connection con = ConnectionPool.getInstance().getConnection();
		  Statement stmt = con.createStatement();
		 
		  int amount = 0;
		  long id = 0;
		  Date date = new Date();
		  Date todayDate = new Date();
		  ResultSet res = stmt.executeQuery(String.format(SqlQueries.couponAmountEndDateId,coupon.getTitle()));
		
		if (res.next()) {
			amount = res.getInt("AMOUNT");
			date = res.getDate("END_DATE");
			id = res.getLong("ID");
		}
		
		if (amount == 0) {
		   throw new RanOutOfStockException("Sorry the coupon "+coupon.getTitle()+" is ran out of stock...");
		   } else {
			
		if (date.before(todayDate)) {
		   throw new RanOutOfStockException("Sorry the coupon "+coupon.getTitle()+" is expired...");
		   } else {
				finallyPurchaseCoupon(coupon, id, amount);
		   }
	    }
	}
	/**
	 * This is the last method of the purchase coupon progress.
	 * this is the method that finally giving the customer ownership on
	 * the coupon by binding the coupon id and the customer id in Cusotmer_Coupon
	 * table in the DB it also subtracting the amount of the coupon in 1.		
	 * @param coupon
	 * @param id
	 * @param amount
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 */
	public void finallyPurchaseCoupon(Coupon coupon,long id,int amount) throws SQLException,
	        ClassNotFoundException, InterruptedException{

		    Creator.creat(String.format(SqlQueries.purchaseCouponDB, sqlId,id));
		    Creator.creat(String.format(SqlQueries.setAmount, (amount - 1),coupon.getTitle()));	
			System.out.println("congratulation You just Purchased a new coupon  !");		
	}
/**
 * This method returning a coupon {@link ArrayList} that owned the specific
 * customer that active this method all the logic access job in front of the DB
 * conducted through {@link CoupList} class which eventually returning
 * coupons {@link ArrayList}.
 * @return coupons {@link ArrayList}.
 * @throws ClassNotFoundException
 * @throws SQLException
 * @throws InterruptedException
 */
	public ArrayList<Coupon> getAllPurchasedCoupon() throws ClassNotFoundException,
	       SQLException, InterruptedException {

		    ArrayList<Coupon> allPurchasedCoupon = new ArrayList<>();
			allPurchasedCoupon.addAll(coupList.executeQuery(String.format
			(SqlQueries.allPurchaseCoupons, sqlId)));
		
		return allPurchasedCoupon;
	}
/**
 * 
 * This method returning a coupon {@link ArrayList} that the specific
 * customer that active this method ownership yet it will return only the 
 * same type of coupons that request. all the logic access job in front of the DB
 * conducted through {@link CoupList} class which eventually returning
 * coupon {@link ArrayList}
 * @param type
 * @return coupon {@link ArrayList}
 * @throws ClassNotFoundException
 * @throws SQLException
 * @throws InterruptedException
 */
	public ArrayList<Coupon> getAllPurchasedCouponByType(CouponType type)
			throws ClassNotFoundException, SQLException, InterruptedException {

		    ArrayList<Coupon> allTypeCoupon = new ArrayList<>();
			allTypeCoupon.addAll(coupList.executeQuery(String.format
					(SqlQueries.purchaseCouponsByType,type, sqlId)));
		
		return allTypeCoupon;
	}
/**
 * This method returning a coupon {@link ArrayList} that the specific
 * customer that active this method ownership yet it will return only the 
 * coupons up to the certain requested price. all the logic access job in front of the DB
 * conducted through {@link CoupList} class which eventually returning
 * coupon {@link ArrayList}
 * @param price
 * @return coupon {@link ArrayList}
 * @throws SQLException
 * @throws ClassNotFoundException
 * @throws InterruptedExceptiton
 */
	public ArrayList<Coupon> getAllPurchasedCouponByPrice(double price)
			throws SQLException, ClassNotFoundException, InterruptedException {
		
		ArrayList<Coupon> allCoupon = new ArrayList<>();		
		allCoupon.addAll(coupList.executeQuery(String.format(SqlQueries.gettingCouponsByPrice,price,sqlId)));
		
		return allCoupon;
	}

/**
 * this method is operate the login in front the DB using {@link SqlExecute} class.
 * it receiving a String values for name and password and 
 * authenticating those values than returning a boolean values according the result.
 * it also returning the id value and update the sqlId field in case the login succeeded.
 */
	@Override
	public boolean login(String custName, String password)
			throws ClassNotFoundException, SQLException, InterruptedException {


		long id = 0;
		id = SqlExecute.check(String.format(SqlQueries.customerloginExecute, custName,password));

		if (id != 0)
		{
			this.sqlId = id;	
			return true;
		}
		    return false;	
	    }
 }


