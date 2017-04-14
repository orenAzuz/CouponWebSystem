package executeMain;


import core.Company;
import core.Customer;
/**
 * This class will remove tables from the DB by following request.
 * @author user
 *
 */
public class Remover extends DataStorage {
	/**
	 * This method will remove the specific company and it's coupons
	 */
	public void removeCompany(){
		
		admin.removeCompany(new Company( 0, "PizzaFace","mushrooms","pFace@gmail.co.il"));
		
	}
	/**
	 * This method will remove this customer and it's all records.
	 */
	public void removeCustomer(){
		
		admin.removeCustomer(new Customer(0,"jon", "joney"));
	}

	/**
	 * this method will remove this specific coupon from all tables in DB.
	 */
	public void removeCoupon(){
		
		compKleyZemer.removeCoupon(CouponCreator.couponsData("tamaDrums"));
	}
	/**
	 * This method actually will empty the DB. it removing every thing that exist by deleting
	 * all companies and customers.
	 */
	public void removeAll(){
		
		admin.removeCompany(new Company( 0, "PizzaFace","mushrooms","pFace@gmail.co.il"));
		admin.removeCompany(new Company( 0, "kley_zemer","kley1","kley@gmail.co.il"));
		admin.removeCompany(new Company(0, "marom", "guitar", "maro@gmail.com"));
		admin.removeCompany(new Company( 0, "PizzaFace","tasty","pFace@gmail.COM"));

		admin.removeCustomer(new Customer(0,"jon", "joney"));
		admin.removeCustomer(new Customer(0,"yos", "yosi"));
		admin.removeCustomer(new Customer(0,"dudi", "dudi1"));
		admin.removeCustomer(new Customer(0,"jon", "jy"));



		
	}
}
