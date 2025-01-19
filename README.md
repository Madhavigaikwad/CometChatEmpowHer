# 🌍 **EcoChat - Chat to Save Nature** 🌿

**Welcome to EcoChat**, an innovative messaging platform designed to create a community of eco-conscious individuals. Share ideas, discuss sustainable living, and promote environmental well-being through real-time conversations.

> “Chat to Save Nature”

---

## 📖 **Project Description**

**EcoChat** is a powerful platform for fostering discussions on sustainability and eco-friendly habits. By leveraging **CometChat’s SDK and UI kits**, users can participate in themed chat rooms, organize green initiatives, and exchange ideas in real-time. The app aims to inspire eco-conscious lifestyles and encourage collaboration on environmental challenges.

---

## ✨ **Why EcoChat?**
- Build meaningful discussions on environmental topics.
- Access themed chat rooms focused on eco-friendly habits.
- Participate in activities and earn rewards for making a positive environmental impact.

---

## 🚀 **Key Features**
- **Real-Time Messaging**: Communicate instantly using the **CometChat SDK**, with support for text, images, videos, and group chats.
- **Themed Chat Rooms**: Join chat rooms focused on topics like waste reduction, energy-saving tips, and sustainable living.
- **Eco-Friendly Rewards System**: Earn rewards for contributing valuable ideas and organizing environmental activities.
- **Location-Based Group Chats**: Collaborate with local environmental groups on community-driven initiatives.

---

## 🛠️ **Setup Instructions**

### Pre-requisites
Before setting up **EcoChat**, ensure the following are installed:
- **Android Studio** (latest version)
- **Kotlin** (for development)
- **Firebase Account** (for push notifications)
- **Git** (for version control)

### Steps to Set Up Locally
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/bhaveshdesale/EcoChatApp.git

   Open the Project:

2. **Open the cloned repository in Android Studio.**
   
3. **Configure Firebase:**

-*Create a Firebase project.
-*Download the google-services.json file and place it in the app directory.
-*Set up Firebase Cloud Messaging (FCM) for push notifications.
-*Install Dependencies: In the build.gradle file, ensure the following dependencies are included:

4.**gradle**
Copy code
implementation 'com.cometchat:pro-android-chat-sdk:3.x.x'
implementation 'com.google.firebase:firebase-messaging:23.0.0

5.**Run the App:**

6.**Connect an Android device or use an emulator.**
Build and run the project by pressing the green play button in Android Studio.

7.**📑 Additional Documentation**
Splash Screen
Description: The app launches with a splash screen showcasing the EcoChat logo and the slogan “Chat to Save Nature”.
Technology: Implemented using a Handler and Intent to transition to the main activity after a 3-second delay.

8.**User Interface Breakdown:**
MainActivity: The central hub for users to access chat rooms, view profiles, and explore eco-friendly tips.
Login/Signup: Firebase-powered secure authentication to handle user logins and registrations.
ConversationsActivity: A robust chat interface for real-time messaging and media sharing.

9.**🎥 Demo Video**
Check out our demo video highlighting EcoChat’s main features: 

https://github.com/user-attachments/assets/488eed63-825d-4a62-80b8-b2242e11c499

https://github.com/user-attachments/assets/bd8affd5-d18d-461d-a603-acc4a3db05d2


https://github.com/user-attachments/assets/91bbbb6f-f733-405d-a070-e1e97330afdd


https://github.com/user-attachments/assets/9eb5c975-cb3f-469a-be81-d0d6337627e9



https://github.com/user-attachments/assets/1d6eb211-98ac-4fcd-9dd8-14404870bf33










10.**🛠️ Future Improvements**
Dark Mode Support: Allow users to toggle between light and dark themes.
Multi-Language Support: Add support for multiple languages to cater to a global audience.
Group Video Calls: Integrate video calling functionality using CometChat’s video SDK to enable face-to-face conversations.

11.**🤝 Contributing**
We welcome contributions to enhance EcoChat! Feel free to submit a pull request or report an issue in the repository.

12.**📄 License**
This project is licensed under the MIT License - see the LICENSE file for details.
