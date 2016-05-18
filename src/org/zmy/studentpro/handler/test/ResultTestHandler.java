/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-5
 * 
 */
package org.zmy.studentpro.handler.test;

import java.util.HashMap;

import org.zmy.studentpro.handler.FunctionHandler;

/**
 * ResultTestHandler
 *
 */
public class ResultTestHandler extends FunctionHandler {
	
	public ResultTestHandler() {
		this.functionId = "Result";
	}

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.handler.FunctionHandler#doHandler(java.util.HashMap)
	 */
	@Override
	protected void doHandler(String cmd, HashMap<String, Object> map) {
		if("test".equals(cmd)) {
			map.put("name", "zhaomingyu");
		} else if("test1".equals(cmd)) {
			map.put("name", "mingmingyu");
			map.put("age", "25");
		} else {
			
		}
	}

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.handler.FunctionHandler#getActionId(java.lang.String)
	 */
	@Override
	protected String getActionId(String cmd) {
		return functionId+"_"+cmd;
	}

	@Override
	protected void showHelp() {
		// TODO Auto-generated method stub
		
	}

}
