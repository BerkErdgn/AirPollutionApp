
# Air Pollution App
 
The application that gives information about the air quality between the desired times from the stations that measure the air quality in Istanbul.








## Technologies Used
- Clean Architecture
- LiveData
- Corutines
- Dagger Hilt
- Retrofit
- Navigation
- Room
- LottieFiles







  


## Screenshots

Splash Screen | Main Screen |Detail Screen|Save Screen|
 --- | --- |  --- | --- | 
![](https://github.com/BerkErdgn/AirPollutionApp/blob/main/sc/1.png?raw=true)| ![](https://github.com/BerkErdgn/AirPollutionApp/blob/main/sc/2.png?raw=true) |![](https://github.com/BerkErdgn/AirPollutionApp/blob/main/sc/3.png?raw=true) |![](https://github.com/BerkErdgn/AirPollutionApp/blob/main/sc/4.png?raw=true)


## API Usage

- Base Url:
```http
    https://api.ibb.gov.tr/havakalitesi/OpenDataPortalHandler/

```
----
- To get all stations:
```http
   GetAQIStations
```


----
- To get one station details:
```http
   GetAQIByStationId
```

| Parameter | Type     | Explanation                |
| :-------- | :------- | :------------------------- |
| `StationId ` | `string` | to get the details in **that station**. | 
| `StartDate ` | `string` | to get the details in **that station**. | 
| `EndDate ` | `string` | to get the details in **that station**. | 



## Download  

To download the project

```bash 
  1-Press the green "Code" button at the top right of this page.
  2-Click on Download ZIP
  3-Extract the ZIP and open it to Android Studio.
```
As an alternative

You can directly download the ZIP by clicking the [link here](https://github.com/BerkErdgn/AirPollutionApp/archive/refs/heads/main.zip).

## Find a bug?

If you found an issue or would like to submit an improvement to this project, please submit an issue using the issues tab above.
Thank you very much.
