Student CRUD – Spring Boot + JPA + H2

Petit projet d’entraînement pour pratiquer un CRUD Student avec Spring Boot 3, Spring Data JPA, Jakarta Validation et H2 (in-memory).

Stack

Java 17+

Spring Boot 3 (Web, Data JPA, Validation)

H2 Database (console activée)

Maven Wrapper (mvnw)

Démarrer
# build
./mvnw clean package
# run
./mvnw spring-boot:run

- App : http://localhost:8080

- Console H2 : http://localhost:8080/h2-console

    - JDBC URL : jdbc:h2:mem:demo

    - User : sa

    - Password : (vide)

Config (src/main/resources/application.properties) : 

spring.datasource.url=jdbc:h2:mem:demo;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

API
Base path: /api/students
| Méthode | Endpoint             | Description   |
| ------: | -------------------- | ------------- |
|    POST | `/api/students`      | Créer         |
|     GET | `/api/students`      | Lister        |
|     GET | `/api/students/{id}` | Détail        |
|     PUT | `/api/students/{id}` | Mettre à jour |
|  DELETE | `/api/students/{id}` | Supprimer     |

Exemples (PowerShell – Invoke-RestMethod)

# Create
Invoke-RestMethod -Method Post `
  -Uri http://localhost:8080/api/students `
  -ContentType 'application/json' `
  -Body '{"fullName":"Ada Lovelace","email":"ada@example.com"}'

# List
Invoke-RestMethod http://localhost:8080/api/students

# Get one
Invoke-RestMethod http://localhost:8080/api/students/1

# Update
Invoke-RestMethod -Method Put `
  -Uri http://localhost:8080/api/students/1 `
  -ContentType 'application/json' `
  -Body '{"fullName":"Ada L.","email":"ada@example.com"}'

# Delete
Invoke-RestMethod -Method Delete http://localhost:8080/api/students/1

(Si tu préfères curl sur Windows PowerShell, utilise des guillemets doubles et échappe les quotes)

Modèle & règles

Student

id (Long, auto)

fullName (@NotBlank)

email (@Email, unique en DB + contrôlé en service)

createdAt (Instant, valeur par défaut)

Transactions :

Lecture : @Transactional(readOnly = true)

Écriture : @Transactional (par méthode service)

Structure
src/main/java/com/asmahan/student_crud
 ├─ domain
 │   └─ Student.java
 ├─ repository
 │   └─ StudentRepository.java
 ├─ service
 │   └─ StudentService.java
 ├─ web
 │   └─ StudentController.java
 └─ StudentCrudApplication.java

Erreurs courantes

Port 8080 déjà utilisé → stoppe l’autre process ou change server.port.

H2 console 404 → vérifie spring.h2.console.enabled=true et l’URL /h2-console.

Validation → réponses 400 si email invalide / fullName vide.

Idées d’extensions

Pagination & tri (Pageable)

Tests (WebMvcTest, DataJpaTest)

Swagger/OpenAPI

DB Postgres en profil dev