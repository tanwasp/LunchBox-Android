# Manage Following 

## 1. Primary actor and goals

* __User__: Wants to manage who is following them and unfollow people they no longer want to connect with.

## 2. Other stakeholders and their goals

* __Other User__: Wants to connect with others and follow their freinds.

## 2. Preconditions

* User is logged into a functional account
* User is following Other User

## 4. Postconditions

* User and Other User are no longer following each other

## 4. Workflow

```plantuml
@startuml

skin rose

title Manage Following (Casual)

|#wheat|User|
|#pink|App|

|User|
start
:Navigate to personal profile;
:Select My Friends;

|App|
:Display list of followers' usernames;

|User|
:Select Options next to Other User's username;

|App|
:Bring up options;

|User|
:Click "Unfollow";

|App|
:Save that User and Other User are no longer followers;
:Removes Other User from User's friend list;

stop
@enduml
```