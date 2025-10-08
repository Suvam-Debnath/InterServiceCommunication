# 🧩 Spring Boot Microservice Communication 

## 📘 Overview

This project demonstrates **inter-service communication** between two Spring Boot microservices — a **Producer Service** and a **Consumer Service** — using multiple approaches.
Both services are registered under a **Eureka Server** for service discovery.

It’s a learning-oriented project designed to show different ways microservices can communicate in a distributed architecture using Spring Cloud tools.

---

## 🏗️ Architecture

```
+-------------------+           +-------------------+
|   Eureka Server   | <-------> |   Producer Service|
+-------------------+           +-------------------+
          ^
          |
          v
+-------------------+
|  Consumer Service |
+-------------------+
```

* **Eureka Server** → Service registry for Producer and Consumer
* **Producer Service** → Exposes REST API endpoints
* **Consumer Service** → Calls Producer APIs using 5 different communication techniques

---

## ⚙️ Technologies Used

* **Java 21**
* **Spring Boot 3+**
* **Spring Cloud Netflix Eureka**
* **Spring Web**
* **Spring Boot Actuator**
* **Spring Cloud OpenFeign**
* **Spring WebClient**
* **RestTemplate**
* **Http Interface (Spring 6 feature)**
* **RestClient (Spring Boot 3.2+)**
* **Maven**

---

## 🧩 Modules

### 1️⃣ Eureka Server

Acts as a **Service Registry** for Producer and Consumer microservices.

**Run Command:**

```bash
mvn spring-boot:run
```

**Access UI:**
[http://localhost:8761/](http://localhost:8761/)

---

### 2️⃣ Producer Service

A simple REST-based service that provides dummy data through API endpoints.

**Example Endpoint:**

```bash
GET /producer/message
Response: "Hello from Producer Service!"
```

**Application.yml**

```yaml
server:
  port: 8081
spring:
  application:
    name: producer
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

---

### 3️⃣ Consumer Service

Consumes the Producer APIs using **five different methods**:

#### a. OpenFeign Client

Declarative REST client that simplifies HTTP calls using interfaces.

```java
@FeignClient(name = "producer")
public interface ProducerFeignClient {
    @GetMapping("/producer/message")
    String getMessage();
}
```

#### b. RestTemplate

Synchronous HTTP client.

```java
RestTemplate restTemplate = new RestTemplate();
String response = restTemplate.getForObject("http://producer/producer/message", String.class);
```

#### c. WebClient

Asynchronous and non-blocking reactive HTTP client.

```java
WebClient webClient = WebClient.builder().baseUrl("http://producer").build();
String response = webClient.get().uri("/producer/message").retrieve().bodyToMono(String.class).block();
```

#### d. Http Interface (Spring 6)

Declarative HTTP client similar to Feign but native to Spring Framework 6+.

```java
@HttpExchange("http://producer")
public interface ProducerHttpClient {
    @GetExchange("/producer/message")
    String getMessage();
}
```

#### e. RestClient (Spring Boot 3.2+)

Modern replacement for RestTemplate.

```java
RestClient restClient = RestClient.create("http://producer");
String response = restClient.get().uri("/producer/message").retrieve().body(String.class);
```

---



## 📊 Comparison Table

| Method             | Type                 | Library          | Blocking | Best For                      |
| ------------------ | -------------------- | ---------------- | -------- | ----------------------------- |
| **Feign Client**   | Declarative          | Spring Cloud     | Yes      | Simplified REST calls         |
| **RestTemplate**   | Imperative           | Spring Web       | Yes      | Legacy projects               |
| **WebClient**      | Reactive             | Spring WebFlux   | No       | Async/Reactive apps           |
| **Http Interface** | Declarative (Native) | Spring 6+        | Yes      | Lightweight Feign alternative |
| **RestClient**     | Modern (Imperative)  | Spring Boot 3.2+ | Yes      | Replacement for RestTemplate  |

---


## 👨‍💻 Author

**Suvam Debnath**

---

⭐ *If you found this helpful, give it a star on GitHub!*
