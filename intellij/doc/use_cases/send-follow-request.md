# Send Follow Request

## 1. Primary actor and goals

* __User__: Wants to request to follow a profile. Wants to connect with people they know and be able to see their reviews.

## 2. Other stakeholders and their goals

* __Friend__: Wants to choose who to follow and accept to follow them. Wants to expand their network. Wants to know who is trying to follow them.

## 2. Preconditions

* User is logged into a functional account
* User knows the username of their intended friend
* Friend's account exists publicly
* User is currently on the search page

## 4. Postconditions

* Request is visible in the friend's Notifications
* Request can be cancelled

## 4. Workflow

```plantuml
@startuml

skin rose

title Send Follow Request (Fully Dressed)

|#wheat|User|
|#pink|App|

|User|
start
:Type Friend's username into search bar;

|App|
:Display matching profile results;

|User|
:Click Friend's profile;

|App|
:Bring up profile;

|User|
if (Already following?) is (No) then
:Click "Follow";

|App|
:Delivers request to friend's Notifications;
stop

else (Yes)
:Follow button says "Following";
stop

@enduml
```