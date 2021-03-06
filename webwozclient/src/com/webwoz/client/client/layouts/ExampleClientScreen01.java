package com.webwoz.client.client.layouts;

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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.webwoz.client.client.DatabaseAccess;
import com.webwoz.client.client.Screen;
import com.webwoz.client.client.DatabaseAccessAsync;

public class ExampleClientScreen01 implements Screen {
	// Panels
	private VerticalPanel layoutPanel = new VerticalPanel();
	private VerticalPanel audioOutputPanel = new VerticalPanel();
	private VerticalPanel textOutputPanel = new VerticalPanel();
	private VerticalPanel mmOutputPanel = new VerticalPanel();
	private HorizontalPanel controlPanel = new HorizontalPanel();
	private VerticalPanel infoPanel = new VerticalPanel();
	private HorizontalPanel contentPanel = new HorizontalPanel();
	private DisclosurePanel translatedUtterancePanel = new DisclosurePanel(
			"Deutscher Text");
	private DisclosurePanel originalUtterancePanel = new DisclosurePanel(
			"Englischer Originaltext");
	private HTML info = new HTML();
	private HTML intro = new HTML(
			"<p><strong>Bitte dr&uuml;cken Sie nun auf <strong>'Version A' </strong> bzw. <strong>'Version B' </strong> um das Experiment zu beginnen.</p>");
	private HTML infoVersion01 = new HTML(
			"<p><strong>Information:</strong><br /> Falls Sie eine Aussage des Systems nicht verstehen haben Sie die M&ouml;glicheit entweder den (maschinell &uuml;bersetzten) Deutschen Text oder den Englischen Orginaltext einzusehen. Bitte klicken sie dazu auf den ensprechenden Pfeil. Um die Session zu beenden dr&uuml;cken Sie bitte auf <strong>'Stop'</strong>.</p>");
	private HTML infoVersion02 = new HTML(
			"<p><strong>Information:</strong><br /> Falls Sie den &uuml;bersetzten Text nicht verstehen haben Sie die M&ouml;glicheit die orginale (Englische) Textversion einzusehen. Bitte klicken sie dazu auf den ensprechenden Pfeil. Um die Session zu beenden dr&uuml;cken Sie bitte auf <strong>'Stop'</strong>.</p>");
	private HTML translUttText = new HTML("");
	private HTML origUttText = new HTML("");

	// Buttons
	private Button startV01Button = new Button("Version A");
	private Button startV02Button = new Button("Version B");
	private Button startV03Button = new Button("Video");
	private Button startV04Button = new Button("English Text & Speech");
	private Button stopButton = new Button("Stop");

	// Refresh interval
	private static final int REFRESH_INTERVAL = 1000;
	private Timer refreshTimer;

	// RPC
	private DatabaseAccessAsync databaseAccessSvc = GWT
			.create(DatabaseAccess.class);

