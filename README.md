# Lost and Found Mobile Application

## Overview

The Lost and Found mobile application is an Android-based solution designed to help users report, search, and manage lost and found items efficiently. The application provides a simple and structured interface where users can post details about lost or found belongings and browse listings shared by others.

This project focuses on creating a user-friendly system that improves the chances of recovering lost items through organized reporting and search capabilities.

---

## Features

* **Post Item**
  Users can report lost or found items by providing details such as title, description, and location.

* **View Items**
  Displays a list of all reported items in a structured format.

* **Search Functionality**
  Allows users to search for specific items based on keywords.

* **Edit and Delete Items**
  Users can update or remove previously posted items.

* **User-Friendly Interface**
  Clean and intuitive design with bottom navigation for easy access to different sections.

---

## Tech Stack

* **Platform:** Android
* **Language:** Java
* **UI Design:** XML Layouts
* **Architecture:** Activity + Fragment based structure
* **Data Storage:** SharedPreferences (local storage)

---

## Project Structure

```
com.example.lostandfound
│
├── activities
│   ├── SplashActivity
│   ├── MainActivity
│   ├── PostItemActivity
│   ├── ItemDetailActivity
│   └── EditItemActivity
│
├── fragments
│   ├── HomeFragment
│   ├── SearchFragment
│   └── ProfileFragment
│
├── adapters
│   └── ItemAdapter
│
├── models
│   └── Item
│
└── utils
    └── PreferencesManager
```

---

## Installation and Setup

1. Clone the repository:

   ```
   git clone https://github.com/Ishi2006/LostAndFound.git
   ```

2. Open the project in Android Studio.

3. Allow Gradle to sync dependencies.

4. Run the application on an emulator or physical device.

---

## Usage

* Launch the application.
* Use the bottom navigation to switch between Home, Search, and Profile sections.
* Add a new item using the post option.
* Browse or search for items.
* Edit or delete items as required.

---

## Future Improvements

* Integration with cloud database (Firebase)
* User authentication system
* Image upload support for items
* Real-time notifications
* Advanced filtering and sorting

---

## Author

Ishita Aggarwal

---

## License

This project is intended for educational purposes.
