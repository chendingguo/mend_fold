package com.airsupply.solr.adapter.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import com.airsupply.adapter.solr.SolrClientFactory;

public class SolrClientTest {
	String ip = "192.168.202.129";
	int port = 8080;
	String coreName = "core1";

	@Test
	public void testQuery() {
		SolrClient client = SolrClientFactory.getSolrClient(ip, port, coreName);
		try {
			QueryResponse resp = client.query(new SolrQuery("*:*"));
			
			SolrDocumentList docList=resp.getResults();
			for(SolrDocument doc:docList){
				System.out.println(doc.getFieldValueMap());
				
				
			}
			
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
