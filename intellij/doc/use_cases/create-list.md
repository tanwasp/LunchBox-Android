# Create Restaurant List
## 1. Primary actor and goals
   __Diner__: Wants to curate and save a list of restaurants based on personal criteria (e.g., cuisine, location, wish list). Desires an easy-to-use interface, the ability to search for and add restaurants, and a seamless saving process.

## 2. Other stakeholders and their goals
   __Restaurant Owners__: Seek visibility on the platform and hope to be featured in popular lists. They aim for positive reviews and increased patronage.
## 3. Preconditions
   Diner is registered and authenticated on the platform.
   There is an available database of restaurants that can be searched and added to the list.
## 4. Postconditions
   New restaurant list is saved under the diner's profile.
   The list is available for viewing, editing, or sharing based on user preferences.
   Notification or update is sent if the list is made public or shared.
## 5. Workflow

   ```plantuml
@startuml

skinparam backgroundColor #EEEBDC
skinparam shadowing false

title Create Restaurant List

start

:Diner is registered and logged in.;

:Diner navigates to the 'Create List' section.;

:Diner enters a unique name for the list;

:Diner optionally adds a description for the list.;

:Diner selects a criterion for the list, e.g., cuisine type or location;

:Diner searches for restaurants to add to the list;

:Display search results based on the diner's inputs;

:Diner selects one or multiple restaurants from results to add;

:System confirms each restaurant added.;

:Diner sets the visibility of the list;

:Diner finalizes and saves the list;

if (Is list name unique?) then (Yes)
:List saved successfully;
else (No)
:Prompt diner to choose a different name or edit the existing list;
endif

if (Is restaurant already in the list?) then (Yes)
:Notify diner that restaurant is already on the list;
else (No)
:Add the restaurant to the list;
endif

if (Wants to remove a restaurant?) then (Yes)
:Diner removes a restaurant from the list;
else (No)
:Proceed;
endif

if (Is list empty?) then (Yes)
:Prompt diner to add at least one restaurant;
else (No)
:Save the list;
endif

:System confirms list creation and updates user's profile.;

stop

@enduml

   ```