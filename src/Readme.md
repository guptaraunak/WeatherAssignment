Solution :-

1. To implement the CRUD operations for weather data Entity : Weather

Properties:-
- private String city
- private Long temp
- private String desc

2. For Repository, we use in-memory HashMap to store the data

3. Services :- WeatherServices and its methods

   - WeatherResponseDTO addWeatherData(String city, Weather weather);

   - WeatherResponseDTO getDataByCity(String city);

   - List<WeatherResponseDTO> getAllData();

   - WeatherResponseDTO updateDataByCity(String city, Weather weather);

   - boolean deleteWeatherData(String city);

4. DTOs -WeatherResponseDTO
    - The reason to use DTO is to secure our original Weather Entity and don't directly return original weather object in the response, so we are wrapping it inside a DTO class and sending DTO object in response, second use is to customize the response which is sent to user as per the requirement 

5. In Mapper Package I have created one class to convert Weather object to DTO class so mapper help in converting the weather response dto from weather Object


     
    
     
