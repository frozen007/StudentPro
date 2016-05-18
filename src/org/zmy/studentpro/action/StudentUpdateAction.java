/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-6
 * 
 */
package org.zmy.studentpro.action;

import java.sql.PreparedStatement;

/**
 * StudentUpdateAction
 *
 */
public class StudentUpdateAction extends GenericAction {
	
	private static String SQL_LOCK_STUDENT = "select * from student where student_no = ? for update";
	
	private static String SQL_LOCK_STUDENTCLASS = "select * from studentclass where student_no = ? for update";
	
	private static String SQL_UPDATE_STUDENT_HEAD = "update student ";
	
	private static String SQL_UPDATE_STUDENT_AGE = " set age = ? ";
	
	private static String SQL_UPDATE_STUDENT_NAME = " set name = ? ";
	
	private static String SQL_UPDATE_STUDENT_WHERE = " where student_no = ? ";
	
	private static String SQL_UPDATE_STUDENTCLASS = "update studentclass set class_no = ? where student_no = ? ";
	
	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.GenericAction#doAction()
	 */
	@Override
	protected String doAction() throws Exception {
	
		String studentNo = (String) this.params.get("student_no");
		String name = (String) this.params.get("name");
		String age = (String) this.params.get("age");
		String classNo = (String) this.params.get("class_no");
		
		PreparedStatement ps1 = conn.prepareStatement(SQL_LOCK_STUDENT);
		ps1.setString(1, studentNo);
		ps1.executeQuery();
		ps1.close();
		
		ps1 = conn.prepareStatement(SQL_LOCK_STUDENTCLASS);
		ps1.setString(1, studentNo);
		ps1.executeQuery();
		ps1.close();
		
		String sql = "";
		if (age != null && !"".equals(age)) {
			sql = SQL_UPDATE_STUDENT_HEAD + SQL_UPDATE_STUDENT_AGE + SQL_UPDATE_STUDENT_WHERE;
			ps1 = conn.prepareStatement(sql);
			ps1.setInt(1, Integer.parseInt(age));
			ps1.setString(2, studentNo);
			int row = ps1.executeUpdate();
			ps1.close();
			if (row == 0) {
				this.writeLine("没有找到学号为'" + studentNo + "'的学生");
				return Action.FAILURE;
			}
		}

		if(name != null && !"".equals(name)) {
			sql = SQL_UPDATE_STUDENT_HEAD + SQL_UPDATE_STUDENT_NAME + SQL_UPDATE_STUDENT_WHERE;
			ps1 = conn.prepareStatement(sql);
			ps1.setString(1, name);
			ps1.setString(2, studentNo);
			ps1.executeUpdate();
			ps1.close();
		}
		
		if (classNo != null && !"".equals(classNo)) {
			ps1 = conn.prepareStatement(SQL_UPDATE_STUDENTCLASS);
			ps1.setString(1, classNo);
			ps1.setString(2, studentNo);
			ps1.executeUpdate();
			ps1.close();
		}

		this.writeLine("学生'" + studentNo + "'信息更改成功!");
		
		return Action.SUCCESS;
	}

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.Action#processError(int)
	 */
	@Override
	public void processError(int errorCode) {
		this.write("发生错误!");
		if(errorCode == 2291) {
			this.write("录入的学生的班级不存在!");
		}
		this.writeLine("");
	}

}
