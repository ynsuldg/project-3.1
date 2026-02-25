## JavaTools

## Applikationens tema

Detta projekt är en Spring Boot-baserad webbapplikation för att hantera följande resurser:

- Drink
- Food
- Pizza
- Spirit

För varje resurs finns full CRUD-funktionalitet:

- Visa alla
- Visa en
- Skapa ny
- Redigera
- Ta bort

Applikationen är uppbyggd enligt MVC-arkitektur med Controller, Service, Repository och Validator.  
Frontend renderas med Thymeleaf.

---

## Gruppmedlemmar

- Yunus Uludag (ynsuldg)
- Jakob Klasén (klasen98livese)
- Lina Wei (L2025S)
- Philip Mitilinos (philipwmitilinos-max)

---

## Hur projektet startas

### Förutsättningar

- Java 21 eller senare
- Maven

### Starta applikationen

1. Klona projektet:

git clone <repo-url>

2. Gå till projektmappen:

cd javatools

3. Starta applikationen:

mvn spring-boot:run

Alternativt kan applikationen startas via IDE genom att köra  
`JavaToolsApplication`.

4. Öppna webbläsaren och gå till:

http://localhost:8080/drinks
http://localhost:8080/foods
http://localhost:8080/pizzas
http://localhost:8080/spirits

---

## Miljövariabler

Applikationen använder följande miljövariabler:

| Variabel    | Beskrivning             |
|-------------|-------------------------|
| DB_URL      | Databasens URL          |
| DB_USERNAME | Databasens användarnamn |
| DB_PASSWORD | Databasens lösenord     |

### Sätta miljövariabler

#### Mac / Linux

export DB_URL=xxxx
export DB_USERNAME=xxxx
export DB_PASSWORD=xxxx

#### Windows (PowerShell)

setx DB_URL xxxx
setx DB_USERNAME xxxx
setx DB_PASSWORD xxxx

#### Via IntelliJ

Run Configuration → Environment Variables → Lägg till variablerna där.