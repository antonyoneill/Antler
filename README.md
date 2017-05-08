# Antler

A Simple Twitter style social application

## Execution

1. Clone the repo
2. Build the jar `mvn package`
3. Execute the jar `java -jar target/antler-0.0.1.jar`

### Commands

- Posting: `<username> -> <message>`
- Reading: `<username>`
- Following: `<username> follows <otherusername>`
- Wall: `<username> wall`

The Users are first created when they post a message.
Users cannot follow themselves, and their username is what makes them unique.
