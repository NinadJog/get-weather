package dataaccess

import sttp.model._
import sttp.client3._
import sttp.client3.circe._

import io.circe._
import io.circe.generic.auto._
import io.circe.syntax._

import data._

//------------------------------------------------------------------------------------  
// Global constants, disguised as functions.
// For the code to work, you will have to provide your own API key below.

def exclude = "minutely,hourly,daily"
def apiKey  = "e85bc3f595e4bf429fa980c174f557cb" // Provide your own API key
def units   = "metric"  // Temperature in degrees Celsius

//------------------------------------------------------------------------------------
/**
 * Calls the API with a GET and converts the resulting response JSON into Scala objects,
 * using Circe's automatic derivation. Returns None if there's an error getting the
 * response.
 */
def getWeatherWithJson(
  lat: Double,
  lon: Double): Option [WeatherResponse] = 

  val backend = HttpURLConnectionBackend()
  val request =
    basicRequest
      .get (uri"https://api.openweathermap.org/data/2.5/onecall?lat=${lat}&lon=${lon}&exclude=${exclude}&units=${units}&appid=${apiKey}")
      .response (asJson [WeatherResponse])
  try 
    val response = request.send (backend)

    response.body match
      case Left(error) => 
        println(s"LEFT:  $error")
        None

      case Right (weatherResp) => 
        Some (weatherResp)

  finally
    backend.close()

//------------------------------------------------------------------------------------  
/** Basic function that gets the weather but does not convert the JSON response to
 *  Scala objects. I stared out with this function before converting it to the more
 * sophisticated version above.
 */
def getWeather: Unit = 

    val lat     = 33.44
    val lon     = -94.04

    val backend = HttpURLConnectionBackend()
    val request =
      basicRequest
        .get (uri"https://api.openweathermap.org/data/2.5/onecall?lat=${lat}&lon=${lon}&exclude=${exclude}&appid=${apiKey}")

    try 
      val response = request.send (backend)

      response.body match
          case Left(error)   => println(s"LEFT:  $error")
          case Right(result) => println(s"RIGHT: $result")

      println (s"Response code = ${response.code}")

    finally
      backend.close()
    