package org.apache.solr.handler.dataimport.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class SolrDataImportProperties {
	private Properties properties;

	public static final String SYNC_ENABLED = "syncEnabled";
	public static final String SYNC_CORES = "syncCores";
	public static final String SERVER = "server";
	public static final String PORT = "port";
	public static final String WEBAPP = "webapp";
	public static final String PARAMS = "params";
	public static final String INTERVAL = "interval";

	public static final String REBUILDINDEXPARAMS = "reBuildIndexParams";
	public static final String REBUILDINDEXBEGINTIME = "reBuildIndexBeginTime";
	public static final String REBUILDINDEXINTERVAL = "reBuildIndexInterval";

	Logger logger = Logger.getLogger(getClass());

	public SolrDataImportProperties() {
		// loadProperties(true);
	}

	public void loadProperties(boolean force) {
		try {
			InputStream in = getClass().getClassLoader().getResourceAsStream(
					"solr-scheduler-config.properties");

			properties = new Properties();

			properties.load(in);

		} catch (FileNotFoundException fnfe) {
			logger.error(
					"Error locating DataImportScheduler dataimport.properties file",
					fnfe);
		} catch (IOException ioe) {
			logger.error(
					"Error reading DataImportScheduler dataimport.properties file",
					ioe);
		} catch (Exception e) {
			logger.error("Error loading DataImportScheduler properties", e);
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}