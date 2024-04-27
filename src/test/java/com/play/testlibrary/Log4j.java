package com.play.testlibrary;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4j{
	  private static final Logger logger = Logger.getLogger(Log4j.class);

	  public static void log(String message) {
	    // Initialize the logger system with the configuration file
	    PropertyConfigurator.configure("configuration/log4j.properties");

	    // Log the message
	    logger.info(message);
	  

	  }
	
/*	
	
	public static Logger log = Logger.getLogger(Log.class.getName());
	
	
	public static void startTestCase(String sTestCaseName ){  
		 DOMConfigurator.configure("configuration/log4j.xml");
	log.info("< ======Test Started==========="+sTestCaseName+" ======================= >");
	}

	public static void endTestCase(String sTestCaseName){
		 DOMConfigurator.configure("configuration/log4j.xml");
	log.info("# =======Test Completed==========="+sTestCaseName+" ====================== #");
	}

	// Need to create below methods, so that they can be called  

	public static void info(String message) {
	   DOMConfigurator.configure("configuration/log4j.xml");
	   log.info(message);
	}

	public static void warn(String message) {
	    DOMConfigurator.configure("configuration/log4j.xml");
	   log.warn(message);

	}

	public static void error(String message) {
	   DOMConfigurator.configure("configuration/log4j.xml");
	   log.error(message);

	}

	public static void fatal(String message) {
	   DOMConfigurator.configure("log4j.xml");
	   log.fatal(message);

	}

	public static void debug(String message) {
	   DOMConfigurator.configure("log4j.xml");
	   log.debug(message);

	}
*/
	
}
