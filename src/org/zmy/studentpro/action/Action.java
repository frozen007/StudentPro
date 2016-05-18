/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-4
 * 
 */
package org.zmy.studentpro.action;

import org.zmy.util.ConnectionPool;

/**
 * Action
 *
 */
public interface Action {
	
	public String SUCCESS = "0";
	
	public String FAILURE = "1";
	
	public String execute() throws Exception;
	
	public void setConnectionPool(ConnectionPool cp);
	
	public void setParams(String name, Object value);
	
	public void setActionId(String id);
	
	public String getActionId();
	
	public void processError(int errorCode);

}
