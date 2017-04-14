package dbDAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import core.Company;

/**
 * This class extends BaseDB<T> and has only one {@link Override} method 
 * that returning a company {@link ArrayList} from the DB.
 * @author user
 *
 * @param <T>
 */
public class CompList<T> extends BaseDB<T> {
	   /**
     * this method is extract companies that exist in the DB according to 
     * the query that received in the super class {@link BaseDB} in the
     * executeQuery method.
     * yet it is only the {@link ResultSet} realization.
     * the first company values that comes out from the DB is setting
     *  in a new company instance fields than the company will be
     * filing in it markets coupons {@link ArrayList} using
     * {@link CouponDBDAO} getAllCouponsByCompanyID() method the compId member
     * of this class is informed earlier to match the specific company that comes 
     * out from the DB.
     */
	@SuppressWarnings("unchecked")
	@Override
	public  ArrayList<T> production() throws ClassNotFoundException,
	    SQLException, InterruptedException {
		
		
		ArrayList<Company> allCompanies = new ArrayList<>();
        CouponDBDAO couponDB = new CouponDBDAO();
		
		while (rs.next())
		{
			long id =rs.getLong("ID");
			String copmName = rs.getString("COMP_NAME");
			String password = rs.getString("PASSWORD");
			String email    = rs.getString("EMAIL");
			Company company = new Company(id, copmName.trim(), password.trim(), email.trim());
			couponDB.setCompID(id);
			((core.Company) company).setCoupons(couponDB.getAllCouponsByCompanyID());
			allCompanies.add(company);
		}

		return (ArrayList<T>) allCompanies;
	}
		

}
