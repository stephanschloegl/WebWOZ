package com.webwoz.wizard.server;

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

import com.webwoz.wizard.server.components.NLGDefault;

public class NLGComp implements Component {

	// variables
	private final int type = 4;
	private final String typeName = "NLG";
	private String name;
	private int mode;

	// Sub classes to override this method
	public String getOutput(String input) {
		return "NLG: " + input;
	}

	public int getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public int getMode() {
		return mode;
	}

	public String printType() {
		return typeName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public Component getComponent(int comp, int mode) {
		// instantiate the right NLG component
		switch (comp) {
		case 0:
			return new NLGDefault(mode);
		default:
			return new NLGDefault(mode);
		}
	}

	public String run(String text, String lang) {
		return null;

	}

	public String correct(String semKey, int m) {
		return null;

	}

	public String simulate(String text, int m) {
		return null;
	}

	public String getUttText() {
		return null;
	}

	public String getUttAudio() {
		return null;
	}

	public String getUttMM() {
		return null;
	}

	public String getResult(String text, String ctrl) {
		return text;
	}

	public String getUttTransText() {
		return null;
	}

	public String getUttTransAudio() {
		return null;
	}

	public String getUttTransMM() {
		return null;
	}

}
