# Spring AI Chatbot using Spring Boot and Ollama

A beginner-friendly AI chatbot built using Java, Spring Boot, Spring AI, and Ollama. This project demonstrates how to integrate a locally running Large Language Model (LLM) into a REST API and progressively implement core Spring AI features.

The goal of this project is to learn Spring AI fundamentals and gradually extend the application with features such as conversation history, prompt templates, Retrieval-Augmented Generation (RAG), and other AI capabilities.

## 📌 Project Overview

This application accepts a user message through a REST API, sends the message to a locally hosted AI model using Spring AI, and returns the generated response as JSON.

Unlike applications that rely on cloud-based AI APIs, this project uses Ollama to run the language model locally.

## ✨ Features

### 1. AI Chat REST API
- Exposes a REST endpoint to interact with the AI model.
- Accepts user messages through JSON requests.
- Returns AI-generated responses in JSON format.

### 2. System Prompt Configuration
- Configures default AI behavior using Spring AI's `ChatClient`.
- Provides a Java interview mentor persona.
- Guides the model to explain concepts using definitions, practical Java examples, complexity analysis where relevant, and interview tips.

### 3. Dynamic Interview Prompts
- Uses Spring AI's `PromptTemplate` to create dynamic prompts.
- Accepts a topic and difficulty level.
- Generates structured, interview-oriented answers for Java concepts.

### 4. Conversation Memory
- Implements conversation history using Spring AI's `ChatMemory`.
- Uses a conversation ID to associate messages with a specific conversation.
- Allows the AI to use previous messages when answering follow-up questions.
- Keeps different conversations logically separate using different conversation IDs.

**Note:** The current in-memory implementation does not persist conversation history across application restarts.

## 🔮 Future Enhancements

- Add request validation and centralized exception handling.
- Implement persistent conversation history using a database.
- Add Retrieval-Augmented Generation (RAG) for document-based question answering.
- Implement structured AI responses mapped to Java objects.
- Explore AI tool calling.
- Add unit and integration tests.
- Dockerize the application.


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
                    ├── ChatResponse.java
                    ├── InterviewPromptRequest.java
                    └── ConversationChatRequest.java
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

#### 1. General Chat

**Endpoint:** `POST /api/chat`

**Request body:**

```json
{
  "message": "Explain HashMap in Java"
}
```

### 2. Dynamic Interview Prompt

**Endpoint:** `POST /api/chat/interview`

**Request body:**

```json
{
  "topic": "HashMap",
  "difficulty": "Intermediate"
}
```

### 3. Conversation Memory

**Endpoint:** `POST /api/chat/conversation`

**Request body:**

```json
{
  "conversationId": "java-chat-001",
  "message": "My favorite programming language is Java. Remember this."
}
```

To continue the same conversation, send another request using the same `conversationId`.

**Example follow-up:**

```json
{
  "conversationId": "java-chat-001",
  "message": "What is my favorite programming language?"
}
```

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
