# Rock, Paper and Scissors - Backend

A simple HTTP-API which lets developers solve their differences with with help
of the game Rock, paper, scissors. 



### Prerequisites

You need these things to install and run the program:

- Java 1.8 - https://www.oracle.com/java/technologies/javase-jdk8-downloads.html
- Maven - https://maven.apache.org/install.html
- Postman - https://www.postman.com/downloads/ NOTE: There are other programs you can use instead of Postman.


### Setting up

1. Unzip the folder.
2. Run the command "mvn clean install spring-boot:run" from the RockPaperScissors folder.
3. Wait for the program to load.

### Playing

Start up Postman. These are the Endpoints used. 

request-body name example:
{
"name": "john"
}

request-body name and move example:
{
"name": "john",
"move": "rock"
}

viable moves(not case-sensitive): rock, paper, scissors OR r, p, s


 POST /api/games
    * requires a player name in request-body
    Example
    
    
 POST /api/games/{id}/join
 POST /api/games/{id}/move
 GET /api/games/{id}

Some 

## Author

* **Niclas Fölster** 
