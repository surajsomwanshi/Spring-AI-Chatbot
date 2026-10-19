# Spring AI Chatbot using Spring Boot and Ollama

A simple AI chatbot REST API built with **Java 21, Spring Boot, Spring AI, and Ollama**. This project demonstrates how to integrate a locally running Large Language Model (LLM) into a Java backend application and expose AI-powered responses through REST APIs.

The goal of this project is to learn Spring AI fundamentals and gradually extend the application with features such as conversation history, prompt templates, Retrieval-Augmented Generation (RAG), and other AI capabilities.

## 📌 Project Overview

This application accepts a user message through a REST API, sends the message to a locally hosted AI model using Spring AI, and returns the generated response as JSON.

Unlike applications that rely on cloud-based AI APIs, this project uses Ollama to run the language model locally.

### Key Features

- REST API integration using Spring Boot.
- Local LLM integration using Ollama.
- Spring AI `ChatClient` for interacting with the AI model.
- Request and response DTOs for structured API communication.
- Separation of concerns using controller and service layers.
- Request validation using Jakarta Bean Validation.
- Configurable AI model and generation settings.
- No external cloud AI API key required for local inference.

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming language |
| Spring Boot | Backend application framework |
| Spring AI | AI model integration |
| Ollama | Runs the LLM locally |
| Qwen 2.5 (3B) | Language model |
| Maven | Dependency management and build |
| REST API | Client-server communication |
| Postman | API testing |
| IntelliJ IDEA | Development environment |

## 🏗️ Architecture

The application follows a layered architecture to keep API handling, business logic, and AI integration separate.

```text
              User / Postman
                     |
                     v
              HTTP POST Request
                     |
                     v
              ChatController
                     |
                     v
               ChatService
                     |
                     v
           Spring AI ChatClient
                     |
                     v
               Ollama API
             localhost:11434
                     |
                     v
              Qwen 2.5 (3B)
                     |
                     v
             Generated Response
                     |
                     v
              ChatController
                     |
                     v
             JSON Response
```

### Request Flow

1. The client sends a message to the chatbot REST endpoint.
2. `ChatController` receives the request and validates the input.
3. `ChatService` handles the chatbot interaction.
4. Spring AI's `ChatClient` prepares and sends the prompt to Ollama.
5. Ollama runs the Qwen 2.5 (3B) model and generates a response.
6. The response travels back through the service and controller layers.
7. The API returns the result as JSON.

## 📂 Project Structure

```text
spring-ai-chatbot/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/springaichatbot/
│   │   │       ├── config/
│   │   │       │   └── AIConfig.java
│   │   │       ├── controller/
│   │   │       │   └── ChatController.java
│   │   │       ├── service/
│   │   │       │   └── ChatService.java
│   │   │       ├── dto/
│   │   │       │   ├── ChatRequest.java
│   │   │       │   └── ChatResponse.java
│   │   │       └── SpringAiChatbotApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── .gitignore
└── README.md
```

## ⚙️ Prerequisites

Install the following before running the application:

- Java Development Kit (JDK) 21.
- Maven, or use the Maven Wrapper included in the project.
- Ollama.
- IntelliJ IDEA or another Java IDE.
- Postman or an equivalent REST client.

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/spring-ai-chatbot.git
cd spring-ai-chatbot
```

Replace `YOUR_USERNAME` with your GitHub username and adjust the repository name if needed.

### 2. Download the AI Model

Install Ollama from [https://ollama.com/](https://ollama.com/).

Download the Qwen 2.5 (3B) model:

```bash
ollama pull qwen2.5:3b
```

Verify that the model is available:

```bash
ollama list
```

### 3. Test Ollama Independently

Run the model directly:

```bash
ollama run qwen2.5:3b
```

Enter a prompt such as:

```text
Explain Java in two sentences.
```

Ollama should generate a response. Exit the interactive session with `/bye` or `Ctrl + D`.

### 4. Configure the Application

Configure `src/main/resources/application.properties`:

```properties
spring.application.name=SpringAIChatbot

server.port=8080

# ollama configuration
spring.ai.ollama.base-url=http://localhost:11434
spring.ai.ollama.chat.model=qwen2.5:3b
spring.ai.ollama.chat.temperature=0.7
spring.ai.ollama.chat.num-predict=150
```

**Configuration details:**

- `server.port`: Port on which the Spring Boot API runs.
- `spring.ai.ollama.base-url`: Ollama server address.
- `spring.ai.ollama.chat.options.model`: Model used for generating responses.
- `temperature`: Controls response variability.
- `num-predict`: Limits the number of generated tokens.

Ollama and Spring Boot run as separate processes. The application expects Ollama to be accessible at the configured URL.

### 5. Run the Spring Boot Application

Start the application from IntelliJ IDEA by running the main application class.

Alternatively, use Maven:

```bash
mvn spring-boot:run
```


## 🔌 API Documentation

### Generate an AI Response

**Endpoint**

```http
POST /api/chat
```

**Full URL**

```text
http://localhost:8080/api/chat
```

**Request Headers**

```http
Content-Type: application/json
```

**Request Body**

```json
{
  "message": "Explain Spring AI in two sentences."
}
```

**Successful Response — Example**

```json
{
  "reply": "Spring AI is a component within the Spring Framework that provides machine learning capabilities, allowing developers to integrate machine learning algorithms and models into their applications with minimal code and without the need to manage complex infrastructure."
}
```


**Expected Status:** `200 OK`

### Test Using Postman

1. Open Postman.
2. Create a new request.
3. Select `POST`.
4. Enter `http://localhost:8080/api/chat`.
5. Open **Body → raw → JSON**.
6. Enter the request JSON shown above.
7. Click **Send**.
8. Verify the JSON response.

## 🔒 Privacy and Security

This project uses a locally hosted language model, so prompts are sent to the configured Ollama server rather than a third-party cloud AI API by default.

However, local execution alone does not guarantee complete privacy. Data handling depends on the application's configuration, logging, and any external services added in the future.

Do not commit credentials, private information, or sensitive production data to the repository.


**Author:** Suraj Somwanshi

**Project:** Spring AI Chatbot using Spring Boot and Ollama
