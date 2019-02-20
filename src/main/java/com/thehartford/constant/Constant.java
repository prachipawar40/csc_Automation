package com.thehartford.constant;

public class Constant {
	
	public static final String ECHO_CMD_JH = "echo %JAVA_HOME%";
	public static final String JH_BIN = "cd bin";
	public static String RESULT = "";
	public static String DIR = "user.home";
	public static String TARGET_DIR = "C:\\Certificate\\";
	public static String PATH = "C:\\CSC2\\pi_csc_common";
	public static String APPCONFIG_PATH = "C:\\CSC2\\appconfig";
	public static String TARGET_DIR_NTCS = "C:\\CSC2\\appconfig\\";
	public static String NTCS_PATH = "C:\\CSC2\\appconfig\\NTCS_Dev.xml";
	public static String WEB_PATH = "C:\\CSC2\\pi_csc_web";
	public static String APPLICATION_PATH="C:\\CSC2\\pi_csc_web\\cscv2app\\src\\main\\application\\META-INF";
	public static String JDBC_PATH = "C:\\WL_HOME_1036\\user_projects\\domains";
	public static String SETDOMAINENV_PATH = "C:\\WL_HOME_1036\\user_projects\\domains\\base_domain\\bin\\setDomainEnv.cmd";
	public static String TEMP_DOMAIN_PATH = "C:\\WL_HOME_1036\\user_projects\\domains\\base_domain\\bin\\setDomain.cmd";
	public static String COMMENV_PATH = "C:\\WL_HOME_1036\\wlserver_10.3\\common\\bin\\commEnv.cmd";
	public static String TEMP_COMMENV_PATH = "C:\\WL_HOME_1036\\wlserver_10.3\\common\\bin\\common.cmd";
	
	public static final String LVP_SERVICE =  " <module>" + "\n" + "<web>" + "\n" + "<web-uri>lvpservice.war</web-uri>"
			+ "\n" + "<context-root>lvpservice</context-root>" + "\n" + "</web>" + "\n" + "</module> ";

	public static final String APP_CONFIG_WEB = " <module>" + "\n" + "<web>" + "\n"
			+ "<web-uri>appConfigWEB.war</web-uri>" + "\n" + "<context-root>appconfig</context-root>" + "\n" + "</web>"
			+ "\n" + "</module> ";

	public static final String OCAWEB = " <module>" + "\n" + "<web>" + "\n" + "<web-uri>ocaweb.war</web-uri>" + "\n"
			+ "<context-root>ocaweb</context-root>" + "\n" + "</web>" + "\n" + "</module> ";

	public static final String QUOTE_EMAIL_SERVICE = " <module>" + "\n" + "<web>" + "\n"
			+ "<web-uri>quoteemailservice.war</web-uri>" + "\n" + "<context-root>quoteemailservice</context-root>"
			+ "\n" + "</web>" + "\n" + "</module> ";

	public static final String OPCWEB = " <module>" + "\n" + "<web>" + "\n" + "<web-uri>opcweb.war</web-uri>" + "\n"
			+ "<context-root>mapsservice</context-root>" + "\n" + "</web>" + "\n" + "</module> ";

	public static final String PI_SERVICE = " <module>" + "\n" + "<web>" + "\n"
			+ "<web-uri>pi_oca_service.war</web-uri>" + "\n" + "<context-root>OCAService</context-root>" + "\n"
			+ "</web>" + "\n" + "</module> ";

	public static final String WEB_SECURITY_FILTER = "<filter>" + "\n" + "<filter-name>WebSecurityFilter</filter-name>"
			+ "\n" + "<filter-class>com.thehartford.pi.service.common.filter.WebSecurityFilter</filter-class>" + "\n"
			+ " <init-param>" + "\n" + "<param-name>excludePatternsError</param-name>" + "\n"
			+ "<param-value>error</param-value>" + "\n" + "</init-param>" + "\n" + "<init-param>" + "\n"
			+ "<param-name>jsfResources</param-name>" + "\n" + "<param-value>javax.faces.resource</param-value>" + "\n"
			+ "</init-param>" + "\n" + "</filter>";

	public static final String SM_FILTER = "<filter>" + "\n" + "<filter-name>SMSessionCookieFilter</filter-name>" + "\n"
			+ "<filter-class> com.thehartford.pi.service.ui.common.filter.SMCookieFilter</filter-class>" + "\n"
			+ "</filter>";

	public static final String WEB_SECURITY_FILTER_MAPPING = "<filter-mapping>" + "\n" + "<filter-name>WebSecurityFilter</filter-name>" + "\n"
			+ "<url-pattern>*.xhtml</url-pattern>" + "\n" + "<url-pattern>/servlet/*</url-pattern>" + "\n" + "</filter-mapping>" + "\n"; 
	
	public static final String SM_FILTER_MAPPING = "<filter-mapping>"
			+ "<filter-name>SMSessionCookieFilter</filter-name>" + "<url-pattern>/*</url-pattern>"
			+ "<dispatcher>FORWARD</dispatcher>" + "<dispatcher>REQUEST</dispatcher>" + "</filter-mapping>";
	
	
	
}
