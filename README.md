# HTTP Server

[![CI](https://github.com/ikarabulut/HttpServer/actions/workflows/build.yml/badge.svg)](https://github.com/ikarabulut/HttpServer/actions/workflows/build.yml)
[![CI](https://github.com/ikarabulut/HttpServer/actions/workflows/acceptanceTests.yml/badge.svg)](https://github.com/ikarabulut/HttpServer/actions/workflows/acceptanceTests.yml)
[![codecov](https://codecov.io/gh/ikarabulut/HttpServer/branch/codecov-badge/graph/badge.svg?token=V2G4O5CC1W)](https://codecov.io/gh/ikarabulut/HttpServer)
---
This project is built in [Java (OpenJDK 18)](https://www.oracle.com/java/technologies/downloads/) using [Gradle](https://docs.gradle.org/current/userguide/userguide.html).

---
## Requirements
- **Java**
  - To install Java locally you can use [Homebrew](https://stackoverflow.com/questions/65601196/how-to-brew-install-java). Which will install OpenJDK and the latest stable version of java.
    - Execute `brew install java` in your Command Line
  - If you do not want to use homebrew, please follow the [Java docs](https://www.java.com/en/download/manual.jsp)
  - After install you can run `java --version` to confirm proper installation
- **Gradle**
  - Follow these [Gradle doc instructions](https://gradle.org/install/)
  
- **[direnv](https://direnv.net/)**
  - Used for local environment variables
  - To install using homebrew run `brew install direnv` in your terminal
  - Follow these instructions to hook direnv to your shell https://direnv.net/docs/hook.html
    - For ZSH add the following line to your .zshrc file
      - `eval "$(direnv hook zsh)"`


## Functional Requirements

---
**A user should be able to interact with the HTTP server as follows:**

- When a client sends a properly formatted request to the server, the server sends an appropriate response back to the client.
- A client can send different HTTP requests to the server and get the appropriate response back each time.
- Different clients can send messages to server and get back their proper responses.
- The server should be able to handle 200, 300, and 400-level responses. Not every response code needs to be complete, but there should be a few representative response codes implemented for each level.

## Current Features

---
🟢 Simple HEAD request

🔴 Simple GET request

🔴 Simple OPTIONS request

🔴 Simple POST request

🔴 Redirect feature

🔴 Method not found feature

🔴 Page not found feature

## Installation

---
1. Clone the repo to your machine, and `cd` into the directory, run the `direnv allow` to load the local env variables using direnv
```
git clone https://github.com/ikarabulut/HttpServer.git
cd HttpServer
direnv allow
```
2. Build the App
```
gradle build
```

## To run the server

---
1. While in the project root directory execute `gradle run`
2. Open a new terminal window
3. Use cURL or Postman to pass a request
4. To Exit the server: ctrl+C

## Tests

---
- Tests use Junit5 & Mockito
- Test Reports are generated using Jacoco
- To Run the tests enter `gradle test` in your terminal
- The generated Jacoco test reports will be located in `build/reports/jacoco/test/`