	// Other variables
	private int currentID = 0;
	private int user;
	private int exp;
	private String origText;
	private String transText;
	private String audioFile;
	private String audioTransFile;
	private String mmFile;
	private String mmTransFile;
	private int version;
	private String timestamp;
	private boolean reload;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ExampleClientScreen01(int userId, int expId) {
		user = userId;
		exp = expId;
		// load info
		getInfo();
		startV01Button.setStyleName("button");
		startV02Button.setStyleName("button");
		startV03Button.setStyleName("button");
		startV04Button.setStyleName("button");
		stopButton.setStyleName("button");
		contentPanel.setStyleName("content");
		controlPanel.add(startV01Button);
		controlPanel.add(startV02Button);
		controlPanel.add(startV03Button);
		controlPanel.add(startV04Button);
		controlPanel.add(stopButton);
		stopButton.setVisible(false);
		infoPanel.add(info);
		infoPanel.add(intro);
		infoPanel.setStyleName("infoPanel");

		translatedUtterancePanel.add(translUttText);
		translatedUtterancePanel.setStyleName("textDiscPanel");
		originalUtterancePanel.add(origUttText);
		originalUtterancePanel.setStyleName("textDiscPanel");

		audioOutputPanel.setStyleName("audioOutputPanel");
		mmOutputPanel.setStyleName("mmOutputPanel");
		controlPanel.setStyleName("controlPanel");

		layoutPanel.add(infoPanel);
		layoutPanel.add(textOutputPanel);
		layoutPanel.add(audioOutputPanel);
		layoutPanel.add(mmOutputPanel);
		layoutPanel.add(controlPanel);

		// turn reload on
		reload = true;

		// handler
		startV01Button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				startSession(2);
				version = 1;
				textOutputPanel.add(translatedUtterancePanel);
				textOutputPanel.add(originalUtterancePanel);
				textOutputPanel.setStyleName("textOutputPanel");
				translatedUtterancePanel.setVisible(true);
				translatedUtterancePanel.setOpen(false);
				originalUtterancePanel.setVisible(false);
				originalUtterancePanel.setOpen(false);
				infoPanel.clear();
				infoPanel.add(infoVersion01);
				startV01Button.setVisible(false);
				startV02Button.setVisible(false);
				startV03Button.setVisible(false);
				startV04Button.setVisible(false);
				stopButton.setVisible(true);

				// Start timer to refresh list automatically.
				refreshTimer = new Timer() {
					@Override
					public void run() {
						if (reload) {
							getPlaylist();
							getTimestamp();
						}
					}
				};

				// run refresh
				refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
			}
		});

		startV02Button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				startSession(2);
				version = 2;
				textOutputPanel.add(translatedUtterancePanel);
				textOutputPanel.add(originalUtterancePanel);
				textOutputPanel.setStyleName("textOutputPanel");
				translatedUtterancePanel.setVisible(true);
				translatedUtterancePanel.setOpen(true);
				originalUtterancePanel.setVisible(true);
				originalUtterancePanel.setOpen(false);
				infoPanel.clear();
				infoPanel.add(infoVersion02);
				startV01Button.setVisible(false);
				startV02Button.setVisible(false);
				startV03Button.setVisible(false);
				startV04Button.setVisible(false);
				stopButton.setVisible(true);

				// Start timer to refresh list automatically.
				refreshTimer = new Timer() {
					@Override
					public void run() {
						getPlaylist();
						getTimestamp();
					}
				};

				// run refresh
				refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
			}
		});

		startV03Button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				startSession(2);
				version = 3;
				textOutputPanel.add(translatedUtterancePanel);
				textOutputPanel.add(originalUtterancePanel);
				textOutputPanel.setStyleName("textOutputPanel");
				translatedUtterancePanel.setOpen(false);
				translatedUtterancePanel.setVisible(true);
				originalUtterancePanel.setVisible(false);
				originalUtterancePanel.setOpen(false);
				infoPanel.clear();
				infoPanel.add(infoVersion01);
				startV01Button.setVisible(false);
				startV02Button.setVisible(false);
				startV03Button.setVisible(false);
				startV04Button.setVisible(false);
				stopButton.setVisible(true);

				// Start timer to refresh list automatically.
				refreshTimer = new Timer() {
					@Override
					public void run() {
						getPlaylist();
						getTimestamp();
					}
				};

				// run refresh
				refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
			}
		});

		startV04Button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				startSession(2);
				version = 4;
				textOutputPanel.add(translatedUtterancePanel);
				textOutputPanel.add(originalUtterancePanel);
				textOutputPanel.setStyleName("textOutputPanel");
				translatedUtterancePanel.setVisible(false);
				originalUtterancePanel.setVisible(true);
				originalUtterancePanel.setOpen(true);
				infoPanel.clear();
				infoPanel.add(infoVersion02);
				startV01Button.setVisible(false);
				startV02Button.setVisible(false);
				startV03Button.setVisible(false);
				startV04Button.setVisible(false);
				stopButton.setVisible(true);

				// Start timer to refresh list automatically.
				refreshTimer = new Timer() {
					@Override
					public void run() {
						getTimestamp();
					}
				};

				// run refresh
				refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
			}
		});

		stopButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				startSession(4);
				infoPanel.clear();
				infoPanel.add(intro);
				translUttText.setHTML("");
				origUttText.setHTML("");
				textOutputPanel.clear();
				audioOutputPanel.clear();
				mmOutputPanel.clear();
				startV01Button.setVisible(true);
				startV02Button.setVisible(true);
				startV03Button.setVisible(true);
				startV04Button.setVisible(true);
				stopButton.setVisible(false);

				// stop timer
				refreshTimer = null;
			}
		});

		translatedUtterancePanel.addOpenHandler(new OpenHandler() {
			public void onOpen(OpenEvent event) {

				if (currentID != 0) {
					setTranslatedUtteranceFlag();
					originalUtterancePanel.setVisible(true);
				}
			}

		});

		originalUtterancePanel.addOpenHandler(new OpenHandler() {
			public void onOpen(OpenEvent event) {

				if (currentID != 0) {
					setOriginalUtteranceFlag();
				}
			}

		});

	}

	public VerticalPanel getScreen() {
		return layoutPanel;
	}

	private void getPlaylist() {
		String sql = "Select * from output where experiment = " + exp
				+ " and user = " + user + " and played = 0 order by id desc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {
					if (version == 1) {
						translatedUtterancePanel.setOpen(false);
						originalUtterancePanel.setVisible(false);
					}
					if (version != 4) {
						originalUtterancePanel.setOpen(false);
					}
					currentID = Integer.parseInt(result[0][0]);
					origText = result[0][1];
					audioFile = result[0][2];
					mmFile = result[0][3];
					transText = result[0][4];
					audioTransFile = result[0][5];
					mmTransFile = result[0][6];
					audioOutputPanel.clear();
					mmOutputPanel.clear();
					translUttText.setHTML("");
					origUttText.setHTML("");

					switch (version) {
					case 1: // audio translation & text trans+orig
						if (!origText.equals("null")) {
							origUttText.setHTML(origText);
						}
						if (!transText.equals("null")) {
							translUttText.setHTML(transText);
						}
						if (!audioTransFile.equals("null")) {
							audioOutputPanel.add(new HTML(audioTransFile));
						}
						break;
					case 2: // text only
						if (!origText.equals("null")) {
							origUttText.setHTML(origText);
						}
						if (!transText.equals("null")) {
							translUttText.setHTML(transText);
						}
						break;
					case 3: // mm only
						if (!mmFile.equals("null")) {
							mmOutputPanel.add(new HTML(mmFile));
						}
						if (!mmTransFile.equals("null")) {
							mmOutputPanel.add(new HTML(mmTransFile));
						}
						break;
					case 4: // english text & speech
						if (!origText.equals("null")) {
							origUttText.setHTML(origText);
						}
						if (!audioFile.equals("null")) {
							audioOutputPanel.add(new HTML(audioFile));
						}
						break;

					default: // all channels
						if (!origText.equals("null")) {
							origUttText.setHTML(origText);
						}
						if (!transText.equals("null")) {
							translUttText.setHTML(transText);
						}
						if (!audioFile.equals("null")) {
							audioOutputPanel.add(new HTML(audioFile));
						}
						if (!mmFile.equals("null")) {
							mmOutputPanel.add(new HTML(mmFile));
						}
						if (!audioTransFile.equals("null")) {
							audioOutputPanel.add(new HTML(audioTransFile));
						}
						if (!mmTransFile.equals("null")) {
							mmOutputPanel.add(new HTML(mmTransFile));
						}
						break;
					}

					updatePlaylist();

				} else {

				}

			}

		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void updatePlaylist() {

		String sql = "Update output set played = 1 where id = " + currentID;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Void result) {

			}
		};

		databaseAccessSvc.storeData(sql, callback);

	}

	private void setTranslatedUtteranceFlag() {

		String sql = "Update output set transtextflag ='" + timestamp
				+ "' where id = " + currentID;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Void result) {

			}
		};

		databaseAccessSvc.storeData(sql, callback);

	}

	private void setOriginalUtteranceFlag() {

		String sql = "Update output set origtextflag ='" + timestamp
				+ "' where id = " + currentID;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Void result) {

			}
		};

		databaseAccessSvc.storeData(sql, callback);
	}

	private void startSession(int signal) {
		String sql = "Insert into output (textorig, audioorig, mmorig, texttrans, audiotrans, mmtrans, experiment, timestamp, played, sig, user) values ('-', '-', '-', '-', '-', '-', "
				+ exp
				+ ", '"
				+ timestamp
				+ "', 1, "
				+ signal
				+ ", "
				+ user
				+ ")";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Void result) {

			}
		};

		databaseAccessSvc.storeData(sql, callback);
	}

	private void getTimestamp() {

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				timestamp = result;
				getPlaylist();
			}
		};

		databaseAccessSvc.getDateTime(callback);
	}

	private void getInfo() {
		String sql = "Select info from experiment where id = " + exp;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {
					info.setHTML(result[0][0]);
				} else {

				}
			}

		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	public void stopReload() {
		refreshTimer.cancel();
		reload = false;
	}

}
