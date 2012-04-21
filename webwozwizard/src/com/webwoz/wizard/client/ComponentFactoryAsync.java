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

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ComponentFactoryAsync {

	void clearComponents(AsyncCallback<Void> callback);

	void pushComponents(int[][] components, int expid, String path,
			String ssLang, String mtsrc, String mttrg, String mtosrc,
			String mtotrg, String asrl, int wiz, AsyncCallback<Void> callback);

	void pushOutput(String output, int user, String mode,
			AsyncCallback<String> callback);

	void getInput(String input, String uttid, String mode,
			AsyncCallback<Vector<String>> callback);

}
