# Manage Account

## 1. Primary actor and goals

* __User__: Wants to change their account settings. Wants to change their privacy setting, change their displayed name, set a profile picture, or delete their account.

## 2. Other stakeholders and their goals

* None

## 2. Preconditions

* User is logged into a functional account
* App has access to Camera Roll

## 4. Postconditions

* If account is changed to private, User activity is only visible to followers.
* If account is changed to public, User activity is visible to anyone.
* If name is changed, the new named will be used on profile and all other relevant locations.
* If profile picture is added, the new pic will be displayed next to the user's name and on their profile
* If account is deleted, the account is no longer visible on the app. All of their activity (reviews) are deleted and no longer influence the restaurant's rating.

## 4. Workflow

```plantuml
@startuml

skin rose

title Manage Account (Fully Dressed)

|#wheat|User|
|#pink|App|

|User|
start
:Navigate to user profile;
:Click gear icon;

|App|
:Bring user to Account Settings;

|User|
if (Delete Account?) is (No) then
switch (Desired Change?) 
case (Privacy)
if (Account Private?) is (Yes) then
:Click Public switch icon;
#pink:Makes activity public;
else (No)
:Click Private switch icon;
#pink:Makes activity private;
endif
case (Name)
|User|
:Types a name in indicated text box;

while (Is valid username?) is (No) 
|User|
:Tries another username;
endwhile (Yes)
:Click confirm;
#pink:Saves new name and updates profile;

case (Picture)
|User|
:Select Change Profile Pic;
#pink:Bring up recent photos from Camera Roll;
:Select desired photo and click save;
#pink:Saves photo as user's profile pic;
endswitch

|User|
:Click Save Account Settings;
stop

else (Yes)
|User|
:Select Delete Account;
|App|
:Pop up confirmation message;
|User|
:Select Confirm;
|App|
:Delete user account;
stop

@enduml
```