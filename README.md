# Java_JDBC

A simple Java JDBC project that connects to an Oracle database using configuration values loaded from `config.properties`.

## Project structure
- `main.java` — entry point
- `database/DatabaseConnection.java` — database connection utility
- `config.properties` — Oracle connection settings
- `lib/ojdbc11-23.4.0.24.05.jar` — Oracle JDBC driver

## Configure database connection
Create or update `config.properties` in the project root:

```properties
DB_URL=jdbc:oracle:thin:@localhost:1521:XE
DB_USER=
DB_PASSWORD=
```

## Compile and run
From the project root:

```bash
javac -d bin *.java database/*.java models/*.java controller/*.java
java -cp bin:lib/ojdbc11-23.4.0.24.05.jar main
```

## Menu options
1. Collect Account
2. Collect Bank Request
3. Exit
