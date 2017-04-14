package core;

import java.lang.reflect.Constructor;
import java.util.Date;
/**
 * this class use is to deposit coupon to the Data Base or to obtain coupon out from DB. 
 * Using {@link Constructor}for all the fields .
 * getter and setter for all the fields to modify or expose fields when needs
 * toString for show the coupon instance you have .
 * @author user
 *
 */
public class Coupon {
	
	private long id;
	private String title;
	private Date startDate;
	private Date endDate;
	private int amount;
	CouponType type;
	private String message;
	private double price;
	private String image;
	/**
	 * This constructor is assigned to the occurrence that needed to fill the fields at once. 
	 * @param id
	 * @param title
	 * @param startDate
	 * @param endDate
	 * @param amount
	 * @param type
	 * @param massage
	 * @param price
	 * @param image
	 */
	public Coupon(long id, String title, Date startDate, Date endDate, int amount, CouponType type, String massage,
			double price, String image) {
		super();
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = massage;
		this.price = price;
		this.image = image;
		
		
	}
	/**
	 * This getter is expose the id that informed at this moment in the id field.
	 * @return
	 */
	public long getId() {
		return id;
	}
	/**
	 * this setter is received an id and update the id field. 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * this getter is expose the coupon title that informed at this moment in the title field.
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * this setter is receiving a title and updating the title field.
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * this getter is expose the start date of the coupon .
	 * @return
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * this setter receiving a {@link Date}value and set it in the startDate field.
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * this getter is expose the end date value that informed now.
	 * @return
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * this setter is receiving {@link Date}value and update with it the endDate field.
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * this getter is exposing the amount that now informed .
	 * @return
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * this setter is receiving an amount value and update the amount field.
	 * @param amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * this  getters is exposing the type of the coupon that informed, it's a {@link CouponType} type
	 */
	public CouponType getType() {
		return type;
	}
	/**
	 * this setter is setting the coupon type by receiving {@link CouponType} type
	 * @param type
	 */
	public void setType(CouponType type) {
		this.type = type;
	}
	/**
	 * this getter is for exposing the message of the coupon that informed at this time.
	 * @return
	 */
	public String getMassage() {
		return message;
	}
	/**
	 * this setter is receiving a string message and updating the message field with this values. 
	 * @param massage
	 */
	public void setMassage(String message) {
		this.message = message;
	}
	/**
	 * this getter is exposing the price of the coupon.
	 * @return
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * this setter is receiving a price to update the price field. 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * this is for exposing the image values which is string values.
	 * @return
	 */
	public String getImage() {
		return image;
	}
	/**
	 * this setter is receiving string values and update with it the image field.
	 * @param image
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * this is an override of the toString which is assigned to extract the coupon instance with it's all details.
	 */
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", type=" + type + ", massage=" + message + ", price=" + price + ", image="
				+ image + "]";
	}
	
	
}
