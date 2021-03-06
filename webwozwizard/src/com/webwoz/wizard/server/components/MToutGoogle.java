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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
import org.json.JSONObject;

import com.webwoz.wizard.server.MToutComp;

public class MToutGoogle extends MToutComp {

	private String uttAudioFile;
	private String uttMMFile;
	private String uttText;
	private HttpClient httpClient;

	public MToutGoogle(int mode) {
		super.setName("Google Translate");
		super.setMode(mode);
	}

	/**
	 * Submits text to Google AJAX Language API for translation.
	 * 
	 * @param sourceText
	 * @param sourceLanguage
	 * @param targetLanguage
	 * @param sourceTextFormat
	 *            The format of the source Text (either "text" or "html", as
	 *            specified by Google Language AJAX API).
	 * @param apiKey
	 *            API Key, obtained after registering with Google.
	 * @param userIP
	 *            IP address of end user (i.e. currently temporarily set to my
	 *            IP or IP address of the PMIR framework's server machine)
	 * @param referer
	 *            URL of the referrer (e.g. URL of the PMIR framework's main
	 *            page).
	 * @return An object of <code>TransaltedText</code> object.
	 * @throws MalformedLanguageAcronymException
	 *             if the length of a language acronym (source or target) does
	 *             not match the standard acronym length used throughout the
	 *             framework.
	 * @throws UnsupportedLanguageException
	 *             if a language (source or target) is not supported in the
	 *             framework.
	 */
	public String translate(String inputText, String srcLang, String trgLang) {

		// initialize connection
		// adapt proxy and settings to fit your server environment
		initializeConnection("www-proxy.cs.tcd.ie", 8080);
		String translation = null;
		String query = inputText;
		String sourceTextFormat = "html";
		String userIP = "134.226.35.174";
		String referer = "http://kdeg-vm14.cs.tcd.ie";
		String GOOGLE_TRANSLATION_BASE_URL = "https://ajax.googleapis.com/ajax/services/language/translate?v=1.0";

		try {
			// encode text
			query = URLEncoder.encode(query, "UTF-8");
			// exchange + for space
			query = query.replace("+", "%20");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}

		String requestURL = GOOGLE_TRANSLATION_BASE_URL + "&q=" + query
				+ "&langpair=" + srcLang + "%7C" + trgLang + // %7C
				"&format=" + sourceTextFormat + "&userip=" + userIP;

		HttpGet getRequest = new HttpGet(requestURL);

		getRequest.addHeader("Referer", referer);

		try {
			HttpResponse response = httpClient.execute(getRequest);
			HttpEntity responseEntity = response.getEntity();

			StringBuilder jsonStringBuilder = new StringBuilder();

			if (responseEntity != null) {
				InputStream inputStream = responseEntity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream));
				String line = null;
				while ((line = reader.readLine()) != null) {
					jsonStringBuilder.append(line);
				}

				inputStream.close();
				reader.close();
			}

			String jsonString = jsonStringBuilder.toString();
			JSONObject wholeJSONObject = new JSONObject(jsonString);

			JSONObject responseDataObject = wholeJSONObject
					.getJSONObject("responseData");

			translation = responseDataObject.getString("translatedText");

		} catch (Exception ex) {
			ex.printStackTrace();
			translation = ex.toString();
		}

		shutDownConnection();
		return translation;
	}

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
		setUttText(translate(text, ctrl.substring(7, 9), ctrl.substring(9, 11)));
		return getUttText();
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
