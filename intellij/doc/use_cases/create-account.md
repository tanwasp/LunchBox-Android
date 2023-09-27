# Create Account (Casual)

## 1. Primary actor and goals

* __User__: Wants to create an account so they can use the app. Wants their account be accessible to them and only them.

## 2. Other stakeholders and their goals

* __Friend__: Wants to be able to find the user's account using their username so that they can interact.

## 3. Preconditions

* App is downloaded and opened 
* Connection to WiFi
* Established Username Requirements:
  * 1-30 characters
  * Only letters, numbers, periods, and underscores
  * Not already taken
* Established Password Requirements:
  * 6-16 characters
  * Only letters, numbers, periods, and underscores

## 4. Postconditions

* User is brought to their profile in their logged in account
* Account info is saved under given username
* User can log into their account from another device using username and password
* Account is private and secure

## 4. Workflow

```plantuml
@startuml

skin rose

title Create Account (Casual)

|#wheat|User|
|#pink|App|

|User|
start
if (Select Log In or Create Account?) is (Log In) then
:Execute __Log In__; 
end
else (Create Account)
|App|
:Prompt user to enter an email or phone number;

|User|
if (Skip) is (No) then
:Select which and enters into text box;
:Click Next;
else (Yes)
endif

|App|
:Prompt user to enter username;

|User|
:Types a username;

while (Is valid username?) is (No) 
|User|
:Tries another username;
endwhile (Yes)
:Click next;

|App|
:Prompt user to enter password;

|User|
:Types a password;

while (Is valid password?) is (No) 
|User|
:Retypes password;
endwhile (Yes)

|App|
:Request access to location tracking (GPS);

|User|
if (Share location?) is (Accept) then
:Click Accept;
#pink:Connects to Phone's GPS feature;
else (Deny)
|App|
:Prompt user to enter default location;
|User|
:Enter location and click confirm;
endif

:Click Create Account;
stop

@enduml
```
```plantuml
@startuml

skin rose

title Log In

'define the lanes
|#wheat|User|
|#pink|App|

|App|
start
:Bring up log in page;

|User|
:Enter username and password;

while (Is valid?) is (No)
|App|
:Prompt user to try again;
endwhile (Yes)
:Bring user into their account;

stop
@enduml
```