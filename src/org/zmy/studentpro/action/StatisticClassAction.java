/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-6
 * 
 */
package org.zmy.studentpro.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import org.zmy.util.RowSetUtil;

/**
 * StatisticClassAction
 *
 */
public class StatisticClassAction extends GenericAction {
	
	private static String SQL_STATISTIC_CLASS = "SELECT " 
		+ "S.NAME, S.STUDENT_NO, SC.CLASS_NO, C.NAME AS COURSES_NAME, R.SCORE "
		+ "FROM STUDENT S, STUDENTCLASS SC, RESULT R, COURSES C "
		+ "WHERE "
		+ "S.STUDENT_NO = SC.STUDENT_NO AND "
		+ "S.STUDENT_NO = R.STUDENT_NO AND "
		+ "R.COURSES_NO = C.COURSES_NO AND "
		+ "R.SCORE < 60 ";
	
	private static String SQL_COURSES_NO = " AND R.COURSES_NO = ? ";
	
	private static String SQL_ORDER_BY = " order by sc.class_no, s.student_no, r.score ";

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.GenericAction#doAction()
	 */
	@Override
	protected String doAction() throws Exception {
		String coursesNo = (String) this.params.get("courses_no");
		
		PreparedStatement ps = null;
		if (coursesNo != null && !"".equals(coursesNo)) {
			ps = conn.prepareStatement(SQL_STATISTIC_CLASS + SQL_COURSES_NO	+ SQL_ORDER_BY);
			ps.setString(1, coursesNo);
		} else {
			ps = conn.prepareStatement(SQL_STATISTIC_CLASS + SQL_ORDER_BY);
		}
		
		ResultSet rs = ps.executeQuery();
		List<HashMap> rowlist = RowSetUtil.getRowsFromResultSet(rs);
		rs.close();
		ps.close();

		if(rowlist.size() == 0) {
			writeLine("找不到相应记录!");
			return Action.FAILURE;
		}
		
		// print the record
		String[] columnNames = new String[] { "姓名", "学号", "班级", "课程", "分数" };
		String[] keyNames = new String[] { "NAME", "STUDENT_NO", "CLASS_NO", "COURSES_NAME", "SCORE" };
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
