<p align="center">
    <img src="https://img.icons8.com/?size=512&id=55494&format=png" align="center" width="30%">
</p>
<p align="center"><h1 align="center">GNSSMOCKER-KOTLIN</h1></p>
<p align="center">
    <em>Mock your location, control your world.</em>
</p>
<p align="center">
    <img src="https://img.shields.io/github/license/renanalencar/gnssmocker-kotlin?style=default&logo=opensourceinitiative&logoColor=white&color=0080ff" alt="license">
    <img src="https://img.shields.io/github/last-commit/renanalencar/gnssmocker-kotlin?style=default&logo=git&logoColor=white&color=0080ff" alt="last-commit">
    <img src="https://img.shields.io/github/languages/top/renanalencar/gnssmocker-kotlin?style=default&color=0080ff" alt="repo-top-language">
    <img src="https://img.shields.io/github/languages/count/renanalencar/gnssmocker-kotlin?style=default&color=0080ff" alt="repo-language-count">
</p>
<p align="center"><!-- default option, no dependency badges. -->
</p>
<p align="center">
    <!-- default option, no dependency badges. -->
</p>
<br>

##  Table of Contents

- [ Overview](#-overview)
- [ Features](#-features)
- [ Project Structure](#-project-structure)
  - [ Project Index](#-project-index)
- [ Getting Started](#-getting-started)
  - [ Prerequisites](#-prerequisites)
  - [ Installation](#-installation)
  - [ Usage](#-usage)
  - [ Testing](#-testing)
- [ Project Roadmap](#-project-roadmap)
- [ Contributing](#-contributing)
- [ License](#-license)
- [ Acknowledgments](#-acknowledgments)

---

##  Overview

**gnssmocker-kotlin** is a versatile open-source project that simplifies GPS simulation for developers. With features like route management, location logging, and UI customization, it empowers app creators to test location-based services efficiently. Ideal for mobile app developers seeking reliable GPS mocking capabilities to enhance testing and user experience.

---

##  Features

|      | Feature         | Summary       |
| :--- | :---:           | :---          |
| ⚙️  | **Architecture**  | <ul><li>Utilizes **Kotlin** language for primary development.</li><li>Follows a modular structure with clear separation of concerns.</li><li>Leverages **Gradle** for build automation and dependency management.</li></ul> |
| 🔩 | **Code Quality**  | <ul><li>Consistent use of **Jetpack Compose** for UI components.</li><li>Implements **ProGuard** rules for optimizing and securing the Android app.</li><li>Uses **JUnit** for unit testing and ensuring code reliability.</li></ul> |
| 📄 | **Documentation** | <ul><li>Comprehensive documentation in **Kotlin** and **Gradle** files.</li><li>Includes detailed explanations of key components and functionalities.</li><li>Utilizes **Toml** for managing library versions across the project.</li></ul> |
| 🔌 | **Integrations**  | <ul><li>Integrates with **Google Play services** for location-related functionalities.</li><li>Utilizes **AndroidX** and **Jetpack Compose** for modern Android development.</li><li>Includes **Gradle Plugin Portal** for managing plugins and dependencies.</li></ul> |
| 🧩 | **Modularity**    | <ul><li>Organized into sub-projects/modules for clear separation of functionalities.</li><li>Encapsulates domain logic into separate use cases and repositories.</li><li>Utilizes **ViewModels** for managing UI-related data and interactions.</li></ul> |
| 🧪 | **Testing**       | <ul><li>Includes **instrumented tests** for verifying app behavior on Android devices.</li><li>Utilizes **local unit tests** for validating specific functionalities.</li><li>Employs **JUnit** for ensuring code correctness and reliability.</li></ul> |
| ⚡️  | **Performance**   | <ul><li>Optimizes performance through **ProGuard** rules for release builds.</li><li>Efficiently manages location data logging and export functionalities.</li><li>Utilizes **Haversine formula** for calculating route distances accurately.</li></ul> |
| 🛡️ | **Security**      | <ul><li>Defines project-specific **ProGuard rules** for optimizing and securing the Android app.</li><li>Preserves debugging information for stack traces to aid in troubleshooting.</li><li>Ensures secure handling of mock location data within the app.</li></ul> |
| 📦 | **Dependencies**  | <ul><li>Centralizes version control using **libs.versions.toml** for key dependencies.</li><li>Manages dependencies for **AndroidX**, **Jetpack Compose**, and **Google Play services**.</li><li>Ensures consistency and compatibility of libraries across the project.</li></ul> |

---

##  Project Structure

```sh
└── gnssmocker-kotlin/
    ├── README.md
    ├── app
    │   ├── .gitignore
    │   ├── build.gradle.kts
    │   ├── proguard-rules.pro
    │   └── src
    ├── build.gradle.kts
    ├── gradle
    │   ├── libs.versions.toml
    │   └── wrapper
    ├── gradle.properties
    ├── gradlew
    ├── gradlew.bat
    └── settings.gradle.kts
```


###  Project Index
<details open>
    <summary><b><code>GNSSMOCKER-KOTLIN/</code></b></summary>
    <details> <!-- __root__ Submodule -->
        <summary><b>__root__</b></summary>
        <blockquote>
            <table>
            <tr>
                <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/gradlew.bat'>gradlew.bat</a></b></td>
                <td>- Facilitates Gradle execution on Windows by setting up JVM options and locating the Java executable<br>- Resolves the Gradle application's home directory and classpath for running Gradle tasks efficiently<br>- Ensures proper Java environment configuration for seamless Gradle operations.</td>
            </tr>
            <tr>
                <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/build.gradle.kts'>build.gradle.kts</a></b></td>
                <td>- Configures common build options for all sub-projects/modules, disabling the application, Android, and Compose plugins<br>- This file sets up global configurations that impact the entire project structure, ensuring consistency across different modules.</td>
            </tr>
            <tr>
                <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/settings.gradle.kts'>settings.gradle.kts</a></b></td>
                <td>- Manages plugin and dependency repositories for the "GNSS Mocker" project<br>- Organizes repositories for Google, Maven Central, and Gradle Plugin Portal<br>- Sets repositories mode to fail on project repos<br>- Includes app module in the project.</td>
            </tr>
            </table>
        </blockquote>
    </details>
    <details> <!-- gradle Submodule -->
        <summary><b>gradle</b></summary>
        <blockquote>
            <table>
            <tr>
                <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/gradle/libs.versions.toml'>libs.versions.toml</a></b></td>
                <td>- Define and manage library versions across the project, ensuring consistency and compatibility<br>- The file specifies versions for key dependencies like AGP, Kotlin, JUnit, and various Android libraries<br>- This centralizes version control, aiding in maintaining a cohesive and functional codebase architecture.</td>
            </tr>
            </table>
        </blockquote>
    </details>
    <details> <!-- app Submodule -->
        <summary><b>app</b></summary>
        <blockquote>
            <table>
            <tr>
                <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/build.gradle.kts'>build.gradle.kts</a></b></td>
                <td>- Configure Android application settings, dependencies, and build features for the project<br>- Set up namespaces, SDK versions, default configurations, build types, and Kotlin options<br>- Manage dependencies for AndroidX, Jetpack Compose, Google Play services, and testing frameworks<br>- Enable features like Jetpack Compose and configure ProGuard settings for release builds.</td>
            </tr>
            <tr>
                <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/proguard-rules.pro'>proguard-rules.pro</a></b></td>
                <td>- Defines project-specific ProGuard rules to optimize and secure the Android app during the build process<br>- Controls the configuration files applied and preserves debugging information for stack traces<br>- Allows customization for WebView with JS and source file attributes<br>- Essential for enhancing app performance and protecting code integrity.</td>
            </tr>
            </table>
            <details>
                <summary><b>src</b></summary>
                <blockquote>
                    <details>
                        <summary><b>main</b></summary>
                        <blockquote>
                            <details>
                                <summary><b>java</b></summary>
                                <blockquote>
                                    <details>
                                        <summary><b>br</b></summary>
                                        <blockquote>
                                            <details>
                                                <summary><b>com</b></summary>
                                                <blockquote>
                                                    <details>
                                                        <summary><b>renanalencar</b></summary>
                                                        <blockquote>
                                                            <details>
                                                                <summary><b>gnssmocker</b></summary>
                                                                <blockquote>
                                                                    <table>
                                                                    <tr>
                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/MainActivity.kt'>MainActivity.kt</a></b></td>
                                                                        <td>- Defines the main activity responsible for initializing the app's UI components and view models<br>- Manages the theme based on user settings and system preferences<br>- Utilizes Jetpack Compose for UI rendering and navigation<br>- Interacts with DataStore for persistent data management<br>- Overall, orchestrates the core functionality and presentation of the GNSSMocker app.</td>
                                                                    </tr>
                                                                    </table>
                                                                    <details>
                                                                        <summary><b>domain</b></summary>
                                                                        <blockquote>
                                                                            <details>
                                                                                <summary><b>model</b></summary>
                                                                                <blockquote>
                                                                                    <table>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/domain/model/FavoriteLocation.kt'>FavoriteLocation.kt</a></b></td>
                                                                                        <td>- Defines a data model for favorite locations with name, latitude, and longitude fields<br>- This model encapsulates essential information about specific locations within the project's domain model architecture.</td>
                                                                                    </tr>
                                                                                    </table>
                                                                                </blockquote>
                                                                            </details>
                                                                            <details>
                                                                                <summary><b>usecase</b></summary>
                                                                                <blockquote>
                                                                                    <table>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/domain/usecase/RouteSimulationManager.kt'>RouteSimulationManager.kt</a></b></td>
                                                                                        <td>- Manages route simulation and exports logs to files for GNSS data<br>- Simulates movement based on provided points, speed, and settings<br>- Allows exporting logs as JSON or CSV files.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/domain/usecase/ImportRouteUseCase.kt'>ImportRouteUseCase.kt</a></b></td>
                                                                                        <td>- Implements a use case to import routes from a file, decoding the data into a list of routes and saving them using a repository<br>- This functionality facilitates the integration of external route data into the application, enhancing the overall user experience and data management capabilities.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/domain/usecase/ExportRouteUseCase.kt'>ExportRouteUseCase.kt</a></b></td>
                                                                                        <td>- The ExportRoutesUseCase class in the provided codebase is responsible for exporting routes to a specified file location<br>- It utilizes the repository to fetch routes, encodes them into JSON format, and writes the data to the file using the Android content resolver<br>- This functionality enables the application to save route data for future reference or sharing.</td>
                                                                                    </tr>
                                                                                    </table>
                                                                                </blockquote>
                                                                            </details>
                                                                            <details>
                                                                                <summary><b>repository</b></summary>
                                                                                <blockquote>
                                                                                    <table>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/domain/repository/RouteRepository.kt'>RouteRepository.kt</a></b></td>
                                                                                        <td>- Defines the contract for interacting with route data within the project's domain layer<br>- It specifies methods for saving, retrieving, deleting, and renaming routes<br>- This interface serves as a bridge between the domain logic and the data layer, enabling seamless manipulation of route entities.</td>
                                                                                    </tr>
                                                                                    </table>
                                                                                </blockquote>
                                                                            </details>
                                                                        </blockquote>
                                                                    </details>
                                                                    <details>
                                                                        <summary><b>util</b></summary>
                                                                        <blockquote>
                                                                            <table>
                                                                            <tr>
                                                                                <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/util/MockLocationEmitter.kt'>MockLocationEmitter.kt</a></b></td>
                                                                                <td>- Facilitates emitting mock location data through a shared flow in the project<br>- This utility enables seamless propagation of location updates to relevant components, enhancing the testing and simulation capabilities of the application.</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/util/RouteDistanceCalculator.kt'>RouteDistanceCalculator.kt</a></b></td>
                                                                                <td>- Calculate the total distance of a route based on a list of location points using the Haversine formula<br>- The code in the provided file computes the distance between consecutive points on the route, contributing to the overall functionality of the project's location-based features.</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/util/MockLocationProvider.kt'>MockLocationProvider.kt</a></b></td>
                                                                                <td>Enables pushing mock location data to the GPS provider and handles provider setup and shutdown within the Android location service.</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/util/SimulationLogger.kt'>SimulationLogger.kt</a></b></td>
                                                                                <td>- SimulationLogger manages location data logging, offering functions to add, clear, and export data in JSON or CSV formats<br>- It plays a crucial role in the project's architecture by providing a centralized utility for handling location data, ensuring efficient data management and export capabilities.</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/util/MockLocationService.kt'>MockLocationService.kt</a></b></td>
                                                                                <td>- Implements a service for simulating mock locations in the app<br>- Handles the creation of a notification channel and starts foreground service to simulate GNSS data<br>- Manages the lifecycle of location updates and sets the mock location accordingly.</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/util/LocationLogEntry.kt'>LocationLogEntry.kt</a></b></td>
                                                                                <td>- Defines a data structure for storing location log entries, including timestamp, latitude, longitude, altitude, and accuracy<br>- This code file encapsulates essential information about a location at a specific time, contributing to the project's functionality for managing and logging GPS data.</td>
                                                                            </tr>
                                                                            </table>
                                                                        </blockquote>
                                                                    </details>
                                                                    <details>
                                                                        <summary><b>ui</b></summary>
                                                                        <blockquote>
                                                                            <details>
                                                                                <summary><b>navigation</b></summary>
                                                                                <blockquote>
                                                                                    <table>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/navigation/AppNavHost.kt'>AppNavHost.kt</a></b></td>
                                                                                        <td>- Defines the navigation flow for the app, connecting screens to actions based on user interactions<br>- Handles routing between main, create route, route list, settings, search, and favorite addresses screens<br>- Utilizes view models to manage data and navigation controller to control screen transitions.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/navigation/Routes.kt'>Routes.kt</a></b></td>
                                                                                        <td>- Define navigation routes for different screens in the app, such as main, settings, routes, favorites, create route, configure route, and search<br>- This centralizes route names for easy reference and consistency across the codebase, enhancing navigation management and user experience.</td>
                                                                                    </tr>
                                                                                    </table>
                                                                                </blockquote>
                                                                            </details>
                                                                            <details>
                                                                                <summary><b>screen</b></summary>
                                                                                <blockquote>
                                                                                    <table>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/screen/MainScreen.kt'>MainScreen.kt</a></b></td>
                                                                                        <td>- The MainScreen code file orchestrates the user interface for the GNSS Mocker app, displaying simulated GNSS data on a Google Map<br>- It integrates user interactions for creating routes, accessing saved routes, adjusting settings, and searching addresses<br>- The screen dynamically updates with location details and resolved addresses, enhancing the user experience.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/screen/SearchAddressScreen.kt'>SearchAddressScreen.kt</a></b></td>
                                                                                        <td>- Enables users to search for addresses, select a location, and retrieve its latitude, longitude, and name<br>- Utilizes Google Places API for autocomplete functionality<br>- Facilitates seamless integration with the app's UI, allowing users to easily navigate and select addresses.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/screen/SettingsScreen.kt'>SettingsScreen.kt</a></b></td>
                                                                                        <td>- Defines the UI layout for the Settings screen, allowing users to customize appearance, map settings, distance units, shortcuts, advanced options, and system preferences<br>- Utilizes Compose components to display and interact with settings data managed by the SettingsViewModel<br>- Enables users to modify various app configurations easily.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/screen/RouteListScreen.kt'>RouteListScreen.kt</a></b></td>
                                                                                        <td>- Displays a screen with saved routes, allowing users to simulate, fix, and rename routes<br>- The screen includes a list of routes with options to interact with each route, such as simulating, fixing, and renaming<br>- The user can also edit the name of a route using a dialog.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/screen/FavoriteAddressesScreen.kt'>FavoriteAddressesScreen.kt</a></b></td>
                                                                                        <td>- Displays a screen with a list of favorite addresses, allowing users to view and delete entries<br>- The screen includes a top app bar with a back button, a list of addresses with their coordinates, and a delete button for each entry<br>- The functionality is driven by the MainViewModel to manage favorite addresses.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/screen/FirstAccessScreen.kt'>FirstAccessScreen.kt</a></b></td>
                                                                                        <td>- Enables user to manage permissions and settings for the app during initial access<br>- Handles permission requests, displays necessary actions, and prompts user interactions<br>- Facilitates user acceptance of disclaimers and guides through essential setup steps<br>- Enhances user experience by ensuring smooth onboarding process and necessary configurations.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/screen/CreateRouteScreen.kt'>CreateRouteScreen.kt</a></b></td>
                                                                                        <td>- Enables users to create and save routes using a Composable screen<br>- Handles route point collection, name suggestion, and saving functionality through interactions with the MainViewModel<br>- Supports a seamless user experience by prompting for route name suggestions and displaying a save dialog when necessary.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/screen/FavoriteListScreen.kt'>FavoriteListScreen.kt</a></b></td>
                                                                                        <td>- Defines the UI screen for displaying a list of favorite locations with the ability to select and navigate back<br>- Displays location names, coordinates, and allows selection of locations<br>- Integrated with Material3 components for a modern UI experience.</td>
                                                                                    </tr>
                                                                                    </table>
                                                                                </blockquote>
                                                                            </details>
                                                                            <details>
                                                                                <summary><b>theme</b></summary>
                                                                                <blockquote>
                                                                                    <table>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/theme/Theme.kt'>Theme.kt</a></b></td>
                                                                                        <td>Defines a Composable function for the GNSSMocker theme, adapting color schemes based on system theme and Android version.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/theme/Type.kt'>Type.kt</a></b></td>
                                                                                        <td>- Define typography styles for a Compose UI theme, including bodyLarge with specified font, weight, size, line height, and letter spacing<br>- This file contributes to the project's UI theming by providing a starting set of Material typography styles to customize and enhance the visual design of the application.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/theme/Color.kt'>Color.kt</a></b></td>
                                                                                        <td>Define color constants for the UI theme in the project.</td>
                                                                                    </tr>
                                                                                    </table>
                                                                                </blockquote>
                                                                            </details>
                                                                            <details>
                                                                                <summary><b>viewmodel</b></summary>
                                                                                <blockquote>
                                                                                    <table>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/viewmodel/SettingsViewModel.kt'>SettingsViewModel.kt</a></b></td>
                                                                                        <td>- Manages application settings using AndroidViewModel, updating and resetting preferences via SettingsDataStore<br>- Provides a StateFlow of GnssSettings for observing changes.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/viewmodel/MainViewModel.kt'>MainViewModel.kt</a></b></td>
                                                                                        <td>- Manages route simulation, user preferences, and favorite locations within the Android application, facilitating route creation, simulation, and management<br>- Enables users to start, stop, and save routes, as well as interact with favorite locations and simulate routes based on specified settings.</td>
                                                                                    </tr>
                                                                                    </table>
                                                                                </blockquote>
                                                                            </details>
                                                                            <details>
                                                                                <summary><b>component</b></summary>
                                                                                <blockquote>
                                                                                    <table>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/component/EditRouteNameDialog.kt'>EditRouteNameDialog.kt</a></b></td>
                                                                                        <td>- Defines a Composable function for editing route names in the UI component<br>- It allows users to rename routes with a dialog that includes text fields for input, buttons for saving or canceling changes, and a title for clarity<br>- This component enhances user experience by providing a seamless way to update route names within the application.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/component/DisclaimerDialog.kt'>DisclaimerDialog.kt</a></b></td>
                                                                                        <td>- Implement a Composable DisclaimerDialog that displays a legal notice and a confirmation button<br>- This component is crucial for informing users about the app's intended use and ensuring compliance with Android system terms.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/component/SaveRouteDialog.kt'>SaveRouteDialog.kt</a></b></td>
                                                                                        <td>- Defines a Composable function for a SaveRouteDialog component in the UI, allowing users to save a route with a specified name<br>- The dialog includes options to confirm or dismiss the action, enhancing user interaction within the application's UI components.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/component/PreferenceGroup.kt'>PreferenceGroup.kt</a></b></td>
                                                                                        <td>- Define a Composable function for rendering a preference group with a title and content in a column layout<br>- The function sets up the layout structure and styling for displaying preferences within the UI component.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/component/PreferenceItem.kt'>PreferenceItem.kt</a></b></td>
                                                                                        <td>Enables rendering a dynamic dropdown menu for selecting options within the UI component, facilitating user interaction and customization.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/component/SwitchPreference.kt'>SwitchPreference.kt</a></b></td>
                                                                                        <td>- Defines a Composable function for a SwitchPreference UI component that displays a label and a switch with a callback for handling state changes<br>- This component enhances the user interface by providing a customizable switch option within the application's UI components.</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/main/java/br/com/renanalencar/gnssmocker/ui/component/RouteConfigurationDialog.kt'>RouteConfigurationDialog.kt</a></b></td>
                                                                                        <td>- Define a Composable function for configuring route simulation settings, including speed, repeat mode, and total distance display<br>- This function utilizes sliders, radio buttons, and text inputs to allow users to customize simulation parameters before starting the simulation.</td>
                                                                                    </tr>
                                                                                    </table>
                                                                                </blockquote>
                                                                            </details>
                                                                        </blockquote>
                                                                    </details>
                                                                </blockquote>
                                                            </details>
                                                        </blockquote>
                                                    </details>
                                                </blockquote>
                                            </details>
                                        </blockquote>
                                    </details>
                                </blockquote>
                            </details>
                        </blockquote>
                    </details>
                    <details>
                        <summary><b>androidTest</b></summary>
                        <blockquote>
                            <details>
                                <summary><b>java</b></summary>
                                <blockquote>
                                    <details>
                                        <summary><b>br</b></summary>
                                        <blockquote>
                                            <details>
                                                <summary><b>com</b></summary>
                                                <blockquote>
                                                    <details>
                                                        <summary><b>renanalencar</b></summary>
                                                        <blockquote>
                                                            <details>
                                                                <summary><b>gnssmocker</b></summary>
                                                                <blockquote>
                                                                    <table>
                                                                    <tr>
                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/androidTest/java/br/com/renanalencar/gnssmocker/ExampleInstrumentedTest.kt'>ExampleInstrumentedTest.kt</a></b></td>
                                                                        <td>- Verifies the app context on an Android device by running an instrumented test<br>- This test ensures that the correct package name is associated with the app under test<br>- The code is part of the Android test suite for the project, validating the app's behavior on real devices.</td>
                                                                    </tr>
                                                                    </table>
                                                                </blockquote>
                                                            </details>
                                                        </blockquote>
                                                    </details>
                                                </blockquote>
                                            </details>
                                        </blockquote>
                                    </details>
                                </blockquote>
                            </details>
                        </blockquote>
                    </details>
                    <details>
                        <summary><b>test</b></summary>
                        <blockquote>
                            <details>
                                <summary><b>java</b></summary>
                                <blockquote>
                                    <details>
                                        <summary><b>br</b></summary>
                                        <blockquote>
                                            <details>
                                                <summary><b>com</b></summary>
                                                <blockquote>
                                                    <details>
                                                        <summary><b>renanalencar</b></summary>
                                                        <blockquote>
                                                            <details>
                                                                <summary><b>gnssmocker</b></summary>
                                                                <blockquote>
                                                                    <table>
                                                                    <tr>
                                                                        <td><b><a href='https://github.com/renanalencar/gnssmocker-kotlin/blob/master/app/src/test/java/br/com/renanalencar/gnssmocker/ExampleUnitTest.kt'>ExampleUnitTest.kt</a></b></td>
                                                                        <td>Verifies correctness of addition operation in a local unit test for the GNSS Mocking application.</td>
                                                                    </tr>
                                                                    </table>
                                                                </blockquote>
                                                            </details>
                                                        </blockquote>
                                                    </details>
                                                </blockquote>
                                            </details>
                                        </blockquote>
                                    </details>
                                </blockquote>
                            </details>
                        </blockquote>
                    </details>
                </blockquote>
            </details>
        </blockquote>
    </details>
</details>

---
##  Getting Started

###  Prerequisites

Before getting started with gnssmocker-kotlin, ensure your runtime environment meets the following requirements:

- **Programming Language:** Kotlin
- **Package Manager:** Gradle


###  Installation

Install gnssmocker-kotlin using one of the following methods:

**Build from source:**

1. Clone the gnssmocker-kotlin repository:
```sh
❯ git clone https://github.com/renanalencar/gnssmocker-kotlin
```

2. Navigate to the project directory:
```sh
❯ cd gnssmocker-kotlin
```

3. Install the project dependencies:


**Using `gradle`** &nbsp; [<img align="center" src="https://img.shields.io/badge/Kotlin-0095D5.svg?style={badge_style}&logo=kotlin&logoColor=white" />](https://kotlinlang.org/)

```sh
❯ gradle build
```




###  Usage
Run gnssmocker-kotlin using the following command:
**Using `gradle`** &nbsp; [<img align="center" src="https://img.shields.io/badge/Kotlin-0095D5.svg?style={badge_style}&logo=kotlin&logoColor=white" />](https://kotlinlang.org/)

```sh
❯ gradle run
```


###  Testing
Run the test suite using the following command:
**Using `gradle`** &nbsp; [<img align="center" src="https://img.shields.io/badge/Kotlin-0095D5.svg?style={badge_style}&logo=kotlin&logoColor=white" />](https://kotlinlang.org/)

```sh
❯ gradle test
```


---
##  Project Roadmap

- [X] **`Task 1`**: <strike>Implement feature one.</strike>
- [ ] **`Task 2`**: Implement feature two.
- [ ] **`Task 3`**: Implement feature three.

---

##  Contributing

- **💬 [Join the Discussions](https://github.com/renanalencar/gnssmocker-kotlin/discussions)**: Share your insights, provide feedback, or ask questions.
- **🐛 [Report Issues](https://github.com/renanalencar/gnssmocker-kotlin/issues)**: Submit bugs found or log feature requests for the `gnssmocker-kotlin` project.
- **💡 [Submit Pull Requests](https://github.com/renanalencar/gnssmocker-kotlin/blob/main/CONTRIBUTING.md)**: Review open PRs, and submit your own PRs.

<details closed>
<summary>Contributing Guidelines</summary>

1. **Fork the Repository**: Start by forking the project repository to your github account.
2. **Clone Locally**: Clone the forked repository to your local machine using a git client.
   ```sh
   git clone https://github.com/renanalencar/gnssmocker-kotlin
   ```
3. **Create a New Branch**: Always work on a new branch, giving it a descriptive name.
   ```sh
   git checkout -b new-feature-x
   ```
4. **Make Your Changes**: Develop and test your changes locally.
5. **Commit Your Changes**: Commit with a clear message describing your updates.
   ```sh
   git commit -m 'Implemented new feature x.'
   ```
6. **Push to github**: Push the changes to your forked repository.
   ```sh
   git push origin new-feature-x
   ```
7. **Submit a Pull Request**: Create a PR against the original project repository. Clearly describe the changes and their motivations.
8. **Review**: Once your PR is reviewed and approved, it will be merged into the main branch. Congratulations on your contribution!
</details>

<details closed>
<summary>Contributor Graph</summary>
<br>
<p align="left">
   <a href="https://github.com{/renanalencar/gnssmocker-kotlin/}graphs/contributors">
      <img src="https://contrib.rocks/image?repo=renanalencar/gnssmocker-kotlin">
   </a>
</p>
</details>

---

##  License

This project is protected under the [SELECT-A-LICENSE](https://choosealicense.com/licenses) License. For more details, refer to the [LICENSE](https://choosealicense.com/licenses/) file.

---

##  Acknowledgments

- List any resources, contributors, inspiration, etc. here.

---
