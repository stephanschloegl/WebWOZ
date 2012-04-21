package com.webwoz.wizard.client;

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
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.webwoz.wizard.client.DatabaseAccess;

public class SettingsScreen implements Screen {

	// Panels
	private VerticalPanel layoutPanel = new VerticalPanel();
	private HorizontalPanel settingsPanel = new HorizontalPanel();
	private VerticalPanel settingsUserPanel = new VerticalPanel();
	private VerticalPanel settingsASRPanel = new VerticalPanel();
	private VerticalPanel settingsMTPanel = new VerticalPanel();
	private VerticalPanel settingsTAPanel = new VerticalPanel();
	private VerticalPanel settingsDMPanel = new VerticalPanel();
	private VerticalPanel settingsSSPanel = new VerticalPanel();
	private VerticalPanel settingsMMPanel = new VerticalPanel();

	// Labels
	private Label settingsUserHeadingLabel = new Label();
	private Label settingsUser1Label = new Label();
	private Label settingsUser2Label = new Label();
	private Label settingsASRLabel = new Label();
	private Label settingsMTLabel = new Label();
	private Label settingsTALabel = new Label();
	private Label settingsDMLabel = new Label();
	private Label settingsSSLabel = new Label();
	private Label settingsMMLabel = new Label();

	// list boxes
	private ListBox asrList1 = new ListBox();
	private ListBox mtList1 = new ListBox();
	private ListBox taList1 = new ListBox();
	private ListBox dmList1 = new ListBox();
	private ListBox ssList1 = new ListBox();
	private ListBox mmList1 = new ListBox();
	private ListBox asrList2 = new ListBox();
	private ListBox mtList2 = new ListBox();
	private ListBox taList2 = new ListBox();
	private ListBox dmList2 = new ListBox();
	private ListBox ssList2 = new ListBox();
	private ListBox mmList2 = new ListBox();

	// RPC
	private DatabaseAccessAsync databaseAccessSvc = GWT
			.create(DatabaseAccess.class);

	// other Variables
	private String[][] settings = new String[2][13];

