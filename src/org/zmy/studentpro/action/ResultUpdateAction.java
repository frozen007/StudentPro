/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-6
 * 
 */
package org.zmy.studentpro.action;

import java.sql.PreparedStatement;

/**
 * ResultUpdateAction
 *
 */
public class ResultUpdateAction extends GenericAction {
	
	private static String SQL_UPDATE_RESULT = "update result set score = ? where student_no = ? and courses_no = ? ";

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.GenericAction#doAction()
	 */
	@Override
	protected String doAction() throws Exception {
		String studentNo = (String) this.params.get("student_no");
		String coursesNo = (String) this.params.get("courses_no");
		int score = Integer.parseInt((String) this.params.get("score"));

		PreparedStatement ps1 = conn.prepareStatement(SQL_UPDATE_RESULT);
		int i=1;
		ps1.setInt(i++, score);
		ps1.setString(i++, studentNo);
		ps1.setString(i++, coursesNo);
		int row = ps1.executeUpdate();
		ps1.close();
		
		if(row == 0) {
			this.writeLine("没有找到相应记录!");
			return Action.FAILURE;
		}
		
		this.writeLine("成绩修改成功!");
		return Action.SUCCESS;
	}

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.Action#processError(int)
	 */
	@Override
	public void processError(int errorCode) {

	}

}
