/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-6
 * 
 */
package org.zmy.studentpro.handler;

import java.util.HashMap;

/**
 * StatisticFunctionHandler
 *
 */
public class StatisticFunctionHandler extends FunctionHandler {

	private static String CMD_STUDENT = "1";
	
	private static String CMD_CLASS = "2";
	
	private static String CMD_COURSES = "3";

	public StatisticFunctionHandler() {
		this.functionId="Statistic";
		this.description="�ɼ���ͳ�ƺͷ���";
	}

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.handler.FunctionHandler#doHandler(java.lang.String, java.util.HashMap)
	 */
	@Override
	protected void doHandler(String cmd, HashMap<String, Object> map) {
		if (CMD_STUDENT.equalsIgnoreCase(cmd)) {
			writeLine("\n");
			writeLine("ѧ��ƽ���ֲ�ѯ:");
		} else if(CMD_CLASS.equalsIgnoreCase(cmd)) {
			writeLine("\n");
			writeLine("�����಻����������ѯ");
			writeLine("������Ҫ��ѯ�Ŀγ̱��(�����������г����пγ�):");
			String buf = this.readCommand();
			map.put("courses_no", buf);
		} else if(CMD_COURSES.equalsIgnoreCase(cmd)) {
			writeLine("\n");
			writeLine("���ſε���߷ֲ�ѯ:");
		} else {
			
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
		this.writeLine("���������ֽ�����Ӧ��ͳ��:");
		this.writeLine("1-��ʾ����ѧ����ƽ����");
		this.writeLine("2-��ʾ����ĳ�ſεĲ���������");
		this.writeLine("3-��ʾ���ſε���߷�");
		this.writeLine("");
		this.writeLine("-��ʾ��������Help");
		this.writeLine("-����back����");
	}

}
