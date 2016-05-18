/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-5
 * 
 */
package org.zmy.studentpro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.zmy.studentpro.action.Action;
import org.zmy.studentpro.action.ActionPool;
import org.zmy.studentpro.handler.Handler;
import org.zmy.util.ConnectionPool;
import org.zmy.util.InteractDevice;

/**
 * ProcessCenter
 *
 */
public class ProcessCenter extends InteractDevice implements ConnectionPool,
		ActionPool {
	
	private Connection conn = null;
	
	private Hashtable<String, Class<Action>> actionMappings = new Hashtable<String, Class<Action>>();
	
	private HashMap<String, String> dbConnection = new HashMap<String, String>();
	
	// <function number, handler>
	private HashMap<String, Handler> funcMap = new HashMap<String, Handler>();
	
	private List<String> descList = new ArrayList<String>();

	public ProcessCenter() {
		
	}
	
	public void initialize(Properties props) throws Exception {
		// props.list(System.out);
		
		HashMap<String, String> funcDesc = new HashMap<String, String>();
		
		Enumeration<String> e = (Enumeration<String>) props.propertyNames();
		while(e.hasMoreElements()) {
			String key = e.nextElement();
			if(key.startsWith("database")) {
				dbConnection.put(key, props.getProperty(key));
			} else if(key.startsWith("studentpro.mainfunc")) {
				try {
					String className = props.getProperty(key);
					Handler h = (Handler) (Class.forName(className).newInstance());
					h.setActionPool(this);
					String funcNo = key.substring("studentpro.mainfunc".length()+1);
					funcMap.put(funcNo, h);
					funcDesc.put(funcNo, h.getDescription());
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			} else if (key.startsWith("studentpro.action")) {
				try {
					String actionId = key.substring("studentpro.action".length() + 1);
					actionMappings.put(actionId.toUpperCase(), (Class<Action>) Class.forName(props.getProperty(key)));
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			} else {
				
			}
		}
		
		try {
			initDBConnection();
		} catch (Exception e1) {
			log(e1);
			this.writeLine("连接数据库失败!请查看连接信息是否正确!并且数据库驱动程序是否正确放置!");
			throw e1;
		}
		
		// sort the descriptions
		Set<String> keyset = funcDesc.keySet();
		List<String> keylist = new ArrayList<String>();
		keylist.addAll(keyset);
		Collections.sort(keylist, new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return Integer.parseInt(o1)- Integer.parseInt(o2);				
			}});
		Iterator<String> itkey = keylist.iterator();
		while (itkey.hasNext()) {
			String key = itkey.next();
			this.descList.add(key + "." + funcDesc.get(key));
		}
	}
	
	private void initDBConnection() throws Exception {
		Class.forName(dbConnection.get("database.driver"));
		String url = dbConnection.get("database.url");
		String user = dbConnection.get("database.user");
		String password = dbConnection.get("database.password");
		this.conn = DriverManager.getConnection(url, user, password);
		this.conn.setAutoCommit(false);
	}
	
	public void process() {
		writeLine("欢迎使用学生成绩管理系统 v1.0");
		displayFunctions();
		String cmd = this.readCommand();
		while (!Handler.CMD_QUIT.equalsIgnoreCase(cmd)) {
			Handler hd = funcMap.get(cmd);
			if (hd != null) {
				String sRet = hd.handle();
				if(Handler.CMD_QUIT.equals(sRet)) {
					log("Received quit command from handler.");
					break;
				}
			} else {
				log("not accepted by any handler");
			}
			displayFunctions();
			cmd = this.readCommand();
		}
		
		// the process is end
		destroyConnections();
		
		writeLine("已经推出学生成绩管理系统!");
	}

	@Override
	public Connection getConnection() {
		return this.conn;
	}

	@Override
	public Action createAction(String actionId) {
		Class<Action> ca = this.actionMappings.get(actionId);
		if (ca == null) {
			log("can't find a action class according to the action id");
			return null;
		}
		log("createAction get action class:"+ca.getName());
		Action a = null;
		try {
			a = ca.newInstance();
			a.setConnectionPool(this);
			a.setActionId(actionId);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		log("createAction get action instance.");
		return a;
	}

	private void displayFunctions() {
		Iterator<String> itDesc = this.descList.iterator();
		this.writeLine("\n");
		this.writeLine("功能列表:(请输入数字进入相应的功能)");
		while(itDesc.hasNext()) {
			this.writeLine(itDesc.next());
		}
		this.writeLine("退出请输入quit");
	}

	@Override
	public void destroyConnections() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			log("exception when close connection!");
			log(e);
		}
		
	}

	@Override
	public void releaseConnection(Connection conn) {
		// there is no need to do anything
	}

}
