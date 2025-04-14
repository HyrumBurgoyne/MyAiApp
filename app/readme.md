Automation MVP App - User Guide
This document explains how to set up and run the automation code that controls the tablet app. Please follow each step closely to ensure proper operation.

Prerequisites
Tablet Connection: The tablet must be plugged into the computer.

App Logins:

Instacart: Ensure you are logged in and that all carts are clear.

Airbnb: Ensure the app is logged into.

Software:

Android Studio with the AutomationMVP project installed.

Appium (accessed via PowerShell).

Python IDE (PyCharm) which will open an environment named LlamaCode.

Setup Instructions
1. Prepare the Tablet
   Connection: Ensure the tablet is connected to the computer via USB.

App Verification:

Open the tablet.

Confirm that Instacart is logged in and the carts are clear.

Confirm that Airbnb is logged in.

2. Start the Android Studio Project
   Open Android Studio and load the AutomationMVP project.

At the top of the interface, look for the redo icon or the play icon; clicking either should run the application.

3. Launch the Appium Server
   Open PowerShell.

Enter the following command and press Enter to start the Appium server with session override:

powershell
Copy
appium --session-override
This step is crucial since the Python automation script requires the Appium server to be running.

4. Run the Python Automation Server
   Open PyCharm (this will launch the LlamaCode IDE).

Locate the file named main.py.

Run main.py. This acts as the server that communicates with the tablet.

Once running, look for the message:

csharp
Copy
WebSocket Server is listening on ws://0.0.0.0:8765
This indicates the server is operational, and the tablet app can now function correctly.

5. Using the Tablet App
   Voice Commands:

Press the Start Listening to Talk button on the tablet.

You can say commands such as:

"Buy my groceries"

"Get me an Airbnb"

"Get me a flight"

Deploy Automation:

Press the Deploy button to initiate the automation.

Note: You cannot run another automation until the current one completes.

Additional Notes
Refreshing Appium:

After long periods of inactivity or if issues occur, refresh Appium:

In PowerShell, press Ctrl + C to stop the current session.

Restart Appium using the command provided earlier.

Android Studio Issues:

If buttons or functionalities in Android Studio stop working, try refreshing by re-clicking the run button.

Important:

DO NOT UPDATE THE APPS PLEASE. Updating might break the automation setup.

Troubleshooting
Tablet Not Recognized:

Confirm the tablet is properly connected and has the required apps logged in.

Appium Issues:

Ensure the Appium server is running and without errors.

Server Message Missing:

Verify that the output in PyCharm shows the WebSocket message. This confirms that main.py is running correctly.

Automation Not Responding:

Double-check that you are not trying to run multiple automations simultaneously.

Conclusion
Following these steps will ensure the automation application operates correctly on your tablet. If you encounter issues beyond these instructions, please reach out for further assistance or consult the documentation for Appium, Android Studio, and your Python environment.