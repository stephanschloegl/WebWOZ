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

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("serial")
public class NotesExport extends HttpServlet {

	private String output;
	private Database db = new Database();
	private String[][] data;
	private String sql;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		sql = "Select * from experimentnotes where expid = "
				+ request.getParameter("p1") + " and userid = "
				+ request.getParameter("p2") + " order by id";

		data = db.retrieveData(sql);

		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition",
				"attachment; filename=WebWOZ-NotesExport.csv");

		output = "<html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"><title>WebWOZ Notes Report</title></head><body>";
		output = output
				+ "<table style=\"font-family:verdana, sans-serif; font-size:small; border-collapse:separate;\"><tr style=\"background-color:#CCCCCC;\"><th style=\"border: 1px solid #000000;\">Experiment ID</th><th style=\"border: 1px solid #000000;\">User ID</th><th style=\"border: 1px solid #000000;\">Timestamp</th><th style=\"border: 1px solid #000000;\">Note</th></tr>";
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				output = output
						+ "<tr><td style=\"border: 1px solid #000000; text-align: center;\">"
						+ data[i][1]
						+ "</td><td style=\"border: 1px solid #000000; text-align: center;\">"
						+ data[i][2]
						+ "</td><td style=\"border: 1px solid #000000; text-align: center;\">"
						+ data[i][3]
						+ "</td><td style=\"border: 1px solid #000000;\">"
						+ data[i][4] + "</td></tr>";
			}
		}
		output = output + "</table>";
		output = output + "</body></html>";
		PrintWriter out = response.getWriter();
		out.println(output);

	}

}
