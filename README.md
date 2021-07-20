# Pokedex Application

The Pokedex application is written as a Spring application and runs on JDK 11.
If you need a Java SDK I suggest using [SDKMAN!](https://sdkman.io/install).
After installing it, you can download and use a JDK 11 by doing:

`sdk install java 11.0.11-zulu && sdk use java 11.0.11-zulu`

Once done, the current shell will be configured to use the correct JDK, which you can chek doing:

`echo ${JAVA_HOME}`

`java --version`

## Running the Pokedex application  
The Docker image used as base image is the [Azul Zulu on Alpine Linux](https://hub.docker.com/r/azul/zulu-openjdk-alpine). 
From the root directory of the project (which includes the Dockerfile) build the Docker image:

`docker build -t truelayer.com/pokedex .`

Run the Pokedex application using Docker:

`docker run -p 5000:5000 truelayer.com/pokedex --server.port=5000 --logging.level.root=ERROR`

### Running the Pokedex application using the Gradle wrapper    
If you prefer running the application directly without using Docker, do the following from the project root directory:

`./gradlew clean build && java -jar build/libs/pokedex-1.0.0.jar`
