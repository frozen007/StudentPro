/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-5
 * 
 */
package org.zmy.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * IODeviceLocator
 *
 */
public class IODeviceLocator {
	
	private static String CONSOLE_TYPE = "console";
	
	public static BufferedReader inputDevice = null;

	public static PrintWriter outputDevice = null;
	
	public static PrintWriter logger = null;

	static {
		// get the device type from system properties
		String inputType = System.getProperty("studentpro.inputType",CONSOLE_TYPE);
		String outputType = System.getProperty("studentpro.outputType", CONSOLE_TYPE);
		
		String logEnable = System.getProperty("studentpro.log");

		// build input device and output device		
		if (CONSOLE_TYPE.equals(outputType)) {
			outputDevice = new PrintWriter(System.out,true);
			// outputDevice.println("output device is console");
		} else {
			// output device to file
			// outputDevice.println("output device is file:" + outputType);
			/**
			 * not implemented yet
			 */
		}

		if (CONSOLE_TYPE.equals(inputType)) {
			inputDevice = new BufferedReader(new InputStreamReader(System.in));
			// outputDevice.println("input device is console");
		} else {
			// input device from file
			// outputDevice.println("input device is file:" + inputType);
			/**
			 * not implemented yet
			 */

		}
		
		if("on".equals(logEnable)) {
			logger = new PrintWriter(System.out,true);
		}
	}
	
	private IODeviceLocator() {
		
	}

}
