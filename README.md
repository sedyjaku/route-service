# Route service
Route service is a REST API service which tries find a route between two countries

## Setup
Service is running on Kotlin 1.52 and Java 16  with maven 3

### Maven (optional)
there is a maven wrapper, for maven  installation just simply follow steps [here](https://maven.apache.org/install.html)

### JDK
for JDK 16 installation just simply follow steps [here](https://www.oracle.com/java/technologies/downloads/#java16)

### Docker (optional)
Docker can be used to run an app without configuring anything else, to install docker follow [these steps](https://docs.docker.com/install/)


## Building and running app
There are tho ways to run the app described bellow (all is done in root dir)

### Using gradle and Java
First you need to build the project using gradle

1. Use wrapper (`mvnw package`) or your maven installation(`mvn package`)  to build the app, you can skip tests by appending  `-DskipTests` to maven command

2. Run java (replace {} with correct values) or dont use those `-Dspring*` parameters if same as default (see [strategies](#Strategies))

`java
-Dstrategy={STRATEGY_TYPE}
-Ddfs.strategy={DFS_STRATEGY_TYPE}
-jar /target/{JAR_FILE_NAME}.jar`

### Using docker (Currently not working)
All you need to do is run `docker-compose up` or `docker-compose up --build` if changed in code were made

## How to use
Service has 1 endpoint returning a list of countries to pass by

### Route
Request url: GET {SERVICE_URL}/routing/{origin}/{destination}

#### Response
Response object contains field with list of routes:

- "route" (list of countries to pass by)

#### Example

`curl --location --request GET 'http://localhost:8080/routing/CZE/ITA'`

##### Response:
`{
"route": [
"CZE",
"AUT",
"ITA"
]
}`

### Strategies

There are currently 2 strategies: BFS and DFS. Another one can be implemented by implementing RouteService interface. 

Strategy can be set by property strategy - BFS is default and can also be set by "bfs" value and DFS is set by "dfs" value in strategy property

#### DFS

Dfs has currently 2 implemented strategies of expansions which differ in "next expansion node" selection.

##### Shortest DFS

shortest DFS tries to find the closest node candidate to the destination and expand there. It is set by default and can also be set by "shortest" value in dfs.strategy property

This DFS does not guarantee to find the shortest path

##### Longest DFS
Longest  DFS tries to find the furthest node candidate to the destination and expand there. It is by "longest" value in dfs.strategy property
