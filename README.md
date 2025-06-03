# Vacation & Excursion Android App

The Vacation Planner App is an Android application developed in Android Studio. It allows users to create and manage vacations, as well as add excursions linked to each vacation.

Throughout the project, additional functionality was implemented, including scheduling start and end dates, sharing vacation details, setting alerts, and generating vacation reports. Once complete, the app was signed and packaged as an APK for deployment to the GitHub Pages.

- **Minimum SDK Version**: 21 (Android 5.0 Lollipop)
- **Target SDK Version**: 35 (Android 14)

## Basic Instructions

Download the Git Repository:

([https://gitlab.com/wgu-gitlab-environment/student-repos/dmck244/d308-mobile-application-development-android.git](https://github.com/DigitallyDeja/vacation-app.git))

Download SDK:

(https://digitallydeja.github.io/wgu-projects/)

Open the project in the IDE: **Android Studio** (Flamingo)

Run the project on a physical Android device or on an Android emulator through the IDE.

## Use Instructions

1. **Login Screen**  
   When the application is launched, the user will land on the **Login** screen.  
   The default credentials are:
   - Username: `username10`
   - Password: `password20`

2. **Vacation List Page**  
   The current landing page is the **Vacation List**. This will hold all saved details from the entered vacations.
   - In the menu in the upper right-hand corner (three dots), select **Add Vacation Details** to populate sample vacation entries.
   - To create a new vacation manually, click the **"+"** button.

3. **Vacation Details Page**  
   Once the **"+"** button is selected, the user is taken to the **Vacation Details** page.
   - Add the **Vacation Title**, **Hotel Name**, **Start Date**, and **End Date**.
   - Below holds all associated excursions.
   - Save the vacation by tapping the menu and selecting **Save Vacation**. This will populate the vacation in the Vacation List.

   **Important Notes:**
   - The **End Date** cannot be before the **Start Date** or an error will display.
   - Existing vacations can be selected, updated, and re-saved.

   Available menu options:
   - **Delete Vacation**: Removes the vacation from the Vacation List.
      - Vacations cannot be deleted if they have excursions. Excursions must be removed first.
   - **Share**: Shares vacation details (including excursions) through the device’s sharing options.
   - **Alert**: Displays a notification if today matches the Start Date, End Date, or both.

4. **Excursion Details Page**  
   On the Vacation Details page, click the second **"+"** button to navigate to the **Excursion Details** page.
   - Fill in the **Excursion Name** and **Excursion Date** fields.
   - Save the excursion by selecting **Save Excursion** from the menu.

   **Important Notes:**
   - The excursion must occur between the vacation’s Start and End Dates. Otherwise, an error message will display.

   Available menu options:
   - **Delete Excursion**: Removes the excursion from the Vacation Details.
   - **Alert**: Displays a notification if the excursion is scheduled for today.

**Encapsulation, Inheritance, & Polymorphism**
   - Encapsulation is implemented throughout using private variables and public getters/setters. Inheritance and polymorphism are demonstrated with the Vacation class extending the Location parent class and overriding its getMessage method. A search functionality is available to filter vacations by name. 

## Additional Features

- **Search Functionality**  
  Use the search bar on the Vacation List page to find a vacation by name.

- **Reports Page**  
  After navigating past the Vacation List and Excursion screens, users can generate a list of vacations scheduled within a selected Start and End Date range.

- **Simple Navigation**  
  The app is designed with intuitive navigation to allow easy creation of vacations, addition of excursions, and generation of reports.

## Deployment

- The app has been signed and packaged as an APK.
- The target Android version is Android 14 (SDK 35).
- The app is compatible with devices running Android 5.0 (Lollipop) and higher.
