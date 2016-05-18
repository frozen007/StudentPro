/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-4
 * 
 */
package org.zmy.studentpro.action;

import java.sql.Connection;
import java.util.Hashtable;

import org.zmy.util.ConnectionPool;
import org.zmy.util.InteractDevice;

/**
 * GenericAction
 *
 */
public abstract class GenericAction extends InteractDevice implements Action {
	
	protected ConnectionPool connPool = null;
	
	protected Hashtable<String, Object> params = new Hashtable<String, Object>();
	
	private String actionId = null;
	
	protected Connection conn = null;

	public void setParam(String name, String value) {
		params.put(name, value);
	}
	
	protected abstract String doAction() throws Exception;

	@Override
	public String execute() throws Exception {
		
		String sRet = Action.SUCCESS;
		conn = connPool.getConnection();
		try {
			sRet = doAction();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		}
		conn.commit();
		this.connPool.releaseConnection(conn);
		return sRet;
	}

	@Override
	public void setConnectionPool(ConnectionPool cp) {
		this.connPool = cp;
		
	}

	@Override
	public void setParams(String name, Object value) {
		this.params.put(name, value);
		
	}

	@Override
	public String getActionId() {
		return actionId;
	}

	@Override
	public void setActionId(String id) {
		this.actionId = id;
		
	}
}
