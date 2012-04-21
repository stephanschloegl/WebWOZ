package com.webwoz.wizard.server.components;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.w3c.dom.Document;

import com.webwoz.wizard.server.MTinComp;

public class MTinMicrosoft extends MTinComp {

	private String uttAudioFile;
	private String uttMMFile;
	private String uttText;
	private HttpClient httpClient;

	public MTinMicrosoft(int mode) {
		super.setName("Microsoft Translate");
		super.setMode(mode);
	}

	public String translate(String inputText, String srcLang, String trgLang) {

		// initialize connection
		// adapt proxies and settings to fit your server environment
		initializeConnection("www-proxy.cs.tcd.ie", 8080);
		String translation = null;
		String query = inputText;
		String MICROSOFT_TRANSLATION_BASE_URL = "http://api.microsofttranslator.com/V2/Http.svc/Translate?";
		String appID = "226846CE16BC2542B7916B05CE9284CF4075B843";

		try {
			// encode text
			query = URLEncoder.encode(query, "UTF-8");
			// exchange + for space
			query = query.replace("+", "%20");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}

		String requestURL = MICROSOFT_TRANSLATION_BASE_URL + "appid=" + appID
				+ "&from=" + srcLang + "&to=" + trgLang + "&text=" + query;

		HttpGet getRequest = new HttpGet(requestURL);

		try {
			HttpResponse response = httpClient.execute(getRequest);
			HttpEntity responseEntity = response.getEntity();

			InputStream inputStream = responseEntity.getContent();

			Document myDocument = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(inputStream);

			XPathFactory factory = XPathFactory.newInstance();
			XPath xPath = factory.newXPath();

			translation = (String) xPath.evaluate("/string", myDocument,
					XPathConstants.STRING);

			inputStream.close();

			translation = translation.trim();

			// if the original string did not have a dot at the end, then
			// remove the dot that Microsoft Translator sometimes places at the
			// end!! (not so sure it still happens anyway!)
			if (!inputText.endsWith(".") && (translation.endsWith("."))) {
				translation = translation
						.substring(0, translation.length() - 1);
			}

			setUttText(translation);

		} catch (Exception ex) {
			ex.printStackTrace();
			translation = ex.toString();
		}

		shutDownConnection();
		return translation;
	}

	/**
	 * Initializes the HTTP connection necessary for communicating with
	 * Microsoft Translator API. <br>
	 * An overloaded method should be implemented that does not take any proxy
	 * settings (in case no proxy connection is needed).
	 * 
	 * @param proxyHostName
	 * @param proxyHostPort
	 */
	public void initializeConnection(String proxyHostName, int proxyHostPort) {
		SchemeRegistry supportedSchemes = new SchemeRegistry();
		// Register the "http" and "https" protocol schemes, they are
		// required by the default operator to look up socket factories.
		supportedSchemes.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		supportedSchemes.register(new Scheme("https", SSLSocketFactory
				.getSocketFactory(), 443));
		// prepare parameters
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, "UTF-8");
		HttpProtocolParams.setUseExpectContinue(params, true);

		ClientConnectionManager ccm = new ThreadSafeClientConnManager(params,
				supportedSchemes);

		httpClient = new DefaultHttpClient(ccm, params);

		HttpHost proxyHost = new HttpHost(proxyHostName, proxyHostPort);

		httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
				proxyHost);
	}

	/**
	 * Shuts down the HTTP connection.
	 */
	public void shutDownConnection() {
		httpClient.getConnectionManager().shutdown();
	}

	public String getResult(String text, String ctrl) {
		setUttAudio("");
		setUttMM("");
		return translate(text, ctrl.substring(3, 5), ctrl.substring(5, 7));
	}

	public void setUttAudio(String uttFile) {
		this.uttAudioFile = uttFile;
	}

	public String getUttAudio() {
		return uttAudioFile;
	}

	public void setUttMM(String uttMMFile) {
		this.uttMMFile = uttMMFile;
	}

	public String getUttMM() {
		return uttMMFile;
	}

	public void setUttText(String uttText) {
		this.uttText = uttText;
	}

	public String getUttText() {
		return uttText;
	}

}
