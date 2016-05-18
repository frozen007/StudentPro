/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-6
 * 
 */
package org.zmy.studentpro;

import java.io.IOException;
import java.util.Properties;

/**
 * StudentProMain
 *
 */
public class StudentProMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties p = new Properties();
		try {
			p.load(StudentProMain.class.getClassLoader().getResourceAsStream("conf.properties"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		ProcessCenter pc = new ProcessCenter();
		try {
			pc.initialize(p);
		} catch (Exception e) {
			return;
		}
		pc.process();

	}

}
