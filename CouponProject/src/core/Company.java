package core;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
/**
 * this class use is to deposit company to the Data Base or to obtain company out from DB. 
 * including {@link ArrayList} that shows the specifies coupons that the company markets.
 * Using {@link Constructor}for all the fields except the {@link ArrayList} for easy use to work with DB
 * getter and setter for all the fields to modify or expose fields when needs.
 * toString for show the company you have include it's specifies coupons.
 * @author user
 *
 */

public class Company {
	
	private long id;
	private String copmName;
	private String password;
	private String email;
	private ArrayList<Coupon> coupons = new ArrayList<>();
	/**
	 * This constructor is assigned to the occurrence that needed to fill the fields of the company
	 * instance at once. 
	 * @param id
	 * @param copmName
	 * @param password
	 * @param email
	 */
	public Company(long id, String copmName,String password ,String email) {
		super();
		this.id = id;
		this.copmName = copmName;
		this.password = password;
		this.email = email;
	}
	/**
	 * this constructor is assigned for the occurrence that needed only instance without the fields to fill.
	 */
	public Company()
	{
		
	}
	/**
	 * This getter is exposing the id that informed in the id field.
	 * @return
	 */
	public long getId() {
		return id;
	}
	/**
	 * this setter is assigned to receiving a double value and update the id field with it.
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * this getter is returning the company name that is informed in the compName field.
	 * @return
	 */
	public String getCopmName() {
		return copmName;
	}
	/**
	 * this setter is receiving a string value and set it as a company name in the compName field.
	 * @param copmName
	 */
	public void setCopmName(String copmName) {
		this.copmName = copmName;
	}
	/**
	 * this getter is exposing the password that informed in the password field.
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * this setter is receiving a string value and set with it the password field.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * this getter is exposing the email of the company that informed in the email field.
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * this setter is receiving a string value to set the company email to the email field.
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * this {@link ArrayList} give the option to receive a coupon {@link ArrayList} that the specific
	 * company markets
	 * @return
	 */
	public ArrayList<Coupon> getCoupons() {
		return coupons;
	}
	/**
	 * this setter is assigned to set the markets coupons instance of the specific company
	 *  into {@link ArrayList}.
	 * @param coupons
	 */
	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}
	/**
	 * this override of the toString method is assigned to exposing the company instance with all it's details.
	 */
	@Override
	public String toString() {
		return "Company [id=" + id + ", copmName=" + copmName + ", password=" + password + ", email=" + email
				+ ", coupons=" + coupons + "]";
	}
	
	
	

}
