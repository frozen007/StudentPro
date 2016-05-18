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
 * StatisticStudentAction
 *
 */
public class StatisticStudentAction extends GenericAction {
	
	private static String SQL_STATISTIC_STUDENT = 
		"SELECT TRIM(S.NAME) AS NAME, S.STUDENT_NO, SC.CLASS_NO, TO_CHAR(ROUND(AVG(R.SCORE))) AS SCORE_AVG "
		+ "FROM STUDENT S, STUDENTCLASS SC, RESULT R "
		+ "WHERE "
		+ "S.STUDENT_NO = SC.STUDENT_NO AND "
		+ "S.STUDENT_NO = R.STUDENT_NO "
		+ "GROUP BY (S.NAME, S.STUDENT_NO, SC.CLASS_NO) "
		+ "ORDER BY SCORE_AVG DESC";

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.GenericAction#doAction()
	 */
	@Override
	protected String doAction() throws Exception {
		PreparedStatement ps1 = conn.prepareStatement(SQL_STATISTIC_STUDENT);
		ResultSet rs = ps1.executeQuery();
		List<HashMap> rowlist = RowSetUtil.getRowsFromResultSet(rs);
		rs.close();
		ps1.close();

		if(rowlist.size() == 0) {
			writeLine("找不到相应记录!");
			return Action.FAILURE;
		}

		// print the record
		String[] columnNames = new String[] { "姓名", "学号", "班级", "平均分" };
		String[] keyNames = new String[] { "NAME", "STUDENT_NO", "CLASS_NO", "SCORE_AVG" };
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
