package dbDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import core.Coupon;
import core.CouponType;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;
/**
 * this class dbDAO is the class that works with the Data Base 
 * in all issues creation and removing updating and so of the coupons options
 * following the methods.
 * all the methods in this class using {@link SqlQueries} class for the SQL queries.
 * the members sqlId and campId with getter and setter to help 
 * methods having the right ID when sending a quarry to DB.
 * @author user
 *
 */
public class CouponDBDAO implements CouponDAO{
	
	CoupList<Coupon> cl = new CoupList<Coupon>();
	private long sqlId;
	private long compID;
/**
 * this getter reveals the company id according the company id in DB that informed in compID member. 
 *  * @return
 */

	public long getCompID() {
		return compID;
	}
/**
 * this setter is made to inform the company id for this compID member.
 * @param compID
 */

	public void setCompID(long compID) {
		this.compID = compID;
	}
/**
 * this getter is made for receiving the coupon id that informed in this class
 * @return
 */

	public long getSqlId() {
		return sqlId;
	}
/**
 * this setter is made to informing the coupon id for the class methods needs
 * @param sqlId
 */

	public void setSqlId(long sqlId) {
		this.sqlId = sqlId;
	}

/**
 * creatCoupon method is sending a checking quarry to the DB using {@link Creator}class 
 * if the title is available in case of true response
 * it will send an {@link DuplicateException} message
 * that the coupon is exist if the result is false it will put the coupon in DB 
 * again using {@link Creator}class 
 * startDate and endDate getting date values from the coupon that obtained in the method 
 * startDateString and endDateString converting the date to string so the DB get it right.
 * then sqlId is updated from the DB by {@link PreparedStatement} executeUpdate from 
 * {@link Creator}class  
 * than tie Coupon to Company by adding a new Company_Coupon line to Company_Coupon table in DB
 * using the ID from sqlID and CompID that updated earlier by the CompanyFacade using 
 * {@link Creator}class 
 * @throws DuplicateException 
 */
	@Override
	public void creatCoupon(Coupon coupon) throws ClassNotFoundException, SQLException, InterruptedException, DuplicateException {
		
		Date startDate = coupon.getStartDate();
		Date endDate = coupon.getEndDate();

		SimpleDateFormat startDateFormat = new SimpleDateFormat("yyyy/MM/dd",Locale.US);
		SimpleDateFormat endDateFormat = new SimpleDateFormat("yyyy/MM/dd",Locale.US);
		String startDateString= startDateFormat.format(startDate);
		String endDateString = endDateFormat.format(endDate);
	    boolean result = Creator.check(String.format(SqlQueries.couponByTitle, coupon.getTitle()));
	    
	        if (result){
	        	throw new DuplicateException("the coupon "+coupon.getTitle()+" is exsist!");
	        }else{
	        		        
			sqlId = Creator.creat(String.format(SqlQueries.insertCouponToDB,
					coupon.getTitle(),startDateString,endDateString,coupon.getAmount(),
					coupon.getType(),coupon.getMassage(),coupon.getPrice(),coupon.getImage()));
			
	        Creator.creat(String.format(SqlQueries.bindCouponToCompanyInDB,compID,sqlId));
	        }
		
	}

/**
 * removeCoupon method is designed to remove coupon either by company or by the 
 * DailyCouponExpirationTask from all the tables in DB Coupon ,Company_Coupon and Customer_Coupon.
 *{@link SqlExecute}class first send a quarry to check if the coupon is exist in DB and if 
 *the result false it will send an Exception message that the coupon is not exist.
 * in case the result is true {@link SqlExecute}class get the coupon id from DB so it can work with. 
 * then it send quarries to delete the coupon records from all tables that first recall.
 * than it send message that the coupon is removed successfully. 
 * @throws DuplicateException 
 */
	@Override
	public void removeCoupon(Coupon coupon) throws ClassNotFoundException, SQLException, InterruptedException, NotAvailableException {
				
		long id = 0;		
		long result = SqlExecute.check(String.format(SqlQueries.couponIdFromDB,coupon.getTitle()));
		
		if(result != 0){
			id=result;
		
		 SqlExecute.execute(String.format(SqlQueries.removeCompanyCouponByCouponID,id));
		 SqlExecute.execute(String.format(SqlQueries.removeCustomerCouponByCouponDI,id));
		 SqlExecute.execute(String.format(SqlQueries.removeCouponByCouponID,id));
		 System.out.println("The Coupon whith title "+coupon.getTitle()+" is removed!");
	}
	   else{
           throw new NotAvailableException("The coupon with title "+coupon.getTitle()+" is not found!");	
           }
	}
/**
 * The method updateCoupon using {@link UpdaterDBDAO}class to updating  end date and price.
 * the update method in {@link UpdaterDBDAO}class is receiving a String and send it as a query to the DB.
 * the String  updatingCoupon using values from the coupon instance that received in the method
 * signature.
 */
	@Override
	public void updateCoupon(Coupon coupon) throws ClassNotFoundException, SQLException, InterruptedException {

		Date endDate = coupon.getEndDate();

		SimpleDateFormat endDateFormat = new SimpleDateFormat("yyyy/MM/dd",Locale.US);
		String endDateString = endDateFormat.format(endDate);
		SqlExecute.execute(String.format(SqlQueries.updatingCoupon, endDateString,coupon.getPrice(),
				coupon.getTitle()));
		
	}
	/**
	 * this method is returning a coupon instance back from DB by adjustment the received id
	 * in the signature of the method to the coupons id column in the DB
	 * using {@link CoupList}class
	 * @throws NotAvailableException 
	 */ 
	
