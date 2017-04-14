package dbDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import core.Coupon;
import core.CouponType;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;
/**
 * this interface class is designed to bound the implements class to apply the following method.
 * @author user
 */
public interface CouponDAO {
	/**
	 *  this an {@link AbstractMethod} that bound to create coupon.
	 * @param coupon
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 * @throws DuplicateException
	 */
	public void creatCoupon(Coupon coupon) throws ClassNotFoundException, SQLException, InterruptedException, DuplicateException;
	/**
	 * this an {@link AbstractMethod} that bound to remove coupon .
	 * @param coupon
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 * @throws DuplicateException
	 * @throws NotAvailableException 
	 */
	public void removeCoupon(Coupon coupon) throws ClassNotFoundException, SQLException, InterruptedException, NotAvailableException;
	/**
	 * this an{@link AbstractMethod} that bound to do an update for coupon.
	 * @param coupon
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	public void updateCoupon(Coupon coupon) throws ClassNotFoundException, SQLException, InterruptedException;
	/**
	 * this an{@link AbstractMethod} that bound to received coupon by transmitting a long id.
	 * @param id
	 * @return coupon
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 * @throws NotAvailableException 
	 */
	public Coupon getCouponeById(long id) throws ClassNotFoundException, SQLException, InterruptedException, NotAvailableException;
	/**
	 * this an{@link AbstractMethod} that bound to extract all the coupons that exist in DB.
	 * @return Coupon{@link ArrayList}
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	public ArrayList<Coupon> getAllCoupon() throws ClassNotFoundException, SQLException, InterruptedException;
/**
 * this an{@link AbstractMethod} that bound to extract the same type coupons in {@link ArrayList}
 * by adjusting their type in DB.
 * @param couponType
 * @return
 * @throws ClassNotFoundException
 * @throws SQLException
 * @throws InterruptedException
 */
	ArrayList<Coupon> getCouponByType(CouponType couponType) throws ClassNotFoundException, SQLException, InterruptedException;
	
	

}
