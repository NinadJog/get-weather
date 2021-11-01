package data

/**
 * Case classes representing the JSON data received as the response. Not all
 * fields from the response JSON are represented in these case classes; only
 * the ones useful for this application are. For example, the acutal weather
 * response contains a field called timezone_offset, but I have excluded it
 * from the WeatherResponse case class because we have no use for it.
 * 
 * Circe's automatice derivation ensures that the conversion happens
 * regardless of the exclusion.
 * 
 * The list of alerts in the WeatherResponse case class is marked as Optional
 * because the HTTP response does not always contain alerts.
 */

// Type aliases for temperature and timezone
type Temperature  = Double
type Timezone     = String

case class WeatherResponse(
  lat:        Double,
  lon:        Double,
  timezone:   Timezone,
  current:    Current,
  alerts:     Option [List [Alerts]])  // Option becasue alerts might not always be present

case class Current(
  temp:       Temperature,
  feels_like: Temperature,
  weather:    List [Weather])

case class Weather(
  main:         String,
  description:  String)

case class Alerts(
  event:        String,
  description:  String)