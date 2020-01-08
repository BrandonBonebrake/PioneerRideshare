# PioneerRideshare
Digitizing the Pioneer Ridesharing program on the UW-Platteville campus. This is an idea project and is not endorsed by the UW-Plattevile campus.

This is a semester project at the University of Wisconsin.

Compiling/Running the program
-----------------------------------------------------------------------------------------------------------------------------------
A release version of the application is on GitHub. Feel free to download and test the program.

Compile the server, located in PioneerRideshare/src/socketCommunication/server.java
into a jar file. The server needs folders in the format that are located in files/.

Compile the client located in PioneerRideshare/src/gui/PioneerApplication.java 
into a jar file.

Nothing else will be required if you use Intelij. 
For eclipse you will need to add the libraries to the server jar.

Assuming the port 63341 has been port forward the program should work on all computers on the same network.
You can run both on the same computer if you want to test the program.

Using the application
-----------------------------------------------------------------------------------------------------------------------------------
To use the application effectively the first that must be done is to login. Any email with the @uwplatt.edu domain will work.
The email will be used to receive emails about accepted ride offers/requests.

Once logged in you are able to create/receive ride offers/requests.
The created rides are persistant and will save upon creation and load upon reload.
