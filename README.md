PaymentServiceTestTaskAlmaTelecom

Выполнил тестовое задание: Сейтнуров Нурдияр
Тестовое задание от компании Alma Telecommunications

Технологии
Java 21
Spring Boot 4.0.5
Spring Data JPA
PostgreSQL 17
Docker & Docker Compose
MapStruct
Lombok
Spring Validation
Springdoc OpenAPI (Swagger)

Запуск проекта
(НИЧЕГО НАСТРАИВАТЬ НЕ НАДО! просто скачиваете проект к себе и прописываете "docker compose up --build" ИЛИ "docker-compose up --build")
1. Запуск через Docker Compose

Построить Docker образы и запустить сервис:

docker-compose up --build
Контейнеры:
db: PostgreSQL 17, порт 5432
app: Spring Boot сервис, порт 8080
Переменные окружения (для Docker Compose):
SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/almatestdb
SPRING_DATASOURCE_USERNAME: alma
SPRING_DATASOURCE_PASSWORD: alma
SPRING_JPA_HIBERNATE_DDL_AUTO: update
🗂 API

Все эндпоинты доступны на http://localhost:8080/api.

Клиенты
Метод	URL	Описание
POST	/clients	Создать клиента
GET	/clients/{clientId}	Получить клиента по ID
GET	/clients/{clientId}/payments	Получить все платежи клиента
Пример запроса создания клиента
POST /api/clients
{
    "name": "John Doe"
}
Платежи
Метод	URL	Описание
POST	/payments	Создать платеж
GET	/payments/{paymentId}	Получить платеж с клиентом
POST	/payments/{paymentId}/confirm	Подтвердить платеж
POST	/payments/{paymentId}/cancel	Отменить платеж
Пример создания платежа
POST /api/payments
{
    "amount": 1000,
    "currency": "KZT",
    "description": "Payment for service",
    "clientId": 1
}
Пример подтверждения платежа
POST /api/payments/1/confirm
Пример отмены платежа
POST /api/payments/1/cancel
Пример получения платежей клиента
GET /api/clients/1/payments
⚡ Swagger

После запуска сервиса Swagger UI доступен по адресу:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui/index.html)
📦 Dockerfile
Сборка через Maven:
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
📌 Важные моменты
Статусы платежей:
PENDING — ожидает обработки
CONFIRMED — подтвержден
CANCELED — отменен
Исключения:
ClientNotFoundException — клиент не найден
PaymentNotFoundException — платеж не найден
PaymentAlreadyProcessedException — платеж уже подтвержден или отменен
Все остальные исключения ловятся с кодом 500

Postman коллекция
Импортируй AlmaTestPaymentService.postman_collection.json для тестирования всех эндпоинтов с примерами запросов.

<img width="1024" height="1536" alt="Payment service API documentation" src="https://github.com/user-attachments/assets/25f0f399-42f2-4dc2-b352-1e8ef949f18c" />

