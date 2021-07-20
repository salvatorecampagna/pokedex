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

`./gradlew clean build && docker build -t truelayer.com/pokedex .`

Run the Pokedex application using Docker:

`docker run -p 5000:5000 truelayer.com/pokedex --server.port=5000 --logging.level.root=ERROR`

### Running the Pokedex application using the Gradle wrapper    
If you prefer running the application directly without using Docker, do the following from the project root directory:

`./gradlew clean build && java -jar build/libs/pokedex-1.0.0.jar`

**NOTE:** the `build/libs` directory includes two Jar files. Make sure to use `pokedex-1.0.0.jar` which is the so called
'fat Jar' that is self-contained and includes all required dependencies.

## Design

The application is available in package `com.truelayer.pokedex` which includes the Spring entry-point class and is further
divided in the following sub-packages:
- `com.truelayer.pokedex.api`: this package includes the HTTP controller which implements the two APIs and includes the DTOs
returned to the client as JSON.
- `com.truelayer.pokedex.details`: this sub-package includes all classes implementing the first API `/pokemon/{pokemonName}`
  (what I refer in code as **pokemon details**).
- `com.truelayer.pokedex.translate`: this sub-package includes all classes implementing the second API `pokemon/translated/{pokemonName}`
  (what I refer in code as **translation**).
- `com.truelayer.pokedex.mapper`: this sub-package includes two classes, one mapping DTOs to service objects and vice-versa
  and one class implementing the logic to extract the description of the Pokemon after calling the remote REST PokeAPI.

When packaging the different classes I used the *package-by-feature* strategy, placing all items for a single feature into
the same package (and directory). This results in modular packages, and minimal coupling between packages. Items that
work together are placed next to each other and are not spread out all over the application.

I decided to return two different DTOs from the two APIs even if not strictly required. The `TranslatedPokemonDto` includes
two additional fields with respect to the `PokemonDto`: a *booelan* `isTranslated` which is true/false depending on whether
the description is translated or not and a `translation` field reporting the actual translation applied ("shakespeare",
"yoda") and *null* if not applied.

### Circuit breaker

A circuit breaker is a **client resiliency pattern** whose goal is to protect a remote resource (another microservice or
a database) from crashing or being overloaded  when errors are taking place. The goal here is to allow the client to
**fail-fast** and prevent errors from spreading upstream.

With a circuit breaker, when a remote service is called (like the APIs for getting pokemon details and the description
translation) it will monitor the call and if it takes too long the circuit breaker will interced and block the call
returning an error upstream. In addition, it will monitor the number of calls failing in a certain period of time and if
a threshold is exceeded the circuit will open causing all subsequent calls to fail. After a while a few more calls will be
allowed again and depending on the outcome of these calls the circuit will be closed restoring normal functionality
or kept open in case the remote service is still experiencing failures.

I used **Hystrix**, made popular by Netflix, to protect calls to remote APIs (translation and pokeapi) and avoid failures
to propagate to upstream components. Even if this is a "toy-applcaition" I believe protecting an application from remote
services failing is a **must-have** for modern cloud-based applications.

For the translation service I also used the *fallback* mechanism provided by the Hystrix circuit breaker to provide a
default response.

### Bulk heading

Using a bulk head pattern we can break calls to remote services into different thread pools and reduce the risk that
a problem with one slow service  will take down the whole application. It is a way to isolate or compartmentalize parts
of an application to avoid errors to propagate. The thread pools act as the bulkhead for a service. Each remote resource
is segregated and calls to other services won't be affected because they are processed by threads in a different thread pool.
In the specific case I used two thread pools for the two different application sub-systems, the translations service
used by the second API and pokemon details service used by the first API.

In the code these two thread pools are identified by two different thread pool keys: `pokemon-details` and `pokemon-translate`
(see `TranslationServiceImpl` and `PokemonDetailsServiceImpl`). In the application properties (see section below setting
are available for the two different thread pools).

Again this is provided by the Hystrix library.

### Application properties

