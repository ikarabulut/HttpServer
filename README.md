# Echo Server

[![CI](https://github.com/ikarabulut/EchoServer/actions/workflows/gradle.yml/badge.svg)](https://github.com/ikarabulut/EchoServer/actions/workflows/gradle.yml)

---
This project is built in [Java (OpenJDK 18)](https://www.oracle.com/java/technologies/downloads/) using [Gradle](https://docs.gradle.org/current/userguide/userguide.html).

(Updated 5/24/22) -> This is a current WIP

---
## Requirements
- **Java**
  - To install Java locally you can use [Homebrew](https://stackoverflow.com/questions/65601196/how-to-brew-install-java). Which will install OpenJDK and the latest stable version of java.
    - Execute `brew install java` in your Command Line
  - If you do not want to use homebrew, please follow the [Java docs](https://www.java.com/en/download/manual.jsp)
  - After install you can run `java --version` to confirm proper installation
- **Gradle**
  - Follow these [Gradle doc instructions](https://gradle.org/install/)

## Functional Requirements

---
A user should be able to interact with the echo server as follows:

- When a client sends a message to the server, the server sends a response back to the client containing the original message.
- A client can send multiple messages to the server and get the echoed response back each time.
- Multiple clients can send messages to server and get back their proper responses.

## Installation

---
1. Clone the repo to your machine, and `cd` into the directory
```
git clone https://github.com/ikarabulut/EchoServer.git
cd EchoServer
```
2. Build the App
```
gradle build
```

## To run the server

---
1. While in the project root directory execute `gradle run`
2. Open a new terminal window
3. Within the new window execute `nc localhost 5000`
4. An empty prompt will appear, whatever you type will be echoed after you press `enter`
