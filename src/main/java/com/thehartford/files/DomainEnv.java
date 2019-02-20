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

public class DomainEnv {

	public void setDomainEnv() throws IOException {

		final Logger logger = Logger.getLogger(Automation.class);
		
		File file = new File(Constant.SETDOMAINENV_PATH);
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		
		 File temp_file = new File(Constant.TEMP_DOMAIN_PATH);
         BufferedWriter bw = new BufferedWriter(new FileWriter(temp_file));

		String current_line; 
		try {
			
			while ((current_line = br.readLine()) != null){
				if(current_line.contains("set WLS_MEM_ARGS_64BIT") && !current_line.contains("-Xms512m -Xmx1024m")){
					int startIndex = current_line.indexOf("=");
					String replacement = "-Xms512m -Xmx1024m";
					String toBeReplaced = current_line.substring(startIndex + 1, startIndex + 18);
					current_line=current_line.replace(toBeReplaced, replacement);
					
				}
				if(current_line.contains("set WLS_MEM_ARGS_32BIT") && !current_line.contains("-Xms512m -Xmx1024m")){
					int startIndex = current_line.indexOf("=");
					String replacement = "-Xms512m -Xmx1024m";
					String toBeReplaced = current_line.substring(startIndex + 1, startIndex + 18);
					current_line=current_line.replace(toBeReplaced, replacement);
					
				}
		
				if(current_line.contains("set MEM_PERM_SIZE_64BIT=-XX:PermSize") && !current_line.contains("256m")){
					int startIndex = current_line.indexOf("=");
					String replacement = "256m";
					String toBeReplaced = current_line.substring(startIndex + 14, startIndex + 18);
					current_line=current_line.replace(toBeReplaced, replacement);
					
				}
				if(current_line.contains("set MEM_PERM_SIZE_32BIT=-XX:PermSize") && !current_line.contains("256m")){
					int startIndex = current_line.indexOf("=");
					String replacement = "256m";
					String toBeReplaced = current_line.substring(startIndex + 14, startIndex + 17);
					current_line=current_line.replace(toBeReplaced, replacement);
					
				}
				if(current_line.contains("set MEM_MAX_PERM_SIZE_32BIT=-XX:MaxPermSize") && !current_line.contains("256m")){
					int startIndex = current_line.indexOf("=");
					String replacement = "256m";
					String toBeReplaced = current_line.substring(startIndex + 17, startIndex + 21);
					current_line=current_line.replace(toBeReplaced, replacement);
					
				}
				
				bw.write(current_line);
				bw.write("\n");
				
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();

		} 
		br.close();
		if(file.delete()) 
        { 
            logger.info("SetDomainEnv File deleted successfully"); 
        } 
        else
        { 
            logger.info("SetDomainEnv Failed to delete the file"); 
        } 
		bw.close();
		
		File newfile =new File(Constant.SETDOMAINENV_PATH);
		
		if(temp_file.renameTo(newfile)){
			logger.info("SetDomainEnv Rename succesfully");
		}else{
			logger.info("SetDomainEnv Rename failed");
		}

	}


}



