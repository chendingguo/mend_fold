package com.http;
import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientPost {

	static HttpClientContext context = null;

	public static void main(String[] args) throws Exception {

		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

			public String handleResponse(final HttpResponse response)
					throws ClientProtocolException, IOException {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					System.out.println(status);
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity) : null;
				} else {
					throw new ClientProtocolException(
							"Unexpected response status: " + status);
				}
			}

		};
		HttpClientContext context = HttpClientContext.create();
		String geetest_challenge = "0e015e57bf7332744fc657f68fd32b9f3g";
		String geetest_validate = "7a7ebe903b6519e04d06cc4dd90a2c3c";
		String geetest_seccode = geetest_validate + "%7Cjordan";

		StringEntity reqEntity = new StringEntity(
				"itemid=244232%2C244231%2C244230%2C244224%2C244223%2C&voteid=8726&stype=savemore&geetest_challenge="
						+ geetest_challenge
						+ "&geetest_validate="
						+ geetest_validate
						+ "&geetest_seccode="
						+ geetest_seccode + "&num=2366");
		reqEntity.setContentType("application/x-www-form-urlencoded");
		URI sendMsgUri = new URIBuilder().setScheme("http")
				.setHost("www.jinta.ccoo.cn").setPath("/tp/vote_save.asp")
				.build();

		HttpPost httppost = new HttpPost(sendMsgUri);
		httppost.setEntity(reqEntity);
		httppost.setHeader("x-requested-with", "XMLHttpRequest");
		httppost.setHeader("Accept-Encoding", "gzip,deflate");
		httppost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		httppost.setHeader("Connection", "keep-alive");
		httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httppost.setHeader("Accept", "*/*");
		httppost.setHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
		httppost.setHeader("Pragma", "no-cache");
		httppost.setHeader("Origin", "http://www.jinta.ccoo.cn");
		httppost.setHeader("Referer",
				"http://www.jinta.ccoo.cn/tp/item_list.asp?voteid=8726&qq-pf-to=pcqq.group");
		httppost.setHeader("Host", "www.jinta.ccoo.cn");
		httppost.setHeader(
				"Cookie",
				"CNZZDATA3834609=cnzz_eid%3D1630933942-1425281640-%26ntime%3D1425351992; vitem8726=/244232_123%u53BF%u8D28%u76D1%u5C40/244231_122%u53BF%u5B89%u76D1%u5C40/244230_121%u53BF%u7802%u4E1A%u5EFA%u6750%u516C%u53F8/244229_120%u53BF%u4EA4%u901A%u8FD0%u8F93%u5C40/244228_119%u53BF%u53F8%u6CD5%u5C40; ASPSESSIONIDCABBARDT=JIDHAOOBIHBKMOGNAFBMGDKH; safedog-flow-item=BBBD5534145F99BF6E68C86CAF9BA550");

		CloseableHttpClient httpclient = HttpClients.custom().build();

		String sendMsgResponse = httpclient.execute(httppost, responseHandler,
				context);
		System.out.println(sendMsgResponse);

		// //=========get check number
		// URI checkNumUri = new URIBuilder().setScheme("http")
		// .setHost("api.geetest.com").setPath("/ajax.php").setCustomQuery("api=jordan&challenge=f59b28f689e234e17687db642bb96c097z&userresponse=244044b33bd71&passtime=3010&b=810959.1621918&imgload=442&random=866432&a=o?$$$p;?@@A@@A@@@AE::::9::e8898877888:9::::::::9$$$%27!M(///0.1-:(+0I4!()0!9(9D*!c0!s(6C*!Z(7%27?*9!/,7%27D!9*%2768);!q.")
		// .build();
		// HttpGet httpget = new
		// HttpGet("http://api.geetest.com/ajax.php?api=jordan&challenge=588154b46c10f4e17f83488ba8b25147kd&userresponse=beeeeebebbbebebfffcc&passtime=1670&b=961097.1922194&imgload=156&random=119487&a=p$$$r;?B;;::;?@;19::::::::9::;-:::/::9$$$%27!)(,70.7J/3*0O!F-6!k(7!1(78)-4*9),IY(84*!N-");
		// httpget.setHeader(
		// "User-Agent",
		// "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
		// httpget.setHeader("Referer",
		// "http://www.jinta.ccoo.cn/tp/item_list.asp?voteid=8726&qq-pf-to=pcqq.group");
		// httpget.setHeader("Host", "api.geetest.com");
		// httpget.setHeader("Cookie",
		// "GeeTestUser=382d8229dab5436f8e4cdbb7e2fa8262");
		//
		// String getResponseMsg=httpclient.execute(httpget,responseHandler);
		//
		// System.out.println(getResponseMsg);
		httpclient.close();

	}

	public void getCheckNum() {

	}

}