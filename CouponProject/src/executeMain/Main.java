package executeMain;

import java.util.Scanner;
import facade.CouponSystem;
/**
 * This is the execute of all the CouponProject possibilities.
 * @author user
 *
 */
public class Main {

/**
 * this main is starting with the creating of all tables in the Data Base for the reason
 * that we need to do login to the system so all of the functions will work and if they all
 * removed or changed for some reason we need to create them.
 * @param args
 */
	
	public static void main(String[] args)  {
	
		
	DataStorage ds = new DataStorage();	
	Remover r = new Remover();
	// the creating process...
		ds.creat();
		
		Scanner scanner = new Scanner(System.in);
		boolean examiner = true;
		String scannerCommand;
		/**
		 * pleas enter "create"- to create.
		 * "display"- to display all the abilities of the system
		 * "removeCompany"- to remove one company.
		 * "removeCustomer"- to remove one customer.
		 * "removeCoupon"- to remove one coupon. 
		 * "removeAll"- to clear the DataBase.
		 * "shutdown" - to shutdown the system.
		 * "stop" - to terminate.
		 */
		while(examiner){
		
		 scannerCommand = scanner.next();
		
		switch (scannerCommand){
		
		case "create":
			ds.creat();
			break;
		case "display":
			ds.display();
			break;
		case "update":
			ds.update();
			break;
		case "removeCompany":
			r.removeCompany();
			break;
		case "removeCustomer":
			r.removeCustomer();
			break;
		case "removeCoupon":
			r.removeCoupon();
			break;
		case "removeAll":
			r.removeAll();
			break;
		case "shutdown":
			CouponSystem.getInstance().shutdown();
		case "stop":
			examiner = false;
			System.exit(0);
			break;
			
			default:
		
		}
		}
		scanner.close();

     }

	

}
