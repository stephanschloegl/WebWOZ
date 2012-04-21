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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.webwoz.wizard.client.DatabaseAccess;

public class SettingsASR implements Screen {

	private VerticalPanel layoutPanel = new VerticalPanel();
	private VerticalPanel settingsPanel = new VerticalPanel();
	private HorizontalPanel asrPanel = new HorizontalPanel();
	private CheckBox asrChk = new CheckBox("ASR");
	private CheckBox textInChk = new CheckBox("Text ");
	private ListBox asrLangList = new ListBox();
	private Label langLabel = new Label("Recognition Language");
	private ListBox corrList = new ListBox();
	private Label corrLabel = new Label("Correction Mode");
	private int compID;

	// other variables
	private int expId;
	private int[][] components;
	private String mediapath;
	private String ssLang;
	private String mtSrc;
	private String mtTrg;
	private String mtoSrc;
	private String mtoTrg;
	private String asrLang;
	private int wiz;

	// RPC
	private DatabaseAccessAsync databaseAccessSvc = GWT
			.create(DatabaseAccess.class);
	private ComponentFactoryAsync componentFactorySvc = GWT
			.create(ComponentFactory.class);

	public SettingsASR(int exp, String path, String ss, String mts, String mtt,
			String mtos, String mtot, String asrl, int w) {
		// store experimentId and mediapath
		expId = exp;
		mediapath = path;
		ssLang = ss;
		mtSrc = mts;
		mtTrg = mtt;
		mtoSrc = mtos;
		mtoTrg = mtot;
		asrLang = asrl;
		wiz = w;
		getSettings();

		// build layout
		asrPanel.clear();
		asrPanel.setWidth("500px");
		textInChk.setStyleName("textInChk");
		asrPanel.add(textInChk);
		asrPanel.add(asrChk);
		asrLangList.clear();
		asrLangList.setStyleName("drp");
		asrLangList.addItem("de");
		asrLangList.addItem("en");
		asrLangList.addItem("es");
		asrLangList.addItem("fr");
		asrLangList.addItem("it");
		asrPanel.add(asrLangList);
		asrPanel.add(langLabel);
		langLabel.setWidth("80px");
		asrPanel.add(corrList);
		asrPanel.add(corrLabel);

		asrPanel.setStyleName("asrPanel");

		settingsPanel.clear();
		settingsPanel.add(asrPanel);

		corrList.clear();
		corrList.setStyleName("drp");
		corrList.addItem("Off");
		corrList.addItem("Wizard Correction");
		corrList.addItem("N-best List");

		layoutPanel.clear();
		layoutPanel.add(settingsPanel);
		layoutPanel.addStyleName("compSet");

		asrChk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				@SuppressWarnings("deprecation")
				boolean checked = ((CheckBox) event.getSource()).isChecked();
				if (checked) {
					changeMode(1, 1);
					asrLangList.setVisible(true);
					langLabel.setVisible(true);
					corrList.setVisible(true);
					corrLabel.setVisible(true);
				} else {
					changeMode(4, 0); // also turn of wizard correction
					asrLangList.setVisible(false);
					langLabel.setVisible(false);
					corrList.setItemSelected(0, true);
					corrList.setVisible(false);
					corrLabel.setVisible(false);
				}

				corrList.setItemSelected(0, true);
			}
		});

		textInChk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				@SuppressWarnings("deprecation")
				boolean checked = ((CheckBox) event.getSource()).isChecked();
				if (checked) {
					changeMode(3, 1);

				} else {
					changeMode(3, 0);

				}
			}
		});

		asrLangList.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeMode(2, 0);
			}

		});

		corrList.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeMode(corrList.getSelectedIndex());
			}
		});
	}

	public VerticalPanel getScreen() {
		return null;
	}

	public VerticalPanel getScreen(int comp, int mode, int id) {
		compID = id;
		// load the right Component
		return layoutPanel;
	}

	private void getSettings() {
		String sql = "Select * from experiment where id = " + expId;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			@SuppressWarnings("deprecation")
			public void onSuccess(String[][] result) {

				// ASR
				if (result[0][14].equals("0")) {
					asrChk.setChecked(false);
					asrLangList.setVisible(false);
					langLabel.setVisible(false);
					corrList.setVisible(false);
					corrLabel.setVisible(false);
				} else {
					asrChk.setChecked(true);
					asrLangList.setVisible(true);
					langLabel.setVisible(true);
					corrList.setVisible(true);
					corrLabel.setVisible(true);
				}

				if (result[0][21].equals("0")) {
					textInChk.setChecked(false);
				} else {
					textInChk.setChecked(true);
				}

				// ASR Lang
				for (int i = 0; i < asrLangList.getItemCount(); i++) {
					if (result[0][13].equals(asrLangList.getItemText(i))) {
						asrLangList.setItemSelected(i, true);
						break;
					}
				}

				// wizard setting
				if (result[0][18].equals("1")) {
					corrList.setItemSelected(2, true);
				} else {
					if (result[0][19].equals("1")) {
						corrList.setItemSelected(1, true);
					} else {
						corrList.setItemSelected(0, true);
					}
				}

			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void changeMode(int type, int newMode) {

		String sql = "";

		switch (type) {

		case 1:
			sql = "Update experiment set asrin = " + newMode + " where id = "
					+ expId + "; ";

			sql = sql + " Update experimentcomponent set component = "
					+ newMode + " where id = " + compID;

			break;
		case 2:
			sql = "Update experiment set asrlang = '"
					+ asrLangList.getItemText(asrLangList.getSelectedIndex())
					+ "' where id = " + expId;
			break;
		case 3:
			sql = "Update experiment set textin = " + newMode + " where id = "
					+ expId + "; ";
			break;
		case 4:
			sql = "Update experiment set asrin = " + newMode
					+ ", wizardcorrection = 0, nbestlist = 0 where id = "
					+ expId + "; ";

			sql = sql + " Update experimentcomponent set component = "
					+ newMode + " where id = " + compID;
			break;
		default:
			break;
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

	private void changeMode(final int newMode) {

		String sql = "Update experimentcomponent set componentmode = "
				+ newMode + " where id = " + compID;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				changeWizardInteraction(newMode);
			}
		};

		databaseAccessSvc.storeData(sql, callback);
	}

	private void changeWizardInteraction(final int m) {

		String sql = "";

		switch (m) {
		case 0:
			sql = "Update experiment set wizardcorrection = 0, nbestlist = 0 where id = "
					+ expId;
			break;
		case 1:
			sql = "Update experiment set wizardcorrection = 1, nbestlist = 0 where id = "
					+ expId;
			break;
		case 2:
			sql = "Update experiment set wizardcorrection = 0, nbestlist = 1 where id = "
					+ expId;
			break;
		}

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				if (m > 0) {
					WebWOZWizard.changeVisiblility(2);
				} else {
					WebWOZWizard.changeVisiblility(3);
					reloadSettings();
				}
			}
		};

		databaseAccessSvc.storeData(sql, callback);

	}

	private void reloadLTCs() {
		String sql = "Select * from experimentcomponent where experimentid = "
				+ expId + " order by rank asc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {
					components = null;
					components = new int[result.length][3];
					for (int i = 0; i < result.length; i++) {
						// add components to local array
						components[i][0] = Integer.parseInt(result[i][2]);
						components[i][1] = Integer.parseInt(result[i][3]);
						components[i][2] = Integer.parseInt(result[i][4]);
					}

					clearComponents();

				} else {
					// System.out.println("No components defined for this experiment!");
				}
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void clearComponents() {

		// Initialize the service remote procedure call
		if (componentFactorySvc == null) {
			componentFactorySvc = GWT.create(ComponentFactory.class);
		}

		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Void result) {
				addComponents();
			}

		};

		componentFactorySvc.clearComponents(callback);

	}

	private void addComponents() {

		// Initialize the service remote procedure call
		if (componentFactorySvc == null) {
			componentFactorySvc = GWT.create(ComponentFactory.class);
		}

		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Void result) {

			}

		};

		componentFactorySvc.pushComponents(components, expId, mediapath,
				ssLang, mtSrc, mtTrg, mtoSrc, mtoTrg, asrLang, wiz, callback);

	}

	public void turnOffCorrectionMode() {
		String sql = "Update experimentcomponent set componentmode = 0 where id = "
				+ compID;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				reloadSettings();
				corrList.setItemSelected(0, true);
				turnOnCorrVisibility(0);
			}
		};

		databaseAccessSvc.storeData(sql, callback);

	}

	@SuppressWarnings("deprecation")
	public void turnOnCorrVisibility(int mode) {
		if (mode == 0) {
			corrList.setVisible(false);
			corrLabel.setVisible(false);
			asrPanel.setWidth("310px");
		} else {
			if (asrChk.isChecked()) {
				corrList.setVisible(true);
				corrLabel.setVisible(true);
				asrPanel.setWidth("500px");
			} else {
				corrList.setVisible(false);
				corrLabel.setVisible(false);
				asrPanel.setWidth("310px");
			}
		}
	}

	private void reloadSettings() {

		String sql = "Select * from experiment where id = " + expId;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				// update settings
				ssLang = result[0][8];
				mtSrc = result[0][9];
				mtTrg = result[0][10];
				mtoSrc = result[0][11];
				mtoTrg = result[0][12];
				asrLang = result[0][13];
				reloadLTCs();
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);

	}

	public void changeVisibility() {

	}
}
