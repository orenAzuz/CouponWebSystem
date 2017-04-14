package executeMain;


import core.Company;
import core.CouponType;
import core.Customer;
import facade.AdminFacade;
import facade.ClientType;
import facade.CompanyFacade;
import facade.CouponSystem;
import facade.CustomerFacade;
/**
 * This class is the storage of the facade class instances
 * It holds fields within the companies, clients and administration.
 * There is carried out the LOGIN and the creation, display
 * and update methods.
 * @author user
 *
 */
public class DataStorage {


	AdminFacade admin = (AdminFacade) CouponSystem.getInstance()
		.login("admin", "1234",ClientType.admin);

    CompanyFacade compKleyZemer ;
    CompanyFacade companyPiza ;    
    CompanyFacade marom ;

    CustomerFacade jon ;
    CustomerFacade yos ;
    CustomerFacade dudi ;

 
    /**
     * This method creates and fills all the tables in the DB 
     */
 public void creat() {
	
	 
		AdminFacade badAdmin = (AdminFacade) CouponSystem.getInstance()
				.login("admin", "wrongPassword!", ClientType.admin);
		if (badAdmin == null)
			System.out.println("On wrong password scneario returns null!");

          admin.createCompany(new Company( 0, "PizzaFace","mushrooms","pFace@gmail.co.il"));
          admin.createCompany(new Company( 0, "kley_zemer","kley1","kley@gmail.co.il"));
          admin.createCompany(new Company(0, "marom", "guitar", "maro@gmail.com"));
          
          compKleyZemer = (CompanyFacade) CouponSystem.getInstance()
        	        .login("kley_zemer", "kley1", ClientType.company);
          companyPiza = (CompanyFacade) CouponSystem.getInstance()
     			 .login("PizzaFace","mushrooms",ClientType.company);
          
          if(companyPiza == null)
          {
        	  companyPiza = (CompanyFacade) CouponSystem.getInstance()
          			 .login("PizzaFace","tasty",ClientType.company); 
          }
          
          marom = (CompanyFacade) CouponSystem.getInstance()
        		  .login("marom","guitar",ClientType.company);
          

	compKleyZemer.creatCoupon(CouponCreator.couponsData("newJam"));
	compKleyZemer.creatCoupon(CouponCreator.couponsData("newFender"));
	compKleyZemer.creatCoupon(CouponCreator.couponsData("dwDrumsKit"));
	compKleyZemer.creatCoupon(CouponCreator.couponsData("newCoachFingers"));
	
	companyPiza.creatCoupon(CouponCreator.couponsData("pizaAndrey"));
	companyPiza.creatCoupon(CouponCreator.couponsData("pizaL"));
	companyPiza.creatCoupon(CouponCreator.couponsData("pizaPepper"));
	compKleyZemer.creatCoupon(CouponCreator.couponsData("tamaDrums"));
 
    marom.creatCoupon(CouponCreator.couponsData("newMarshelAmp"));
    marom.creatCoupon(CouponCreator.couponsData("classicGuirat"));
    marom.creatCoupon(CouponCreator.couponsData("lastGibson"));
    marom.creatCoupon(CouponCreator.couponsData("wrongDate"));

    
    admin.createCustomer(new Customer(0,"jon", "joney"));
    admin.createCustomer(new Customer(0,"yos", "yosi"));
    admin.createCustomer(new Customer(0,"dudi", "dudi1"));
    
    jon =  (CustomerFacade) CouponSystem.getInstance()
     		.login("jon", "joney",ClientType.customer);
    
    if(jon == null)
       {
    	jon =  (CustomerFacade) CouponSystem.getInstance()
 		.login("jon", "jy",ClientType.customer);
    	}
    
    yos =  (CustomerFacade) CouponSystem.getInstance()
    		.login("yos", "yosi",ClientType.customer);
    dudi=  (CustomerFacade) CouponSystem.getInstance()
    		.login("dudi", "dudi1", ClientType.customer);
    
    jon.purchaseCoupon(CouponCreator.couponsData("lastGibson"));
    yos.purchaseCoupon(CouponCreator.couponsData("lastGibson"));
    dudi.purchaseCoupon(CouponCreator.couponsData("newJam"));
    dudi.purchaseCoupon(CouponCreator.couponsData("newJam"));
    dudi.purchaseCoupon(CouponCreator.couponsData("pizaAndrey"));
    yos.purchaseCoupon(CouponCreator.couponsData("newMarshelAmp"));
    yos.purchaseCoupon(CouponCreator.couponsData("wrongDate"));
    yos.purchaseCoupon(CouponCreator.couponsData("dwDrumsKit"));
     
	}
	/**
	 * This method is for prove the abilities of the facade out data 
	 * from the DB. 
	 */
	public void display(){
		System.out.println("This is the Admin abilities!!!");
		System.out.println("Admin All Companies "+admin.getAllCompanies()); 
		System.out.println("Admin All Customers "+admin.getAllCustomer()); 
        System.out.println("Admin get Customer By ID "+admin.getCustomer(1262));
		System.out.println("Admin get Company By Id "+admin.getCompanyById(94)); 
		System.out.println();
		
		System.out.println("This is the Company abilities!!");
        System.out.println(" The  Company Coupons "+compKleyZemer.getAllCoupon());
		System.out.println("The Company Coupons By Type "+compKleyZemer.getCouponByType(CouponType.MUSIC_INSTRUMENTS)); 
		System.out.println("The Company Coupon By ID "+compKleyZemer.getCouponById(2)); 
		System.out.println();
		
		System.out.println("This is the Customer abilities!");
		System.out.println("Customer All Purchase Coupons "+dudi.getAllPurchasedCoupon()); 
		System.out.println("Customer All Coupons By Type "+dudi.getAllPurchasedCouponByType(CouponType.MUSIC_INSTRUMENTS));
		System.out.println("Customer All Coupons By Price "+dudi.getAllPurchasedCouponByPrice(100));
		System.out.println("Customer name yos show his purchase Coupons "+yos.getAllPurchasedCoupon());
		System.out.println("The Customer watch all the coupons in the system  "+jon.seeAllCoupons());
		
		
		

		
		
	}
	/**
	 * This method performs UPDATE to the follows Company,
	 * customer and coupon.
	 */
	public void update(){
		
		
		admin.updateCompany(new Company( 0, "PizzaFace","tasty","pFace@gmail.COM"));
		admin.updateCustomet(new Customer(0,"jon", "jy"));
	    compKleyZemer.updateCoupon(CouponCreator.couponsData("update"));

        companyPiza = (CompanyFacade) CouponSystem.getInstance()
   			 .login("PizzaFace","tasty",ClientType.company);

        jon =  (CustomerFacade) CouponSystem.getInstance()
         		.login("jon", "jy",ClientType.customer);
	}
	
	
}
