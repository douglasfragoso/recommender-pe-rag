# 🤖 Multi AI Assistant with RAG

## About the Project

AI assistant system with multiple providers (DeepSeek, Gemini, Ollama) integrating **RAG (Retrieval-Augmented Generation)** and **real-time streaming**. The application enables contextual conversations based on loaded documents, utilizing advanced natural language processing techniques.

## Key Features

### Multi-Model
- **DeepSeek**: OpenAI-compatible API
- **Google Gemini**: Advanced Google model
- **Ollama**: Local models (Llama 3.1 8B)

### Advanced RAG System
- **Document Splitting**: Optimized chunks (500 characters + 50 overlap)
- **Local Embedding**: BGE Small EN V1.5 Quantized model (ONNX)
- **Semantic Search**: Vector similarity retrieval
- **In-Memory Store**: Efficient embedding storage
- **Chat Persistence**: Chat storage in relational database

### Technologies
- **Java 25** + **Spring Boot 3.5.7**
- **LangChain4j** - AI Framework
- **WebFlux** - Reactive streaming
- **Swagger/OpenAPI** - Interactive documentation
- **Actuator** - Monitoring and metrics
- **MySQL** - Relational Database

## Interactive Documentation

Access the Swagger documentation at: http://localhost:8080/swagger-ui.html

## Tests

### Endpoint - Ollama
```sh
 curl -X POST http://localhost:8080/ai/chat/ollama -H "Content-Type: application/json" -d "{\"message\": \"O que voce sabe sobre o sistema de recomendação?\"}"
```

### Endpoint - Gemini
```sh
curl -X POST http://localhost:8080/ai/chat/gemini -H "Content-Type: application/json" -d "{\"message\": \"O que voce sabe sobre o sistema de recomendação?\"}"
```    
### Endpoint - DeepSeek
```sh
curl -X POST http://localhost:8080/ai/chat/deepseek -H "Content-Type: application/json" -d "{\"message\": \"O que voce sabe sobre o sistema de recomendação?\"}"
``` 
### Endpoint - Check system health
```sh
curl "http://localhost:8080/ai/health"
``` 
### Check listeners status (RAG, Chat, Embedding)
```sh
curl "http://localhost:8080/ai/listeners/status"
``` 
### Endpoint - Basic streaming test
```sh
curl -N "http://localhost:8080/ai/test/stream"
``` 
Important: Ensure the [Frontend Service](https://github.com/douglasfragoso/recommender-pe-rag-react) is running after starting the backend.
