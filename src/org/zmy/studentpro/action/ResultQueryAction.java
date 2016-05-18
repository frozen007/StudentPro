/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-6
 * 
 */
package org.zmy.studentpro.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zmy.util.RowSetUtil;

/**
 * ResultQueryAction
 *
 */
public class ResultQueryAction extends GenericAction {
	
	private static String SQL_SELECT_RESULT = "SELECT " 
		+ "SC.CLASS_NO, S.STUDENT_NO, S.NAME AS STUDENT_NAME, C.NAME AS COURSES_NAME, R.SCORE "
		+ "FROM STUDENT S, RESULT R, STUDENTCLASS SC, COURSES C "
		+ "WHERE "
		+ "S.STUDENT_NO = R.STUDENT_NO AND "
		+ "S.STUDENT_NO = SC.STUDENT_NO AND "
		+ "R.COURSES_NO = C.COURSES_NO ";
	
	private static String SQL_AND_1 = " AND SC.CLASS_NO = ? ";	

	private static String SQL_AND_2 = " AND S.STUDENT_NO = ? ";

	private static String SQL_ORDER_BY = " ORDER BY SC.CLASS_NO,S.STUDENT_NO,C.COURSES_NO ";
	
	private static String SQL_SELECT_CREDIT = "SELECT " 
			+ "S.NAME AS STUDENT_NAME, SUM(C.CREDIT) AS CREDIT,COUNT(C.COURSES_NO) AS COUNT "
			+ "FROM STUDENT S, RESULT R, COURSES C "
			+ "WHERE "
			+ "S.STUDENT_NO = R.STUDENT_NO AND "
			+ "R.COURSES_NO = C.COURSES_NO AND "
			+ "R.SCORE >= 60 AND "
			+ "S.STUDENT_NO = ? " 
			+ "GROUP BY(S.NAME) ";

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.GenericAction#doAction()
	 */
	@Override
	protected String doAction() throws Exception {
		String classNo = (String) this.params.get("class_no");
		String studentNo = (String) this.params.get("student_no");
		
		String sql = SQL_SELECT_RESULT;
		ArrayList<String> list = new ArrayList<String>();
		if (classNo != null && !"".equals(classNo)) {
			sql = sql + SQL_AND_1;
			list.add(classNo);
		}

		if (studentNo != null && !"".equals(studentNo)) {
			sql = sql + SQL_AND_2;
			list.add(studentNo);
		}
		
		PreparedStatement ps1 = conn.prepareStatement(sql + SQL_ORDER_BY);
		for (int i = 0; i < list.size(); i++) {
			ps1.setString(i+1, list.get(i));
		}
		
		ResultSet rs = ps1.executeQuery();
		List<HashMap> rowlist = RowSetUtil.getRowsFromResultSet(rs);
		rs.close();
		ps1.close();

		if(rowlist.size() == 0) {
			writeLine("找不到班级号为'" + classNo + "'，学号为'" + studentNo + "'的相应记录!");
			return Action.FAILURE;
		}
		
		String[] columnNames = new String[] { "班级", "学号", "姓名", "课程", "分数" };
		String[] keyNames = new String[] { "CLASS_NO", "STUDENT_NO", "STUDENT_NAME", "COURSES_NAME", "SCORE" };
		StringBuilder sbuilder = RowSetUtil.rowSetPrint(columnNames, keyNames, rowlist);
		write(sbuilder.toString());
		writeLine("\n");

		if (studentNo != null && !"".equals(studentNo)) {
			ps1 = conn.prepareStatement(SQL_SELECT_CREDIT);
			ps1.setString(1, studentNo);
			rs = ps1.executeQuery();
			rowlist = RowSetUtil.getRowsFromResultSet(rs);			
			rs.close();
			ps1.close();
			columnNames = new String[] { "姓名", "获得总学分", "通过的门数" };
			keyNames = new String[] { "STUDENT_NAME", "CREDIT", "COUNT" };
			sbuilder = RowSetUtil.rowSetPrint(columnNames, keyNames, rowlist);
			write(sbuilder.toString());
			writeLine("\n");
		}

		return Action.SUCCESS;
	}

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.Action#processError(int)
	 */
	@Override
	public void processError(int errorCode) {

	}

}
