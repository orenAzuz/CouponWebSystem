package facade;

import java.sql.SQLException;
import java.util.ArrayList;

import core.Coupon;
import core.CouponType;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;
import dbDAO.CompanyDBDAO;
import dbDAO.CouponDBDAO;
/**
 * This is the class that director all the CompanyFacade class possibility
 * in this system of the coupon project .
 * @author user
 *
 */
public class CompanyFacade implements CouponClientFacade{
	
          CouponDBDAO couponDBDAO = new CouponDBDAO();
	private  long id;

	
	public long getId() {
		return id;
	}
	/**
	 * This setter is made to inform the company id for this id member.
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * this method gives instruction for {@link CouponDBDAO}class
	 * to set a coupon to the DB.it receiving a coupon instance and 
	 * conductor it to the creatCoupon method in {@link CouponDBDAO} class
	 * @param coupon
	 */
	public void creatCoupon(Coupon coupon) {
		try {
		
				couponDBDAO.creatCoupon(coupon);
		
		} catch (ClassNotFoundException | SQLException | InterruptedException | DuplicateException e) {
			System.err.println(e.getMessage());
		}
	}
	/**
	 * this method gives instruction for {@link CouponDBDAO}class
     * to remove a coupon from the DB.it receiving a coupon instance and 
	 * conductor it to the removeCoupon method in {@link CouponDBDAO} class			
	 * @param coupon
	 */
	public void removeCoupon(Coupon coupon){
		
		try {
			couponDBDAO.removeCoupon(coupon);
		} catch (ClassNotFoundException | SQLException | InterruptedException | NotAvailableException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		
	}
	/**
	 * This method gives instruction for {@link CouponDBDAO}class
	 * to update a coupon in the DB it receiving a coupon instance and 
	 * conductor it to the updateCoupon method in {@link CouponDBDAO} class
	 * @param coupon
	 */	
	public void updateCoupon(Coupon coupon){
		
		try {
			couponDBDAO.updateCoupon(coupon);
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			
			System.err.println(e.getMessage());
		}	
	}
	/**
	 * This method gives instruction for {@link CouponDBDAO}class
	 * to extract a coupon from DB by receiving long id and 
	 * conductor it to the getCouponById method in {@link CouponDBDAO}class
	 * @param id
	 * @return coupon
	 */
	public Coupon getCouponById(long id){
		
		Coupon coupon = null ;
		
		try {
			coupon =  couponDBDAO.getCouponeById(id);
		} catch (ClassNotFoundException | SQLException | InterruptedException | NotAvailableException e) {
			
			System.err.println(e.getMessage());
		}
		return coupon;

	}
	/**
	  * this method gives instruction for {@link CouponDBDAO}class
	 * to extract all the coupons that exist in the DB .
	 * @return allCoupons {@link ArrayList}.
	 */
	public ArrayList<Coupon> getAllCoupon(){
		ArrayList<Coupon> allCoupons = new ArrayList<>();
		try {
			allCoupons.addAll(couponDBDAO.getAllCouponsByCompanyID());
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			
			System.out.println(e.getMessage());
		}

		return allCoupons;
	}
	/**
	 * this method gives instruction for {@link CouponDBDAO}class
	 * to extract all the coupons that exist in the DB that has the
	 * same type and marked by the specific company which send the request.
	 * @param couponType
	 * @return allCoupons {@link ArrayList}.
	 */
	public ArrayList<Coupon> getCouponByType(CouponType couponType){
		ArrayList<Coupon>couponByType= new ArrayList<>();
		
		try {
			couponByType.addAll(couponDBDAO.getCouponByType(couponType)) ;
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
		
			System.out.println(e.getMessage());
		}

		return couponByType;
	}
	/**
	 * this login method is exercises the {@link CouponClientFacade} Interface
	 * it gives instruction for {@link CompanyDBDAO}class
	 * it receiving a name and a password String values and 
	 * conductor it to the login method in {@link CompanyDBDAO} class
	 * returning a boolean answer.
	 */
	@Override
	public boolean login(String name, String password, String clientType) {
		boolean log = false;
	      CompanyDBDAO companyDBDAO = new CompanyDBDAO();

		

		try {
		log =	companyDBDAO.login(name, password);
			
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			
			System.out.println(e.getMessage());
			
		}
		id = companyDBDAO.getSqlId();
		couponDBDAO.setCompID(companyDBDAO.getSqlId()); 

		return log;
	
	}

}
