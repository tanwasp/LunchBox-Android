# Check User Profile

## 1. Primary actor and goals
* __User__: Aims to search for and view another user's profile to see their basic info, 
public restaurant lists, and reviews. They're particularly interested in 
connecting with friends and seeing their friends' preferences.

## 2. Other stakeholders and their goals
* __Unfollowed Profile Owner__: Wishes to gain followers or connections, and wants their public lists and reviews to be seen.
* __Followed Friends__: Hope to connect with the user and view shared restaurant interests.

## 2. Preconditions
* User is logged into a functional account
* Searched user's profile exists in the app's database
* User's profile has public lists and reviews visible to other users
* User can search for profiles by username

## 4. Postconditions
* Nothing will be changed on the user's or the searched profile's data
* User can decide to follow or connect with the searched profile based on the information they see

## 5. Workflow

```plantuml
@startuml

skin rose

title Check User Profile (Fully Dressed)

|#wheat|User|
|#pink|App|

|User|
start
:Navigate to User search page;
:Enter desired username into search box;

|App|
:Display matching user profiles based on input;

|User|
:Select desired user profile;

|App|
:Show selected user's profile;

|User|
if (Want to view a specific list?) is (Yes) then
:Click on the desired list;
|App|
:Show content of the selected list;
else (No)
endif

|User|
if (Want to view a specific review?) is (Yes) then
  :Click on the desired review;
  |App|
  :Show full content of the selected review;
else (No)
endif

|User|
:Decide on further actions, such as following the profile owner;

stop

@enduml
```

## Notes

A user's profile includes:
* Basic info (e.g. name, profile pic)
* Publicly available restaurant lists
* Reviews