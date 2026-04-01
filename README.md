# 💳 Payment Service — Тестовое задание Alma Telecom

> **Выполнил:** Сейтнуров Нурдияр  
> **Компания:** Alma Telecommunications

---

## 🛠 Технологический стек

| Технология | Версия |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.5 |
| Spring Data JPA | — |
| PostgreSQL | 17 |
| Docker & Docker Compose | — |
| MapStruct | — |
| Lombok | — |
| Spring Validation | — |
| Springdoc OpenAPI (Swagger) | — |

---

## 🚀 Быстрый старт

> ⚡ **Никакой дополнительной настройки не требуется!**  
> Просто скачайте проект и выполните одну команду.

```bash
docker compose up --build
```

или (для старых версий Docker):

```bash
docker-compose up --build
```

### Запущенные контейнеры

| Контейнер | Описание | Порт |
|---|---|---|
| `db` | PostgreSQL 17 | `5432` |
| `app` | Spring Boot сервис | `8080` |

### Переменные окружения (Docker Compose)

| Переменная | Значение |
|---|---|
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://db:5432/almatestdb` |
| `SPRING_DATASOURCE_USERNAME` | `alma` |
| `SPRING_DATASOURCE_PASSWORD` | `alma` |
| `SPRING_JPA_HIBERNATE_DDL_AUTO` | `update` |

---

## 🗂 API

Все эндпоинты доступны по базовому адресу: **`http://localhost:8080/api`**

### 👤 Клиенты

| Метод | URL | Описание |
|---|---|---|
| `POST` | `/clients` | Создать клиента |
| `GET` | `/clients/{clientId}` | Получить клиента по ID |
| `GET` | `/clients/{clientId}/payments` | Получить все платежи клиента |

**Создание клиента:**
```http
POST /api/clients
Content-Type: application/json

{
    "name": "John Doe"
}
```

---

### 💰 Платежи

| Метод | URL | Описание |
|---|---|---|
| `POST` | `/payments` | Создать платёж |
| `GET` | `/payments/{paymentId}` | Получить платёж с данными клиента |
| `POST` | `/payments/{paymentId}/confirm` | Подтвердить платёж |
| `POST` | `/payments/{paymentId}/cancel` | Отменить платёж |

**Создание платежа:**
```http
POST /api/payments
Content-Type: application/json

{
    "amount": 1000,
    "currency": "KZT",
    "description": "Payment for service",
    "clientId": 1
}
```

**Подтверждение платежа:**
```http
POST /api/payments/1/confirm
```

**Отмена платежа:**
```http
POST /api/payments/1/cancel
```

**Получение платежей клиента:**
```http
GET /api/clients/1/payments
```

---

## 📊 Статусы платежей

```
PENDING    → Ожидает обработки
CONFIRMED  → Подтверждён
CANCELED   → Отменён
```

Переход статусов:

```
PENDING ──→ CONFIRMED
PENDING ──→ CANCELED
```

> ⚠️ Повторно подтвердить или отменить уже обработанный платёж **нельзя** — будет выброшено исключение `PaymentAlreadyProcessedException`.

---

## ⚠️ Обработка исключений

| Исключение | Описание | HTTP-статус |
|---|---|---|
| `ClientNotFoundException` | Клиент не найден | `404` |
| `PaymentNotFoundException` | Платёж не найден | `404` |
| `PaymentAlreadyProcessedException` | Платёж уже подтверждён или отменён | `409` |
| Все остальные исключения | Внутренняя ошибка сервера | `500` |

---

## ⚡ Swagger UI

После запуска сервиса документация API доступна по адресу:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

---

## 🐳 Dockerfile

Многоэтапная сборка через Maven:

```dockerfile
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
```

---

## 📬 Postman коллекция

Для удобного тестирования всех эндпоинтов импортируйте готовую коллекцию:

```
AlmaTestPaymentService.postman_collection.json
```

Коллекция содержит примеры запросов для всех операций с клиентами и платежами.

---

## 📁 Структура проекта

```
src/
├── main/
│   ├── java/
│   │   └── ...
│   │       ├── client/     # Client все что связанно с ними
│   │       ├── common/        # основные классы (Exceptions)
│   │       │   ├── dto        # DTO классы 
│   │       │   └── exception    # Кастомные исключения
│   │       └── payment/     # Payment все что связанно с ними
│   │           ├── controller     # REST контроллеры
│   │           ├── dto        # DTO классы (MapStruct)
│   │           ├── entity     # JPA сущности
│   │           ├── service    # Бизнес-логика
│   │           └── repository    # JPA репозитории
│   └── resources/
│       └── application.yml
docker-compose.yml
Dockerfile
pom.xml
```
