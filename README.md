# Testing the [Eventures Web App](http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/)

This project contains both **automated test scripts** and **manual test cases** for the **Enetures app**, designed to ensure the functionality and reliability of the application. The tests were performed to demonstrate proficiency in both **manual testing** and **automation with Selenium WebDriver and TestNG**.

## Table of Contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Test Cases](#test-cases)
  - [Automated Tests](#automated-tests)
  - [Manual Tests](#manual-tests)
- [Running Tests](#running-tests)
- [Contributing](#contributing)

## Overview

This repository includes both **automated tests** and **manual tests** for the following functionalities of the **Enetures app**:
- **Login**
- **Register**
- **Home Page**
- **Create Event**

The **automated tests** are written in Java using the Selenium WebDriver for browser automation and TestNG for test execution and reporting. The **manual tests** were created and tracked in Jira to ensure a thorough exploration of the application's functionality.

## Prerequisites

To run the tests, ensure the following tools and dependencies are installed:

- **Java**: Version 19 or later
- **Selenium WebDriver**: Browser automation framework
- **TestNG**: Test framework for running tests and generating reports
- **Maven**: To manage dependencies and build the project
- **IDE**: IntelliJ IDEA

Additionally, make sure to have the following browser drivers:
- **ChromeDriver** (for Google Chrome)
- **GeckoDriver** (for Firefox)

## Setup

1. Clone this repository to your local machine:

    ```bash
    git clone https://github.com/your-username/your-repository-name.git
    cd your-repository-name
    ```

2. Install the required dependencies:

    If you're using Maven, run:

    ```bash
    mvn clean install
    ```

3. Ensure that the correct browser driver (e.g., ChromeDriver) is installed on your system and accessible via the `PATH` environment variable.

## Test Requirements

## **Login Page**
Users can log in to the application using a **username** and **password**.
### Requirements:
- The user **cannot log in** without entering both a username and a password.
- A default guest account is available:
  - **Username:** `guest`
  - **Password:** `guest`
- If the correct login credentials are entered and the **Log in** button is clicked, the user is **redirected to the Home page**.

## **Homepage**
The homepage provides key navigation options and displays event-related statistics.
### **Accessing All Events**
- The **All Events** page can be accessed in two ways:
  1. Clicking the **"All Events"** link on the **Homepage**.
  2. Selecting **"All Events"** from the **Events** dropdown menu.

### **Creating an Event**
- A new event can be created in two ways:
  1. Clicking the **"New Event"** link on the **Homepage**.
  2. Selecting **"Create Event"** from the **Events** dropdown menu.

### **Logging Out**
- The user must be able to log out by clicking the **"Logout"** button.

### **Event Statistics**
- The **total number of events** in the system is displayed on the **Homepage**.
- The **number of events owned by the logged-in user** is displayed on the **Homepage**.

## **Homepage**
Users can register on the site by providing the following details:  
- **Username**
- **Email**
- **Password**
- **First Name**
- **Last Name**
### **Requirements:**
- The **username** must be at least **5 characters long** and consist only of **letters and numbers**.
- The **email** must follow a valid format: `something@something.something` (e.g., `petrov@abv.bg`).  
  - **Email is required**.
- The **password** must be between **6 and 20 characters**.
- The **confirmation password** must match the chosen password.
- The **first name** is required and must contain **only letters**, with a minimum length of **2 characters**.
- The **last name** is required and must contain **only letters**, with a minimum length of **2 characters**.
  
## **Create New Event**
Logged-in users can create new events.
### **Requirements:**
- Every event must have a **Name**:
  - The name must be at least **3 characters** and no more than **50 characters**.
- Every event must have a **Place**:
  - The place must be at least **3 characters** and no more than **70 characters**.
- Every event must have a **Start Date** and an **End Date**:
- The **total number of tickets** must be a **positive number greater than 0**.
  - The maximum total number of tickets is **1000**.
- The **ticket price** must be between **0 and 1000** (inclusive).
- When clicking the **"Create Event"** button:
  - A new event is created.
  - The user is redirected to the **All Events** page, where the newly created event is displayed.
- When clicking the **"Back to List"** button:
  - The user is redirected to the **All Events** page.

## Running Tests

You can run the tests in the following ways:

1. **Using Maven** (from the command line):

    To run the tests, use the following command:

    ```bash
    mvn test
    ```

    This will run all the test cases defined in the project.

2. **Using TestNG**:

    - If you are using an IDE like IntelliJ IDEA or Eclipse, you can directly run the tests using the TestNG suite.
      
## Test Cases

### Automated Tests
The automated tests are written to validate the following functionalities:

#### 1. **Login Test**

#### 2. **Register Test**

#### 3. **Home Page Test**

#### 4. **Create Event Test**

### Manual Tests

In addition to the automated tests, manual testing was performed to thoroughly assess the app's behavior. All manual test cases were documented and tracked using **Jira**. These tests include:

- **Login Functionality**: Testing different combinations of valid and invalid credentials.
- **Registration Form**: Verifying the behavior with edge cases (empty fields, invalid email format, etc.).
- **UI Elements**: Ensuring all visual elements load correctly (buttons, forms, text fields, etc.).
- **Event Creation**: Testing event creation with invalid and valid inputs to ensure proper error handling and data submission.

#### Manual Testing Process
- Test cases were created and executed based on the app's features and user stories.
- Bugs or issues found during manual testing were logged and tracked in Jira, providing detailed steps, **I know there must be screenshots/videos though I missed uploading them**, and environment details.
- Links to Jira tickets for the manual tests are available [here](https://stantodorov09.atlassian.net/jira/software/c/projects/EV/issues?jql=project%20%3D%20%22EV%22%20ORDER%20BY%20created%20DESC).


## Contributing

Contributions to this project are welcome! If you'd like to improve the tests or add new ones, feel free to fork the repository and submit a pull request. Please follow the steps below to contribute:

1. Fork the repository
2. Create a new branch for your changes
3. Commit your changes and push them to your fork
4. Open a pull request with a detailed explanation of the changes
