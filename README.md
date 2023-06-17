# Maximum Service
This service is developed to find the maximum number of 3 parameters

## Building the project
You will need:
*	Maven 3.8.x
*	Git
*	Docker 4.x.x
*   Docker Compose V2

Clone the project and use docker-compose to build the server

	$ docker-compose up --build

if you have problems with the previous command, then run the following command

    $ docker-compose up

to remove all containers, execute:

    $docker-compose down

## Swagger documentation
This project contains a swagger documentation at the following url:
http://localhost:8080/maximum-service/swagger-ui/index.html


## Environment Variables
this project contains a .env file that contains the following variables


    USER_DB=      --> database user
    PASSWORD_DB=  --> database password
    SERVER_PORT=  --> service port
    PORT_DB       --> database password
    DB_URL=       --> database host
    NAME_DB=      --> database name

Feel free to change the environment variables


