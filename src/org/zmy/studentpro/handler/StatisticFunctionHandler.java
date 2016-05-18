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
		this.description="成绩的统计和分析";
	}

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.handler.FunctionHandler#doHandler(java.lang.String, java.util.HashMap)
	 */
	@Override
	protected void doHandler(String cmd, HashMap<String, Object> map) {
		if (CMD_STUDENT.equalsIgnoreCase(cmd)) {
			writeLine("\n");
			writeLine("学生平均分查询:");
		} else if(CMD_CLASS.equalsIgnoreCase(cmd)) {
			writeLine("\n");
			writeLine("各个班不及格名单查询");
			writeLine("请输入要查询的课程编号(不输入编号则列出所有课程):");
			String buf = this.readCommand();
			map.put("courses_no", buf);
		} else if(CMD_COURSES.equalsIgnoreCase(cmd)) {
			writeLine("\n");
			writeLine("各门课的最高分查询:");
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
		this.writeLine("请输入数字进行相应的统计:");
		this.writeLine("1-显示各个学生的平均分");
		this.writeLine("2-显示个班某门课的不及格名单");
		this.writeLine("3-显示各门课的最高分");
		this.writeLine("");
		this.writeLine("-显示帮助输入Help");
		this.writeLine("-输入back返回");
	}

}
