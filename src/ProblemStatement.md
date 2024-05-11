Problem Statement: Building a Weather Data CRUD API with Spring Boot

You are tasked with building a web service using Spring for a weather data application. The application should expose API endpoints to perform CRUD operations on weather data for different locations.

Requirements:

Implement a spring application with the following CRUD API endpoints for weather data:

Create weather data for a specific city.
Retrieve weather data for a specific city.
Update weather data for a specific city.
Delete weather data for a specific city.
Use an in-memory data store (e.g., a HashMap) to store weather data for different cities.

Design the API to handle input validation and error cases gracefully, providing appropriate error messages and status codes. Use gradle as a build tool and write test cases for those and your code coverage should be more than 75 %. Use java 17 to build your application.

API Endpoints:

POST: /weather/{city}

HTTP Method: POST

Request Body:

{
"temperature": <temperature>,
"description": "<weather_description>"
}

Response:


{
"city": "<city_name>",
"temperature": <temperature>,
"description": "<weather_description>"
}

GET: /weather/{city}

HTTP Method: GET

Response:

{
"city": "<city_name>",
"temperature": <temperature>,
"description": "<weather_description>"
}

GET: /weather/all

HTTP Method: GET

Response:

{
"cities": [
"<city_name_1>",
"<city_name_2>"
]
}




PUT: /weather/{city}

HTTP Method: PUT

Request Body:

{
"temperature": <updated_temperature>,
"description": "<updated_weather_description>"
}

Response:

{
"city": "<city_name>",
"temperature": <updated_temperature>,
"description": "<updated_weather_description>"
}

DELETE: /weather/{city}

HTTP Method: DELETE

Response:

{
"message": "Weather data for <city_name> has been deleted."
}
