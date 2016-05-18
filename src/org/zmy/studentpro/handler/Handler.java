/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-4
 * 
 */
package org.zmy.studentpro.handler;

import org.zmy.studentpro.action.ActionPool;


/**
 * FunctionHandler
 *
 */
public interface Handler {
	
	public String CMD_QUIT = "QUIT";
	
	public String SUCCESS = "0";

	public String handle();
	
	public void setActionPool(ActionPool actionPool);
	
	public String getDescription();

}
