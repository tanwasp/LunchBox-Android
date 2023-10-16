# Sequence Diagram

```plantuml
@startuml
actor User as user
<<<<<<< HEAD
participant " : UI" as ui
=======
participant " : TextUI" as ui
participant " : Controller" as controller
participant " : RestaurantLibrary" as lib

ui -> user : Display search prompt
user -> ui : Enter search term (name)
ui -> controller : search(name)
controller -> lib : results = search(name)
ui -> user : Want price filter?
user -> ui : Enter desired price filter
ui -> user : Want location filter?
user -> ui : Enter desired location filter
ui -> controller : filter(filters)
controller -> IFilter : filteredResults = filter(results, filters)
ui -> user : Which sorting algorithm?
user -> ui : Indicate desired sort
ui -> controller : sortBy(sort)
controller -> lib : sortedResults = sortBy(filteredResults, sort)
controller -> lib : finalResults = toString(sortedResults)
controller -> ui : showResults(finalResults)
>>>>>>> 1fe70e343b7b1c937a6d396b5378341820086cb1

@enduml
```

# Design Class Diagram

```plantuml
@startuml
skin rose
'skinparam classAttributeIconSize 0

class Restaurant{
    -restaurantId: String
    --
    getRestaurantInfo(): RestaurantInfo
}

class RestaurantInfo{
    -name: String
    -location: Location
    -rating: number
    -review_list: ArrayList<String>
    -description: String
    -cuisines: Array<String>
}

class Location{
    -address: String
    -city: String
    -state: String
    -country: String
    -postalCode: String
}

class Review{
    -reviewId: String
    -rating: float
    -reviewInfo: ReviewInfo
    -reviewText: String
}

class ReviewInfo{
    -username: String
    -restaurantId: String
    -date: DateTime
}

class User{
    -userName: String
    -joinedDateTime: DateTime
}

class RestaurantLibrary{
    -allRestaurants: ArrayList<Restaurant>
}

' associations
RestaurantLibrary "1" - "1..*" Restaurant : \tIs-information-expert-of\t\t
Restaurant "1" - "1" RestaurantInfo : \tIs-described-by\t\t
RestaurantInfo "1" - "1" Location : \tContains\t\t
Review "*" -- "1" RestaurantInfo : \tIs-part-of\t\t
Review "1" - "1" ReviewInfo : \tIs-described-by\t\t
User "1" - "*" Review : \tCreates\t\t
@enduml
```
