# Automation MVP App - User Guide

This document explains how to set up and run the automation code controlling the tablet app. Please follow each step closely to ensure proper operation.

## Prerequisites

- **Tablet Connection:** The tablet must be plugged into the computer.
- **Required Logins and App State:**
  - **Instacart:** Make sure you are logged in and that all carts are clear.
  - **Airbnb:** Ensure that the app is logged into.
- **Software:**
  - **Android Studio:** With the `AutomationMVP` project.
  - **Appium:** To be launched via PowerShell.
  - **Python IDE (PyCharm):** This will launch an environment called LlamaCode.

## Setup Instructions

### 1. Prepare the Tablet

- **Connection:** Ensure the tablet is connected to the computer via USB.
- **Verification on the Tablet:**
  - Open the tablet.
  - Confirm that Instacart is logged in and the carts are clear.
  - Confirm that Airbnb is logged into.

### 2. Run the Android Studio Project

- Open **Android Studio** and load the `AutomationMVP` project.
- At the top of the interface, locate either the **redo** icon or the **play** icon. Click it to run the application.

### 3. Start the Appium Server

- Open **PowerShell**.
- Execute the following command to start Appium with session override:

  ```powershell
  appium --session-override
