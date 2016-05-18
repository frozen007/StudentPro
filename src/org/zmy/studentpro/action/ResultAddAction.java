/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-6
 * 
 */
package org.zmy.studentpro.action;

import java.sql.PreparedStatement;

/**
 * ResultAddAction
 *
 */
public class ResultAddAction extends GenericAction {
	
	private static String SQL_INSERT_RESULT = "insert into result values(?,?,?)";
	
	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.GenericAction#doAction()
	 */
	@Override
	protected String doAction() throws Exception {
		PreparedStatement ps1 = conn.prepareStatement(SQL_INSERT_RESULT);
		int i=1;
		ps1.setString(i++, (String) this.params.get("student_no"));
		ps1.setString(i++, (String) this.params.get("courses_no"));
		ps1.setInt(i++, Integer.parseInt((String) this.params.get("score")));
		ps1.executeUpdate();
		ps1.close();

		this.writeLine("学生成绩已经成功追加!");
		return Action.SUCCESS;
	}

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.Action#processError(int)
	 */
	@Override
	public void processError(int errorCode) {
		this.write("发生错误!");
		if(errorCode == 1) {
			this.write("录入的学生成绩有重复!");
		} else if(errorCode == 2291) {
			this.write("录入的学生不存在!或者课程不存在!");
		} else {
			this.write("未知错误!");
		}
		this.writeLine("");
	}

}
