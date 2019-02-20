package com.thehartford.main;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.thehartford.constant.Constant;
import com.thehartford.exception.IllegalCommandException;
import com.thehartford.files.Application;
import com.thehartford.files.CommEnv;
import com.thehartford.files.Configuration;
import com.thehartford.files.DBSetup;
import com.thehartford.files.Directory;
import com.thehartford.files.DomainEnv;
import com.thehartford.files.WebConfiguration;
import com.thehartford.popup.UserInput;
import com.thehartford.reader.ProcessResultReader;

public class Automation {

	public static void main(String[] args) {

		final Logger logger = Logger.getLogger(Automation.class);
		
		Automation automateCertInstall = new Automation();
		String cert_install = null;
		try {
			UUID uid = UUID.randomUUID();
			String randomUUID = uid.toString();

			logger.info("AUTOMATION FOR CERTIFICATE INSTALLATION STARTED");

			String homePath = automateCertInstall.get_commandline_results(Constant.ECHO_CMD_JH);
			String javaBinPath = "cd" + " " + homePath + "\\bin";

			UserInput popup = new UserInput();
			File[] selected_file = popup.generatepopup(Constant.DIR,Constant.TARGET_DIR);
			logger.info("files selected"+selected_file.length);
			for(int i=0;i<selected_file.length;i++) 
			{
				String fileName = selected_file[i].getName();
				logger.info("selected file name "+fileName);
				String alias = fileName.substring(0, fileName.lastIndexOf('.'));

				cert_install = "keytool -keystore " + "\"" + homePath + "\\jre\\lib\\security\\cacerts" + "\""
						+ " -import" + " " + "-alias " + alias + randomUUID + " " + "-file "
						+ "C:\\Certificate\\" + fileName + " " + "-trustcacerts" + " -storepass " + "changeit "
						+ "-noprompt";
				logger.info(cert_install);

				String appendPath = "\"" + javaBinPath + " && " + cert_install + "\"";
				automateCertInstall.get_commandline_results(appendPath);
				logger.info("AUTOMATION FOR CERTIFICATE INSTALLATION ENDED");
			}

			logger.info("AUTOMATION FOR CREATING DIRECTORIES STARTED");
			Directory directy = new Directory();
			directy.directory();
			
			Configuration configuration = new Configuration();
			configuration.listFiles(Constant.PATH);
			logger.info("AUTOMATION FOR CREATING DIRECTORIES ENDED");

			logger.info("AUTOMATION FOR JDBC STARTED");
			DBSetup dbSetup = new DBSetup();
			dbSetup.listJdbcFiles(Constant.JDBC_PATH);
			logger.info("AUTOMATION FOR JDBC ENDED");

			logger.info("AUTOMATION FOR APPLICATION FILE STARTED");
			Application applicationFile= new Application();
			applicationFile.applicationXml(Constant.APPLICATION_PATH);
			logger.info("AUTOMATION FOR APPLICATION FILE ENDED");


			logger.info("AUTOMATION FOR WEB FILE STARTED");
			WebConfiguration webFile = new WebConfiguration();
			webFile.webXml(Constant.WEB_PATH);
			logger.info("AUTOMATION FOR WEB FILE ENDED");
			
			logger.info("AUTOMATION FOR SETDOMAINENV FILE STARTED");
			DomainEnv domainFile = new DomainEnv();
			domainFile.setDomainEnv();
			logger.info("AUTOMATION FOR SETDOMAINENV FILE ENDED.");
			
			logger.info("AUTOMATION FOR COMMENV FILE STARTED");
			CommEnv commFile = new CommEnv();
			commFile.commEnv();
			logger.info("AUTOMATION FOR COMMENV FILE ENDED");
			
			JOptionPane.showMessageDialog(null, "AUTOMATION COMPLETED!!!");
			

		} catch (Exception e) {
			logger.info("Something Wrong ");
			e.printStackTrace();
		}
	}

	public String get_commandline_results(String cmd)
			throws IOException, InterruptedException, IllegalCommandException {

		final Process process = Runtime.getRuntime().exec(String.format("cmd /c %s", cmd));

		final ProcessResultReader stderr = new ProcessResultReader(process.getErrorStream(), "STDERR");
		final ProcessResultReader stdout = new ProcessResultReader(process.getInputStream(), "STDOUT");

		stderr.start();
		stdout.start();

		final int exitValue = process.waitFor();
		if (exitValue == 0) {
			Constant.RESULT = stdout.toString();
		} else {
			Constant.RESULT = stderr.toString();
		}

		return Constant.RESULT;
	}

}
