# 🚀 Spring Boot + Kinde Authentication

This project uses [Spring Boot](https://spring.io/projects/spring-boot) and [Kinde](https://kinde.com/) for user authentication and identity management.

## ✅ Requirements

- Java 17+
- Maven
- A Kinde account
- A `.env` file containing your credentials

---

## 🔐 Kinde Configuration

1. Sign up at [Kinde](https://kinde.com) if you haven't already.
2. Create a new **application** in your Kinde dashboard.
3. Obtain the following credentials:
    - `KINDE_CLIENT_ID`
    - `KINDE_CLIENT_SECRET`

---

## 📁 `.env` File

Create a `.env` file in the root of the project and add the following:

```env
KINDE_CLIENT_ID=your-client-id
KINDE_CLIENT_SECRET=your-client-secret
