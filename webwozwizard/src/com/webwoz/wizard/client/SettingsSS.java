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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SettingsSS implements Screen {

	private VerticalPanel layoutPanel = new VerticalPanel();
	private HorizontalPanel settingsPanel = new HorizontalPanel();
	private HTML heading = new HTML("TTS");
	private ListBox compList = new ListBox();
	private CheckBox recordedChk = new CheckBox("Recorded Utterances");
	private ListBox outLangList = new ListBox();
	private int compID;

	// other variables
	private int[][] components;
	private int expId;
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

	public SettingsSS(int exp, String path, String ss, String mts, String mtt,
			String mtos, String mtot, String asrl, int w) {
		// store experimentId
		expId = exp;
		mediapath = path;
		ssLang = ss;
		mtSrc = mts;
		mtTrg = mtt;
		mtoSrc = mtos;
		mtoTrg = mtot;
		asrLang = asrl;
		wiz = w;

		compList.setStyleName("drp");

		outLangList.clear();
		outLangList.setStyleName("drp");
		outLangList.addItem("de");
		outLangList.addItem("en");
		outLangList.addItem("es");
		outLangList.addItem("fr");
		outLangList.addItem("it");

		// build layout
		heading.setWidth("40px");
		settingsPanel.clear();
		settingsPanel.add(heading);
		settingsPanel.add(compList);
		settingsPanel.add(outLangList);
		settingsPanel.add(recordedChk);
		layoutPanel.clear();
		layoutPanel.add(settingsPanel);
		layoutPanel.addStyleName("compSet");

		compList.addItem("Off", "0");
		compList.addItem("MuSE", "2");
		// stop Google support for the moment
		// compList.addItem("Google (experimental)", "3");

		// SRC Lang
		for (int i = 0; i < outLangList.getItemCount(); i++) {
			if (ssLang.equals(outLangList.getItemText(i))) {
				outLangList.setItemSelected(i, true);
				break;
			}
		}

		// component handler
		compList.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeComponent(compList.getValue(compList.getSelectedIndex()));
			}

		});

		recordedChk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				@SuppressWarnings("deprecation")
				boolean checked = ((CheckBox) event.getSource()).isChecked();
				if (checked) {
					compList.setVisible(false);
					outLangList.setVisible(false);
					changeComponent("1");
					WebWOZWizard.changeVisiblility(0);

				} else {
					compList.setVisible(true);
					outLangList.setVisible(true);
					changeComponent(compList.getValue(compList
							.getSelectedIndex()));
					WebWOZWizard.changeVisiblility(1);
				}
			}
		});

		outLangList.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeLang();
			}

		});

	}

	public VerticalPanel getScreen() {
		// overload Method!
		return null;
	}

	@SuppressWarnings("deprecation")
	public VerticalPanel getScreen(int comp, int mode, int id) {
		compID = id;
		// load the right Component => reduce index to consider Recorded Speech
		if (comp > 0) {
			compList.setItemSelected(comp - 1, true);
		} else {
			compList.setItemSelected(comp, true);
		}

		// adapt visibility of language drop down
		if (compList.getSelectedIndex() == 0) {
			outLangList.setVisible(false);
		} else {
			outLangList.setVisible(true);
		}

		// check for recorded speech
		if (comp == 1) {
			recordedChk.setChecked(true);
			compList.setVisible(false);
			outLangList.setVisible(false);
			WebWOZWizard.changeVisiblility(0);
		} else {
			recordedChk.setChecked(false);
			compList.setVisible(true);
			WebWOZWizard.changeVisiblility(1);
		}
		return layoutPanel;
	}

	private void changeComponent(final String value) {
		String sql = "Update experimentcomponent set component = " + value
				+ " where id = " + compID;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {

				// turn off language if not SS is selected
				if (value.equals("0")) {
					outLangList.setVisible(false);
				} else {
					outLangList.setVisible(true);
				}

				reloadSettings();
			}
		};

		databaseAccessSvc.storeData(sql, callback);
	}

	// reload LTCs
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
					System.out
							.println("No components defined for this experiment!");
				}
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
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

	public void stopReload() {

	}

	private void changeLang() {

		String sql = "Update experiment set ssoutputlang = '"
				+ outLangList.getItemText(outLangList.getSelectedIndex())
				+ "' where id = " + expId;

		ssLang = outLangList.getItemText(outLangList.getSelectedIndex());

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				reloadSettings();
			}
		};

		databaseAccessSvc.storeData(sql, callback);
	}

	public void turnOffComponent() {
		String sql = "Update experimentcomponent set component = 0 where id = "
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
				compList.setItemSelected(0, true);
			}
		};

		databaseAccessSvc.storeData(sql, callback);

	}

	@SuppressWarnings("deprecation")
	public void turnOffRecSpeech() {

		if (recordedChk.isChecked()) {
			recordedChk.setChecked(false);
			recordedChk.setVisible(false);
			compList.setVisible(true);
			outLangList.setVisible(true);
			changeComponent("0");
			WebWOZWizard.changeVisiblility(1);
		} else {
			recordedChk.setVisible(false);
		}

	}

	public void turnOnRecSpeech() {
		recordedChk.setVisible(true);
	}

	public void changeVisibility() {

	}

}
