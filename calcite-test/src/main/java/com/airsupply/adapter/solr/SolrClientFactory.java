package com.airsupply.adapter.solr;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
/**
 * solr client factory 
 * @author arisupply
 *
 */
public class SolrClientFactory {
	static Map<String, SolrClient> sorlClientPool = new ConcurrentHashMap<String, SolrClient>();
	public static SolrClient solrClient = null;
   /**
    * get the solr client
    * @param ip
    * @param port
    * @param coreName 
    * @return
    */
	public static SolrClient getSolrClient(String ip, int port, String coreName) {
		String key = ip.concat(String.valueOf(port)).concat(coreName);
		solrClient = sorlClientPool.get(key);
		if (solrClient == null) {
			String connUrl = "http://" + ip + ":" + port + "/solr/" + coreName;
			solrClient = new HttpSolrClient(connUrl);
			sorlClientPool.put(key, solrClient);

		}
		return solrClient;
	}

}
