# Accept Follow Request

## 1. Primary actor and goals

__User__: Wants to connect with people they know. Wants to be able to choose whether or not to be followed by someone.

## 2. Other stakeholders and their goals

* __Friend__: Wants to follow you. Wants user to accept the request.

## 2. Preconditions

* User is logged into a functional account
* User received request in Notifications

## 4. Postconditions

* Friend is following User and is listed under their Friends
* Friend can see User's reviews, lists, and other public account info (and vice versa)

## 4. Workflow

```plantuml
@startuml

skin rose

title Accept Follow Request (Brief)

|#wheat|User|
|#pink|App|

|User|
start
:Navigate to Notifications by clicking the bell icon;
:Select the request notification;
:Click "Accept";

|App|
:Save that Friend and User follow each other;
:Delivers acceptance to Friend's Notifications;

stop
@enduml
```