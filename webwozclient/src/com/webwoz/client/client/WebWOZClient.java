package com.webwoz.client.client;

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

import java.util.Date;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.media.client.Audio;
import com.google.gwt.media.client.Video;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.webwoz.client.client.DatabaseAccess;
import com.webwoz.client.client.DatabaseAccessAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WebWOZClient implements EntryPoint {

	// panels to structure layout
	private VerticalPanel headPanel;
	private HorizontalPanel titlePanel;
	private VerticalPanel contentPanel;
	private VerticalPanel footPanel;
	private HorizontalPanel loginPanel;
	private VerticalPanel asrPanel;
	private VerticalPanel textPanel;
	private VerticalPanel audioPanel;
	private VerticalPanel mmPanel;
	private HorizontalPanel txtInPanel;

	// labels
	private Label userLabel;
	private Label pwLabel;
	private Label loginMessage;

	// text boxes
	private TextBox userTextBox;
	private PasswordTextBox pwTextBox;

	// buttons
	private Button loginButton;
	private Button logoutButton;
	private Button sendButton;

	// Text area
	private TextArea textInputTextArea;

	// handler
	private ClickHandler sendClickHandler;
	private KeyPressHandler sendKeyPressHandler;
	private ClickHandler loginClickHandler;
	private KeyPressHandler loginUserKeyPressHandler;
	private KeyPressHandler loginPWKeyPressHandler;
	private ClickHandler logoutClickHandler;

	// handler registration
	private HandlerRegistration sendClickHandlerRegistration;
	private HandlerRegistration sendKeyPressHandlerRegistration;
	private HandlerRegistration loginClickHandlerRegistration;
	private HandlerRegistration loginUserKeyPressHandlerRegistration;
	private HandlerRegistration loginPWKeyPressHandlerRegistration;
	private HandlerRegistration logoutClickHandlerRegistration;

	// RPC
	private DatabaseAccessAsync databaseAccessSvc;

	// Refresh interval
	private static final int REFRESH_INTERVAL = 1000;
	private Timer refreshTimer;
	private boolean reload;

	// Other variables
	private int user;
	private int experiment;
	private int wizard;
	private int asr;
	private int text;
	private int audio;
	private int mm;
	private int txtIn;

	private String login;
	private int currentID;
	private String textOutput;
	private String audioFile;
	private String mmFile;

	private Audio audioHTML;
	private Video videoHTML;
	private String processing;

	private boolean useClientLogout;

	public void onModuleLoad() {
		// get login cookie
		login = Cookies.getCookie("loggedin");
		stopSession();
	}

	private void startSession() {

		// define whether client logout should be used or not
		// useClientLogout = true;

		// initialize variables
		processing = "Processing";

		// panels to structure layout
		headPanel = new VerticalPanel();
		titlePanel = new HorizontalPanel();
		contentPanel = new VerticalPanel();
		footPanel = new VerticalPanel();
		loginPanel = new HorizontalPanel();
		asrPanel = new VerticalPanel();
		textPanel = new VerticalPanel();
		audioPanel = new VerticalPanel();
		mmPanel = new VerticalPanel();
		txtInPanel = new HorizontalPanel();

		// labels
		userLabel = new Label("User: ");
		pwLabel = new Label("Password: ");
		loginMessage = new Label();

		// text boxes
		userTextBox = new TextBox();
		pwTextBox = new PasswordTextBox();

		// text areas
		textInputTextArea = new TextArea();

		// buttons
		loginButton = new Button("login");
		logoutButton = new Button("logout");
		sendButton = new Button("Send");

		// RPC
		databaseAccessSvc = GWT.create(DatabaseAccess.class);

		currentID = 0;

		audioHTML = Audio.createIfSupported();
		videoHTML = Video.createIfSupported();

		// head
		String header = "<div id='header'></div>";
		headPanel.clear();
		headPanel.add(new HTML(header));

		// title
		String title = "<div id='title'></div>";
		titlePanel.clear();
		titlePanel.add(new HTML(title));

		// build login
		loginPanel.clear();
		loginPanel.add(userLabel);
		loginMessage.setStyleName("loginMessage");
		userLabel.setStyleName("label");
		loginPanel.add(userTextBox);
		userTextBox.setStyleName("text");
		loginPanel.add(pwLabel);
		pwLabel.setStyleName("label");
		loginPanel.add(pwTextBox);
		pwTextBox.setStyleName("text");
		loginPanel.add(loginButton);
		loginButton.setStyleName("buttonLogin");
		// define button but do not add it
		logoutButton.setStyleName("buttonLogin");
		logoutButton.setVisible(false);

		// loginPanel.setStyleName("layout");
		loginPanel.setVisible(true);

		// output panels
		asrPanel.clear();
		asrPanel.setStyleName("asr");
		txtInPanel.clear();
		txtInPanel.setStyleName("txtIn");
		textPanel.clear();
		textPanel.setStyleName("txtOut");
		audioPanel.clear();
		audioPanel.setStyleName("audioOut");
		mmPanel.clear();
		mmPanel.setStyleName("mmOut");

		// content
		contentPanel.clear();
		contentPanel.setStyleName("content");
		contentPanel.add(loginPanel);
		contentPanel.add(loginMessage);
		contentPanel.add(txtInPanel);
		contentPanel.add(asrPanel);
		contentPanel.add(textPanel);
		contentPanel.add(audioPanel);
		contentPanel.add(mmPanel);

		// foot
		String foot = "<div id='foot'></div>";
		footPanel.clear();
		footPanel.add(new HTML(foot));

		// other panels to change the layout can be added if needed
		// headPanel.setStyleName("layout");
		// titlePanel.setStyleName("layout");
		// contentPanel.setStyleName("layout");
		// footPanel.setStyleName("layout");

		if (login != null) {
			if (login.equals("1")) {
				stopReload();
				loggedIn();
			} else {
				loadLoginScreen();
			}
		} else {
			loadLoginScreen();
		}

		// RootPanel.get().clear();
		RootPanel.get("head").add(headPanel);
		RootPanel.get("title").add(titlePanel);
		RootPanel.get("content").add(contentPanel);
		RootPanel.get("foot").add(footPanel);

		// handler
		// login click
		loginClickHandler = new ClickHandler() {
			public void onClick(ClickEvent event) {
				login();
			}
		};

		if (loginClickHandlerRegistration != null) {
			loginClickHandlerRegistration.removeHandler();
		}

		loginClickHandlerRegistration = loginButton
				.addClickHandler(loginClickHandler);

		// login keypress
		loginUserKeyPressHandler = new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
					login();
				}
			}

		};
		if (loginUserKeyPressHandlerRegistration != null) {
			loginUserKeyPressHandlerRegistration.removeHandler();
		}
		loginUserKeyPressHandlerRegistration = userTextBox
				.addKeyPressHandler(loginUserKeyPressHandler);

		loginPWKeyPressHandler = new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
					login();
				}
			}

		};
		if (loginPWKeyPressHandlerRegistration != null) {
			loginPWKeyPressHandlerRegistration.removeHandler();
		}
		loginPWKeyPressHandlerRegistration = pwTextBox
				.addKeyPressHandler(loginPWKeyPressHandler);

		// logout click
		logoutClickHandler = new ClickHandler() {
			public void onClick(ClickEvent event) {
				logout();
			}
		};
		if (logoutClickHandlerRegistration != null) {
			logoutClickHandlerRegistration.removeHandler();
		}
		logoutClickHandlerRegistration = logoutButton
				.addClickHandler(logoutClickHandler);

		// send click
		sendClickHandler = new ClickHandler() {
			public void onClick(ClickEvent event) {
				reload = false;
				getTimeStamp(textInputTextArea.getText());
				textInputTextArea.setText("");
			}
		};
		if (sendClickHandlerRegistration != null) {
			sendClickHandlerRegistration.removeHandler();
		}
		sendClickHandlerRegistration = sendButton
				.addClickHandler(sendClickHandler);

		// send keypress
		sendKeyPressHandler = new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
					reload = false;
					getTimeStamp(textInputTextArea.getText());
					textInputTextArea.setText("");
				}
			}
		};
		if (sendKeyPressHandlerRegistration != null) {
			sendKeyPressHandlerRegistration.removeHandler();
		}
		sendKeyPressHandlerRegistration = textInputTextArea
				.addKeyPressHandler(sendKeyPressHandler);

		refreshTimer = new Timer() {
			@Override
			public void run() {
				if (reload) {
					getData();
				}
			}
		};

		// run refresh
		refreshTimer.scheduleRepeating(REFRESH_INTERVAL);

	}

	private void loggedIn() {
		user = Integer.parseInt(Cookies.getCookie("user"));
		experiment = Integer.parseInt(Cookies.getCookie("experiment"));
		setLoginScreen(1);
		loadScreen();
		getExperimentSettings();
	}

	private void stopSession() {

		// stop reload in userInterface
		stopReload();

		// clear elements
		RootPanel.get("head").clear();
		RootPanel.get("title").clear();
		RootPanel.get("content").clear();
		RootPanel.get("foot").clear();
		RootPanel.get().clear();

		// start new session
		startSession();
	}

	private void loadLoginScreen() {
		loginPanel.setVisible(true);
		logoutButton.setVisible(false);
		pwTextBox.setText("");
		userTextBox.setText("");
		loginMessage.setText("");
	}

	private void logout() {
		Cookies.setCookie("loggedin", "0");
		this.login = "0";
		setLoginScreen(0);
		stopEntry();
		stopSession();
	}

	private void login() {

		String sql = "Select * from user inner join experimentuser where user.id = experimentuser.userid and user.name = '"
				+ userTextBox.getText()
				+ "' and user.pw = '"
				+ pwTextBox.getText() + "' and user.role = 2";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {

					user = (Integer.parseInt(result[0][0]));
					experiment = (Integer.parseInt(result[0][4]));
					// loginPanel.setVisible(false);
					setLoginScreen(1);

					loginMessage.setText("");
					loadScreen();

					// set cookies
					Cookies.setCookie("user", "" + user + "");
					Cookies.setCookie("experiment", "" + experiment + "");
					getExperimentSettings();

				} else {
					// clear login if no user is found
					setLoginScreen(0);
					loginMessage.setText("Sorry! Wrong user name or password ");
					Cookies.setCookie("loggedin", "0");
				}
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void setLoginScreen(int status) {
		switch (status) {
		case 0:
			logoutButton.setVisible(false);
			loginButton.setVisible(true);
			pwTextBox.setText("");
			userTextBox.setText("");
			pwTextBox.setVisible(true);
			userTextBox.setVisible(true);
			userLabel.setVisible(true);
			pwLabel.setVisible(true);
			break;
		case 1:
			logoutButton.setVisible(true);
			loginButton.setVisible(false);
			pwTextBox.setVisible(false);
			userTextBox.setVisible(false);
			userLabel.setVisible(false);
			pwLabel.setVisible(false);
			break;
		}
	}

	private void getExperimentSettings() {
		String sql = "Select * from experiment where id = " + experiment;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {
					wizard = Integer.parseInt(result[0][7]);
					asr = Integer.parseInt(result[0][14]);
					text = Integer.parseInt(result[0][15]);
					audio = Integer.parseInt(result[0][16]);
					mm = Integer.parseInt(result[0][17]);
					setTxtIn(Integer.parseInt(result[0][21]));

					if (Integer.parseInt(result[0][22]) == 1) {
						useClientLogout = true;
					} else {
						useClientLogout = false;
					}

					if (useClientLogout) {
						loginPanel.add(logoutButton);
					}

					// create speech
					if (asr == 1) {
						integrateASR();
					} else {

					}

					if (getTxtIn() == 1) {
						integrateTxt();
					}

				} else {

				}

				// start entry only if not already logged in
				if (!login.equals("1")) {
					startEntry();
				}

				// }

			}
		};

		databaseAccessSvc.retrieveData(sql, callback);

	}

	private void startEntry() {
		// time stamp
		@SuppressWarnings("deprecation")
		String time = DateTimeFormat.getMediumDateTimeFormat().format(
				new Date());

		String sql = "Insert into output (textorig, audioorig, mmorig, texttrans, audiotrans, mmtrans, experiment, timestamp, played, signal, sender, user, receiver) values ('Start Session (Client)', '-', '-', 'Start Session (Client)', '-', '-', "
				+ experiment
				+ ", '"
				+ time
				+ "', 1, 1, "
				+ user
				+ ", "
				+ user
				+ ", " + wizard + ")";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Void result) {
				// set login cookie
				Cookies.setCookie("loggedin", "1");
			}
		};

		databaseAccessSvc.storeData(sql, callback);
	}

	private void stopEntry() {
		// time stamp
		@SuppressWarnings("deprecation")
		String time = DateTimeFormat.getMediumDateTimeFormat().format(
				new Date());

		String sql = "Insert into output (textorig, audioorig, mmorig, texttrans, audiotrans, mmtrans, experiment, timestamp, played, signal, user, sender, receiver) values ('Stop Session (Client)', '-', '-', 'Stop Session (Client)', '-', '-', "
				+ experiment
				+ ", '"
				+ time
				+ "', 1, 0, "
				+ user
				+ " , "
				+ user
				+ ", " + wizard + ")";

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

	private void getData() {
		String sql = "Select * from output where experiment = " + experiment
				+ " and receiver = " + user
				+ " and played = 0 order by id desc";

		// Initialize the service remote procedure call

		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {
					if (Integer.parseInt(result[0][16]) != 6) {
						processing = "Processing";
						reload = false;
						currentID = Integer.parseInt(result[0][0]);
						textOutput = result[0][4];
						audioFile = result[0][5];
						mmFile = result[0][6];

						// text
						if (text == 1) {
							textPanel.clear();
							textPanel.add(new HTML("<div>" + textOutput
									+ "</div>"));
						}

						// audio
						if (audio == 1) {
							audioPanel.clear();
							// add the audio tag
							audioPanel
									.add(new HTML(
											"<audio id=\"audioTag\" src=\""
													+ audioFile
													+ "\" autobuffer=\"autobuffer\"> </audio>"));

							// play the audio
							playAudioTag();

						}

						// multimedia
						if (mm == 1) {
							mmPanel.clear();
							// add the video tag
							mmPanel.add(new HTML(
									"<video id=\"videoTag\" src=\""
											+ mmFile
											+ "\" autobuffer=\"autobuffer\"> </video>"));

							// play the video
							playVideoTag();

						}
						updatePlaylist();

					} else {
						processing = processing + ".";

						if (text == 1) {
							textPanel.clear();
							textPanel.add(new HTML(
									"<div style=\"width: 250px;\">"
											+ processing + "</div>"));
						}

					}

				} else {

				}

			}

		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	public native void playAudioTag() /*-{
		$doc.getElementById('audioTag').play();
		//$doc and $wnd are JSNI-speak for document and window
	}-*/;

	public native void playVideoTag() /*-{
		$doc.getElementById('videoTag').play();
		//$doc and $wnd are JSNI-speak for document and window
	}-*/;

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
				reload = true;
			}
		};

		databaseAccessSvc.storeData(sql, callback);

	}

	public void stopReload() {
		if (refreshTimer != null) {
			refreshTimer.cancel();
		}
		reload = false;
	}

	private void loadScreen() {
		// Start timer to refresh list automatically.
		reload = true;

	}

	private void integrateASR() {
		asrPanel.clear();
		asrPanel.add(new HTML(
				"<input name=\"speech\" id=\"speech\" type=\"text\" x-webkit-speech onwebkitspeechchange=\"store(this.value, "
						+ user
						+ ", "
						+ experiment
						+ ", "
						+ wizard
						+ ")\" style=\"color:transparent; background-color:transparent; border:0px; width:15px; position: relative; z-index: 9999\" />"));

	}

	private void integrateTxt() {
		txtInPanel.clear();

		textInputTextArea.setVisibleLines(3);
		textInputTextArea.setCharacterWidth(50);
		// check that only one handler is added
		sendKeyPressHandlerRegistration.removeHandler();
		textInputTextArea.addKeyPressHandler(sendKeyPressHandler);

		// check that only one handler is added
		sendButton.setStyleName("button");
		sendClickHandlerRegistration.removeHandler();
		sendClickHandlerRegistration = sendButton
				.addClickHandler(sendClickHandler);

		txtInPanel.add(textInputTextArea);
		txtInPanel.add(sendButton);
	}

	private void insertInput(String input, String time) {

		String sql = "Insert into output (textorig, audioorig, mmorig, texttrans, audiotrans, mmtrans, timestamp, experiment, user, signal, sender, receiver, asr) values (\""
				+ input
				+ "\", '-', '-', '-', '-', '-', '"
				+ time
				+ "', "
				+ experiment
				+ ", "
				+ user
				+ ", 3, "
				+ user
				+ ", "
				+ wizard
				+ ", 1)";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Void result) {
				reload = true;
			}
		};

		databaseAccessSvc.storeData(sql, callback);
	}

	public void setTxtIn(int txtIn) {
		this.txtIn = txtIn;
	}

	public int getTxtIn() {
		return txtIn;
	}

	private void getTimeStamp(final String input) {

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {

				insertInput(input, result);

			}

		};

		databaseAccessSvc.getDateTime(callback);
	}

}
