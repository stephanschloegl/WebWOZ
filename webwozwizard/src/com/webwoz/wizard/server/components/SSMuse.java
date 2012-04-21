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

import com.webwoz.wizard.server.SSComp;

public class SSMuse extends SSComp {

	private String uttText;
	private String uttAudio;
	private String uttMM;
	private String uttTransText;
	private String uttTransAudio;
	private String uttTransMM;

	public SSMuse(int mode) {
		super.setMode(mode);
		super.setName("Muse");
	}

	public String getResult(String text, String ctrl) {
		String speaker = "rich";
		String format = "mp3";
		String lang = "usenglish";
		String inputClean = "";

		if (!text.equals("")) {
			inputClean = text.replaceAll(" ", "%20");
		}
		// using example user
		String html = "http://muster.ucd.ie/beta/tts.aspx?applicationid=b1b02ac60fa6fd6395428d0fc403ddec&language="
				+ lang
				+ "&username=Schlogls&text="
				+ inputClean
				+ "&speaker="
				+ speaker + "&format=" + format;
		setUttText(text);
		setUttMM("-");
		setUttAudio(html);
		setUttTransText(text);
		setUttTransMM("-");
		setUttTransAudio(html);

		return getUttAudio();
	}

	public void setUttText(String uttText) {
		this.uttText = uttText;
	}

	public String getUttText() {
		return uttText;
	}

	public void setUttMM(String uttMM) {
		this.uttMM = uttMM;
	}

	public String getUttMM() {
		return uttMM;
	}

	public void setUttAudio(String uttAudio) {
		this.uttAudio = uttAudio;
	}

	public String getUttAudio() {
		return uttAudio;
	}

	public void setUttTransText(String uttTransText) {
		this.uttTransText = uttTransText;
	}

	public String getUttTransText() {
		return uttTransText;
	}

	public void setUttTransAudio(String uttTransAudio) {
		this.uttTransAudio = uttTransAudio;
	}

	public String getUttTransAudio() {
		return uttTransAudio;
	}

	public void setUttTransMM(String uttTransMM) {
		this.uttTransMM = uttTransMM;
	}

	public String getUttTransMM() {
		return uttTransMM;
	}

}
