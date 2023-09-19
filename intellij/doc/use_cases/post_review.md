# Use case name, e.g., process sale

## 1. Primary actor and goals

__User__: wants to leave a rating and written review for a restaurant. Wants to be able to share opinion on the restaurant, and for the review to be saved fully and accurately.

## 2. Other stakeholders and their goals

* __Restaurant__: Wants to avoid negative and fake reviews so that it will not hurt their business.
* __Friend__: Wants to be able to see the user's review when visiting the restaurant's page so they can make an informed decision about whether to eat there.


## 2. Preconditions

* User is logged into a functional account
* Restaurant page already exists on the app

## 4. Postconditions

* Review (and all its components) is saved
* Review is visible on the user's profile page
* Review is visible on the restaurant's page

## 4. Workflow

```plantuml
@startuml

skin rose

title Review Restaurant (Fully Dressed)

'define the lanes
|#wheat|User|
|#pink|App|

|User|
start
:Open app and navigate to search screen;
:Type desired restaurant name in the search bar and hit enter;

|App|
:Display the appropriate restaurant profile in the search results;

|User|
:Select restaurant profile;

|App|
:Bring user to restaurant's profile;

|User|
:Select "Leave Review" button;
```

