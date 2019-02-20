package com.thehartford.files;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.thehartford.constant.Constant;
import com.thehartford.main.Automation;

public class CommEnv {

	public void commEnv() throws IOException {
		
		final Logger logger = Logger.getLogger(Automation.class);
		
		File file = new File(Constant.COMMENV_PATH);
		BufferedReader br = new BufferedReader(new FileReader(file));

		File temp_file = new File(Constant.TEMP_COMMENV_PATH);
		BufferedWriter bw = new BufferedWriter(new FileWriter(temp_file));

		String current_line;
		String nextLine;

		try {

			while ((current_line = br.readLine()) != null) {
				if (current_line.contains("set BEA_HOME=")) {
					bw.write(current_line);
					bw.write("\n");
					nextLine = br.readLine();
					logger.info("This is nextline::" + nextLine);
					if (!nextLine.contains("set PRE_CLASSPATH=%BEA_HOME%\\modules\\javax.persistence_1.1.0.0_2-0.jar;%BEA_HOME%\\modules\\com.oracle.jpa2support_1.0.0.0_2-1.jar")) {
						bw.write("set PRE_CLASSPATH=%BEA_HOME%\\modules\\javax.persistence_1.1.0.0_2-0.jar;%BEA_HOME%\\modules\\com.oracle.jpa2support_1.0.0.0_2-1.jar");
						bw.write("\n");
					}
					logger.info("This is present line:" + current_line);
					bw.write(nextLine);
					bw.write("\n");

				} else {
					
					bw.write(current_line);
					bw.write("\n");
				}
			}

		} catch (IOException e1) {
			e1.printStackTrace();

		}
		br.close();
		bw.close();
		if (file.delete()) {
			logger.info("CommEnvFile deleted successfully");
		} else {
			logger.info("CommEnvFailed to delete the file");
		}

		File newfile = new File(Constant.COMMENV_PATH);

		if (temp_file.renameTo(newfile)) {
			logger.info("CommEnv Renamed succesfully");
		} else {
			logger.info("CommEnv Rename failed");
		}

	}

}
