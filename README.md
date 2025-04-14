# Automation MVP App - User Guide

This document explains how to set up and run the automation code controlling the tablet app. Please follow the steps closely to ensure proper operation.

---

## Prerequisites

- **Tablet Connection:**  
  The tablet must be plugged into the computer.

- **Required Logins & App State:**  
  - **Instacart:**  
    - The tablet must be opened and Instacart must be logged in.
    - Ensure that the carts are all clear.
  - **Airbnb:**  
    - Make sure Airbnb is logged in.

- **Software Requirements:**  
  - **Android Studio:**  
    - Open the `AutomationMVP` project.
  - **Appium:**  
    - To be started via PowerShell.
  - **Python IDE (PyCharm):**  
    - This will open an IDE called LlamaCode.

---

## Step-by-Step Setup

### 1. Prepare the Tablet

- **Connect the Tablet:**  
  Ensure the tablet is physically connected to the computer.

- **Verify Tablet Apps:**  
  - Open the tablet.
  - Make sure **Instacart** is logged in and that the carts are clear.
  - Make sure **Airbnb** is logged in.

### 2. Open Android Studio and Run the Project

- **Launch Android Studio:**  
  - Open the `AutomationMVP` project in Android Studio.
  - Look at the top for either a **redo** icon or a **play** icon.
  - Clicking either one should run the app.

### 3. Start the Appium Server

- **Using PowerShell:**  
  - Open PowerShell.
  - Type the following command and press **Enter**:

    ```powershell
    appium --session-override
    ```

  - **Note:** The python automation script requires the Appium server to be running, so this step is mandatory.

### 4. Launch the Python Automation Server in PyCharm

- **Start PyCharm (LlamaCode IDE):**  
  - Open PyCharm and it should launch an IDE session called **LlamaCode**.
  
- **Run the Server:**  
  - Locate the file named `main.py`.
  - Run `main.py`.
  
- **Server Confirmation:**  
  - Watch for the output message:
  
    ```
    WebSocket Server is listening on ws://0.0.0.0:8765
    ```
  
  - This message confirms that the server is running and the tablet app will operate correctly.

### 5. Using the Tablet App

- **Voice Command Feature:**  
  - Press **Start Listening to Talk** on the tablet.
  - You can speak commands such as:
    - "Buy my groceries"
    - "Get me an Airbnb"
    - "Get me a flight"

- **Deploy Automation:**  
  - Press the **Deploy** button to start the automation process.
  - **Important:** You cannot run another automation until the current one is complete.

---

## Additional Notes and Maintenance

- **Refreshing Appium:**
  - After long periods of time or if there are issues:
    - Go back to PowerShell.
    - Refresh Appium by typing `Ctrl + C` to stop the current session.
    - Restart Appium with the same command: `appium --session-override`.

- **Refreshing Android Studio:**
  - If the buttons (redo/play) stop working:
    - Refresh Android Studio by re-clicking the run button.

- **Critical Reminder:**
  - **DO NOT UPDATE THE APPS PLEASE.**  
    Updating the apps may break the automation setup.

---

## Troubleshooting

- **Tablet Connection Issues:**  
  - Confirm the tablet is properly connected to the computer.
  - Ensure that both Instacart and Airbnb are logged in as required.

- **Appium Not Running:**  
  - Verify that the Appium server is active and running without errors in PowerShell.

- **Missing Server Message:**  
  - If you do not see "WebSocket Server is listening on ws://0.0.0.0:8765" after running `main.py`:
    - Double-check that PyCharm launched correctly with the LlamaCode IDE.
    - Ensure `main.py` is executed without errors.

- **Automation Process Issues:**  
  - Only one automation process can run at a time.
  - Ensure the current automation completes before starting a new one.

---

By following these instructions, you should be able to successfully set up and operate the automation app on your tablet. If further issues arise, refer to the documentation for Android Studio, Appium, or consult technical support.
