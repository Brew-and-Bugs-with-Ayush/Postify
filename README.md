# 📝 Postify

---

## 🪶 Badges

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql)
![JWT](https://img.shields.io/badge/JWT-Security-red?logo=jsonwebtokens)
![React](https://img.shields.io/badge/React-18-blue?logo=react)
![MapStruct](https://img.shields.io/badge/MapStruct-Mapper-yellow)
![License](https://img.shields.io/badge/License-MIT-green)

---

> A secure and simple **blog application** where users can **create, update, delete, and view** their own posts — and no one else’s!  
> Built with **Spring Boot**, **React**, **JWT**, and **PostgreSQL**.

---

## 🛡️ Overview

**Postify** is a full-stack blog platform focusing on **security and simplicity**.  
Every user can manage only their own posts using **JWT authentication**.  
This ensures that no other user can alter, update, or delete your posts.

---

## 🧰 Tech Stack

| Layer | Technologies |
|-------|---------------|
| 💻 Backend | Java 21 · Spring Boot · Spring Security (JWT) · PostgreSQL · MapStruct · Hibernate/JPA · Validation API |
| 🌐 Frontend | React 18 · Axios · React Router |
| ⚙️ Tools | Maven · Git/GitHub · Postman |

---

## ⚙️ Features

✅ User Registration & Login (JWT Authentication)  
✅ Only post owner can edit or delete their posts  
✅ PostgreSQL as a persistent database  
✅ MapStruct for clean DTO conversion  
✅ Spring Validation for input handling  
✅ RESTful API architecture  
✅ React frontend with clean UI  

---

## 🧩 Architecture

```text
Frontend (React)
      ↓
Backend (Spring Boot REST API)
      ↓
PostgreSQL (Database)
      ↓
JWT Authentication Layer (Secures all user operations)
```
---

🏗️ Project Setup

🔹 Backend

# Clone repository
```bash
git clone https://github.com/yourusername/Postify.git
cd Postify/backend
```

# Configure application.properties
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/postify
spring.datasource.username=postgres
spring.datasource.password=yourpassword
jwt.secret=your_secret_key
```

# Run the application
```bash
mvn spring-boot:run
```

🔹 Frontend
```bash
cd ../frontend
npm install
npm start
```

App will be available at 👉 http://localhost:5137

---

🙌 Contributing

Contributions, issues, and feature requests are welcome!
Feel free to fork the repository and submit a pull request.

---
🧑‍💻 Author

Ayush Gupta
💼 GitHub: https://github.com/Brew-and-Bugs-with-Ayush

🌐 LinkedIn: https://www.linkedin.com/in/ayush-gupta004

📧 Email: ayushgupta.Codex@gmail.com

---

📝 License

This project is licensed under the MIT License — feel free to use, learn, and build upon it.

---

🌟 Support

If you find this project helpful, please ⭐ star the repository — it helps others discover it and motivates continued development!

“Code. Build. Flow. — That’s Postify.” 🚀
