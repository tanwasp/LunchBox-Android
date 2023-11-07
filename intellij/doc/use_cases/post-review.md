# Post Review

## 1. Primary actor and goals

* __User__: Wants to leave a rating and written review for a restaurant. Wants to be able to share opinion on the restaurant, and for the review to be saved so it can be displayed on the app.

## 2. Other stakeholders and their goals

* __Restaurant__: Wants to avoid negative and fake reviews to avoid hurting their business.
* __Friend__: Wants to be able to see the user's review when visiting the restaurant's page so they can make an informed decision about whether to eat there.


## 2. Preconditions

* User is logged into a functional account
* App has access to phone's Camera Roll (if photo option is selected)

## 4. Postconditions

* Review (and all its components) is saved
  * Including date, username, and restaurant name
* Review is visible on the user's profile page
* Review is visible on the restaurant's page
* Any added restaurants will be saved and visible to all viewers when searched

## 4. Workflow

```plantuml
@startuml

skin rose

title Review Restaurant (Fully Dressed)

|#wheat|User|
|#pink|App|

|User|
start
:Execute __Check Restaurant__;

|App|
if (Restaurant profile exists?) is  ( Yes ) then
:Display matching restaurant profile in the search results;
|User|
:Select restaurant profile;
else ( No ) 
|App|
:Execute __Add Restaurant__;
endif

|App|
:Bring user to restaurant's profile;

|User|
:Select "+" button;

|App|
:Bring up a review form;

|User|
:Tap desired number of stars;
if (Wants to add comment?) is (Yes) then
:Type review in text box;
else (No)
endif
if (Wants to add photo?) is (Yes) then
:Select photo icon;
|App|
:Bring up recent photos using Camera Roll;
|User|
:Select photos to add;
else (No)
endif
if (Wants to add tags?) is (Yes) then
:Click desired tags;
else (No)
endif

:Click "Post";

|App|
:Save post content and metadata;
stop
@enduml
```
