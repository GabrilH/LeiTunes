# LeiTunes

## Introduction
LeiTunes is a Java music management and playback app developed as an Eclipse Project during Object-Oriented Programming course. The application allows users to manage their music library, create playlists, and play songs. It features a graphical user interface (GUI) for easy interaction.

## Features
- View and manage music library
- Create and manage playlists
- Play songs from the library or playlists
- Rate songs and track play counts

## Technologies and Requirements
- Java Development Kit (JDK)
- Eclipse IDE (recommended for development)
- SWT (Standard Widget Toolkit)
- MP3agic (MP3 metadata library)
- JLayer (MP3 player library)

## Usage
1. Open the project in Eclipse IDE.
2. Add the required libraries:
    - Download and add `swt.jar` to the `lib` folder.
    - Download and add `mp3agic-0.9.1.jar` to the `lib` folder.
    - Download and add `jl1.0.1.jar` to the `lib` folder.
3. Place your songs in the `songs` folder.
4. Build the project.
5. Run the `GUIClient` class to start the application.
6. Optionally, run the `SimpleClient` class to automatically execute some application functions. Note that `SimpleClient` expects 10 songs to be present in the `songs` folder.

## Testing
The project includes JUnit tests to ensure the functionality of core components. The tests can be found in the `tests` directory and can be run using the Eclipse IDE or any other compatible Java IDE.

## Contributors
- Gabriel Henriques (fc58182)
- Guilherme Sousa (fc58170)
- Antónia Lopes (Lecturer)