	@Override
	public Coupon getCouponeById(long id) throws ClassNotFoundException,
	SQLException, InterruptedException, NotAvailableException {
		
		ArrayList<Coupon> allCoupons = null;
		long i = SqlExecute.check(String.format(SqlQueries.couponByID,id));
		if(i == 0){
			throw new NotAvailableException("The coupon is not found insert right id !");
		}else
		allCoupons =  cl.executeQuery(String.format(SqlQueries.couponByID,id));
		Coupon coupon = allCoupons.get(0);
		
		return coupon;
	}
/**
 * this method is returning a coupons {@link ArrayList} from DB.
 * the {@link CoupList}class do all the work with the DB
 * and also returning a coupons ArrayList.
 */
	@Override
	public ArrayList<Coupon> getAllCoupon() throws ClassNotFoundException, SQLException, InterruptedException {
		
		ArrayList<Coupon> allCoupons = new ArrayList<>();
	    allCoupons.addAll(cl.executeQuery(String.format(SqlQueries.allCouponsInDB)));	
	    
		return allCoupons;	
	}
	/**
	 * This method is returning an {@link ArrayList} of coupon instance type from DB.
	 * unlike the previews method it will return the coupons that owned to the specific
	 * company that call the method.
	 * the {@link CoupList}class do all the work with the DB.
	 * @return ArrayList<Coupon> allCoupons.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	public  ArrayList<Coupon> getAllCouponsByCompanyID() throws ClassNotFoundException,
	    SQLException, InterruptedException{
				
		ArrayList<Coupon> allCoupons = new ArrayList<>();
	    allCoupons = (cl.executeQuery(String.format
	    		(SqlQueries.allCouponsByCompanyID, compID)));	
		
		return allCoupons;
	}
/**
 * this method is extract the coupons {@link ArrayList} with the same type as request.
 * it will also adjust the company id to received the coupons of the requesting company.
 * the type is a {@link CouponType} enumClass type that defines the category of the coupon.
 * {@link CoupList}class do all the logic work with the DB by receiving a string
 * quarry and returning an ArrayList.
 */
	@Override
	public ArrayList<Coupon> getCouponByType(CouponType couponType) throws ClassNotFoundException,
	    SQLException, InterruptedException {

		ArrayList<Coupon>couponByType= new ArrayList<>();
		couponByType.addAll(cl.executeQuery(String.format(SqlQueries.allCouponsByTypeAndCompID,
				couponType,compID)));
		
		return couponByType;
	}



}
