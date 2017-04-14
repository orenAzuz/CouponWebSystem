package facade;

import java.sql.SQLException;

import dbDAO.ConnectionPool;
import dbDAO.DailyCouponExpirationTask;
/**
 *  This class is a singleton which means that it will provide
 *  only one appearance of this class.
 *  It is the engine system for this project
 *  Hence entrance to the system.
 *  Hence the system's closing.
 *  The {@link DailyCouponExpirationTask} is operated from this class
 *  private constructor.
 * @author user
 *
 */
public class CouponSystem {
	
	DailyCouponExpirationTask dailyCouponExpirationTask = new DailyCouponExpirationTask();
	
	Thread task = new Thread(dailyCouponExpirationTask);
	private static CouponSystem instance = null;
	
	private  CouponSystem (){
		
		   task.setDaemon(true);

			task.start();
	}
/**
 * This method provide instance of the couponSystem although it will
 * provide the same instance.
 * @return
 */
	public static synchronized CouponSystem getInstance(){
		

		if(instance==null){
			
			instance = new CouponSystem();
		}
	
		return instance;
	
	}
	/**
	 * This is the login method which attendant the entering to the system.
	 * It receiving a String values for name and password and an ENUM for
	 * the type of the applicant to adjusted it to the right case.
	 * than it will transmit the values to the facade classes and regain
	 * a boolean value in case the login succeeded it returning an instance
	 * of the required type customer , company or admin.
	 * @param name
	 * @param password
	 * @param clientType
	 * @return CouponClientFacade instance.
	 */
	public CouponClientFacade login(String name,String password,ClientType clientType){

		AdminFacade admin = new AdminFacade();
		CompanyFacade company = new CompanyFacade();
		CustomerFacade customer = new CustomerFacade();
		boolean log = false;
		switch (clientType){
		case admin:
			log = admin.login(name, password, name);
			if (log == true){
				return admin;
			}
			break;
		case company:
			log = company.login(name, password, name);
			if (log==true){
				return    company;
			}
			break;
		case customer:
		    log = customer.login(name, password, name);
			if(log==true){
				return customer;
			}
			break;
		}
				
		return null;
		
	}
	/**
	 * This method role is to take care that all the the connection in the 
	 * {@link ConnectionPool} will be closed . first it will cause the while loop
	 * in the {@link DailyCouponExpirationTask} to stop by set the runner to false and
	 * than run the closeAllConection method in the {@link ConnectionPool}.
	 */
		public void shutdown(){
			
			dailyCouponExpirationTask.setRunner(false);
			

			try {
				ConnectionPool.getInstance().closeAllConection();
				
			} catch (ClassNotFoundException | SQLException e) {
				
				System.err.println(e.getMessage());
			}
		}
	
}
