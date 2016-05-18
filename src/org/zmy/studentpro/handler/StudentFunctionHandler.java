/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-5
 * 
 */
package org.zmy.studentpro.handler;

import java.util.HashMap;

/**
 * StudentFunctionHandler
 *
 */
public class StudentFunctionHandler extends FunctionHandler {
	
	private static String CMD_ADD = "Add";
	
	private static String CMD_DEL = "Delete";
	
	private static String CMD_UPD = "Update";
	
	private static String CMD_QUE = "Query";
	
	public StudentFunctionHandler() {
		this.functionId="Student";
		this.description="ѧ����Ϣ��׷�ӣ��޸ģ�ɾ������ѯ";
	}

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.handler.FunctionHandler#doHandler(java.lang.String, java.util.HashMap)
	 */
	@Override
	protected void doHandler(String cmd, HashMap<String, Object> map) {
		if (CMD_ADD.equalsIgnoreCase(cmd)) {
			String[] params = null;
			while(true) {
				this.writeLine("����������ѧ�ţ����������䣬���ڰ༶�ţ���','�Ÿ���:");
				String buf = this.readCommand();
				params = buf.split(",");
				if (params != null && params.length == 4) {
					break;
				} else {
					this.writeLine("�����ʽ��������������!");
				}
			}

			map.put("student_no", params[0]);
			map.put("name", params[1]);
			map.put("age", params[2]);
			map.put("class_no", params[3]);
		} else if (CMD_DEL.equalsIgnoreCase(cmd)) {
			this.writeLine("������Ҫɾ��ѧ����ѧ�ţ��ûس�ȷ��:");
			String buf = this.readCommand();
			map.put("student_no", buf);
		} else if(CMD_UPD.equalsIgnoreCase(cmd)) {
			this.writeLine("������Ҫ�޸�ѧ����ѧ�ţ��ûس�ȷ��:");
			String buf = this.readCommand();
			map.put("student_no", buf);
			
			String[] params = null;
			while(true) {
				this.writeLine("�������޸ĺ��ѧ�����������䣬���ڰ༶�ţ���','�Ÿ����������µ���Ŀ�벹�ո�:");
				buf = this.readCommand();
				params = buf.split(",");
				if (params != null && params.length == 3) {
					break;
				} else {
					this.writeLine("�����ʽ��������������!");
				}
			}
			map.put("name", params[0].trim());
			map.put("age", params[1].trim());
			map.put("class_no", params[2].trim());
			
		} else if(CMD_QUE.equalsIgnoreCase(cmd)) {
			this.writeLine("����������ѧ�ţ��༶�ţ���','�Ÿ���:");
			this.writeLine("(���������Ŀ���ÿո��㣬��������ʱ�г�����ѧ����Ϣ)");
			String buf = this.readCommand();
			String[] params = buf.split(",");
			if (params == null || params.length == 0) {
				
			} else if (params.length == 1) {
				map.put("student_no", params[0].trim());
			} else {
				map.put("student_no", params[0].trim());
				map.put("class_no", params[1].trim());
			}
		}else {

		}
	}

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.handler.FunctionHandler#getActionId(java.lang.String)
	 */
	@Override
	protected String getActionId(String cmd) {
		String actionId = this.functionId + "_" + cmd;
		return actionId.toUpperCase();
	}

	@Override
	protected void showHelp() {
		this.writeLine(this.description);
		this.writeLine("*׷��ѧ��������Add");
		this.writeLine("*ɾ��ѧ��������Delete");
		this.writeLine("*��ѯ����ѧ����Ϣ�������༶��Ϣ������Query");
		this.writeLine("*�޸�ѧ����Ϣ����Update");
		this.writeLine("");
		this.writeLine("-��ʾ��������Help");
		this.writeLine("-����back����");
	}

}
