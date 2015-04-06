package org.apache.solr.handler.dataimport.scheduler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
public  class TestHttpTask  {
	protected String syncEnabled;
	protected String[] syncCores;
	protected String server;
	protected String port;
	protected String webapp;
	protected String params;
	protected String interval;
	protected String cores;
	protected SolrDataImportProperties p;
	protected boolean singleCore;
	
	protected String reBuildIndexParams;
	protected String reBuildIndexBeginTime;
	protected String reBuildIndexInterval;

	Logger logger = Logger.getLogger(getClass());

	

	

	protected void fixParams(String webAppName) {
		if (server == null || server.isEmpty())
			server = "localhost";
		if (port == null || port.isEmpty())
			port = "8080";
		if (webapp == null || webapp.isEmpty())
			webapp = webAppName;
		if (interval == null || interval.isEmpty() || getIntervalInt() <= 0)
			interval = "30";
		if (reBuildIndexBeginTime == null || reBuildIndexBeginTime.isEmpty())
			interval = "00:00:00";
		if (reBuildIndexInterval == null || reBuildIndexInterval.isEmpty()
				|| getReBuildIndexIntervalInt() <= 0)
			reBuildIndexInterval = "0";
	}

	protected void prepUrlSendHttpPost(String params) {
		String coreUrl = "http://" + server + ":" + port + "/" + webapp
				+ params;
		sendHttpPost(coreUrl, null);
	}

	protected void prepUrlSendHttpPost(String coreName, String params) {
		String coreUrl = "http://" + server + ":" + port + "/" + webapp + "/"
				+ coreName + params;
		sendHttpPost(coreUrl, coreName);
	}

	protected void sendHttpPost(String completeUrl, String coreName) {
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss SSS");
		Date startTime = new Date();

		// prepare the core var
		String core = coreName == null ? "" : "[" + coreName + "] ";

		logger.error(core
				+ "<index update process> Process started at .............. "
				+ df.format(startTime));

		try {

			URL url = new URL(completeUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			conn.setRequestProperty("type", "submit");
			conn.setDoOutput(true);

			// Send HTTP POST
			conn.connect();

			logger.error(core + "<index update process> Full URL\t\t\t\t"
					+ conn.getURL());
			logger.error(core + "<index update process> Response message\t\t\t"
					+ conn.getResponseMessage());
			logger.error(core + "<index update process> Response code\t\t\t"
					+ conn.getResponseCode());

			// listen for change in properties file if an error occurs
		

			conn.disconnect();
			logger.error(core
					+ "<index update process> Disconnected from server\t\t"
					+ server);
			Date endTime = new Date();
			logger.error(core
					+ "<index update process> Process ended at ................ "
					+ df.format(endTime));
		} catch (MalformedURLException mue) {
			logger.error("Failed to assemble URL for HTTP POST", mue);
		} catch (IOException ioe) {
			logger.error(
					"Failed to connect to the specified URL while trying to send HTTP POST",
					ioe);
		} catch (Exception e) {
			logger.error("Failed to send HTTP POST", e);
		}
	}

	public int getIntervalInt() {
		try {
			return Integer.parseInt(interval);
		} catch (NumberFormatException e) {
			logger.warn(
					"Unable to convert 'interval' to number. Using default value (30) instead",
					e);
			return 30; // return default in case of error
		}
	}

	public int getReBuildIndexIntervalInt() {
		try {
			return Integer.parseInt(reBuildIndexInterval);
		} catch (NumberFormatException e) {
			logger.info(
					"Unable to convert 'reBuildIndexInterval' to number. do't rebuild index.",
					e);
			return 0; // return default in case of error
		}
	}

	public Date getReBuildIndexBeginTime() {
		Date beginDate = null;
		try {
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = sdfDate.format(new Date());
			beginDate = sdfDate.parse(dateStr);
			if (reBuildIndexBeginTime == null
					|| reBuildIndexBeginTime.isEmpty()) {
				return beginDate;
			}
			if (reBuildIndexBeginTime.matches("\\d{2}:\\d{2}:\\d{2}")) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				beginDate = sdf.parse(dateStr + " " + reBuildIndexBeginTime);
			} else if (reBuildIndexBeginTime
					.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				beginDate = sdf.parse(reBuildIndexBeginTime);
			}
			return beginDate;
		} catch (ParseException e) {
			logger.warn(
					"Unable to convert 'reBuildIndexBeginTime' to date. use now time.",
					e);
			return beginDate;
		}
	}
	
	public static void main(String[] args) {
		String coreUrl = "http://" + "localhost" + ":" + 8080 + "/" + "solr" + "/"
				+ "collection1" + "/dataimport?command=delta-import&clean=false&commit=true";
		new TestHttpTask().sendHttpPost(coreUrl, "collection1");
	}

}
