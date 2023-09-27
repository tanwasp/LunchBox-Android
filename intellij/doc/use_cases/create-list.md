# Create Restaurant List
## 1. Primary actor and goals
   __User__: Wants to curate and save a list of restaurants based on personal criteria (e.g., cuisine, location, wish list). 
   Desires an easy-to-use interface, the ability to search for and add restaurants, and a seamless saving process.

## 2. Other stakeholders and their goals
   __Restaurant Owners__: Seek visibility on the platform and hope to be featured in popular lists. They aim for positive reviews and increased patronage.
   __Friends__: Want to be able to view and interact with the user's list.
## 3. Preconditions
   User is registered and authenticated on the platform.
   There is an available database of restaurants that can be searched and added to the list.
## 4. Post conditions
   New restaurant list is saved under the User's profile.
   The list is available for viewing, editing and is either private or public based on user preferences.
## 5. Workflow
```plantuml
@startuml

skin rose

title Create Restaurant List (Fully Dressed)

|#wheat|User|
|#pink|App|

|User|
start
:Navigate to 'Create List' section;
:Input a name for the list;
|App|
while (Is list name unique for user?) is (No)
  |User|
  :Provide a different name for the list;
endwhile (Yes)

|User|
:Optionally provide a description for the list;
:Optionally add tags to the list;
:Begins searching for restaurants;

if (Sort by) is (default) then
:most relevant
(algorithm tbd);
else (other)
:select
* nearest
* highest rating
* most reviews;
endif
if (Select Filters) is (Yes) then
:multi-select
*cuisine
*price range
*reviewed by friends
*location;

else (No)
endif



while (Add more restaurants?) is (Yes)
  :Enter restaurant name for search;
  :Optionally change search parameters 
  (filters and sorting);

  |App|
  :Display search results based on 
  User's criteria and parameters;

  |User|
  :Select restaurants from search results;
  :Add selected restaurants to the list;
endwhile (No)

:Decide visibility settings:
* private
* friends-only
* public;

|App|
:Save list based on provided details;
:Display confirmation message;

stop

@enduml

```