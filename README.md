# HTTP Server

[![CI](https://github.com/ikarabulut/EchoServer/actions/workflows/gradle.yml/badge.svg)](https://github.com/ikarabulut/EchoServer/actions/workflows/gradle.yml)

[![Coverage](.github/badges/jacoco.svg)](https://github.com/ikarabulut/HttpServer/workflows/build.yml)


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

## Functional Requirements

---
A user should be able to interact with the HTTP server as follows:

When a client sends a properly formatted request to the server, the server sends an appropriate response back to the client.
A client can send different HTTP requests to the server and get the appropriate response back each time.
Different clients can send messages to server and get back their proper responses.
The server should be able to handle 200, 300, and 400-level responses. Not every response code needs to be complete, but there should be a few representative response codes implemented for each level.

## Current Features

---
<ul>
<li>🟢 Simple HEAD request
</li>
<li>🔴 Simple GET request
</li>
<li>🔴 Simple OPTIONS request
</li>
<li>🔴 Simple POST request
</li>
<li>🔴 Redirect feature
</li>
<li>🔴 Method not found feature
</li>
<li>🔴 Page not found feature
</li>
</ul>


## Installation

---
1. Clone the repo to your machine, and `cd` into the directory
```
git clone https://github.com/ikarabulut/HttpServer.git
cd HttpServer
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
