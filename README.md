# Cron Expression Parser

This is a Java-based command-line application that parses and expands cron expressions, validating and expanding various cron fields such as minutes, hours, days, and commands. The application follows design patterns and principles to ensure clean, maintainable, and extendable code.

## Folder Structure

```
cron-expression-parser/
│
├── src/
│   ├── com/
│   │   ├── cronparser/
│   │   │   ├── factory/
│   │   │   │   └── FieldParsingStrategyFactory.java
│   │   │   ├── strategy/
│   │   │   │   ├── FieldParsingStrategy.java
│   │   │   │   ├── MinuteParsingStrategy.java
│   │   │   │   ├── HourParsingStrategy.java
│   │   │   │   ├── DayOfMonthParsingStrategy.java
│   │   │   │   ├── MonthParsingStrategy.java
│   │   │   │   └── DayOfWeekParsingStrategy.java
│   │   │   ├── parser/
│   │   │   │   └── CronParser.java
│   │   │   ├── utils/
│   │   │   │   ├── ExpansionUtils.java
│   │   │   │   └── ValidationUtils.java
│   │   │   ├── constants/
│   │   │   │   └── CronConstants.java
│   │   │   ├── exceptions/
│   │   │   │   └── InvalidCronExpressionException.java
│   │   │   └── Main.java
│   └── resources/
│       └── application.properties
└── pom.xml
```

---

## How to Run

1. Clone the repository.

   ```bash
   git clone https://github.com/your-repository/crone-expression-parser.git
   ```

2. Navigate to the project directory.

   ```bash
   cd cron-expression-parser
   ```

3. Run the following command to build the project:

   ```bash
   mvn clean install
   ```

4. To run the application, use the following command:

   ```bash
   mvn exec:java
   ```

5. Provide the Cron Expression in terminal once prompted. Enjoy! :)

---

## Design Patterns and Principles

### Design Patterns

| **Pattern**               | **Description**                                                                                                                                                             |
|---------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Factory Pattern**        | The `FieldParsingStrategyFactory` class creates the appropriate parsing strategy (like minute, hour, day of month, etc.) based on the cron field name. This keeps object creation logic separate and easy to modify. |
| **Strategy Pattern**       | `ExpansionUtils` and `ValidationUtils` use the Strategy pattern to handle different cron field types (ranges, lists, steps) in a flexible and interchangeable way. |
| **Singleton Pattern**      | Utility classes (`CronParser`, `ExpansionUtils`, `ValidationUtils`) are stateless and have methods that are used directly, following the Singleton pattern to avoid unnecessary object creation. |
| **Template Method Pattern**| The process for parsing and expanding cron expressions follows a common skeleton, with specific steps (like expanding ranges or steps) implemented in separate methods. This helps maintain clarity and extensibility. |
| **Validation Pattern**     | `ValidationUtils` checks if the cron expression is valid before processing it. It ensures that invalid expressions are caught and appropriate error messages are shown. |
| **Exception Handling**     | Custom exceptions (`InvalidCronExpressionException`) are used to handle errors, providing clear feedback to users when their cron expression is invalid. |

---

### SOLID Principles

| **Principle**                                      | **Description**                                                                                                                                                             |
|----------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Single Responsibility Principle (SRP)**          | Each class handles a single responsibility, making the code more modular and easier to maintain. For example, `CronParser` handles parsing, while `ValidationUtils` handles validation. |
| **Open/Closed Principle (OCP)**                    | The code can be extended (to support new cron formats or parsing strategies) without modifying existing code. For example, adding new fields can be done by extending the factory or adding new strategies. |
| **Liskov Substitution Principle (LSP)**            | New classes can replace existing ones without breaking functionality. For example, new `FieldParsingStrategy` implementations can replace old ones without affecting the rest of the code. |
| **Interface Segregation Principle (ISP)**          | Methods and interfaces are designed for specific tasks, ensuring clients don’t depend on unnecessary methods. For example, each cron field type has its own parsing strategy. |
| **Dependency Inversion Principle (DIP)**           | High-level modules don’t depend on low-level modules. Both depend on abstractions. For example, `FieldParsingStrategyFactory` provides a level of abstraction for field-specific parsing strategies. |

---

## Sample Input and Output

---

### Sample Input 1

```
Enter a cron expression:
*/15 0 1,15 * 1-5 /usr/bin/find
```

### Sample Output 1

```
minute         0 15 30 45
hour           0
day of month   1 15
month          1 2 3 4 5 6 7 8 9 10 11 12
day of week    1 2 3 4 5
command        /usr/bin/find
```

---

### Sample Input 2

```
Enter a cron expression:
5 0 * * 0-6 /usr/bin/backup
```

### Sample Output 2

```
minute         5
hour           0
day of month   1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
month          1 2 3 4 5 6 7 8 9 10 11 12
day of week    0 1 2 3 4 5 6
command        /usr/bin/backup
```

---

### Sample Input 3

```
Enter a cron expression:
0 12 1 * 1-5 /usr/bin/logrotate
```

### Sample Output 3

```
minute         0
hour           12
day of month   1
month          1 2 3 4 5 6 7 8 9 10 11 12
day of week    1 2 3 4 5
command        /usr/bin/logrotate
```

---
