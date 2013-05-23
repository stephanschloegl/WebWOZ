package com.webwoz.client.server;

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

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webwoz.client.server.Database;

@SuppressWarnings("serial")
public class ASR extends HttpServlet {

	private String output;
	private Database db = new Database();
	private String sql = "";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("speechinput") != null) {

			sql = "Insert into output (textorig, audioorig, mmorig, texttrans, audiotrans, mmtrans, timestamp, experiment, user, sign, sender, receiver, asr) values (\""
					+ request.getParameter("speechinput")
					+ "\", '-', '-', '-', '-', '-', '"
					+ getDateTime()
					+ "', "
					+ request.getParameter("experiment")
					+ ", "
					+ request.getParameter("user")
					+ ", 3, "
					+ request.getParameter("user")
					+ ", "
					+ request.getParameter("wizard") + ", 1)";
			// store input
			db.storeData(sql);

		} else {

		}

		// forward to WebWOZ Client interface
		output = "<html><head><meta http-equiv=\"refresh\" content=\"0; URL=../WebWOZClient.html\"><title>Speech Recognition...</title></head><body></body></html>";
		PrintWriter out = response.getWriter();
		out.println(output);

	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

}
