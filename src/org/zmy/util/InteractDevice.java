/**
 * Project StudentPro
 * by zhao-mingyu at 2008-3-5
 * 
 */
package org.zmy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * InteractDevice
 *
 */
public abstract class InteractDevice {
	
	private PrintWriter pw = IODeviceLocator.outputDevice;
	
	private BufferedReader reader = IODeviceLocator.inputDevice;
	
	private PrintWriter logger = IODeviceLocator.logger;
	
	public String readCommand() {
		String cmd = "";
		try {
			this.pw.print(">");
			this.pw.flush();
			cmd = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cmd;
	}
	
	public void writeLine(Object msg) {
		pw.println(msg);
	}
	
	public void write(Object msg) {
		pw.print(msg);
	}
	
	public void log(Object msg) {
		if (logger != null) {
			logger.println("LOG:"+msg);
		}
	}

}
