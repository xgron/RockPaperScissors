# Rock, Paper and Scissors - Backend

A simple HTTP-API which lets developers solve their differences with with help
of the game Rock, paper, scissors. It is created using Java, Spring Boot and Maven.



## Prerequisites

You need these things to install and run the program:

- Java 1.8 - https://www.oracle.com/java/technologies/javase-jdk8-downloads.html
- Maven - https://maven.apache.org/install.html
- Postman - https://www.postman.com/downloads/ NOTE: There are other programs you can use instead of Postman.


## Setting up

1. Unzip the folder.
2. Run the command "mvn clean install spring-boot:run" from the RockPaperScissors folder.
3. Wait for the program to load.

## Playing

Start up Postman. the port is the default for Spring Boot, which is 8080

To edit request-body, chose raw and select JSON. These are the Endpoints used. 

### request-body structure:

name:
{
"name": "john"
}

name and move:
{
"name": "john",
"move": "rock"
}

viable moves(not case-sensitive): rock, paper, scissors OR r, p, s

### Endpoints
 * POST /api/games (requires first players name)
    * Gives the id to use in the other endpoints
 
 * POST /api/games/{id}/join (requires second players name)
 
 * POST /api/games/{id}/move (requires name and move)
 
 * GET /api/games/{id}

### Some restrictions set: 

* There must be two players in a game before anyone can make a move.
* Once a player has chosen a move, they can not change it.

## Example game:
localhost:8080/api/games (POST)
* Body: { "name":"john" }

localhost:8080/api/games/{id}/join (POST)
* Body: { "name":"jane" }

localhost:8080/api/games/{id}/move (POST)
* Body: { "name":"jane", "move":"rock" }

localhost:8080/api/games/{id}/move (POST)
* Body: { "name":"john", "move":"scissors" }

localhost:8080/api/games/{id} (GET)

(See the result)

## Author

* **Niclas Fölster** 
