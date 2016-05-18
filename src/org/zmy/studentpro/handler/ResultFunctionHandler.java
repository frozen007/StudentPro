/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-6
 * 
 */
package org.zmy.studentpro.handler;

import java.util.HashMap;

/**
 * ResultFunctionHandler
 *
 */
public class ResultFunctionHandler extends FunctionHandler {
	
	private static String CMD_ADD = "Add";
	
	private static String CMD_DEL = "Delete";
	
	private static String CMD_UPD = "Update";
	
	private static String CMD_QUE = "Query";

	public ResultFunctionHandler() {
		this.functionId="Result";
		this.description="ѧ���ɼ���׷�ӣ���ѯ�����º�ɾ��";
	}

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.handler.FunctionHandler#doHandler(java.lang.String, java.util.HashMap)
	 */
	@Override
	protected void doHandler(String cmd, HashMap<String, Object> map) {
		if (CMD_ADD.equalsIgnoreCase(cmd)) {
			String[] params = null;
			while(true) {
				this.writeLine("����������ѧ��ѧ�ţ��γ̱�ţ����Է�������','�Ÿ���:");
				String buf = this.readCommand();
				params = buf.split(",");
				if (params != null && params.length == 3) {
					break;
				} else {
					this.writeLine("�����ʽ��������������!");
				}
			}
			map.put("student_no", params[0]);
			map.put("courses_no", params[1]);
			map.put("score", params[2].trim());
		} else if (CMD_DEL.equalsIgnoreCase(cmd)) {
			String[] params = null;
			while(true) {
				this.writeLine("����������Ҫɾ����ѧ��ѧ�źͿγ̱�ţ���','�Ÿ���(ֻ����ѧ��ʱɾ����ѧ�������гɼ�):");
				String buf = this.readCommand();
				params = buf.split(",");
				if (params != null && params.length > 0) {
					break;
				} else {
					this.writeLine("�����ʽ��������������!");
				}
			}

			map.put("student_no", params[0]);
			if (params.length == 2) {
				map.put("courses_no", params[1].trim());
			}			
			
		} else if (CMD_UPD.equalsIgnoreCase(cmd)) {
			String[] params = null;
			while(true) {
				this.writeLine("������������Ҫ�޸�ѧ��ѧ�ţ��γ̱�ź��޸ĺ�ķ�������','�Ÿ���:");
				String buf = this.readCommand();
				params = buf.split(",");
				if (params != null && params.length == 3) {
					break;
				} else {
					this.writeLine("�����ʽ��������������!");
				}
			}
			map.put("student_no", params[0]);
			map.put("courses_no", params[1]);
			map.put("score", params[2]);
		} else if (CMD_QUE.equalsIgnoreCase(cmd)) {
			this.writeLine("����������༶�ţ�ѧ�ţ���','�Ÿ���:");
			this.writeLine("(���������Ŀ���ÿո��㣬��������ʱ�г����п��Գɼ�)");
			String buf = this.readCommand();
			String[] params = buf.split(",");
			if (params == null || params.length == 0) {
				
			} else if (params.length == 1) {
				map.put("class_no", params[0].trim());
			} else {
				map.put("class_no", params[0].trim());
				map.put("student_no", params[1].trim());
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

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.handler.FunctionHandler#showHelp()
	 */
	@Override
	protected void showHelp() {
		this.writeLine(this.description);
		this.writeLine("*׷��ѧ���ɼ�������Add");
		this.writeLine("*ɾ��ѧ���ɼ�������Delete");
		this.writeLine("*��ѯѧ���ɼ�����Query");
		this.writeLine("*�޸�ѧ���ɼ�����Update");
		this.writeLine("");
		this.writeLine("-��ʾ��������Help");
		this.writeLine("-����back����");
	}

}
