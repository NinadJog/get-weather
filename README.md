## REST Client for getting weather data

REST client that gets weather data from the Open Weather Map (https://openweathermap.org), parses the JSON, and displays some of the results in human-readable terms.

It shows the location's current weather (whether it is clear, rainy, etc.), how the temperature feels (hot, cold, moderate), and whether there are any alerts.

### Technologies

The implementation uses the following technologies.

1. Scala 3
2. STTP: the clean, easy-to-program Scala HTTP client library
3. STTP's HTTP URL backend, which is based on Akka-http.
4. Open Weather Map's One-Call API (https://openweathermap.org/api/one-call-api)
5. Circe and its Automatic Derivation for parsing the response JSON

### Code Organization

The code is organized into the following packages.

1. The _data_ package contains the case classes used by the response JSON. It also contains type aliases.

2. The _dataaccess_ package contains the functions that contact the Open Weather Map server and fetch the data.

3. The Main.scala file contains the "business logic" that interprets and displays the data in human-readable form. It also contains the test function. Ideally, the business logic and the test function should be in two separate packackages, but since this is a small program, I have used just one.

### Usage

This is a normal sbt project compiled with Scala 3. You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL.
