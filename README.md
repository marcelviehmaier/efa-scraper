# Scraper for EFA-BW

### 1. Purpose of this project
This project is a scraper for the EFA-BW website and provides a REST-API to get connection data using the following website: https://www.efa-bw.de/nvbw/XSLT_TRIP_REQUEST2?language=de&itdLPxx_calcMethod=BW&itdLPxx_frames=&sessionID=0&requestID=0&ptOptionsActive=1&useProxFootSearch=1&lineRestriction=400

### 2. Installation to run this project

### 3. Documentation
The projects provides a REST-API to get connection data between an origin and destination place at a specific datetime for several transportationtypes. The transportation types that can be filtered are:
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
