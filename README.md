# 🧪 Automation Project – DailyFinance App
## Automation-tests-by-SeleniumTestNg

This project automates the test cases of the [DailyFinance Web App](https://dailyfinance.roadtocareer.net/) using **Selenium**, **TestNG**, and **Java** following the **Page Object Model (POM)** architecture.

---

## ✅ Test Coverage

### 1. **User Registration + Email Verification**
- Register a new user with `gmail+randomDigit@gmail.com`
- Assert the “Congratulations” email is received

### 2. **Reset Password**
- Negative Test Case 1: Unregistered email input → Assert proper validation
- Negative Test Case 2: Invalid email format → Assert email is taken or not
- Input valid registered email → Click *Send Reset Link*
- Retrieve reset link from Gmail → Set a new password
- Login using new password and assert success

### 3. **Item Handling**
- Add **2 items**:
    - One with all fields
    - One with only mandatory fields
- Assert both items are shown in item list

### 4. **Profile Update**
- Update user’s Gmail address
- Logout and login with:
    - ✅ Updated email → assert login success
    - ❌ Previous email → assert login fails

### 5. **Admin Workflow**
- Login as **Admin** (Credentials passed via Terminal securely)
- Search updated user by email
- Assert updated Gmail is shown on admin dashboard

### 6. **CSV User Registration**
- Register 3 users using a CSV input file

### 7. **Admin Export Users**
- Login as admin
- Extract all user data from admin table
- Write the data (except "View" column) into `users.txt` file including headers

---
## 👨‍💻 Tech Stack

- **Java** – Programming language
- **Selenium WebDriver** – Web automation framework
- **TestNG** – Testing framework
- **Allure** – Test reporting tool
- **Gradle** – Build tool
- **Gmail API ** – Email fetching
- **Page Object Model (POM)** – Design pattern for test maintenance
---

## 📸 Allure Report Screenshot

> 

---
## 🎥 Demo Recording

A full walkthrough of the automation process has been recorded.  
You can watch the demo recording [here]

---

## 📋 Test Cases (Excel)

All standard and negative test cases have been documented in an Excel file.  
Access the test cases [here](https://docs.google.com/spreadsheets/d/1gweKyqPyMwp11loTaKPrV3c9YLV-l_UY/edit?usp=sharing&ouid=110591976413796555813&rtpof=true&sd=true)

## 🚀 Run Instructions

```bash
# Run test with Allure report
gradle clean test

# Generate Allure report
allure generate allure-results --clean -output
allure serve allure-results

# Pass admin credentials securely
-Pusername=admin@test.com -Ppassword=admin123
```

---

## ❌ Ignored Files

The following files and folders are excluded using `.gitignore`:
```bash
.gradle
.idea
build
gradle
logs
!gradle/wrapper/gradle-wrapper.jar
!**/src/main/**/build/
!**/src/test/**/build/
allure-report
allure-results
config.properties
```
📫 Contact me:
- **Email:** onlyfaisalkabir@gmail.com
- **LinkedIn:** [Faisal Kabir](https://www.linkedin.com/in/faisal-kabir1/)
- **GitHub:** [faisalkabir1](https://github.com/faisalkabir1)