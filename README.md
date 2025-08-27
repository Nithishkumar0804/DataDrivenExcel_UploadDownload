# DataDriven & Upload/Download Functionalities

This project demonstrates **data-driven testing with Selenium** using Excel files and automating **file upload and download scenarios**.

---

## 📌 Features Covered
- Reading test data from Excel using Apache POI  
- Parameterizing test cases with TestNG DataProvider  
- Automating file uploads with:
  - sendKeys()
  - AutoIT scripts
  - Robot Class  
- Automating file downloads using Chrome Options  
- Handling dynamic file paths and downloaded files  

---

## 🛠 Tech Stack
- **Language**: Java  
- **Automation Tool**: Selenium WebDriver  
- **Build Tool**: Maven  
- **Testing Framework**: TestNG  
- **Library**: Apache POI (for Excel integration)  

---

## 📂 Project Structure
┣ src/
┃ ┣ main/
┃ ┃ ┣ java/ # Utility classes
┃ ┃ ┗ resources/ # Excel test data files
┃ ┗ test/
┃ ┗ java/ # Test classes (DataDriven, UploadDownload, AutoIT, etc.)
┣ pom.xml
┗ README.md
