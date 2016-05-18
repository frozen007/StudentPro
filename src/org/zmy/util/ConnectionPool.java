/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-5
 * 
 */
package org.zmy.util;

import java.sql.Connection;

/**
 * ConnectionPool
 *
 */
public interface ConnectionPool {

	public Connection getConnection();
	
	public void releaseConnection(Connection conn);
	
	public void destroyConnections();
}
