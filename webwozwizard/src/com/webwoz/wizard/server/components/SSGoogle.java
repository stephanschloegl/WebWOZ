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

public class SSGoogle extends SSComp {

	private String uttText;
	private String uttAudio;
	private String uttMM;
	private String uttTransText;
	private String uttTransAudio;
	private String uttTransMM;

	public SSGoogle(int mode) {
		super.setName("Google");
		super.setMode(mode);
	}

	public String getResult(String text, String ctrl) {
		// use ctrl to define language
		String inputClean = "";
		if (!text.equals("")) {
			inputClean = text.replaceAll(" ", "%20");
		}
		String link = "http://translate.google.com/translate_tts?tl="
				+ ctrl.substring(1, 3) + "&q=" + inputClean;
		setUttText(text);
		setUttMM("-");
		setUttAudio(link);
		setUttTransText(text);
		setUttTransMM("-");
		setUttTransAudio(link);
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
