/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-5
 * 
 */
package org.zmy.studentpro.handler.test;

import java.util.HashMap;

import org.zmy.studentpro.handler.FunctionHandler;

/**
 * StudentProTestHandler
 *
 */
public class StudentProTestHandler extends FunctionHandler {
	
	public StudentProTestHandler() {
		this.functionId="StudentProTest";
	}

	@Override
	protected void doHandler(String cmd, HashMap<String, Object> map) {
		map.put("name", "zhaomingyu");
	}

	@Override
	protected String getActionId(String cmd) {
		return functionId+"_"+cmd;
	}

	@Override
	protected void showHelp() {
		// TODO Auto-generated method stub
		
	}

}
