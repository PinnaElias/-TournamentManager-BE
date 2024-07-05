
# Tournament Manager Application
    Back-End di una Web App per la creazione e gestione di tornei di e-sports.

## Tecnologie Utilizzate

    Java 11
    Spring Boot
    Spring Boot Starter Data JPA
    Spring Boot Starter Security
    Spring Boot Starter Validation
    Spring Boot Starter Web
    PostgreSQL
    Lombok per ridurre il boilerplate di codice
    JWT (JSON Web Token) per l'autenticazione e l'autorizzazione
    Maven per la gestione delle dipendenze


## Prerequisiti
    Java 11 o superiore
    Maven 3.6 o superiore
    PostgreSQL


## Configurazione del Database

    Assicurarsi di avere PostgreSQL installato e in esecuzione. Creare un database per l'applicazione:
    sql
    CREATE DATABASE tournamentmanager;
    Aggiornare il file application.properties con le credenziali del database:

## properties:
    spring.datasource.url=jdbc:postgresql://localhost:5432/tournament_management
    spring.datasource.username=your_db_username
    spring.datasource.password=your_db_password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

## Installazione

    Clonare il repository: git clone https://github.com/PinnaElias/TournamentManager-BE.git

## Costruire il progetto:
    npm install


