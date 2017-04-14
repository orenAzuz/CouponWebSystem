package facade;

import java.sql.SQLException;
/**
 * This interface has only one abstract method the login.
 * it designed to bind all facade classes so the {@link CouponSystem}
 * can make instances for all of them in one method . 
 * @author user
 *
 */
public interface CouponClientFacade {
	/**
	 * this an{@link AbstractMethod} that bound to apply login in the facade implements.
	 * @param name
	 * @param password
	 * @param clientType
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	public boolean login(String name,String password,String clientType) throws ClassNotFoundException, SQLException, InterruptedException;

}