	SettingsScreen(int user) {
		// format Labels
		settingsUserHeadingLabel.setText("Client");
		settingsUser1Label.setText("Client 1:");
		settingsUser1Label.setHeight("23px");
		settingsUser2Label.setText("Client 2:");
		settingsUser2Label.setHeight("23px");
		settingsASRLabel.setText("ASR");
		settingsMTLabel.setText("MT");
		settingsTALabel.setText("TA");
		settingsDMLabel.setText("DM");
		settingsSSLabel.setText("SS");
		settingsMMLabel.setText("Multi-Media");

		// add List items
		asrList1.addItem("-");
		asrList2.addItem("-");

		mtList1.addItem("-");
		mtList1.addItem("CNGL MaTreX");
		mtList1.addItem("Google Translate");
		mtList1.addItem("Microsoft Bing");
		mtList2.addItem("-");
		mtList2.addItem("CNGL MaTreX");
		mtList2.addItem("Google Translate");
		mtList2.addItem("Microsoft Bing");

		taList1.addItem("-");
		taList2.addItem("-");

		dmList1.addItem("-");
		dmList2.addItem("-");

		ssList1.addItem("-");
		ssList1.addItem("CNGL Muse");
		ssList1.addItem("Google Speech");
		ssList1.addItem("Recorded Speech");
		ssList2.addItem("-");
		ssList2.addItem("CNGL Muse");
		ssList2.addItem("Google Speech");
		ssList2.addItem("Recorded Speech");

		mmList1.addItem("-");
		mmList1.addItem("Video Output");
		mmList2.addItem("-");
		mmList2.addItem("Video Output");

		// build settings panels; add second settings if 2 users
		settingsUserPanel.add(settingsUserHeadingLabel);
		settingsUserPanel.add(settingsUser1Label);
		if (user == 2) {
			settingsUserPanel.add(settingsUser2Label);
		}
		settingsUserPanel.addStyleName("settingsUserPanel");
		settingsASRPanel.add(settingsASRLabel);
		settingsASRPanel.add(asrList1);
		if (user == 2) {
			settingsASRPanel.add(asrList2);
		}

		settingsTAPanel.add(settingsTALabel);
		settingsTAPanel.add(taList1);
		if (user == 2) {
			settingsTAPanel.add(taList2);
		}

		settingsDMPanel.add(settingsDMLabel);
		settingsDMPanel.add(dmList1);
		if (user == 2) {
			settingsDMPanel.add(dmList2);
		}

		settingsMTPanel.add(settingsMTLabel);
		settingsMTPanel.add(mtList1);
		if (user == 2) {
			settingsMTPanel.add(mtList2);
		}

		settingsSSPanel.add(settingsSSLabel);
		settingsSSPanel.add(ssList1);
		if (user == 2) {
			settingsSSPanel.add(ssList2);
		}

		settingsMMPanel.add(settingsMMLabel);
		settingsMMPanel.add(mmList1);
		if (user == 2) {
			settingsMMPanel.add(mmList2);
		}

		// add to Settings Panel
		settingsPanel.add(settingsUserPanel);
		settingsPanel.add(settingsASRPanel);
		settingsPanel.add(settingsTAPanel);
		settingsPanel.add(settingsDMPanel);
		settingsPanel.add(settingsMTPanel);
		settingsPanel.add(settingsSSPanel);
		settingsPanel.add(settingsMMPanel);

		// add to Layout Panel
		layoutPanel.add(settingsPanel);

		// retrieve Settings
		getSettings();

		// listen for change of drop-boxes. Change user A. Extension can be
		// added for experiments wit more users
		asrList1.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeLTC(1);
			}

		});

		asrList2.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeLTC(2);
			}

		});

		mtList1.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeLTC(1);
			}

		});

		mtList2.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeLTC(2);
			}

		});

		taList1.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeLTC(1);
			}

		});

		taList2.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeLTC(2);
			}

		});

		dmList1.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeLTC(1);
			}

		});

		dmList2.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeLTC(2);
			}

		});

		ssList1.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeLTC(1);
			}

		});

		ssList2.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeLTC(2);
			}

		});

		mmList1.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeLTC(1);
			}

		});

		mmList2.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeLTC(2);
			}

		});

	}

	public VerticalPanel getScreen() {
		return layoutPanel;
	}

	private void getSettings() {

		String sql = "Select * from ltc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {
					for (int i = 0; i < result.length; i++) {
						for (int j = 0; j < 13; j++) {
							settings[i][j] = result[i][j];
							settings[i][j] = result[i][j];
							settings[i][j] = result[i][j];
							settings[i][j] = result[i][j];
							settings[i][j] = result[i][j];
							settings[i][j] = result[i][j];
							settings[i][j] = result[i][j];
						}
					}
				}

				// set the drop-down
				setLTC();
			}

		};

		databaseAccessSvc.retrieveData(sql, callback);

	}

	private void setLTC() {

		for (int i = 0; i < 2; i++) {

			if (Integer.parseInt(settings[i][0]) == 1) {
				asrList1.setItemSelected(Integer.parseInt(settings[i][1]), true);
				mtList1.setItemSelected(Integer.parseInt(settings[i][3]), true);
				ssList1.setItemSelected(Integer.parseInt(settings[i][5]), true);
				taList1.setItemSelected(Integer.parseInt(settings[i][7]), true);
				dmList1.setItemSelected(Integer.parseInt(settings[i][9]), true);
				mmList1.setItemSelected(Integer.parseInt(settings[i][11]), true);

			} else {
				asrList2.setItemSelected(Integer.parseInt(settings[i][1]), true);
				mtList2.setItemSelected(Integer.parseInt(settings[i][3]), true);
				ssList2.setItemSelected(Integer.parseInt(settings[i][5]), true);
				taList2.setItemSelected(Integer.parseInt(settings[i][7]), true);
				dmList2.setItemSelected(Integer.parseInt(settings[i][9]), true);
				mmList2.setItemSelected(Integer.parseInt(settings[i][11]), true);
			}
		}

	}

	private void changeLTC(int user) {

		String sql = "";
		if (user == 1) {
			sql = "Update ltc set asr = '" + asrList1.getSelectedIndex()
					+ "', mt = '" + mtList1.getSelectedIndex() + "', ss = '"
					+ ssList1.getSelectedIndex() + "', ta = '"
					+ taList1.getSelectedIndex() + "', dm = '"
					+ dmList1.getSelectedIndex() + "', mm = '"
					+ mmList1.getSelectedIndex() + "' where id = " + user;
		} else {
			sql = "Update ltc set asr = '" + asrList2.getSelectedIndex()
					+ "', mt = '" + mtList2.getSelectedIndex() + "', ss = '"
					+ ssList2.getSelectedIndex() + "', ta = '"
					+ taList2.getSelectedIndex() + "', dm = '"
					+ dmList2.getSelectedIndex() + "', mm = '"
					+ mmList2.getSelectedIndex() + "' where id = " + user;
		}

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {

			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {

			}

		};

		databaseAccessSvc.storeData(sql, callback);

	}

	public void stopReload() {

	}

	public void turnOffComponent() {

	}

	public void changeVisibility() {

	}

}
