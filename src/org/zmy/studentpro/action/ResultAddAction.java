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

		this.writeLine("ѧ���ɼ��Ѿ��ɹ�׷��!");
		return Action.SUCCESS;
	}

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.action.Action#processError(int)
	 */
	@Override
	public void processError(int errorCode) {
		this.write("��������!");
		if(errorCode == 1) {
			this.write("¼���ѧ���ɼ����ظ�!");
		} else if(errorCode == 2291) {
			this.write("¼���ѧ��������!���߿γ̲�����!");
		} else {
			this.write("δ֪����!");
		}
		this.writeLine("");
	}

}
