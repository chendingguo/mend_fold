package org.apache.solr.handler.dataimport.scheduler;

import java.util.Timer;

import org.apache.log4j.Logger;



/**
 * 增量更新索引的任务
 * @author zhangliang
 *
 */
public class DeltaImportHTTPGetScheduler extends BaseTimerTask {

	Logger logger = Logger.getLogger(getClass());

	public DeltaImportHTTPGetScheduler(String webAppName, Timer t)
			throws Exception {
		super(webAppName, t);
		logger.info("<index update process> DeltaImportHTTPPostScheduler init");
	}

	public void run() {
		try {
			// check mandatory params
			if (server.isEmpty() || webapp.isEmpty() || params == null
					|| params.isEmpty()) {
				logger.warn("<index update process> Insuficient info provided for data import");
				logger.info("<index update process> Reloading global dataimport.properties");
				reloadParams();
				// single-core
			} else if (singleCore) {
				prepUrlSendHttpGet(params);

				// multi-core
			} else if (syncCores.length == 0
					|| (syncCores.length == 1 && syncCores[0].isEmpty())) {
				logger.warn("<index update process> No cores scheduled for data import");
				logger.info("<index update process> Reloading global dataimport.properties");
				reloadParams();

			} else {
				for (String core : syncCores) {
					prepUrlSendHttpGet(core, params);
				}
			}
		} catch (Exception e) {
			logger.error("Failed to prepare for sendHttpPost", e);
			reloadParams();
		}
	}
}