
 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
| Welcome to WebWOZ the web-based Wizard of Oz Prototyping Platform!  |
 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 

WebWOZ has been developed at the School of Computer Science & Statistics at Trinity College Dublin as part of research conducted at the Centre for Next Generation Localisation (CNGL). Our goal is it to give designers and researchers a quick and easy way to design and conduct Wizard of Oz (WOZ) experiments in connection with Language Technologies. WebWOZ is entirely web-based and can therefore be used with any device capable of running a modern web browser. 

WebWOZ Features:
- Integration of existing Language Technology Components into WOZ
- Generic platform that can be used for different experiments
- Entirely web-based and therefore almost no setup time
- Multi-platform support
- Iteratively developed and improved by looking at the task of the wizard and its challenges

Please check out the project website at http://www.webwoz.com for further information!


 - - - - - - - - - - - - - - -
| R E Q U I R E M E N T S :   |
 - - - - - - - - - - - - - - -

WebWOZ is developed in Java and therefore requires an application container that allows to run server-based Java code. While in general any application server should do the trick, we recommend Apache Tomcat (http://tomcat.apache.org/). In addition you need to operate a MySQL database (http://www.mysql.com/). Again, WebWOZ will also work with alternative databases, however, to do so certain adaption need to be made to the source code.


 - - - - - - - - - - - - - - -
| I N S T A L L A T I O N :   |
 - - - - - - - - - - - - - - -

In order to install WebWOZ your need to first create the required database environment. Login to your MySQL database and run the SQL commands outlined in DATABASE.txt

After the database environment is created you can copy the compiled Java code for the two webwozwizard as well as the webwozclient into the 'webapps' directory of your applications server. To do so, go first into the 'webwozwizard' directory and COPY the 'war' directory into the 'webapps' directory of your application server. Then RENAME the war directory that is now in your 'webapps' folder into 'webwozwizard'. Repeat the same for the 'war' directory in 'webwozclient'. This one your rename in 'webwozclient'. At the end your should have two new drectories in your 'webapps' folder. One called 'webwozwizard' and one called 'webwozclient'. Now restart your application server. After the server is back the WebWOZ wizard interface should be available at http://YourLocalhostApplicationServer/webwozwizard and the client interface at http://YourLocalhostApplicationServer/webwozclient.


 - - - - - - - - - - - - -
| D E V E L O P M E N T : |
 - - - - - - - - - - - - -

WebWOZ was developed using the Google GWT in version 2.3.0. Further information about GWT can be found at: https://developers.google.com/web-toolkit/


 - - - - - - - - - - - - - - -
| D O C U M E N T A T I O N : |
 - - - - - - - - - - - - - - -

Please check out the WebWOZ documentation at: http://www.webwoz.com/downloads


 - - - - - - - - -
| L I C E N S E : |
 - - - - - - - - -

WebWOZ is licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at: http://www.apache.org/licenses/LICENSE-2.0
 
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.