Application properties are setup in `src/main/resources/application.properties` and it is possible to override
each one of them through the command line. The list of properties is the following:
- `logging.level.root` (default: ERROR): the logging level used by the application when writing messages to the console.
- `server.port` (default: 5000): the port the server will listen to for HTTP requests;
- `service.pokemon.details.url` (default: https://pokeapi.co/api/v2/pokemon-species/%s): a string identifying the endpoint
  for the first service API call.
- `service.pokemon.translate.url` (default: https://api.funtranslations.com/translate/%s.json): a string identifying the
  endpoint for the second service API call.
- `hystrix.threadpool.pokemon-translate.coreSize` (default = 20): the size of the thread pool used to run threads calling
  the first REST API (first API).
- `hystrix.threadpool.pokemon-translate.maximumSize` (default = 50): the maximum thread pool size used to run threads calling
  the first REST API. Under high-load the number of threads will increase up to this value (first API).
- `hystrix.threadpool.pokemon-translate.maxQueueSize` (default = 50): the maximum queue size set in front of the thread pool.
  If set to -1, no queue is used and Hystrix will block the request until a thread becomes available (first API).
- `hystrix.threadpool.pokemon-translate.queueSizeRejectionThreshold` (default = 300): the maximum queue size for request
  above which requests will be discarded (first API).
- `hystrix.threadpool.pokemon-translate.allowMaximumSizeToDivergeFromCoreSize` (default = true): allows threads to be created
  if required to serve more requests (first API).
- `hystrix.threadpool.pokemon-translate.keepAliveTimeMinutes` (default = 1): after threads are created to serve more requests
  this is the time they will be kept around before they are release to return to the normal thread pool size (first API).
- `hystrix.threadpool.pokemon-details.coreSize` (default = 20): the size of the thread pool used to run threads calling
  the first REST API (second API).
- `hystrix.threadpool.pokemon-details.maximumSize` (default = 50): the maximum thread pool size used to run threads calling
  the first REST API. Under high-load the number of threads will increase up to this value (second API).
- `hystrix.threadpool.pokemon-details.maxQueueSize` (default = 50): the maximum queue size set in front of the thread pool.
  If set to -1, no queue is used and Hystrix will block the request until a thread becomes available (second API).
- `hystrix.threadpool.pokemon-details.queueSizeRejectionThreshold` (default = 300): the maximum queue size for request
  above which requests will be discarded (second API).
- `hystrix.threadpool.pokemon-details.allowMaximumSizeToDivergeFromCoreSize` (default = true): allows threads to be created
  if required to serve more requests (second API).
- `hystrix.threadpool.pokemon-details.keepAliveTimeMinutes` (default = 1): after threads are created to serve more requests
  this is the time they will be kept around before they are release to return to the normal thread pool size (second API).

**NOTE:** the values I provided as default values should not be considered the best possible choice and this is
also the reason why all these properties can be set on the command line overriding the default values. My opinion is that:
1. the correct values should be found by testing (load testing).
2. a monitoring system should be in place and tools should be available to control these values until suitable values are
   found.

As an example, changing the `hystrix.threadpool.pokemon-translate.maximumSize` on the command line can be done like follows:

`docker run -p 5000:5000 truelayer.com/pokedex --hystrix.threadpool.pokemon-translate.maximumSize=200`

Also, it is possible to override these values using environment variables. As a result, in a cloud environment where,
for instance applications run in Docker, it is possible to spin up new applications *injecting* the Docker container
with different values provided through environment variables.

### Tests

Tests are provided in the `src/test` directory. It is possible to run tests using the Gradle wrapper from the command line:

`./gradlew test`

### Production improvements:

When it comes to improvements, especially for production environment I would suggest the following:

* Use **versioning**. There are a few ways to version REST APIs, and it is important, especially if we have
  a lot of customers integrating with our API, that we have different versions available in case we need to deploy breaking
  changes. This allows us to evolve our APIs without causing issues to customers and allows customers to adopt new APIs
  at their own pace. For instance, we could have our APIs versioned by *mounting* them under `/api/v1/pokemon/{pokemonName}` and
  `/api/v1/pokemon/translated/{pokemonName}`.

* Use an **api key**: it would allow us to identify customers using our API and also apply rate-limiting (if necessary).

* Use **API description**: use something like Swagger to describe the API. It will be useful for our customers both for
  documentation purposes but also, for instance, to automatically generate API stubs and DTO classes in the language of
  their choice.

* **Cache** data: most of the data we are returning in this API is not changing...a Pokemon is always the same right? As
  a result, using caches would allow us to decrease the API latency (or response time) and, if making the remote API calls
  costs money we would also decrease the cost while serving our customers. This can easily be implemented by, first doing
  a cache lookup in our service to check if Pokemon data is in the cache and return it immediately on a cache hit, or just
  make the REST API call in case of a cache miss. Other than that, caches help the resiliency of our application as a result of
  having data available 'locally' and not relying on remote service calls which are subject to failures due to network issues.

Some of these improvements are not really required for this specific API since it is quite simple, but things which we might
need in general when using APIs in a cloud environment, and a decent number of microservices include:

* **Monitoring**: have a system in place to monitor API endpoints and measure at least latency and errors per each endpoint.

* **Logging**: have a system to centralize logs collection (ELK, Graylog,...).

* **Tracing**: having the ability to 'follow' API calls from one service to another is very important. A tracing system
  usually works injecting a so called 'correlation id' into the HTTP request as an HTTP header. Later, traces are collected
  in a central place and aggregated using the correlation id, showing the different service a request went through including
  timings.