package dbDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import core.Coupon;
import customExceptions.NotAvailableException;
/**
 * This is a {@link Thread} implements Runnable
 * It's a thread that once a day going through all
 * the last dates of all the coupons in the DB and delete the expired
 * one from the DB.
 * @author user
 *
 */
public class DailyCouponExpirationTask implements Runnable{

	CouponDBDAO coupDb = new CouponDBDAO();
	
	private boolean runner = true;

	public boolean isRunner() {
		return runner;
	}

	public void setRunner(boolean runner) {
		this.runner = runner;
	}
/**
 * An {@link Override} method that exercises the runnable super class method.
 * it has an {@link ArrayList} of coupons and a {@link Date} as a members
 * for the task it will add all the coupons in the DB to the allCoupons {@link ArrayList}
 * and go through each one of them and remove the expired by contrasting the 
 * end date of the coupon to the endDate member that up date with the latest date
 * it will run once a day until the the boolean runner will receive a negative value.
 * 
 */
	@Override
	public void run() {
		
			
		while(runner){
			
		ArrayList<Coupon> allCoupons = new ArrayList<>();

		Date endDate = new Date();
		endDate.getTime();
		try {
			allCoupons.addAll(coupDb.getAllCoupon());
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			System.err.println(e.getMessage());
		}
		
		for(int i=0; i<allCoupons.size(); i++){
			
		if(allCoupons.get(i).getEndDate().before(endDate)){
			
		try {
			
			coupDb.removeCoupon(allCoupons.get(i));
			} catch (ClassNotFoundException | SQLException | InterruptedException | NotAvailableException e) {
				System.err.println(e.getMessage());
			}
		}
	}
		
		 System.out.println("DailyCouponExpirationTask done!");
		 
		 try {
			 TimeUnit.DAYS.sleep(1);
		     }
		     catch (InterruptedException e) {
			 System.err.println(e.getMessage());
		}
	   }
	  }
	}


