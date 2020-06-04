# Scraper for EFA-BW

### 1. Purpose of this project
This project is a scraper for the EFA-BW website and provides a REST-API to get connection data using the following website: https://www.efa-bw.de/nvbw/XSLT_TRIP_REQUEST2?language=de&itdLPxx_calcMethod=BW&itdLPxx_frames=&sessionID=0&requestID=0&ptOptionsActive=1&useProxFootSearch=1&lineRestriction=400

### 2. Installation to run this project
To run this project, you need the following programms:
1. Maven: https://maven.apache.org/download.cgi
2. Java

When everything is installed you can run the project with the following command: `mvn spring-boot-run`

### 3. Documentation
The projects provides a REST-API to get connection data between an origin and destination place at a specific datetime for several transportationtypes.

You can access the REST-API with the following URL: `http://localhost:8081/connections/from/{origin}/to/{destination}/at/{timestamp}/transportationTypes/{transportationTypes}`

The transportation types that can be filtered are:
* 0: Zug
* 1: S-Bahn
* 2: Stadt-/U-Bahn
* 3: Strassen-/Trambahn
* 4: Stadtbus
* 5: Regionalbus
* 6: Nachtbus
* 7: Seil-/Zahnradbahn
* 8: Schiff
* 9: Anruf-, Sammeltaxi / Rufbus
* 10: Sonstige

For origin and destination just use a string for a place. For the timestamp use a UNIX timestamp in milliseconds.The transportationTypes are a row of numbers for the specific transportation types, seperated with "!".
