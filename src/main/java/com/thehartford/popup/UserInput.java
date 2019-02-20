package com.thehartford.popup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

public class UserInput {
	
	public String fileName;
	public File[] generatepopup(String directory, String targetdir)
	{
		final Logger logger = Logger.getLogger(UserInput.class);

		JFileChooser fileChoosen = new JFileChooser();
	    fileChoosen.setMultiSelectionEnabled(true);
		JFrame frame = new JFrame();
		fileChoosen.showOpenDialog(frame);
		File[] files =fileChoosen.getSelectedFiles();
		for(int i=0;i<files.length;i++) 
		{
			Path sourceDirectory= Paths.get(files[i].getAbsolutePath());
			fileName = sourceDirectory.getFileName().toString();
			Path targetDirectory = Paths.get(targetdir, fileName);
			try 
			{
				Files.copy(sourceDirectory, targetDirectory,StandardCopyOption.REPLACE_EXISTING);
				logger.info("File successfully copied..");
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}                 

		}
		return files;
	}

}
