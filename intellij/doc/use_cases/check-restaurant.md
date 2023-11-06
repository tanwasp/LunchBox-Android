# Check Restaurant Review

## 1. Primary actor and goals

* __User__: Wants to find and view ratings/reviews for a specific restaurant. 
They are particularly interested in feedback from their friends.

## 2. Other stakeholders and their goals

* __Restaurant__: Wants the user to see positive reviews and have a positive impression of their business.
* __Friend__: Wants their reviews to be seen, especially by their friends.

## 2. Preconditions

* User is logged into a functional account
* Restaurant profile exists in the app's database and has reviews (with tags)
* A location is set for the user
  * This can be done automatically using the user's device
  * This can be set or changed manually by the user

## 4. Postconditions

* Nothing will be changed on the user's or restaurant's profile
* User will be able to make an informed decision about whether to visit the restaurant

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

if (Sort by) is (Default) then
:Most relevant
(algorithm tbd);
else (Other)
:Select:
* Nearest
* Highest rating
* Most reviews;
endif
if (Select Filters) is (Yes) then
:Multi-select category:
*Cuisine
*Price range
*Reviewed by friends;

:Multi-select tags under given category
(Ex: Check cuisine and then specific 
cuisine tags will pop up and user could
select "Chinese" and "Italian");

else (No)
endif

|App|
:Display relevant restaurant results with
given sorting algorithm and filters;
|User|


|User|
:Select profile for 
desired restaurant;

|App|
:Bring up desired 
profile with:
*Profile info
*Reviews;


|User|
:Scroll down to reviews;
if (Sort reviews by) is (Default) then
:Most liked;
else (Other)
:Select
* Most recent;
endif

if (Want to filter reviews?) is (Yes) then 
:Select
* Friends only
* Time range
* Tags;

else (No)
endif

|App|
:Organize and filter reviews based
on selected criteria;

|User|
:Browse reviews and make decision;

stop
@enduml
```

## Notes
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