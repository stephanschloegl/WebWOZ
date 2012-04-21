package com.webwoz.wizard.client.wizardlayouts;

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

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.webwoz.wizard.client.Screen;

public class HomeScreen implements Screen {

	private VerticalPanel layout = new VerticalPanel();

	public HomeScreen() {
		layout.add(new HTML(
				"<div><strong>Welcome to WebWOZ the web-based Wizard of Oz Prototyping Platform!</strong><br />Please choose one of your existing experiments or create a new one via 'Organise Experiments'.</div>"));
	}

	public VerticalPanel getScreen() {
		return layout;
	}

	public void stopReload() {

	}

	public void turnOffComponent() {

	}

	public void changeVisibility() {

	}

}
