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

import java.sql.*;

public class Database {

	// Database access
	private Connection conn = null;
	private String url = "jdbc:mysql://localhost:3306/webwoz?allowMultiQueries=true&autoReconnect=true";
	private String user = "wizard";
	private String pass = "wizard";

	public void MySQLConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, pass);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// retrieveData
	public String[][] retrieveData(String sql) {
		MySQLConnection();

		try {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			// get rs meta data
			ResultSetMetaData meta = rs.getMetaData();
			// get column count
			int cCount = meta.getColumnCount();

			// get result count in order to define the size of the array
			int current = rs.getRow();
			rs.last();
			int count = rs.getRow();
			if (count != 0) {
				// count = 0;
				if (current == 0) {
					rs.beforeFirst();
				} else {
					rs.absolute(current);
				}

				String[][] s = new String[count][cCount];
				int i = 0;

				while (rs.next()) {
					for (int j = 0; j < cCount; j++) {
						s[i][j] = rs.getString(j + 1);
					}
					i++;
				}
				rs.close();
				return s;
			} else {
				return null;
			}

		} catch (Exception sqle) {
			System.out.println(sqle);
			return null;

		} finally {

		}
	}

	// storeData
	public int storeData(String sql) {

		MySQLConnection();

		int lastInsertedId = 0;

		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs2 = stmt.getGeneratedKeys();

			if (rs2 != null && rs2.next()) {
				lastInsertedId = rs2.getInt(1);
			}

			rs2.close();
			stmt.close();

		} catch (Exception sqle) {
			System.out.println(sqle);

		} finally {

		}
		return lastInsertedId;

	}

	// close database connection
	public void closeDBCon() {
		try {
			conn.close();
		} catch (Exception dbe) {
			System.out.println(dbe);
		}

	}

}
