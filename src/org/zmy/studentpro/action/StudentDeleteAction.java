/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-6
 * 
 */
package org.zmy.studentpro.action;

import java.sql.PreparedStatement;

/**
 * StudentDeleteAction
 *
 */
public class StudentDeleteAction extends GenericAction {
	
	private static String SQL_DELETE_STUDENTCLASS = "delete from studentclass where student_no=?";
	
	private static String SQL_DELETE_STUDENT = "delete from student where student_no=?";

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.GenericAction#doAction()
	 */
	@Override
	protected String doAction() throws Exception {
		PreparedStatement ps = conn.prepareStatement(SQL_DELETE_STUDENTCLASS);
		
		String studentNo = (String) this.params.get("student_no");
		ps.setString(1, studentNo);
		int rows = ps.executeUpdate();
		ps.close();
		
		if (rows == 0) {
			this.writeLine("没有找到学号为'" + studentNo + "'的学生");
			return Action.FAILURE;
		}
		
		ps = conn.prepareStatement(SQL_DELETE_STUDENT);
		ps.setString(1, studentNo);
		ps.executeUpdate();
		ps.close();
		
		this.writeLine("学生'"+studentNo+"'已经删除!");
		return Action.SUCCESS;
	}

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.Action#processError(int)
	 */
	@Override
	public void processError(int errorCode) {
		this.writeLine("发生错误!");
		if (errorCode == 2292) {
			this.writeLine("请先确认该学生的成绩是否删除!");
		}

	}

}
