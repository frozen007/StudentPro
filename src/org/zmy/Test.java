package org.zmy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.zmy.studentpro.ProcessCenter;
import org.zmy.util.IODeviceLocator;
import org.zmy.util.RowSetUtil;
import org.zmy.util.StringUtil;

public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Test t = new Test();
		//t.test01();
		t.test02();
		// t.test03();
		// t.test04();

	}
	
	public void test01() throws IOException {
		PrintWriter pw = IODeviceLocator.outputDevice;
		pw.println("hello world");
		
		BufferedReader bf = IODeviceLocator.inputDevice;
		
		String i = bf.readLine();
		
		pw.println(i);
	}
	
	public void test02() {
		Properties p = new Properties();
		try {
			p.load(this.getClass().getClassLoader().getResourceAsStream("conf.properties"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		ProcessCenter pc = new ProcessCenter();
		try {
			pc.initialize(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pc.process();
	}
	
	public void test03() {
		// String out = StringUtil.rightPadding("aa", '0', 5);
		// System.out.println(out);
		
		ArrayList list = new ArrayList();
		HashMap map = new HashMap();
		map.put("name", "赵明宇我是a");
		map.put("age", "25");
		map.put("id", "0001");
		list.add(map);
		
		map = new HashMap();
		map.put("name", "my");
		map.put("age", "25   ");
		map.put("id", "04");
		list.add(map);
		
		System.out.println(RowSetUtil.rowSetPrint(new String[]{"姓名","年龄","编号"}, new String[]{"name","age","id"},list).toString());
	}

	public void test04() throws Exception {
		Class.forName("org.apache.derby.jdbc.ClientDriver");
		Connection con = DriverManager.getConnection("jdbc:derby://localhost/zmydb;user=zmy;password=zmy");
		Statement stat = con.createStatement();
		ResultSet rs = stat.executeQuery("select * from persons");
		List<HashMap> list = RowSetUtil.getRowsFromResultSet(rs);
		Iterator<HashMap> it = list.iterator();
		while(it.hasNext()) {
			HashMap<String, Object> map = it.next();
			Iterator<String> itkey = map.keySet().iterator();
			while(itkey.hasNext()) {
				String key = itkey.next();
				System.out.print(key+":"+map.get(key)+"|");
			}
			System.out.println();
		}
		rs.close();
		stat.close();
		con.close();
	}
}
