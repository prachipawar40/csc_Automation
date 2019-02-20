package com.thehartford.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class ProcessResultReader extends Thread {
	final InputStream is;
	final String type;
	final StringBuilder sb;

	public ProcessResultReader(final InputStream is, String type){
		this.is = is;
		this.type = type;
		this.sb = new StringBuilder();
	}

	public void run()
	{
		final Logger logger = Logger.getLogger(ProcessResultReader.class);
		try{

			final InputStreamReader isr = new InputStreamReader(is);
			final BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null)
			{

				this.sb.append(line);
				logger.info(line);       
				if(line.equalsIgnoreCase("Certificate was added to keystore")){
					logger.info("Certificate installed successfully...");
					break;
				}
			}
		}
		catch (final IOException ioe)
		{
			logger.info(ioe.getMessage());
			throw new RuntimeException(ioe);
		}
	}
	@Override
	public String toString()
	{
		return this.sb.toString();
	}

}
