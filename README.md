# Android-Game-Flappy-Bird

This project is an Android 2D game inspired by classic games like "Flappy Bird" and "Space Raiders." The main goal of this project was to design and implement an Android game using the Android Studio IDE.

## Table of Contents
- [Abstract](#abstract)
- [Introduction and Showcase](#introduction-and-showcase)
- [OOP Design](#oop-design)
- [Memory Usage and Speed Improvements](#memory-usage-and-speed-improvements)
- [Improvements and Extensions](#improvements-and-extensions)
- [Conclusion](#conclusion)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Abstract
This project involved creating a 2D game based on my experience playing games like "Flappy Bird" and "Space Raiders." The objective was to design and implement an Android game using Android Studio. Throughout this report, I provide evidence of how my game operates and showcase the design of the game itself. I also discuss the concept of each class and the objects and functions they contain. Lastly, I mention potential improvements, such as adding a high score section or a user creation section.

## Introduction and Showcase
The game features a main menu screen with a "Start" button and difficulty options ranging from "Easy" to "Hard." The game is displayed in landscape orientation. Once the game starts, the player controls a character called "Hero" and must save raccoons while avoiding enemies like birds and aliens, as well as obstacles inspired by "Flappy Bird." The player can collect coins throughout the game. The game ends when the player loses all their lives, and a screen is displayed showing the score, coins collected, and raccoons saved, along with a "Start" button to restart the game.

## OOP Design
The game is designed using an Object-Oriented Programming (OOP) paradigm. The abstract class `GameObject` is inherited by 8 classes, each inheriting the position method and the `getWidth()` and `getHeight()` methods. The `Hero` class inherits from `GameObject` and has a `setup()` function that allows the user to move the character up and down, demonstrating polymorphism. Most of these classes are called within the `GamePanel()` class, which is responsible for generating the background, assets, graphics, and sounds, and is an example of encapsulation. The `MainActivity` class starts the game and holds a relative layout for function buttons, while the `GameMenu()` class displays another screen for accessing other activities.

## Memory Usage and Speed Improvements
The game's speed is measured in the `MainThread` class by tracking the average frames per second (FPS). The target is to run at 30 FPS for optimal gameplay. Different frame patterns are considered, ranging from excellent to slow-paced scenarios. Multithreading is used to optimize memory consumption and improve performance. However, the changing background increases RAM usage and may cause lag in the game's latency.

## Improvements and Extensions
Potential improvements and extensions for the game include adding a user creation section, implementing an online high score using Firebase, and incorporating proper level-based settings. However, due to time constraints and challenges encountered, these features were not fully implemented in the current version of the game.

## Conclusion
Creating this Android game was a new experience, and not using the given framework allowed for further research and exploration of ideas. While there is room for significant enhancements, both in terms of expansions and minor details like the game's aesthetic, this project provided valuable learning opportunities in game development.

## Installation
To run the Android game, follow these steps:

1. Clone the repository: `git clone https://github.com/mo-ai-source/android-2d-game.git`
2. Open the project in Android Studio.
3. Build the project and resolve any dependencies.
4. Connect an Android device or use an emulator.
5. Run the app on the device or emulator.

## Usage
1. Launch the game on your Android device or emulator.
2. On the main menu, select the difficulty level and press the "Start" button.
3. Control the character using touch gestures to avoid obstacles and enemies while collecting coins and saving raccoons.
4. The game ends when you lose all your lives. Your score, coins collected, and raccoons saved will be displayed.
5. Press the "Start" button to restart the game.

## Contributing
Contributions to the Android 2D game project are welcome. If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License
This project is licensed under the [MIT License](LICENSE).
