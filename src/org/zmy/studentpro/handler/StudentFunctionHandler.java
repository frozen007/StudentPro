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
		this.description="学生信息的追加，修改，删除，查询";
	}

	/* (non-Javadoc)
	 * @see org.zmy.studentpro.handler.FunctionHandler#doHandler(java.lang.String, java.util.HashMap)
	 */
	@Override
	protected void doHandler(String cmd, HashMap<String, Object> map) {
		if (CMD_ADD.equalsIgnoreCase(cmd)) {
			String[] params = null;
			while(true) {
				this.writeLine("请依次输入学号，姓名，年龄，所在班级号，用','号隔开:");
				String buf = this.readCommand();
				params = buf.split(",");
				if (params != null && params.length == 4) {
					break;
				} else {
					this.writeLine("输入格式错误，请重新输入!");
				}
			}

			map.put("student_no", params[0]);
			map.put("name", params[1]);
			map.put("age", params[2]);
			map.put("class_no", params[3]);
		} else if (CMD_DEL.equalsIgnoreCase(cmd)) {
			this.writeLine("请输入要删除学生的学号，敲回车确认:");
			String buf = this.readCommand();
			map.put("student_no", buf);
		} else if(CMD_UPD.equalsIgnoreCase(cmd)) {
			this.writeLine("请输入要修改学生的学号，敲回车确认:");
			String buf = this.readCommand();
			map.put("student_no", buf);
			
			String[] params = null;
			while(true) {
				this.writeLine("请输入修改后的学生姓名，年龄，所在班级号，用','号隔开，不更新的项目请补空格:");
				buf = this.readCommand();
				params = buf.split(",");
				if (params != null && params.length == 3) {
					break;
				} else {
					this.writeLine("输入格式错误，请重新输入!");
				}
			}
			map.put("name", params[0].trim());
			map.put("age", params[1].trim());
			map.put("class_no", params[2].trim());
			
		} else if(CMD_QUE.equalsIgnoreCase(cmd)) {
			this.writeLine("请依次输入学号，班级号，用','号隔开:");
			this.writeLine("(不输入的项目请用空格补足，都不输入时列出所有学生信息)");
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
		this.writeLine("*追加学生请输入Add");
		this.writeLine("*删除学生请输入Delete");
		this.writeLine("*查询单个学生信息和整个班级信息请输入Query");
		this.writeLine("*修改学生信息输入Update");
		this.writeLine("");
		this.writeLine("-显示帮助输入Help");
		this.writeLine("-输入back返回");
	}

}
