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

public class Utterance {

	private int experiment;
	private String semKey;
	private int section;
	private int rank;
	private String text;
	private String textAudioFile;
	private String textMMFile;
	private String translation;
	private String translationAudioFile;
	private String translationMMFile;
	private String translationSrcLang;
	private String translationTrgLang;
	private int translEngine;
	private int speechEnging;
	private int recEngine;

	public void setExperiment(int experiment) {
		this.experiment = experiment;
	}

	public int getExperiment() {
		return experiment;
	}

	public void setSemKey(String semKey) {
		this.semKey = semKey;
	}

	public String getSemKey() {
		return semKey;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getRank() {
		return rank;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public int getSection() {
		return section;
	}

	public void setTextFile(String textFile) {
		this.textAudioFile = textFile;
	}

	public String getTextFile() {
		return textAudioFile;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslationFile(String translationFile) {
		this.translationAudioFile = translationFile;
	}

	public String getTranslationFile() {
		return translationAudioFile;
	}

	public void setTranslationSrcLang(String translationSrcLang) {
		this.translationSrcLang = translationSrcLang;
	}

	public String getTranslationSrcLang() {
		return translationSrcLang;
	}

	public void setTranslationTrgLang(String translationTrgLang) {
		this.translationTrgLang = translationTrgLang;
	}

	public String getTranslationTrgLang() {
		return translationTrgLang;
	}

	public void setSpeechEnging(int speechEnging) {
		this.speechEnging = speechEnging;
	}

	public int getSpeechEnging() {
		return speechEnging;
	}

	public void setTranslEngine(int translEngine) {
		this.translEngine = translEngine;
	}

	public int getTranslEngine() {
		return translEngine;
	}

	public void setRecEngine(int recEngine) {
		this.recEngine = recEngine;
	}

	public int getRecEngine() {
		return recEngine;
	}

	public void setTranslationMMFile(String translationMMFile) {
		this.translationMMFile = translationMMFile;
	}

	public String getTranslationMMFile() {
		return translationMMFile;
	}

	public void setTextMMFile(String textMMFile) {
		this.textMMFile = textMMFile;
	}

	public String getTextMMFile() {
		return textMMFile;
	}

}
