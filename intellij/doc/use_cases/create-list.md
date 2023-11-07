# Create Restaurant List

## 1. Primary actor and goals
* __User__: Wants to curate and save a list of restaurants based on personal criteria (e.g., cuisine, location, favorites, wish list). 
   Desires an easy-to-use interface, the ability to search for and add restaurants, and a seamless saving process.

## 2. Other stakeholders and their goals
* __Restaurant Owners__: Seek visibility on the platform and hope to be featured in popular lists. They aim for positive reviews and increased patronage.
* __Friends__: Want to be able to view and interact with the user's list.

## 3. Preconditions
* User is registered and authenticated on the platform.
* There is an available database of restaurants that can be searched and added to the list.

## 4. Post conditions
* New restaurant list is saved under the User's profile with the given name and contents.
* The list is available for viewing, editing and is either private or public based on user preferences.

## 5. Workflow

```plantuml
@startuml

skin rose

title Create Restaurant List (Fully Dressed)

|#wheat|User|
|#pink|App|

|User|
start
:Navigate to 'List' section of User Profile;
:Click + to add list;

|App|
:Bring up list creation form;

|User|
:Input a name for the list;

|App|
while (Check is list name unique for user?) is (No)
  |User|
  :Provide a different name for the list;
endwhile (Yes)

|User|
:Provide a description for the list (optional);
:Select visibility setting:
* Private
* Friends-only
* Public;
:Click Create List;

|App|
:Save empty list with the given name,
description, and privacy setting;
if (Ask user if they want to add restaurants) is (Yes) then
|User|
while (Add more restaurants?) is (Yes)
:Execute __Add restaurant__;
endwhile
else (No)
endif

|App|
:Save list with all desired contents;
:Display confirmation message;

stop

@enduml

```

```plantuml
@startuml

skin rose

title Add Restaurant (Brief)

|#wheat|User|
|#pink|App|

|App|
start
:Display search box. While box is blank, 
recently reviewed restaurants are suggested
below with + marks next to them;
if (Want recommended restaurant?) is (Yes) then
else (No)

|User|
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

:Multi-select tags under given category;

else (No)
endif

|App|
:Display relevant restaurant results with
given sorting algorithm and filters;

|User|
endif
:Click the + button to add desired restaurants;

stop
@enduml
```