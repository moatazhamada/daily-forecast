# Android Developer Position Technical

# Task

Our task focuses on architecture and coding skills, so please spend time
reviewing task architecture and code readability.

# Daily forecast Application

Daily forecast application is a simple application consisting of only one screen
with a top bar containing a text field accepting the city name then when the user
click search app should hit api and get daily forecast data for given city name,
and cache it.
Each day in days list should contain any ui item (it’s up to you) to indicate
weather description like (​Rain, Snow, Extreme etc.).
If a user faces any failure in data retrieving app should first check if needed data
exists in local cache if yes app should display cached data and show some
warning to indicate it’s not accurate data, if no data cached he should see UI
represent this error and option to retry.

## Here is the API to use in this task:

api.openweathermap.org/data/2.5/forecast/daily?q={cityname}&cnt={cnt}&appid={API key}

**Documentation link:** ​ **https://openweathermap.org/forecast16 **

## Evaluation

## Your code will be assessed based on the following:

### ● Screen performs as described and contains the specified details.

### ● Code must be clean and readable

### ● SOLID principles and OOP applied .(but don’t over engineer any task).


## Bonuses ​​

### ● Unit testing

### ● UI Testing
