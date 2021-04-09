**Description**

This is just a training project. The idea is to write a web-application that can
monitor some resources (http, ping) and collect monitoring information like uptime, downtime etc.
It should be able to send alert notifications by SMS gateway or email.

**Build**

To build project you need to have Java 8 and NodeJS.
Run the following commands to build the project:

```
npm install
gradlew build
```

**Run**

You need to have installed MariaDB database, with the schema 'pinger'.  
To start application, just execute the following:

```
gradle bootRun
```