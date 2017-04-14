package facade;

import java.sql.SQLException;
import java.util.ArrayList;
import core.Coupon;
import core.CouponType;
import customExceptions.PurchaseFailedException;
import customExceptions.RanOutOfStockException;
import dbDAO.CouponDBDAO;
import dbDAO.CustomerDBDAO;
/**
 * This is the class that director all the CustomerFacade class possibility
 * in this system of the coupon project .
 * @author user
 *
 */
public class CustomerFacade implements CouponClientFacade{
	
	 CustomerDBDAO customerDBDAO = new CustomerDBDAO();
	/**
	 * this method gives instruction for the {@link CustomerDBDAO}class
	 * to purchase a coupon all the logic and the work in front of the DB
	 * is in the {@link CustomerDBDAO}class.
	 * @param coupon
	 */
	public void purchaseCoupon(Coupon coupon){

		
			try {
				customerDBDAO.purchaseCoupon(coupon);
		    	}
		    catch (RanOutOfStockException | PurchaseFailedException | ClassNotFoundException 
					| SQLException | InterruptedException e) {
				
				      System.err.println(e.getMessage());
		
		}
		
	}
	/**
	 * This method is ordered the {@link CustomerDBDAO} to get all of the
	 * coupons of the applicant customer own.
	 * all the work with the DB is in the {@link CustomerDBDAO}class.
	 * @return ArrayList<Coupon>allPurchasedCoupon
	 */
	public ArrayList<Coupon> getAllPurchasedCoupon(){
		
		ArrayList<Coupon>allPurchasedCoupon = new ArrayList<>();
		
		try {
			allPurchasedCoupon=	customerDBDAO.getAllPurchasedCoupon();
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			
			System.err.println(e.getMessage());
		}
		return allPurchasedCoupon;
		
	}
	/**
     * This method is ordered the {@link CustomerDBDAO} to get all of the
	 * coupons of the applicant customer own in this case it will return 
	 * only those that fit the same type that received in the method signature.
	 * all the work with the DB is in the {@link CustomerDBDAO}class.
	 * @param CouponType.
	 * @return ArrayList<Coupon>allTypeCoupon.
	 */
	public ArrayList<Coupon> getAllPurchasedCouponByType(CouponType type){
		
		ArrayList<Coupon>allTypeCoupon = new ArrayList<>();
		
		try {
			allTypeCoupon =	customerDBDAO.getAllPurchasedCouponByType(type);
		    }
		 catch (ClassNotFoundException | SQLException | InterruptedException e) {
			
			System.err.println(e.getMessage());
		}
		return allTypeCoupon;
	}
	/**
	 * This method is ordered the {@link CustomerDBDAO} to get all of the
	 * coupons of the applicant customer own in this case it will return 
	 * only those that equal or less the price that received in the method signature.
	 * all the work with the DB is in the {@link CustomerDBDAO}class.
	 * @param price
	 * @return ArrayList<Coupon>allCoupon
	 */
	public ArrayList<Coupon> getAllPurchasedCouponByPrice(double price){
		 
		ArrayList<Coupon>allCoupon = new ArrayList<>();
		
		try {
			allCoupon = customerDBDAO.getAllPurchasedCouponByPrice(price);
		    }
		 catch (ClassNotFoundException | SQLException | InterruptedException e) {
			
			System.err.println(e.getMessage());
		}

		return allCoupon;
	}
	/**
	 * This method giving the option for the customer to watch all the coupons in the system
	 * using the {@link CouponDBDAO} getAllCoupon method.
	 * @return
	 */
	public ArrayList<Coupon> seeAllCoupons(){
		
		CouponDBDAO couponDB = new  CouponDBDAO();
		ArrayList<Coupon>allCoupons = new ArrayList<>();
		
		try {
			allCoupons = couponDB.getAllCoupon();
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			System.err.println(e.getMessage());

		}
		
		return allCoupons;
	}
	/** 
	 * this login method is exercises the {@link CouponClientFacade} Interface
	 * it gives instruction for {@link CustomerDBDAO}class
	 * it receiving a name and a password String values and 
	 * conductor it to the login method in {@link CustomerDBDAO} class
	 * returning a boolean answer.
	 */
	@Override
	public boolean login(String name, String password, String clientType) {

		boolean log = false;
		
		try {
			log = customerDBDAO.login(name, password);
		    }
	    catch (ClassNotFoundException | SQLException | InterruptedException e) 
		    {	
			System.err.println(e.getMessage());
		    }
		return log;
	}
	

}
