# Sequence Diagram

## Check Out Restaurant

```plantuml
@startuml
hide footbox
skin rose

actor User as user
participant " : TextUI" as ui
participant " : Controller" as controller
participant " : RestaurantLibrary" as lib

ui -> user : Display search prompt
user -> ui : Enter search term (name)
ui -> user : Want price filter?
user -> ui : Enter desired price filter
ui -> user : Want location filter?
user -> ui : Enter desired location filter
ui -> user : Which sorting algorithm?
user -> ui : Indicate desired sort
ui -> controller : search(name)
controller -> lib : results = search(name)
ui -> controller : filter(filters)
controller -> IFilter : filteredResults = filter(results, filters)
ui -> controller : sortBy(sort)
controller -> lib : sortedResults = sortBy(filteredResults, sort)
controller -> lib : finalResults = toString(sortedResults)
controller -> ui : showResults(finalResults)

@enduml
```

## Review Restaurant

# Design Class Diagram

```plantuml
@startuml
skin rose
'skinparam classAttributeIconSize 0

class Restaurant{
    -restaurantId: String
    -name: String
    -location: Location
    -rating: float
    -address: String
    -city: String
    -state: String
    -country: String
    -postalCode: String
    -lat: float
    -lon: float
    -review_list: ArrayList<String>
    -distanceToUser: float
    -description: String
    -priceRange: int
    --
    toString(): String
    setDistToUser(u: User): void
}

class Review{
    -reviewId: String
    -rating: float
    -reviewInfo: ReviewInfo
    -reviewText: String
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
Review "*" -- "1" Restaurant : \tIs-part-of\t\t
User "1" - "*" Review : \tCreates\t\t
@enduml
```
