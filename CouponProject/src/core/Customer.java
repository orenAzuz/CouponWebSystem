package core;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
/**
 * this class use is to deposit customer to the Data Base or to obtain customer out from DB. 
 * including {@link ArrayList} that shows the specifies coupons that the specifies customer procurement.
 * Using {@link Constructor}for all the fields except the {@link ArrayList} for easy use to work with DB
 * getter and setter for all the fields to modify or expose fields when needs
 * toString for show the customer you have include the specifies coupons if exist
 * @author user
 *
 */

public class Customer {
	
	private  long id;
	private String custName;
	private String password;
	private ArrayList<Coupon>allCoupon = new ArrayList<>();
	/**
	 * This constructor is assigned to the occurrence that needed to fill the fields at once. 
	 * @param id
	 * @param custName
	 * @param password
	 */
	public Customer(long id, String custName,String password) {
		super();
		this.id = id;
		this.custName = custName;
		this.password = password;
	}
	
	/**
	 * This constructor is assigned to the occurrence that just need an instance.
	 */
	public Customer(){
		
	}
	
	/**
	 * this getter is returning the id that informed at this moment.
	 * @return
	 */
	public long getId() {
		return id;
	}
	/**
	 * This setter is updating the id field for this class.
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * this getter is returning the customer name that informed at this moment.
	 * @return custName
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * This setter is receiving a customer name and  updating the custName field with it.
	 * @param custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * this getter is returning the password that informed at this moment.
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * This setter is receiving a password name and  updating the password field with it.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * this getter is returning the ArrayList<Coupon> that informed at this moment.
	 * @return coupons {@link ArrayList}
	 */
	public ArrayList<Coupon> getCoupon() {
		return allCoupon;
	}
	/**
	 * This setter is receiving an ArrayList<Coupon>  and  updating the ArrayList<Coupon>(allCoupon) field with it.
	 * @param coupon
	 */
	public void setCoupon(ArrayList<Coupon> coupon) {
		this.allCoupon = coupon;
	}
	/**
	 * this is an override toString which is assigned to extract the customer instance with it's all details.
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + custName + ", coupons=" + allCoupon +", password = "+password + "]";
	}
	
	

}
