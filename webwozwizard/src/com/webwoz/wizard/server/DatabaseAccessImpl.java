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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webwoz.wizard.server.Database;
import com.webwoz.wizard.client.DatabaseAccess;

@SuppressWarnings("serial")
public class DatabaseAccessImpl extends RemoteServiceServlet implements
		DatabaseAccess {

	private Database db = new Database();

	// retrieveData
	public String[][] retrieveData(String sql) {
		return db.retrieveData(sql);
	}

	// storeData
	public String storeData(String sql) {
		return "" + db.storeData(sql);
	}

	public String getTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void closeConnection() {
		db.closeDBCon();
	}
}
