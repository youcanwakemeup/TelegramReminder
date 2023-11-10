# Telegram Reminder

## Overview
The Telegram Reminder project is a Java-based application designed to help users set and receive reminders through the popular messaging platform Telegram. With this application, users can easily schedule reminders for various tasks, events, or important activities directly from their Telegram chat.

## Features

1. **Telegram Integration:** Seamless integration with Telegram for setting and receiving reminders through a user-friendly chat interface.

2. **Flexible Scheduling:** Users can schedule reminders for specific dates and times, ensuring they never miss important events.

3. **Reminder Notifications:** Receive timely notifications on Telegram to alert users about upcoming reminders.

4. **User-Friendly Commands:** Intuitive commands make it easy for users to set, view, and manage their reminders.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) installed
- Telegram account and API token

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/yourusername/telegram-reminder.git
    ```

2. Navigate to the project directory:

    ```bash
    cd telegram-reminder
    ```

3. Configure the Telegram API token in the `config.properties` file:

    ```properties
    telegram.api.token=YOUR_TELEGRAM_API_TOKEN
    ```

4. Build the project:

    ```bash
    javac -cp .:./lib/telegram-bot-api-4.9.jar -d out src/com/example/Main.java
    ```

5. Run the application:

    ```bash
    java -cp .:./lib/telegram-bot-api-4.9.jar:./out com.example.Main
    ```

