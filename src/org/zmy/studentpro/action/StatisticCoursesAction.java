/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-6
 * 
 */
package org.zmy.studentpro.action;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import org.zmy.util.RowSetUtil;

/**
 * StatisticCoursesAction
 *
 */
public class StatisticCoursesAction extends GenericAction {
	
	private static String SQL_COURSES_MAX = "SELECT C.NAME, MAX(R.SCORE) AS SCORE "
			+ "FROM RESULT R, COURSES C "
			+ "WHERE R.COURSES_NO = C.COURSES_NO "
			+ "GROUP BY (C.NAME) ";

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.GenericAction#doAction()
	 */
	@Override
	protected String doAction() throws Exception {
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(SQL_COURSES_MAX);
		List<HashMap> rowlist = RowSetUtil.getRowsFromResultSet(rs);
		rs.close();
		stat.close();
		
		if(rowlist.size() == 0) {
			writeLine("找不到相应记录!");
			return Action.FAILURE;
		}
		
		// print the record
		String[] columnNames = new String[] { "课程", "最高分数" };
		String[] keyNames = new String[] { "NAME", "SCORE" };
		StringBuilder sbuilder = RowSetUtil.rowSetPrint(columnNames, keyNames, rowlist);
		write(sbuilder.toString());
		writeLine("\n");

		return Action.SUCCESS;
	}

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.Action#processError(int)
	 */
	@Override
	public void processError(int errorCode) {

	}

}
