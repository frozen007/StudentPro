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
		this.description="学生成绩的追加，查询，更新和删除";
	}

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.handler.FunctionHandler#doHandler(java.lang.String, java.util.HashMap)
	 */
	@Override
	protected void doHandler(String cmd, HashMap<String, Object> map) {
		if (CMD_ADD.equalsIgnoreCase(cmd)) {
			String[] params = null;
			while(true) {
				this.writeLine("请依次输入学生学号，课程编号，考试分数，用','号隔开:");
				String buf = this.readCommand();
				params = buf.split(",");
				if (params != null && params.length == 3) {
					break;
				} else {
					this.writeLine("输入格式错误，请重新输入!");
				}
			}
			map.put("student_no", params[0]);
			map.put("courses_no", params[1]);
			map.put("score", params[2].trim());
		} else if (CMD_DEL.equalsIgnoreCase(cmd)) {
			String[] params = null;
			while(true) {
				this.writeLine("请依次输入要删除的学生学号和课程编号，用','号隔开(只输入学号时删除该学生的所有成绩):");
				String buf = this.readCommand();
				params = buf.split(",");
				if (params != null && params.length > 0) {
					break;
				} else {
					this.writeLine("输入格式错误，请重新输入!");
				}
			}

			map.put("student_no", params[0]);
			if (params.length == 2) {
				map.put("courses_no", params[1].trim());
			}			
			
		} else if (CMD_UPD.equalsIgnoreCase(cmd)) {
			String[] params = null;
			while(true) {
				this.writeLine("请依次输入所要修改学生学号，课程编号和修改后的分数，用','号隔开:");
				String buf = this.readCommand();
				params = buf.split(",");
				if (params != null && params.length == 3) {
					break;
				} else {
					this.writeLine("输入格式错误，请重新输入!");
				}
			}
			map.put("student_no", params[0]);
			map.put("courses_no", params[1]);
			map.put("score", params[2]);
		} else if (CMD_QUE.equalsIgnoreCase(cmd)) {
			this.writeLine("请依次输入班级号，学号，用','号隔开:");
			this.writeLine("(不输入的项目请用空格补足，都不输入时列出所有考试成绩)");
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
		this.writeLine("*追加学生成绩请输入Add");
		this.writeLine("*删除学生成绩请输入Delete");
		this.writeLine("*查询学生成绩输入Query");
		this.writeLine("*修改学生成绩输入Update");
		this.writeLine("");
		this.writeLine("-显示帮助输入Help");
		this.writeLine("-输入back返回");
	}

}
