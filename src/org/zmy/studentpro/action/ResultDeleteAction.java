/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-6
 * 
 */
package org.zmy.studentpro.action;

import java.sql.PreparedStatement;

/**
 * ResultDeleteAction
 *
 */
public class ResultDeleteAction extends GenericAction {
	
	private static String SQL_DELETE_RESULT = "delete from result where student_no = ? ";
	
	private static String SQL_DELETE_RESULT_WITH_COURSES = " and courses_no = ? ";

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.GenericAction#doAction()
	 */
	@Override
	protected String doAction() throws Exception {
		PreparedStatement ps1 = null; 
		String studentNo = (String) this.params.get("student_no");
		String coursesNo = (String) this.params.get("courses_no");
		if (coursesNo != null && !"".equals(coursesNo)) {
			ps1 = conn.prepareStatement(SQL_DELETE_RESULT + SQL_DELETE_RESULT_WITH_COURSES);
			int i=1;
			ps1.setString(i++, studentNo);
			ps1.setString(i++, coursesNo);
		} else {
			ps1 = conn.prepareStatement(SQL_DELETE_RESULT);
			ps1.setString(1, studentNo);
		}
		
		int row = ps1.executeUpdate();
		ps1.close();
		if (row == 0) {
			this.writeLine("没有找到删除的对象!");
			return Action.FAILURE;
		}
		
		this.writeLine("学生成绩已经删除!");
		return Action.SUCCESS;
	}

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.Action#processError(int)
	 */
	@Override
	public void processError(int errorCode) {
		

	}

}
