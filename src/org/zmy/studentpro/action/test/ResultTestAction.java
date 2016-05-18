/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-5
 * 
 */
package org.zmy.studentpro.action.test;

import org.zmy.studentpro.action.Action;
import org.zmy.studentpro.action.GenericAction;

/**
 * ResultTestAction
 *
 */
public class ResultTestAction extends GenericAction {

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.GenericAction#doAction()
	 */
	@Override
	protected String doAction() throws Exception {
		log("this is ResultTestAction.");
		writeLine(this.params.get("name"));
		writeLine(this.params.get("age"));
		return Action.SUCCESS;
	}

	@Override
	public void processError(int errorCode) {
		// TODO Auto-generated method stub
		
	}

}
