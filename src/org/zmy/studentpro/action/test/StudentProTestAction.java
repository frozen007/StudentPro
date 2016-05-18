/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-5
 * 
 */
package org.zmy.studentpro.action.test;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.zmy.studentpro.action.Action;
import org.zmy.studentpro.action.GenericAction;
import org.zmy.util.RowSetUtil;

/**
 * StudentProTestAction
 *
 */
public class StudentProTestAction extends GenericAction {

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.GenericAction#doAction()
	 */
	@Override
	protected String doAction() throws Exception {
		log("This is StudentProTestAction.");

		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("select * from persons");
		
		List<HashMap> list = RowSetUtil.getRowsFromResultSet(rs);
		
		Iterator<HashMap> it = list.iterator();
		while(it.hasNext()) {
			Map map = it.next();
			writeLine(map.get("PERSON_ID")+"|"+map.get("PERSON_NAME")+"|"+map.get("AGE"));
		}
		
		return Action.SUCCESS;
	}

	@Override
	public void processError(int errorCode) {
		// TODO Auto-generated method stub
		
	}

}
