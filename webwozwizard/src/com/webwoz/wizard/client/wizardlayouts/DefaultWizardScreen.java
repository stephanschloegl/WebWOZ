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

import java.util.Date;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.webwoz.wizard.client.ComponentFactory;
import com.webwoz.wizard.client.ComponentFactoryAsync;
import com.webwoz.wizard.client.DatabaseAccess;
import com.webwoz.wizard.client.DatabaseAccessAsync;
import com.webwoz.wizard.client.Screen;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class DefaultWizardScreen implements Screen {

	// Panels
	private VerticalPanel layoutPanel;
	private HorizontalPanel horLayoutPanel;
	private VerticalPanel historyPanel;
	private VerticalPanel historyUtterancesPanel;
	private ScrollPanel historyUtterancesScrollPanel;
	private VerticalPanel leftPanel;
	private VerticalPanel rightPanel;
	private VerticalPanel recoveryPanel;
	private HorizontalPanel experimentNotesPanel;
	private VerticalPanel domainDataResponsePanel;
	private VerticalPanel freeTextElementsCollectionPanel;
	private ScrollPanel domainDataResponseScrollPanel;
	private VerticalPanel[] domainDataSlotPanel;
	private HorizontalPanel[] domainDataSlotPanelHeading;
	private HorizontalPanel userPanel;
	private HorizontalPanel editHeadingButtonsPanel;
	private static VerticalPanel editPanel;
	private static VerticalPanel editTabsPanel;
	private static VerticalPanel editFreeTextPanel;
	private VerticalPanel preparedFreeTextPanel;
	private TabPanel dialogueStructurePanel;
	private TabPanel domainDataTabPanel;
	private VerticalPanel newTabPanel;
	private HorizontalPanel addTabPanel;
	private HorizontalPanel[] uttPanel;
	private HorizontalPanel[] editTabs;
	private HorizontalPanel signalPanel;
	private HorizontalPanel[] domainUttPanel;
	private HorizontalPanel[] slots;
	private VerticalPanel[] tabNotesPanel;
	private VerticalPanel[] dialogueTabPanel;
	private HorizontalPanel domainDataSlotCollectionPanel;
	private ScrollPanel domainDataSlotScrollPanel;
	private ScrollPanel domainDataFreeTextScrollPanel;
	private ScrollPanel wizardCorrectionScrollPanel;
	private ScrollPanel nBestListScrollPanel;
	private VerticalPanel wizardCorrectionPanel;
	private VerticalPanel nBestListPanel;
	private VerticalPanel freeTextPanel;
	private ScrollPanel[] dialogueTabScrollPanel;
	private ScrollPanel[] notesTabScrollanel;
	private VerticalPanel[] dialogueUtterancesPanel;
	private VerticalPanel[] utterancesPanel;
	private VerticalPanel recoveryUtterancePanel;
	private ScrollPanel recoveryScrollPanel;
	private HorizontalPanel editButtonsPanel;
	private HorizontalPanel editDomainButtonsPanel;
	private static VerticalPanel reportPanel;
	private ScrollPanel reportContentScrollPanel;
	private HorizontalPanel reportButtonsPanel;
	private HorizontalPanel reportContentHeadingPanel;
	private ScrollPanel editTabsScrollPanel;
	private VerticalPanel editTabsTabsPanel;
	private static VerticalPanel editDomainDataPanel;
	private static VerticalPanel editSlotPopUpPanel;
	private static VerticalPanel addSlotPopUpPanel;
	private VerticalPanel editDomainFilterPanel;
	private HorizontalPanel editFilterPanel;
	private HorizontalPanel editFilterAddSlotPanel;
	private HorizontalPanel[] editFilterSlotsPanel;
	private VerticalPanel editFilterSlotCollectionPanel;
	private HorizontalPanel addFilterPanel;
	private HorizontalPanel addFilterButtons;
	private VerticalPanel addFilterSlotCollection;
	private HorizontalPanel addFiltersStandardSlotPanel;
	private HorizontalPanel addFilterValuePanel;
	private HorizontalPanel shoutPanel;
	private VerticalPanel[] editTabsContainer;
	private ScrollPanel experimentNotesContainer;
	private VerticalPanel experimentNotesCollection;
	private HorizontalPanel notesHeadingPanel;
	private HorizontalPanel preparedFreeTextButtonsPanel;
	private HorizontalPanel[] domainDataFreeTextPanels;

	// lists
	private ListBox userList;
	private ListBox editTabUttList;
	private ListBox[] filterList;

	// Labels
	private Label recoveryHeadingLabel;
	private Label semLabel;
	private Label textLabel;
	private Label audioLabel;
	private Label mmLabel;
	private Label translationLabel;
	private Label translationAudioLabel;
	private Label translationMMLabel;
	private Label tabLabel;
	private Label rankLabel;
	private Label[] uttLabels;
	private Label user;
	private Label[] domainUttLabels;
	private Label[] domainUttNoLabels;
	private Label[] slotHeadingLabel;
	private Label historyHeadingLabel;
	private Label[] tabNotes;
	private Label[] tabNotesHeading;
	private Label[] uttHeadings;
	private Label experimentNotesHeading;
	private Label editTabsErrorLabel;
	private Label semDomainLabel;
	private Label textDomainLabel;
	private Label audioDomainLabel;
	private Label mmDomainLabel;
	private Label translationDomainLabel;
	private Label translationDomainAudioLabel;
	private Label translationDomainMMLabel;
	private Label[] filterHeadingLabel;
	private Label editFilterSlotValues;
	private Label errorDeleteSlotValueLabel;
	private Label standardFilterValueLabel;
	private Label standardFilterValueExpLabel;
	private Label addFilterLabel;
	private Label tabNameLabel;
	private Label tabInstructionLabel;
	private Label preparedFreeTextSemKeyLabel;
	private Label preparedFreeTextLabel;
	private Label[] domainDataFreeTextLabels;

	// TextBoxes
	private TextBox semKeyTextBox;
	private TextBox audioFileTextBox;
	private TextBox mmFileTextBox;
	private TextBox translAudioFileTextBox;
	private TextBox translMMFileTextBox;
	private TextBox rankTextBox;
	private TextBox addTabTextBox;
	private TextBox[] tabText;
	private TextBox semKeyDomainTextBox;
	private TextBox audioFileDomainTextBox;
	private TextBox mmFileDomainTextBox;
	private TextBox translAudioFileDomainTextBox;
	private TextBox translMMFileDomainTextBox;
	private TextBox editFilterTextBox;
	private TextBox[] editFilterSlotValuesTextBox;
	private TextBox editFilterAddSlotTextBox;
	private TextBox addFilterTextBox;
	private TextBox standardFilterValueTextBox;
	private TextBox addFilterValueTextBox;
	private TextBox preparedFreeTextShortTextBox;

	// Text Areas
	private TextArea experimentNotesTextArea;
	private TextArea translTextArea;
	private TextArea textTextArea;
	private TextArea textDomainTextArea;
	private TextArea translDomainTextArea;
	private TextArea shoutBoxTexatArea;
	private TextArea intructionTextArea;
	private TextArea[] tabInstructions;
	private TextArea preparedFreeTextTextArea;

	// Buttons
	private Button startEditButton;
	private Button endEditButton;
	private Button changeUttEditButton;
	private Button deleteUttEditButton;
	private Button addUttEditButton;
	private Button cancelUttEditButton;
	private Button addUttButton;
	private Button editTabsButton;
	private Button addTabEditButton;
	private Button addPreparedFreeTextElementsButton;
	private Button cancelTabEditButton;
	private Button openReportButton;
	private Button endExperimentMarkerButton;
	private Button processingButton;
	private Button closeReportButton;
	private Button addDomainDataButton;
	private Button addFilterButton;
	private Button saveNotesButton;
	private Button editFilterSaveButton;
	private Button editFilterDeleteButton;
	private Button[] tabDelButton;
	private Button[] tabSaveButton;
	private Button[] uttButtons;
	private Button[] uttButtonsEdit;
	private Button[] uttButtonsToFreeText;
	private Button[] domainUttButtons;
	private Button[] domainUttButtonsEdit;
	private Button[] domainDataSlotEditButtons;
	private Button printReportButton;
	private Button exportReportButton;
	private Button exportNotesButton;
	private Button changeUttDomainEditButton;
	private Button addUttDomainEditButton;
	private Button cancelUttDomainEditButton;
	private Button deleteUttDomainEditButton;
	private Button cancelEditSlotPopUpButton;
	private Button[] editFilterDeletSlotButton;
	private Button[] editFilterChangeSlotButton;
	private Button editFilterAddSlotButton;
	private Button cancelAddSlotPopUpButton;
	private Button addFilterSaveButton;
	private Button addFilterValueButton;
	private Button sendFreeTextButton;
	private Button addFreeTextButton;
	private Button closeFreeTextButton;
	private Button editFreeTextButton;
	private Button deleteFreeTextButton;
	private Button[] domainDataFreeTextButtons;
	private Button[] domainDataFreeTextButtonsEdit;
	private Button[] uttButtonsRankUpButtons;
	private Button[] uttButtonsRankDownButtons;

	// Radio Buttons
	private RadioButton[][] slotRadioButton;

	// handler
	private ClickHandler[] addUtteranceClickHandler;
	private ClickHandler[] editUtteranceClickHandler;
	private ClickHandler[] addDomainUtteranceClickHandler;
	private ClickHandler[] editDomainUtteranceClickHandler;
	private ClickHandler[] saveTabClickHandler;
	private ClickHandler[] delTabClickHandler;
	private ClickHandler[] editSlotHandler;
	private ClickHandler[][] slotRadioButtonHandler;
	private ClickHandler[] changeSlotValueHandler;
	private ClickHandler[] deleteSlotValueHandler;
	private ClickHandler[] addFreeTextHandler;
	private ClickHandler[] editFreeTextHandler;
	private ClickHandler[] addToFreeTextHandler;
	private ClickHandler[] uttButtonRankUpHandler;
	private ClickHandler[] uttButtonRankDownHandler;

	// handler registration
	private HandlerRegistration[] addUtteranceClickHandlerRegistration;
	private HandlerRegistration[] editUtteranceClickHandlerRegistration;
	private HandlerRegistration[] addToFreeClickHandlerRegistration;
	private HandlerRegistration[] addDomainUtteranceClickHandlerRegistration;
	private HandlerRegistration[] editDomainUtteranceClickHandlerRegistration;
	private HandlerRegistration[] saveTabClickHandlerRegistration;
	private HandlerRegistration[] delTabClickHandlerRegistration;
	private HandlerRegistration[] editSlotHandlerRegistration;
	private HandlerRegistration[][] slotRadioButtonHandlerRegistration;
	private HandlerRegistration[] changeSlotValueHandlerRegistration;
	private HandlerRegistration[] deleteSlotValueHandlerRegistration;
	private HandlerRegistration[] addFreeTextHandlerRegistration;
	private HandlerRegistration[] editFreeTextHandlerRegistration;
	private HandlerRegistration[] uttButtUpClickHandlerRegistration;
	private HandlerRegistration[] uttButtDownClickHandlerRegistration;

	// Pop-ups
	private EditUtterancesPopup editUtterancesPopup;
	private EditDomainDataPopUp editDomainDataPopup;
	private EditTabsPopup editTabsPopup;
	private PrintReportPopup printReportPopup;
	private EditSlotPopUp editSlotPopUp;
	private AddSlotPopUp addSlotPopUp;
	private EditPreparedFreeTextPopup editFreeTextPopUp;

	// HTML
	private HTML reportTable;
	private HTML reportHeadingTable;
	private HTML statusHtml;
	private HTML loggedInHtml;

	// RPC
	private DatabaseAccessAsync databaseAccessSvc;
	private ComponentFactoryAsync componentFactorySvc;

	// Vectors
	private Vector<HorizontalPanel> inputElementVectorHorPanels;
	private Vector<Button> inputButtonVectorButtons;
	private Vector<TextArea> inputTextAreaVectorTextAreas;
	private Vector<String> inputTextVector;
	private Vector<VerticalPanel> inputNBestListVectorVerPanels;
	private Vector<HorizontalPanel> inputNBestListVectorHorPanels;
	private Vector<Button> inputNBestListVectorButtons;
	private Vector<Label> inputNBestListVectorLabels;
	private Vector<String> inputNBestListAlternatives;

	// Refresh interval
	private final int REFRESH_INTERVAL = 1000;
	private Timer refreshTimer;

	// Layout variables
	private int expId;
	private int userId;
	private int wizId;
	private String editUtt;
	private String editDomainUtt;
	private int countDomainUtt;
	private int countUtt;
	private int editId;
	private int countTab;
	private int editSlotRank;
	private int editSlotId;
	private String editSlot;
	private String reportHtml;
	private String reportHeadingHtml;
	private Integer[][] domainDataSort;
	private Integer[] selectedSlots;
	private Integer[][] possibleData;
	private int reloadMode;
	private Boolean reload;
	private Boolean statusUpdate;
	private int rankEstimation;
	private String defaultSlotValue;
	private int notesCount;
	private boolean slotsExist;
	private String semKey;
	private String rank;
	private String text;
	private String audio;
	private String mm;
	private String transtext;
	private String transaudio;
	private String transmm;
	private String note;
	private String tabName;
	private String tabInst;
	private String newFilter;
	private String newValue;
	private int selectedTab;
	private int selectedDomainTab;
	private String currentFreeTextElement;
	private int countFreeTextElements;
	private boolean reloadFreeText;
	private boolean nBestList;
	private boolean wizardCorrection;
	private boolean domainPanelVisible;
	private boolean freeText;
	private String recognized;
	private String alterantives;

	public DefaultWizardScreen(int exp, int wiz) {
		// set wizard and experiment
		wizId = wiz;
		expId = exp;

		// initialize variables
		initialize();

	}

	private void initialize() {

		// Panels
		layoutPanel = new VerticalPanel();
		horLayoutPanel = new HorizontalPanel();
		historyPanel = new VerticalPanel();
		historyUtterancesPanel = new VerticalPanel();
		historyUtterancesScrollPanel = new ScrollPanel(historyUtterancesPanel);
		leftPanel = new VerticalPanel();
		rightPanel = new VerticalPanel();
		recoveryPanel = new VerticalPanel();
		experimentNotesPanel = new HorizontalPanel();
		domainDataResponsePanel = new VerticalPanel();
		freeTextElementsCollectionPanel = new VerticalPanel();
		domainDataResponseScrollPanel = new ScrollPanel();
		userPanel = new HorizontalPanel();
		editHeadingButtonsPanel = new HorizontalPanel();
		editPanel = new VerticalPanel();
		editTabsPanel = new VerticalPanel();
		editFreeTextPanel = new VerticalPanel();
		preparedFreeTextPanel = new VerticalPanel();
		dialogueStructurePanel = new TabPanel();
		domainDataTabPanel = new TabPanel();
		newTabPanel = new VerticalPanel();
		addTabPanel = new HorizontalPanel();
		signalPanel = new HorizontalPanel();
		domainDataSlotCollectionPanel = new HorizontalPanel();
		domainDataSlotScrollPanel = new ScrollPanel();
		domainDataFreeTextScrollPanel = new ScrollPanel();
		wizardCorrectionScrollPanel = new ScrollPanel();
		nBestListScrollPanel = new ScrollPanel();
		wizardCorrectionPanel = new VerticalPanel();
		nBestListPanel = new VerticalPanel();
		freeTextPanel = new VerticalPanel();
		recoveryUtterancePanel = new VerticalPanel();
		recoveryScrollPanel = new ScrollPanel();
		editButtonsPanel = new HorizontalPanel();
		editDomainButtonsPanel = new HorizontalPanel();
		reportPanel = new VerticalPanel();
		reportContentScrollPanel = new ScrollPanel();
		reportButtonsPanel = new HorizontalPanel();
		reportContentHeadingPanel = new HorizontalPanel();
		editTabsScrollPanel = new ScrollPanel();
		editTabsTabsPanel = new VerticalPanel();
		editDomainDataPanel = new VerticalPanel();
		editSlotPopUpPanel = new VerticalPanel();
		addSlotPopUpPanel = new VerticalPanel();
		editDomainFilterPanel = new VerticalPanel();
		editFilterPanel = new HorizontalPanel();
		editFilterAddSlotPanel = new HorizontalPanel();
		editFilterSlotCollectionPanel = new VerticalPanel();
		addFilterPanel = new HorizontalPanel();
		addFilterButtons = new HorizontalPanel();
		addFilterSlotCollection = new VerticalPanel();
		addFiltersStandardSlotPanel = new HorizontalPanel();
		addFilterValuePanel = new HorizontalPanel();
		shoutPanel = new HorizontalPanel();
		experimentNotesContainer = new ScrollPanel();
		experimentNotesCollection = new VerticalPanel();
		notesHeadingPanel = new HorizontalPanel();
		preparedFreeTextButtonsPanel = new HorizontalPanel();

		// lists
		userList = new ListBox();
		editTabUttList = new ListBox();

		// Labels
		recoveryHeadingLabel = new Label();
		semLabel = new Label("Short Name / Label: ");
		textLabel = new Label("Utterance: ");
		audioLabel = new Label("Link to Audio File: ");
		mmLabel = new Label("Link to Multi-Media File: ");
		translationLabel = new Label("Translated Utterance: ");
		translationAudioLabel = new Label("Link to Translated Audio File: ");
		translationMMLabel = new Label("Link to Translated Multi-Media File: ");
		tabLabel = new Label("Tab: ");
		rankLabel = new Label("Rank: ");
		user = new Label("User: ");
		historyHeadingLabel = new Label("Sent Utterances:");
		experimentNotesHeading = new Label();
		editTabsErrorLabel = new Label();
		semDomainLabel = new Label("Short Name / Label: ");
		textDomainLabel = new Label("Utterance: ");
		audioDomainLabel = new Label("Link to Audio File: ");
		mmDomainLabel = new Label("Link to Multi-Media File: ");
		translationDomainLabel = new Label("Translated Utterance: ");
		translationDomainAudioLabel = new Label(
				"Link to Translated Audio File: ");
		translationDomainMMLabel = new Label(
				"Link to Translated Multi-Media File: ");
		editFilterSlotValues = new Label("Filter Values:");
		errorDeleteSlotValueLabel = new Label();
		standardFilterValueLabel = new Label("Default Value*");
		standardFilterValueExpLabel = new Label(
				"*The default value is applied to all domain data utterances as soon as the filter is created! Values can be changed by separately editing the different utterances.");
		addFilterLabel = new Label("Filter Name");
		tabNameLabel = new Label("Tab Name");
		tabInstructionLabel = new Label("Tab Instructions");
		preparedFreeTextSemKeyLabel = new Label("Short Name / Label: ");
		preparedFreeTextLabel = new Label("Text Element: ");

		// TextBoxes
		semKeyTextBox = new TextBox();
		audioFileTextBox = new TextBox();
		mmFileTextBox = new TextBox();
		translAudioFileTextBox = new TextBox();
		translMMFileTextBox = new TextBox();
		rankTextBox = new TextBox();
		addTabTextBox = new TextBox();
		semKeyDomainTextBox = new TextBox();
		audioFileDomainTextBox = new TextBox();
		mmFileDomainTextBox = new TextBox();
		translAudioFileDomainTextBox = new TextBox();
		translMMFileDomainTextBox = new TextBox();
		editFilterTextBox = new TextBox();
		editFilterAddSlotTextBox = new TextBox();
		addFilterTextBox = new TextBox();
		standardFilterValueTextBox = new TextBox();
		addFilterValueTextBox = new TextBox();
		preparedFreeTextShortTextBox = new TextBox();

		// Text Areas
		experimentNotesTextArea = new TextArea();
		translTextArea = new TextArea();
		textTextArea = new TextArea();
		textDomainTextArea = new TextArea();
		translDomainTextArea = new TextArea();
		shoutBoxTexatArea = new TextArea();
		intructionTextArea = new TextArea();
		preparedFreeTextTextArea = new TextArea();

		// Buttons
		startEditButton = new Button("Enter Edit Mode");
		endEditButton = new Button("Stop Edit Mode");
		changeUttEditButton = new Button("Save Changes");
		deleteUttEditButton = new Button("Delete Utterance");
		addUttEditButton = new Button("Add Utterance");
		cancelUttEditButton = new Button("Close");
		addUttButton = new Button("Add Utterance");
		editTabsButton = new Button("Add/Edit Tabs");
		addTabEditButton = new Button("Add Tab");
		addPreparedFreeTextElementsButton = new Button(
				"Add Prepared Free Text Elements");
		cancelTabEditButton = new Button("Close");
		openReportButton = new Button("Show Report");
		endExperimentMarkerButton = new Button("End Experiment");
		processingButton = new Button("Processing...");
		closeReportButton = new Button("Close");
		addDomainDataButton = new Button("Add Domain Data");
		addFilterButton = new Button("Add Filter");
		saveNotesButton = new Button("Save");
		editFilterSaveButton = new Button("Save Changes");
		editFilterDeleteButton = new Button("Delete Filter");
		printReportButton = new Button("Print Report");
		exportReportButton = new Button("Export Report");
		exportNotesButton = new Button("Export Notes");
		changeUttDomainEditButton = new Button("Save Changes");
		addUttDomainEditButton = new Button("Add Utterance");
		cancelUttDomainEditButton = new Button("Close");
		deleteUttDomainEditButton = new Button("Delete Utterance");
		cancelEditSlotPopUpButton = new Button("Close");
		editFilterAddSlotButton = new Button("Add Filter Value");
		cancelAddSlotPopUpButton = new Button("Close");
		addFilterSaveButton = new Button("Add Filter");
		addFilterValueButton = new Button("Add Value");
		sendFreeTextButton = new Button("Send");
		addFreeTextButton = new Button("Add Free Text Element");
		closeFreeTextButton = new Button("Close");
		editFreeTextButton = new Button("Save Changes");
		deleteFreeTextButton = new Button("Delete Element");

		// HTML
		reportTable = new HTML();
		reportHeadingTable = new HTML();
		statusHtml = new HTML();
		loggedInHtml = new HTML();

		// RPC
		databaseAccessSvc = GWT.create(DatabaseAccess.class);
		componentFactorySvc = GWT.create(ComponentFactory.class);

		// Vectors
		inputElementVectorHorPanels = new Vector<HorizontalPanel>();
		inputButtonVectorButtons = new Vector<Button>();
		inputTextAreaVectorTextAreas = new Vector<TextArea>();
		inputTextVector = new Vector<String>();
		inputNBestListVectorVerPanels = new Vector<VerticalPanel>();
		inputNBestListVectorHorPanels = new Vector<HorizontalPanel>();
		inputNBestListVectorButtons = new Vector<Button>();
		inputNBestListVectorLabels = new Vector<Label>();
		inputNBestListAlternatives = new Vector<String>();

		// reload variables (for editing)
		reloadMode = 0;
		reload = true;
		// start with no status queries => is turned on after all the data is
		// loaded!
		statusUpdate = false;
		// keep track of which tab is selected
		selectedTab = 0;
		// set it to 100 at the beginning to distinguish between load and reload
		selectedDomainTab = 100;

		recognized = "";

		reloadFreeText = false;

		// Instantiate pop-ups
		editUtterancesPopup = new EditUtterancesPopup();
		editDomainDataPopup = new EditDomainDataPopUp();
		editTabsPopup = new EditTabsPopup();
		printReportPopup = new PrintReportPopup();
		editSlotPopUp = new EditSlotPopUp();
		addSlotPopUp = new AddSlotPopUp();
		editFreeTextPopUp = new EditPreparedFreeTextPopup();

		buildLayout();

	}

	private void buildLayout() {

		// hide popups
		editUtterancesPopup.hide();
		editTabsPopup.hide();
		printReportPopup.hide();
		editDomainDataPopup.hide();
		editSlotPopUp.hide();
		addSlotPopUp.hide();
		editFreeTextPopUp.hide();

		// load user
		loadUser();

		// status
		signalPanel.clear();
		loggedInHtml.setHTML("<div><strong>Logged out</strong></div>");
		statusHtml.setHTML("");
		signalPanel.add(loggedInHtml);
		signalPanel.add(statusHtml);
		signalPanel.setWidth("220px");

		// edit panel
		editPanel.clear();
		editPanel.add(semLabel);
		semLabel.setStyleName("labelVertical");
		semLabel.addStyleName("strong");
		editPanel.add(semKeyTextBox);
		semKeyTextBox.setWidth("100px");
		semKeyTextBox.setStyleName("text");

		editPanel.add(textLabel);
		textLabel.setStyleName("labelVertical");
		textLabel.addStyleName("strong");
		editPanel.add(textTextArea);
		textTextArea.setCharacterWidth(110);
		textTextArea.setVisibleLines(3);
		textTextArea.setStyleName("text");

		editPanel.add(audioLabel);
		audioLabel.setStyleName("labelVertical");
		audioLabel.addStyleName("strong");
		editPanel.add(audioFileTextBox);
		audioFileTextBox.setWidth("400px");
		audioFileTextBox.setStyleName("text");

		editPanel.add(mmLabel);
		mmLabel.setStyleName("labelVertical");
		mmLabel.addStyleName("strong");
		editPanel.add(mmFileTextBox);
		mmFileTextBox.setWidth("400px");
		mmFileTextBox.setStyleName("text");

		editPanel.add(translationLabel);
		translationLabel.setStyleName("labelVertical");
		translationLabel.addStyleName("strong");
		editPanel.add(translTextArea);
		translTextArea.setCharacterWidth(110);
		translTextArea.setVisibleLines(3);
		translTextArea.setStyleName("text");

		editPanel.add(translationAudioLabel);
		translationAudioLabel.setStyleName("labelVertical");
		translationAudioLabel.addStyleName("strong");
		editPanel.add(translAudioFileTextBox);
		translAudioFileTextBox.setWidth("400px");
		translAudioFileTextBox.setStyleName("text");

		editPanel.add(translationMMLabel);
		translationMMLabel.setStyleName("labelVertical");
		translationMMLabel.addStyleName("strong");
		editPanel.add(translMMFileTextBox);
		translMMFileTextBox.setWidth("400px");
		translMMFileTextBox.setStyleName("text");

		editPanel.add(tabLabel);
		tabLabel.setStyleName("labelVertical");
		tabLabel.addStyleName("strong");
		editPanel.add(editTabUttList);
		editTabUttList.setStyleName("list");

		editPanel.add(rankLabel);
		rankLabel.setStyleName("labelVertical");
		editPanel.add(rankTextBox);
		rankTextBox.setWidth("30px");
		rankTextBox.setStyleName("text");
		// for the moment set invisible
		rankLabel.setVisible(false);
		rankTextBox.setVisible(false);

		changeUttEditButton.setStyleName("buttonHorizontal");
		addUttEditButton.setStyleName("buttonHorizontal");
		cancelUttEditButton.setStyleName("buttonHorizontal");
		deleteUttEditButton.setStyleName("buttonHorizontal");

		editButtonsPanel.clear();
		editButtonsPanel.add(deleteUttEditButton);
		editButtonsPanel.add(changeUttEditButton);
		editButtonsPanel.add(addUttEditButton);
		editButtonsPanel.add(cancelUttEditButton);
		editPanel.add(editButtonsPanel);
		editPanel.setStyleName("editPanel");
		editPanel.setHeight("500px");
		editPanel.setWidth("810px");

		// edit tabs panel error
		editTabsErrorLabel.setStyleName("error");

		// edit domain data panel
		editDomainDataPanel.clear();
		editDomainDataPanel.add(semDomainLabel);
		semDomainLabel.setStyleName("labelVertical");
		semDomainLabel.addStyleName("strong");
		editDomainDataPanel.add(semKeyDomainTextBox);
		semKeyDomainTextBox.setWidth("100px");
		semKeyDomainTextBox.setStyleName("text");

		editDomainDataPanel.add(textDomainLabel);
		textDomainLabel.setStyleName("labelVertical");
		textDomainLabel.addStyleName("strong");
		editDomainDataPanel.add(textDomainTextArea);
		textDomainTextArea.setCharacterWidth(110);
		textDomainTextArea.setVisibleLines(3);
		textDomainTextArea.setStyleName("text");

		editDomainDataPanel.add(audioDomainLabel);
		audioDomainLabel.setStyleName("labelVertical");
		audioDomainLabel.addStyleName("strong");
		editDomainDataPanel.add(audioFileDomainTextBox);
		audioFileDomainTextBox.setWidth("400px");
		audioFileDomainTextBox.setStyleName("text");

		editDomainDataPanel.add(mmDomainLabel);
		mmDomainLabel.setStyleName("labelVertical");
		mmDomainLabel.addStyleName("strong");
		editDomainDataPanel.add(mmFileDomainTextBox);
		mmFileDomainTextBox.setWidth("400px");
		mmFileDomainTextBox.setStyleName("text");

		editDomainDataPanel.add(translationDomainLabel);
		translationDomainLabel.setStyleName("labelVertical");
		translationDomainLabel.addStyleName("strong");
		editDomainDataPanel.add(translDomainTextArea);
		translDomainTextArea.setCharacterWidth(110);
		translDomainTextArea.setVisibleLines(3);
		translDomainTextArea.setStyleName("text");

		editDomainDataPanel.add(translationDomainAudioLabel);
		translationDomainAudioLabel.setStyleName("labelVertical");
		translationDomainAudioLabel.addStyleName("strong");
		editDomainDataPanel.add(translAudioFileDomainTextBox);
		translAudioFileDomainTextBox.setWidth("400px");
		translAudioFileDomainTextBox.setStyleName("text");

		editDomainDataPanel.add(translationDomainMMLabel);
		translationDomainMMLabel.setStyleName("labelVertical");
		translationDomainMMLabel.addStyleName("strong");
		editDomainDataPanel.add(translMMFileDomainTextBox);
		translMMFileDomainTextBox.setWidth("400px");
		translMMFileDomainTextBox.setStyleName("text");

		editDomainFilterPanel.setStyleName("editDomainFilterPanel");
		editDomainFilterPanel.addStyleName("strong");
		editDomainDataPanel.add(editDomainFilterPanel);
		changeUttDomainEditButton.setStyleName("buttonHorizontal");
		addUttDomainEditButton.setStyleName("buttonHorizontal");
		cancelUttDomainEditButton.setStyleName("buttonHorizontal");
		deleteUttDomainEditButton.setStyleName("buttonHorizontal");

		editDomainButtonsPanel.clear();
		editDomainButtonsPanel.add(deleteUttDomainEditButton);
		editDomainButtonsPanel.add(changeUttDomainEditButton);
		editDomainButtonsPanel.add(addUttDomainEditButton);
		editDomainButtonsPanel.add(cancelUttDomainEditButton);
		editDomainDataPanel.add(editDomainButtonsPanel);
		editDomainDataPanel.setStyleName("editPanel");
		editDomainDataPanel.setHeight("500px");
		editDomainDataPanel.setWidth("810px");

		// edit slots
		editFilterPanel.clear();
		editFilterPanel.add(editFilterTextBox);
		editFilterSaveButton.setStyleName("button");
		editFilterDeleteButton.setStyleName("button");
		editFilterPanel.add(editFilterSaveButton);
		editFilterPanel.add(editFilterDeleteButton);
		cancelEditSlotPopUpButton.setStyleName("cancelEditSlotPopUpButton");
		editFilterPanel.setStyleName("editFilterPanel");

		editSlotPopUpPanel.clear();
		editSlotPopUpPanel.add(editFilterPanel);
		editFilterSlotValues.setStyleName("editFilterSlotValues");
		editSlotPopUpPanel.add(editFilterSlotValues);
		editSlotPopUpPanel.add(editFilterSlotCollectionPanel);

		editFilterAddSlotPanel.clear();
		editFilterAddSlotPanel.add(editFilterAddSlotTextBox);
		editFilterAddSlotButton.setStyleName("button");
		editFilterAddSlotPanel.add(editFilterAddSlotButton);
		editFilterAddSlotPanel.setStyleName("editFilterSlotValues");
		errorDeleteSlotValueLabel.setStyleName("errorSlot");

		editSlotPopUpPanel.add(errorDeleteSlotValueLabel);
		editSlotPopUpPanel.add(editFilterAddSlotPanel);
		editSlotPopUpPanel.add(cancelEditSlotPopUpButton);

		// add slots
		addFilterPanel.clear();
		addFilterPanel.add(addFilterTextBox);
		addFilterPanel.add(addFilterLabel);
		addFilterLabel.setStyleName("labelLeft");
		addFilterLabel.addStyleName("strong");
		addFilterPanel.setStyleName("editFilterPanel");

		addFilterSlotCollection.clear();
		addFilterSlotCollection.setStyleName("addFilterSlotCollection");
		addFilterSlotCollection.add(addFiltersStandardSlotPanel);

		addFiltersStandardSlotPanel.clear();
		addFiltersStandardSlotPanel.add(standardFilterValueTextBox);
		standardFilterValueTextBox.setText("Default-");
		standardFilterValueLabel.setStyleName("labelLeft");
		standardFilterValueLabel.addStyleName("strong");
		addFiltersStandardSlotPanel.add(standardFilterValueLabel);
		standardFilterValueExpLabel.setStyleName("standardFilterValueExpLabel");
		addFilterSlotCollection.add(standardFilterValueExpLabel);

		addFilterValuePanel.clear();
		addFilterValuePanel.setStyleName("addFilterSlotCollection");
		addFilterValuePanel.add(addFilterValueTextBox);
		addFilterValuePanel.add(addFilterValueButton);
		addFilterValueButton.setStyleName("button");

		addFilterButtons.clear();
		addFilterButtons.setStyleName("addFilterButtons");
		addFilterSaveButton.setStyleName("button");
		addFilterButtons.add(addFilterSaveButton);
		cancelAddSlotPopUpButton.setStyleName("button");
		addFilterButtons.add(cancelAddSlotPopUpButton);

		addSlotPopUpPanel.clear();
		addSlotPopUpPanel.add(addFilterPanel);
		addSlotPopUpPanel.add(addFilterSlotCollection);
		addSlotPopUpPanel.add(addFilterValuePanel);
		// for now keep it invisible
		// addFilterValuePanel.setVisible(false);
		addSlotPopUpPanel.add(addFilterButtons);

		// define size recoveryPanel
		recoveryHeadingLabel.setText("Frequently Used Utterances:");
		recoveryHeadingLabel.setStyleName("recoveryHeading");
		recoveryPanel.setStyleName("recoveryPanel");
		recoveryScrollPanel.clear();
		recoveryScrollPanel.setWidth("310px");
		recoveryScrollPanel.add(recoveryUtterancePanel);

		// experiment Notes
		experimentNotesPanel.clear();

		experimentNotesContainer.clear();
		experimentNotesContainer.setHeight("150px");
		experimentNotesContainer.setWidth("250px");
		experimentNotesContainer.setStyleName("experimentNotesContainer");
		experimentNotesContainer.add(experimentNotesCollection);

		experimentNotesCollection.setStyleName("experimentNotesPanel");

		experimentNotesPanel.add(experimentNotesContainer);
		experimentNotesPanel.add(saveNotesButton);
		saveNotesButton.setStyleName("saveNotesbutton");
		experimentNotesPanel.setStyleName("experimentNotesPanel");

		// domain Data
		domainDataResponseScrollPanel.clear();
		domainDataResponseScrollPanel.add(domainDataResponsePanel);
		domainDataResponseScrollPanel.setStyleName("domainDataPanel");
		domainDataResponseScrollPanel.setHeight("90px");
		domainDataResponseScrollPanel.setWidth("800px");

		domainDataSlotScrollPanel.clear();
		domainDataSlotScrollPanel.add(domainDataSlotCollectionPanel);
		domainDataSlotCollectionPanel.setStyleName("domainDataSlotPanel");
		domainDataSlotScrollPanel.setHeight("90px");
		domainDataSlotScrollPanel.setWidth("800px");

		domainDataFreeTextScrollPanel.clear();
		domainDataFreeTextScrollPanel.add(freeTextElementsCollectionPanel);
		domainDataFreeTextScrollPanel.setStyleName("domainDataPanel");
		domainDataFreeTextScrollPanel.setHeight("90px");
		domainDataFreeTextScrollPanel.setWidth("800px");

		// wizard correction
		wizardCorrectionScrollPanel.clear();
		wizardCorrectionScrollPanel.add(wizardCorrectionPanel);
		wizardCorrectionScrollPanel.setStyleName("domainDataPanel");
		wizardCorrectionScrollPanel.setHeight("357px");
		wizardCorrectionScrollPanel.setWidth("806px");

		// n-best list
		nBestListScrollPanel.clear();
		nBestListScrollPanel.add(nBestListPanel);
		nBestListScrollPanel.setStyleName("domainDataPanel");
		nBestListScrollPanel.setHeight("357px");
		nBestListScrollPanel.setWidth("806px");

		// free text field
		shoutBoxTexatArea.setCharacterWidth(100);
		shoutBoxTexatArea.setVisibleLines(5);
		shoutPanel.clear();
		shoutPanel.add(shoutBoxTexatArea);
		shoutPanel.add(sendFreeTextButton);
		sendFreeTextButton.setStyleName("button");
		shoutPanel.setStyleName("domainDataPanel");
		shoutBoxTexatArea.setText("");
		freeTextPanel.clear();
		freeTextPanel.add(shoutPanel);
		freeTextPanel.setHeight("90px");
		freeTextPanel.setWidth("806px");

		domainDataTabPanel.setWidth("825px");
		domainDataTabPanel.setHeight("140px");
		domainDataTabPanel.setStyleName("domainData");
		domainDataTabPanel.clear();
		domainDataTabPanel.add(domainDataResponseScrollPanel, "Domain Data");
		domainDataTabPanel.add(domainDataSlotScrollPanel, "Filter");
		domainDataTabPanel.add(freeTextPanel, "Free Text");

		domainDataTabPanel.selectTab(0);
		// turn off visibilty at the beginning
		domainDataTabPanel.setVisible(false);

		userPanel.clear();
		userPanel.add(startEditButton);
		startEditButton.setStyleName("button");
		userPanel.add(endEditButton);
		endEditButton.setStyleName("button");
		endEditButton.setVisible(false);
		user.setStyleName("user");
		userPanel.add(user);
		userPanel.add(userList);
		userList.setStyleName("list");
		userPanel.add(signalPanel);
		userPanel.setStyleName("userPanel");
		openReportButton.setStyleName("showReportButton");
		endExperimentMarkerButton.setStyleName("showReportButton");
		userPanel.add(openReportButton);
		userPanel.add(endExperimentMarkerButton);
		processingButton.setStyleName("processingButton");

		editHeadingButtonsPanel.clear();
		editHeadingButtonsPanel.add(addUttButton);
		addUttButton.setStyleName("button");
		editHeadingButtonsPanel.add(editTabsButton);
		editTabsButton.setStyleName("button");
		editHeadingButtonsPanel.add(addDomainDataButton);
		addDomainDataButton.setStyleName("button");
		editHeadingButtonsPanel.add(addFilterButton);
		addFilterButton.setStyleName("button");
		editHeadingButtonsPanel.setVisible(false);
		editHeadingButtonsPanel.setStyleName("editHeadingPanel");

		// history
		historyPanel.clear();
		historyPanel.setStyleName("historyPanel");
		historyPanel.add(historyHeadingLabel);
		historyHeadingLabel.setStyleName("historyHeading");
		historyPanel.add(historyUtterancesScrollPanel);
		historyUtterancesScrollPanel.setWidth("800px");
		historyUtterancesScrollPanel.setHeight("65px");
		historyUtterancesPanel.setWidth("750px");
		historyUtterancesPanel.setStyleName("historyUtterancesPanel");

		// report panel
		reportContentHeadingPanel.clear();
		reportContentHeadingPanel.add(reportHeadingTable);
		reportContentHeadingPanel.setStyleName("reportContentScrollPanel");
		reportContentHeadingPanel.setWidth("880px");
		reportContentScrollPanel.add(reportTable);
		reportContentScrollPanel.setStyleName("reportContentScrollPanel");
		reportContentScrollPanel.setWidth("880px");
		reportContentScrollPanel.setHeight("450px");
		reportPanel.clear();
		reportPanel.add(reportContentHeadingPanel);
		reportPanel.add(reportContentScrollPanel);
		reportPanel.add(reportButtonsPanel);

		reportButtonsPanel.clear();
		reportButtonsPanel.setStyleName("reportButtons");
		printReportButton.setStyleName("button");
		reportButtonsPanel.add(printReportButton);
		exportReportButton.setStyleName("button");
		reportButtonsPanel.add(exportReportButton);
		closeReportButton.setStyleName("button");
		reportButtonsPanel.add(closeReportButton);
		reportPanel.setWidth("900px");
		reportPanel.setHeight("500px");

		// free text panel
		preparedFreeTextPanel.clear();
		preparedFreeTextPanel.add(preparedFreeTextSemKeyLabel);
		preparedFreeTextPanel.add(preparedFreeTextShortTextBox);
		preparedFreeTextPanel.add(preparedFreeTextLabel);
		preparedFreeTextPanel.add(preparedFreeTextTextArea);
		preparedFreeTextTextArea.setText("");
		preparedFreeTextShortTextBox.setText("");
		preparedFreeTextTextArea.setCharacterWidth(50);
		preparedFreeTextTextArea.setVisibleLines(5);
		preparedFreeTextButtonsPanel.clear();
		preparedFreeTextButtonsPanel.add(addFreeTextButton);
		preparedFreeTextButtonsPanel.add(deleteFreeTextButton);
		preparedFreeTextButtonsPanel.add(editFreeTextButton);
		preparedFreeTextButtonsPanel.add(closeFreeTextButton);
		preparedFreeTextPanel.add(preparedFreeTextButtonsPanel);
		addFreeTextButton.setStyleName("button");
		deleteFreeTextButton.setStyleName("button");
		editFreeTextButton.setStyleName("button");
		closeFreeTextButton.setStyleName("button");
		preparedFreeTextSemKeyLabel.setStyleName("labelVertical");
		preparedFreeTextSemKeyLabel.addStyleName("strong");
		preparedFreeTextLabel.setStyleName("labelVertical");
		preparedFreeTextLabel.addStyleName("strong");
		preparedFreeTextPanel.setStyleName("preparedFreeTextPanel");
		editFreeTextPanel.clear();
		editFreeTextPanel.add(preparedFreeTextPanel);

		// add to Layout
		leftPanel.clear();
		leftPanel.add(historyPanel);
		leftPanel.add(editHeadingButtonsPanel);
		leftPanel.add(domainDataTabPanel);
		leftPanel.add(dialogueStructurePanel);

		recoveryPanel.clear();
		recoveryPanel.add(recoveryHeadingLabel);
		recoveryPanel.add(processingButton);
		recoveryPanel.add(recoveryScrollPanel);
		experimentNotesHeading.setStyleName("experimentNotesHeading");
		experimentNotesHeading.setWidth("100px");

		rightPanel.clear();
		rightPanel.add(recoveryPanel);

		notesHeadingPanel.add(experimentNotesHeading);
		notesHeadingPanel.add(exportNotesButton);
		exportNotesButton.setStyleName("experimentNotesHeadingButton");
		notesHeadingPanel.setStyleName("notesHeadingPanel");

		rightPanel.add(notesHeadingPanel);
		rightPanel.add(experimentNotesPanel);

		horLayoutPanel.clear();
		horLayoutPanel.add(leftPanel);
		horLayoutPanel.add(rightPanel);

		layoutPanel.clear();
		layoutPanel.add(userPanel);
		layoutPanel.add(horLayoutPanel);

		// handler
		processingButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				statusUpdate = false;
				getTimeStamp();
			}
		});

		startEditButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// turn status update off
				statusUpdate = false;

				reloadFreeText = true;

				endEditButton.setVisible(true);
				startEditButton.setVisible(false);
				editHeadingButtonsPanel.setVisible(true);

				sendFreeTextButton.setVisible(false);

				// standard response utterances
				for (int i = 0; i < countUtt; i++) {
					uttPanel[i].setStyleName("utteranceEdit");
					uttPanel[i].getWidget(0).setVisible(false);
					uttPanel[i].getWidget(1).setVisible(true);
					uttPanel[i].getWidget(2).setVisible(false);
					uttPanel[i].getWidget(4).setVisible(true);
					uttPanel[i].getWidget(5).setVisible(true);
				}
				// domain data
				for (int i = 0; i < countDomainUtt; i++) {
					domainUttPanel[i].setStyleName("utteranceEdit");
					domainUttPanel[i].getWidget(1).setVisible(false);
					domainUttPanel[i].getWidget(2).setVisible(true);
				}
				// free text elements
				if (domainDataFreeTextPanels != null) {
					for (int i = 0; i < domainDataFreeTextPanels.length; i++) {
						domainDataFreeTextPanels[i]
								.setStyleName("utteranceEdit");
						domainDataFreeTextPanels[i].getWidget(0).setVisible(
								false);
						domainDataFreeTextPanels[i].getWidget(1).setVisible(
								true);
					}
				}
				// slots
				if (domainDataSlotEditButtons != null) {
					for (int i = 0; i < domainDataSlotEditButtons.length; i++) {
						domainDataSlotEditButtons[i].setVisible(true);
					}
				}
			}
		});

		endEditButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// turn on status information
				statusUpdate = true;
				reloadMode = 0;
				reload = true;
				reloadFreeText = false;

				endEditButton.setVisible(false);
				startEditButton.setVisible(true);
				editHeadingButtonsPanel.setVisible(false);

				sendFreeTextButton.setVisible(true);

				// standard response utterances
				for (int i = 0; i < countUtt; i++) {
					uttPanel[i].setStyleName("utterance");
					uttPanel[i].getWidget(0).setVisible(true);
					uttPanel[i].getWidget(1).setVisible(false);
					uttPanel[i].getWidget(2).setVisible(true);
					uttPanel[i].getWidget(4).setVisible(false);
					uttPanel[i].getWidget(5).setVisible(false);

				}
				// domain data

				for (int i = 0; i < countDomainUtt; i++) {
					domainUttPanel[i].setStyleName("utterance");
					domainUttPanel[i].getWidget(1).setVisible(true);
					domainUttPanel[i].getWidget(2).setVisible(false);
				}
				// free text
				if (domainDataFreeTextPanels != null) {
					for (int i = 0; i < domainDataFreeTextPanels.length; i++) {
						domainDataFreeTextPanels[i].setStyleName("utterance");
						domainDataFreeTextPanels[i].getWidget(0).setVisible(
								true);
						domainDataFreeTextPanels[i].getWidget(1).setVisible(
								false);
					}
				}
				// slots
				if (domainDataSlotEditButtons != null) {
					for (int i = 0; i < domainDataSlotEditButtons.length; i++) {
						domainDataSlotEditButtons[i].setVisible(false);
					}
				}
			}
		});

		cancelUttEditButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				editUtterancesPopup.hide();
			}
		});

		changeUttEditButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				// clean from hivens
				semKey = clearHiven(semKeyTextBox.getText());
				rank = clearHiven(rankTextBox.getText());
				text = clearHiven(textTextArea.getText());
				audio = clearHiven(audioFileTextBox.getText());
				mm = clearHiven(mmFileTextBox.getText());
				transtext = clearHiven(translTextArea.getText());
				transaudio = clearHiven(translAudioFileTextBox.getText());
				transmm = clearHiven(translMMFileTextBox.getText());

				selectedTab = dialogueStructurePanel.getTabBar()
						.getSelectedTab();

				String sql = "Update recording set semkey = '" + semKey
						+ "', section = " + editTabUttList.getSelectedIndex()
						+ ", rank = " + rank + ", origtext = '" + text
						+ "', origaudiofile = '" + audio + "', origmmfile = '"
						+ mm + "', transtext = '" + transtext
						+ "', transaudiofile = '" + transaudio
						+ "', transmmfile = '" + transmm + "' where id = "
						+ editUtt;
				changeUtt(sql, 1);
				editUtterancesPopup.hide();
			}
		});

		changeUttDomainEditButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				semKey = clearHiven(semKeyDomainTextBox.getText());
				text = clearHiven(textDomainTextArea.getText());
				audio = clearHiven(audioFileDomainTextBox.getText());
				mm = clearHiven(mmFileDomainTextBox.getText());
				transtext = clearHiven(translDomainTextArea.getText());
				transaudio = clearHiven(translAudioFileDomainTextBox.getText());
				transmm = clearHiven(translMMFileDomainTextBox.getText());

				selectedDomainTab = domainDataTabPanel.getTabBar()
						.getSelectedTab();

				String sql1 = "Update domaindata set semkey = '" + semKey
						+ "', origtext = '" + text + "', origaudiofile = '"
						+ audio + "', origmmfile = '" + mm + "', transtext = '"
						+ transtext + "', transaudiofile = '" + transaudio
						+ "', transmmfile = '" + transmm + "' where id = "
						+ editDomainUtt + "; ";

				// build sql to update the different filter
				String sql2 = "";
				if (slotsExist) {
					for (int i = 0; i < filterHeadingLabel.length; i++) {
						sql2 = sql2
								+ "update domaindataslot inner join slot on domaindataslot.slotid = slot.id set slotid = "
								+ filterList[i].getValue(filterList[i]
										.getSelectedIndex())
								+ " where name = '"
								+ filterHeadingLabel[i].getText()
								+ "' and dataid = " + editDomainUtt + "; ";
					}
				}

				// Merge the sql statements
				String sql = sql1 + sql2;
				changeUtt(sql, 1);
				editDomainDataPopup.hide();
			}
		});

		editFilterSaveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				newFilter = clearHiven(editFilterTextBox.getText());

				selectedDomainTab = domainDataTabPanel.getTabBar()
						.getSelectedTab();

				String sql = "Update Slot set name = '" + newFilter
						+ "' where name = '" + editSlot + "' and expid = "
						+ expId + ";";
				changeUtt(sql, 4);
			}
		});

		editFilterDeleteButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				selectedDomainTab = domainDataTabPanel.getTabBar()
						.getSelectedTab();

				String sql = "Delete domaindataslot from domaindataslot inner join slot where slot.id = domaindataslot.slotid and slot.name = '"
						+ editSlot + "' and slot.expid = " + expId + "; ";
				sql = sql + "Delete from slot where name = '" + editSlot
						+ "' and expid = " + expId + ";";
				changeUtt(sql, 1);
				editSlotPopUp.hide();
			}
		});

		deleteUttEditButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				selectedTab = dialogueStructurePanel.getTabBar()
						.getSelectedTab();

				String sql = "Delete from recording where id = " + editUtt;
				changeUtt(sql, 1);
				editUtterancesPopup.hide();
			}
		});

		deleteUttDomainEditButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				selectedDomainTab = domainDataTabPanel.getTabBar()
						.getSelectedTab();

				String sql = "Delete from domaindata where id = "
						+ editDomainUtt + "; ";
				sql = sql + "Delete from domaindataslot where dataid = "
						+ editDomainUtt + ";";
				changeUtt(sql, 1);
				editDomainDataPopup.hide();
			}
		});

		addFilterValueButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				selectedDomainTab = domainDataTabPanel.getTabBar()
						.getSelectedTab();

				addFilterSlot();
				addFilterValueTextBox.setText("");
			}
		});

		addUttEditButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				semKey = clearHiven(semKeyTextBox.getText());
				rank = clearHiven(rankTextBox.getText());
				text = clearHiven(textTextArea.getText());
				audio = clearHiven(audioFileTextBox.getText());
				mm = clearHiven(mmFileTextBox.getText());
				transtext = clearHiven(translTextArea.getText());
				transaudio = clearHiven(translAudioFileTextBox.getText());
				transmm = clearHiven(translMMFileTextBox.getText());

				selectedTab = dialogueStructurePanel.getTabBar()
						.getSelectedTab();

				String sql = "Insert into recording (expid, semkey, section, rank, origtext, origaudiofile, origmmfile, transtext, transaudiofile, transmmfile) values ("
						+ expId
						+ ", '"
						+ semKey
						+ "', "
						+ editTabUttList.getSelectedIndex()
						+ ", "
						+ rank
						+ ", '"
						+ text
						+ "', '"
						+ audio
						+ "', '"
						+ mm
						+ "', '"
						+ transtext
						+ "', '"
						+ transaudio
						+ "', '"
						+ transmm
						+ "')";

				editUtterancesPopup.hide();
				changeUtt(sql, 1);
			}
		});

		deleteFreeTextButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String sql = "Delete from freetext where id = "
						+ currentFreeTextElement;
				selectedDomainTab = domainDataTabPanel.getTabBar()
						.getSelectedTab();
				reloadMode = 1;
				changeFreeText(sql, 1);
				editFreeTextPopUp.hide();
			}
		});

		editFreeTextButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String sql = "Update freetext set text = '"
						+ preparedFreeTextTextArea.getText() + "', semkey = '"
						+ preparedFreeTextShortTextBox.getText()
						+ "' where id =" + currentFreeTextElement;
				selectedDomainTab = domainDataTabPanel.getTabBar()
						.getSelectedTab();
				reloadMode = 1;
				changeFreeText(sql, 1);
			}
		});

		addUttDomainEditButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				// clear hivens
				semKey = clearHiven(semKeyDomainTextBox.getText());
				text = clearHiven(textDomainTextArea.getText());
				audio = clearHiven(audioFileDomainTextBox.getText());
				mm = clearHiven(mmFileDomainTextBox.getText());
				transtext = clearHiven(translDomainTextArea.getText());
				transaudio = clearHiven(translAudioFileDomainTextBox.getText());
				transmm = clearHiven(translMMFileDomainTextBox.getText());

				selectedDomainTab = domainDataTabPanel.getTabBar()
						.getSelectedTab();

				String sql = "Insert into domaindata (expid, semkey, section, rank, origtext, origaudiofile, origmmfile, transtext, transaudiofile, transmmfile) values ("
						+ expId
						+ ", '"
						+ semKey
						+ "', "
						+ 1
						+ ", "
						+ 1
						+ ", '"
						+ text
						+ "', '"
						+ audio
						+ "', '"
						+ mm
						+ "', '"
						+ transtext
						+ "', '"
						+ transaudio
						+ "', '"
						+ transmm
						+ "')";

				changeUtt(sql, 3);
			}
		});

		editFilterAddSlotButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getFilterRank();
			}
		});

		addUttButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				selectedTab = dialogueStructurePanel.getTabBar()
						.getSelectedTab();

				editTabUttList.setSelectedIndex(selectedTab + 1);

				editButtonsPanel.setStyleName("addButtons");
				addUttEditButton.setVisible(true);
				deleteUttEditButton.setVisible(false);
				changeUttEditButton.setVisible(false);
				semKeyTextBox.setText("");
				textTextArea.setText("");
				audioFileTextBox.setText("");
				mmFileTextBox.setText("");
				translTextArea.setText("");
				translAudioFileTextBox.setText("");
				translMMFileTextBox.setText("");
				rankTextBox.setText("1");
				editUtterancesPopup.show();
				editUtterancesPopup.center();
			}
		});

		addDomainDataButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				editDomainButtonsPanel.setStyleName("addButtons");
				addUttDomainEditButton.setVisible(true);
				deleteUttDomainEditButton.setVisible(false);
				changeUttDomainEditButton.setVisible(false);

				semKeyDomainTextBox.setText("");
				textDomainTextArea.setText("");
				audioFileDomainTextBox.setText("");
				mmFileDomainTextBox.setText("");
				translDomainTextArea.setText("");
				translAudioFileDomainTextBox.setText("");
				translMMFileDomainTextBox.setText("");

				if (filterList != null) {
					for (int i = 0; i < filterList.length; i++) {
						filterList[i].setItemSelected(0, true);
					}
				}

				editDomainDataPopup.show();
				editDomainDataPopup.center();

			}
		});

		editTabsButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				editTabsErrorLabel.setText("");
				editTabsPopup.show();
				editTabsPopup.center();
			}
		});

		addPreparedFreeTextElementsButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				preparedFreeTextTextArea.setText("");
				preparedFreeTextShortTextBox.setText("");
				addFreeTextButton.setVisible(true);
				editFreeTextButton.setVisible(false);
				deleteFreeTextButton.setVisible(false);
				editFreeTextPopUp.show();
				editFreeTextPopUp.center();
				preparedFreeTextButtonsPanel.setStyleName("addPreparedText");
				selectedTab = 2;
			}

		});

		closeFreeTextButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				editFreeTextPopUp.hide();
			}
		});

		addFreeTextButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String semkey = clearHiven(preparedFreeTextShortTextBox
						.getText());
				String freeText = clearHiven(preparedFreeTextTextArea.getText());
				String sql = "Insert into freetext (expid, semkey, text) values ("
						+ expId + ", '" + semkey + "', '" + freeText + "');";
				changeFreeText(sql, 1);
				editFreeTextPopUp.hide();
			}

		});

		addTabEditButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (!addTabTextBox.getText().equals("")) {

					tabName = clearHiven(addTabTextBox.getText());
					tabInst = clearHiven(intructionTextArea.getText());

					String sql = "Insert into tab (tabname, notes, exp, rank) values ('"
							+ tabName
							+ "', \""
							+ tabInst
							+ "\", "
							+ expId
							+ "," + countTab + ")";
					changeUtt(sql, 1);
					addTabTextBox.setText("");
					intructionTextArea.setText("");
				}

			}
		});

		cancelTabEditButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				editTabsPopup.hide();
			}
		});

		cancelUttDomainEditButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				editDomainDataPopup.hide();
			}
		});

		cancelEditSlotPopUpButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				errorDeleteSlotValueLabel.setText("");
				editFilterAddSlotTextBox.setText("");
				editSlotPopUp.hide();
			}
		});

		cancelAddSlotPopUpButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addSlotPopUp.hide();
				addFilterValueTextBox.setText("");
			}
		});

		addFilterButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addFilterTextBox.setText("Default Filter");
				standardFilterValueTextBox.setText(defaultSlotValue);
				addFilterSlotCollection.clear();
				addFilterSlotCollection.add(addFiltersStandardSlotPanel);
				addFilterSlotCollection.add(standardFilterValueExpLabel);
				addSlotPopUp.show();
				addSlotPopUp.center();
			}
		});

		saveNotesButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getNotesTime();
			}
		});

		experimentNotesTextArea.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
					getNotesTime();
				}
			}

		});

		openReportButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				printReport();
			}
		});

		endExperimentMarkerButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				endExperiment();
			}
		});

		closeReportButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				reportTable.setHTML("");
				printReportPopup.hide();
			}
		});

		printReportButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				openPrintView();
			}
		});

		exportReportButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				openExport();
			}
		});

		exportNotesButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				exportNotes();
			}
		});

		sendFreeTextButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				pushOutput(shoutBoxTexatArea.getText(), "3", 0);
				shoutBoxTexatArea.setText("");
			}
		});

		shoutBoxTexatArea.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
					shoutBoxTexatArea.cancelKey();
					pushOutput(shoutBoxTexatArea.getText(), "3", 0);
					shoutBoxTexatArea.setText("");
				}
			}

		});

		userList.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeUser();
			}

		});

		// Filter
		addFilterSaveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String sql = "";
				for (int i = 1; i < (addFilterSlotCollection.getWidgetCount() - 1); i++) {
					HorizontalPanel hp = (HorizontalPanel) addFilterSlotCollection
							.getWidget(i);
					Label lb = (Label) hp.getWidget(0);

					newFilter = clearHiven(addFilterTextBox.getText());
					newValue = clearHiven(lb.getText());

					sql = sql
							+ "Insert into slot (expid, name, value, type, rank) values ("
							+ expId + ", '" + newFilter + "', '" + newValue
							+ "', " + 1 + ", " + rankEstimation + "); ";
				}

				newFilter = clearHiven(addFilterTextBox.getText());
				newValue = clearHiven(standardFilterValueTextBox.getText());

				sql = sql
						+ "Insert into slot (expid, name, value, type, rank) values ("
						+ expId + ", '" + newFilter + "', '" + newValue + "', "
						+ 1 + ", " + rankEstimation + "); ";
				changeUtt(sql, 6);
				addSlotPopUp.hide();
			}
		});

		// Setup timer to refresh parts of the site automatically.
		setTimer();

	}

	private void setTimer() {
		refreshTimer = new Timer() {
			@Override
			public void run() {
				if (statusUpdate) {
					getSignal();
				}
			}
		};

		// run refresh
		refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
	}

	private void getSignal() {

		if (reload & userList.getItemCount() > 0) {
			// System.out.println("reload");

			String sql = "Select * from output where experiment = " + expId
					+ " and sender = "
					+ userList.getValue(userList.getSelectedIndex())
					+ " and receiver = " + wizId
					+ " and sign in (0, 1, 2, 3, 4) order by id desc limit 1";

			// Initialize the service remote procedure call
			DatabaseAccessAsync databaseAccessSvc = GWT
					.create(DatabaseAccess.class);

			AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
				public void onFailure(Throwable caught) {

				}

				public void onSuccess(String[][] result) {

					if (result != null) {
						// get signal
						switch (Integer.parseInt(result[0][16])) {
						case 1:
							loggedInHtml
									.setHTML("<div style='color:red; margin-left:5px;'><strong>Logged in</strong></div>");
							statusHtml.setHTML("");
							break;
						case 2:
							loggedInHtml
									.setHTML("<div style='color:red; margin-left:5px;'><strong>Logged in | </strong></div>");
							statusHtml
									.setHTML("<div style='color:green; margin-left:5px;'><strong>Session started</strong></div>");
							break;
						case 3:
							loggedInHtml
									.setHTML("<div style='color:red; margin-left:5px;'><strong>Logged in | </strong></div>");
							statusHtml
									.setHTML("<div style='color:green; margin-left:5px;'><strong>Session running</strong></div>");
							break;
						case 4:
							loggedInHtml
									.setHTML("<div style='color:red; margin-left:5px;'><strong>Logged in | </strong></div>");
							statusHtml
									.setHTML("<div style='color:navy; margin-left:5px;'><strong>Session stopped</strong></div>");
							break;
						default:
							loggedInHtml
									.setHTML("<div style='margin-left:5px;'><strong>Logged out</strong></div>");
							statusHtml.setHTML("");
							break;
						}

						// check for user input
						if (Integer.parseInt(result[0][20]) == 1) {

							getInput(result[0][1], result[0][0], "1");
							statusUpdate = false;
						}

					} else {

					}
				}
			};

			databaseAccessSvc.retrieveData(sql, callback);

			AsyncCallback<Void> dbCloseCallBack = new AsyncCallback<Void>() {

				public void onFailure(Throwable caught) {

				}

				public void onSuccess(Void result) {

				}
			};
			databaseAccessSvc.closeConnection(dbCloseCallBack);
		}
	}

	private void getInput(String text, String id, String mode) {

		// Initialize the service remote procedure call
		if (componentFactorySvc == null) {
			componentFactorySvc = GWT.create(ComponentFactory.class);
		}

		AsyncCallback<Vector<String>> callback = new AsyncCallback<Vector<String>>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Vector<String> result) {

				String t = "";
				if (result.size() == 1) {
					t = result.get(0);
				} else {
					t = result.get(0) + " (";
					for (int i = 1; i < result.size() - 1; i++) {
						t = t + result.get(i) + ", ";
					}

					if (result.size() > 1) {
						t = t + result.get(result.size() - 1) + ")";
					} else {
						t = t + ")";
					}
				}

				historyUtterancesPanel.insert(new HTML("&rarr; " + t), 0);
				historyUtterancesPanel.getWidget(0).setStyleName("asrInput");

				// Wizard Correction
				int i = inputElementVectorHorPanels.size();
				inputTextVector.add(i, new String());
				inputTextVector.set(i, result.get(0));
				inputTextAreaVectorTextAreas.add(i, new TextArea());
				inputTextAreaVectorTextAreas.get(i).setText(result.get(0));
				inputTextAreaVectorTextAreas.get(i).setCharacterWidth(50);
				inputTextAreaVectorTextAreas.get(i).setVisibleLines(3);
				inputButtonVectorButtons.add(i, new Button("Send"));
				inputButtonVectorButtons.get(i).setStyleName("button");
				inputElementVectorHorPanels.add(i, new HorizontalPanel());
				inputElementVectorHorPanels.get(i).add(
						inputTextAreaVectorTextAreas.get(i));
				inputElementVectorHorPanels.get(i).add(
						inputButtonVectorButtons.get(i));
				wizardCorrectionPanel.add(inputElementVectorHorPanels.get(i));
				addInputElementClickHandler(inputButtonVectorButtons.get(i), i,
						wizardCorrectionPanel.getWidgetCount() - 1);
				addInputElementKeyPressHandler(
						inputTextAreaVectorTextAreas.get(i), i,
						wizardCorrectionPanel.getWidgetCount() - 1);

				// N-Best list
				int j = inputNBestListVectorVerPanels.size();
				int k = inputNBestListVectorHorPanels.size();
				inputNBestListVectorVerPanels.add(j, new VerticalPanel());
				String list = "";
				for (int x = 0; x < result.size(); x++) {
					list = list + ";" + result.get(x);
					inputNBestListVectorLabels.add(k, new Label());
					inputNBestListVectorLabels.get(k).setText(result.get(x));
					inputNBestListVectorButtons.add(k, new Button("Send"));
					inputNBestListVectorButtons.get(k).setStyleName("button");
					inputNBestListVectorHorPanels.add(k, new HorizontalPanel());
					inputNBestListVectorHorPanels.get(k).add(
							inputNBestListVectorButtons.get(k));
					inputNBestListVectorHorPanels.get(k).add(
							inputNBestListVectorLabels.get(k));
					inputNBestListVectorVerPanels.get(j).add(
							inputNBestListVectorHorPanels.get(k));
					addInputNBestListClickHandler(
							inputNBestListVectorButtons.get(k), k,
							nBestListPanel.getWidgetCount(), j);
					k++;
				}

				inputNBestListAlternatives.add(j, new String(list));

				inputNBestListVectorVerPanels.get(j).add(
						new HTML("<div style=\"margin-left:15px;\">--</div>"));
				nBestListPanel.add(inputNBestListVectorVerPanels.get(j));

				statusUpdate = true;
			}

		};

		componentFactorySvc.getInput(text, id, mode, callback);

	}

	private void addInputElementClickHandler(Button b, final int id,
			final int panelId) {

		b.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				// Temporarily save the original text
				recognized = inputTextVector.get(id);
				pushOutput(inputTextAreaVectorTextAreas.get(id).getText(), "4",
						0);

				while (inputElementVectorHorPanels.get(id).getWidgetCount() > 0) {
					inputElementVectorHorPanels.get(id).getWidget(0)
							.removeFromParent();
				}
				inputElementVectorHorPanels.get(id).clear();

			}

		});

	}

	private void addInputNBestListClickHandler(Button b, final int id,
			final int panelId, final int listId) {

		b.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				String out = inputNBestListVectorLabels.get(id).getText();

				alterantives = "";
				String nlist = inputNBestListAlternatives.get(panelId);
				Vector<Integer> index = new Vector<Integer>();
				Vector<String> list = new Vector<String>();

				// find ;
				for (int i = 0; i < nlist.length(); i++) {
					if (nlist.substring(i, i + 1).equals(";")) {
						index.add(i);
					}
				}

				// check if there is at least one ;
				if (index.size() != 0) {

					list.add(nlist.substring(0, index.get(0)));

					for (int j = 0; j < index.size() - 1; j++) {
						list.add(nlist.substring(index.get(j) + 1,
								index.get(j + 1)));
					}

					// get the last one
					if (index.size() > 1) {
						list.add(nlist.substring(
								index.get(index.size() - 1) + 1, nlist.length()));
					}

				} else {
					// return the whole String - no n-Best list
					list.add(nlist);
				}

				// compare if it is the one that was send an add the rest to
				// alternatives
				for (int j = 0; j < list.size(); j++) {
					System.out.println(list.get(j));
					if (!list.get(j).equals(out)) {
						alterantives = alterantives + list.get(j) + ", ";
					}
				}
				// trim the last and first comma as well as the last and first
				// white space
				alterantives = alterantives.substring(2,
						alterantives.length() - 2);

				pushOutput(out, "5", 0);
				// remove all elements
				while (inputNBestListVectorVerPanels.get(panelId)
						.getWidgetCount() > 0) {
					inputNBestListVectorVerPanels.get(panelId).getWidget(0)
							.removeFromParent();
				}

				inputNBestListVectorVerPanels.get(panelId).clear();

			}

		});

	}

	private void addInputElementKeyPressHandler(TextArea t, final int id,
			final int panelId) {

		t.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
					pushOutput(inputTextAreaVectorTextAreas.get(id).getText(),
							"4", 0);
					inputTextAreaVectorTextAreas.remove(id);
					inputButtonVectorButtons.remove(id);
					inputElementVectorHorPanels.remove(id);
				}
			}
		});

	}

	private void loadUser() {
		String sql = "Select * from user inner join experimentuser where user.id = experimentuser.userid and experimentuser.expid = "
				+ expId + " and user.role = 2 order by user.id asc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				userList.clear();
				if (result != null) {
					userId = Integer.parseInt(result[0][0]);
					for (int i = 0; i < result.length; i++) {
						userList.addItem(result[i][1] + " (" + result[i][0]
								+ ")", result[i][0]);
					}
				} else {
					System.out.println("No users for this experiment");
				}

				// turn reload on
				reload = true;
				// load experiment notes
				loadExperimentNotes(1);

			}

		};

		databaseAccessSvc.retrieveData(sql, callback);

	}

	private void changeUser() {
		userId = Integer
				.parseInt(userList.getValue(userList.getSelectedIndex()));
		loadExperimentNotes(0);
	}

	public VerticalPanel getScreen() {
		return layoutPanel;
	}

	private void loadResponseUtterances() {
		String sql = "Select * from recording where expid = " + expId
				+ " order by rank desc, id asc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {

					// remove click handler
					if (addUtteranceClickHandlerRegistration != null) {
						for (int i = 0; i < addUtteranceClickHandlerRegistration.length; i++) {
							addUtteranceClickHandlerRegistration[i]
									.removeHandler();
							editUtteranceClickHandlerRegistration[i]
									.removeHandler();
							addToFreeClickHandlerRegistration[i]
									.removeHandler();
							uttButtUpClickHandlerRegistration[i]
									.removeHandler();
							uttButtDownClickHandlerRegistration[i]
									.removeHandler();

						}
					}

					addUtteranceClickHandler = new ClickHandler[result.length];
					editUtteranceClickHandler = new ClickHandler[result.length];
					addToFreeTextHandler = new ClickHandler[result.length];
					uttButtonRankUpHandler = new ClickHandler[result.length];
					uttButtonRankDownHandler = new ClickHandler[result.length];

					addUtteranceClickHandlerRegistration = new HandlerRegistration[result.length];
					editUtteranceClickHandlerRegistration = new HandlerRegistration[result.length];
					addToFreeClickHandlerRegistration = new HandlerRegistration[result.length];
					uttButtUpClickHandlerRegistration = new HandlerRegistration[result.length];
					uttButtDownClickHandlerRegistration = new HandlerRegistration[result.length];

					countUtt = result.length;
					uttPanel = new HorizontalPanel[result.length];
					uttLabels = new Label[result.length];
					uttButtons = new Button[result.length];
					uttButtonsEdit = new Button[result.length];
					uttButtonsToFreeText = new Button[result.length];
					uttButtonsRankUpButtons = new Button[result.length];
					uttButtonsRankDownButtons = new Button[result.length];
					recoveryUtterancePanel.clear();
					for (int i = 0; i < result.length; i++) {

						// create panel, label & button for utterance
						uttPanel[i] = new HorizontalPanel();
						uttPanel[i].setHeight("20px");
						uttLabels[i] = new Label();
						uttLabels[i].setText(result[i][5]);
						uttLabels[i].setWidth("640px");
						uttButtonsEdit[i] = new Button("Edit");
						uttButtonsEdit[i].setStyleName("button");
						uttButtons[i] = new Button("Send");
						uttButtons[i].setStyleName("button");
						uttButtonsToFreeText[i] = new Button("Free Text");
						uttButtonsToFreeText[i].setStyleName("button");
						uttButtonsRankUpButtons[i] = new Button("up");
						uttButtonsRankUpButtons[i].setStyleName("button");
						uttButtonsRankDownButtons[i] = new Button("down");
						uttButtonsRankDownButtons[i].setStyleName("button");

						// add handler to buttons using the id
						addUtteranceHandler(uttButtons[i], result[i][0], i);
						addEditHandler(uttButtonsEdit[i], result[i][0], i);
						addToFreeTextHandler(uttButtonsToFreeText[i], i);
						addUttUpHandler(uttButtonsRankUpButtons[i],
								result[i][0], i, result[i][4]);
						addUttDownHandler(uttButtonsRankDownButtons[i],
								result[i][0], i, result[i][4]);

						uttPanel[i].clear();
						uttPanel[i].add(uttButtons[i]);
						uttPanel[i].add(uttButtonsEdit[i]);
						uttPanel[i].add(uttButtonsToFreeText[i]);
						uttPanel[i].add(uttLabels[i]);
						uttPanel[i].add(uttButtonsRankUpButtons[i]);
						uttPanel[i].add(uttButtonsRankDownButtons[i]);

						if (reloadMode < 1) {
							uttButtonsEdit[i].setVisible(false);
							uttButtonsRankUpButtons[i].setVisible(false);
							uttButtonsRankDownButtons[i].setVisible(false);
							uttButtonsToFreeText[i].setVisible(true);
							uttPanel[i].setStyleName("utterance");
						} else {
							uttButtons[i].setVisible(false);
							uttButtonsToFreeText[i].setVisible(false);
							uttButtonsRankUpButtons[i].setVisible(true);
							uttButtonsRankDownButtons[i].setVisible(true);
							uttPanel[i].setStyleName("utteranceEdit");
						}

						// check for frequently used utterances
						if (Integer.parseInt(result[i][3]) == 0) {
							uttLabels[i].setWidth("150px");
							recoveryUtterancePanel.add(uttPanel[i]);

						} else {
							// add utterance
							int section = Integer.parseInt(result[i][3]);
							if (section <= countTab) {

								dialogueUtterancesPanel[Integer
										.parseInt(result[i][3]) - 1]
										.add(uttPanel[i]);
							}
						}

					}

				} else {

				}

				dialogueStructurePanel.selectTab(selectedTab);

				loadDomainData();
			}

		};

		databaseAccessSvc.retrieveData(sql, callback);

	}

	public void addUtteranceHandler(Button b, final String id, final int i) {

		addUtteranceClickHandler[i] = new ClickHandler() {
			public void onClick(ClickEvent event) {
				// stop reload to prevent disruption
				statusUpdate = false;
				uttPanel[i].setStyleName("utteranceSent");
				pushOutput(id, "1", i);
			}
		};

		addUtteranceClickHandlerRegistration[i] = b
				.addClickHandler(addUtteranceClickHandler[i]);

	}

	public void addUttUpHandler(Button b, final String id, final int i,
			final String rank) {

		int newRank = Integer.parseInt(rank);
		// increase
		newRank++;

		final String sql = "Update recording set rank = " + newRank
				+ " where id = " + id;

		uttButtonRankUpHandler[i] = new ClickHandler() {
			public void onClick(ClickEvent event) {
				updateRank(sql);
			}
		};

		uttButtUpClickHandlerRegistration[i] = b
				.addClickHandler(uttButtonRankUpHandler[i]);

	}

	public void addUttDownHandler(Button b, final String id, final int i,
			final String rank) {

		int newRank = Integer.parseInt(rank);

		final String sql = "Update recording set rank = " + newRank
				+ " where id = " + id;
		;

		// only reduce if more than 1
		if (newRank > 1) {
			newRank--;
		}

		uttButtonRankDownHandler[i] = new ClickHandler() {
			public void onClick(ClickEvent event) {
				updateRank(sql);

			}
		};

		uttButtDownClickHandlerRegistration[i] = b
				.addClickHandler(uttButtonRankDownHandler[i]);

	}

	public void addDomainUtteranceHandler(Button b, final String id, final int i) {
		addDomainUtteranceClickHandler[i] = new ClickHandler() {
			public void onClick(ClickEvent event) {
				// stop reload to prevent disruption
				statusUpdate = false;
				domainUttPanel[i].setStyleName("utteranceSent");
				pushOutput(id, "2", i);
			}
		};
		addDomainUtteranceClickHandlerRegistration[i] = b
				.addClickHandler(addDomainUtteranceClickHandler[i]);

		selectedDomainTab = domainDataTabPanel.getTabBar().getSelectedTab();
	}

	public void addFreeTextAddHandler(Button b, final int i) {
		addFreeTextHandler[i] = new ClickHandler() {
			public void onClick(ClickEvent event) {
				// add free text to text field
				shoutBoxTexatArea.setText(shoutBoxTexatArea.getText() + " "
						+ domainDataFreeTextLabels[i].getText());
			}
		};
		addFreeTextHandlerRegistration[i] = b
				.addClickHandler(addFreeTextHandler[i]);
	}

	public void addEditHandler(Button b, final String id, final int uttId) {
		editUtteranceClickHandler[uttId] = new ClickHandler() {
			public void onClick(ClickEvent event) {
				addUttEditButton.setVisible(false);
				deleteUttEditButton.setVisible(true);
				changeUttEditButton.setVisible(true);
				editUtt = id;
				setEditId(uttId);
				loadUtt();
				editButtonsPanel.setStyleName("editButtons");
				editUtterancesPopup.show();
				editUtterancesPopup.center();
			}
		};
		editUtteranceClickHandlerRegistration[uttId] = b
				.addClickHandler(editUtteranceClickHandler[uttId]);
	}

	public void addToFreeTextHandler(Button b, final int uttId) {
		addToFreeTextHandler[uttId] = new ClickHandler() {
			public void onClick(ClickEvent event) {

				shoutBoxTexatArea.setText(shoutBoxTexatArea.getText() + " "
						+ uttLabels[uttId].getText());

			}
		};
		addToFreeClickHandlerRegistration[uttId] = b
				.addClickHandler(addToFreeTextHandler[uttId]);
	}

	public void addDomainUtteranceEditHandler(Button b, final String id,
			final int uttId) {
		editDomainUtteranceClickHandler[uttId] = new ClickHandler() {
			public void onClick(ClickEvent event) {
				addUttDomainEditButton.setVisible(false);
				deleteUttDomainEditButton.setVisible(true);
				changeUttDomainEditButton.setVisible(true);
				editDomainUtt = id;
				if (slotsExist) {
					loadDomainUtt();
				} else {
					loadDomainUttNormal();
				}
				editDomainButtonsPanel.setStyleName("editButtons");
				editDomainDataPopup.show();
				editDomainDataPopup.center();
			}
		};
		editDomainUtteranceClickHandlerRegistration[uttId] = b
				.addClickHandler(editDomainUtteranceClickHandler[uttId]);

		selectedDomainTab = domainDataTabPanel.getTabBar().getSelectedTab();
	}

	public void addFreeTextEditHandler(Button b, final String id,
			final String text, final String semk, final int uttId) {
		editFreeTextHandler[uttId] = new ClickHandler() {
			public void onClick(ClickEvent event) {
				preparedFreeTextTextArea.setText(text);
				preparedFreeTextShortTextBox.setText(semk);
				addFreeTextButton.setVisible(false);
				editFreeTextButton.setVisible(true);
				deleteFreeTextButton.setVisible(true);
				editFreeTextPopUp.show();
				editFreeTextPopUp.center();
				preparedFreeTextButtonsPanel.setStyleName("editPreparedText");
				currentFreeTextElement = id;
			}
		};
		editFreeTextHandlerRegistration[uttId] = b
				.addClickHandler(editFreeTextHandler[uttId]);
	}

	public void addChangeSlotValueHandler(Button b, final int itemId,
			final int listId) {

		changeSlotValueHandler[itemId] = new ClickHandler() {
			public void onClick(ClickEvent event) {

				newValue = clearHiven(editFilterSlotValuesTextBox[itemId]
						.getText());

				String sql = "Update slot set value = '" + newValue
						+ "' where value = '"
						+ filterList[listId].getItemText(itemId)
						+ "' and expid =" + expId;
				changeUtt(sql, 1);
			}
		};

		changeSlotValueHandlerRegistration[itemId] = b
				.addClickHandler(changeSlotValueHandler[itemId]);

		selectedDomainTab = domainDataTabPanel.getTabBar().getSelectedTab();

	}

	public void addDeleteSlotValueHandler(Button b, final int itemId,
			final int listId) {

		deleteSlotValueHandler[itemId] = new ClickHandler() {
			public void onClick(ClickEvent event) {
				String sql1 = "select count(*) from slot inner join domaindataslot where slot.id = domaindataslot.slotid and slot.value = '"
						+ filterList[listId].getItemText(itemId)
						+ "' and name = '"
						+ filterHeadingLabel[listId].getText()
						+ "' and expid =" + expId;
				String sql2 = "delete from slot where value = '"
						+ filterList[listId].getItemText(itemId)
						+ "' and name = '"
						+ filterHeadingLabel[listId].getText()
						+ "' and  expid =" + expId;
				getSlotValueUseCount(sql1, sql2, itemId);
			}
		};

		deleteSlotValueHandlerRegistration[itemId] = b
				.addClickHandler(deleteSlotValueHandler[itemId]);

		selectedDomainTab = domainDataTabPanel.getTabBar().getSelectedTab();

	}

	public void addSlotEditHandler(Button b, final int id) {

		editSlotHandler[id] = new ClickHandler() {
			public void onClick(ClickEvent event) {
				editFilterTextBox.setText(filterHeadingLabel[id].getText());
				editSlot = filterHeadingLabel[id].getText();
				editSlotId = id;
				editSlotPopUp.show();
				editSlotPopUp.center();

				// remove handler
				if (changeSlotValueHandlerRegistration != null) {
					for (int i = 0; i < changeSlotValueHandlerRegistration.length; i++) {
						changeSlotValueHandlerRegistration[i].removeHandler();
						deleteSlotValueHandlerRegistration[i].removeHandler();
					}
				}

				changeSlotValueHandler = new ClickHandler[filterList[id]
						.getItemCount()];
				deleteSlotValueHandler = new ClickHandler[filterList[id]
						.getItemCount()];
				changeSlotValueHandlerRegistration = new HandlerRegistration[filterList[id]
						.getItemCount()];
				deleteSlotValueHandlerRegistration = new HandlerRegistration[filterList[id]
						.getItemCount()];
				editFilterSlotValuesTextBox = new TextBox[filterList[id]
						.getItemCount()];
				editFilterDeletSlotButton = new Button[filterList[id]
						.getItemCount()];
				editFilterChangeSlotButton = new Button[filterList[id]
						.getItemCount()];
				editFilterSlotsPanel = new HorizontalPanel[filterList[id]
						.getItemCount()];

				editFilterSlotCollectionPanel.clear();
				for (int i = 0; i < filterList[id].getItemCount(); i++) {
					editFilterSlotValuesTextBox[i] = new TextBox();
					editFilterSlotValuesTextBox[i].setText(filterList[id]
							.getItemText(i));
					editFilterChangeSlotButton[i] = new Button("Save Changes ");
					editFilterChangeSlotButton[i].setStyleName("button");
					addChangeSlotValueHandler(editFilterChangeSlotButton[i], i,
							id);

					editFilterDeletSlotButton[i] = new Button(
							"Delete Filter Value");
					editFilterDeletSlotButton[i].setStyleName("button");
					addDeleteSlotValueHandler(editFilterDeletSlotButton[i], i,
							id);

					editFilterSlotsPanel[i] = new HorizontalPanel();
					editFilterSlotsPanel[i].add(editFilterSlotValuesTextBox[i]);
					editFilterSlotsPanel[i].add(editFilterChangeSlotButton[i]);
					editFilterSlotsPanel[i].add(editFilterDeletSlotButton[i]);
					editFilterSlotCollectionPanel.add(editFilterSlotsPanel[i]);
					editFilterSlotCollectionPanel
							.setStyleName("editFilterSlotsPanel");
				}

			}
		};

		editSlotHandlerRegistration[id] = b
				.addClickHandler(editSlotHandler[id]);

	}

	public void addDelHandler(Button b, final String id, final int uttId) {
		b.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String sql = "Delete from recording where id = " + id;
				uttPanel[uttId].setVisible(false);
				startEditButton.setVisible(true);
				endEditButton.setVisible(false);
				addUttButton.setVisible(true);
				editTabsButton.setVisible(true);
				changeUtt(sql, 2);
			}
		});
	}

	public void addDelTabHandler(Button b, final String id, final int tabId) {
		delTabClickHandler[tabId] = new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (dialogueUtterancesPanel[tabId].getWidgetCount() == 0) {
					String sql = "Delete from tab where id = " + id;
					editTabs[tabId].setVisible(false);
					countTab--;
					changeUtt(sql, 1);
				} else {

					editTabsErrorLabel
							.setText("Error: It is impossible to delete a tab that holds utterances! Please delete the utterances first or move them to a different tab.");
				}
			}
		};
		delTabClickHandlerRegistration[tabId] = b
				.addClickHandler(delTabClickHandler[tabId]);
	}

	public void addSaveTabHandler(Button b, final String id, final int tabId) {
		saveTabClickHandler[tabId] = new ClickHandler() {
			public void onClick(ClickEvent event) {
				tabName = clearHiven(tabText[tabId].getText());
				tabInst = clearHiven(tabInstructions[tabId].getText());
				String sql = "Update tab set tabname = '" + tabName
						+ "', notes = \"" + tabInst + "\" where id = " + id;
				changeUtt(sql, 1);
			}
		};
		saveTabClickHandlerRegistration[tabId] = b
				.addClickHandler(saveTabClickHandler[tabId]);
	}

	public void pushOutput(final String output, final String mode, final int i) {

		// Initialize the service remote procedure call
		if (componentFactorySvc == null) {
			componentFactorySvc = GWT.create(ComponentFactory.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				// change the color of the old history elements
				if (historyUtterancesPanel.getWidgetCount() > 0) {
					for (int i = 0; i < historyUtterancesPanel.getWidgetCount(); i++) {
						historyUtterancesPanel.getWidget(i).setStyleName(
								"historyOld");
					}
				}

				switch (Integer.parseInt(mode)) {
				case 1: // recorded utterance
				case 2: // domain utterance
				case 3: // free text
					historyUtterancesPanel.insert(new HTML("&larr; " + result),
							0);
					break;
				case 4: // wizard correction
					historyUtterancesPanel.insert(new HTML("&larr; " + result
							+ " (" + recognized + ")"), 0);
					break;
				case 5: // n-best list
					historyUtterancesPanel.insert(new HTML("&larr; " + result
							+ " (" + alterantives + ")"), 0);
					break;

				}

				// turn reload back on
				statusUpdate = true;
			}

		};

		componentFactorySvc.pushOutput(output, userId, mode, callback);

	}

	public void startProcessing(String time) {

		String sql = "Insert into output (textorig, audioorig, mmorig, texttrans, audiotrans, mmtrans, timestamp, experiment, user, sign, sender, receiver) values (\""
				+ "P R O C E S S I N G . . ."
				+ "\", '"
				+ "-"
				+ "', '"
				+ "-"
				+ "', \""
				+ "-"
				+ "\", '"
				+ "-"
				+ "', '"
				+ "-"
				+ "', \""
				+ time
				+ "\", "
				+ expId
				+ ", "
				+ userId
				+ ", 6, "
				+ wizId
				+ ", "
				+ userId + ")";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {

				// change the color of the old history elements
				if (historyUtterancesPanel.getWidgetCount() > 0) {
					for (int i = 0; i < historyUtterancesPanel.getWidgetCount(); i++) {
						historyUtterancesPanel.getWidget(i).setStyleName(
								"historyOld");
					}
				}

				historyUtterancesPanel.insert(new HTML(
						"< P R O C E S S I N G >"), 0);
				statusUpdate = true;
			}

		};

		databaseAccessSvc.storeData(sql, callback);
	}

	public void changeUtt(String sql, final int reload) {

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				if (reload == 4) {
					editSlot = editFilterTextBox.getText();
				}

				if (reload == 5) {
					// reload filter values in pop-up
					reloadFilter();
				}

				if (reload == 6) {
					getLastFilter();
				} else {
					reloadLayout(reload);
				}
			}

		};

		databaseAccessSvc.storeData(sql, callback);
	}

	private void changeFreeText(String sql, final int reload) {

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				// reload
				loadFreeTextElements();
			}

		};

		databaseAccessSvc.storeData(sql, callback);

	}

	private void loadUtt() {
		String sql = "Select * from recording where id = " + editUtt;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				semKeyTextBox.setText(result[0][2]);
				textTextArea.setText(result[0][5]);
				audioFileTextBox.setText(result[0][6]);
				mmFileTextBox.setText(result[0][7]);
				translTextArea.setText(result[0][8]);
				translAudioFileTextBox.setText(result[0][9]);
				translMMFileTextBox.setText(result[0][10]);
				editTabUttList.setSelectedIndex(Integer.parseInt(result[0][3]));
				rankTextBox.setText(result[0][4]);
			}

		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	public void getSlotValueUseCount(final String sql1, final String sql,
			final int id) {

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				if (Integer.parseInt(result[0][0]) == 0) {
					changeUtt(sql, 1);
					editFilterSlotCollectionPanel.remove(id);
					errorDeleteSlotValueLabel.setText("");
				} else {
					errorDeleteSlotValueLabel
							.setText("Error: You cannot delete filter values that are still in use.");
				}
			}

		};

		databaseAccessSvc.retrieveData(sql1, callback);

	}

	private void loadDomainUtt() {
		String sql = "Select * from domaindata inner join (select domaindataslot.dataid, domaindataslot.slotid, slot.name, slot.id, slot.rank from domaindataslot inner join slot where domaindataslot.slotid = slot.id) as q1 where domaindata.id = q1.dataid and domaindata.id = "
				+ editDomainUtt + " order by q1.rank";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				semKeyDomainTextBox.setText(result[0][2]);
				textDomainTextArea.setText(result[0][5]);
				audioFileDomainTextBox.setText(result[0][6]);
				mmFileDomainTextBox.setText(result[0][7]);
				translDomainTextArea.setText(result[0][8]);
				translAudioFileDomainTextBox.setText(result[0][9]);
				translMMFileDomainTextBox.setText(result[0][10]);

				for (int i = 0; i < filterHeadingLabel.length; i++) {
					for (int j = 0; j < filterList[i].getItemCount(); j++)
						if (filterList[i].getValue(j).equals(result[i][14])) {
							filterList[i].setItemSelected(j, true);
						}
				}
			}

		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void loadDomainUttNormal() {
		String sql = "Select * from domaindata where id = " + editDomainUtt;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				semKeyDomainTextBox.setText(result[0][2]);
				textDomainTextArea.setText(result[0][5]);
				audioFileDomainTextBox.setText(result[0][6]);
				mmFileDomainTextBox.setText(result[0][7]);
				translDomainTextArea.setText(result[0][8]);
				translAudioFileDomainTextBox.setText(result[0][9]);
				translMMFileDomainTextBox.setText(result[0][10]);

				editDomainFilterPanel.clear();
			}

		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	public void loadTabs() {
		String sql = "Select * from tab where exp = " + expId
				+ " and visible = 1 order by rank asc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				if (result != null) {

					// remove click handler
					if (saveTabClickHandlerRegistration != null) {
						for (int i = 0; i < saveTabClickHandlerRegistration.length; i++) {
							saveTabClickHandlerRegistration[i].removeHandler();
							delTabClickHandlerRegistration[i].removeHandler();
						}
					}
					saveTabClickHandler = new ClickHandler[result.length];
					delTabClickHandler = new ClickHandler[result.length];
					saveTabClickHandlerRegistration = new HandlerRegistration[result.length];
					delTabClickHandlerRegistration = new HandlerRegistration[result.length];

					// add heading for recovery panel
					countTab = result.length;
					uttHeadings = new Label[result.length];
					utterancesPanel = new VerticalPanel[result.length];
					tabText = new TextBox[result.length];
					tabInstructions = new TextArea[result.length];
					editTabsContainer = new VerticalPanel[result.length];

					tabDelButton = new Button[result.length];
					tabSaveButton = new Button[result.length];
					tabNotesHeading = new Label[result.length];
					tabNotesPanel = new VerticalPanel[result.length];
					tabNotes = new Label[result.length];
					dialogueTabPanel = new VerticalPanel[result.length];
					dialogueTabScrollPanel = new ScrollPanel[result.length];
					notesTabScrollanel = new ScrollPanel[result.length];
					dialogueUtterancesPanel = new VerticalPanel[result.length];
					editTabs = new HorizontalPanel[result.length];
					editTabUttList.clear();
					editTabUttList.addItem("Frequently Used Utterances");
					// clear panel to prepare for reload
					editTabsTabsPanel.clear();
					dialogueStructurePanel.clear();

					for (int i = 0; i < result.length; i++) {

						// build tab layout
						dialogueTabPanel[i] = new VerticalPanel();
						uttHeadings[i] = new Label();
						uttHeadings[i].setText("Utterances:");
						uttHeadings[i].setStyleName("utteranceHeading");
						dialogueTabScrollPanel[i] = new ScrollPanel();
						dialogueTabScrollPanel[i].setWidth("808px");
						notesTabScrollanel[i] = new ScrollPanel();
						notesTabScrollanel[i].setWidth("808px");
						notesTabScrollanel[i].setHeight("80px");
						tabNotesHeading[i] = new Label();
						tabNotesHeading[i].setText("Instructions:");
						tabNotesHeading[i].setStyleName("tabNotesHeading");

						utterancesPanel[i] = new VerticalPanel();
						utterancesPanel[i].clear();
						utterancesPanel[i].add(uttHeadings[i]);
						utterancesPanel[i].add(dialogueTabScrollPanel[i]);

						tabNotesPanel[i] = new VerticalPanel();
						tabNotesPanel[i].clear();
						tabNotesPanel[i].add(tabNotesHeading[i]);
						tabNotesPanel[i].add(notesTabScrollanel[i]);

						dialogueUtterancesPanel[i] = new VerticalPanel();
						dialogueTabPanel[i].clear();
						dialogueTabPanel[i].add(utterancesPanel[i]);
						dialogueTabPanel[i].add(tabNotesPanel[i]);
						dialogueTabScrollPanel[i]
								.add(dialogueUtterancesPanel[i]);

						// tab notes
						tabNotes[i] = new Label();
						tabNotes[i].setText(result[i][4]);
						tabNotes[i].setStyleName("experimentTabNotes");
						notesTabScrollanel[i].add(tabNotes[i]);

						if (result[i][4].equals("")) {
							tabNotesPanel[i].setVisible(false);
						} else {
							tabNotesPanel[i].setVisible(true);
						}

						dialogueStructurePanel.add(dialogueTabPanel[i],
								result[i][1]);

						if (Integer.parseInt(result[i][5]) != 1) {
							editTabUttList.addItem(result[i][1]);
						}

						// build edit tab structure
						tabText[i] = new TextBox();
						tabText[i].setWidth("250px");
						tabText[i].setText(result[i][1]);
						tabInstructions[i] = new TextArea();
						tabInstructions[i].setCharacterWidth(60);
						tabInstructions[i].setVisibleLines(5);
						tabInstructions[i].setText(result[i][4]);
						editTabs[i] = new HorizontalPanel();
						editTabs[i].clear();
						editTabs[i].add(tabText[i]);
						tabSaveButton[i] = new Button("Save Changes");
						tabSaveButton[i].setStyleName("button");
						addSaveTabHandler(tabSaveButton[i], result[i][0], i);
						editTabs[i].add(tabSaveButton[i]);
						tabDelButton[i] = new Button("Delete");
						tabDelButton[i].setStyleName("button");
						addDelTabHandler(tabDelButton[i], result[i][0], i);
						editTabs[i].add(tabDelButton[i]);
						if (i == 0) {
							tabDelButton[i].setVisible(false);
						}

						editTabs[i].setStyleName("editTabs");

						editTabsContainer[i] = new VerticalPanel();
						editTabsContainer[i].clear();
						editTabsContainer[i].add(editTabs[i]);
						editTabsContainer[i].add(tabInstructions[i]);

						editTabsTabsPanel.add(editTabsContainer[i]);

					}

					editTabsPanel.setStyleName("editTabsPanel");

					editTabsScrollPanel.setWidth("500px");
					editTabsScrollPanel.setHeight("150px");
					// clear panel before adding (implemented to allow reload)
					editTabsScrollPanel.clear();
					editTabsScrollPanel.add(editTabsTabsPanel);

					addTabTextBox.setWidth("150px");
					addTabEditButton.setStyleName("button");
					addTabPanel.clear();
					tabNameLabel.setStyleName("labelVertical");
					tabNameLabel.addStyleName("strong");
					tabInstructionLabel.setStyleName("labelVertical");
					tabInstructionLabel.addStyleName("strong");

					newTabPanel.add(tabNameLabel);
					addTabPanel.add(addTabTextBox);
					addTabPanel.add(addTabEditButton);
					newTabPanel.add(addTabPanel);
					newTabPanel.add(tabInstructionLabel);
					intructionTextArea.setCharacterWidth(70);
					intructionTextArea.setVisibleLines(8);
					newTabPanel.add(intructionTextArea);

					editTabsPanel.clear();
					editTabsPanel.add(editTabsScrollPanel);
					editTabsPanel.add(editTabsErrorLabel);
					editTabsPanel.add(newTabPanel);
					editTabsPanel.add(cancelTabEditButton);
					cancelTabEditButton.setStyleName("cancleEditTabButton");

					dialogueStructurePanel.add(wizardCorrectionScrollPanel,
							"Wizard Correction");
					dialogueStructurePanel.add(nBestListScrollPanel,
							"N-best List");

				} else {
					System.out.println("No tabs connected to this experiment!");

				}
				dialogueStructurePanel.selectTab(0);

				// load utterances
				loadResponseUtterances();
			}

		};

		databaseAccessSvc.retrieveData(sql, callback);

	}

	private void reloadLayout(int mode) {

		reloadMode = mode;

		if (mode > 0) {
			// clear all variables
			tabText = null;
			tabDelButton = null;
			tabSaveButton = null;
			tabNotesHeading = null;
			editTabs = null;
			uttPanel = null;
			uttLabels = null;
			uttButtons = null;
			uttButtonsEdit = null;
			domainUttPanel = null;
			domainUttLabels = null;
			domainUttButtons = null;
			domainUttButtonsEdit = null;
			editTabUttList.clear();
			dialogueStructurePanel.clear();
			editTabsPanel.clear();
			recoveryUtterancePanel.clear();
			// reload tabs
			if (mode == 3) {
				getLastDomainDatatID();
			} else {
				loadTabs();
			}
		}
	}

	private void loadExperimentNotes(final int control) {

		String sql = "Select * from experimentnotes where expid = " + expId
				+ " and userid = " + userId + " order by id";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				experimentNotesCollection.clear();
				experimentNotesCollection.add(experimentNotesTextArea);
				if (result != null) {
					notesCount = result.length;
					if (result.length > 0) {
						for (int i = 0; i < result.length; i++) {
							if (experimentNotesCollection.getWidgetCount() > 1) {
								experimentNotesCollection.insert(new HTML(
										result[i][3].substring(11) + ": "
												+ result[i][4]), 1);
							} else {
								experimentNotesCollection.add(new HTML(
										result[i][3].substring(11) + ": "
												+ result[i][4]));
							}

							experimentNotesCollection.getWidget(1)
									.setStyleName("experimentNotesSaved");
						}

					}

				} else {
					notesCount = 0;
				}
				experimentNotesHeading.setText("Notes (" + notesCount + ")");
				experimentNotesTextArea.setStyleName("experimentNotes");

				if (control == 1) {
					loadTabs();
				}

			}

		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void getNotesTime() {

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				saveNotes(result);
			}

		};

		databaseAccessSvc.getTimeStamp(callback);

	}

	private void saveNotes(final String time) {

		note = clearHiven(experimentNotesTextArea.getText());

		String sql = "Insert into experimentnotes (expid, userid, timestamp, note) values ("
				+ expId
				+ ", "
				+ userId
				+ ", \""
				+ time
				+ "\", \""
				+ note
				+ "\");";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				if (experimentNotesCollection.getWidgetCount() > 1) {
					experimentNotesCollection.insert(
							new HTML(time.substring(11) + ": "
									+ experimentNotesTextArea.getText()), 1);
				} else {
					experimentNotesCollection.add(new HTML(time.substring(11)
							+ ": " + experimentNotesTextArea.getText()));
				}
				experimentNotesCollection.getWidget(1).setStyleName(
						"experimentNotesSaved");
				notesCount++;
				experimentNotesHeading.setText("Notes (" + notesCount + ")");
				experimentNotesTextArea.setText("");
				experimentNotesTextArea.setFocus(true);
			}

		};

		databaseAccessSvc.storeData(sql, callback);
	}

	private void loadDomainData() {

		String sql = "Select * from domaindata where expid = " + expId
				+ " order by id";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {

					// remove handler
					if (addDomainUtteranceClickHandlerRegistration != null) {
						for (int i = 0; i < addDomainUtteranceClickHandlerRegistration.length; i++) {
							addDomainUtteranceClickHandlerRegistration[i]
									.removeHandler();
							editDomainUtteranceClickHandlerRegistration[i]
									.removeHandler();
						}
					}

					addDomainUtteranceClickHandler = new ClickHandler[result.length];
					editDomainUtteranceClickHandler = new ClickHandler[result.length];
					addDomainUtteranceClickHandlerRegistration = new HandlerRegistration[result.length];
					editDomainUtteranceClickHandlerRegistration = new HandlerRegistration[result.length];

					addFilterButton.setVisible(true);
					possibleData = new Integer[result.length][2];
					setCountDomainUtt(result.length);
					domainDataTabPanel.getTabBar().setTabText(0,
							"Domain Data: (" + result.length + ")");
					domainUttPanel = new HorizontalPanel[result.length];
					domainUttNoLabels = new Label[result.length];
					domainUttLabels = new Label[result.length];
					domainUttButtons = new Button[result.length];
					domainUttButtonsEdit = new Button[result.length];
					domainDataResponsePanel.clear();
					for (int i = 0; i < result.length; i++) {

						// set visibility of data
						possibleData[i][0] = Integer.parseInt(result[i][0]);
						possibleData[i][1] = 1;
						// create panel, label & button for utterance
						domainUttPanel[i] = new HorizontalPanel();
						domainUttPanel[i].clear();
						domainUttPanel[i].setHeight("30px");
						domainUttLabels[i] = new Label();
						domainUttLabels[i].setText(result[i][5]);
						domainUttLabels[i].setWidth("660px");
						domainUttNoLabels[i] = new Label();
						domainUttNoLabels[i].setText((i + 1) + ")");
						domainUttNoLabels[i].setWidth("20px");

						domainUttButtonsEdit[i] = new Button("Edit");
						domainUttButtonsEdit[i].setStyleName("button");
						addDomainUtteranceEditHandler(domainUttButtonsEdit[i],
								result[i][0], i);
						domainUttButtons[i] = new Button("Send");
						domainUttButtons[i].setStyleName("button");
						// add handler to buttons using the id
						addDomainUtteranceHandler(domainUttButtons[i],
								result[i][0], i);

						domainUttPanel[i].add(domainUttNoLabels[i]);
						domainUttPanel[i].add(domainUttButtons[i]);
						domainUttPanel[i].add(domainUttButtonsEdit[i]);
						domainUttPanel[i].add(domainUttLabels[i]);

						if (reloadMode < 1) {
							domainUttButtonsEdit[i].setVisible(false);
							domainUttPanel[i].setStyleName("utterance");
						} else {
							domainUttButtons[i].setVisible(false);
							domainUttPanel[i].setStyleName("utteranceEdit");
						}

						domainDataResponsePanel.add(domainUttPanel[i]);
					}

					domainDataTabPanel.selectTab(selectedDomainTab);
					loadSlots();

				} else {
					countDomainUtt = 0;
					addFilterButton.setVisible(false);

					// turn on status update
					if (reloadMode < 1) {
						// start statusUpdate
						statusUpdate = true;
					}

					loadFreeTextElements();

				}

			}

		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	public void changeDomainUtt(String sql, final int reload) {

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				reloadLayout(reload);
			}

		};

		databaseAccessSvc.storeData(sql, callback);
	}

	public void setCountDomainUtt(int countDomainUtt) {
		this.countDomainUtt = countDomainUtt;
	}

	public int getCountDomainUtt() {
		return countDomainUtt;
	}

	private void loadSlots() {
		String sql = "Select id, name, count(value), rank from slot where expid = "
				+ expId + " group by name order by rank, id asc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			@SuppressWarnings("deprecation")
			public void onSuccess(String[][] result) {

				if (result != null) {

					// remove handler
					if (slotRadioButtonHandlerRegistration != null) {
						for (int i = 0; i < slotRadioButtonHandlerRegistration.length; i++) {
							for (int j = 0; j < slotRadioButtonHandlerRegistration[i].length; j++) {
								slotRadioButtonHandlerRegistration[i][j]
										.removeHandler();
							}
						}

					}

					// clear handler
					if (editSlotHandlerRegistration != null) {
						for (int i = 0; i < editSlotHandlerRegistration.length; i++) {
							editSlotHandlerRegistration[i].removeHandler();
						}
					}

					editSlotHandler = new ClickHandler[result.length];
					editSlotHandlerRegistration = new HandlerRegistration[result.length];

					slotRadioButtonHandler = new ClickHandler[result.length][];
					slotRadioButtonHandlerRegistration = new HandlerRegistration[result.length][];

					slotsExist = true;
					domainDataTabPanel.getTabBar().setTabEnabled(1, true);
					rankEstimation = (result.length) + 1;
					defaultSlotValue = "Default-" + rankEstimation;
					standardFilterValueTextBox.setText(defaultSlotValue);
					domainDataSlotPanel = new VerticalPanel[result.length];
					domainDataSlotPanelHeading = new HorizontalPanel[result.length];
					domainDataSlotEditButtons = new Button[result.length];
					slotHeadingLabel = new Label[result.length];
					filterList = new ListBox[result.length];
					filterHeadingLabel = new Label[result.length];
					slots = new HorizontalPanel[result.length];
					slotRadioButton = new RadioButton[result.length][];

					domainDataSlotCollectionPanel.clear();
					editDomainFilterPanel.clear();
					for (int i = 0; i < result.length; i++) {

						// filter
						filterHeadingLabel[i] = new Label();
						filterHeadingLabel[i].setText(result[i][1]);
						filterList[i] = new ListBox();
						editDomainFilterPanel.add(filterHeadingLabel[i]);
						editDomainFilterPanel.add(filterList[i]);

						domainDataSlotPanel[i] = new VerticalPanel();
						domainDataSlotPanel[i].clear();
						domainDataSlotCollectionPanel
								.add(domainDataSlotPanel[i]);
						slotHeadingLabel[i] = new Label();
						slotHeadingLabel[i].setText(result[i][1]);

						domainDataSlotPanelHeading[i] = new HorizontalPanel();
						domainDataSlotPanelHeading[i].clear();
						domainDataSlotPanelHeading[i].add(slotHeadingLabel[i]);
						domainDataSlotEditButtons[i] = new Button("Edit");
						domainDataSlotEditButtons[i]
								.setStyleName("buttonSlotEdit");
						addSlotEditHandler(domainDataSlotEditButtons[i], i);
						domainDataSlotPanelHeading[i]
								.add(domainDataSlotEditButtons[i]);
						if (reloadMode < 1) {
							domainDataSlotEditButtons[i].setVisible(false);
						} else {
							domainDataSlotEditButtons[i].setVisible(true);
						}

						domainDataSlotPanel[i]
								.add(domainDataSlotPanelHeading[i]);
						domainDataSlotPanel[i]
								.setStyleName("domainDataSlotPanel");
						slots[i] = new HorizontalPanel();
						slots[i].clear();
						// Amount of radio buttons + 1 for disabling the filter
						slotRadioButton[i] = new RadioButton[Integer
								.parseInt(result[i][2]) + 1];
						// handler
						slotRadioButtonHandler[i] = new ClickHandler[Integer
								.parseInt(result[i][2]) + 1];
						slotRadioButtonHandlerRegistration[i] = new HandlerRegistration[Integer
								.parseInt(result[i][2]) + 1];

						// add the first radio button to disable filter
						slotRadioButton[i][0] = new RadioButton(
								slotHeadingLabel[i].getText());
						slotRadioButton[i][0].setChecked(true);
						slots[i].add(slotRadioButton[i][0]);
						// add the rest of the radio buttons for this slot
						for (int j = 1; j < Integer.parseInt(result[i][2]) + 1; j++) {
							slotRadioButton[i][j] = new RadioButton(
									slotHeadingLabel[i].getText());
							slots[i].add(slotRadioButton[i][j]);
						}
						domainDataSlotPanel[i].add(slots[i]);
						slotHeadingLabel[i].setStyleName("slotHeading");
						slots[i].setStyleName("slot");
					}

					loadSlotLabels();
				} else {
					slotsExist = false;
					domainDataTabPanel.getTabBar().setTabEnabled(1, false);
					domainDataTabPanel.selectTab(0);
					loadFreeTextElements();
				}
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void reloadDomainData() {
		String s[] = new String[domainDataSort.length];
		String c[] = new String[domainDataSort.length];
		int countData = 0;

		// turn all records to visible before filtering them
		for (int k = 0; k < possibleData.length; k++) {
			possibleData[k][1] = 1;
		}

		for (int i = 0; i < domainDataSort.length; i++) {
			s[i] = "";
			c[i] = "";
			for (int j = 0; j < selectedSlots.length; j++) {

				if (selectedSlots[j] != 0) {

					s[i] = s[i] + selectedSlots[j];
					c[i] = c[i] + domainDataSort[i][j + 1];

					// Then String comparison to find which to turn on and which
					// to turn off
					System.out.print(s[i] + " | ");
					System.out.println(c[i]);

					if (s[i].equals(c[i])) {
						for (int k = 0; k < possibleData.length; k++) {
							if (possibleData[k][0].byteValue() == domainDataSort[i][0]
									.byteValue()) {
								possibleData[k][1] = 1;
							}

						}
					} else {
						for (int k = 0; k < possibleData.length; k++) {
							System.out.println(possibleData[k][0] + " | "
									+ domainDataSort[i][0]);
							if (possibleData[k][0].byteValue() == domainDataSort[i][0]
									.byteValue()) {
								possibleData[k][1] = 0;
							}
						}
					}

				}

			}

		}

		// change data visibility
		int z = 1;
		for (int x = 0; x < possibleData.length; x++) {

			System.out.println(possibleData[x][0] + ": " + possibleData[x][1]);

			if (possibleData[x][1] == 0) {
				domainUttPanel[x].setVisible(false);
			} else {
				domainUttNoLabels[x].setText(z + ")");
				domainUttPanel[x].setVisible(true);
				z++;
			}
		}

		// get amount of possible data
		for (int x = 0; x < possibleData.length; x++) {
			if (possibleData[x][1] == 1) {
				countData++;

			}
		}

		domainDataTabPanel.getTabBar().setTabText(0,
				"Domain Data: (" + countData + ")");

	}

	private void loadSlotLabels() {
		String sql = "Select * from slot where expid = " + expId
				+ " order by rank, id asc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {
					int k = 0;
					for (int i = 0; i < slotRadioButton.length; i++) {
						// add the heading for 'Filter off'
						slotRadioButton[i][0].setHTML("OFF");
						addRadioButtonValueChangeHandler(slotRadioButton[i][0],
								0, i, 0);
						// add the rest of the headings
						for (int j = 1; j < slotRadioButton[i].length; j++) {
							slotRadioButton[i][j].setHTML(result[k][3]);
							addRadioButtonValueChangeHandler(
									slotRadioButton[i][j],
									Integer.parseInt(result[k][0]), i, j);
							filterList[i].addItem(result[k][3], result[k][0]);
							k++;
						}
					}
				}

				getDomainDataSort();

			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void addRadioButtonValueChangeHandler(RadioButton rb, final int id,
			final int i, final int j) {
		slotRadioButtonHandler[i][j] = new ClickHandler() {
			public void onClick(ClickEvent event) {
				selectedSlots[i] = id;
				reloadDomainData();
			}
		};
		slotRadioButtonHandlerRegistration[i][j] = rb
				.addClickHandler(slotRadioButtonHandler[i][j]);
	}

	private void getDomainDataSort() {
		String sql = " SELECT * FROM (domaindataslot INNER JOIN slot ON domaindataslot.slotid = slot.id) inner JOIN domaindata WHERE domaindataslot.dataid = domaindata.id AND domaindata.expid = "
				+ expId
				+ " order by domaindataslot.dataid, slot.rank, domaindataslot.id, domaindataslot.slotid asc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {
					// define slot tracking and set all values to 0
					selectedSlots = new Integer[slots.length];
					for (int i = 0; i < selectedSlots.length; i++) {
						selectedSlots[i] = 0;
					}

					domainDataSort = new Integer[result.length / slots.length][slots.length + 1];
					int k = 0;
					for (int i = 0; i < domainDataSort.length; i++) {

						for (int j = 0; j < domainDataSort[i].length; j++) {
							if (j == 0) {
								domainDataSort[i][j] = Integer
										.parseInt(result[k][0]);
							} else {
								domainDataSort[i][j] = Integer
										.parseInt(result[k][1]);
								k++;
							}

						}
					}

				}

				loadFreeTextElements();

			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	public void setEditId(int editId) {
		this.editId = editId;
	}

	public int getEditId() {
		return editId;
	}

	private static class EditUtterancesPopup extends PopupPanel {

		public EditUtterancesPopup() {
			// Set the dialog box's caption.
			setWidth("810px");
			setHeight("500px");
			// Enable animation.
			setAnimationEnabled(true);
			setGlassEnabled(true);
			setWidget(editPanel);
		}
	}

	private static class EditPreparedFreeTextPopup extends PopupPanel {

		public EditPreparedFreeTextPopup() {
			// Set the dialog box's caption.
			setWidth("400px");
			setHeight("200px");
			// Enable animation.
			setAnimationEnabled(true);
			setGlassEnabled(true);
			setWidget(editFreeTextPanel);
		}

	}

	private void printReport() {
		String sql = "select * from output where experiment = " + expId
				+ " and user = " + userId
				+ " and sign in (2, 3, 4) order by id";

		// System.out.println(sql);

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(final String[][] result) {

				// heading
				reportHeadingHtml = "<table>";
				reportHeadingHtml = reportHeadingHtml + "<tr>";
				reportHeadingHtml = reportHeadingHtml
						+ "<th width=\"80px\">Timestamp</th>";
				reportHeadingHtml = reportHeadingHtml
						+ "<th width=\"150px\">Sent</th>";
				reportHeadingHtml = reportHeadingHtml
						+ "<th width=\"150px\">Original</th>";
				reportHeadingHtml = reportHeadingHtml
						+ "<th width=\"80px\" align=\"center\">Translation Text Flag</th>";
				reportHeadingHtml = reportHeadingHtml
						+ "<th width=\"80px\" align=\"center\">Original Text Flag</th>";
				reportHeadingHtml = reportHeadingHtml
						+ "<th width=\"80px\" align=\"center\">Translation Audio Flag</th>";
				reportHeadingHtml = reportHeadingHtml
						+ "<th width=\"80px\" align=\"center\">Original Audio Flag</th>";
				reportHeadingHtml = reportHeadingHtml
						+ "<th width=\"80px\" align=\"center\">Translation MM Flag</th>";
				reportHeadingHtml = reportHeadingHtml
						+ "<th width=\"80px\" align=\"center\">Original MM Flag</th>";
				reportHeadingHtml = reportHeadingHtml + "</tr>";
				reportHeadingHtml = reportHeadingHtml + "</table>";

				if (result != null) {

					// String record;
					reportHtml = "<table>";

					for (int i = 0; i < result.length; i++) {

						// different color for every second record
						if (i % 2 == 0) {
							reportHtml = reportHtml
									+ "<tr style=\"background-color:#ADD8E6\">";
						} else {
							reportHtml = reportHtml
									+ "<tr style=\"background-color:#C0C0C0\">";
						}
						reportHtml = reportHtml
								+ "<td width=\"80px\" align=\"left\">"
								+ result[i][8] + "</td>";
						reportHtml = reportHtml + "<td width=\"150px\">"
								+ result[i][4] + "</td>";
						reportHtml = reportHtml + "<td width=\"150px\">"
								+ result[i][1] + "</td>";
						reportHtml = reportHtml
								+ "<td width=\"80px\" align=\"center\">"
								+ result[i][10] + "</td>";
						reportHtml = reportHtml
								+ "<td width=\"80px\" align=\"center\">"
								+ result[i][11] + "</td>";
						reportHtml = reportHtml
								+ "<td width=\"80px\" align=\"center\">"
								+ result[i][12] + "</td>";
						reportHtml = reportHtml
								+ "<td width=\"80px\" align=\"center\">"
								+ result[i][13] + "</td>";
						reportHtml = reportHtml
								+ "<td width=\"80px\" align=\"center\">"
								+ result[i][14] + "</td>";
						reportHtml = reportHtml
								+ "<td width=\"80px\" align=\"center\">"
								+ result[i][15] + "</td>";
						reportHtml = reportHtml + "</tr>";
					}
					reportHtml = reportHtml + "</table>";
				} else {
					reportHtml = "";
				}
				reportHeadingTable.setHTML(reportHeadingHtml);
				reportTable.setHTML(reportHtml);
				printReportPopup.show();
				printReportPopup.center();
			}

		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private static class EditTabsPopup extends PopupPanel {

		public EditTabsPopup() {
			// Set the dialog box's caption.
			setWidth("530px");
			setHeight("400px");
			// Enable animation.
			setAnimationEnabled(true);
			setGlassEnabled(true);
			setWidget(editTabsPanel);
		}
	}

	private static class PrintReportPopup extends PopupPanel {
		public PrintReportPopup() {
			// Set the dialog box's caption.
			setWidth("900px");
			setHeight("500px");
			// Enable animation.
			setAnimationEnabled(true);
			setGlassEnabled(true);
			setWidget(reportPanel);
		}
	}

	private static class EditDomainDataPopUp extends PopupPanel {
		public EditDomainDataPopUp() {
			// Set the dialog box's caption.
			setWidth("810px");
			setHeight("500px");
			// Enable animation.
			setAnimationEnabled(true);
			setGlassEnabled(true);
			setWidget(editDomainDataPanel);
		}
	}

	private static class EditSlotPopUp extends PopupPanel {
		public EditSlotPopUp() {
			// Set the dialog box's caption.
			setWidth("580px");
			setHeight("200px");
			// Enable animation.
			setAnimationEnabled(true);
			setGlassEnabled(true);
			setWidget(editSlotPopUpPanel);
		}
	}

	private static class AddSlotPopUp extends PopupPanel {
		public AddSlotPopUp() {
			// Set the dialog box's caption.
			setWidth("580px");
			setHeight("200px");
			// Enable animation.
			setAnimationEnabled(true);
			setGlassEnabled(true);
			setWidget(addSlotPopUpPanel);
		}
	}

	private void openPrintView() {
		Window.open("/webwozwizard/webwozwizard/experimentReport?p1=user&p2="
				+ expId + "&p3=" + userId, "", "");
	}

	private void openExport() {
		Window.open("/webwozwizard/webwozwizard/excelExport?p1=user&p2="
				+ expId + "&p3=" + userId, "", "");
	}

	private void exportNotes() {
		Window.open("/webwozwizard/webwozwizard/notesExport?p1=" + expId
				+ "&p2=" + userId, "", "");
	}

	private void getLastDomainDatatID() {
		String sql = "select id from domaindata where expid = " + expId
				+ " order by id desc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(final String[][] result) {
				if (filterList != null) {
					String sql = "";
					for (int i = 0; i < filterList.length; i++) {
						sql = sql
								+ "Insert into domaindataslot (dataid, slotid) values ("
								+ result[0][0]
								+ ", "
								+ filterList[i].getValue(filterList[i]
										.getSelectedIndex()) + "); ";
					}

					insertFilterSettings(sql);
				} else {
					loadTabs();
					editDomainDataPopup.hide();
				}
			}

		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void insertFilterSettings(String sql) {

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				loadTabs();
				editDomainDataPopup.hide();
			}

		};

		databaseAccessSvc.storeData(sql, callback);
	}

	private void getFilterRank() {
		String sql = "Select rank from slot where name = '" + editSlot
				+ "' and expid = " + expId;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				editSlotRank = Integer.parseInt(result[0][0]);

				newValue = clearHiven(editFilterAddSlotTextBox.getText());

				String sql = "insert into slot (expid, name, value, type, rank) values ("
						+ expId
						+ ", '"
						+ editSlot
						+ "', '"
						+ newValue
						+ "', "
						+ 1 + ", " + editSlotRank + ")";

				changeUtt(sql, 5);

			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void reloadFilter() {

		filterList[editSlotId].addItem(editFilterAddSlotTextBox.getText());
		editFilterSlotsPanel = null;
		editFilterSlotsPanel = new HorizontalPanel[filterList[editSlotId]
				.getItemCount()];
		editFilterSlotValuesTextBox = null;
		editFilterSlotValuesTextBox = new TextBox[filterList[editSlotId]
				.getItemCount()];
		editFilterChangeSlotButton = null;
		editFilterChangeSlotButton = new Button[filterList[editSlotId]
				.getItemCount()];
		editFilterDeletSlotButton = null;
		editFilterDeletSlotButton = new Button[filterList[editSlotId]
				.getItemCount()];

		editFilterSlotCollectionPanel.clear();

		for (int i = 0; i < filterList[editSlotId].getItemCount(); i++) {
			editFilterSlotValuesTextBox[i] = new TextBox();
			editFilterSlotValuesTextBox[i].setText(filterList[editSlotId]
					.getItemText(i));
			editFilterChangeSlotButton[i] = new Button("Save Changes ");
			editFilterChangeSlotButton[i].setStyleName("button");
			addChangeSlotValueHandler(editFilterChangeSlotButton[i], i,
					editSlotId);

			editFilterDeletSlotButton[i] = new Button("Delete Filter Value");
			editFilterDeletSlotButton[i].setStyleName("button");
			addDeleteSlotValueHandler(editFilterDeletSlotButton[i], i,
					editSlotId);

			editFilterSlotsPanel[i] = new HorizontalPanel();
			editFilterSlotsPanel[i].add(editFilterSlotValuesTextBox[i]);
			editFilterSlotsPanel[i].add(editFilterChangeSlotButton[i]);
			editFilterSlotsPanel[i].add(editFilterDeletSlotButton[i]);
			editFilterSlotCollectionPanel.add(editFilterSlotsPanel[i]);
			editFilterSlotCollectionPanel.setStyleName("editFilterSlotsPanel");
		}

		editFilterAddSlotTextBox.setText("");

	}

	private void getLastFilter() {
		String sql = "Select id from slot where expid = " + expId
				+ " order by id desc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				String sql2 = "";
				for (int i = 0; i < possibleData.length; i++) {
					sql2 = sql2
							+ "Insert into domaindataslot (dataid, slotid) values ("
							+ possibleData[i][0] + ", " + result[0][0] + "); ";
				}

				changeUtt(sql2, 7);
			}

		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void addFilterSlot() {
		int id = addFilterSlotCollection.getWidgetCount();
		addFilterSlotCollection.insert(new HorizontalPanel(), id - 1);
		getFilterSlotPanel((HorizontalPanel) addFilterSlotCollection
				.getWidget(id - 1));
	}

	private void getFilterSlotPanel(HorizontalPanel p) {
		p.setStyleName("addFilterValue");
		p.add(new Label(addFilterValueTextBox.getText()));
		p.getWidget(0).setWidth("90px");
		p.add(new Button("Delete"));
		addButtonHandler((Button) p.getWidget(1));

	}

	private void addButtonHandler(final Button b) {
		b.setStyleName("button");
		b.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addFilterSlotCollection.remove((HorizontalPanel) b.getParent());
			}
		});
	}

	public void stopReload() {
		refreshTimer.cancel();
		statusUpdate = false;
		// flush popups
		editUtterancesPopup = null;
		editDomainDataPopup = null;
		editTabsPopup = null;
		printReportPopup = null;
		editSlotPopUp = null;
		addSlotPopUp = null;
		editFreeTextPopUp = null;
	}

	public void turnOffComponent() {

	}

	private String clearHiven(String source) {
		source = source.replaceAll("\"", "");
		return source.replaceAll("'", " ");
	}

	private void loadFreeTextElements() {

		String sql = "Select * from freetext where expid = " + expId
				+ " order by id";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				freeTextElementsCollectionPanel.clear();
				if (result != null) {

					if (addFreeTextHandlerRegistration != null) {
						for (int i = 0; i < addFreeTextHandlerRegistration.length; i++) {
							addFreeTextHandlerRegistration[i].removeHandler();
							editFreeTextHandlerRegistration[i].removeHandler();
						}
					}

					setCountFreeTextElements(result.length);

					addFreeTextHandler = new ClickHandler[result.length];
					editFreeTextHandler = new ClickHandler[result.length];
					addFreeTextHandlerRegistration = new HandlerRegistration[result.length];
					editFreeTextHandlerRegistration = new HandlerRegistration[result.length];

					domainDataFreeTextPanels = new HorizontalPanel[result.length];
					domainDataFreeTextLabels = new Label[result.length];
					domainDataFreeTextButtons = new Button[result.length];
					domainDataFreeTextButtonsEdit = new Button[result.length];

					for (int i = 0; i < result.length; i++) {

						// create panel, label & button for free text
						domainDataFreeTextPanels[i] = new HorizontalPanel();
						domainDataFreeTextPanels[i].clear();
						domainDataFreeTextPanels[i].setHeight("30px");
						domainDataFreeTextLabels[i] = new Label();
						domainDataFreeTextLabels[i].setText(result[i][3]);
						domainDataFreeTextLabels[i].setWidth("600px");

						domainDataFreeTextButtonsEdit[i] = new Button("Edit");
						domainDataFreeTextButtonsEdit[i].setStyleName("button");
						addFreeTextEditHandler(
								domainDataFreeTextButtonsEdit[i], result[i][0],
								result[i][3], result[i][2], i);
						domainDataFreeTextButtons[i] = new Button(
								"Add to Free Text");
						domainDataFreeTextButtons[i].setStyleName("button");
						addFreeTextAddHandler(domainDataFreeTextButtons[i], i);

						domainDataFreeTextPanels[i]
								.add(domainDataFreeTextButtons[i]);
						domainDataFreeTextPanels[i]
								.add(domainDataFreeTextButtonsEdit[i]);
						domainDataFreeTextPanels[i]
								.add(domainDataFreeTextLabels[i]);

						if (reloadMode < 1) {
							domainDataFreeTextButtonsEdit[i].setVisible(false);
							domainDataFreeTextPanels[i]
									.setStyleName("utterance");
						} else {
							domainDataFreeTextButtons[i].setVisible(false);
							domainDataFreeTextPanels[i]
									.setStyleName("utteranceEdit");
						}

						freeTextElementsCollectionPanel
								.add(domainDataFreeTextPanels[i]);
					}

					// turn freeText to editMode
					if (reloadFreeText) {
						for (int i = 0; i < domainDataFreeTextPanels.length; i++) {
							domainDataFreeTextPanels[i]
									.setStyleName("utteranceEdit");
							domainDataFreeTextPanels[i].getWidget(0)
									.setVisible(false);
							domainDataFreeTextPanels[i].getWidget(1)
									.setVisible(true);
						}
					}

				} else {
					setCountFreeTextElements(0);
				}

				changeVisibility();

			}

		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void getTimeStamp() {

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				startProcessing(result);
			}

		};

		databaseAccessSvc.getTimeStamp(callback);
	}

	public void setCountFreeTextElements(int countFreeTextElements) {
		this.countFreeTextElements = countFreeTextElements;
	}

	public int getCountFreeTextElements() {
		return countFreeTextElements;
	}

	public void changeVisibility() {
		String sql = "Select nbestlist, wizardcorrection, freetext from experiment where id = "
				+ expId;

		// clear content of n-Best list and wizard correction
		wizardCorrectionPanel.clear();
		nBestListPanel.clear();
		inputElementVectorHorPanels.clear();
		inputButtonVectorButtons.clear();
		inputTextAreaVectorTextAreas.clear();
		inputNBestListVectorVerPanels.clear();
		inputNBestListVectorHorPanels.clear();
		inputNBestListVectorButtons.clear();
		inputNBestListVectorLabels.clear();

		statusUpdate = false;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				if (Integer.parseInt(result[0][0]) == 1) {
					nBestList = true;
				} else {
					nBestList = false;
				}

				if (Integer.parseInt(result[0][1]) == 1) {
					wizardCorrection = true;
				} else {
					wizardCorrection = false;
				}

				if (Integer.parseInt(result[0][2]) == 1) {
					freeText = true;
				} else {
					freeText = false;
				}

				if (reloadMode < 1) {
					// start statusUpdate
					statusUpdate = true;
				}

				checkTabVisibility();

			}

		};

		databaseAccessSvc.retrieveData(sql, callback);

	}

	private void checkTabVisibility() {

		// start with domainDataPanel visible false
		domainPanelVisible = false;

		// domain data
		if (countDomainUtt > 0) {
			domainDataTabPanel.getTabBar().setTabEnabled(0, true);
			domainPanelVisible = true;
			domainDataTabPanel.selectTab(0);

		} else {
			domainDataTabPanel.getTabBar().setTabEnabled(0, false);
			domainDataTabPanel.getTabBar().setTabEnabled(1, false);
			domainDataTabPanel.getTabBar().setTabText(0, "Domain Data");
		}

		if (slotsExist) {
			domainDataTabPanel.getTabBar().setTabEnabled(1, true);
			domainPanelVisible = true;
		} else {
			domainDataTabPanel.getTabBar().setTabEnabled(1, false);
		}

		// free text
		if (freeText) {
			domainDataTabPanel.getTabBar().setTabEnabled(2, true);
			domainPanelVisible = true;
			domainDataTabPanel.selectTab(2);
			// turn on to free text buttons
			for (int i = 0; i < countUtt; i++) {
				uttPanel[i].getWidget(2).setVisible(true);
			}

		} else {
			domainDataTabPanel.getTabBar().setTabEnabled(2, false);
			// turn off to free text buttons
			for (int i = 0; i < countUtt; i++) {
				uttPanel[i].getWidget(2).setVisible(false);
			}

		}

		// domainDataPanel Visibility
		if (domainPanelVisible) {
			domainDataTabPanel.setVisible(true);
		} else {
			domainDataTabPanel.setVisible(false);
		}

		// wizard Correction
		if (wizardCorrection) {
			dialogueStructurePanel.getTabBar().setTabEnabled(countTab, true);
		} else {
			dialogueStructurePanel.getTabBar().setTabEnabled(countTab, false);
		}

		// n-best List
		if (nBestList) {
			dialogueStructurePanel.getTabBar()
					.setTabEnabled(countTab + 1, true);
		} else {
			dialogueStructurePanel.getTabBar().setTabEnabled(countTab + 1,
					false);
		}

		// change the selected tab if reload
		if (selectedDomainTab != 100) {
			domainDataTabPanel.selectTab(selectedDomainTab);
		}

		if (selectedTab != 0) {
			dialogueStructurePanel.selectTab(selectedTab);
		}

	}

	private void endExperiment() {
		// time stamp
		@SuppressWarnings("deprecation")
		String time = DateTimeFormat.getMediumDateTimeFormat().format(
				new Date());

		String sql = "Insert into output (textorig, audioorig, mmorig, texttrans, audiotrans, mmtrans, experiment, timestamp, played, sign, user, sender, receiver) values ('Stop Session (Wizard)', '-', '-', 'Stop Session (Wizard)', '-', '-', "
				+ expId
				+ ", '"
				+ time
				+ "', 1, 0, "
				+ userId
				+ " , "
				+ userId
				+ ", " + wizId + ")";

		System.out.println(sql);

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

	private void updateRank(String sql) {

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				reloadLayout(1);
			}

		};

		databaseAccessSvc.storeData(sql, callback);

	}
}
