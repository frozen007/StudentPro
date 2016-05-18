/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-5
 * 
 */
package org.zmy.studentpro.action;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * StudentAddAction
 *
 */
public class StudentAddAction extends GenericAction {
	
	private static String SQL_INSERT_STUDENT = "insert into student values(?,?,?)";
	
	private static String SQL_INSERT_STUDENTCLASS = "insert into studentclass values(?,?)";
	
	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.GenericAction#doAction()
	 */
	@Override
	protected String doAction() throws SQLException {
		PreparedStatement ps1 = conn.prepareStatement(SQL_INSERT_STUDENT);
		int i=1;
		ps1.setString(i++, (String) this.params.get("student_no"));
		ps1.setString(i++, (String) this.params.get("name"));
		ps1.setInt(i++, Integer.parseInt((String) this.params.get("age")));
		ps1.executeUpdate();
		ps1.close();
		
		ps1 = conn.prepareStatement(SQL_INSERT_STUDENTCLASS);
		i=1;
		ps1.setString(i++, (String) this.params.get("student_no"));
		ps1.setString(i++, (String) this.params.get("class_no"));
		ps1.executeUpdate();
		ps1.close();
	
		this.writeLine("学生信息已经成功追加!");
		return Action.SUCCESS;
	}

	@Override
	public void processError(int errorCode) {
		this.write("发生错误!");
		if(errorCode == 1) {
			this.write("录入的学生有重复!");
		} else if(errorCode == 2291) {
			this.write("录入的学生的班级不存在!");
		} else {
			this.write("未知错误!");
		}
		this.writeLine("");
	}

}
