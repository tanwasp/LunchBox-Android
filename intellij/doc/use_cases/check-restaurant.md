# Check Restaurant Review (Fully Dressed)

## 1. Primary actor and goals

__User__: Desires to find and view ratings and reviews for a specific restaurant. They are particularly interested in feedback from their friends.

## 2. Other stakeholders and their goals

* __Restaurant__: Wants the user to see positive reviews and have a positive impression of their business.
* __Friend__: Wants to be able to find the user's account using their username so that they can interact.

## 2. Preconditions

* User is logged into a functional account
* Restaurant profile exists in the app's database and has reviews
* A location is set for the user
  * This can be done automatically using the user's device
  * This can be set or changed manually by the user
* User has the option to filter restaurant searches:
  * By cuisine
  * By price range
  * By friends
* User has the option to customize sort restaurant searches:
  * By most relevant (default)
  * By nearest
  * By highest rating
  * By most reviews
* User has the option to filter reviews:
  * By friends only
  * By time range
  * By tags
* User has the option to sort reviews:
  * By most liked (default)
  * By most recent

## 4. Postconditions

* Nothing will be changed on the user's or restaurant's profile
* User will be able to make a decision about whether to visit the restaurant

## 5. Workflow

```plantuml
@startuml

skin rose

title Check Restaurant Reviews (Fully Dressed)

|#wheat|User|
|#pink|App|

|User|
start
:Navigate to Restaurant search page;
:Enter name into search box;

if (Sort by) is (default) then
:most relevant
(algorithm tbd);
else (other)
:select
* nearest
* highest rating
* most reviews;
endif
if (Select Filters) is (Yes) then
:multi-select
*cuisine
*price range
*reviewed by friends;

:Enter appropriate data for each filter;
while (Is valid data?) is (No)
:Re-enter data;
endwhile (Yes)
else (No)
endif

|App|
:Display relevant 
restaurant results nearby 
user;
|User|


|User|
:Select profile for 
desired restaurant ;

|App|
:Bring up desired 
profile with:
*profile info
*reviews;


|User|
:Scroll down to reviews;
if (Sort by) is (default) then
:most liked;
else (other)
:select
* most recent;
endif

if (Want to filter reviews?) is (Yes) then 
:Select a filter
* friends only
* time range
* tags;

else (No)
endif

:Browse reviews and make decision;



stop
@enduml
```