package dbDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import core.Coupon;
import core.CouponType;
/**
 * This class is extends {@link BaseDB} and has only one method and
 * it's assignment is to provide coupons {@link ArrayList}
 * to all comers.
 * @author user
 *
 */
public class CoupList<T> extends BaseDB<T> {
	
/**
 * this is a {@link Override} method which suppose to issue several coupon
 * instances out from DB.
 * every coupon that comes out from the DB first set the fields of coupon constructor
 * than the allCoupon {@link ArrayList} will add it and this process will keep going
 * until the {@link ResultSet} emptied.
 * than it returning the allCoupon {@link ArrayList} with the desirable coupons.
 * @param query
 * @return {@link ArrayList}
 * @throws SQLException
 * @throws ClassNotFoundException
 * @throws InterruptedException
 */
	    @SuppressWarnings("unchecked")
		@Override
	 public ArrayList<T> production() throws ClassNotFoundException,
	    SQLException, InterruptedException {

		ArrayList<Coupon> allCoupons = new ArrayList<>();
		
		while (rs.next()) {
			long id1 = rs.getLong("ID");
			String title = rs.getString("TITLE");
			Date startDate = rs.getDate("START_DATE");
			Date endDate = rs.getDate("END_DATE");
			int amount = rs.getInt("AMOUNT");
			String typeFromDb = rs.getString("TYPE");
			String message = rs.getString("MESSAGE");
			double price1 = rs.getDouble("PRICE");
			String image = rs.getString("IMAGE");
			Coupon coupon = new Coupon(id1, title.trim(), startDate, endDate, amount,
					CouponType.valueOf(typeFromDb.trim()),message.trim(), price1, image.trim());
			allCoupons.add(coupon);
		}

		return (ArrayList<T>) allCoupons;

	}





	
}
