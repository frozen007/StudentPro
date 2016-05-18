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
 * StudentQueryAction
 *
 */
public class StudentQueryAction extends GenericAction {
	
	private static String SQL_SELECT_STUDENT_CLASS = "SELECT " 
		+ "C.CLASS_NO, S.STUDENT_NO, S.NAME, S.AGE, C.TEACHER_NAME "
		+ "FROM STUDENT S, STUDENTCLASS SC, CLASS C " 
		+ "WHERE "
		+ "S.STUDENT_NO=SC.STUDENT_NO AND " 
		+ "C.CLASS_NO=SC.CLASS_NO ";
	
	private static String SQL_AND_1 = " AND S.STUDENT_NO = ? ";
	
	private static String SQL_AND_2 = " AND C.CLASS_NO = ? ";

	private static String SQL_ORDER_BY = " ORDER BY C.CLASS_NO,S.STUDENT_NO ";

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.GenericAction#doAction()
	 */
	@Override
	protected String doAction() throws Exception {
		String studentNo = (String) this.params.get("student_no");
		String classNo = (String) this.params.get("class_no");
		
		String sql = SQL_SELECT_STUDENT_CLASS;
		ArrayList<String> list = new ArrayList<String>();
		if (studentNo != null && !"".equals(studentNo)) {
			sql = sql + SQL_AND_1;
			list.add(studentNo);
		}
		
		if (classNo != null && !"".equals(classNo)) {
			sql = sql + SQL_AND_2;
			list.add(classNo);
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
			writeLine("找不到相应记录!");
			return Action.FAILURE;
		}
		
		// print the record
		String[] columnNames = new String[] { "班级", "学号", "姓名", "年龄", "班主任" };
		String[] keyNames = new String[] { "CLASS_NO", "STUDENT_NO", "NAME", "AGE", "TEACHER_NAME" };
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
