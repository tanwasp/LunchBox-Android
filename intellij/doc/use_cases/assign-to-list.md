# Assign to List

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

title Assign to List Restaurant (Casual)

|#wheat|User|
|#pink|App|

|User|
:Execute __Check Restaurant__;
:Select desired restaurant profile;
:Click the Options button to view options;
|App|
:Display option to 'Add to List';
|User|
if (Add to list?) is (Yes) then
|App|
:Display list of lists;
|User|
:Select lists to add to;
|App|
:Add restaurant to lists;
:Display confirmation message;
else (No)
endif
|User|
stop
@enduml
```