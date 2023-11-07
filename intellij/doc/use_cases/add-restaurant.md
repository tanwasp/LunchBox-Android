# Add Restaurant

## 1. Primary actor and goals

* __User__: Wants the restaurant they visited to be on the app. Wants them and their friends to be able to view and rate the restaurant.

## 2. Other stakeholders and their goals

* __Restaurant__: Wants to have a profile on the app so their business gets visibility.

## 2. Preconditions

* User is logged into a functional account
* User is on a search results page 

## 4. Postconditions

* Added restaurant profile is be saved and visible to all viewers when searched

## 4. Workflow

```plantuml
@startuml

skin rose

title Add Restaurant (Fully Dressed)

'define the lanes
|#wheat|User|
|#pink|App|

|User|
start
:Select "Add Restaurant";

|App|
:Brings up restaurant addition form;

|User|
while (All data complete?) is (No)
:Enter restaurant name into appropriate text box;
:Enter restaurant location info into appropriate text boxes;
:Clicks save;
endwhile (Yes)

|App|
:Save new restaurant to app;

stop
@enduml
```
