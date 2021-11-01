import data._
import dataaccess._

// The transcript of a sample run is shown at the end of this file. 
//-------------------------------------------------------------------------------------
@main def testShowWeatherDetails: Unit = 
  val (lat, lon)  = (51.51, -0.14) // London
  showWeatherDetails (lat, lon)

//-------------------------------------------------------------------------------------
/* Queries the server for the weather at a location and prints out some of the results. 
 */
def showWeatherDetails (lat: Double, lon: Double): Unit = 

  val response = getWeatherWithJson (lat, lon)

  response match
    case None => println ("Weather response not obtained due to error")

    case Some (weatherResp) => 
      println             (s"RIGHT: $weatherResp")
      showCurrentWeather  (weatherResp.current.weather)
      showHotCold         (weatherResp.current.feels_like)

      weatherResp.alerts match
        case None           => println ("There are no alerts")
        case Some (alerts)  => showCurrentAlerts (alerts)

//------------------------------------------------------------------------------------  
def showHotCold (hotCold: Temperature): Unit =
  println (s"It feels ${getTemperatureFeeling (hotCold)} outside.")

//------------------------------------------------------------------------------------
/* Returns how it feels: hot, cold, or moderate, depending upon the temperature.
 * The temperature is obtained from the server in degrees Celsius.
 */
def getTemperatureFeeling (temperature: Temperature): String =
  temperature match
    case a if a < 12  => "cold"
    case b if b < 30  => "moderate"
    case _            => "hot"

//------------------------------------------------------------------------------------  
def showCurrentWeather(
  weathers: List [Weather]): Unit =

  for weather <- weathers do
    println
    println (s"Event:       ${weather.main}")
    println (s"Description: ${weather.description}")

//------------------------------------------------------------------------------------  
def showCurrentAlerts(
  alerts: List [Alerts]): Unit =

  for alert <- alerts do
    println
    println (s"Event:       ${alert.event}")
    println (s"Description: ${alert.description}")

//------------------------------------------------------------------------------------  
/* Transcript of a sample run:

[info] running showWeatherDetails 
RIGHT: WeatherResponse(51.51,-0.14,Europe/London,Current(7.35,5.27,List(Weather(Clear,clear sky))),None)

Event:       Clear
Description: clear sky
It feels cold outside.
There are no alerts
[success] Total time: 5 s, completed Nov 1, 2021 3:19:53 PM
*/