# QR_generator_contacts

A modern Android contact sharing app that generates QR codes for individual contacts, built with Jetpack Compose.

**Group 7:** Tuan Kiet Bui, Eric Darko, Bamila Pacis Jedidiah

**GitHub:** [https://github.com/edarko265/QR_generator_contacts](https://github.com/edarko265/QR_generator_contacts)

## Screenshots

| Main Screen / Contact List | Add Contact Form | QR Code Display |
| :------------------------: | :--------------: | :-------------: |
| <img width="228" alt="image" src="https://github.com/user-attachments/assets/44b9110f-7f22-4163-bf55-c64925182914" /> | <img width="227" alt="image" src="https://github.com/user-attachments/assets/dae7ab65-bfde-4c53-bea1-e171b369d2e7" /> |    <img width="232" alt="image" src="https://github.com/user-attachments/assets/2bd4ef31-fcc0-49b2-8ff2-58d2432c32ba" />
   |
| _View, delete, and select contacts._ |           _Clean input form._ |                  _Generated QR code._ |

## About The App

QR-Contact simplifies contact management and sharing by leveraging QR codes.

### Features:

* **Add New Contacts:** Easily add new contacts (First Name, Last Name, Phone, Email) via a clean, user-friendly form.
* **View Contacts:** Display all saved contacts in an organized, scrollable card layout.
* **Delete Contacts:** Remove existing contacts directly from the list view with a dedicated delete button.
* **Generate QR Code on Click:** Tap any contact card to instantly generate a unique QR code containing that contact's information (First Name, Last Name, Phone, Email).
* **QR Code Display:** Shows the generated QR code in a clear dialog for easy scanning.

## Technology Stack & Architecture

* **UI:** Jetpack Compose (Android's modern toolkit for building native UI)
* **Architecture:** MVVM (Model-View-ViewModel)
    * **View (UI):** Composables like `ContactApp`, `ContactInputForm`, `ContactCard`, `QRCodeImage`. Handles UI display and user interaction.
    * **ViewModel:** `ContactViewModel` manages contact-related logic, holds contact data (potentially using LiveData/StateFlow), and exposes functions like `addContact()` and `deleteContact()`. Decouples UI from data handling.
    * **Model:** `Contact` data class defines the contact structure. Data is managed via `ContactRepository` interacting with a `ContactDatabase` (likely using Room Persistence Library).
* **QR Code Generation:** [ZXing (Zebra Crossing)](https://github.com/zxing/zxing) library via `MultiFormatWriter` and `BarcodeEncoder`.
* **State Management:** Utilizes `remember`, `mutableStateOf`, and `collectAsState()` for managing UI state and observing data flow from the ViewModel.
* **Database:** Room Persistence Library for local data storage.
* **Language:** Kotlin

## Getting Started

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/edarko265/QR_generator_contacts.git](https://github.com/edarko265/QR_generator_contacts.git)
    ```
2.  **Open in Android Studio:** Open the cloned project directory in Android Studio (latest stable version recommended).
3.  **Build:** Let Android Studio sync Gradle dependencies. Build the project (Build > Make Project).
4.  **Run:** Run the app on an Android emulator or a physical device.

## Contributing

Contributions are welcome! Please submit pull requests or open issues to improve the app.

---

*This README was generated based on the project presentation.*
