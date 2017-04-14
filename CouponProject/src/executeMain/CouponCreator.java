package executeMain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import core.Coupon;
import core.CouponType;
/**
 * All this class do is creating Coupons to this execute main to
 * demonstrate the system abilities.
 * they are all in switch case to easy access. 
 * @author user
 *
 */
public class CouponCreator {
	
	/**
	 * This is a static method which returning a coupon by adjusting a string value
	 * to the name of the desirable coupon.
	 * @param name
	 * @return Coupon
	 */
	public static Coupon couponsData(String name){
		

		Date upDate = new Date();
	    Date startDate= new Date();
		Date endDate = new Date();
		Date expireDate = new Date();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			endDate = dateFormat.parse("30/10/2017");
			expireDate = dateFormat.parse("30/10/2016");
			upDate = dateFormat.parse("30/12/2017");
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		
		switch (name) {
		case "newJam":
	        Coupon newJam = new Coupon(0, "The_new_JAM_of_Ibanez", startDate, endDate, 3,
	    			CouponType.MUSIC_INSTRUMENTS, "the best price ever!", 10000.99, "qa");
	        return newJam;
	        
		case "newFender":
	        Coupon newFender = new Coupon(0, "The_new_fender_guitar", startDate, endDate, 3,
	    			CouponType.MUSIC_INSTRUMENTS, "the best price ever!", 7999.99, "qa");
			return newFender;
			
		case "dwDrumsKit":
            Coupon dwDrumsKit = new Coupon(0, "dw_drume_kit", startDate, endDate, 3,
    			CouponType.MUSIC_INSTRUMENTS, "the best price ever!", 20000.99, "qa");
            return dwDrumsKit;
        case "pizaAndrey":
           Coupon pizaAndrey = new Coupon(0, "Piza_Andrey", startDate, endDate, 3,
    			CouponType.FOOD, "Very BIGG Very Tasty whith Extras", 39.99, "qa");
            return pizaAndrey;
            
        case "pizaL":
	        Coupon pizaL = new Coupon(0, "Piza_L", startDate, endDate, 3,
	    			CouponType.FOOD, "Very BIGG Very Tasty whith Extras", 29.99, "qa");
			return pizaL;
			
		case "pizaPepper":
            Coupon pizaPepper = new Coupon(0, "Piza_Pepper_xxl", startDate, endDate, 3,
    			CouponType.FOOD, "Very BIGG Very Tasty whith Extras", 39.99, "qa");
             return pizaPepper;
             
        case "tamaDrums":
             Coupon tamaDrums = new Coupon(0, "tama_drume_kit", startDate, endDate, 4,
    			CouponType.MUSIC_INSTRUMENTS, "the best price ever!", 10000.99, "qa");
             return tamaDrums;
             
        case"newMarshelAmp":
            Coupon newMarshelAmp = new Coupon(0, "new_Marshell_AmP", startDate, endDate, 4,
        		CouponType.MUSIC_INSTRUMENTS, "The best Price seans 1960!!",3499.99, "sd");
             return newMarshelAmp;
             
        case"classicGuirat":	
            Coupon classicGuirat = new Coupon(0, "new_classic_guitar", startDate, endDate, 4,
        		CouponType.MUSIC_INSTRUMENTS, "The best Price!!",499.99, "sd");
            return classicGuirat;
            
        case "lastGibson":
            Coupon lastGibson = new Coupon(0, "last_gibson_sg", startDate, endDate, 1,
     		   CouponType.MUSIC_INSTRUMENTS, "The best Price!!",2599.99, "sd");
            return lastGibson;
        
        case"wrongDate":
            Coupon wrongDate = new Coupon(0,"expireDate" , startDate,expireDate,3 ,
     		   CouponType.SPORT,"ForFree",3.3,"sd");
            return wrongDate;
        
        case"update":		
           Coupon update = new Coupon(0, "The_new_fender_guitar", startDate, upDate, 3,
       			CouponType.MUSIC_INSTRUMENTS, "the best price ever!", 7800.99, "qa");
            return update;
        case"newCoachFingers":
        	Coupon newCoachFingers = new Coupon(0,"The_new_Coach_Fingers",startDate,endDate,3,
        			CouponType.SPORT,"You must try....",19.90,"asd");
        	return newCoachFingers;

	   default:
		   break;
	 }
		return null;
	}
}
