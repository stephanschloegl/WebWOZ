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

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webwoz.wizard.client.ComponentFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class ComponentFactoryImpl extends RemoteServiceServlet implements
		ComponentFactory {

	// ArrayList to log activated components
	private ArrayList<Component> components = new ArrayList<Component>();
	private int id;
	private int userID;
	private int wizardRank;
	private Database db = new Database();
	private Utterance utt = new Utterance();
	private String origAudioFile;
	private String origMMFile;
	private String transAudioFile;
	private String transMMFile;

	// Variables
	private String path;
	private String ssLanguage;
	private String mtSrcLang;
	private String mtTrgLang;
	private String mtSrcLangOut;
	private String mtTrgLangOut;
	private String asrLanguage;
	private int wizard;
	private Boolean translated = false;
	private String outputText;
	private String outputTranslation;
	private boolean recordedSpeech;

	public void pushComponents(int[][] type, int expid, String mediapath,
			String sslang, String mtSrc, String mtTrg, String mtoSrc,
			String mtoTrg, String asrlang, int wiz) {

		path = mediapath;
		mtSrcLang = mtSrc;
		mtTrgLang = mtTrg;
		mtSrcLangOut = mtoSrc;
		mtTrgLangOut = mtoTrg;
		asrLanguage = asrlang;

		setId(expid);
		utt.setExperiment(expid);
		ssLanguage = sslang;
		wizard = wiz;

		// clear existing components
		if (components != null) {
			components.clear();
		}

		for (int i = 0; i < type.length; i++) {

			switch (type[i][0]) {
			case 0:
				// wizard
				setWizardRank(components.size());
				components.add(new WizardComp().getComponent(type[i][1],
						type[i][2]));
				break;
			case 1:
				// ASR
				components.add(new ASRComp().getComponent(type[i][1],
						type[i][2]));
				break;
			case 2:
				// MT in
				components.add(new MTinComp().getComponent(type[i][1],
						type[i][2]));
				break;
			case 3:
				// NLU
				components.add(new NLUComp().getComponent(type[i][1],
						type[i][2]));
				break;
			case 4:
				// NLG
				components.add(new NLGComp().getComponent(type[i][1],
						type[i][2]));
				break;
			case 5:
				// MT out
				components.add(new MToutComp().getComponent(type[i][1],
						type[i][2]));
				if (type[i][1] > 0) {
					translated = true;
				} else {
					translated = false;
				}
				break;
			case 6:
				// SS
				if (type[i][1] == 1) {
					setRecordedSpeech(true);
				} else {
					setRecordedSpeech(false);
				}

				components.add(new SSComp()
						.getComponent(type[i][1], type[i][2]));
				break;
			default:
				break;
			}

		}

	}

	public void clearComponents() {
		// empty array list if it is not already empty
		if (components.size() > 0) {
			components.clear();
		}

	}

	public void printComponents() {

		System.out.println("Experiment: " + id);

		for (int i = 0; i < components.size(); i++) {
			System.out.println(components.get(i).printType() + ": "
					+ components.get(i).getName() + ", Mode: "
					+ components.get(i).getMode());
		}
	}

	// generate the appropriate output based on the components selected
	public String pushOutput(String output, int user, String ctrl) {

		String translation = "";
		userID = user;
		String control = ctrl + "" + ssLanguage + "" + mtSrcLang + ""
				+ mtTrgLang + "" + mtSrcLangOut + "" + mtTrgLangOut + ""
				+ asrLanguage + "" + path + "" + id + "/";

		String sql = "";
		String[][] data;

		// get text in case it is prepared
		switch (Integer.parseInt(ctrl)) {

		case 1:
			sql = sql + "Select origtext from recording where id = " + output
					+ "";
			data = db.retrieveData(sql);
			utt.setText(data[0][0]);
			break;
		case 2:
			sql = sql + "Select origtext from domaindata where id = " + output
					+ "";
			data = db.retrieveData(sql);
			utt.setText(data[0][0]);
			break;
		default:
			utt.setText(output);
		}

		// push output to components
		for (int i = 3; i < components.size(); i++) {

			try {

				switch (components.get(i).getType()) {

				case 3: // NLU
					break;

				case 4: // NLG
					break;

				case 5: // MT out
					// get the translation
					translation = (components.get(i).getResult(utt.getText(),
							control));
					utt.setTranslation(translation);
					break;

				case 6: // SS

					if (!recordedSpeech) {
						utt.setTextFile(components.get(i).getResult(
								utt.getText(), control));
						utt.setTextMMFile(components.get(i).getUttMM());
						// run second time for translation
						components.get(i).getResult(utt.getTranslation(),
								control);
						utt.setTranslationFile(components.get(i)
								.getUttTransAudio());
						utt.setTranslationMMFile(components.get(i)
								.getUttTransMM());
					} else {
						utt.setTextFile(components.get(i).getResult(output,
								control));
						utt.setTextMMFile(components.get(i).getUttMM());
						utt.setTranslationFile(components.get(i)
								.getUttTransAudio());
						utt.setTranslationMMFile(components.get(i)
								.getUttTransMM());
						utt.setTranslation(components.get(i).getUttTransText());

					}

					// with gwt 2.2 Audio and Video is natively supported
					origAudioFile = utt.getTextFile();
					origMMFile = utt.getTextMMFile();

					// build links translation
					// with gwt 2.2 Audio and Video is natively supported
					transAudioFile = utt.getTranslationFile();
					transMMFile = utt.getTranslationMMFile();

					storeUtterance();
					break;

				default:
					break;
				}
			} catch (Exception ex) {
				System.out.println("Exception: " + ex);
			}

		}

		printUtterance();

		if (translated) {
			return utt.getTranslation();
		} else {
			return utt.getText();
		}

	}

	private void printUtterance() {
		System.out.println(utt.getText());
		System.out.println(utt.getTextFile());
		System.out.println(utt.getTextMMFile());
		System.out.println(utt.getTranslation());
		System.out.println(utt.getTranslationFile());
		System.out.println(utt.getTranslationMMFile());
	}

	public Vector<String> getInput(String input, String uttId, String ctrl) {
		// define user
		String control = ctrl + "" + ssLanguage + "" + mtSrcLang + ""
				+ mtTrgLang + "" + mtSrcLangOut + "" + mtTrgLangOut + ""
				+ asrLanguage + "" + path + "" + id + "/";

		Vector<String> list;
		Vector<String> translatedList = new Vector<String>();
		list = produceNBestList(input);

		// push output to components
		for (int i = 1; i < 4; i++) {

			switch (components.get(i).getType()) {

			case 1: // ASR
				break;

			case 2: // MT in

				for (int j = 0; j < list.size(); j++) {
					translatedList.add(components.get(i).getResult(list.get(j),
							control));
				}

				// channge Vector
				list = translatedList;
				break;

			case 3: // NLU
				break;

			default:
				break;
			}

		}

		return updateUtterance(uttId, list);
	}

	public void storeUtterance() {

		// clear output from " and '
		outputText = utt.getText().replaceAll("\"", "");
		outputText = outputText.replaceAll("'", " ");

		origAudioFile = origAudioFile.replaceAll("\"", "");
		origAudioFile = origAudioFile.replaceAll("'", " ");

		origMMFile = origMMFile.replaceAll("\"", "");
		origMMFile = origMMFile.replaceAll("'", " ");

		outputTranslation = utt.getTranslation().replaceAll("\"", "");
		outputTranslation = outputTranslation.replaceAll("'", " ");

		transAudioFile = transAudioFile.replaceAll("\"", "");
		transAudioFile = transAudioFile.replaceAll("'", " ");

		transMMFile = transMMFile.replaceAll("\"", "");
		transMMFile = transMMFile.replaceAll("'", " ");

		// update all to played incl. PROCESSING
		String sql = "Update output set played = 1 where experiment = " + id
				+ "; ";

		sql = sql
				+ "Insert into output (textorig, audioorig, mmorig, texttrans, audiotrans, mmtrans, timestamp, experiment, user, sign, sender, receiver) values (\""
				+ outputText + "\", '" + origAudioFile + "', '" + origMMFile
				+ "', \"" + outputTranslation + "\", '" + transAudioFile
				+ "', '" + transMMFile + "', \"" + getDateTime() + "\", " + id
				+ ", " + userID + ", 3, " + wizard + ", " + userID + ")";

		// store utterance
		db.storeData(sql);
	}

	public Vector<String> updateUtterance(String eid, Vector<String> trans) {

		String t = "";
		t = trans.get(0) + " (";
		for (int i = 1; i < trans.size() - 1; i++) {
			t = t + trans.get(i) + ", ";
		}
		t = t + trans.get(trans.size() - 1) + ")";

		String sql = "Update output set texttrans = \"" + t
				+ "\", asr = 0, played = 1 where id = " + eid;
		// udate utterance
		db.storeData(sql);
		return trans;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setWizardRank(int wizardRank) {
		this.wizardRank = wizardRank;
	}

	public int getWizardRank() {
		return wizardRank;
	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void setRecordedSpeech(boolean recordedSpeech) {
		this.recordedSpeech = recordedSpeech;
	}

	public boolean isRecordedSpeech() {
		return recordedSpeech;
	}

	private Vector<String> produceNBestList(String input) {

		Vector<Integer> index = new Vector<Integer>();
		Vector<String> list = new Vector<String>();

		// find ;
		for (int i = 0; i < input.length(); i++) {
			if (input.substring(i, i + 1).equals(";")) {
				index.add(i);
			}
		}

		// check if there is at least one ;
		if (index.size() != 0) {

			list.add(input.substring(0, index.get(0)));

			for (int j = 0; j < index.size() - 1; j++) {
				list.add(input.substring(index.get(j) + 1, index.get(j + 1)));
			}

			// get the last one
			if (index.size() > 1) {
				list.add(input.substring(index.get(index.size() - 1) + 1,
						input.length()));
			}

		} else {

			// return the whole String - no n-Best list
			list.add(input);
		}

		return list;

	}

}
