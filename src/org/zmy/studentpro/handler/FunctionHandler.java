/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-5
 * 
 */
package org.zmy.studentpro.handler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import org.zmy.studentpro.action.Action;
import org.zmy.studentpro.action.ActionPool;
import org.zmy.util.InteractDevice;

/**
 * FunctionHandler
 *
 */
public abstract class FunctionHandler extends InteractDevice implements Handler {
	
	protected String functionId = "";
	
	protected String description = "";
	
	protected ActionPool actionPool = null;
	
	protected static String CMD_HELP = "help";
	
	protected static String CMD_BACK = "back";

	/**
	 * @param actionPool the actionPool to set
	 */
	public void setActionPool(ActionPool actionPool) {
		this.actionPool = actionPool;
	}

	@Override
	public String handle() {
		String sRet = Handler.SUCCESS;
		log("I am in handler:"+this.functionId);
		this.showHelp();
		String cmd = this.readCommand();
		while (!CMD_QUIT.equalsIgnoreCase(cmd) && !CMD_BACK.equalsIgnoreCase(cmd)) {
			if (CMD_HELP.equalsIgnoreCase(cmd)) {
				this.showHelp();
				cmd = this.readCommand();
				continue;
			}
			String actionId = getActionId(cmd);
			if (actionId == null || "".equals(actionId)) {
				log("command can't be accepted by any action.");
				this.showHelp();
				cmd = this.readCommand();
				continue;
			}
			HashMap<String, Object> paramsMap = new HashMap<String, Object>();
			doHandler(cmd, paramsMap);
			
			Action action = this.actionPool.createAction(actionId);
			if(action == null) {
				log("action instance creation erro");
				this.writeLine("命令输入错误!请看帮助.");
				this.showHelp();
				cmd = this.readCommand();
				continue;
			}

			// params propagation
			Iterator<String> itkey = paramsMap.keySet().iterator();
			while(itkey.hasNext()) {
				String key = itkey.next();
				action.setParams(key, paramsMap.get(key));
			}			
			String sActionRet = null;
			try {
				sActionRet = action.execute();
				if (Action.FAILURE.equals(sActionRet)) {
					log("action execution failure:No Exception");
				}
			} catch (SQLException e) {
				log(e);
				log("action execution failure:SQLException");
				action.processError(e.getErrorCode());

			} catch (Exception e) {
				log(e);
				log("action execution failure:Exception");
				this.writeLine("数据格式输入错误!");
			}

			cmd = this.readCommand();
		}

		if(CMD_QUIT.equalsIgnoreCase(cmd)) {
			sRet = Handler.CMD_QUIT;
		} else {
			sRet = Handler.SUCCESS;
		}
		return sRet;
	}

	/**
	 * Get the action id according to the command
	 * @param cmd input command
	 * @return
	 */
	protected abstract String getActionId(String cmd);
	
	/**
	 * Collect parameters input by user
	 * @param map the params map
	 * @return 
	 */
	protected abstract void doHandler(String cmd, HashMap<String,Object> map);
	
	/**
	 * Display a description about this function
	 */
	protected abstract void showHelp();
	
	/**
	 * @return the functionId
	 */
	public String getFunctionId() {
		return functionId;
	}

	/**
	 * @param functionId the functionId to set
	 */
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	@Override
	public String getDescription() {
		return this.description;
	}
}
