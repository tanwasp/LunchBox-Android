# Assign to List (Brief)

## 1. Primary actor and goals
* __User__: Wants to add a restaurant to a list. Desires an easy-to-use interface and a seamless saving process.

## 2. Other stakeholders and their goals
* __Restaurant Owners__: Seek visibility on the platform and hope to be featured in popular lists. They aim for positive reviews and increased patronage.
* __Friends__: Want to be able to view and interact with the user's list.

## 2. Preconditions
* User is registered and authenticated on the platform.
* The user has one or more existing restaurant lists.
* There is an available database of restaurants that can be searched.

## 4. Postconditions
* The selected restaurant is added to the user's chosen list.

## 5. Workflow


```plantuml
@startuml

skin rose

title Assign to List Restaurant (Brief)

|#wheat|User|
|#pink|App|

|User|
start
:Navigate to restaurant search box;
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
:Select desired restaurant profile;
:Click the Options button to view options;
|App|
:Display option to 'Add to List';
|User|
if (Add to list?) is (Yes) then
|App|
:Display list of lists;
|User|
:Select list to add to;
|App|
:Add restaurant to list;
:Display confirmation message;
else (No)
endif
|User|
stop
@enduml
```