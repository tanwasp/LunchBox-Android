# Check Review

## 1. Primary actor and goals

__User__: Wants to see the rating of a specific restaurant and their friends' reviews of it.

## 2. Other stakeholders and their goals

* __Restaurant__: Wants the user to see positive reviews and have a positive impression of their business.

## 2. Preconditions

* User is logged into a functional account
* Restaurant profile exists and has reviews
* Filters include:
  * Friends only
  * Most liked (default)
  * Most recent

## 4. Postconditions

* Nothing will be changed on the user's or restaurant's profile

## 4. Workflow

```plantuml
@startuml

skin rose

title Check Restaurant Reviews (Fully Dressed)

|#wheat|User|
|#pink|App|

|User|
start
:Navigate to search page;
:Enter restaurant name into search box;

|App|
:Display relevant search results;

|User|
:Select profile for desired restaurant or friend;

|App|
:Bring up desired profile;


|User|

if (Want to filter reviews?) is (Yes) then 
:Select a filter;
else (No)
endif

:Scroll down to reviews;

stop
@enduml
```